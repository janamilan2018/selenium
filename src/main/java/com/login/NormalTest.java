package com.login;
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

import static org.testng.AssertJUnit.assertTrue;

public class NormalTest {

    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        int passedCount = 0;
        int failedCount = 0;
        try {
            loginToChatbot(driver, wait);

            List<String[]> csvData = readCsvFile("/home/cbnits-208/Downloads/tableConvert.com_x8fk3u.csv");

            for (int i = 0; i < csvData.size(); i++) {
                try {
                    String dataB = csvData.get(i)[0].trim();

                    sendMessageToChatbot(driver, wait, dataB);

                    String response = getChatbotResponse(driver, wait);
                    assertTrue("Expected response not found", response.contains("Expected Response"));
                    passedCount++;
                } catch (Exception e) {
                    e.printStackTrace();
                    failedCount++;
                }
            }
            System.out.println("Passed count: " + passedCount);
            System.out.println("Failed count: " + failedCount);
        } catch (Exception e) {
            e.printStackTrace();
            assertTrue("Test failed due to exception: " + e.getMessage(), false);
        } finally {
            driver.quit();
        }
    }

    private static void loginToChatbot(WebDriver driver, WebDriverWait wait) {
        driver.get("https://ai-chatbot-dev.swiftsecurity.ai/");

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

    private static String getChatbotResponse(WebDriver driver, WebDriverWait wait) {
        WebElement responseElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.chatbot-response")));

        return responseElement.getText();
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