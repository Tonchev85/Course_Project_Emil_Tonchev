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

        productsPage.Checkout("Click buttons");

        ProductsPage productsPage1 = new ProductsPage(driver);
        ProductsPage productsPage2 = productsPage1.login("Emil", "Tonchev", "1619");

        productsPage.Continue("Click Buttons");

        Assert.assertTrue(productsPage2.ponyExpress("Pony picture"));
    }
}
