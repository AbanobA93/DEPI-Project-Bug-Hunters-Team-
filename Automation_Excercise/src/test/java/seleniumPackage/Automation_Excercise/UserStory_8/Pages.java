package seleniumPackage.Automation_Excercise.UserStory_8;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Pages {
	private WebDriver driver;
	private WebDriverWait wait;
	private final String baseUrl = "http://automationexercise.com";
	// Data
	private final String email = "mahmoudehab30@gmail.com";
	private final String password = "@DSkUVsXqFz6g2";
	public final String firstProductName = "Blue Top";
	public final String firstProductCategory = "Women > Tops";
	public final String firstProductPrice = "Rs. 500";
	public final String firstProductAvaliability = "In Stock";
	public final String firstProductCondition = "New";
	public final String firstProductBrand = "Polo";
	// Locators
	private By productNavigationButton = By.xpath("//*[@id=\"header\"]/div/div/div/div[2]/div/ul/li[2]/a");
	private By emailField = By.name("email");
	private By passwordField = By.name("password");
	private By loginNavigationButton = By.xpath("//*[@id=\"header\"]/div/div/div/div[2]/div/ul/li[4]/a");
	private By loginButton = By.xpath("//*[@id=\"form\"]/div/div/div[1]/div/form/button");
	private By productsItemsGrid = By.className("features_items");
	private By searchField = By.id("search_product");
	private By searchButton = By.id("submit_search");
	private By viewFirstProductButton = By.xpath("/html/body/section[2]/div/div/div[2]/div/div[2]/div/div[2]/ul/li/a");
	private By firstProductNameLocator = By.xpath("/html/body/section/div/div/div[2]/div[2]/div[2]/div/h2");
	private By firstProductCategoryLocator = By.xpath("/html/body/section/div/div/div[2]/div[2]/div[2]/div/p[1]");
	private By firstProductAvaliabilityLocator = By.xpath("/html/body/section/div/div/div[2]/div[2]/div[2]/div/p[2]");
	private By firstProductPriceLocator = By.xpath("/html/body/section/div/div/div[2]/div[2]/div[2]/div/span/span");
	private By firstProductConditionLocator = By.xpath("/html/body/section/div/div/div[2]/div[2]/div[2]/div/p[3]");
	private By firstProductBrandLocator = By.xpath("/html/body/section/div/div/div[2]/div[2]/div[2]/div/p[4]");
	
	
	public Pages(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	// utils
	private WebElement get(By locator) {
		return driver.findElement(locator);
	}

	// Home Page
	public void navigateToHomePage() {
		driver.get(baseUrl);
		
	}

	// Login Page

	public void clickLoginNavigationButton() {
		get(loginNavigationButton).click();
	}

	public void signin() {
		get(emailField).sendKeys(email);
		get(passwordField).sendKeys(password);
		get(loginButton).click();
	}

	// Products Page
	public WebElement getProductNavigationButton() {
		return get(productNavigationButton);
	}

	public void clickProductNavigationButton() {
		get(productNavigationButton).click();
	}

	public WebElement getProductsItemsGrid() {
		return get(productsItemsGrid);
	}

	public WebElement getSearchField() {
		return get(searchField);
	}

	public WebElement getSearchButton() {
		return get(searchButton);
	}

	public void searchByEnter(String searchText) {
		getSearchField().sendKeys(searchText + Keys.ENTER);
	}

	public void searchByButton(String searchText) {
		getSearchField().sendKeys(searchText);
		getSearchButton().click();
	}
	
	public void clickViewFirstProductButton() {
		get(viewFirstProductButton).click();
		
	}
	public String getFirstProductName() {
		return get(firstProductNameLocator).getText();
	}
	
	public String getFirstProductCategory() {
		String category = get(firstProductCategoryLocator).getText();
		return category.replace("Category:", "").trim();

	}
	
	public String getFirstProductPrice() {
		return get(firstProductPriceLocator).getText();
	}
	
	public String getFirstProductAvaliability() {
		String avaliability = get(firstProductAvaliabilityLocator).getText();
		return avaliability.replace("Availability:", "").trim();
		
	}
	
	public String getFirstProductCondition() {
		String condition = get(firstProductConditionLocator).getText();
		return condition.replace("Condition:", "").trim();
		
	}
	
	public String getFirstProductBrand() {
		String brand = get(firstProductBrandLocator).getText();
		return brand.replace("Brand:", "").trim();
		
	}
}
