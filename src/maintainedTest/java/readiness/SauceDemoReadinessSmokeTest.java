package readiness;

import java.time.Duration;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class SauceDemoReadinessSmokeTest {

    private static final String SAUCE_DEMO_URL =
            "https://www.saucedemo.com/";

    private static final Duration WAIT_TIMEOUT =
            Duration.ofSeconds(10);

    private WebDriver driver;

    @Test
    public void confirmsSauceDemoLoginPageIsAutomationReady() {
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);

        Capabilities capabilities =
                ((RemoteWebDriver) driver).getCapabilities();

        String driverVersion = "not reported";
        Object chromeCapability =
                capabilities.getCapability("chrome");

        if (chromeCapability instanceof Map<?, ?> chromeDetails) {
            Object reportedDriverVersion =
                    chromeDetails.get("chromedriverVersion");

            if (reportedDriverVersion != null) {
                driverVersion = reportedDriverVersion.toString();
            }
        }

        System.out.printf(
                "Browser: %s%n",
                capabilities.getBrowserName()
        );
        System.out.printf(
                "Browser version: %s%n",
                capabilities.getBrowserVersion()
        );
        System.out.printf(
                "Driver version: %s%n",
                driverVersion
        );

        driver.get(SAUCE_DEMO_URL);

        WebDriverWait wait =
                new WebDriverWait(driver, WAIT_TIMEOUT);

        wait.until(ExpectedConditions.urlToBe(SAUCE_DEMO_URL));
        wait.until(ExpectedConditions.titleIs("Swag Labs"));

        WebElement username = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector("[data-test='username']")
                )
        );

        WebElement password = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector("[data-test='password']")
                )
        );

        WebElement loginButton = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.cssSelector("[data-test='login-button']")
                )
        );

        Assert.assertTrue(
                username.isDisplayed(),
                "The username field was not visible."
        );
        Assert.assertTrue(
                password.isDisplayed(),
                "The password field was not visible."
        );
        Assert.assertTrue(
                loginButton.isDisplayed(),
                "The Login control was not visible."
        );
        Assert.assertTrue(
                loginButton.isEnabled(),
                "The Login control was not enabled."
        );

        Assert.assertEquals(
                username.getAttribute("value"),
                "",
                "The username field was not blank."
        );
        Assert.assertEquals(
                password.getAttribute("value"),
                "",
                "The password field was not blank."
        );
    }

    @AfterMethod(alwaysRun = true)
    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
