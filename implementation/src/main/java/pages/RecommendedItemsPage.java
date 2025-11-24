package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class RecommendedItemsPage extends BasePage {
    public RecommendedItemsPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    JavascriptExecutor js;
    By recommendedSection = By.xpath("//h2[text()='recommended items']");
    By continueShoppingBtn = By.cssSelector(".btn.btn-success.close-modal");
    By viewCartBtn = By.xpath("//u[text()='View Cart']");

    public void scrollToRecommended() {
        WebElement section = this.driver.findElement(this.recommendedSection);
        this.js.executeScript("arguments[0].scrollIntoView(true);", new Object[] { section });
    }

    public void addProductById(String productId) throws InterruptedException {
        WebElement addToCartBtn = this.driver.findElement(By.cssSelector("a[data-product-id='" + productId + "']"));
        this.js.executeScript("arguments[0].click();", new Object[] { addToCartBtn });
        this.driver.findElement(this.continueShoppingBtn).click();
    }

    public void clickViewCart() {
        WebElement viewCart = this.driver.findElement(this.viewCartBtn);
        this.js.executeScript("arguments[0].click();", new Object[] { viewCart });
    }

    public int getCartItemsCount() {
        List<WebElement> cartItems = this.driver.findElements(By.cssSelector("tr[id^='product-']"));
        return cartItems.size();
    }
}
