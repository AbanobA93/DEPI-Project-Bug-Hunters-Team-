package tests;

import org.testng.annotations.Test;
import pages.LandingPage;
import pages.ProductsPage;

public class SearchAndVerifyTest extends BaseTest {

    @Test
    public void verifySearchProductsRemainAfterLogin() {
        LandingPage landingPage = new LandingPage(driver, wait);
        ProductsPage productsPage = new ProductsPage(driver, wait);
        landingPage.moveToProductsPage();
        productsPage.searchProduct();
        productsPage.addProductsToCart();
        productsPage.clickFirstProduct();
    }

}
