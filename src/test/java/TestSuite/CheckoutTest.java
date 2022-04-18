package TestSuite;

import base.TestUtil;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductsPage;


public class CheckoutTest extends TestUtil {

    @Test
    public void addItemToCheckout() {
        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productsPage = loginPage.login("standard_user", "secret_sauce");
        productsPage.addItemToTheCart("onesie");
        productsPage.addItemToTheCart("backpack");
        productsPage.addItemToTheCart("bolt-t-shirt");

        productsPage.checkout("Click button and fluent wait");

        Assert.assertTrue(productsPage.errorMessageIsDisplayed("First Name is required"));
    }
}
