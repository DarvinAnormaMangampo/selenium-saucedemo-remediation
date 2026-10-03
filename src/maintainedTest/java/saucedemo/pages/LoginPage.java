package saucedemo.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public final class LoginPage {
    private static final By USERNAME =
            By.cssSelector("[data-test='username']");
    private static final By PASSWORD =
            By.cssSelector("[data-test='password']");
    private static final By LOGIN_BUTTON =
            By.cssSelector("[data-test='login-button']");
    private static final By ERROR =
            By.cssSelector("[data-test='error']");

    private final WebDriver driver;
    private final WebDriverWait wait;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Confirm this page is ready before a test uses it.
        wait.until(ExpectedConditions.visibilityOfElementLocated(USERNAME));
        wait.until(ExpectedConditions.visibilityOfElementLocated(PASSWORD));
        wait.until(ExpectedConditions.elementToBeClickable(LOGIN_BUTTON));
    }

    public void login(String username, String password) {
        WebElement usernameField =
                wait.until(ExpectedConditions.visibilityOfElementLocated(USERNAME));
        usernameField.clear();
        usernameField.sendKeys(username);

        WebElement passwordField =
                wait.until(ExpectedConditions.visibilityOfElementLocated(PASSWORD));
        passwordField.clear();
        passwordField.sendKeys(password);

        wait.until(ExpectedConditions.elementToBeClickable(LOGIN_BUTTON)).click();
    }

    public String visibleErrorText() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(ERROR)
        ).getText();
    }

    public boolean isLoginFormVisible() {
        return driver.findElements(USERNAME).stream().anyMatch(WebElement::isDisplayed)
                && driver.findElements(PASSWORD).stream().anyMatch(WebElement::isDisplayed);
    }
}