package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignUpPage extends BasePage {

    public SignUpPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    // locators
    private By signupNameField = By.cssSelector("input[data-qa=\"signup-name\"]");
    private By signupEmailField = By.cssSelector("input[data-qa=\"signup-email\"]");
    private By signupBTN = By.cssSelector("button[data-qa=\"signup-button\"]");
    private By maleRadioBTN = By.id("id_gender1");
    private By passwordCreationField = By.id("password");
    private By newsLitterCheckBox = By.id("newsletter");
    private By specialOfferCheckBox = By.id("optin");
    private By firstNameField = By.id("first_name");
    private By lastNameField = By.id("last_name");
    private By companyField = By.id("company");
    private By addresField1 = By.id("address1");
    private By addressField2 = By.id("address2");
    private By countryDropDown = By.id("country");
    private By stateField = By.id("state");
    private By cityField = By.id("city");
    private By zipCodeField = By.id("zipcode");
    private By mobileNumberField = By.id("mobile_number");
    private By createAccountBtn = By.cssSelector("button[data-qa='create-account']");
    private By actualAccountCreatedMsg = By.xpath(" //b[text()='Account Created!']");
    private By continueBtnToHomeAfterCreateAccount = By.cssSelector("a[data-qa=\"continue-button\"]");
    private By deleteAccountBTN = By.className("fa-trash-o");
    private By actualAccountDeletedMsg = By.xpath("//*[@id=\"form\"]/div/div/div/h2/b");
    private By continueToHomeAfterDeleteBTN = By.cssSelector("a[data-qa=\"continue-button\"]");
    private By expectedEmailAlreadyExists = By.cssSelector("#form > div > div > div:nth-child(3) > div > form > p");
    private By loggedInAsMsg = By.xpath("//*[contains(text(),'Logged in as')]");
    // strings
    private String expectedAccountCreatedMSG = "ACCOUNT CREATED!";
    private String expectedURL = "https://automationexercise.com/login";
    private String expectedAccountDeletedMSG = "ACCOUNT DELETED!";
    private String expectedEmailAlreadyExistsMSG = "Email Address already exist!";

    // getter
    public String getActualURL() {
        return expectedURL;
    }

    public String getActualAccountCreatedMSG() {
        return getText(actualAccountCreatedMsg);
    }

    public String getExpectedAccountCreatedMSG() {
        return expectedAccountCreatedMSG;
    }

    public String getExpectedAccountDeletedMSG() {
        return expectedAccountDeletedMSG;
    }

    public String getActualDeleteMSG() {
        return getText(actualAccountDeletedMsg);
    }

    public String getActualUrl_Login() {
        return driver.getCurrentUrl();
    }

    public String getExpectedEmailAlreadyExistsMSG() {
        return getText(expectedEmailAlreadyExists);
    }

    // Actions

    public boolean isLoggedUserVisible() {
        return driver.findElement(loggedInAsMsg).isDisplayed();
    }

    public void clickSignupButton() {
        click(signupBTN);
    }

    public void signUpPhase1(String name, String email) {

        writeText(signupNameField, name);
        writeText(signupEmailField, email);
        click(signupBTN);
    }

    public void mailRadioBtn() {
        click(maleRadioBTN);
    }

    public void password(String password) {
        writeText(passwordCreationField, password);
    }

    // calendar drop list

    public void dob(String day, String month, String year) {
        WebElement dayDropList = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("days")));
        WebElement monthDropList = driver.findElement(By.id("months"));
        WebElement yearDropList = driver.findElement(By.id("years"));

        new Select(dayDropList).selectByVisibleText(day);
        new Select(monthDropList).selectByVisibleText(month);
        new Select(yearDropList).selectByVisibleText(year);
    }

    public void checkBoxes() {
        click(newsLitterCheckBox);
        click(specialOfferCheckBox);
    }

    public void firstAndLastName(String firstname, String lastname) {
        writeText(firstNameField, firstname);
        writeText(lastNameField, lastname);
    }

    public void company(String company) {
        writeText(companyField, company);
    }

    public void addresses(String firstAddress,
            String secondAddress,
            String country,
            String stateName,
            String zipCodeNumber,
            String cityName) {
        writeText(addresField1, firstAddress);
        writeText(addressField2, secondAddress);
        writeText(countryDropDown, country + Keys.ENTER);
        writeText(stateField, stateName);
        writeText(cityField, cityName);
        writeText(zipCodeField, zipCodeNumber);
    }

    public void mobileNumber(String mobile) {
        writeText(mobileNumberField, mobile);
    }

    public void createAccount() {
        click(createAccountBtn);
    }

    public void continueToHomePageAfterCreateAccount() {
        click(continueBtnToHomeAfterCreateAccount);
    }

    public void delete() {
        click(deleteAccountBTN);
    }

    public void continueToHomeAfterDelete() {
        click(continueToHomeAfterDeleteBTN);
    }

    public String actualURL() {
        return driver.getCurrentUrl();
    }

}
