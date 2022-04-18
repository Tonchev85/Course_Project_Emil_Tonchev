package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    protected WebDriver driver;

    @FindBy(id = "user-name")
    private WebElement userNameInput;

    @FindBy(css = "[placeholder=Password]")
    private WebElement passwordInput;

    @FindBy(xpath = "//input[@value='Login']")
    private WebElement loginBtn;

    // Constructor
    public LoginPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public ProductsPage login(String username, String password){
        userNameInput.click();
        userNameInput.sendKeys(username);

        passwordInput.click();
        passwordInput.sendKeys(password);

        loginBtn.click();

        return new ProductsPage(driver);
    }

    public boolean menuBtn (String menuIsDisplayed){
        WebElement menuButton = driver.findElement(By.id("react-burger-menu-btn"));
        return menuButton.isDisplayed();
    }

    public  boolean errorLogin (String errorMessage){
        WebElement errorLoginLabel = driver.findElement(By.xpath("//*[text()='Epic sadface: Username and password do not match any user in this service']"));
        return errorLoginLabel.isDisplayed();
    }

}
