package TestSuite;

import base.TestUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductsPage;

import java.time.Duration;

public class CheckoutTest extends TestUtil {

    @Test
    public void addItemToCheckout() {
        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productsPage = loginPage.login("standard_user", "secret_sauce");
        productsPage.addItemToTheCart("onesie");
        productsPage.addItemToTheCart("backpack");
        productsPage.addItemToTheCart("bolt-t-shirt");

        WebElement shoppingCart = driver.findElement(By.className("shopping_cart_link"));
        shoppingCart.click();

        WebElement checkoutBtn = driver.findElement(By.id("checkout"));
        checkoutBtn.click();

        FluentWait fluentWait = new FluentWait(driver).withTimeout(Duration.ofSeconds(5));
        WebElement submitBtn = driver.findElement(By.id("continue"));
        fluentWait.until(ExpectedConditions.elementToBeClickable(submitBtn));
        submitBtn.click();

        WebElement errorMessage = driver.findElement(By.xpath("//*[text()='Error: First Name is required']"));
        Assert.assertTrue(errorMessage.isDisplayed());
    }
}
