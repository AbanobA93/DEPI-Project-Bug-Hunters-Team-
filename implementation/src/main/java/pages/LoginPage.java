package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    // Data
    static String username = "hellolo@heleilo.com";
    static String password = "test.tester@!#";
    static String expectedColor = "#ffa500";
    static String expectedInvalidLogin = "Your email or password is incorrect!";

    // locators
    By loginEmailField = By.cssSelector("input[data-qa=\"login-email\"]");
    By passwordField = By.cssSelector("input[data-qa=\"login-password\"]");
    By loginBTN = By.cssSelector("button[data-qa=\"login-button\"]");
    By invalidLoginErrorMSG = By.cssSelector("p[style=\"color: red;\"]");

    // getter

    public static String getExpectedColor() {
        return expectedColor;
    }

    public String getExpectedInvalidLogin() {
        return expectedInvalidLogin;
    }

    public String getActualInvalidLoginMSG() {
        return getText(invalidLoginErrorMSG);
    }

    public By getInvalidLoginErrorLocator() {
        return invalidLoginErrorMSG;
    }

    // actions
    public void validLogin() {
        writeText(loginEmailField, username);
        writeText(passwordField, password);
        click(loginBTN);
    }

    public String actualHomeBtnColor() {
        WebElement home = driver.findElement(By.xpath("//a[contains(.,'Home')]"));
        String color = home.getCssValue("color");
        return org.openqa.selenium.support.Color.fromString(color).asHex();
    }

    public void invalidLogin() {
        writeText(loginEmailField, "username@user.name");
        writeText(passwordField, "password");
        click(loginBTN);
    }

}
