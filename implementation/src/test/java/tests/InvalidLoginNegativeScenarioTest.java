package tests;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LandingPage;
import pages.LoginPage;

public class InvalidLoginNegativeScenarioTest extends BaseTest {

    @Test
    public void invalidLogin() {
        LandingPage landingPage = new LandingPage(driver, wait);
        LoginPage loginPage = new LoginPage(driver, wait);
        landingPage.moveToLoginPage();
        loginPage.invalidLogin();
        wait.until(ExpectedConditions.presenceOfElementLocated(loginPage.getInvalidLoginErrorLocator()));
        Assert.assertEquals(loginPage.getActualInvalidLoginMSG(), loginPage.getActualInvalidLoginMSG());
    }
}
