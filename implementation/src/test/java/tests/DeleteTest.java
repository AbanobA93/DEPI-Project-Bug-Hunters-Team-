package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LandingPage;
import pages.SignUpPage;
import utils.FakerDataProvider;

public class DeleteTest extends BaseTest{
    @Test(dataProvider = "fullRegisterData", dataProviderClass = FakerDataProvider.class)
    public void deleteTest(String name, String email, String password, String company, String addr1, String addr2,
                           String country, String state, String zip, String city, String mobile,
                           String day, String month, String year) {
        LandingPage landingPage = new LandingPage(driver, wait);
        SignUpPage signUpPage = new SignUpPage(driver, wait);

        landingPage.moveToLoginPage();
        signUpPage.signUpPhase1(name, email);
        signUpPage.mailRadioBtn();
        signUpPage.password(password);
        signUpPage.dob(day, month, year);
        signUpPage.checkBoxes();

        // Split full name into first and last (assuming space-separated)
        String[] names = name.split(" ", 2);
        String firstName = names[0];
        String lastName = (names.length > 1) ? names[1] : "";
        signUpPage.firstAndLastName(firstName, lastName);

        signUpPage.company(company);
        signUpPage.addresses(addr1, addr2, country, state, zip, city);
        signUpPage.mobileNumber(mobile);
        signUpPage.createAccount();
        signUpPage.continueToHomePageAfterCreateAccount();
        signUpPage.delete();
        Assert.assertEquals(signUpPage.getExpectedAccountDeletedMSG(),signUpPage.getActualDeleteMSG());
        signUpPage.continueToHomeAfterDelete();
    }
}


