package saucedemo.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import org.openqa.selenium.support.ui.Select;

public final class InventoryPage {
    private static final By TITLE =
            By.cssSelector("[data-test='title']");
    private static final By INVENTORY_ITEM =
            By.cssSelector("[data-test='inventory-item']");
    private static final By PRODUCT_NAME =
            By.cssSelector("[data-test='inventory-item-name']");
    private static final By PRODUCT_PRICE =
            By.cssSelector("[data-test='inventory-item-price']");
    private static final By ADD_BUTTON =
            By.cssSelector("button[data-test^='add-to-cart-']");
    private static final By REMOVE_BUTTON =
            By.cssSelector("button[data-test^='remove-']");
    private static final By CART_LINK =
            By.cssSelector("[data-test='shopping-cart-link']");
    private static final By CART_BADGE =
            By.cssSelector("[data-test='shopping-cart-badge']");
    private static final By SORT_CONTROL =
            By.cssSelector("[data-test='product-sort-container']");



    private final WebDriver driver;
    private final WebDriverWait wait;

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.urlContains("/inventory.html"));
        wait.until(ExpectedConditions.textToBePresentInElementLocated(
                TITLE, "Products"
        ));
        wait.until(ExpectedConditions.visibilityOfElementLocated(INVENTORY_ITEM));
    }

    public String heading() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(TITLE)
        ).getText();
    }

    public long visibleItemCount() {
        return driver.findElements(INVENTORY_ITEM).stream()
                .filter(WebElement::isDisplayed)
                .count();
    }

    public static boolean productsContentVisible(WebDriver driver) {
    boolean headingVisible = driver.findElements(TITLE).stream()
            .anyMatch(element -> element.isDisplayed()
                    && "Products".equals(element.getText()));
    boolean itemVisible = driver.findElements(INVENTORY_ITEM).stream()
            .anyMatch(WebElement::isDisplayed);
    return headingVisible || itemVisible;
    }
    
    private WebElement productCard(String productName) {
    return wait.until(d -> d.findElements(INVENTORY_ITEM).stream()
            .filter(card -> productName.equals(
                    card.findElement(PRODUCT_NAME).getText()))
            .findFirst()
            .orElse(null));
    }

    public String displayedPrice(String productName) {
        return productCard(productName)
                .findElement(PRODUCT_PRICE).getText();
    }

    public void addProduct(String productName) {
        WebElement button = productCard(productName)
                .findElement(ADD_BUTTON);
        wait.until(ExpectedConditions.elementToBeClickable(button)).click();
    }

    public void removeProduct(String productName) {
        WebElement button = productCard(productName)
                .findElement(REMOVE_BUTTON);
        wait.until(ExpectedConditions.elementToBeClickable(button)).click();
    }

    public void openCart() {
        wait.until(ExpectedConditions.elementToBeClickable(CART_LINK)).click();
    }

    public void waitForCartCount(int expected) {
        wait.until(d -> {
            var badges = d.findElements(CART_BADGE);
            if (expected == 0) {
                return badges.isEmpty();
            }
            return badges.size() == 1
                    && Integer.toString(expected).equals(badges.get(0).getText());
        });
    }

    public int cartCount() {
        var badges = driver.findElements(CART_BADGE);
        return badges.isEmpty() ? 0 : Integer.parseInt(badges.get(0).getText());
    }

    public boolean hasCartBadge() {
        return !driver.findElements(CART_BADGE).isEmpty();
    }

    public boolean isProductSelected(String productName) {
    return !productCard(productName)
            .findElements(REMOVE_BUTTON).isEmpty();
    }

    public void selectPriceLowToHigh() {
    new Select(wait.until(
            ExpectedConditions.elementToBeClickable(SORT_CONTROL)))
            .selectByValue("lohi");

    wait.until(d -> "lohi".equals(
            new Select(d.findElement(SORT_CONTROL))
                    .getFirstSelectedOption()
                    .getDomProperty("value")));
    }

    public String selectedSortValue() {
        return new Select(wait.until(
                ExpectedConditions.visibilityOfElementLocated(SORT_CONTROL)))
                .getFirstSelectedOption()
                .getDomProperty("value");
    }

    public String selectedSortLabel() {
        return new Select(wait.until(
                ExpectedConditions.visibilityOfElementLocated(SORT_CONTROL)))
                .getFirstSelectedOption()
                .getText();
    }

    public List<String> displayedPricesInOrder() {
        return driver.findElements(INVENTORY_ITEM).stream()
                .filter(WebElement::isDisplayed)
                .map(card -> card.findElement(PRODUCT_PRICE).getText())
                .toList();
    }
}