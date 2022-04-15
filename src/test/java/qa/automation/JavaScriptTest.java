package qa.automation;

import base.TestUtil;
import org.openqa.selenium.*;
import org.testng.annotations.Test;

public class JavaScriptTest extends TestUtil {

    @Test
    public void LoginWithJSAction () throws InterruptedException {

        WebElement username = driver.findElement(By.id("user-name"));
        username.click();
        username.sendKeys("standard_user");

        WebElement passwordInput = driver.findElement(By.name("password"));
        passwordInput.click();
        passwordInput.sendKeys("secret_sauce");

        WebElement loginBtn = driver.findElement(By.cssSelector("[value=Login]"));
        loginBtn.click();
//        loginBtn.sendKeys(Keys.ENTER);

        JavascriptExecutor script = (JavascriptExecutor) driver;
        script.executeScript("argument[0].scrollIntoView()",
                driver.findElement(By.xpath("//a[@href='https://www.linkedin.com/company/sauce-labs/']")));

//        WebElement linkedInLink = driver.findElement(By.xpath("//a[@href='https://www.linkedin.com/company/sauce-labs/']"));
//        linkedInLink.click();
    }
}
