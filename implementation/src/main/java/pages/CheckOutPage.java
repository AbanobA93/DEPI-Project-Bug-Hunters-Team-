package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckOutPage extends BasePage{
    public CheckOutPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public class CheckoutPage {
        WebDriver driver;
        By comment = By.name("message");
        By placeOrderBtn = By.cssSelector("a.btn.btn-default.check_out");

        public CheckoutPage(WebDriver driver) {
            this.driver = driver;
        }

        public void placeOrder(String text) {
            WebDriverWait wait = new WebDriverWait(this.driver, Duration.ofSeconds(30L));
            wait.until(ExpectedConditions.visibilityOfElementLocated(this.comment));
            this.driver.findElement(this.comment).sendKeys(new CharSequence[]{text});
            wait.until(ExpectedConditions.elementToBeClickable(this.placeOrderBtn));
            this.driver.findElement(this.placeOrderBtn).click();
        }
    }

}
