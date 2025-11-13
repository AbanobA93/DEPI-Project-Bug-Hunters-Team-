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

public class UserStory11 {
	WebDriver driver = new ChromeDriver();
	Pages page = new Pages(driver);
	SoftAssert softAssert = new SoftAssert();
	private final String POLO = "Polo";
	private final String H_and_M = "H&M";
	private final String MADAME = "Madame";
	private final String MAST_and_HARBOUR = "Mast & Harbour";
	private final String BABYHUG = "Babyhug";
	private final String ALLEN_SOLLY_JUNIOR = "Allen Solly Junior";
	private final String KOOKIE_KIDS = "Kookie Kids";
	private final String BIBA = "Biba";
	
	@BeforeTest
	public void initialize() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		page.navigateToHomePage();
		page.clickLoginNavigationButton();
		page.signin();
		page.clickProductNavigationButton();
	}

	@Test(priority = 1, description = "Verify that the brand sidebar is visible")
    public void verifyBrandSidebarVisibility() {
		softAssert = new SoftAssert();
		softAssert.assertTrue(page.getBrandSidebarHeader().isDisplayed(), "Brand sidebar header is not visible");
		softAssert.assertAll();
	}

	@Test(priority = 2, description = "Verify that all available brands are listed")
    public void verifyAllBrandsAreListed() {
		softAssert = new SoftAssert();
		softAssert.assertTrue(page.getAllBrandLinks().size() >= 0, "No brand links were found");
		List<String> brandNames = new ArrayList<>();
		for (WebElement brand : page.getAllBrandLinks()) {
			brandNames.add(brand.getText());
		}
		softAssert.assertTrue(brandNames.stream().anyMatch(name -> name.contains("POLO")), "Polo brand not found");
		softAssert.assertTrue(brandNames.stream().anyMatch(name -> name.contains("H&M")), "H&M brand not found");
		softAssert.assertTrue(brandNames.stream().anyMatch(name -> name.contains("MADAME")), "MADAME brand not found");
		softAssert.assertTrue(brandNames.stream().anyMatch(name -> name.contains("MAST & HARBOUR")), "MAST & HARBOUR brand not found");
		softAssert.assertTrue(brandNames.stream().anyMatch(name -> name.contains("BABYHUG")), "BABYHUG brand not found");
		softAssert.assertTrue(brandNames.stream().anyMatch(name -> name.contains("ALLEN SOLLY JUNIOR")), "ALLEN SOLLY JUNIOUR brand not found");
		softAssert.assertTrue(brandNames.stream().anyMatch(name -> name.contains("KOOKIE KIDS")), "KOOKIE KIDS brand not found");
		softAssert.assertTrue(brandNames.stream().anyMatch(name -> name.contains("BIBA")), "BIBA brand not found");
		softAssert.assertAll();
	}

	@Test(priority = 3, description = "Verify navigation to a specific brand page")
    public void verifyNavigationToBrandPage() {
		softAssert = new SoftAssert();
		page.clickBrandLink(POLO);
		softAssert.assertTrue(driver.getCurrentUrl().contains("brand_products/"+ POLO), "URL did not navigate to Polo brand page");
		softAssert.assertAll();
	}

	@Test(priority = 4, description = "Verify confirmatory text or heading on brand page")
    public void verifyConfirmatoryTextOnBrandPage() {
		softAssert = new SoftAssert();
		page.clickBrandLink(H_and_M);
		softAssert.assertTrue(driver.getCurrentUrl().contains("brand_products/"+ H_and_M), "URL did not navigate to Polo brand page");
        softAssert.assertEquals(page.getBrandPageTitle(), "BRAND - "+H_and_M.toUpperCase()+" PRODUCTS", "Brand page title is incorrect for "+ H_and_M);
		softAssert.assertAll();
	}

	@Test(priority = 5, description = "Verify that only brand-specific products are displayed")
    public void verifyOnlyBrandSpecificProductsDisplayed() {
		softAssert = new SoftAssert();
		page.clickBrandLink(BIBA);
		softAssert.assertEquals(page.getBrandPageTitle(), "BRAND - "+BIBA.toUpperCase()+" PRODUCTS", "Brand page title is incorrect for "+ BIBA);
        softAssert.assertTrue(page.getProductTitle().size() > 0, "No products are displayed for Biba brand.");
		softAssert.assertAll();
	}

	@Test(priority = 6, description = "Verify that selecting a new brand updates the product list")
    public void verifySelectingNewBrandUpdatesList() {
		softAssert = new SoftAssert();
		page.clickBrandLink(BIBA);
        String firstTitle = page.getBrandPageTitle();
        page.clickBrandLink(H_and_M);
        String secondTitle = page.getBrandPageTitle();
        softAssert.assertNotEquals(firstTitle, secondTitle, "Brand page title did not change after clicking new brand.");
        softAssert.assertEquals(secondTitle, "BRAND - "+H_and_M.toUpperCase()+" PRODUCTS", "Page did not update to "+H_and_M+" products.");
		softAssert.assertAll();
	}

	@Test(priority = 7, description = "Verify URL updates according to selected brand")
    public void verifyURLUpdatesForBrand() {
		softAssert = new SoftAssert();
		page.clickBrandLink(H_and_M);
        String expectedUrl = "https://automationexercise.com/brand_products/"+H_and_M;
        softAssert.assertEquals(driver.getCurrentUrl(), expectedUrl, "URL did not update correctly for "+H_and_M+" brand");
        softAssert.assertAll();
	}

	@Test(priority = 8, description = "Verify sidebar and brand list remain accessible")
    public void verifySidebarRemainsAccessible() {
		softAssert = new SoftAssert();
		page.clickBrandLink(BIBA);
        softAssert.assertTrue(page.getBrandSidebarHeader().isDisplayed(), "Brands sidebar header is not visible after navigation");
		softAssert.assertAll();
	}
	
	@Test(priority = 9, description = "Verify brand selection with long brand names")
    public void verifyNavigationWithLongBrandName() {
		softAssert = new SoftAssert();
		page.clickBrandLink(ALLEN_SOLLY_JUNIOR);
        softAssert.assertEquals(page.getBrandPageTitle(), "BRAND - "+ALLEN_SOLLY_JUNIOR.toUpperCase()+" PRODUCTS", "Title is incorrect for "+ALLEN_SOLLY_JUNIOR);
        softAssert.assertAll();
	}
	

	@AfterTest
	public void closeBrowser() {
		driver.close();
	}

}
