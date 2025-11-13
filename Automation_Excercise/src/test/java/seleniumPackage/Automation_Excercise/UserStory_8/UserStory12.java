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

public class UserStory12 {
	WebDriver driver = new ChromeDriver();
	Pages page = new Pages(driver);
	SoftAssert softAssert = new SoftAssert();

	
	@BeforeTest
	public void initialize() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		page.navigateToHomePage();
		page.clickLoginNavigationButton();
		page.signin();
		page.clickProductNavigationButton();
		page.clickViewFirstProductButton();
	}

	@Test(priority = 1, description = "Verify that the 'Write Your Review' section is visible")
    public void verifyWriteYourReviewSectionIsVisible() {
		softAssert = new SoftAssert();
        softAssert.assertTrue(page.getWriteYourReviewHeader().isDisplayed(), "'Write Your Review' header is not visible.");
        softAssert.assertAll();
	}
	

    @Test(priority = 2, description = "Verify all review input fields are present")
    public void verifyAllReviewInputFieldsArePresent() {
    	softAssert = new SoftAssert();
    	softAssert.assertTrue(page.getReviewNameField().isDisplayed(), "Name field is not visible.");
        softAssert.assertTrue(page.getReviewEmailField().isDisplayed(), "Email field is not visible.");
        softAssert.assertTrue(page.getReviewTextArea().isDisplayed(), "Review text area is not visible.");
        softAssert.assertTrue(page.getReviewSubmitButton().isDisplayed(), "Submit button is not visible.");
        softAssert.assertAll();
    }

    @Test(priority = 3, description = "Verify that review submission works with valid data")
    public void verifyReviewSubmissionWithValidDataAndSuccessMessage() {
    	softAssert = new SoftAssert();
        page.submitReview("Mahmoud Ehab", "mahmoudehab30@gmail.com", "best product ever");
        softAssert.assertEquals(page.getReviewSuccessMessage().getText(), "Thank you for your review.", "Success message text is incorrect.");
        page.waitForReviewFormToClear();
        softAssert.assertAll();
    }

    @Test(priority = 4, description = "Verify mandatory fields validation (e.g., empty email)")
    public void verifyMandatoryFieldsValidation() {
    	softAssert = new SoftAssert();
        page.submitReview("Mahmoud Ehab", "", "best product ever");
        softAssert.assertFalse(page.isSuccessMessageDisplayed(), "Success message was displayed, and form validation have failed.");
        page.clearReviewFormFields();
        softAssert.assertAll();
    }

    @Test(priority = 5, description = "Verify email field validation (per test data)")
    public void verifyEmailFieldValidation() {
    	softAssert = new SoftAssert();
        page.submitReview("Mahmoud Ehab", "mahmoudehab30@afgaga.com", "best product ever");
        softAssert.assertTrue(page.getReviewSuccessMessage().isDisplayed(), "Success message appeared for email 'mahmoudehab30@afgaga.com' which is an invalid email");
        page.waitForReviewFormToClear();
        softAssert.assertAll();
    }
    
    @Test(priority = 6, description = "Verify review submission with only spaces in review text (per test data)")
    public void verifySubmissionWithSpacesOnlyReview() {
    	softAssert = new SoftAssert();
        page.submitReview("Mahmoud Ehab", "mahmoudehab30@gmail.com", "   ");
        softAssert.assertTrue(page.getReviewSuccessMessage().isDisplayed(), "Success message did not appear for spaces-only review.");
        page.waitForReviewFormToClear();
        softAssert.assertAll();
    }

    @Test(priority = 7, description = "Verify that fields are cleared after successful submission")
    public void verifyFieldsAreClearedAfterSubmission() {
    	softAssert = new SoftAssert();
        page.submitReview("Mahmoud Ehab", "mahmoudehab30@gmail.com", "best product ever");
        page.getReviewSuccessMessage(); 
        page.waitForReviewFormToClear();
        softAssert.assertTrue(page.isReviewFormCleared(), "Form fields were not cleared after submission.");
        softAssert.assertAll();
    }

    @Test(priority = 8, description = "Verify that customer reviews are not displayed")
    public void verifyCustomerReviewsSectionIsNotDisplayed() {
    	softAssert = new SoftAssert();
        softAssert.assertFalse(page.isCustomerReviewsSectionVisible(), "Customer reviews section was found, but was expected to be not visible.");
        softAssert.assertAll();
    }

	@AfterTest
	public void closeBrowser() {
		driver.close();
	}

}
