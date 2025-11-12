package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LandingPage extends BasePage{

    public LandingPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    //Data
    private static  String expectedURL = "https://www.automationexercise.com/";
    private static  String expectedTitle = "Automation Exercise";


    //locators
    By loginBTN = By.cssSelector("a[href=\"/login\"]");
    By homeBTN = By.linkText("Home");
    By contactUsBTN = By.className("fa-envelope");



    //getters
    public String getExpectedURL() {
        return actualURL();
    }

    public String getExpectedTitle() {
        return actualTitle();
    }


    //actions
    public String actualURL() {
        return driver.getCurrentUrl();
    }

    public String actualTitle (){return driver.getTitle();}

    public void moveToLoginPage(){
        driver.findElement(loginBTN).click();
    }

    public void moveToContactUsPage (){driver.findElement(contactUsBTN).click();}



}
