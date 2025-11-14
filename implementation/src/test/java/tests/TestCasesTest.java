package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LandingPage;
import pages.TesCasesPage;

public class TestCasesTest extends BaseTest {

    @Test
    public void verifyTestCasesPageNavigation() {
        LandingPage landingPage = new LandingPage(driver, wait);
        TesCasesPage testCasesPage = new TesCasesPage(driver, wait);
        landingPage.moveToTestCasesPage();
        String actualHeader = testCasesPage.getActualTestCasesHeaderText();
        String expectedHeader = testCasesPage.getExpectedText();
        Assert.assertEquals(actualHeader, expectedHeader);
    }
}
