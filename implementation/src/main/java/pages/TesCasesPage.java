package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TesCasesPage extends BasePage{
    public TesCasesPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    String expectedText ="TEST CASES";
    By testCasesHeader = By.xpath("//h2[text()='Test Cases']");

    public String getActualTestCasesHeaderText() {
        return driver.findElement(testCasesHeader).getText();
    }

    public String getExpectedText() {
        return expectedText;
    }
}
