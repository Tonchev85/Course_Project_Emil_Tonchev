package TestSuite;

import base.TestUtil;
import com.opencsv.exceptions.CsvException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductsPage;
import utils.CsvHelper;

import java.io.IOException;

public class SuccessfulLogin extends TestUtil {

    @DataProvider(name = "csvUserList")
    public static Object[][] readUsersFromCsvFile() throws IOException, CsvException {
        return CsvHelper.readCsvFile("src/test/resources/users.csv");
    }

    @Test(dataProvider = "csvUserList")
    public void successfulLogin(String userName, String password){

        WebElement username = driver.findElement(By.id("user-name"));
        username.click();
        username.sendKeys(userName);

        WebElement passwordInput = driver.findElement(By.name("password"));
        passwordInput.click();
        passwordInput.sendKeys(password);

        WebElement loginBtn = driver.findElement(By.cssSelector("[value=Login]"));
        loginBtn.click();

        WebElement menuButton = driver.findElement(By.id("react-burger-menu-btn"));
        Assert.assertTrue(menuButton.isDisplayed());
    }

    @Test(dataProvider = "csvUserList")
    public void successfulLoginTest(String userName, String password){

        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productsPage = loginPage.login(userName, password);
    }
}
