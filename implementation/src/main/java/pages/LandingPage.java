package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LandingPage extends BasePage {

    public LandingPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    // Data
    private static String expectedURL = "https://www.automationexercise.com/";
    private static String expectedTitle = "Automation Exercise";

    // locators
    By loginBTN = By.cssSelector("a[href=\"/login\"]");
    By contactUsBTN = By.className("fa-envelope");
    By testCasesBtn = By.linkText("Test Cases");

    // getters
    public String getExpectedURL() {
        return actualURL();
    }

    public String getExpectedTitle() {
        return actualTitle();
    }

    By productsBtn = By.cssSelector("a[href='/products']");

    // actions
    public String actualURL() {
        return driver.getCurrentUrl();
    }

    public String actualTitle() {
        return driver.getTitle();
    }

    public void moveToLoginPage() {
        click(loginBTN);
    }

    public void moveToContactUsPage() {
        click(contactUsBTN);
    }

    public void moveToTestCasesPage() {
        click(testCasesBtn);
    }

    public void moveToProductsPage() {
        click(productsBtn);
    }
}
