package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;

public class ContactUsPage extends BasePage {
    public ContactUsPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    // locators
    By nameField = By.name("name");
    By emailField = By.name("email");
    By subjectField = By.name("subject");
    By messageField = By.id("message");
    By uploadFileBTN = By.name("upload_file");
    By submitBTN = By.className("submit_form");
    By contactUsMsg = By.xpath("//h2[text()='Contact ']");
    By successMSG = By.className("alert-success");

    // Data
    String contactusTXT = "CONTACT US";

    // getters
    public String getContactusTXT() {
        return contactusTXT;
    }

    public String contactUsMsgTXT() {
        return driver.findElement(contactUsMsg).getText();
    }

    // actions
    public void userCanContactUs(String name, String email, String subject, String message) {

        driver.findElement(nameField).sendKeys(name);
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(subjectField).sendKeys(subject);
        driver.findElement(messageField).sendKeys(message);
        /// Upload File
        String filePath = System.getProperty("user.dir") + "\\images\\Mohamed Salah.jpg";
        driver.findElement(uploadFileBTN).sendKeys(filePath);
        driver.findElement(submitBTN).click();
    }

}
