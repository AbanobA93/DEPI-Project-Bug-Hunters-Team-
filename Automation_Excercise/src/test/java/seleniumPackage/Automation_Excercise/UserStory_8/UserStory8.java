// Specifies the package this class belongs to.
package seleniumPackage.Automation_Excercise.UserStory_8;

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
 * This class contains TestNG tests for "User Story 8",
 * which is focused on verifying the main Products page and product detail page functionality.
 */
public class UserStory8 {
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
	  // Navigates to the homepage using the method from the Pages class.
	  page.navigateToHomePage();
	  // Clicks the 'Signup / Login' button in the header.
	  page.clickLoginNavigationButton();
	  // Enters credentials and clicks 'Login'.
	  page.signin();
	  // Clicks the 'Products' button in the header to land on the products page.
	  page.clickProductNavigationButton();
  }

  /**
   * Test 1: Verifies that the 'Products' navigation button is visible and enabled.
   * @throws InterruptedException if the thread is interrupted.
   */
  @Test(priority = 1 , description = "Verify that the “Products” button is visible on the navigation menu")
  public void verifyProductsButtonVisibility() throws InterruptedException {
	  // Gets the 'Products' button WebElement from the Pages class.
	  WebElement productsNavigationButton = page.getProductNavigationButton();
	  // Asserts that the button is displayed.
	  softAssert.assertTrue(productsNavigationButton.isDisplayed(), "The Products Navigation Button is Not Visible");
	  // Asserts that the button is enabled (clickable).
	  softAssert.assertTrue(productsNavigationButton.isEnabled(), "The Products Navigation Button is Not Clickable");  
	  // Collects and reports all assertions.
	  softAssert.assertAll();
  }
  
  /**
   * Test 2: Verifies that clicking the 'Products' button navigates to the correct URL.
   */
  @Test(priority = 2 , description = "Verify navigation to “ALL PRODUCTS” page from the “Products” button")
  public void verifyNavigationToAllProductsPage() {
	  // Creates a new SoftAssert instance for this test.
	  softAssert = new SoftAssert();
	  // Clicks the 'Products' navigation button.
	  page.clickProductNavigationButton();
	  // Asserts that the current URL matches the expected products page URL.
	  softAssert.assertEquals(driver.getCurrentUrl(), "https://automationexercise.com/products","Not navigated to ALL PRODUCTS page URL");
	  // Collects and reports all assertions.
	  softAssert.assertAll();
  }
  
  /**
   * Test 3: Verifies that the 'ALL PRODUCTS' page displays the product grid.
   */
  @Test(priority = 3 , description = "Verify that the “ALL PRODUCTS” page displays product listings")
  public void verifyProductListingsDisplay() {
	  // Creates a new SoftAssert instance.
	  softAssert = new SoftAssert();
	  // Clicks the 'Products' navigation button.
	  page.clickProductNavigationButton();
	  // Gets the product items grid WebElement.
	  WebElement productsItemsGrid = page.getProductsItemsGrid();
	  // Asserts that the product grid is displayed.
	  softAssert.assertTrue(productsItemsGrid.isDisplayed(),"The products are not visible.");
	  // Collects and reports all assertions.
	  softAssert.assertAll();
  }
  
  /**
   * Test 4: Verifies the product search functionality using both the button and the Enter key.
   */
  @Test(priority = 4 , description = "Verify search/filter options on “ALL PRODUCTS” page")
  public void verifyProductSearchFunctionality() {
	  // Creates a new SoftAssert instance.
	  softAssert = new SoftAssert();
	  // Navigates to the products page.
	  page.clickProductNavigationButton();
	  // Defines a search keyword.
	  String searchKey = "shirt";
	  // Performs a search using the search button.
	  page.searchByButton(searchKey);
	  // Asserts that the URL updated correctly after the button search.
	  softAssert.assertEquals(driver.getCurrentUrl(),"https://automationexercise.com/products?search="+searchKey,"Search Function Doesnot Work using Search Button");
	  // Navigates back to the products page for the next check.
	  page.clickProductNavigationButton();
	  // Performs a search using the Enter key.
	  page.searchByEnter(searchKey);
	  // Asserts that the URL updated correctly after the Enter key search.
	  softAssert.assertEquals(driver.getCurrentUrl(),"https://automationexercise.com/products?search="+searchKey,"Search Function Doesnot Work using Enter Key");
	  // Collects and reports all assertions.
	  softAssert.assertAll();
  }
  
  /**
   * Test 5: Verifies that clicking 'View Product' navigates to the product detail page.
   */
  @Test(priority = 5, description = "Verify that user can click on a product to open its detail page.")
  public void verifyNavigationToProductDetailPage() {
	  // Creates a new SoftAssert instance.
	  softAssert = new SoftAssert();
	  // Navigates to the products page.
	  page.clickProductNavigationButton();
	  // Clicks the 'View Product' button for the first product.
	  page.clickViewFirstProductButton();
	  // Asserts that the current URL contains the expected path for product details.
	  softAssert.assertTrue(driver.getCurrentUrl().contains("/product_details/"), "Not navigated to a product detail page");
	  // Collects and reports all assertions.
	  softAssert.assertAll();
  }
  
  /**
   * Test 6: Verifies that all expected details (name, category, price, etc.) are correct on the product detail page.
   */
  @Test(priority = 6, description = "Verify product detail page displays name, category, price, availability, condition, and brand.")
  public void verifyProductDetails() {
	  // Creates a new SoftAssert instance.
	  softAssert = new SoftAssert();
	  // Navigates to the products page.
	  page.clickProductNavigationButton();
	  // Clicks the 'View Product' button for the first product.
	  page.clickViewFirstProductButton();
	  // Asserts that the product name matches the expected name from the Pages class.
	  softAssert.assertEquals( page.getFirstProductName(),page.firstProductName  ,"Product Name is not Correct");
	  // Asserts that the product category matches the expected category.
	  softAssert.assertEquals( page.getFirstProductCategory(),page.firstProductCategory  ,"Product Category is not Correct");
	  // Asserts that the product price matches the expected price.
	  softAssert.assertEquals( page.getFirstProductPrice(),page.firstProductPrice  ,"Product Price is not Correct");
	  // Asserts that the product availability matches the expected availability.
	  softAssert.assertEquals( page.getFirstProductAvaliability(),page.firstProductAvaliability  ,"Product Avaliability is not Correct");
	  // Asserts that the product condition matches the expected condition.
	  softAssert.assertEquals( page.getFirstProductCondition(),page.firstProductCondition  ,"Product Condition is not Correct");
	  // Asserts that the product brand matches the expected brand.
	  softAssert.assertEquals( page.getFirstProductBrand(),page.firstProductBrand  ,"Product Brand is not Correct");
	  // Collects and reports all assertions.
	  softAssert.assertAll();
  }
  
  /**
   * Test 7: Verifies that clicking the 'Products' button from a detail page navigates back to the main products page.
   */
  @Test(priority = 7, description = "Verify navigation back to 'ALL PRODUCTS' page from the product detail page.")
  public void verifyNavigationBackToAllProductsPage() {
	  // Creates a new SoftAssert instance.
	  softAssert = new SoftAssert();
	  // Navigates to the products page.
	  page.clickProductNavigationButton();
	  // Clicks the 'View Product' button to go to a detail page.
	  page.clickViewFirstProductButton();
	  // Clicks the 'Products' button again to go back.
	  page.clickProductNavigationButton();
	  // Asserts that the current URL is the main products page URL.
	  softAssert.assertEquals(driver.getCurrentUrl(), "https://automationexercise.com/products", "Not navigated back to ALL PRODUCTS page URL");
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

} // End of the UserStory8 class