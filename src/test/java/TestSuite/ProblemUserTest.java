package TestSuite;

import base.TestUtil;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.LoginPage;
import pages.ProductsPage;

public class ProblemUserTest extends TestUtil {

    @Test
    public void viewProblemUser(){
        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productsPage = loginPage.login("problem_user", "secret_sauce");

        productsPage.addItemToTheCart("backpack");

        Assert.assertEquals(productsPage.getItemsInTheCart(), 1, "I will be executed");

        productsPage.addItemToTheCart("bolt-t-shirt");

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(productsPage.getItemsInTheCart(), 1);
        System.out.println("I will be executed and you shouldn't");
        softAssert.assertAll();

    }
}
