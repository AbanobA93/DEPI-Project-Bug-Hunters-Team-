package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LandingPage;
import pages.ProductsPage;
import utils.FakerDataProvider;

public class AddReviewTest extends BaseTest {
    @Test(dataProvider = "reviewData", dataProviderClass = FakerDataProvider.class)
    public void addReviewOnProduct(String name, String email, String review) {
        LandingPage landingPage = new LandingPage(driver, wait);
        ProductsPage productsPage = new ProductsPage(driver, wait);
        landingPage.moveToProductsPage();
        productsPage.clickFirstProduct();
        productsPage.writeReview(name, email, review);
        Assert.assertTrue(productsPage.isReviewSuccessDisplayed(), "Review success message not displayed!");
    }

}
