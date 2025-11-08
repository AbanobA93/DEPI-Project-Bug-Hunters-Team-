package seleniumPackage.Automation_Excercise.UserStory_8;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
public class UserStory8 {
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

  @Test(priority = 1 , description = "Verify that the “Products” button is visible on the navigation menu")
  public void verifyProductsButtonVisibility() throws InterruptedException {
	  WebElement productsNavigationButton = page.getProductNavigationButton();
	  softAssert.assertTrue(productsNavigationButton.isDisplayed(), "The Products Navigation Button is Not Visible");
	  softAssert.assertTrue(productsNavigationButton.isEnabled(), "The Products Navigation Button is Not Clickable");  
	  softAssert.assertAll();
  }
  @Test(priority = 2 , description = "Verify navigation to “ALL PRODUCTS” page from the “Products” button")
  public void verifyNavigationToAllProductsPage() {
	  softAssert = new SoftAssert();
	  page.clickProductNavigationButton();
	  softAssert.assertEquals(driver.getCurrentUrl(), "https://automationexercise.com/products","Not navigated to ALL PRODUCTS page URL");
	  softAssert.assertAll();
  }
  @Test(priority = 3 , description = "Verify that the “ALL PRODUCTS” page displays product listings")
  public void verifyProductListingsDisplay() {
	  softAssert = new SoftAssert();
	  page.clickProductNavigationButton();
	  WebElement productsItemsGrid = page.getProductsItemsGrid();
	  softAssert.assertTrue(productsItemsGrid.isDisplayed(),"The products are not visible.");
	  softAssert.assertAll();
  }
  @Test(priority = 4 , description = "Verify search/filter options on “ALL PRODUCTS” page")
  public void verifyProductSearchFunctionality() {
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
  @Test(priority = 5, description = "Verify that user can click on a product to open its detail page.")
  public void verifyNavigationToProductDetailPage() {
	  softAssert = new SoftAssert();
	  page.clickProductNavigationButton();
	  page.clickViewFirstProductButton();
	  softAssert.assertTrue(driver.getCurrentUrl().contains("/product_details/"), "Not navigated to a product detail page");
	  softAssert.assertAll();
  }
  @Test(priority = 6, description = "Verify product detail page displays name, category, price, availability, condition, and brand.")
  public void verifyProductDetails() {
	  softAssert = new SoftAssert();
	  page.clickProductNavigationButton();
	  page.clickViewFirstProductButton();
	  softAssert.assertEquals( page.getFirstProductName(),page.firstProductName  ,"Product Name is not Correct");
	  softAssert.assertEquals( page.getFirstProductCategory(),page.firstProductCategory  ,"Product Category is not Correct");
	  softAssert.assertEquals( page.getFirstProductPrice(),page.firstProductPrice  ,"Product Price is not Correct");
	  softAssert.assertEquals( page.getFirstProductAvaliability(),page.firstProductAvaliability  ,"Product Avaliability is not Correct");
	  softAssert.assertEquals( page.getFirstProductCondition(),page.firstProductCondition  ,"Product Condition is not Correct");
	  softAssert.assertEquals( page.getFirstProductBrand(),page.firstProductBrand  ,"Product Brand is not Correct");
	  softAssert.assertAll();
  }
  
  @Test(priority = 7, description = "Verify navigation back to 'ALL PRODUCTS' page from the product detail page.")
  public void verifyNavigationBackToAllProductsPage() {
	  softAssert = new SoftAssert();
	  page.clickProductNavigationButton();
	  page.clickViewFirstProductButton();
	  page.clickProductNavigationButton();
	  softAssert.assertEquals(driver.getCurrentUrl(), "https://automationexercise.com/products", "Not navigated back to ALL PRODUCTS page URL");
	  softAssert.assertAll();
  }
  @AfterTest
  public void closeBrowser() {
	   driver.close();
  }

}
