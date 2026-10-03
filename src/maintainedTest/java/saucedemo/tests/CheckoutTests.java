package saucedemo.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import saucedemo.pages.CartPage;
import saucedemo.pages.CheckoutPage;
import saucedemo.pages.InventoryPage;
import saucedemo.pages.LoginPage;
import saucedemo.support.BaseUiTest;
import saucedemo.support.TestData;

import java.math.BigDecimal;

public final class CheckoutTests extends BaseUiTest {

    @Test
    public void autF04_firstNameIsRequired() {
        recordCheckpoint("AUT-F04", "sign in and check empty cart");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(TestData.STANDARD_USER, TestData.DEMO_PASSWORD);

        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.openCart();

        CartPage cartPage = new CartPage(driver);
        cartPage.waitForItemCount(0);
        Assert.assertEquals(cartPage.itemCount(), 0);
        Assert.assertFalse(cartPage.hasCartBadge(),
                "The starting cart displayed a numeric badge.");

        recordCheckpoint("AUT-F04", "add Backpack and open checkout");
        cartPage.continueShopping();
        inventoryPage = new InventoryPage(driver);
        inventoryPage.addProduct(TestData.BACKPACK);
        inventoryPage.waitForCartCount(1);
        inventoryPage.openCart();

        cartPage = new CartPage(driver);
        cartPage.waitForItemCount(1);
        Assert.assertEquals(cartPage.itemCount(), 1);
        Assert.assertTrue(cartPage.containsProduct(TestData.BACKPACK));
        Assert.assertEquals(cartPage.quantity(TestData.BACKPACK), 1);
        Assert.assertEquals(cartPage.cartCount(), 1);

        cartPage.startCheckout();
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        Assert.assertEquals(
                checkoutPage.heading(), "Checkout: Your Information");

        recordCheckpoint("AUT-F04", "submit with First Name blank");
        checkoutPage.enterCustomerDetails(
                "",
                TestData.LAST_NAME,
                TestData.POSTAL_CODE
        );
        checkoutPage.continueCheckout();

        recordCheckpoint("AUT-F04", "required-field error and page state");
        Assert.assertTrue(
                checkoutPage.visibleErrorText()
                        .contains("First Name is required"),
                "The expected first-name error was not displayed."
        );
        Assert.assertEquals(
                checkoutPage.heading(), "Checkout: Your Information");
        Assert.assertTrue(
                driver.getCurrentUrl().contains("/checkout-step-one.html"),
                "Checkout did not remain on Your Information."
        );
        Assert.assertFalse(
                driver.getCurrentUrl().contains("/checkout-step-two.html"),
                "Checkout reached Overview despite the missing first name."
        );
        captureVerifiedCheckpoint("first-name-required");

    }

    @Test
    public void autF06_twoItemOrderCompletes() {
        recordCheckpoint("AUT-F06", "sign in and check empty cart");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(TestData.STANDARD_USER, TestData.DEMO_PASSWORD);

        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.openCart();

        CartPage cartPage = new CartPage(driver);
        cartPage.waitForItemCount(0);
        Assert.assertEquals(cartPage.itemCount(), 0);
        Assert.assertFalse(cartPage.hasCartBadge());

        recordCheckpoint("AUT-F06", "capture prices and add two products");
        cartPage.continueShopping();
        inventoryPage = new InventoryPage(driver);

        String backpackPrice = inventoryPage.displayedPrice(TestData.BACKPACK);
        String bikeLightPrice = inventoryPage.displayedPrice(TestData.BIKE_LIGHT);
        Assert.assertFalse(backpackPrice.isBlank());
        Assert.assertFalse(bikeLightPrice.isBlank());

        inventoryPage.addProduct(TestData.BACKPACK);
        inventoryPage.waitForCartCount(1);
        inventoryPage.addProduct(TestData.BIKE_LIGHT);
        inventoryPage.waitForCartCount(2);
        Assert.assertEquals(inventoryPage.cartCount(), 2);

        recordCheckpoint("AUT-F06", "check Cart entries");
        inventoryPage.openCart();
        cartPage = new CartPage(driver);
        cartPage.waitForItemCount(2);

        Assert.assertEquals(cartPage.itemCount(), 2);
        Assert.assertTrue(cartPage.containsProduct(TestData.BACKPACK));
        Assert.assertTrue(cartPage.containsProduct(TestData.BIKE_LIGHT));
        Assert.assertEquals(cartPage.quantity(TestData.BACKPACK), 1);
        Assert.assertEquals(cartPage.quantity(TestData.BIKE_LIGHT), 1);
        Assert.assertEquals(cartPage.displayedPrice(TestData.BACKPACK), backpackPrice);
        Assert.assertEquals(cartPage.displayedPrice(TestData.BIKE_LIGHT), bikeLightPrice);
        Assert.assertEquals(cartPage.cartCount(), 2);

        recordCheckpoint("AUT-F06", "enter checkout details");
        cartPage.startCheckout();
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.enterCustomerDetails(
                TestData.FIRST_NAME,
                TestData.LAST_NAME,
                TestData.POSTAL_CODE
        );
        checkoutPage.continueCheckout();
        checkoutPage.waitForOverview();

        recordCheckpoint("AUT-F06", "check Overview entries");
        Assert.assertEquals(checkoutPage.heading(), "Checkout: Overview");
        Assert.assertEquals(checkoutPage.overviewItemCount(), 2);
        Assert.assertTrue(checkoutPage.overviewContainsProduct(TestData.BACKPACK));
        Assert.assertTrue(checkoutPage.overviewContainsProduct(TestData.BIKE_LIGHT));
        Assert.assertEquals(checkoutPage.overviewQuantity(TestData.BACKPACK), 1);
        Assert.assertEquals(checkoutPage.overviewQuantity(TestData.BIKE_LIGHT), 1);
        Assert.assertEquals(
                checkoutPage.overviewPrice(TestData.BACKPACK), backpackPrice);
        Assert.assertEquals(
                checkoutPage.overviewPrice(TestData.BIKE_LIGHT), bikeLightPrice);
        Assert.assertEquals(checkoutPage.cartCount(), 2);

        recordCheckpoint("AUT-F06", "reconcile Overview amounts");
        BigDecimal expectedItemTotal =
                money(checkoutPage.overviewPrice(TestData.BACKPACK))
                        .multiply(BigDecimal.valueOf(
                                checkoutPage.overviewQuantity(TestData.BACKPACK)))
                        .add(money(checkoutPage.overviewPrice(TestData.BIKE_LIGHT))
                                .multiply(BigDecimal.valueOf(
                                        checkoutPage.overviewQuantity(
                                                TestData.BIKE_LIGHT))));

        BigDecimal itemTotal = money(checkoutPage.itemTotalText());
        BigDecimal tax = money(checkoutPage.taxText());
        BigDecimal total = money(checkoutPage.totalText());

        Assert.assertEquals(
                itemTotal.compareTo(expectedItemTotal), 0,
                "Item total did not equal price times quantity.");
        Assert.assertEquals(
                total.compareTo(itemTotal.add(tax)), 0,
                "Total did not equal Item total plus displayed Tax.");

        System.out.printf(
                "AMOUNTS id=AUT-F06 itemTotal=%s tax=%s total=%s%n",
                itemTotal, tax, total);
        captureVerifiedCheckpoint("overview-amounts");

        recordCheckpoint("AUT-F06", "finish order");
        checkoutPage.finishOrder();
        checkoutPage.waitForCompletion();

        recordCheckpoint("AUT-F06", "check completion and PDF control");
        Assert.assertEquals(checkoutPage.heading(), "Checkout: Complete!");
        Assert.assertEquals(
                checkoutPage.thankYouText(), "Thank you for your order!");
        Assert.assertTrue(
                checkoutPage.pdfOrderControlVisible(),
                "Generate PDF order control was not visible.");
        Assert.assertFalse(
                checkoutPage.hasCartBadge(),
                "A numeric cart badge remained after completion.");
        
        Assert.assertEquals(checkoutPage.cartCount(), 0);
        captureVerifiedCheckpoint("order-complete");

    }

    private static BigDecimal money(String text) {
        int dollar = text.lastIndexOf('$');
        if (dollar < 0) {
            throw new IllegalArgumentException("No dollar amount in: " + text);
        }
        return new BigDecimal(text.substring(dollar + 1).trim());
    }
}