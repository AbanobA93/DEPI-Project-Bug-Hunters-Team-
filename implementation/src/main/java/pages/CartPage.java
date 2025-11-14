//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage extends BasePage{
    //constructor
    public CartPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    private static final int TIMEOUT_SECONDS = 30;
    By proceedToCheckoutButton = By.cssSelector(".btn.btn-default.check_out");
    By proceedBtn = By.cssSelector(".check_out");
    By checkoutBtn = By.cssSelector(".btn.btn-default.check_out");
    By deleteAccount = By.cssSelector("a[href='/delete_account']");
    By registerLoginBtn = By.linkText("Register / Login");
    By quantityButton = By.cssSelector("button.disabled");
    By deleteButtons = By.cssSelector("a.cart_quantity_delete");




    public void proceedToCheckout() {
        this.wait.until((webDriver) -> ((JavascriptExecutor)webDriver).executeScript("return document.readyState", new Object[0]).equals("complete"));

        WebElement checkoutBtnElement = (WebElement)this.wait.until(ExpectedConditions.elementToBeClickable(this.proceedToCheckoutButton));
        ((JavascriptExecutor)this.driver).executeScript("arguments[0].click();", new Object[]{checkoutBtnElement});
    }

    public void clickRegisterLogin() {
        this.wait.until((webDriver) -> ((JavascriptExecutor)webDriver).executeScript("return document.readyState", new Object[0]).equals("complete"));

        WebElement registerLink = (WebElement)this.wait.until(ExpectedConditions.elementToBeClickable(this.registerLoginBtn));
        ((JavascriptExecutor)this.driver).executeScript("arguments[0].click();", new Object[]{registerLink});
    }

    public String getCartQuantity() {
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(this.quantityButton));
        return this.driver.findElement(this.quantityButton).getText().trim();
    }

    public void clickDeleteAccount() {
        this.wait.until((webDriver) -> ((JavascriptExecutor)webDriver).executeScript("return document.readyState", new Object[0]).equals("complete"));
        WebElement deleteLink = (WebElement)this.wait.until(ExpectedConditions.elementToBeClickable(this.deleteAccount));
        ((JavascriptExecutor)this.driver).executeScript("arguments[0].click();", new Object[]{deleteLink});
    }

    public void deleteAllProducts() {
        while(this.driver.findElements(this.deleteButtons).size() > 0) {
            WebElement buttonToDelete = (WebElement)this.wait.until(ExpectedConditions.elementToBeClickable(this.deleteButtons));
            buttonToDelete.click();
            this.wait.until(ExpectedConditions.invisibilityOf(buttonToDelete));
        }

    }
}
