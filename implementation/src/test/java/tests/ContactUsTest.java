package tests;

import org.openqa.selenium.Alert;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ContactUsPage;
import pages.LandingPage;

public class ContactUsTest extends BaseTest {


    @Test
    public void contactUsTest (){
        LandingPage landingPage = new LandingPage(driver,wait);
        ContactUsPage contactUsPage = new ContactUsPage(driver,wait);

        landingPage.moveToContactUsPage();
        Assert.assertEquals(contactUsPage.contactUsMsgTXT(),contactUsPage.getContactusTXT());
        contactUsPage.userCanContactUs("Abdelrahman Osama", "abdelrahman@gmail.com", "Complain", "My order doesn't deliver yet");
        Alert alert = driver.switchTo().alert();
        Assert.assertEquals(alert.getText(), "Press OK to proceed!");
        alert.accept();
    }
}
