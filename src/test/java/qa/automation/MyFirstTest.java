package qa.automation;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class MyFirstTest {
    private WebDriver driver;

    @BeforeTest
    public void initializeDriver(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
       // WebDriverManager.firefoxdriver().setup();
        //driver = new FirefoxDriver();
    }

    @AfterTest
    public void tearDown(){
        driver.quit();
    }

    @Test
    public void successfulLoginTest(){
        driver.get("https://www.saucedemo.com/");

        WebElement username = driver.findElement(By.id("user-name"));
        username.click();
        username.sendKeys("standard_user");

        WebElement password = driver.findElement(By.name("password"));
        password.click();
        password.sendKeys("secret_sauce");

        WebElement loginBtn = driver.findElement(By.cssSelector("[value=Login]"));
        loginBtn.click();

        WebElement menuButton = driver.findElement(By.id("react-burger-menu-btn"));
        Assert.assertTrue(menuButton.isDisplayed());
    }

}
