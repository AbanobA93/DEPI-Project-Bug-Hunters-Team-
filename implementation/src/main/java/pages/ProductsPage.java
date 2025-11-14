package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.List;

public class ProductsPage extends BasePage {

    // Locators

    By searchBox = By.id("search_product");
    By searchBtn = By.id("submit_search");
    By searchedLabel = By.xpath("//*[text()='Searched Products']");
    By addToCartBtns = By.cssSelector(".add-to-cart");
    By closeModalBtn = By.cssSelector(".btn.btn-success.close-modal");
    By firstProductView = By.cssSelector("a[href^='/product_details/']");
    By reviewTab = By.cssSelector("li.active a[href='#reviews']");
    By nameInput = By.id("name");
    By emailInput = By.id("email");
    By reviewInput = By.id("review");
    By submitBtn = By.id("button-review");
    By successMsg = By.cssSelector(".alert-success");
    By firstProduct = By.cssSelector("a[data-product-id='1']");
    By viewCart = By.linkText("View Cart");
    By successModalContainer = By.cssSelector(".modal-content");

    // Default data
    private String defaultProduct = "dress";
    private int defaultAddToCartCount = 2;

    public ProductsPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    // Getters for default data
    public String getDefaultProduct() {
        return defaultProduct;
    }

    public int getDefaultAddToCartCount() {
        return defaultAddToCartCount;
    }

    // Methods
    public void searchProduct() {
        waitForPageLoad();
        driver.findElement(searchBox).sendKeys(getDefaultProduct());
        driver.findElement(searchBtn).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchedLabel));
    }

    public void addProductsToCart() {
        List<WebElement> addButtons = driver.findElements(addToCartBtns);

        for (int i = 0; i < getDefaultAddToCartCount() && i < addButtons.size(); i++) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addButtons.get(i));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".modal-content")));
            WebElement closeModal = wait.until(ExpectedConditions.elementToBeClickable(closeModalBtn));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", closeModal);
        }
    }

    public void clickFirstProduct() {
        WebElement viewBtn = wait.until(ExpectedConditions.elementToBeClickable(firstProductView));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", viewBtn);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", viewBtn);
    }

    public void waitForPageLoad() {
        new WebDriverWait(driver, Duration.ofSeconds(30))
                .until(webDriver -> ((JavascriptExecutor) webDriver)
                        .executeScript("return document.readyState").equals("complete"));
    }
    public void writeReview(String name, String email, String review) {
        WebElement reviewTabElement = (WebElement)this.wait.until(ExpectedConditions.elementToBeClickable(this.reviewTab));
        ((JavascriptExecutor)this.driver).executeScript("arguments[0].click();", new Object[]{reviewTabElement});
        ((WebElement)this.wait.until(ExpectedConditions.visibilityOfElementLocated(this.nameInput))).sendKeys(new CharSequence[]{name});
        ((WebElement)this.wait.until(ExpectedConditions.visibilityOfElementLocated(this.emailInput))).sendKeys(new CharSequence[]{email});
        ((WebElement)this.wait.until(ExpectedConditions.visibilityOfElementLocated(this.reviewInput))).sendKeys(new CharSequence[]{review});
        ((WebElement)this.wait.until(ExpectedConditions.elementToBeClickable(this.submitBtn))).click();
    }

    public boolean isReviewSuccessDisplayed() {
        WebElement msg = (WebElement)this.wait.until(ExpectedConditions.visibilityOfElementLocated(this.successMsg));
        return msg.isDisplayed() && msg.getText().trim().equals("Thank you for your review.");
    }
}
