package TestSuite;

import base.TestUtil;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductsPage;

public class AddItemToCart extends TestUtil {

    @Test
    public void addItemToTheCart(){
        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productsPage = loginPage.login("standard_user", "secret_sauce");
        productsPage.addItemToTheCart("onesie");
        productsPage.addItemToTheCart("backpack");
        productsPage.addItemToTheCart("bolt-t-shirt");

        Assert.assertEquals(productsPage.getItemsInTheCart(), 3, "Because we have only three items so far");
    }
}
