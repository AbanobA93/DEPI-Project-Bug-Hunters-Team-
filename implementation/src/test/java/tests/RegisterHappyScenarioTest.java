package tests;

import org.testng.annotations.Test;
import org.testng.Assert;
import pages.LandingPage;
import pages.SignUpPage;
import utils.FakerDataProvider;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;

public class RegisterHappyScenarioTest {
    protected WebDriver driver;
    protected WebDriverWait wait;
    private SignUpPage signUpPage;
    private LandingPage landingPage;

    @BeforeClass
    public void setup() {
        driver = new org.openqa.selenium.chrome.ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to("https://www.automationexercise.com/");
        wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(5));
        landingPage = new LandingPage(driver, wait);
        signUpPage = new SignUpPage(driver, wait);
    }

    @Test(dataProvider = "fullRegisterData", dataProviderClass = FakerDataProvider.class)
    public void register(String fullName, String email, String password, String company, String addr1,
            String addr2,
            String country, String state, String zip, String city, String mobile,
            String day, String month, String year) {

        // Verify that home page is visible successfully
        Assert.assertEquals(landingPage.getExpectedURL(), landingPage.actualURL());
        Assert.assertEquals(landingPage.getExpectedTitle(), landingPage.actualTitle());

        landingPage.moveToLoginPage();
        signUpPage.signUpPhase1(fullName, email);
        signUpPage.mailRadioBtn();
        signUpPage.password(password);
        signUpPage.dob(day, month, year);
        signUpPage.checkBoxes();

        String[] names = fullName.split(" ", 2);
        String firstName = names[0];
        String lastName = (names.length > 1) ? names[1] : "";

        signUpPage.firstAndLastName(firstName, lastName);

        signUpPage.company(company);
        signUpPage.addresses(addr1, addr2, country, state, zip, city);
        signUpPage.mobileNumber(mobile);
        signUpPage.createAccount();
    }

    @Test(dependsOnMethods = "register")
    public void verifyAccountCreated() {
        Assert.assertEquals(signUpPage.getExpectedAccountCreatedMSG(), signUpPage.getActualAccountCreatedMSG());
        signUpPage.continueToHomePageAfterCreateAccount();
    }

    @Test(dependsOnMethods = "verifyAccountCreated")
    public void verifyLoggedIn() {
        Assert.assertTrue(signUpPage.isLoggedUserVisible());
    }

    @Test(dependsOnMethods = "verifyLoggedIn")
    public void deleteAccount() {
        signUpPage.delete();
    }

    @Test(dependsOnMethods = "deleteAccount")
    public void verifyAccountDeleted() {
        Assert.assertEquals(signUpPage.getExpectedAccountDeletedMSG(), signUpPage.getActualDeleteMSG());
        signUpPage.continueToHomeAfterDelete();
    }

    @AfterClass
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}