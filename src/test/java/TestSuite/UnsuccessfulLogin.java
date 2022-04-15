package TestSuite;

import base.TestUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductsPage;

public class UnsuccessfulLogin extends TestUtil {

    @DataProvider(name = "wrongUsersList")
    public Object [][] getWrongUsers(){
        return new Object[][]{
                {"standard_user", "wrong password"},
                {"standard_user8", "secret_sauce"},
                {"blah", "blah"}
        };
    }

    @Test(dataProvider = "wrongUsersList")
    public void unsuccessfulLogin(String userName, String password){

        WebElement username = driver.findElement(By.id("user-name"));
        username.click();
        username.sendKeys(userName);

        WebElement passwordInput = driver.findElement(By.name("password"));
        passwordInput.click();
        passwordInput.sendKeys(password);

        WebElement loginBtn = driver.findElement(By.cssSelector("[value=Login]"));
        loginBtn.click();

        WebElement errorLoginLabel = driver.findElement(By.xpath("//*[text()='Epic sadface: Username and password do not match any user in this service']"));
        Assert.assertTrue(errorLoginLabel.isDisplayed());
    }

    @Test(dataProvider = "wrongUsersList")
    public void unsuccessfulLoginTest(String userName, String password){

        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productsPage = loginPage.login(userName, password);
    }

}
