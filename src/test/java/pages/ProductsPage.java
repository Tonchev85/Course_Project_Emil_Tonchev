package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;

public class ProductsPage {
    protected WebDriver driver;
    private static final String ADD_TO_CART_LOCATOR = "//button[@id='add-to-cart-sauce-labs-%s']";
    private static final String REMOVE_TO_CART_LOCATOR = "//button[@id='remove-sauce-labs-%s']";

    @FindBy (className = "shopping_cart_link")
    private WebElement shoppingCartLink;

    @FindBy (className = "shopping_cart_badge")
    private WebElement shoppingCartCounter;

    @FindBy (id = "checkout")
    private WebElement checkoutBtn;

    @FindBy (id = "first-name")
    private WebElement firstName;

    @FindBy (id = "last-name")
    private WebElement lastName;

    @FindBy (id = "postal-code")
    private WebElement postalCode;

    @FindBy (id = "finish")
    private WebElement finishBtn;

    @FindBy (id = "continue")
    private  WebElement continueBtn;


    public ProductsPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void addItemToTheCart(String productName){
        String xpathOfElementToBeAdded = String.format(ADD_TO_CART_LOCATOR, productName);
        WebElement addToCartButton = driver.findElement(By.xpath(xpathOfElementToBeAdded));
        addToCartButton.click();
    }

    public boolean removeItemFromTheCart(String productName){
        String xpathOfElementToBeAdded = String.format(REMOVE_TO_CART_LOCATOR, productName);
        FluentWait fluentWait = new FluentWait(driver)
                .withTimeout(Duration.ofSeconds(3));
        WebElement removeButton = driver.findElement(By.xpath(xpathOfElementToBeAdded));
        fluentWait.until(ExpectedConditions.elementToBeClickable(removeButton));
        if (removeButton.getText().equals("Remove")){
            removeButton.click();
            return true;
        }else {
            return false;
        }
    }

    public int getItemsInTheCart(){
        if (shoppingCartCounter.getText().isEmpty()){
            return 0;
        }else {
            return Integer.parseInt(shoppingCartCounter.getText());
        }
    }

    public void VerifiedCheckout (String checkoutButton){
        shoppingCartLink.click();
        checkoutBtn.click();
        FluentWait fluentWait = new FluentWait(driver).withTimeout(Duration.ofSeconds(5));
        WebElement submitBtn = driver.findElement(By.id("continue"));
        fluentWait.until(ExpectedConditions.elementToBeClickable(submitBtn));
        submitBtn.click();
    }

    public boolean errorMessageIsDisplayed (String errorText){
        WebElement errorMessage = driver.findElement(By.xpath("//*[text()='Error: First Name is required']"));
        return errorMessage.isDisplayed();
    }

    public ProductsPage login (String firstname, String lastname, String postcode){
        firstName.click();
        firstName.sendKeys(firstname);

        lastName.click();
        lastName.sendKeys(lastname);

        postalCode.click();
        postalCode.sendKeys(postcode);

        return new ProductsPage(driver);
    }

    public boolean ponyExpress (String img){
        WebElement ponyImg = driver.findElement(By.className("pony_express"));
        return  ponyImg.isDisplayed();
    }

    public void Checkout (String checkBtn){
        shoppingCartLink.click();
        checkoutBtn.click();
    }
    public void Continue (String Btn){
        continueBtn.click();
        finishBtn.click();
    }
}
