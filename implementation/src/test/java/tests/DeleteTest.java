package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.LandingPage;
import pages.SignUpPage;
import utils.FakerDataProvider;

@Listeners({ io.qameta.allure.testng.AllureTestNg.class })
@Feature("Delete User Account")
public class DeleteTest extends BaseTest {

    @Test(dataProvider = "fullRegisterData", dataProviderClass = FakerDataProvider.class)
    @Severity(SeverityLevel.CRITICAL)
    public void deleteTest(String fullName, String email, String password, String company, String addr1, String addr2,
            String country, String state, String zip, String city, String mobile,
            String day, String month, String year) {

        LandingPage landingPage = new LandingPage(driver, wait);
        SignUpPage signUpPage = new SignUpPage(driver, wait);

        landingPage.moveToLoginPage();
        signUpPage.signUpPhase1(fullName, email);

        signUpPage.mailRadioBtn();
        signUpPage.password(password);

        String[] names = fullName.split(" ", 2);
        signUpPage.firstAndLastName(names[0], names.length > 1 ? names[1] : "");

        signUpPage.addresses(addr1, addr2, country, state, zip, city);
        signUpPage.mobileNumber(mobile);

        signUpPage.createAccount();
        signUpPage.continueToHomePageAfterCreateAccount();
        signUpPage.delete();

        Assert.assertEquals(signUpPage.getActualDeleteMSG(), signUpPage.getExpectedAccountDeletedMSG());
        signUpPage.continueToHomeAfterDelete();
    }
}
