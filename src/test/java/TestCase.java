import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class TestCase {
        public static void main(String[] args) {
//            ChromeOptions options = new ChromeOptions();
//            options.addArguments("--remote-allow-origins=*");
            WebDriver driver = new ChromeDriver();
            driver.get("https://swift.swiftsecurity.ai/login");
            driver.manage().window().maximize();

            // Print page source for debugging
//            System.out.println(driver.getPageSource());

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
            usernameField.sendKeys("pbandyo@swiftsecurity.ai");

            WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));
            passwordField.sendKeys("Test@12345");

            WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@aria-label='LOGIN']")));
//            driver.switchTo().defaultContent();
            loginButton.click();
            WebElement errorBottom = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("svg[data-testid=\"KeyboardDoubleArrowRightIcon\"]")));
            errorBottom.click();
            WebElement policiesElement = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("body > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > ul:nth-child(1)>div:nth-child(4)>div:first-child>div:first-child")));
            policiesElement.click();
//
//            WebElement    searchInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='ag-97-input']")));
//            searchInput.sendKeys("BIOMETRIC_DATA_AFRIKAANS");

            WebElement llmGuardrails=wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("ul:nth-child(1) div:nth-child(4) div:nth-child(2) div:nth-child(1) div:nth-child(1) ul:nth-child(1) li:nth-child(5) div:nth-child(1) div:nth-child(1) span:nth-child(1)")));
            llmGuardrails.click();

            WebElement addPolicy=wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("svg[data-testid=\"AddIcon\"]")));
            addPolicy.click();
            WebElement policyDetails=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='policy_details']//div[@class='MuiAccordionSummary-expandIconWrapper css-1fx8m19']//*[name()='svg']")));
            policyDetails.click();
            WebElement policyName=wait.until(ExpectedConditions.elementToBeClickable(By.name("name")));
            policyName.sendKeys("AutoTest1");

            WebElement description=wait.until(ExpectedConditions.elementToBeClickable(By.name("description")));
            description.sendKeys("Automation Testing");

           driver.findElement(By.id("applications")).sendKeys("ai");
            List<WebElement> list=driver.findElements(By.xpath("//div[@class=\"MuiButtonBase-root MuiChip-root MuiChip-filled MuiChip-sizeMedium MuiChip-colorDefault MuiChip-deletable MuiChip-deletableColorDefault MuiChip-filledDefault MuiAutocomplete-tag MuiAutocomplete-tagSizeMedium css-10f1bb6\"]"));

            System.out.println(list.size());
            for(int i=0;i< list.size();i++) {
                System.out.println(list.get(i).getText());
                if (list.get(i).getText().equals("ai")) {
                    list.get(i).click();
                    break;
                }
            }
//            WebElement dropdownOption = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div[class='MuiInputBase-root MuiOutlinedInput-root MuiInputBase-colorPrimary MuiInputBase-fullWidth MuiInputBase-formControl MuiInputBase-adornedStart MuiInputBase-adornedEnd MuiAutocomplete-inputRoot css-i36zc6'] div:nth-child(1) span:nth-child(1)")));
//            dropdownOption.click();
//
//            // Verify the selection if needed
//            WebElement selectedOption = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[class='MuiInputBase-root MuiOutlinedInput-root MuiInputBase-colorPrimary MuiInputBase-fullWidth MuiInputBase-formControl MuiInputBase-adornedStart MuiInputBase-adornedEnd MuiAutocomplete-inputRoot css-i36zc6'] div:nth-child(1) span:nth-child(1)")));
//            System.out.println("Selected option: " + selectedOption.getText());
//
//            driver.quit();
        }


    }

