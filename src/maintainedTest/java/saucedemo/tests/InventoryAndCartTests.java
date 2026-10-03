package saucedemo.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import saucedemo.pages.CartPage;
import saucedemo.pages.InventoryPage;
import saucedemo.pages.LoginPage;
import saucedemo.support.BaseUiTest;
import saucedemo.support.TestData;

import java.math.BigDecimal;
import java.util.List;

public final class InventoryAndCartTests extends BaseUiTest {

    @Test
    public void autF03_twoItemCartAndRemovals() {
        recordCheckpoint("AUT-F03", "sign in and check empty cart");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(TestData.STANDARD_USER, TestData.DEMO_PASSWORD);

        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.openCart();

        CartPage cartPage = new CartPage(driver);
        cartPage.waitForItemCount(0);
        Assert.assertEquals(cartPage.itemCount(), 0);
        Assert.assertFalse(cartPage.hasCartBadge(),
                "An empty cart displayed a numeric badge.");

        recordCheckpoint("AUT-F03", "capture current product prices");
        cartPage.continueShopping();
        inventoryPage = new InventoryPage(driver);

        String backpackPrice = inventoryPage.displayedPrice(TestData.BACKPACK);
        String bikeLightPrice = inventoryPage.displayedPrice(TestData.BIKE_LIGHT);
        Assert.assertFalse(backpackPrice.isBlank(), "Backpack price was blank.");
        Assert.assertFalse(bikeLightPrice.isBlank(), "Bike Light price was blank.");

        recordCheckpoint("AUT-F03", "add both products");
        inventoryPage.addProduct(TestData.BACKPACK);
        inventoryPage.waitForCartCount(1);
        inventoryPage.addProduct(TestData.BIKE_LIGHT);
        inventoryPage.waitForCartCount(2);
        Assert.assertEquals(inventoryPage.cartCount(), 2);

        recordCheckpoint("AUT-F03", "check both cart entries");
        inventoryPage.openCart();
        cartPage = new CartPage(driver);
        cartPage.waitForItemCount(2);

        Assert.assertEquals(cartPage.itemCount(), 2);
        Assert.assertTrue(cartPage.containsProduct(TestData.BACKPACK));
        Assert.assertTrue(cartPage.containsProduct(TestData.BIKE_LIGHT));
        Assert.assertEquals(cartPage.quantity(TestData.BACKPACK), 1);
        Assert.assertEquals(cartPage.quantity(TestData.BIKE_LIGHT), 1);
        Assert.assertEquals(
                cartPage.displayedPrice(TestData.BACKPACK), backpackPrice);
        Assert.assertEquals(
                cartPage.displayedPrice(TestData.BIKE_LIGHT), bikeLightPrice);
        Assert.assertEquals(cartPage.cartCount(), 2);
        captureVerifiedCheckpoint("cart-two-items");

        recordCheckpoint("AUT-F03", "remove Backpack in Cart");
        cartPage.removeProduct(TestData.BACKPACK);
        cartPage.waitForItemCount(1);

        Assert.assertEquals(cartPage.itemCount(), 1);
        Assert.assertFalse(cartPage.containsProduct(TestData.BACKPACK));
        Assert.assertTrue(cartPage.containsProduct(TestData.BIKE_LIGHT));
        Assert.assertEquals(cartPage.quantity(TestData.BIKE_LIGHT), 1);
        Assert.assertEquals(
                cartPage.displayedPrice(TestData.BIKE_LIGHT), bikeLightPrice);
        Assert.assertEquals(cartPage.cartCount(), 1);
        captureVerifiedCheckpoint("cart-one-item");
        
        recordCheckpoint("AUT-F03", "remove Bike Light on Products");
        cartPage.continueShopping();
        inventoryPage = new InventoryPage(driver);
        inventoryPage.removeProduct(TestData.BIKE_LIGHT);
        inventoryPage.waitForCartCount(0);
        
        Assert.assertFalse(inventoryPage.isProductSelected(TestData.BIKE_LIGHT));
        Assert.assertEquals(inventoryPage.cartCount(), 0);
        captureVerifiedCheckpoint("products-after-removal");

        recordCheckpoint("AUT-F03", "reopen empty cart");
        inventoryPage.openCart();
        cartPage = new CartPage(driver);
        cartPage.waitForItemCount(0);

        Assert.assertEquals(cartPage.itemCount(), 0);
        
        Assert.assertFalse(cartPage.hasCartBadge(),
                "The empty cart still displayed a numeric badge.");
        captureVerifiedCheckpoint("cart-empty");
    }

    @Test
    public void autF05_pricesSortLowToHigh() {
        recordCheckpoint("AUT-F05", "sign in and inspect product prices");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(TestData.STANDARD_USER, TestData.DEMO_PASSWORD);

        InventoryPage inventoryPage = new InventoryPage(driver);
        List<String> startingPrices = inventoryPage.displayedPricesInOrder();
        Assert.assertTrue(startingPrices.size() >= 2,
                "Fewer than two visible prices were available.");

        recordCheckpoint("AUT-F05", "select price low to high");
        inventoryPage.selectPriceLowToHigh();
        Assert.assertEquals(inventoryPage.selectedSortValue(), "lohi");
        Assert.assertEquals(
                inventoryPage.selectedSortLabel(), "Price (low to high)");

        recordCheckpoint("AUT-F05", "parse displayed prices in order");
        List<String> priceLabels = inventoryPage.displayedPricesInOrder();
        Assert.assertTrue(priceLabels.size() >= 2,
                "Fewer than two visible prices remained after sorting.");

        List<BigDecimal> prices = priceLabels.stream()
                .map(label -> new BigDecimal(label.replace("$", "").trim()))
                .toList();

        recordCheckpoint("AUT-F05", "compare each adjacent price");
        for (int i = 1; i < prices.size(); i++) {
            Assert.assertTrue(
                    prices.get(i).compareTo(prices.get(i - 1)) >= 0,
                    "Price decreased between positions " + i + " and "
                            + (i + 1) + ": " + priceLabels.get(i - 1)
                            + " > " + priceLabels.get(i)
            );
        }
        captureVerifiedCheckpoint("prices-low-to-high");
    }
}