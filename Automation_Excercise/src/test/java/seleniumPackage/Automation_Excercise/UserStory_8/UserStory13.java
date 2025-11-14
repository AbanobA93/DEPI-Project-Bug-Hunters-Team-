package seleniumPackage.Automation_Excercise.UserStory_8;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

public class UserStory13 {
	WebDriver driver = new ChromeDriver();
	Pages page = new Pages(driver);
	SoftAssert softAssert = new SoftAssert();
	private final String TEST_EMAIL = "mahmoudehab30@gmail.com";

	
	@BeforeTest
	public void initialize() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		page.navigateToHomePage();
		page.clickLoginNavigationButton();
		page.signin();
		page.clickProductNavigationButton();
		
	}

	@Test(priority = 1, description = "Verify that 'SUBSCRIPTION' text is visible")
    public void verifySubscriptionTextVisible() {
		softAssert = new SoftAssert();
        softAssert.assertTrue(page.getSubscriptionHeader().isDisplayed(), "'SUBSCRIPTION' header is not visible.");
        softAssert.assertEquals(page.getSubscriptionHeader().getText(), "SUBSCRIPTION", "Header text is not correct.");
        softAssert.assertAll();
	}

    @Test(priority = 2, description = "Verify email input and subscribe button are visible")
    public void verifyEmailFieldAndButtonVisible() {
    	softAssert = new SoftAssert();
        softAssert.assertTrue(page.getSubscriptionEmailField().isDisplayed(), "Email input field is not visible.");
        softAssert.assertTrue(page.getSubscribeButton().isDisplayed(), "Subscribe button is not visible.");
        softAssert.assertAll();
    }

    @Test(priority = 3, description = "Verify successful subscription with valid email")
    public void verifySuccessfulSubscription() {
    	softAssert = new SoftAssert();
        page.submitSubscription(TEST_EMAIL);
        softAssert.assertTrue(page.getSubscriptionSuccessMessage().isDisplayed(), "Success message did not appear.");
        page.waitForSubscriptionEmailToClear();
        softAssert.assertAll();
    }

    @Test(priority = 4, description = "Verify success message text after valid submission")
    public void verifySuccessMessageText() {
    	softAssert = new SoftAssert();
        page.submitSubscription(TEST_EMAIL);
        softAssert.assertEquals(page.getSubscriptionSuccessMessage().getText(), "You have been successfully subscribed!", "Success message text is incorrect.");
        page.waitForSubscriptionEmailToClear();
        softAssert.assertAll();
    }

    @Test(priority = 5, description = "Verify invalid email format is accepted")
    public void verifyInvalidEmailFormatAcceptance() {
    	softAssert = new SoftAssert();
        page.submitSubscription("asfac@com");
        softAssert.assertTrue(page.getSubscriptionSuccessMessage().isDisplayed(), "Success message did not appear for 'asfac@com'.");
        page.waitForSubscriptionEmailToClear();
        softAssert.assertAll();
    }

    @Test(priority = 6, description = "Verify empty email field cannot be submitted")
    public void verifyEmptyEmailSubmission() {
    	softAssert = new SoftAssert();
        page.submitSubscription("");
        softAssert.assertEquals(page.getSubscriptionEmailField().getAttribute("validationMessage"), "Please fill out this field.", "HTML5 validation message for empty field is incorrect or missing.");
        softAssert.assertAll();
    }

    @Test(priority = 7, description = "Verify that the system allows duplicate subscriptions ")
    public void verifyDuplicateSubscriptions() {
    	softAssert = new SoftAssert();
        page.submitSubscription(TEST_EMAIL);
        page.getSubscriptionSuccessMessage(); 
        page.waitForSubscriptionEmailToClear(); 
        page.submitSubscription(TEST_EMAIL);
        softAssert.assertTrue(page.getSubscriptionSuccessMessage().isDisplayed(), "Success message did not appear for duplicate email.");
        softAssert.assertAll();
    }

    @Test(priority = 8, description = "Verify that the form clears after successful subscription")
    public void verifyFormClearsAfterSubmission() {
    	softAssert = new SoftAssert();
        page.submitSubscription(TEST_EMAIL);
        page.getSubscriptionSuccessMessage();
        softAssert.assertTrue(page.waitForSubscriptionEmailToClear(), "Email field did not clear after submission.");
        softAssert.assertAll();
    }

    @Test(priority = 9, description = "Verify email field validation with invalid characters")
    public void verifyInvalidCharactersInEmail() {
    	softAssert = new SoftAssert();
        page.submitSubscription("mahmoud@@gmail.com");
        softAssert.assertEquals(page.getSubscriptionEmailField().getAttribute("validationMessage"), "A part following '@' should not contain the symbol '@'.", "HTML5 validation for '@@' failed.");
        softAssert.assertFalse(page.isSubscriptionSuccessMessageVisible(), "Success message appeared for invalid email '@@'.");
        softAssert.assertAll();
    }
    

	@AfterTest
	public void closeBrowser() {
		driver.close();
	}

}
