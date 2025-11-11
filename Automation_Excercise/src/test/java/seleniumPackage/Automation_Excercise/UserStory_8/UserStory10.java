package seleniumPackage.Automation_Excercise.UserStory_8;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
public class UserStory10 {
  WebDriver driver = new ChromeDriver();
  Pages page = new Pages(driver);
  SoftAssert softAssert = new SoftAssert(); 
  private final String WOMEN_CAT = "WOMEN";
  private final String DRESSES_SUBCAT = "DRESS";
  private final String TOPS_SUBCAT = "TOPS";
  private final String MEN_CAT = "MEN";
  private final String JEANS_SUBCAT = "JEANS";
  private final String TSHIRTS_SUBCAT = "TSHIRTS";
  private final String KIDS_CAT = "Kids";
  private final String KIDS_TOPS_SUBCAT = "TOPS & SHIRTS";
  @BeforeTest
  public void initialize() {
	  page.navigateToHomePage();
	  page.clickLoginNavigationButton();
	  page.signin();
	  page.clickProductNavigationButton();
  }

  @Test(priority = 1, description = "Verify that category sidebar is visible and contains main categories.")
  public void verifyCategorySidebarVisibility() {
	  softAssert = new SoftAssert();
	  softAssert.assertTrue(page.getCategorySidebarHeader().isDisplayed(), "Category sidebar header is not visible");
	  softAssert.assertAll();
  }
  @Test(priority = 2, description = "Verify that all expected main categories are displayed in the sidebar.")
  public void verifyAllMainCategoriesDisplayed(){
	  softAssert = new SoftAssert();
	  softAssert.assertTrue(page.getAllMainCategories().size() >= 3, "Less than 3 main categories found");
	  List<String> categoryNames = new ArrayList<>();
	  for(WebElement category : page.getAllMainCategories()) {
		  categoryNames.add(category.getText());
	  }
	  softAssert.assertTrue(categoryNames.contains("WOMEN"), "Women category is missing");
      softAssert.assertTrue(categoryNames.contains("MEN"), "Men category is missing");
      softAssert.assertTrue(categoryNames.contains("KIDS"), "Kids category is missing");
	  softAssert.assertAll();
  }
  @Test(priority = 3, description = "Verify that subcategories appear under main category.")
  public void verifySubcategoriesAppear() {
	  softAssert = new SoftAssert();
	  List<String> subCategoryNames = new ArrayList<>();
	  for(WebElement category : page.getAllMainCategories()) { 
		  category.click();
		  List<WebElement> subCategories = page.getSubCategories(category.getText());
	      softAssert.assertTrue(subCategories.size() >= 2, "Less than 3 subcategories found under "+category.getText());
	      for(WebElement subCategory : subCategories) {
	    	  subCategoryNames.add(subCategory.getText());
		  }
	  }
	  softAssert.assertTrue(subCategoryNames.contains("DRESS"), "DRESS subcategory is missing");
      softAssert.assertTrue(subCategoryNames.contains("TOPS"), "TOPS subcategory is missing");
      softAssert.assertTrue(subCategoryNames.contains("SAREE"), "SAREE subcategory is missing");
      softAssert.assertTrue(subCategoryNames.contains("TSHIRTS"), "TSHIRTS subcategory is missing");
      softAssert.assertTrue(subCategoryNames.contains("JEANS"), "JEANS subcategory is missing");
      softAssert.assertTrue(subCategoryNames.contains("TOPS & SHIRTS"), "TOPS & TSHIRTS subcategory is missing");
      softAssert.assertTrue(subCategoryNames.contains("DRESS"), "DRESS subcategory is missing");
	  softAssert.assertAll();
  }
  @Test(priority = 4, description = "Verify navigation to a selected subcategory and content match.")
  public void verifyNavigationAndProductListMatch() {
	  softAssert = new SoftAssert();

	  page.clickMainCategory(WOMEN_CAT);
      page.clickSubCategory(WOMEN_CAT, DRESSES_SUBCAT);
      String title = page.getCategoryTitle();
      softAssert.assertEquals(title, "WOMEN - DRESS PRODUCTS", "Category title is incorrect after selecting Dresses.");
      List<WebElement> products = page.getProductTitle();
      softAssert.assertTrue(products.size() > 0, "No products found in the Women - Dress category.");
      softAssert.assertAll();
  }
  @Test(priority = 5, description = "Verify confirmatory text on category page.")
  public void verifyConfirmatoryText() {
	  softAssert = new SoftAssert();
	  page.clickMainCategory(WOMEN_CAT);
      page.clickSubCategory(WOMEN_CAT, TOPS_SUBCAT);
      String title = page.getCategoryTitle();
      softAssert.assertEquals(title, "WOMEN - TOPS PRODUCTS", "Category title does not match 'Women - Tops Products'.");
      softAssert.assertAll();
  }
  @Test(priority = 6, description = "Verify category selection updates URL.")
  public void verifyCategorySelectionUpdatesURL() {
	  softAssert = new SoftAssert();
      page.clickMainCategory(MEN_CAT);
      page.clickSubCategory(MEN_CAT, TSHIRTS_SUBCAT);
      softAssert.assertTrue(driver.getCurrentUrl().contains("category_products"), "URL did not update to the expected category format");
      softAssert.assertAll();
  }
  
  @Test(priority = 7, description = "Verify that selecting a new category updates products accordingly (cross-category check).")
  public void verifyNewCategoryUpdatesProducts() {
	  softAssert = new SoftAssert();
	  page.clickMainCategory(MEN_CAT);
      page.clickSubCategory(MEN_CAT, TSHIRTS_SUBCAT);
      String firstTitle = page.getCategoryTitle();
      page.clickMainCategory(KIDS_CAT);
      page.clickSubCategory(KIDS_CAT, KIDS_TOPS_SUBCAT);
      String secondTitle = page.getCategoryTitle();
      softAssert.assertEquals(secondTitle, "KIDS - TOPS & SHIRTS PRODUCTS", "Second category title is incorrect.");
      softAssert.assertNotEquals(firstTitle, secondTitle, "Category title did not change after selecting a new category.");
      List<WebElement> products = page.getProductTitle();
      softAssert.assertTrue(products.size() > 0, "No products found after selecting the second category.");
	  softAssert.assertAll();
  }
  @Test(priority = 8, description = "Verify that no duplicate categories or subcategories appear.")
  public void verifyNoDuplicateCategories() {
	  softAssert = new SoftAssert();
	  List<WebElement> mainCategories = page.getAllMainCategories();
      Set<String> categoryNames = new HashSet<>();
      for (WebElement cat : mainCategories) {
          String name = cat.getText().trim();
          softAssert.assertFalse(categoryNames.contains(name), "Duplicate main category found: " + name);
          categoryNames.add(name);
          if (name.equals(WOMEN_CAT)) {
              page.clickMainCategory(WOMEN_CAT);
              List<WebElement> subCategories = page.getSubCategories(WOMEN_CAT);
              Set<String> subCategoryNames = new HashSet<>();
              for (WebElement subCat : subCategories) {
                  String subName = subCat.getText().trim();
                  softAssert.assertFalse(subCategoryNames.contains(subName), "Duplicate subcategory found: " + subName);
                  subCategoryNames.add(subName);
              }
          }
      }
	  softAssert.assertAll();
  }
  
  @AfterTest
  public void closeBrowser() {
	   driver.close();
  }

}
