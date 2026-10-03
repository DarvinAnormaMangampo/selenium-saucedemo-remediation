package saucedemo.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public final class CartPage {
    private static final By TITLE =
            By.cssSelector("[data-test='title']");
    private static final By CART_ITEM =
            By.cssSelector("[data-test='inventory-item']");
    private static final By PRODUCT_NAME =
            By.cssSelector("[data-test='inventory-item-name']");
    private static final By PRODUCT_PRICE =
            By.cssSelector("[data-test='inventory-item-price']");
    private static final By ITEM_QUANTITY =
            By.cssSelector("[data-test='item-quantity']");
    private static final By REMOVE_BUTTON =
            By.cssSelector("button[data-test^='remove-']");
    private static final By CART_BADGE =
            By.cssSelector("[data-test='shopping-cart-badge']");
    private static final By CONTINUE_SHOPPING =
            By.cssSelector("[data-test='continue-shopping']");
    private static final By CHECKOUT =
            By.cssSelector("[data-test='checkout']");

    private final WebDriver driver;
    private final WebDriverWait wait;

    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.urlContains("/cart.html"));
        wait.until(ExpectedConditions.textToBePresentInElementLocated(
                TITLE, "Your Cart"));
    }

    private WebElement productCard(String productName) {
        return wait.until(d -> d.findElements(CART_ITEM).stream()
                .filter(card -> productName.equals(
                        card.findElement(PRODUCT_NAME).getText()))
                .findFirst()
                .orElse(null));
    }

    public boolean containsProduct(String productName) {
        return driver.findElements(CART_ITEM).stream()
                .anyMatch(card -> productName.equals(
                        card.findElement(PRODUCT_NAME).getText()));
    }

    public String displayedPrice(String productName) {
        return productCard(productName)
                .findElement(PRODUCT_PRICE).getText();
    }

    public int quantity(String productName) {
        return Integer.parseInt(productCard(productName)
                .findElement(ITEM_QUANTITY).getText());
    }

    public int itemCount() {
        return driver.findElements(CART_ITEM).size();
    }

    public void waitForItemCount(int expected) {
        wait.until(d -> d.findElements(CART_ITEM).size() == expected);
    }

    public void removeProduct(String productName) {
        WebElement button = productCard(productName)
                .findElement(REMOVE_BUTTON);
        wait.until(ExpectedConditions.elementToBeClickable(button)).click();
    }

    public int cartCount() {
        var badges = driver.findElements(CART_BADGE);
        return badges.isEmpty() ? 0 : Integer.parseInt(badges.get(0).getText());
    }

    public boolean hasCartBadge() {
        return !driver.findElements(CART_BADGE).isEmpty();
    }

    public void continueShopping() {
        wait.until(ExpectedConditions.elementToBeClickable(
                CONTINUE_SHOPPING)).click();
    }

    public void startCheckout() {
        wait.until(ExpectedConditions.elementToBeClickable(CHECKOUT)).click();
    }
}