package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
    // driver
    protected WebDriver driver;
    protected WebDriverWait wait;

    // constructor
    public BasePage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    // Common Actions
    public void click(By locator) {
        org.openqa.selenium.WebElement element = wait
                .until(org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable(locator));
        try {
            element.click();
        } catch (org.openqa.selenium.ElementClickInterceptedException e) {
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
    }

    public void writeText(By locator, String text) {
        wait.until(org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated(locator))
                .sendKeys(text);
    }

    public String getText(By locator) {
        return wait.until(org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated(locator))
                .getText();
    }

    public void waitForPageLoad() {
        wait.until(webDriver -> ((org.openqa.selenium.JavascriptExecutor) webDriver)
                .executeScript("return document.readyState").equals("complete"));
    }
}
