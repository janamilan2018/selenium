//package com.login;
//
//import com.opencsv.CSVReader;
//import com.opencsv.exceptions.CsvException;
//import org.openqa.selenium.By;
//import org.openqa.selenium.Keys;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//import org.testng.Assert;
//import org.testng.annotations.AfterClass;
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.DataProvider;
//import org.testng.annotations.Test;
//
//import java.io.FileReader;
//import java.io.IOException;
//import java.time.Duration;
//import java.util.ArrayList;
//import java.util.List;
//
//public class CsvFileTest {
//
//    private static WebDriver driver;
//    private static WebDriverWait wait;
//
//    @BeforeClass
//    public static void setUp() {
//        driver = new ChromeDriver();
//        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        loginToChatbot();
//        System.out.println("successfully the login page");
//    }
//
//    @AfterClass
//    public static void tearDown() {
//        driver.quit();
//    }
//
//    @Test(dataProvider = "csvDataProvider")
//    public void testChatbotResponses(String message) {
//        sendMessageToChatbot(message);
//        String response = getChatbotResponse();
//        Assert.assertTrue(response.contains("Expected Response"), "Expected response not found");
//    }
//
//    @DataProvider(name = "csvDataProvider")
//    public Object[][] csvDataProvider() {
//        List<String[]> csvData = readCsvFile("/home/cbnits-208/Downloads/data/pii.csv");
//        Object[][] data = new Object[csvData.size() - 1][1]; // Skip header row
//        for (int i = 1; i < csvData.size(); i++) {
//            data[i - 1][0] = csvData.get(i)[1].trim();
//        }
//        return data;
//    }
//
//    private static void loginToChatbot() {
//        driver.get("https://aichatbot-staging.swiftsecurity.ai/");
//        WebElement usernameField = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[aria-label='Username']")));
//        WebElement passwordField = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[aria-label='Password']")));
//        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Login']")));
//        usernameField.sendKeys("johndoe@acmebank.com");
//        passwordField.sendKeys("Test@12345");
//        loginButton.click();
//    }
//
//    private static void sendMessageToChatbot(String message) {
//        WebElement chatInputField = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("textarea[placeholder='Message Acme Bank Chatbot...']")));
//        chatInputField.sendKeys(message);
//        chatInputField.sendKeys(Keys.ENTER);
//    }
//
//    private static String getChatbotResponse() {
//        WebElement responseElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.chatbot-response")));
//        return responseElement.getText();
//    }
//
//    private static List<String[]> readCsvFile(String filePath) {
//        List<String[]> data = new ArrayList<>();
//        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
//            String[] line;
//            while ((line = reader.readNext()) != null) {
//                data.add(line);
//            }
//        } catch (IOException | CsvException e) {
//            e.printStackTrace();
//        }
//        return data;
//    }
//}
//
//package com.login;
//import com.opencsv.CSVReader;
//import com.opencsv.exceptions.CsvException;
//import org.openqa.selenium.By;
//import org.openqa.selenium.Keys;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//import org.testng.annotations.AfterClass;
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.Test;
//
//import java.io.FileReader;
//import java.io.IOException;
//import java.time.Duration;
//import java.util.ArrayList;
//import java.util.List;
//
//
//public class CsvFileTest  {
//    private WebDriver driver;
//    private WebDriverWait wait;
//
//    @BeforeClass
//    public void setUp() {
//        driver = new ChromeDriver();
//        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//    }
//
//    @AfterClass
//    public void tearDown() {
//        if (driver != null) {
//            driver.quit();
//        }
//    }
//
//    @Test(priority = 1)
//    public void testLoginToChatbot() {
//        loginToChatbot();
//    }
//
//    @Test(priority = 2)
//    public void testReceiveCsvFile() {
//        loginToChatbot();
//        List<String[]> csvData = readCsvFile("/home/cbnits-208/Downloads/data/pii.csv");
//    }
//
//    @Test(priority = 3)
//    public void testSendMessageToChatbot() {
//        loginToChatbot();
//        List<String[]> csvData = readCsvFile("/home/cbnits-208/Downloads/data/pii.csv");
//
//        for (String[] rowData : csvData) {
//            String message = rowData[1].trim();
//            sendMessageToChatbot(message);
//        }
//    }
//
//    @Test(priority = 4)
//    public void testChatbotResponse() {
//        loginToChatbot();
//        List<String[]> csvData = readCsvFile("/home/cbnits-208/Downloads/data/pii.csv");
//
//        for (String[] rowData : csvData) {
//            String message = rowData[1].trim();
//            sendMessageToChatbot(message);
//            String response = getChatbotResponse();
//        }
//    }
//
//    @Test(priority = 5)
//    public void testCount() {
//        loginToChatbot();
//        List<String[]> csvData = readCsvFile("/home/cbnits-208/Downloads/data/pii.csv");
//
//        int passedCount = 0;
//        int failedCount = 0;
//
//        for (String[] rowData : csvData) {
//            String message = rowData[1].trim();
//            sendMessageToChatbot(message);
//            String response = getChatbotResponse();
//
//            if (response.contains("Expected Response")) {
//                passedCount++;
//            } else {
//                failedCount++;
//            }
//        }
//
//        System.out.println("Passed count: " + passedCount);
//        System.out.println("Failed count: " + failedCount);
//
//    }
//
//    private void loginToChatbot() {
//        driver.get("https://aichatbot-staging.swiftsecurity.ai/");
//
//        WebElement usernameField = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[aria-label='Username']")));
//        WebElement passwordField = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[aria-label='Password']")));
//        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Login']")));
//
//        usernameField.sendKeys("johndoe@acmebank.com");
//        passwordField.sendKeys("Test@12345");
//        loginButton.click();
//    }
//
//    private void sendMessageToChatbot(String message) {
//        WebElement chatInputField = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("textarea[placeholder='Message Acme Bank Chatbot...']")));
//
//        chatInputField.sendKeys(message);
//        chatInputField.sendKeys(Keys.ENTER);
//    }
//
//    private String getChatbotResponse() {
//        WebElement responseElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.chatbot-response")));
//        return responseElement.getText();
//    }
//
//    private List<String[]> readCsvFile(String filePath) {
//        List<String[]> data = new ArrayList<>();
//        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
//            String[] line;
//            while ((line = reader.readNext()) != null) {
//                data.add(line);
//            }
//        } catch (IOException | CsvException e) {
//            e.printStackTrace();
//        }
//        return data;
//    }
//}



//
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
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class CsvFileTest {
    private WebDriver driver;
    private static WebDriverWait wait;

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

    @Test(priority = 1)
    public void  testLogin(){
        loginToChatbot();

    }

    @Test(priority = 2, dependsOnMethods = "testLogin", dataProvider = "csvDataProvider")
    public void testCsvFile() {
        List<String[]> csvData = readCsvFile("/home/cbnits-208/Downloads/tableConvert.com_x8fk3u.csv");

        int passedCount = 0;
        int failedCount = 0;

        for (int i = 0; i < csvData.size(); i++) {
            try {
                String dataB = csvData.get(i)[0].trim();

                sendMessageToChatbot(dataB);

                String response = getChatbotResponse();
                if (!response.isEmpty()) {
                    System.out.println("Message sent: " + dataB);
                    System.out.println("Response received: " + response);
                    passedCount++;
                } else {
                    System.out.println("Message sent: " + dataB);
                    System.out.println("No response received for message.");
                    failedCount++;
                }
            } catch (Exception e) {
                e.printStackTrace();
                failedCount++;
            }
        }

        System.out.println("Passed count: " + passedCount);
        System.out.println("Failed count: " + failedCount);

        if (failedCount == 0) {
            System.out.println("All messages received responses. Test passed.");
        } else {
            System.out.println("Some messages did not receive responses. Test failed.");
            Assert.fail("Some messages did not receive responses.");
        }
    }

//    public void testCsvFile() {
//        int passedCount = 0;
//        int failedCount = 0;
//
//        List<String[]> csvData = readCsvFile("/home/cbnits-208/Downloads/data/pii.csv");
//
//        for (int i = 0; i < Math.min(3, csvData.size()); i++) {
//            try {
//                String dataB = csvData.get(i)[1].trim();
//
//                sendMessageToChatbot(dataB);
//
//                String response = getChatbotResponse();
//                Assert.assertTrue(response.contains("Expected Response"), "Expected response not found");
//                passedCount++;
//            } catch (Exception e) {
//                e.printStackTrace();
//                failedCount++;
//            }
//        }
//        System.out.println("Passed count: " + passedCount);
//        System.out.println("Failed count: " + failedCount);
//    }

    private void loginToChatbot() {
        driver.get("https://aichatbot-staging.swiftsecurity.ai/");

        WebElement usernameField = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[aria-label='Username']")));
        WebElement passwordField = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[aria-label='Password']")));
        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Login']")));

        usernameField.sendKeys("johndoe@acmebank.com");
        passwordField.sendKeys("Test@12345");
        loginButton.click();
    }

    private void sendMessageToChatbot( String message) {
        WebElement chatInputField = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("textarea[placeholder='Message Acme Bank Chatbot...']")));

        chatInputField.sendKeys(message);
        chatInputField.sendKeys(Keys.ENTER);
    }

    private static String getChatbotResponse( ) {
        WebElement responseElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.chatbot-response")));
        return responseElement.getText();
    }


    private List<String[]> readCsvFile(String filePath) {
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

