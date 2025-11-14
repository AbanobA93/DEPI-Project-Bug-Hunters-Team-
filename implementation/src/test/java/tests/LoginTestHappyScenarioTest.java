package tests;


import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LandingPage;
import pages.LoginPage;

public class LoginTestHappyScenarioTest extends BaseTest{
    @Test
    public void validLogin(){
        LandingPage landingPage = new LandingPage(driver,wait);
        LoginPage loginPage = new LoginPage(driver,wait);
        landingPage.moveToLoginPage();
        loginPage.validLogin();
        Assert.assertEquals(loginPage.getExpectedColor(),loginPage.actualHomeBtnColor());
    }
}
