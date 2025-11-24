//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package pages;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrderConfirmationPage extends BasePage {

    public OrderConfirmationPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    By successMsg = By.xpath("//p[contains(text(),'Congratulations! Your order has been confirmed!')]");
    By downloadInvoiceBtn = By.linkText("Download Invoice");
    By continueBtn = By.linkText("Continue");

    public boolean isOrderSuccessMessageDisplayed() {
        WebDriverWait wait = new WebDriverWait(this.driver, Duration.ofSeconds(30L));
        WebElement msg = (WebElement) wait.until(ExpectedConditions.visibilityOfElementLocated(this.successMsg));
        return msg.isDisplayed();
    }

    public void downloadInvoice() {
        WebDriverWait wait = new WebDriverWait(this.driver, Duration.ofSeconds(30L));
        ((WebElement) wait.until(ExpectedConditions.elementToBeClickable(this.downloadInvoiceBtn))).click();
    }

    public void clickContinue() {
        WebDriverWait wait = new WebDriverWait(this.driver, Duration.ofSeconds(30L));
        ((WebElement) wait.until(ExpectedConditions.elementToBeClickable(this.continueBtn))).click();
    }
}
