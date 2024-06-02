
//
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CsvFile{
    private WebDriver driver;
    private WebDriverWait wait;
    private static final Logger LOGGER = Logger.getLogger(CsvFile.class.getName());

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @BeforeMethod
    public void navigateToLoginPage() {
        driver.get("https://aichatbot-staging.swiftsecurity.ai/");
    }

    @Test(priority = 1)
    public void testLogin() {
        try {
            WebElement usernameField = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[aria-label='Username']")));
            WebElement passwordField = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[aria-label='Password']")));
            WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Login']")));

            usernameField.sendKeys("johndoe@acmebank.com");
            passwordField.sendKeys("Test@12345");
            loginButton.click();



            LOGGER.log(Level.INFO, "Login test executed successfully.");
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Login test failed: ", e);
            Assert.fail("Login test failed due to an exception.");
        }
    }
}
