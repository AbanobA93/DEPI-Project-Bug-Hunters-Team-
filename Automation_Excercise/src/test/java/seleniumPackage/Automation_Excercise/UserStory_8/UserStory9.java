// Specifies the package this class belongs to.
package seleniumPackage.Automation_Excercise.UserStory_8;

// Imports the List interface for handling collections of WebElements.
import java.util.List;

// Imports all public classes and interfaces from org.openqa.selenium.
import org.openqa.selenium.*;
// Imports the ChromeDriver class.
import org.openqa.selenium.chrome.*;
// Imports the Assert class from TestNG for hard assertions.
import org.testng.Assert;
// Imports all TestNG annotations (like @Test, @BeforeTest, etc.).
import org.testng.annotations.*;
// Imports the SoftAssert class from TestNG for soft assertions.
import org.testng.asserts.SoftAssert;

/**
 * This class contains TestNG tests for "User Story 9", 
 * which appears to be focused on product search functionality.
 */
public class UserStory9 {
  // Initializes a new instance of ChromeDriver.
  WebDriver driver = new ChromeDriver();
  // Initializes a new instance of the Pages class, passing the driver to it.
  Pages page = new Pages(driver);
  // Initializes a SoftAssert object to allow multiple failures in a single test.
  SoftAssert softAssert = new SoftAssert(); 
  
  /**
   * This method runs once before any @Test methods in this class.
   * It performs the initial setup: navigating to the homepage, logging in,
   * and navigating to the products page.
   */
  @BeforeTest
  public void initialize() {
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
   * Test 1: Verifies that the search bar is visible on the products page.
   * @throws InterruptedException if the thread is interrupted while sleeping (though Thread.sleep is not used here).
   */
  @Test(priority = 1 , description = "Verify that the search bar is visible on the page")
  public void verifySearchBarVisibility() throws InterruptedException {
	  // Creates a new SoftAssert instance for this test method.
	  softAssert = new SoftAssert();
	  // Asserts that the search field is displayed, otherwise logs a failure message.
	  softAssert.assertTrue(page.getSearchField().isDisplayed(), "The Search bar isn't visible");
	  // Collects all soft assertions; if any failed, the test is marked as failed here.
	  softAssert.assertAll();
  }
  
  /**
   * Test 2: Verifies that searching for an exact product name returns only that product.
   */
  @Test(priority = 2 , description = "Verify search by exact product name")
  public void verifySearchByExactProductName() {
	  // Creates a new SoftAssert instance.
	  softAssert = new SoftAssert();
	  // Performs a search using the exact keyword from the Pages class.
	  page.searchByButton(page.exactSearchKeyword);
	  // Gets the list of product titles from the search results.
	  List<WebElement> results = page.getProductTitle();
	  // Asserts that exactly one product was returned.
	  softAssert.assertEquals(results.size(), 1, "Expected exactly 1 product result for exact search");
	  // Asserts that the name of the returned product matches the search keyword.
	  softAssert.assertEquals(results.get(0).getText().trim(), page.exactSearchKeyword, "The displayed product name does not match the exact search term");
	  // Collects and reports all assertions.
	  softAssert.assertAll();
  }
  
  /**
   * Test 3: Verifies that searching for a partial product name returns all relevant products.
   */
  @Test(priority = 3 , description = "Verify search by partial product name")
  public void verifySearchByPartialProductName() {
	  // Creates a new SoftAssert instance.
	  softAssert = new SoftAssert();
	  // Clears the search field from the previous test.
	  page.searchFieldClear();
	  // Performs a search using the partial keyword.
	  page.searchByButton(page.partialSearchKeyword);
	  // Gets the list of search results.
	  List<WebElement> results = page.getProductTitle();
	  // Asserts that at least one product was found.
	  softAssert.assertTrue(results.size() > 0, "No products found for partial search term: " + page.partialSearchKeyword);
	  // Loops through each product in the results.
	  for (WebElement product : results) {
		  // Gets the product's name and converts it to lowercase.
          String productName = product.getText().toLowerCase();
          // Asserts that the product name contains the partial search keyword (also in lowercase).
          softAssert.assertTrue(productName.contains(page.partialSearchKeyword.toLowerCase()), 
                            "Product name '" + productName + "' does not contain the partial search term '" + page.partialSearchKeyword + "'.");
      }
	  // Collects and reports all assertions.
	  softAssert.assertAll();
  }
  
  /**
   * Test 4: Verifies that the search is case-insensitive.
   */
  @Test(priority = 4 , description = "Verify search results for case insensitivity")
  public void verifySearchCaseInsensitivity() {
	  // Creates a new SoftAssert instance.
	  softAssert = new SoftAssert();
	  // Clears the search field.
	  page.searchFieldClear();
	  // Sets the expected result to be the exact product name.
	  String expectedProductName = page.exactSearchKeyword;
	  // Performs a search using a mixed-case version of the keyword.
	  page.searchByButton(page.mixedCaseSearchKeyword);
	  // Gets the search results.
	  List<WebElement> results = page.getProductTitle();
	  // Asserts that exactly one product was found.
	  softAssert.assertEquals(results.size(), 1, "Expected exactly 1 product result for case-insensitive search");
	  // Asserts that the product found matches the expected (properly cased) product name.
	  softAssert.assertEquals(results.get(0).getText().trim(), expectedProductName, "Case-insensitive search failed to find the product");
	  // Collects and reports all assertions.
	  softAssert.assertAll();
  }
  
  /**
   * Test 5: Verifies the behavior when no products match the search term.
   */
  @Test(priority = 5, description = "Verify behavior when no matching products are found")
  public void verifyNoMatchingProductsFound() {
	  // Creates a new SoftAssert instance.
	  softAssert = new SoftAssert();
	  // Clears the search field.
	  page.searchFieldClear();
	  // Performs a search using a keyword for a non-existent product.
	  page.searchByButton(page.nonExistantProductKeyword);
	  // Gets the search results.
	  List<WebElement> results = page.getProductTitle();
	  // Asserts that the results list is empty (size is 0).
	  softAssert.assertTrue(results.size()==0,"Product list is NOT empty for non-existent product search");
	  // Asserts that the 'no product found' message/state is visible (as defined in the Pages class).
	  softAssert.assertTrue(page.isNoProductFoundMessageVisible(),"No Product message is not visible indicating that products were found");
	  // Collects and reports all assertions.
	  softAssert.assertAll();
  }
  
  /**
   * Test 6: Verifies that the search works with keywords containing special characters.
   */
  @Test(priority = 6, description = "Verify search functionality with special characters")
  public void verifySearchWithSpecialCharacters() {
	  // Creates a new SoftAssert instance.
	  softAssert = new SoftAssert();
	  // Clears the search field.
	  page.searchFieldClear();
	  // Performs a search using a keyword with special characters.
	  page.searchByButton(page.specialCharSearchKeyword);
	  // Gets the search results.
	  List<WebElement> results = page.getProductTitle();
	  // Asserts that at least one product was found.
	  softAssert.assertTrue(results.size() > 0,  "No results found when searching with special characters ('-')");
	  // Collects and reports all assertions.
	  softAssert.assertAll();
  }
  
  /**
   * Test 7: Verifies that the search handles leading and trailing spaces in the keyword.
   */
  @Test(priority = 7, description = "Verify search with leading/trailing spaces")
  public void verifySearchWithLeadingTrailingSpaces() {
	  // Creates a new SoftAssert instance.
	  softAssert = new SoftAssert();
	  // Clears the search field.
	  page.searchFieldClear();
	  // Performs a search using a keyword with spaces.
	  page.searchByButton(page.searchWithSpacesKeyword);
	  // Gets the search results.
	  List<WebElement> results = page.getProductTitle();
	  // Asserts that at least one product was found.
	  softAssert.assertTrue(results.size() > 0, "Search failed to find results when using leading/trailing spaces");
	  // Loops through each product in the results.
	  for (WebElement product : results) {
		  // Gets the product name in lowercase.
          String productName = product.getText().toLowerCase();
          // Asserts that the product name contains the trimmed, lowercase version of the search keyword.
          softAssert.assertTrue(productName.contains(page.searchWithSpacesKeyword.trim().toLowerCase()), 
                            "Product name '" + productName + "' does not contain the word 'white' (case-insensitive) after spaced search");
      }
	  // Collects and reports all assertions.
	  softAssert.assertAll();
  }
  
  /**
   * Test 8: Verifies that search results update by running two different searches back-to-back.
   * Note: This test re-runs the logic from other test methods.
   */
  @Test(priority = 8, description = "Verify that the search results update when a new search is performed")
  public void verifySearchResultsUpdate() {
	  // Creates a new SoftAssert instance.
	  softAssert = new SoftAssert();
	  // Clears the search field.
	  page.searchFieldClear();
	  // Runs the exact product name search logic.
	  verifySearchByExactProductName();
	  // Runs the partial product name search logic.
	  verifySearchByPartialProductName();
	  // Collects and reports all assertions (from both called methods).
	  softAssert.assertAll();
  }
  
  
  /**
   * Test 9: Verifies that searching with an empty string returns the 'ALL PRODUCTS' view.
   */
  @Test(priority = 9, description = "Verify search with empty input")
  public void verifySearchWithEmptyInput() {
	  // Creates a new SoftAssert instance.
	  softAssert = new SoftAssert();
	  // Clears the search field.
	  page.searchFieldClear();
	  // Performs a search with an empty string.
	  page.searchByButton("");
	  // Asserts that the page header is 'ALL PRODUCTS', indicating the page reset.
	  softAssert.assertEquals(page.getAllProductsHeader().trim(), "ALL PRODUCTS","Search with empty input did not revert to 'ALL PRODUCTS' view");
	  // Collects and reports all assertions.
	  softAssert.assertAll();
  }
  
  /**
   * Test 10: Verifies that the search is triggered by both the 'Enter' key and the search button.
   */
  @Test(priority = 10 , description = "Verify if search is performed using Enter key and search icon both")
  public void verifySearchTriggerMethods() {
	  // Creates a new SoftAssert instance.
	  softAssert = new SoftAssert();
	  // Navigates back to the products page to ensure a clean state.
	  page.clickProductNavigationButton();
	  // Defines a search keyword.
	  String searchKey = "shirt";
	  // Performs a search using the button.
	  page.searchByButton(searchKey);
	  // Asserts that the URL updated correctly for a button search.
	  softAssert.assertEquals(driver.getCurrentUrl(),"https://automationexercise.com/products?search="+searchKey,"Search Function Doesnot Work using Search Button");
	  // Navigates back to the products page.
	  page.clickProductNavigationButton();
	  // Performs a search using the 'Enter' key.
	  page.searchByEnter(searchKey);
	  // Asserts that the URL updated correctly for an 'Enter' key search.
	  softAssert.assertEquals(driver.getCurrentUrl(),"https://automationexercise.com/products?search="+searchKey,"Search Function Doesnot Work using Enter Key");
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

} // End of the UserStory9 class