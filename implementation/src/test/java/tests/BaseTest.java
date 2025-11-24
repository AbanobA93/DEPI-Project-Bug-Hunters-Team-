package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class BaseTest {
    protected WebDriverWait wait;
    protected WebDriver driver;

    // Constructor
    public BaseTest() {
    }

    @BeforeMethod
    public void setDriver() {
        this.driver = new ChromeDriver();
        this.driver.manage().window().maximize();
        this.driver.navigate().to("https://www.automationexercise.com/");
        this.wait = new WebDriverWait(this.driver, Duration.ofSeconds(15));
    }

    @AfterMethod
    public void teardown() {
        this.driver.quit();
    }
}
