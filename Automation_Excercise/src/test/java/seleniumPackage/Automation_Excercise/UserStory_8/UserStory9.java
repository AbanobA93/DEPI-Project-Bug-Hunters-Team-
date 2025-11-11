package seleniumPackage.Automation_Excercise.UserStory_8;

import java.util.List;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
public class UserStory9 {
  WebDriver driver = new ChromeDriver();
  Pages page = new Pages(driver);
  SoftAssert softAssert = new SoftAssert(); 
  
  @BeforeTest
  public void initialize() {
	  page.navigateToHomePage();
	  page.clickLoginNavigationButton();
	  page.signin();
	  page.clickProductNavigationButton();
  }

  @Test(priority = 1 , description = "Verify that the search bar is visible on the page")
  public void verifySearchBarVisibility() throws InterruptedException {
	  softAssert = new SoftAssert();
	  softAssert.assertTrue(page.getSearchField().isDisplayed(), "The Search bar isn't visible");
	  softAssert.assertAll();
  }
  @Test(priority = 2 , description = "Verify search by exact product name")
  public void verifySearchByExactProductName() {
	  softAssert = new SoftAssert();
	  page.searchByButton(page.exactSearchKeyword);
	  List<WebElement> results = page.getProductTitle();
	  softAssert.assertEquals(results.size(), 1, "Expected exactly 1 product result for exact search");
	  softAssert.assertEquals(results.get(0).getText().trim(), page.exactSearchKeyword, "The displayed product name does not match the exact search term");
	  softAssert.assertAll();
  }
  @Test(priority = 3 , description = "Verify search by partial product name")
  public void verifySearchByPartialProductName() {
	  softAssert = new SoftAssert();
	  page.searchFieldClear();
	  page.searchByButton(page.partialSearchKeyword);
	  List<WebElement> results = page.getProductTitle();
	  softAssert.assertTrue(results.size() > 0, "No products found for partial search term: " + page.partialSearchKeyword);
	  for (WebElement product : results) {
          String productName = product.getText().toLowerCase();
          softAssert.assertTrue(productName.contains(page.partialSearchKeyword.toLowerCase()), 
                            "Product name '" + productName + "' does not contain the partial search term '" + page.partialSearchKeyword + "'.");
      }
	  softAssert.assertAll();
  }
  @Test(priority = 4 , description = "Verify search results for case insensitivity")
  public void verifySearchCaseInsensitivity() {
	  softAssert = new SoftAssert();
	  page.searchFieldClear();
	  String expectedProductName = page.exactSearchKeyword;
	  page.searchByButton(page.mixedCaseSearchKeyword);
	  List<WebElement> results = page.getProductTitle();
	  softAssert.assertEquals(results.size(), 1, "Expected exactly 1 product result for case-insensitive search");
	  softAssert.assertEquals(results.get(0).getText().trim(), expectedProductName, "Case-insensitive search failed to find the product");
	  softAssert.assertAll();
  }
  @Test(priority = 5, description = "Verify behavior when no matching products are found")
  public void verifyNoMatchingProductsFound() {
	  softAssert = new SoftAssert();
	  page.searchFieldClear();
	  page.searchByButton(page.nonExistantProductKeyword);
	  List<WebElement> results = page.getProductTitle();
	  softAssert.assertTrue(results.size()==0,"Product list is NOT empty for non-existent product search");
	  softAssert.assertTrue(page.isNoProductFoundMessageVisible(),"No Product message is not visible indicating that products were found");
	  softAssert.assertAll();
  }
  @Test(priority = 6, description = "Verify search functionality with special characters")
  public void verifySearchWithSpecialCharacters() {
	  softAssert = new SoftAssert();
	  page.searchFieldClear();
	  page.searchByButton(page.specialCharSearchKeyword);
	  List<WebElement> results = page.getProductTitle();
	  softAssert.assertTrue(results.size() > 0,  "No results found when searching with special characters ('-')");
	  softAssert.assertAll();
  }
  
  @Test(priority = 7, description = "Verify search with leading/trailing spaces")
  public void verifySearchWithLeadingTrailingSpaces() {
	  softAssert = new SoftAssert();
	  page.searchFieldClear();
	  page.searchByButton(page.searchWithSpacesKeyword);
	  List<WebElement> results = page.getProductTitle();
	  softAssert.assertTrue(results.size() > 0, "Search failed to find results when using leading/trailing spaces");
	  for (WebElement product : results) {
          String productName = product.getText().toLowerCase();
          softAssert.assertTrue(productName.contains(page.searchWithSpacesKeyword.trim().toLowerCase()), 
                            "Product name '" + productName + "' does not contain the word 'white' (case-insensitive) after spaced search");
      }
	  softAssert.assertAll();
  }
  @Test(priority = 8, description = "Verify that the search results update when a new search is performed")
  public void verifySearchResultsUpdate() {
	  softAssert = new SoftAssert();
	  page.searchFieldClear();
	  verifySearchByExactProductName();
	  verifySearchByPartialProductName();
	  softAssert.assertAll();
  }
  
  
  @Test(priority = 9, description = "Verify search with empty input")
  public void verifySearchWithEmptyInput() {
	  softAssert = new SoftAssert();
	  page.searchFieldClear();
	  page.searchByButton("");
	  softAssert.assertEquals(page.getAllProductsHeader().trim(), "ALL PRODUCTS","Search with empty input did not revert to 'ALL PRODUCTS' view");
	  softAssert.assertAll();
  }
  
  @Test(priority = 10 , description = "Verify if search is performed using Enter key and search icon both")
  public void verifySearchTriggerMethods() {
	  softAssert = new SoftAssert();
	  page.clickProductNavigationButton();
	  String searchKey = "shirt";
	  page.searchByButton(searchKey);
	  softAssert.assertEquals(driver.getCurrentUrl(),"https://automationexercise.com/products?search="+searchKey,"Search Function Doesnot Work using Search Button");
	  page.clickProductNavigationButton();
	  page.searchByEnter(searchKey);
	  softAssert.assertEquals(driver.getCurrentUrl(),"https://automationexercise.com/products?search="+searchKey,"Search Function Doesnot Work using Enter Key");
	  softAssert.assertAll();
  }
  
  @AfterTest
  public void closeBrowser() {
	   driver.close();
  }

}
