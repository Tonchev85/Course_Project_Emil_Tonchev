package qa.automation;

import base.TestUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.LoginPage;
import pages.ProductsPage;

import java.time.Duration;
import java.util.Collections;
import java.util.NoSuchElementException;

public class ProductTests extends TestUtil {
    @Test
    public void selectDifferentOrder() throws InterruptedException {
        WebElement username = driver.findElement(By.id("user-name"));
        username.click();
        username.sendKeys("standard_user");

        WebElement password = driver.findElement(By.name("password"));
        password.click();
        password.sendKeys("secret_sauce");

        WebElement loginBtn = driver.findElement(By.cssSelector("[value=Login]"));
        loginBtn.click();

        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement dropDown = driver.findElement(By.xpath("//select[@class='product_sort_container']"));
        wait.until(ExpectedConditions.elementToBeClickable(dropDown));
        dropDown.click();

        FluentWait fluentWait = new FluentWait(driver)
                .withTimeout(Duration.ofSeconds(20))
                .pollingEvery(Duration.ofSeconds(2))
                .ignoreAll(Collections.singleton(NoSuchElementException.class));
        WebElement lowToHighPrice = driver.findElement(By.cssSelector("[value=lohi]"));

        fluentWait.until(ExpectedConditions.elementToBeClickable(lowToHighPrice));
        lowToHighPrice.click();
    }

    @Test
    public void addItemToTheCart(){
        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productsPage = loginPage.login("standard_user", "secret_sauce");
        productsPage.addItemToTheCart("onesie");

        // Hard Assert
        Assert.assertEquals(productsPage.getItemsInTheCart(), 1, "Because we have only one item so far");

        productsPage.removeItemFromTheCart("onesie");

        // Soft Assert
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(productsPage.getItemsInTheCart(), 0, "Because we have only one item so far");
        System.out.println("I will be executed");

        softAssert.assertAll();

    }
}
