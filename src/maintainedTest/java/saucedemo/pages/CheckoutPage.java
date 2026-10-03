package saucedemo.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public final class CheckoutPage {
    private static final By TITLE =
            By.cssSelector("[data-test='title']");
    private static final By FIRST_NAME =
            By.cssSelector("[data-test='firstName']");
    private static final By LAST_NAME =
            By.cssSelector("[data-test='lastName']");
    private static final By POSTAL_CODE =
            By.cssSelector("[data-test='postalCode']");
    private static final By CONTINUE =
            By.cssSelector("[data-test='continue']");
    private static final By ERROR =
            By.cssSelector("[data-test='error']");
    private static final By OVERVIEW_ITEM =
            By.cssSelector("[data-test='inventory-item']");
    private static final By PRODUCT_NAME =
            By.cssSelector("[data-test='inventory-item-name']");
    private static final By PRODUCT_PRICE =
            By.cssSelector("[data-test='inventory-item-price']");
    private static final By ITEM_QUANTITY =
            By.cssSelector("[data-test='item-quantity']");
    private static final By ITEM_TOTAL =
            By.cssSelector("[data-test='subtotal-label']");
    private static final By TAX =
            By.cssSelector("[data-test='tax-label']");
    private static final By TOTAL =
            By.cssSelector("[data-test='total-label']");
    private static final By FINISH =
            By.cssSelector("[data-test='finish']");
    private static final By COMPLETE_HEADER =
            By.cssSelector("[data-test='complete-header']");
    private static final By CART_BADGE =
            By.cssSelector("[data-test='shopping-cart-badge']");
    private static final By PDF_ORDER_CONTROL =
            By.xpath("//*[self::button or self::a]"
                    + "[normalize-space(.)='Generate PDF order']");        

    private final WebDriver driver;
    private final WebDriverWait wait;

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.urlContains("/checkout-step-one.html"));
        wait.until(ExpectedConditions.textToBePresentInElementLocated(
                TITLE, "Checkout: Your Information"));
    }

    public String heading() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(TITLE)
        ).getText();
    }

    public void enterCustomerDetails(
            String firstName, String lastName, String postalCode) {
        replaceText(FIRST_NAME, firstName);
        replaceText(LAST_NAME, lastName);
        replaceText(POSTAL_CODE, postalCode);
    }

    private void replaceText(By locator, String value) {
        WebElement field = wait.until(
                ExpectedConditions.visibilityOfElementLocated(locator));
        field.clear();
        if (!value.isEmpty()) {
            field.sendKeys(value);
        }
    }

    public void continueCheckout() {
        wait.until(ExpectedConditions.elementToBeClickable(CONTINUE)).click();
    }

    public String visibleErrorText() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(ERROR)
        ).getText();
    }

    public void waitForOverview() {
    wait.until(ExpectedConditions.urlContains("/checkout-step-two.html"));
    wait.until(ExpectedConditions.textToBePresentInElementLocated(
            TITLE, "Checkout: Overview"));
    wait.until(ExpectedConditions.visibilityOfElementLocated(OVERVIEW_ITEM));
    }

    private WebElement overviewProductCard(String productName) {
        return wait.until(d -> d.findElements(OVERVIEW_ITEM).stream()
                .filter(card -> productName.equals(
                        card.findElement(PRODUCT_NAME).getText()))
                .findFirst()
                .orElse(null));
    }

    public int overviewItemCount() {
        return driver.findElements(OVERVIEW_ITEM).size();
    }

    public boolean overviewContainsProduct(String productName) {
        return driver.findElements(OVERVIEW_ITEM).stream()
                .anyMatch(card -> productName.equals(
                        card.findElement(PRODUCT_NAME).getText()));
    }

    public String overviewPrice(String productName) {
        return overviewProductCard(productName)
                .findElement(PRODUCT_PRICE).getText();
    }

    public int overviewQuantity(String productName) {
        return Integer.parseInt(overviewProductCard(productName)
                .findElement(ITEM_QUANTITY).getText());
    }

    public String itemTotalText() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(ITEM_TOTAL))
                .getText();
    }

    public String taxText() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(TAX))
                .getText();
    }

    public String totalText() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(TOTAL))
                .getText();
    }

    public int cartCount() {
        var badges = driver.findElements(CART_BADGE);
        return badges.isEmpty() ? 0 : Integer.parseInt(badges.get(0).getText());
    }

    public boolean hasCartBadge() {
        return !driver.findElements(CART_BADGE).isEmpty();
    }

    public void finishOrder() {
        wait.until(ExpectedConditions.elementToBeClickable(FINISH)).click();
    }

    public void waitForCompletion() {
        wait.until(ExpectedConditions.urlContains("/checkout-complete.html"));
        wait.until(ExpectedConditions.textToBePresentInElementLocated(
                TITLE, "Checkout: Complete!"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(COMPLETE_HEADER));
    }

    public String thankYouText() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(COMPLETE_HEADER))
                .getText();
    }

    public boolean pdfOrderControlVisible() {
        return driver.findElements(PDF_ORDER_CONTROL).stream()
                .anyMatch(WebElement::isDisplayed);
    }
}