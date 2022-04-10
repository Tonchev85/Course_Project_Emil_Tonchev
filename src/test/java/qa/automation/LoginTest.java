package qa.automation;

import com.opencsv.exceptions.CsvException;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.CsvHelper;

import java.io.IOException;

public class LoginTest {
    private WebDriver driver;

    @BeforeTest
    public void initializeDriver(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        //WebDriverManager.firefoxdriver().setup();
        //driver = new FirefoxDriver();
    }

    @AfterTest
    public void tearDown(){
        driver.quit();
    }

    @DataProvider(name = "wrongUsersList")
    public Object [][] getWrongUsers(){
        return new Object[][]{
                {"standard_user", "wrong password"},
                {"standard_user8", "secret_sauce"},
                {"blah", "blah"}
        };
    }

    @DataProvider(name = "csvUserList")
    public static Object[][] readUsersFromCsvFile() throws IOException, CsvException {
       return CsvHelper.readCsvFile("src/test/resources/users.csv");
    }

    @Test (dataProvider = "wrongUsersList")
    public void unsuccessfulLogin(String userName, String password){
        driver.get("https://www.saucedemo.com/");

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

    @Test(dataProvider = "csvUserList")
    public void successfulLogin(String userName, String password){
        driver.get("https://www.saucedemo.com/");

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
}
