// Specifies the package this class belongs to.
package seleniumPackage.Automation_Excercise.UserStory_8;

// Imports the Duration class for setting timeouts.
import java.time.Duration;
// Imports the List interface for handling collections of WebElements.
import java.util.List;

// Imports the By class, used for locating elements.
import org.openqa.selenium.By;
// Imports the JavascriptExecutor interface, used for running JavaScript commands.
import org.openqa.selenium.JavascriptExecutor;
// Imports the Keys class, used for simulating keyboard presses.
import org.openqa.selenium.Keys;
// Imports the main WebDriver interface.
import org.openqa.selenium.WebDriver;
// Imports the WebElement interface, representing an HTML element.
import org.openqa.selenium.WebElement;
// Imports the WebDriverWait class, used for explicit waits.
import org.openqa.selenium.support.ui.WebDriverWait;
// Imports the ExpectedConditions class, providing conditions for explicit waits.
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * This is the Page Object Model (POM) class for the application.
 * It encapsulates all WebElements (as locators) and methods to interact with them.
 */
public class Pages {
	// Private field to hold the WebDriver instance (the browser).
	private WebDriver driver;
	// Private field to hold the explicit wait instance.
	private WebDriverWait wait;
	// The base URL of the website under test.
	private final String baseUrl = "http://automationexercise.com";
	
	// --- Test Data Section ---
	// Defines constant variables for test data, like credentials and expected values.
	
	// Hardcoded email for login.
	private final String email = "mahmoudehab30@gmail.com";
	// Hardcoded password for login.
	private final String password = "@DSkUVsXqFz6g2";
	// Expected name of the first product.
	public final String firstProductName = "Blue Top";
	// Expected category of the first product.
	public final String firstProductCategory = "Women > Tops";
	// Expected price of the first product.
	public final String firstProductPrice = "Rs. 500";
	// Expected availability of the first product.
	public final String firstProductAvaliability = "In Stock";
	// Expected condition of the first product.
	public final String firstProductCondition = "New";
	// Expected brand of the first product.
	public final String firstProductBrand = "Polo";
	// Test data for an exact product name search.
	public final String exactSearchKeyword = "Sleeves Top and Short - Blue & Pink";
	// Test data for a partial product name search.
	public final String partialSearchKeyword = "Blue";
	// Test data for a case-insensitive search.
	public final String mixedCaseSearchKeyword = "sLeeVes Top aNd sHort - BluE & piNk";
	// Test data for a product that does not exist.
	public final String nonExistantProductKeyword = "sleeves top and short - red & black";
	// Test data for a search containing special characters.
	public final String specialCharSearchKeyword = "Top - White";
	// Test data for a search with leading/trailing spaces.
	public final String searchWithSpacesKeyword = "       White ";
	// Expected maximum stock number (used for assertions).
	public final int	maxNumberOfItemsInStock = 34;
	
	// --- Locators Section ---
	// Defines all element locators using the By class.
	
	// Locator for the 'Products' button in the header navigation.
	private By productNavigationButton = By.xpath("//*[@id=\"header\"]/div/div/div/div[2]/div/ul/li[2]/a");
	// Locator for the email input field on the login page.
	private By emailField = By.name("email");
	// Locator for the password input field on the login page.
	private By passwordField = By.name("password");
	// Locator for the 'Signup / Login' button in the header navigation.
	private By loginNavigationButton = By.xpath("//*[@id=\"header\"]/div/div/div/div[2]/div/ul/li[4]/a");
	// Locator for the 'Login' button on the login form.
	private By loginButton = By.xpath("//*[@id=\"form\"]/div/div/div[1]/div/form/button");
	// Locator for the grid container of product items.
	private By productsItemsGrid = By.className("features_items");
	// Locator for the product search input field.
	private By searchField = By.id("search_product");
	// Locator for the product search submit button.
	private By searchButton = By.id("submit_search");
	// Locator for the 'View Product' button of the first product (Absolute XPath).
	private By viewFirstProductButton = By.xpath("/html/body/section[2]/div/div/div[2]/div/div[2]/div/div[2]/ul/li/a");
	// Locator for the product name on the product detail page (Absolute XPath).
	private By firstProductNameLocator = By.xpath("/html/body/section/div/div/div[2]/div[2]/div[2]/div/h2");
	// Locator for the product category on the product detail page (Absolute XPath).
	private By firstProductCategoryLocator = By.xpath("/html/body/section/div/div/div[2]/div[2]/div[2]/div/p[1]");
	// Locator for the product availability on the product detail page (Absolute XPath).
	private By firstProductAvaliabilityLocator = By.xpath("/html/body/section/div/div/div[2]/div[2]/div[2]/div/p[2]");
	// Locator for the product price on the product detail page (Absolute XPath).
	private By firstProductPriceLocator = By.xpath("/html/body/section/div/div/div[2]/div[2]/div[2]/div/span/span");
	// Locator for the product condition on the product detail page (Absolute XPath).
	private By firstProductConditionLocator = By.xpath("/html/body/section/div/div/div[2]/div[2]/div[2]/div/p[3]");
	// Locator for the product brand on the product detail page (Absolute XPath).
	private By firstProductBrandLocator = By.xpath("/html/body/section/div/div/div[2]/div[2]/div[2]/div/p[4]");
	// Locator for all product names displayed in the product grid.
	private By productNamesLocator = By.xpath("//div[@class='productinfo text-center']/p");
	// Locator for the 'All Products' header on the products page (Absolute XPath).
	private By allProductsHeaderLocator = By.xpath("/html/body/section[2]/div/div/div[2]/div/h2");
	// Locator for the category sidebar container (accordion).
	private By categorySidebarHeader = By.id("accordian");											
	// Locator for all main category links in the sidebar.
	private By mainCategoriesLocator =By.xpath("//div[@class='panel-heading']/h4/a");
	// Locator for the title header on a category page (e.g., "Women - Tops Products") (Absolute XPath).
	private By categoryTitleHeaderLocator = By.xpath("/html/body/section/div/div[2]/div[2]/div/h2");
	// Locator for the 'Brands' sidebar header.
	private By brandSidebarHeader = By.className("brands-name");
	// Locator for all brand links in the sidebar.
	private By allBrandsLinksLocator = By.xpath("//*[@class=\"brands-name\"]/ul/li/a");
	// Locator for the 'Write Your Review' header on the product detail page (Absolute XPath).
	private By writeYourReviewHeader = By.xpath("/html/body/section/div/div/div[2]/div[3]/div[1]/ul/li/a");
	// Locator for the 'Name' input field in the review form.
	private By reviewNameField = By.id("name");
	// Locator for the 'Email Address' input field in the review form.
	private By reviewEmailField = By.id("email");
	// Locator for the 'Add Review Here' text area in the review form.
	private By reviewTextArea = By.id("review");
	// Locator for the 'Submit' button in the review form.
	private By reviewSubmitButton = By.id("button-review");
	// Locator for the success message after submitting a review.
	private By reviewSuccessMessage = By.xpath("//*[@id=\"review-section\"]/div/div/span");
	
	// Locator for the 'Subscription' header in the footer.
	private By subscriptionHeaderLocator =By.xpath("//footer[@id='footer']//h2[text()='Subscription']");
	// Locator for the email input field in the subscription form.
	private By subscriptionEmailField = By.id("susbscribe_email");
	// Locator for the 'Subscribe' button (arrow) in the subscription form.
	private By subscribeButton = By.id("subscribe");
	// Locator for the success message after submitting a subscription.
	private By subscriptionSuccessMessage = By.xpath("//*[@id=\"success-subscribe\"]/div");
	
	
	
	/**
	 * Constructor for the Pages class.
	 * @param driver The WebDriver instance passed from the test class.
	 */
	public Pages(WebDriver driver) {
		// Assigns the WebDriver instance from the test to the class's private driver field.
		this.driver = driver;
		// Initializes the WebDriverWait with the driver and a 10-second timeout.
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	// --- Utility Methods ---
	
	/**
	 * A private helper method to find an element with an explicit wait.
	 * It waits until the element is visible before returning it.
	 * @param locator The By locator for the element.
	 * @return The WebElement once it is visible.
	 */
	private WebElement get(By locator) {
		// Uses the 'wait' object to wait for the element to be visible, then returns it.
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	// --- Home Page Methods ---
	
	/**
	 * Navigates the browser to the homepage (baseUrl).
	 * Waits for the login button to be visible to ensure the page is loaded.
	 */
	public void navigateToHomePage() {
		// Tells the driver to open the specified URL.
		driver.get(baseUrl);
		// Waits for the login button to be visible, confirming page load.
		wait.until(ExpectedConditions.visibilityOfElementLocated(loginNavigationButton));
	}

	// --- Login Page Methods ---
	
	/**
	 * Clicks the 'Signup / Login' button in the header.
	 */
	public void clickLoginNavigationButton() {
		// Finds the login button (using 'get' method) and clicks it.
		get(loginNavigationButton).click();
	}

	/**
	 * Performs the sign-in action using predefined credentials.
	 */
	public void signin() {
		// Finds the email field and types in the email.
		get(emailField).sendKeys(email);
		// Finds the password field and types in the password.
		get(passwordField).sendKeys(password);
		// Finds the login button and clicks it.
		get(loginButton).click();
	}

	// --- Products Page Methods ---
	
	/**
	 * Gets the 'Products' navigation button WebElement.
	 * @return The 'Products' button WebElement.
	 */
	public WebElement getProductNavigationButton() {
		// Finds and returns the 'Products' button element.
		return get(productNavigationButton);
	}

	/**
	 * Clicks the 'Products' navigation button in the header.
	 * Waits for the 'All Products' header to be visible to ensure page load.
	 */
	public void clickProductNavigationButton() {
		// Finds and clicks the 'Products' button.
		get(productNavigationButton).click();
		// Waits for the products page header to be visible.
		wait.until(ExpectedConditions.visibilityOfElementLocated(allProductsHeaderLocator));
	}

	/**
	 * Gets the main grid container for product items.
	 * @return The 'features_items' grid WebElement.
	 */
	public WebElement getProductsItemsGrid() {
		// Finds and returns the product grid element.
		return get(productsItemsGrid);
	}

	/**
	 * Gets the search input field WebElement.
	 * @return The search field WebElement.
	 */
	public WebElement getSearchField() {
		// Finds and returns the search field element.
		return get(searchField);
	}

	/**
	 * Gets the search submit button WebElement.
	 * @return The search button WebElement.
	 */
	public WebElement getSearchButton() {
		// Finds and returns the search button element.
		return get(searchButton);
	}

	/**
	 * Performs a search by typing text and pressing the Enter key.
	 * @param searchText The text to search for.
	 */
	public void searchByEnter(String searchText) {
		// Finds the search field, types the text, and appends the Enter key press.
		getSearchField().sendKeys(searchText + Keys.ENTER);
	}

	/**
	 * Performs a search by typing text and clicking the search button.
	 * @param searchText The text to search for.
	 */
	public void searchByButton(String searchText) {
		// Finds the search field and types the text.
		getSearchField().sendKeys(searchText);
		// Finds the search button and clicks it.
		getSearchButton().click();
	}
	
	/**
	 * Clears any text from the search input field.
	 */
	public void searchFieldClear() {
		// Finds the search field and calls the clear() method.
		getSearchField().clear();
	}
	
	// --- Product Detail Page Methods ---
	
	/**
	 * Clicks the 'View Product' button for the first product.
	 * Waits for the product name to be visible on the detail page.
	 */
	public void clickViewFirstProductButton() {
		// Finds and clicks the 'View Product' button.
		get(viewFirstProductButton).click();
		// Waits for the product detail page to load by checking for the product name.
		wait.until(ExpectedConditions.visibilityOfElementLocated(firstProductNameLocator));
	}
	
	/**
	 * Gets the text of the product's name from the detail page.
	 * @return The product name as a String.
	 */
	public String getFirstProductName() {
		// Finds the name element and returns its text.
		return get(firstProductNameLocator).getText();
	}
	
	/**
	 * Gets the text of the product's category and cleans it.
	 * @return The cleaned category name as a String.
	 */
	public String getFirstProductCategory() {
		// Gets the raw text (e.g., "Category: Women > Tops").
		String category = get(firstProductCategoryLocator).getText();
		// Removes the "Category:" prefix and any extra whitespace.
		return category.replace("Category:", "").trim();
	}
	
	/**
	 * Gets the text of the product's price from the detail page.
	 * @return The product price as a String.
	 */
	public String getFirstProductPrice() {
		// Finds the price element and returns its text.
		return get(firstProductPriceLocator).getText();
	}
	
	/**
	 * Gets the text of the product's availability and cleans it.
	 * @return The cleaned availability status as a String.
	 */
	public String getFirstProductAvaliability() {
		// Gets the raw text (e.g., "Availability: In Stock").
		String avaliability = get(firstProductAvaliabilityLocator).getText();
		// Removes the "Availability:" prefix and any extra whitespace.
		return avaliability.replace("Availability:", "").trim();
	}
	
	/**
	 * Gets the text of the product's condition and cleans it.
	 * @return The cleaned condition as a String.
	 */
	public String getFirstProductCondition() {
		// Gets the raw text (e.g., "Condition: New").
		String condition = get(firstProductConditionLocator).getText();
		// Removes the "Condition:" prefix and any extra whitespace.
		return condition.replace("Condition:", "").trim();
	}
	
	/**
	 * Gets the text of the product's brand and cleans it.
	 * @return The cleaned brand name as a String.
	 */
	public String getFirstProductBrand() {
		// Gets the raw text (e.g., "Brand: Polo").
		String brand = get(firstProductBrandLocator).getText();
		// Removes the "Brand:" prefix and any extra whitespace.
		return brand.replace("Brand:", "").trim();
	}
	
	// --- Product Listing Methods ---
	
	/**
	 * Gets a list of all product title WebElements visible on the page.
	 * @return A List of WebElements.
	 */
	public List<WebElement> getProductTitle() {
		// returns the list of elements.
		return driver.findElements(productNamesLocator);
	}
	
	/**
	 * Checks if the "No product found" message is visible (by checking for absence of products).
	 * @return true if no products are found, false otherwise.
	 */
	public boolean isNoProductFoundMessageVisible() {
		// Checks if the list of product names is empty.
		if(driver.findElements(productNamesLocator).isEmpty()) {
			// Returns true if the list is empty (assuming this means no products were found).
			return true; 
		}else return false; // Returns false if any products were found.
	}
	
	/**
	 * Gets the text of the 'All Products' header.
	 * @return The header text as a String.
	 */
	public String getAllProductsHeader() {
		// Finds the header element and returns its text.
		return get(allProductsHeaderLocator).getText();
	}
	
	// --- Category Sidebar Methods ---
	
	/**
	 * Gets the category sidebar header/container WebElement.
	 * @return The category sidebar WebElement.
	 */
	public WebElement getCategorySidebarHeader() {
		// Finds and returns the category sidebar element.
		return get(categorySidebarHeader);
	}
	
	/**
	 * Gets a list of all main category WebElements in the sidebar.
	 * @return A List of WebElements.
	 */
	public List<WebElement> getAllMainCategories() {
		// Waits for all main category elements to be visible, then returns the list.
	    return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(mainCategoriesLocator));
	}
	
	/**
	 * Gets a list of subcategory WebElements for a given main category.
	 * @param categoryName The name of the main category (e.g., "Women").
	 * @return A List of subcategory WebElements.
	 */
	public List<WebElement> getSubCategories(String categoryName) {
		// Dynamically builds an XPath locator based on the categoryName.
		By subCatListLocator = By.xpath("//*[@id="+"\"" +categoryName.charAt(0)+categoryName.substring(1).toLowerCase()+ "\""+ "]/div/ul/li/a");
		// Waits for all subcategory elements to be visible, then returns the list.
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(subCatListLocator));
    }
	
	/**
	 * Clicks a main category link by its text.
	 * @param categoryName The text of the category to click.
	 */
	public void clickMainCategory(String categoryName) {
		// Iterates through all main category WebElements.
        for(WebElement category: getAllMainCategories()) {
        	// Compares the element's text (lowercase, trimmed) to the target name.
        	if(category.getText().toLowerCase().trim().equals(categoryName.toLowerCase())) {
        		// Clicks the matching element.
        		category.click();
        		// Exits the method after clicking.
        		return;
        	}
        }
     }
	
	/**
	 * Clicks a main category to expand it, then clicks a specific subcategory.
	 * @param categoryName The text of the main category to click.
	 * @param subCategoryName The text of the subcategory to click.
	 */
	public void clickSubCategory(String categoryName, String subCategoryName) {
		// Iterates through all main category WebElements.
		for(WebElement category: getAllMainCategories()) {
			// Finds the matching main category.
        	if(category.getText().toLowerCase().trim().equals(categoryName.toLowerCase())) {
        		// Clicks the main category to expand it.
        		category.click();
        		// Iterates through the subcategories of the clicked main category.
        		for(WebElement subCategory: getSubCategories(categoryName)) {
					// Finds the matching subcategory.
                	if(subCategory.getText().toLowerCase().trim().equals(subCategoryName.toLowerCase())) {
                		// Clicks the subcategory.
                		subCategory.click();
                		// Waits for the category title to appear, confirming navigation.
                		wait.until(ExpectedConditions.visibilityOfElementLocated(categoryTitleHeaderLocator));
                		// Exits the method.
                		return;
                	}
                }
        	}
        }
	}
	
	/**
	 * Gets the text of the title header on the category/brand page.
	 * @return The title text as a String.
	 */
	public String getCategoryTitle() {
		// Finds the title element and returns its text.
		return get(categoryTitleHeaderLocator).getText();
	}
	
	// --- Brand Sidebar Methods ---
	
	/**
	 * Gets the 'Brands' sidebar header WebElement.
	 * @return The 'Brands' header WebElement.
	 */
	public WebElement getBrandSidebarHeader() {
		// Finds and returns the brand sidebar header element.
		return get(brandSidebarHeader);
	}
	
	/**
	 * Gets a list of all brand link WebElements in the sidebar.
	 * @return A List of WebElements.
	 */
	public List<WebElement> getAllBrandLinks() {
		// Waits for all brand link elements to be visible, then returns the list.
	    return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(allBrandsLinksLocator));
	}
	
	/**
	 * Clicks a brand link by its text.
	 * @param brandName The text of the brand to click.
	 */
	public void clickBrandLink(String brandName) {
		// Iterates through all brand link WebElements.
		for (WebElement brand : getAllBrandLinks()) {
			// Checks if the element's text (lowercase, trimmed) contains the target name.
			if(brand.getText().toLowerCase().trim().contains(brandName.toLowerCase().trim())) {
				// Clicks the matching brand link.
				brand.click();	
				// Waits for the page title to load, confirming navigation.
				wait.until(ExpectedConditions.visibilityOfElementLocated(categoryTitleHeaderLocator));
				// Exits the method.
	            return;
			}
		}
		// If the loop finishes without finding the brand, throw an error.
		throw new RuntimeException("Could not find brand link with name: " + brandName);
	}
	
	/**
	 * Gets the title of the brand page.
	 * (Reuses the getCategoryTitle method as they share the same locator).
	 * @return The brand page title text as a String.
	 */
	public String getBrandPageTitle() {
		// Calls the existing method to get the page title.
	    return getCategoryTitle();
	}
	
	// --- Review Section Methods ---
	
	/**
	 * Gets the 'Write Your Review' header WebElement.
	 * @return The 'Write Your Review' header WebElement.
	 */
	public WebElement getWriteYourReviewHeader() {
		// Finds and returns the review header element.
	    return get(writeYourReviewHeader);
	}

	/**
	 * Gets the 'Name' input field WebElement in the review form.
	 * @return The 'Name' field WebElement.
	 */
	public WebElement getReviewNameField() {
		// Finds and returns the review name field element.
	    return get(reviewNameField);
	}

	/**
	 * Gets the 'Email Address' input field WebElement in the review form.
	 * @return The 'Email' field WebElement.
	 */
	public WebElement getReviewEmailField() {
		// Finds and returns the review email field element.
	    return get(reviewEmailField);
	}

	/**
	 * Gets the 'Add Review Here' text area WebElement in the review form.
	 * @return The review text area WebElement.
	 */
	public WebElement getReviewTextArea() {
		// Finds and returns the review text area element.
	    return get(reviewTextArea);
	}

	/**
	 * Gets the 'Submit' button WebElement in the review form.
	 * @return The review 'Submit' button WebElement.
	 */
	public WebElement getReviewSubmitButton() {
		// Finds and returns the review submit button element.
	    return get(reviewSubmitButton);
	}
	
	/**
	 * Fills out the review form and clicks the submit button.
	 * @param name The name to enter in the form.
	 * @param email The email to enter in the form.
	 * @param review The review text to enter in the form.
	 */
	public void submitReview(String name, String email, String review) {
		// Finds the name field and types in the name.
	    getReviewNameField().sendKeys(name);
	    // Finds the email field and types in the email.
	    getReviewEmailField().sendKeys(email);
	    // Finds the text area and types in the review.
	    getReviewTextArea().sendKeys(review);
	    // Finds the submit button and clicks it.
	    getReviewSubmitButton().click();
	}
	
	/**
	 * Gets the success message WebElement after submitting a review.
	 * Waits for the message to be visible.
	 * @return The success message WebElement.
	 */
	public WebElement getReviewSuccessMessage() {
		// Waits for the success message to be visible, then returns it.
	    return wait.until(ExpectedConditions.visibilityOfElementLocated(reviewSuccessMessage));
	}
	
	/**
	 * Checks if the review success message is displayed (for negative tests).
	 * Uses a short wait to avoid slowing down tests that expect failure.
	 * @return true if the message is visible, false otherwise.
	 */
	public boolean isSuccessMessageDisplayed() {
	    try {
	    	// Creates a new wait object with a short 2-second timeout.
	        WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(2));
	        // Tries to wait for the message.
	        shortWait.until(ExpectedConditions.visibilityOfElementLocated(reviewSuccessMessage));
	        // If found, return true.
	        return true;
	    } catch (Exception e) {
	    	// If the wait times out (throws an exception), return false.
	        return false;
	    }
	}
	
	/**
	 * Checks if all fields in the review form are empty.
	 * @return true if all fields are empty, false otherwise.
	 */
	public boolean isReviewFormCleared() {
		// Checks if the 'value' attribute of the name field is empty.
	    boolean isNameEmpty = getReviewNameField().getAttribute("value").isEmpty();
	    // Checks if the 'value' attribute of the email field is empty.
	    boolean isEmailEmpty = getReviewEmailField().getAttribute("value").isEmpty();
	    // Checks if the 'value' attribute of the text area is empty.
	    boolean isReviewEmpty = getReviewTextArea().getAttribute("value").isEmpty();
	    // Returns true only if all three fields are empty.
	    return isNameEmpty && isEmailEmpty && isReviewEmpty;
	}
	
	/**
	 * Checks if the "Customer Reviews" section is visible on the page.
	 * @return true if the section is found, false otherwise.
	 */
	public boolean isCustomerReviewsSectionVisible() {
		// Defines a locator for the customer reviews section.
	    By customerReviewsLocator = By.id("customer-reviews-section"); 
	    // Returns true if the list of found elements is NOT empty (i.e., element exists).
	    return !driver.findElements(customerReviewsLocator).isEmpty();
	}
	
	/**
	 * Waits explicitly for the review form's 'Name' field to be cleared.
	 * @return true if the field is cleared within the timeout.
	 */
	public boolean waitForReviewFormToClear() {
		// Waits for the 'value' attribute of the name field to become an empty string "".
	    return   wait.until(ExpectedConditions.attributeToBe(reviewNameField, "value", ""));
	}
	
	/**
	 * Clears all text from the review form fields.
	 */
	public void clearReviewFormFields() {
		// Finds the name field and clears it.
		getReviewNameField().clear();
		// Finds the email field and clears it.
	    getReviewEmailField().clear();
	    // Finds the text area and clears it.
	    getReviewTextArea().clear();
	}
	
	// --- Footer Subscription Methods ---
	
	/**
	 * Gets the 'Subscription' header WebElement in the footer.
	 * @return The 'Subscription' header WebElement.
	 */
	public WebElement getSubscriptionHeader() {
		// Finds and returns the subscription header element.
	    return get(subscriptionHeaderLocator);
	}

	/**
	 * Gets the email input field WebElement in the subscription form.
	 * @return The subscription email field WebElement.
	 */
	public WebElement getSubscriptionEmailField() {
		// Finds and returns the subscription email field element.
	    return get(subscriptionEmailField);
	}

	/**
	 * Gets the 'Subscribe' button WebElement in the subscription form.
	 * @return The 'Subscribe' button WebElement.
	 */
	public WebElement getSubscribeButton() {
		// Finds and returns the subscribe button element.
	    return get(subscribeButton);
	}
	
	/**
	 * Submits an email to the subscription form.
	 * @param email The email to subscribe with.
	 */
	public void submitSubscription(String email) {
		// Clears any existing text from the email field.
	    getSubscriptionEmailField().clear();
	    // Types the new email into the field.
	    getSubscriptionEmailField().sendKeys(email);
	    // Clicks the subscribe button.
	    getSubscribeButton().click();
	}
	
	/**
	 * Gets the success message WebElement after submitting a subscription.
	 * Waits for the message to be visible.
	 * @return The subscription success message WebElement.
	 */
	public WebElement getSubscriptionSuccessMessage() {
		// Waits for the success message to be visible, then returns it.
	    return wait.until(ExpectedConditions.visibilityOfElementLocated(subscriptionSuccessMessage));
	}
	
	/**
	 * Checks if the subscription success message is displayed (for negative tests).
	 * Uses a short wait.
	 * @return true if the message is visible, false otherwise.
	 */
	public boolean isSubscriptionSuccessMessageVisible() {
	    try {
	    	// Creates a new wait object with a short 2-second timeout.
	        WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(2));
	        // Tries to wait for the message.
	        shortWait.until(ExpectedConditions.visibilityOfElementLocated(subscriptionSuccessMessage));
	        // If found, return true.
	        return true;
	    } catch (Exception e) {
	    	// If the wait times out (throws an exception), return false.
	        return false;
	    }
	}
	
	/**
	 * Waits explicitly for the subscription email field to be cleared.
	 * @return true if the field is cleared within the timeout.
	 */
	public boolean waitForSubscriptionEmailToClear() {
	  
		// Waits for the 'value' attribute of the email field to become an empty string "".
		return wait.until(ExpectedConditions.attributeToBe(subscriptionEmailField, "value", ""));
	}
	
	
} // End of the Pages class