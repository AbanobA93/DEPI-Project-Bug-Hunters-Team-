// Specifies the package this class belongs to.
package seleniumPackage.Automation_Excercise.UserStory_8;

// Imports the Duration class for setting timeouts.
import java.time.Duration;
// Imports the ArrayList class.
import java.util.ArrayList;
// Imports the HashSet class.
import java.util.HashSet;
// Imports the List interface.
import java.util.List;
// Imports the Set interface.
import java.util.Set;

// Imports all public classes and interfaces from org.openqa.selenium.
import org.openqa.selenium.*;
// Imports the ChromeDriver class.
import org.openqa.selenium.chrome.*;
// Imports all TestNG annotations (like @Test, @BeforeTest, etc.).
import org.testng.annotations.*;
// Imports the SoftAssert class from TestNG for soft assertions.
import org.testng.asserts.SoftAssert;

/**
 * This class contains TestNG tests for "User Story 12",
 * which is focused on verifying the "Write Your Review" functionality on the product detail page.
 */
public class UserStory12 {
	// Initializes a new instance of ChromeDriver.
	WebDriver driver = new ChromeDriver();
	// Initializes a new instance of the Pages class, passing the driver to it.
	Pages page = new Pages(driver);
	// Initializes a SoftAssert object to allow multiple failures in a single test.
	SoftAssert softAssert = new SoftAssert();

	
	/**
	 * This method runs once before any @Test methods in this class.
	 * It sets up an implicit wait, navigates to the homepage, logs in,
	 * navigates to the products page, and clicks on the first product.
	 */
	@BeforeTest
	public void initialize() {
		// Sets an implicit wait for the driver to wait up to 10 seconds when finding elements.
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		// Navigates to the homepage.
		page.navigateToHomePage();
		// Clicks the 'Signup / Login' button.
		page.clickLoginNavigationButton();
		// Enters credentials and clicks 'Login'.
		page.signin();
		// Clicks the 'Products' button in the header.
		page.clickProductNavigationButton();
		// Clicks the 'View Product' button for the first product.
		page.clickViewFirstProductButton();
	}

	/**
	 * Test 1: Verifies that the "Write Your Review" section is visible.
	 */
	@Test(priority = 1, description = "Verify that the 'Write Your Review' section is visible")
    public void verifyWriteYourReviewSectionIsVisible() {
		// Creates a new SoftAssert instance for this test.
		softAssert = new SoftAssert();
		// Asserts that the 'Write Your Review' header is displayed.
        softAssert.assertTrue(page.getWriteYourReviewHeader().isDisplayed(), "'Write Your Review' header is not visible.");
        // Collects and reports all assertions.
        softAssert.assertAll();
	}
	

	/**
	 * Test 2: Verifies that all input fields in the review form are present.
	 */
    @Test(priority = 2, description = "Verify all review input fields are present")
    public void verifyAllReviewInputFieldsArePresent() {
    	// Creates a new SoftAssert instance.
    	softAssert = new SoftAssert();
    	// Asserts that the 'Name' field is displayed.
    	softAssert.assertTrue(page.getReviewNameField().isDisplayed(), "Name field is not visible.");
    	// Asserts that the 'Email' field is displayed.
        softAssert.assertTrue(page.getReviewEmailField().isDisplayed(), "Email field is not visible.");
        // Asserts that the 'Review' text area is displayed.
        softAssert.assertTrue(page.getReviewTextArea().isDisplayed(), "Review text area is not visible.");
        // Asserts that the 'Submit' button is displayed.
        softAssert.assertTrue(page.getReviewSubmitButton().isDisplayed(), "Submit button is not visible.");
        // Collects and reports all assertions.
        softAssert.assertAll();
    }

	/**
	 * Test 3: Verifies that submitting a review with valid data works and shows the correct success message.
	 */
    @Test(priority = 3, description = "Verify that review submission works with valid data")
    public void verifyReviewSubmissionWithValidDataAndSuccessMessage() {
    	// Creates a new SoftAssert instance.
    	softAssert = new SoftAssert();
    	// Submits the review form with valid data.
        page.submitReview("Mahmoud Ehab", "mahmoudehab30@gmail.com", "best product ever");
        // Asserts that the success message text is correct.
        softAssert.assertEquals(page.getReviewSuccessMessage().getText(), "Thank you for your review.", "Success message text is incorrect.");
        // Waits for the review form fields to be cleared.
        page.waitForReviewFormToClear();
        // Collects and reports all assertions.
        softAssert.assertAll();
    }

	/**
	 * Test 4: Verifies validation for mandatory fields (e.g., submitting with an empty email).
	 */
    @Test(priority = 4, description = "Verify mandatory fields validation ")
    public void verifyMandatoryFieldsValidation() {
    	// Creates a new SoftAssert instance.
    	softAssert = new SoftAssert();
    	// Submits the form with an empty email field.
        page.submitReview("Mahmoud Ehab", "", "best product ever");
        // Asserts that the success message is *not* displayed, as validation should have failed.
        softAssert.assertFalse(page.isSuccessMessageDisplayed(), "Success message was displayed, and form validation have failed.");
        // Clears the form fields to avoid impacting subsequent tests.
        page.clearReviewFormFields();
        // Collects and reports all assertions.
        softAssert.assertAll();
    }

	/**
	 * Test 5: Verifies that the system accepts an email with an invalid format (based on test data).
	 */
    @Test(priority = 5, description = "Verify email field accepts invalid email format ")
    public void verifyEmailFieldValidation() {
    	// Creates a new SoftAssert instance.
    	softAssert = new SoftAssert();
    	// Submits the form with an email that has an invalid TLD.
        page.submitReview("Mahmoud Ehab", "mahmoudehab30@afgaga.com", "best product ever");
        // Asserts that the success message *is* displayed (documenting the system accepts this).
        softAssert.assertTrue(page.getReviewSuccessMessage().isDisplayed(), "Success message appeared for email 'mahmoudehab30@afgaga.com' which is an invalid email");
        // Waits for the form fields to clear.
        page.waitForReviewFormToClear();
        // Collects and reports all assertions.
        softAssert.assertAll();
    }
    
	/**
	 * Test 6: Verifies that the system accepts a review containing only spaces (based on test data).
	 */
    @Test(priority = 6, description = "Verify review submission with only spaces in review text is accepted ")
    public void verifySubmissionWithSpacesOnlyReview() {
    	// Creates a new SoftAssert instance.
    	softAssert = new SoftAssert();
    	// Submits the form with only spaces in the review text area.
        page.submitReview("Mahmoud Ehab", "mahmoudehab30@gmail.com", "   ");
        // Asserts that the success message *is* displayed (documenting the system accepts this).
        softAssert.assertTrue(page.getReviewSuccessMessage().isDisplayed(), "Success message did not appear for spaces-only review.");
        // Waits for the form fields to clear.
        page.waitForReviewFormToClear();
        // Collects and reports all assertions.
        softAssert.assertAll();
    }

	/**
	 * Test 7: Verifies that the form fields are cleared after a successful submission.
	 */
    @Test(priority = 7, description = "Verify that fields are cleared after successful submission")
    public void verifyFieldsAreClearedAfterSubmission() {
    	// Creates a new SoftAssert instance.
    	softAssert = new SoftAssert();
    	// Submits a valid review.
        page.submitReview("Mahmoud Ehab", "mahmoudehab30@gmail.com", "best product ever");
        // Waits for the success message to appear.
        page.getReviewSuccessMessage(); 
        // Waits for the form fields to be cleared.
        page.waitForReviewFormToClear();
        // Asserts that the form fields are indeed empty.
        softAssert.assertTrue(page.isReviewFormCleared(), "Form fields were not cleared after submission.");
        // Collects and reports all assertions.
        softAssert.assertAll();
    }

	/**
	 * Test 8: Verifies that the "Customer Reviews" section is not visible (based on test data).
	 */
    @Test(priority = 8, description = "Verify that customer reviews are not displayed")
    public void verifyCustomerReviewsSectionIsNotDisplayed() {
    	// Creates a new SoftAssert instance.
    	softAssert = new SoftAssert();
    	// Asserts that the customer reviews section is *not* visible.
        softAssert.assertFalse(page.isCustomerReviewsSectionVisible(), "Customer reviews section was found, but was expected to be not visible.");
        // Collects and reports all assertions.
        softAssert.assertAll();
    }

	/**
	 * This method runs once after all @Test methods in this class have finished.
	 * It closes the browser window.
	 */
	@AfterTest
	public void closeBrowser() {
		// Closes the current browser window.
		driver.close();
	}

} // End of the UserStory12 class