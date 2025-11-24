package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PaymentPage extends BasePage {
    public PaymentPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    private static final int TIMEOUT_SECONDS = 30;
    By nameOnCardInput = By.name("name_on_card");
    By cardNumberInput = By.name("card_number");
    By cvcInput = By.name("cvc");
    By expiryMonthInput = By.name("expiry_month");
    By expiryYearInput = By.name("expiry_year");
    By submitButton = By.id("submit");

    public void fillPaymentDetails() {
        ((WebElement) this.wait.until(ExpectedConditions.visibilityOfElementLocated(this.nameOnCardInput)))
                .sendKeys(new CharSequence[] { "Test User" });
        ((WebElement) this.wait.until(ExpectedConditions.visibilityOfElementLocated(this.cardNumberInput)))
                .sendKeys(new CharSequence[] { "4444444444444444" });
        ((WebElement) this.wait.until(ExpectedConditions.visibilityOfElementLocated(this.cvcInput)))
                .sendKeys(new CharSequence[] { "123" });
        ((WebElement) this.wait.until(ExpectedConditions.visibilityOfElementLocated(this.expiryMonthInput)))
                .sendKeys(new CharSequence[] { "12" });
        ((WebElement) this.wait.until(ExpectedConditions.visibilityOfElementLocated(this.expiryYearInput)))
                .sendKeys(new CharSequence[] { "2028" });
        WebElement submitBtn = (WebElement) this.wait
                .until(ExpectedConditions.presenceOfElementLocated(this.submitButton));
        ((JavascriptExecutor) this.driver).executeScript("arguments[0].click();", new Object[] { submitBtn });
    }
}
