package qa.automation;

import base.TestUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class ProductTests extends TestUtil {
    @Test
    public void selectDifferentOrder(){
        WebElement username = driver.findElement(By.id("user-name"));
        username.click();
        username.sendKeys("standard_user");

        WebElement password = driver.findElement(By.name("password"));
        password.click();
        password.sendKeys("secret_sauce");

        WebElement loginBtn = driver.findElement(By.cssSelector("[value=Login]"));
        loginBtn.click();

        WebElement dropDown = driver.findElement(By.xpath("//select[@class='product_sort_container']"));
        dropDown.click();
        WebElement lowToHighPrice = driver.findElement(By.cssSelector("[value=lohi]"));
        lowToHighPrice.click();
    }
}
