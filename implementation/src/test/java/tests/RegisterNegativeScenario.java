package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LandingPage;
import pages.SignUpPage;

public class RegisterNegativeScenario extends BaseTest {

    @Test
    public void invalidEmailRegistrationTest() {
        LandingPage landingPage = new LandingPage(driver, wait);
        SignUpPage signUpPage = new SignUpPage(driver, wait);

        landingPage.moveToLoginPage();
        signUpPage.signUpPhase1("asda", "ada");

        Assert.assertEquals(signUpPage.getActualUrl_Login(), signUpPage.actualURL());
    }

    @Test
    public void emailAlreadyRegisteredTest() {
        LandingPage landingPage = new LandingPage(driver, wait);
        SignUpPage signUpPage = new SignUpPage(driver, wait);

        landingPage.moveToLoginPage();
        signUpPage.signUpPhase1("asda", "abanob.ashraf1@outlook.com");

        /*
         * Assert.assertEquals(signUpPage.getExpectedEmailAlreadyExistsMSG(),
         * signUpPage.actual);
         */
    }
}
