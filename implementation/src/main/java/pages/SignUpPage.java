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
    //strings
    private String expectedAccountCreatedMSG = "Account Created!";
    private String expectedlURL = "https://automationexercise.com/login";
    private String expectedAccountDeletedMSG = "ACCOUNT DELETED!";
    private String expectedEmailAlreadyExistsMSG = "Email Address already exist!";


    //getter
    public String getActualURL(){
        return expectedlURL;
    }

    public By getActualAccountCreatedMSG (){
        return actualAccountDeletedMsg;
    }

    public String getExpectedAccountCreatedMSG() {
        return expectedAccountCreatedMSG;
    }

    public String getExpectedAccountDeletedMSG(){
        return expectedAccountDeletedMSG;
    }
    public String getActualDeleteMSG(){
        WebElement msgElement = wait.until(ExpectedConditions.visibilityOfElementLocated(actualAccountDeletedMsg));
        return msgElement.getText();
    }


    public String getActualUrl_Login() {
        return driver.getCurrentUrl();
    }

    public String getExpectedEmailAlreadyExistsMSG(){
        WebElement msgElement = wait.until(
                ExpectedConditions.visibilityOfElementLocated(expectedEmailAlreadyExists)
        );
        return msgElement.getText();
    }

    // Actions

    public void clickSignupButton() {
        driver.findElement(signupBTN).click();
    }


    public void signUpPhase1(String name, String email) {

        driver.findElement(signupNameField).sendKeys(name);
        driver.findElement(signupEmailField).sendKeys(email);
        driver.findElement(signupBTN).click();
    }
    public void mailRadioBtn (){
        driver.findElement(maleRadioBTN).click();
    }
    public void password (String password){
        driver.findElement(passwordCreationField).sendKeys(password);
    }

    //calendar drop list

    public void dob(String day, String month, String year) {
        WebElement dayDropList = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("days")));
        WebElement monthDropList = driver.findElement(By.id("months"));
        WebElement yearDropList = driver.findElement(By.id("years"));

        new Select(dayDropList).selectByVisibleText(day);
        new Select(monthDropList).selectByVisibleText(month);
        new Select(yearDropList).selectByVisibleText(year);
    }
    public void checkBoxes (){
        driver.findElement(newsLitterCheckBox).click();
        driver.findElement(specialOfferCheckBox).click();
    }

    public void firstAndLastName(String firstname,String lastname){
        driver.findElement(firstNameField).sendKeys(firstname);
        driver.findElement(lastNameField).sendKeys(lastname);
    }
    public void company (String company){
        driver.findElement(companyField).sendKeys(company);
    }

    public void addresses (String firstAddress,
                           String secondAddress,
                           String country,
                           String stateName,
                           String zipCodeNumber,
                           String cityName) {
        driver.findElement(addresField1).sendKeys(firstAddress);
        driver.findElement(addressField2).sendKeys(secondAddress);
        driver.findElement(countryDropDown).sendKeys(country + Keys.ENTER);
        driver.findElement(stateField).sendKeys(stateName);
        driver.findElement(cityField).sendKeys(cityName);
        driver.findElement(zipCodeField).sendKeys(zipCodeNumber);
    }
    public void mobileNumber(String mobile) {
        driver.findElement(mobileNumberField).sendKeys(mobile);
    }

    public void createAccount (){
        driver.findElement(createAccountBtn).click();
    }
    public void continueToHomePageAfterCreateAccount (){
        driver.findElement(continueBtnToHomeAfterCreateAccount).click();
    }
    public void delete (){
        driver.findElement(deleteAccountBTN).click();
    }
    public void continueToHomeAfterDelete(){
        driver.findElement(continueToHomeAfterDeleteBTN).click();
    }
    public String actualURL() {
        return driver.getCurrentUrl();
    }
}
