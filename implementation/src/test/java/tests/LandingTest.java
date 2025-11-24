package tests;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LandingPage;
import pages.LoginPage;

public class LandingTest extends BaseTest {
    @Test
    public void assertLandingPageURL() {
        LandingPage landingPage = new LandingPage(driver, wait);
        Assert.assertEquals(landingPage.getExpectedURL(), landingPage.actualURL());
    }

    @Test
    public void assertPageTitle() {
        LandingPage landingPage = new LandingPage(driver, wait);
        Assert.assertEquals(landingPage.getExpectedTitle(), landingPage.actualTitle());
    }
}
