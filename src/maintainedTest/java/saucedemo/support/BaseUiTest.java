package saucedemo.support;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public abstract class BaseUiTest {
    protected WebDriver driver;

    private String automationId = "unassigned";
    private String checkpoint = "browser start";
    private final List<CapturedCheckpoint> capturedCheckpoints = new ArrayList<>();

    private record CapturedCheckpoint(
            String name, String step, String url, byte[] png) {
    }

    @BeforeMethod(alwaysRun = true)
    public void openBrowser() {
        automationId = "unassigned";
        checkpoint = "browser start";
        capturedCheckpoints.clear();

        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption(
                "prefs",
                Map.of("profile.password_manager_leak_detection", false)
        );
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get(TestData.BASE_URL);
    }

    protected final void recordCheckpoint(String id, String name) {
        automationId = id;
        checkpoint = name;
        System.out.printf("CHECKPOINT id=%s step=%s%n", id, name);
    }

    // Call this only after the assertions for the visible state have passed.
    protected final void captureVerifiedCheckpoint(String name) {
        if (driver == null || "unassigned".equals(automationId)) {
            throw new IllegalStateException(
                    "Cannot capture a checkpoint before the browser and F ID exist."
            );
        }

        byte[] png = ((TakesScreenshot) driver)
                .getScreenshotAs(OutputType.BYTES);
        capturedCheckpoints.add(new CapturedCheckpoint(
                name, checkpoint, driver.getCurrentUrl(), png
        ));
    }

    @AfterMethod(alwaysRun = true)
    public void closeBrowser(ITestResult result) {
        boolean failed = result.getStatus() == ITestResult.FAILURE;
        boolean passed = result.getStatus() == ITestResult.SUCCESS;

        try {
            if (failed) {
                System.err.printf(
                        "FAIL id=%s checkpoint=%s%n",
                        automationId, checkpoint
                );
                if (result.getThrowable() != null) {
                    result.getThrowable().printStackTrace(System.err);
                }

                if (driver != null) {
                    try {
                        saveFailureScreenshot(result);
                    } catch (Exception captureError) {
                        System.err.printf(
                                "Failure screenshot unavailable: %s%n",
                                captureError
                        );
                    }
                } else {
                    System.err.println(
                            "Failure screenshot unavailable: browser did not start."
                    );
                }
            }

            try {
                if (passed && capturedCheckpoints.isEmpty()) {
                    throw new IllegalStateException(
                            "No verified screenshots were captured for this test."
                    );
                }
                saveCapturedCheckpoints(result);
            } catch (Exception evidenceError) {
                if (failed) {
                    System.err.printf(
                            "Checkpoint screenshots unavailable: %s%n",
                            evidenceError
                    );
                } else {
                    throw new IllegalStateException(
                            "Screenshot evidence is incomplete.",
                            evidenceError
                    );
                }
            }
        } finally {
            if (driver != null) {
                try {
                    driver.quit();
                } catch (RuntimeException cleanupError) {
                    if (failed) {
                        System.err.printf(
                                "Browser cleanup also failed: %s%n",
                                cleanupError
                        );
                    } else {
                        throw cleanupError;
                    }
                } finally {
                    driver = null;
                    capturedCheckpoints.clear();
                }
            } else {
                capturedCheckpoints.clear();
            }
        }

        if (passed) {
            System.out.printf("PASS id=%s%n", automationId);
        }
    }

    private void saveCapturedCheckpoints(ITestResult result)
            throws IOException {
        if (capturedCheckpoints.isEmpty()) {
            return;
        }

        String outcome = switch (result.getStatus()) {
            case ITestResult.SUCCESS -> "successful-tests";
            case ITestResult.FAILURE -> "failed-tests";
            default -> "incomplete-tests";
        };

        Path folder = captureRoot()
                .resolve(outcome)
                .resolve(safeName(automationId));
        Files.createDirectories(folder);

        for (int i = 0; i < capturedCheckpoints.size(); i++) {
            CapturedCheckpoint image = capturedCheckpoints.get(i);
            Path file = folder.resolve(String.format(
                    "%02d-%s.png", i + 1, safeName(image.name())
            ));

            Files.write(
                    file, image.png(),
                    StandardOpenOption.CREATE_NEW,
                    StandardOpenOption.WRITE
            );

            System.out.printf(
                    "SCREENSHOT_EVIDENCE id=%s outcome=%s step=%s url=%s png=%s%n",
                    automationId, outcome, image.step(), image.url(), file
            );
        }
    }

    private void saveFailureScreenshot(ITestResult result)
            throws IOException {
        Path folder = captureRoot()
                .resolve("failed-tests")
                .resolve(safeName(automationId));
        Files.createDirectories(folder);

        String filename = "failure-"
                + safeName(result.getMethod().getMethodName())
                + "-" + System.currentTimeMillis() + ".png";
        Path screenshot = folder.resolve(filename);

        byte[] png = ((TakesScreenshot) driver)
                .getScreenshotAs(OutputType.BYTES);
        Files.write(
                screenshot, png,
                StandardOpenOption.CREATE_NEW,
                StandardOpenOption.WRITE
        );

        String url;
        try {
            url = driver.getCurrentUrl();
        } catch (RuntimeException browserError) {
            url = "unavailable";
        }

        System.err.printf(
                "FAILURE_EVIDENCE id=%s checkpoint=%s url=%s png=%s%n",
                automationId, checkpoint, url, screenshot
        );
    }

    private static Path captureRoot() {
        String directory = System.getProperty("milestoneF.captureDir");
        if (directory == null || directory.isBlank()) {
            throw new IllegalStateException(
                    "milestoneF.captureDir was not provided by the Gradle task."
            );
        }
        return Path.of(directory);
    }

    private static String safeName(String value) {
        String safe = value.replaceAll("[^A-Za-z0-9_-]", "-");
        if (safe.isBlank()) {
            throw new IllegalArgumentException("Empty screenshot name.");
        }
        return safe;
    }
}