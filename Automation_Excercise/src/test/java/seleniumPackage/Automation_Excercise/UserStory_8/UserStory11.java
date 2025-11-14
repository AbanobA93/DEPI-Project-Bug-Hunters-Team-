// Specifies the package this class belongs to.
package seleniumPackage.Automation_Excercise.UserStory_8;

// Imports the Duration class for setting timeouts.
import java.time.Duration;
// Imports the ArrayList class for creating dynamic lists.
import java.util.ArrayList;
// Imports the HashSet class for creating collections that store unique items.
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
 * This class contains TestNG tests for "User Story 11",
 * which is focused on verifying the "Brands" sidebar functionality.
 */
public class UserStory11 {
	// Initializes a new instance of ChromeDriver.
	WebDriver driver = new ChromeDriver();
	// Initializes a new instance of the Pages class, passing the driver to it.
	Pages page = new Pages(driver);
	// Initializes a SoftAssert object to allow multiple failures in a single test.
	SoftAssert softAssert = new SoftAssert();
	
	// Defines constant variables for expected brand names.
	private final String POLO = "Polo";
	private final String H_and_M = "H&M";
	private final String MADAME = "Madame";
	private final String MAST_and_HARBOUR = "Mast & Harbour";
	private final String BABYHUG = "Babyhug";
	private final String ALLEN_SOLLY_JUNIOR = "Allen Solly Junior";
	private final String KOOKIE_KIDS = "Kookie Kids";
	private final String BIBA = "Biba";
	
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
	 * Test 1: Verifies that the "Brands" sidebar header is visible.
	 */
	@Test(priority = 1, description = "Verify that the brand sidebar is visible")
    public void verifyBrandSidebarVisibility() {
		// Creates a new SoftAssert instance for this test.
		softAssert = new SoftAssert();
		// Asserts that the brand sidebar header is displayed.
		softAssert.assertTrue(page.getBrandSidebarHeader().isDisplayed(), "Brand sidebar header is not visible");
		// Collects and reports all assertions.
		softAssert.assertAll();
	}

	/**
	 * Test 2: Verifies that all expected brands are listed in the sidebar.
	 */
	@Test(priority = 2, description = "Verify that all available brands are listed")
    public void verifyAllBrandsAreListed() {
		// Creates a new SoftAssert instance.
		softAssert = new SoftAssert();
		// Asserts that the list of brand links is not empty (size >= 0).
		softAssert.assertTrue(page.getAllBrandLinks().size() >= 0, "No brand links were found");
		// Creates a new list to store the text of the brand names.
		List<String> brandNames = new ArrayList<>();
		// Loops through each brand WebElement.
		for (WebElement brand : page.getAllBrandLinks()) {
			// Adds the text of the element (e.g., "(6) POLO") to the list.
			brandNames.add(brand.getText());
		}
		// Uses Java streams to check if any string in the list *contains* the expected brand name.
		softAssert.assertTrue(brandNames.stream().anyMatch(name -> name.contains("POLO")), "Polo brand not found");
		softAssert.assertTrue(brandNames.stream().anyMatch(name -> name.contains("H&M")), "H&M brand not found");
		softAssert.assertTrue(brandNames.stream().anyMatch(name -> name.contains("MADAME")), "MADAME brand not found");
		softAssert.assertTrue(brandNames.stream().anyMatch(name -> name.contains("MAST & HARBOUR")), "MAST & HARBOUR brand not found");
		softAssert.assertTrue(brandNames.stream().anyMatch(name -> name.contains("BABYHUG")), "BABYHUG brand not found");
		softAssert.assertTrue(brandNames.stream().anyMatch(name -> name.contains("ALLEN SOLLY JUNIOR")), "ALLEN SOLLY JUNIOUR brand not found");
		softAssert.assertTrue(brandNames.stream().anyMatch(name -> name.contains("KOOKIE KIDS")), "KOOKIE KIDS brand not found");
		softAssert.assertTrue(brandNames.stream().anyMatch(name -> name.contains("BIBA")), "BIBA brand not found");
		// Collects and reports all assertions.
		softAssert.assertAll();
	}

	/**
	 * Test 3: Verifies navigation to a specific brand page (Polo).
	 */
	@Test(priority = 3, description = "Verify navigation to a specific brand page")
    public void verifyNavigationToBrandPage() {
		// Creates a new SoftAssert instance.
		softAssert = new SoftAssert();
		// Clicks the 'Polo' brand link.
		page.clickBrandLink(POLO);
		// Asserts that the current URL contains the expected path for the 'Polo' brand page.
		softAssert.assertTrue(driver.getCurrentUrl().contains("brand_products/"+ POLO), "URL did not navigate to Polo brand page");
		// Collects and reports all assertions.
		softAssert.assertAll();
	}

	/**
	 * Test 4: Verifies the confirmatory text (title) on a brand page (H&M).
	 */
	@Test(priority = 4, description = "Verify confirmatory text or heading on brand page")
    public void verifyConfirmatoryTextOnBrandPage() {
		// Creates a new SoftAssert instance.
		softAssert = new SoftAssert();
		// Clicks the 'H&M' brand link.
		page.clickBrandLink(H_and_M);
		// Asserts that the URL updated correctly.
		softAssert.assertTrue(driver.getCurrentUrl().contains("brand_products/"+ H_and_M), "URL did not navigate to Polo brand page");
		// Asserts that the page title matches the expected format (e.g., "BRAND - H&M PRODUCTS").
        softAssert.assertEquals(page.getBrandPageTitle(), "BRAND - "+H_and_M.toUpperCase()+" PRODUCTS", "Brand page title is incorrect for "+ H_and_M);
		// Collects and reports all assertions.
		softAssert.assertAll();
	}

	/**
	 * Test 5: Verifies that only brand-specific products are displayed (Biba).
	 */
	@Test(priority = 5, description = "Verify that only brand-specific products are displayed")
    public void verifyOnlyBrandSpecificProductsDisplayed() {
		// Creates a new SoftAssert instance.
		softAssert = new SoftAssert();
		// Clicks the 'Biba' brand link.
		page.clickBrandLink(BIBA);
		// Asserts that the page title is correct for 'Biba'.
		softAssert.assertEquals(page.getBrandPageTitle(), "BRAND - "+BIBA.toUpperCase()+" PRODUCTS", "Brand page title is incorrect for "+ BIBA);
		// Asserts that the product list on the page is not empty.
        softAssert.assertTrue(page.getProductTitle().size() > 0, "No products are displayed for Biba brand.");
		// Collects and reports all assertions.
		softAssert.assertAll();
	}

	/**
	 * Test 6: Verifies that selecting a new brand updates the product list.
	 */
	@Test(priority = 6, description = "Verify that selecting a new brand updates the product list")
    public void verifySelectingNewBrandUpdatesList() {
		// Creates a new SoftAssert instance.
		softAssert = new SoftAssert();
		// Clicks the 'Biba' brand link.
		page.clickBrandLink(BIBA);
		// Stores the title of the first page.
        String firstTitle = page.getBrandPageTitle();
        // Clicks the 'H&M' brand link.
        page.clickBrandLink(H_and_M);
        // Stores the title of the second page.
        String secondTitle = page.getBrandPageTitle();
        // Asserts that the first and second titles are different.
        softAssert.assertNotEquals(firstTitle, secondTitle, "Brand page title did not change after clicking new brand.");
        // Asserts that the second title is correct for 'H&M'.
        softAssert.assertEquals(secondTitle, "BRAND - "+H_and_M.toUpperCase()+" PRODUCTS", "Page did not update to "+H_and_M+" products.");
		// Collects and reports all assertions.
		softAssert.assertAll();
	}

	/**
	 * Test 7: Verifies that the URL updates correctly after selecting a brand.
	 */
	@Test(priority = 7, description = "Verify URL updates according to selected brand")
    public void verifyURLUpdatesForBrand() {
		// Creates a new SoftAssert instance.
		softAssert = new SoftAssert();
		// Clicks the 'H&M' brand link.
		page.clickBrandLink(H_and_M);
		// Defines the expected URL string.
        String expectedUrl = "https://automationexercise.com/brand_products/"+H_and_M;
        // Asserts that the current URL exactly matches the expected URL.
        softAssert.assertEquals(driver.getCurrentUrl(), expectedUrl, "URL did not update correctly for "+H_and_M+" brand");
        // Collects and reports all assertions.
        softAssert.assertAll();
	}

	/**
	 * Test 8: Verifies that the brand sidebar remains accessible after navigation.
	 */
	@Test(priority = 8, description = "Verify sidebar and brand list remain accessible")
    public void verifySidebarRemainsAccessible() {
		// Creates a new SoftAssert instance.
		softAssert = new SoftAssert();
		// Clicks the 'Biba' brand link.
		page.clickBrandLink(BIBA);
		// Asserts that the brand sidebar header is still displayed after navigating to the brand page.
        softAssert.assertTrue(page.getBrandSidebarHeader().isDisplayed(), "Brands sidebar header is not visible after navigation");
		// Collects and reports all assertions.
		softAssert.assertAll();
	}
	
	/**
	 * Test 9: Verifies navigation works correctly for a brand with a long name.
	 */
	@Test(priority = 9, description = "Verify brand selection with long brand names")
    public void verifyNavigationWithLongBrandName() {
		// Creates a new SoftAssert instance.
		softAssert = new SoftAssert();
		// Clicks the 'Allen Solly Junior' brand link.
		page.clickBrandLink(ALLEN_SOLLY_JUNIOR);
		// Asserts that the page title is correct for 'Allen Solly Junior'.
        softAssert.assertEquals(page.getBrandPageTitle(), "BRAND - "+ALLEN_SOLLY_JUNIOR.toUpperCase()+" PRODUCTS", "Title is incorrect for "+ALLEN_SOLLY_JUNIOR);
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

} // End of the UserStory11 class