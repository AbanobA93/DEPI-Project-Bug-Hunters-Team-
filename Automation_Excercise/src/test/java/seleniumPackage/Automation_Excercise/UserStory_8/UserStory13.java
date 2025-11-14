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
 * This class contains TestNG tests for "User Story 13",
 * which is focused on verifying the footer "Subscription" form functionality.
 */
public class UserStory13 {
	// Initializes a new instance of ChromeDriver.
	WebDriver driver = new ChromeDriver();
	// Initializes a new instance of the Pages class, passing the driver to it.
	Pages page = new Pages(driver);
	// Initializes a SoftAssert object to allow multiple failures in a single test.
	SoftAssert softAssert = new SoftAssert();
	// Defines a constant variable for the email address used in tests.
	private final String TEST_EMAIL = "mahmoudehab30@gmail.com";

	
	/**
	 * This method runs once before any @Test methods in this class.
	 * It sets up an implicit wait, navigates to the homepage, logs in,
	 * and navigates to the products page.
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
		
	}

	/**
	 * Test 1: Verifies that the "SUBSCRIPTION" text is visible in the footer.
	 */
	@Test(priority = 1, description = "Verify that 'SUBSCRIPTION' text is visible")
    public void verifySubscriptionTextVisible() {
		// Creates a new SoftAssert instance for this test.
		softAssert = new SoftAssert();
		// Asserts that the subscription header is displayed.
        softAssert.assertTrue(page.getSubscriptionHeader().isDisplayed(), "'SUBSCRIPTION' header is not visible.");
        // Asserts that the header text is exactly "SUBSCRIPTION".
        softAssert.assertEquals(page.getSubscriptionHeader().getText(), "SUBSCRIPTION", "Header text is not correct.");
        // Collects and reports all assertions.
        softAssert.assertAll();
	}

	/**
	 * Test 2: Verifies that the email input field and subscribe button are visible.
	 */
    @Test(priority = 2, description = "Verify email input and subscribe button are visible")
    public void verifyEmailFieldAndButtonVisible() {
    	// Creates a new SoftAssert instance.
    	softAssert = new SoftAssert();
    	// Asserts that the email input field is displayed.
        softAssert.assertTrue(page.getSubscriptionEmailField().isDisplayed(), "Email input field is not visible.");
        // Asserts that the subscribe button is displayed.
        softAssert.assertTrue(page.getSubscribeButton().isDisplayed(), "Subscribe button is not visible.");
        // Collects and reports all assertions.
        softAssert.assertAll();
    }

	/**
	 * Test 3: Verifies a successful subscription using a valid email.
	 */
    @Test(priority = 3, description = "Verify successful subscription with valid email")
    public void verifySuccessfulSubscription() {
    	// Creates a new SoftAssert instance.
    	softAssert = new SoftAssert();
    	// Submits the subscription form with the test email.
        page.submitSubscription(TEST_EMAIL);
        // Asserts that the success message is displayed.
        softAssert.assertTrue(page.getSubscriptionSuccessMessage().isDisplayed(), "Success message did not appear.");
        // Waits for the email field to be cleared.
        page.waitForSubscriptionEmailToClear();
        // Collects and reports all assertions.
        softAssert.assertAll();
    }

	/**
	 * Test 4: Verifies the text of the success message after a valid submission.
	 */
    @Test(priority = 4, description = "Verify success message text after valid submission")
    public void verifySuccessMessageText() {
    	// Creates a new SoftAssert instance.
    	softAssert = new SoftAssert();
    	// Submits the subscription form with the test email.
        page.submitSubscription(TEST_EMAIL);
        // Asserts that the success message text is correct.
        softAssert.assertEquals(page.getSubscriptionSuccessMessage().getText(), "You have been successfully subscribed!", "Success message text is incorrect.");
        // Waits for the email field to be cleared.
        page.waitForSubscriptionEmailToClear();
        // Collects and reports all assertions.
        softAssert.assertAll();
    }

	/**
	 * Test 5: Verifies that the system accepts an invalid email format (based on test data).
	 */
    @Test(priority = 5, description = "Verify invalid email format is accepted")
    public void verifyInvalidEmailFormatAcceptance() {
    	// Creates a new SoftAssert instance.
    	softAssert = new SoftAssert();
    	// Submits the form with an email that has an invalid TLD.
        page.submitSubscription("asfac@com");
        // Asserts that the success message *is* displayed (documenting the system accepts this).
        softAssert.assertTrue(page.getSubscriptionSuccessMessage().isDisplayed(), "Success message did not appear for 'asfac@com'.");
        // Waits for the email field to be cleared.
        page.waitForSubscriptionEmailToClear();
        // Collects and reports all assertions.
        softAssert.assertAll();
    }

	/**
	 * Test 6: Verifies that the form cannot be submitted with an empty email field.
	 */
    @Test(priority = 6, description = "Verify empty email field cannot be submitted")
    public void verifyEmptyEmailSubmission() {
    	// Creates a new SoftAssert instance.
    	softAssert = new SoftAssert();
    	// Submits the form with an empty email string.
        page.submitSubscription("");
        // Asserts that the browser's HTML5 validation message is correct.
        softAssert.assertEquals(page.getSubscriptionEmailField().getAttribute("validationMessage"), "Please fill out this field.", "HTML5 validation message for empty field is incorrect or missing.");
        // Collects and reports all assertions.
        softAssert.assertAll();
    }

	/**
	 * Test 7: Verifies that the system allows duplicate subscriptions (based on test data).
	 */
    @Test(priority = 7, description = "Verify that the system allows duplicate subscriptions ")
    public void verifyDuplicateSubscriptions() {
    	// Creates a new SoftAssert instance.
    	softAssert = new SoftAssert();
    	// Submits the email for the first time.
        page.submitSubscription(TEST_EMAIL);
        // Waits for the success message to appear.
        page.getSubscriptionSuccessMessage(); 
        // Waits for the email field to clear.
        page.waitForSubscriptionEmailToClear(); 
        // Submits the *same* email for the second time.
        page.submitSubscription(TEST_EMAIL);
        // Asserts that the success message is displayed again (documenting that duplicates are allowed).
        softAssert.assertTrue(page.getSubscriptionSuccessMessage().isDisplayed(), "Success message did not appear for duplicate email.");
        // Collects and reports all assertions.
        softAssert.assertAll();
    }

	/**
	 * Test 8: Verifies that the email field is cleared after a successful submission.
	 */
    @Test(priority = 8, description = "Verify that the form clears after successful subscription")
    public void verifyFormClearsAfterSubmission() {
    	// Creates a new SoftAssert instance.
    	softAssert = new SoftAssert();
    	// Submits a valid email.
        page.submitSubscription(TEST_EMAIL);
        // Waits for the success message to appear.
        page.getSubscriptionSuccessMessage();
        // Asserts that the wait for the email field to clear returns true.
        softAssert.assertTrue(page.waitForSubscriptionEmailToClear(), "Email field did not clear after submission.");
        // Collects and reports all assertions.
        softAssert.assertAll();
    }

	/**
	 * Test 9: Verifies browser-level validation for an email with invalid characters (e.g., "@@").
	 */
    @Test(priority = 9, description = "Verify email field validation with invalid characters")
    public void verifyInvalidCharactersInEmail() {
    	// Creates a new SoftAssert instance.
    	softAssert = new SoftAssert();
    	// Submits an email with "@@".
        page.submitSubscription("mahmoud@@gmail.com");
        // Asserts that the browser's HTML5 validation message is correct.
        softAssert.assertEquals(page.getSubscriptionEmailField().getAttribute("validationMessage"), "A part following '@' should not contain the symbol '@'.", "HTML5 validation for '@@' failed.");
        // Asserts that the server-side success message is *not* displayed.
        softAssert.assertFalse(page.isSubscriptionSuccessMessageVisible(), "Success message appeared for invalid email '@@'.");
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

} // End of the UserStory13 class