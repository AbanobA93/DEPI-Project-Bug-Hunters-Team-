package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.BasePage;
import pages.LandingPage;
import pages.LoginPage;

public class InvalidLoginNegativeScenarioTest extends BasePage {
    public InvalidLoginNegativeScenarioTest(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }
    @Test
    public void invalidLogin(){
        LandingPage landingPage = new LandingPage(driver,wait);
        LoginPage loginPage = new LoginPage(driver,wait);
        landingPage.moveToLoginPage();
        loginPage.invalidLogin();
        Assert.assertEquals(loginPage.getActualInvalidLoginMSG(),loginPage.getActualInvalidLoginMSG());
    }
}
