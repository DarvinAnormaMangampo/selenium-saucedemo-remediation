package saucedemo.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import saucedemo.pages.InventoryPage;
import saucedemo.pages.LoginPage;
import saucedemo.support.BaseUiTest;
import saucedemo.support.TestData;

public final class AuthenticationTests extends BaseUiTest {

    @Test
    public void autF01_standardLoginShowsInventory() {
        recordCheckpoint("AUT-F01", "submit standard login");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(
                TestData.STANDARD_USER,
                TestData.DEMO_PASSWORD
        );

        recordCheckpoint("AUT-F01", "Products page and inventory");
        InventoryPage inventoryPage = new InventoryPage(driver);

        Assert.assertTrue(
                driver.getCurrentUrl().contains("/inventory.html"),
                "Standard login did not reach Products."
        );
        Assert.assertEquals(inventoryPage.heading(), "Products");
        Assert.assertTrue(
                inventoryPage.visibleItemCount() > 0,
                "No visible inventory item was found."
        );
        captureVerifiedCheckpoint("products-inventory");

    }

    @Test
    public void autF02_lockedOutLoginIsRejected() {
        recordCheckpoint("AUT-F02", "submit locked-out login");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(
                TestData.LOCKED_OUT_USER,
                TestData.DEMO_PASSWORD
        );

        recordCheckpoint("AUT-F02", "rejection and sign-in state");
        Assert.assertEquals(
                loginPage.visibleErrorText(),
                "Epic sadface: Sorry, this user has been locked out."
        );
        Assert.assertTrue(
                loginPage.isLoginFormVisible(),
                "Sign-in controls did not remain visible."
        );
        Assert.assertFalse(
                driver.getCurrentUrl().contains("/inventory.html"),
                "Locked-out account reached Products."
        );
        Assert.assertFalse(
                InventoryPage.productsContentVisible(driver),
                "Products heading or inventory was visible."
        );
        captureVerifiedCheckpoint("locked-out-error");

    }
}