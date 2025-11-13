package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;


public class LoginPage extends BasePage{

    public LoginPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    //Data
    static String username = "hellolo@heleilo.com";
    static String password = "test.tester@!#";


    //locators
    By loginEmailField = By.cssSelector("input[data-qa=\"login-email\"]");
    By passwordField = By.cssSelector("input[data-qa=\"login-password\"]");
    By loginBTN = By.cssSelector("button[data-qa=\"login-button\"]");
    By invalidLoginErrorMessage = By.xpath("//p[text()=\"Your email or password is incorrect!\"]");

    public void validLogin (){
        driver.findElement(loginEmailField).sendKeys(username);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(loginBTN).click();
    }

    public void invalidLogin (){
        driver.findElement(loginEmailField).sendKeys("username");
        driver.findElement(passwordField).sendKeys("password");
        driver.findElement(loginBTN).click();
    }
}
