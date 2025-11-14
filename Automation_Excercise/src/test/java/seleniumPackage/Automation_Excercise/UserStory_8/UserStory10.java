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
 * This class contains TestNG tests for "User Story 10", 
 * which is focused on verifying the category sidebar functionality.
 */
public class UserStory10 {
	// Initializes a new instance of ChromeDriver.
	WebDriver driver = new ChromeDriver();
	// Initializes a new instance of the Pages class, passing the driver to it.
	Pages page = new Pages(driver);
	// Initializes a SoftAssert object to allow multiple failures in a single test.
	SoftAssert softAssert = new SoftAssert();
	
	// Defines constant variables for expected category names.
	private final String WOMEN_CAT = "WOMEN";
	private final String DRESSES_SUBCAT = "DRESS";
	private final String TOPS_SUBCAT = "TOPS";
	private final String MEN_CAT = "MEN";
	private final String JEANS_SUBCAT = "JEANS";
	private final String TSHIRTS_SUBCAT = "TSHIRTS";
	private final String KIDS_CAT = "Kids";
	private final String KIDS_TOPS_SUBCAT = "TOPS & SHIRTS";

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
	 * Test 1: Verifies that the category sidebar header is visible.
	 */
	@Test(priority = 1, description = "Verify that category sidebar is visible and contains main categories.")
	public void verifyCategorySidebarVisibility() {
		// Creates a new SoftAssert instance for this test.
		softAssert = new SoftAssert();
		// Asserts that the category sidebar header is displayed.
		softAssert.assertTrue(page.getCategorySidebarHeader().isDisplayed(), "Category sidebar header is not visible");
		// Collects and reports all assertions.
		softAssert.assertAll();
	}

	/**
	 * Test 2: Verifies that the expected main categories are displayed.
	 */
	@Test(priority = 2, description = "Verify that all expected main categories are displayed in the sidebar.")
	public void verifyAllMainCategoriesDisplayed() {
		// Creates a new SoftAssert instance.
		softAssert = new SoftAssert();
		// Asserts that at least 3 main categories are found.
		softAssert.assertTrue(page.getAllMainCategories().size() >= 3, "Less than 3 main categories found");
		// Creates a new list to store the text of the category names.
		List<String> categoryNames = new ArrayList<>();
		// Loops through each category WebElement.
		for (WebElement category : page.getAllMainCategories()) {
			// Adds the text of the element to the list.
			categoryNames.add(category.getText());
		}
		// Asserts that "WOMEN" is in the list of category names.
		softAssert.assertTrue(categoryNames.contains("WOMEN"), "Women category is missing");
		// Asserts that "MEN" is in the list of category names.
		softAssert.assertTrue(categoryNames.contains("MEN"), "Men category is missing");
		// Asserts that "KIDS" is in the list of category names.
		softAssert.assertTrue(categoryNames.contains("KIDS"), "Kids category is missing");
		// Collects and reports all assertions.
		softAssert.assertAll();
	}

	/**
	 * Test 3: Verifies that subcategories appear under their main categories.
	 */
	@Test(priority = 3, description = "Verify that subcategories appear under main category.")
	public void verifySubcategoriesAppear() {
		// Creates a new SoftAssert instance.
		softAssert = new SoftAssert();
		// Creates a new list to store all subcategory names.
		List<String> subCategoryNames = new ArrayList<>();
		// Loops through each main category element.
		for (WebElement category : page.getAllMainCategories()) {
			// Clicks the main category to expand it.
			category.click();
			// Gets the list of subcategories for the clicked category.
			List<WebElement> subCategories = page.getSubCategories(category.getText());
			// Asserts that at least 2 subcategories were found.
			softAssert.assertTrue(subCategories.size() >= 2,
					"Less than 3 subcategories found under " + category.getText());
			// Loops through each subcategory element.
			for (WebElement subCategory : subCategories) {
				// Adds the subcategory text to the list.
				subCategoryNames.add(subCategory.getText());
			}
		}
		// Asserts that specific expected subcategories are present in the collected list.
		softAssert.assertTrue(subCategoryNames.contains("DRESS"), "DRESS subcategory is missing");
		softAssert.assertTrue(subCategoryNames.contains("TOPS"), "TOPS subcategory is missing");
		softAssert.assertTrue(subCategoryNames.contains("SAREE"), "SAREE subcategory is missing");
		softAssert.assertTrue(subCategoryNames.contains("TSHIRTS"), "TSHIRTS subcategory is missing");
		softAssert.assertTrue(subCategoryNames.contains("JEANS"), "JEANS subcategory is missing");
		softAssert.assertTrue(subCategoryNames.contains("TOPS & SHIRTS"), "TOPS & SHIRTS subcategory is missing");
		softAssert.assertTrue(subCategoryNames.contains("DRESS"), "DRESS subcategory is missing"); // Note: Duplicate check
		// Collects and reports all assertions.
		softAssert.assertAll();
	}

	/**
	 * Test 4: Verifies navigation to a subcategory page and checks the content.
	 */
	@Test(priority = 4, description = "Verify navigation to a selected subcategory and content match.")
	public void verifyNavigationAndProductListMatch() {
		// Creates a new SoftAssert instance.
		softAssert = new SoftAssert();
		
		// Clicks the 'DRESS' subcategory under the 'WOMEN' category.
		page.clickSubCategory(WOMEN_CAT, DRESSES_SUBCAT);
		// Gets the title of the resulting page.
		String title = page.getCategoryTitle();
		// Asserts that the title is correct.
		softAssert.assertEquals(title, "WOMEN - DRESS PRODUCTS",
				"Category title is incorrect after selecting Dresses.");
		// Gets the list of products displayed on the page.
		List<WebElement> products = page.getProductTitle();
		// Asserts that at least one product is displayed.
		softAssert.assertTrue(products.size() > 0, "No products found in the Women - Dress category.");
		// Collects and reports all assertions.
		softAssert.assertAll();
	}

	/**
	 * Test 5: Verifies the confirmatory text (title) on a category page.
	 */
	@Test(priority = 5, description = "Verify confirmatory text on category page.")
	public void verifyConfirmatoryText() {
		// Creates a new SoftAssert instance.
		softAssert = new SoftAssert();
		
		// Clicks the 'TOPS' subcategory under the 'WOMEN' category.
		page.clickSubCategory(WOMEN_CAT, TOPS_SUBCAT);
		// Gets the title of the resulting page.
		String title = page.getCategoryTitle();
		// Asserts that the title is correct.
		softAssert.assertEquals(title, "WOMEN - TOPS PRODUCTS",
				"Category title does not match 'Women - Tops Products'.");
		// Collects and reports all assertions.
		softAssert.assertAll();
	}

	/**
	 * Test 6: Verifies that the URL updates correctly after selecting a category.
	 */
	@Test(priority = 6, description = "Verify category selection updates URL.")
	public void verifyCategorySelectionUpdatesURL() {
		// Creates a new SoftAssert instance.
		softAssert = new SoftAssert();
		
		// Clicks the 'TSHIRTS' subcategory under the 'MEN' category.
		page.clickSubCategory(MEN_CAT, TSHIRTS_SUBCAT);
		// Asserts that the current URL contains the expected path for category products.
		softAssert.assertTrue(driver.getCurrentUrl().contains("category_products"),
				"URL did not update to the expected category format");
		// Collects and reports all assertions.
		softAssert.assertAll();
	}

	/**
	 * Test 7: Verifies that selecting a new category updates the product list.
	 */
	@Test(priority = 7, description = "Verify that selecting a new category updates products accordingly (cross-category check).")
	public void verifyNewCategoryUpdatesProducts() {
		// Creates a new SoftAssert instance.
		softAssert = new SoftAssert();
	
		// Clicks the 'TSHIRTS' subcategory under 'MEN'.
		page.clickSubCategory(MEN_CAT, TSHIRTS_SUBCAT);
		// Stores the title of the first page.
		String firstTitle = page.getCategoryTitle();
		// Clicks the 'KIDS' main category.
		page.clickMainCategory(KIDS_CAT);
		// Clicks the 'TOPS & SHIRTS' subcategory under 'KIDS'.
		page.clickSubCategory(KIDS_CAT, KIDS_TOPS_SUBCAT);
		// Stores the title of the second page.
		String secondTitle = page.getCategoryTitle();
		// Asserts that the second title is correct.
		softAssert.assertEquals(secondTitle, "KIDS - TOPS & SHIRTS PRODUCTS", "Second category title is incorrect.");
		// Asserts that the first and second titles are different.
		softAssert.assertNotEquals(firstTitle, secondTitle,
				"Category title did not change after selecting a new category.");
		// Gets the list of products on the second page.
		List<WebElement> products = page.getProductTitle();
		// Asserts that at least one product is displayed.
		softAssert.assertTrue(products.size() > 0, "No products found after selecting the second category.");
		// Collects and reports all assertions.
		softAssert.assertAll();
	}

	/**
	 * Test 8: Verifies that there are no duplicate main categories or subcategories.
	 */
	@Test(priority = 8, description = "Verify that no duplicate categories or subcategories appear.")
	public void verifyNoDuplicateCategories() {
		// Creates a new SoftAssert instance.
		softAssert = new SoftAssert();
		// Gets all main category WebElements.
		List<WebElement> mainCategories = page.getAllMainCategories();
		// Creates a Set to store main category names (sets only allow unique values).
		Set<String> categoryNames = new HashSet<>();
		// Loops through each main category.
		for (WebElement cat : mainCategories) {
			// Gets the trimmed text of the category.
			String name = cat.getText().trim();
			// Asserts that the name is not already in the set (i.e., it's not a duplicate).
			softAssert.assertFalse(categoryNames.contains(name), "Duplicate main category found: " + name);
			// Adds the name to the set.
			categoryNames.add(name);
			// Special check for 'WOMEN' category subcategories.
			if (name.equals(WOMEN_CAT)) {
				// Clicks the 'WOMEN' category.
				page.clickMainCategory(WOMEN_CAT);
				// Gets all subcategories under 'WOMEN'.
				List<WebElement> subCategories = page.getSubCategories(WOMEN_CAT);
				// Creates a Set to store subcategory names.
				Set<String> subCategoryNames = new HashSet<>();
				// Loops through each subcategory.
				for (WebElement subCat : subCategories) {
					// Gets the trimmed text of the subcategory.
					String subName = subCat.getText().trim();
					// Asserts that the subcategory name is not already in the set.
					softAssert.assertFalse(subCategoryNames.contains(subName),
							"Duplicate subcategory found: " + subName);
					// Adds the subcategory name to the set.
					subCategoryNames.add(subName);
				}
			}
		}
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

} // End of the UserStory10 class