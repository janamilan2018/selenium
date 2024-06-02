import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class AbuseCsvFile {

    public static void main(String[] args) {
        // Initialize WebDriver
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        int totalSentMessages = 0;
        int successfulResponses = 0;
        int failedResponses = 0;

        try {
            loginToChatbot(driver, wait);

            List<String[]> csvData = readCsvFile("/home/ubuntu/FirsTestCase/Prompting.csv");

            for (int i = 1; i < csvData.size(); i++) {
                try {
                    String dataF = csvData.get(i)[6].trim(); // Column F is index 5
                    totalSentMessages++;

                    sendMessageToChatbot(driver, wait, dataF);

                    if (waitForResponse(driver, wait)) {
                        successfulResponses++;
                    } else {
                        failedResponses++;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    failedResponses++;
                }
            }

            System.out.println("Total Sent Messages: " + totalSentMessages);
            System.out.println("Successful Responses: " + successfulResponses);
            System.out.println("Failed Responses: " + failedResponses);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }

    private static void loginToChatbot(WebDriver driver, WebDriverWait wait) {
        driver.get("https://aichatbot-staging.swiftsecurity.ai/");

        // Find and interact with web elements for login
        WebElement usernameField = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[aria-label='Username']")));
        WebElement passwordField = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[aria-label='Password']")));
        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Login']")));

        usernameField.sendKeys("johndoe@acmebank.com");
        passwordField.sendKeys("Test@12345");
        loginButton.click();

    }

    private static void sendMessageToChatbot(WebDriver driver, WebDriverWait wait, String message) {
        WebElement chatInputField = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("textarea[placeholder='Message Acme Bank Chatbot...']")));

        chatInputField.sendKeys(message);
        chatInputField.sendKeys(Keys.ENTER);
    }

    private static boolean waitForResponse(WebDriver driver, WebDriverWait wait) {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.chatbot-response")));
            return true; // Response received successfully
        } catch (Exception e) {
            return false; // Failed to receive response
        }
    }

    private static List<String[]> readCsvFile(String filePath) {
        List<String[]> data = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            String[] line;
            while ((line = reader.readNext()) != null) {
                data.add(line);
            }
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
        return data;
    }
}
