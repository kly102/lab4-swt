package dfgfg;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class sdfjdrer {

    private static WebDriver driver;
    private static final String driverPath = "D:\\swt301\\chromedriver-win64\\chromedriver.exe";
    private static final String BASE_URL = "http://localhost:8080/AzanShop/manager";

    @BeforeAll
    public static void setUpClass() {
        System.setProperty("webdriver.chrome.driver", driverPath);
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterAll
    public static void tearDownClass() {
        if (driver != null) {
            driver.quit();
        }
    }

    @BeforeEach
    public void setUp() {
        driver.get(BASE_URL);
        login();
    }

    @AfterEach
    public void tearDown() {
        // Optional: Add any necessary cleanup code here.
    }

    @Test
    public void testUpdateNameNull() {
        navigateToManager();
        editFirstProduct();

        WebElement nameField = driver.findElement(By.name("name"));
        nameField.clear();
        
        // Wait until the submit button is clickable
        WebElement submitButton = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"editEmployeeModal\"]/div/div/form/div[3]/input")));
        submitButton.click();

        String validationMessage = nameField.getAttribute("validationMessage");
        assertEquals("Please fill out this field.", validationMessage);
    }

    @Test
    public void testUpdatelinkanhNull() {
        navigateToManager();
        editFirstProduct();

        WebElement linkanhField = driver.findElement(By.name("image"));
        linkanhField.clear();
        
        // Wait until the submit button is clickable
        WebElement submitButton = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"editEmployeeModal\"]/div/div/form/div[3]/input")));
        submitButton.click();

        String validationMessage = linkanhField.getAttribute("validationMessage");
        assertEquals("Please fill out this field.", validationMessage);
    }

    @Test
    public void testUpdatePriceNull() {
        navigateToManager();
        editFirstProduct();

        WebElement priceField = driver.findElement(By.name("price"));
        priceField.clear();
        
        // Wait until the submit button is clickable
        WebElement submitButton = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"editEmployeeModal\"]/div/div/form/div[3]/input")));
        submitButton.click();

        String validationMessage = priceField.getAttribute("validationMessage");
        assertEquals("Please fill out this field.", validationMessage);
    }

    @Test
    public void testUpdateTittleNull() {
        editFirstProduct();

        WebElement titleField = driver.findElement(By.name("title"));
        titleField.clear();
        
        // Wait until the submit button is clickable
        WebElement submitButton = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"editEmployeeModal\"]/div/div/form/div[3]/input")));
        submitButton.click();

        String validationMessage = titleField.getAttribute("validationMessage");
        assertEquals("Please fill out this field.", validationMessage);
    }

    @Test
    public void testUpdatePriceSpecialCharacters() {
        navigateToManager();
        editFirstProduct();

        WebElement priceField = driver.findElement(By.name("price"));
        priceField.clear();
        priceField.sendKeys("5400$%");

        // Submit the form
        WebElement submitButton = driver.findElement(By.xpath("//*[@id=\"editEmployeeModal\"]/div/div/form/div[3]/input"));
        submitButton.click();

        // Wait for redirection to manager page
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.urlContains("/manager"));

        // Assert that the current URL contains "/manager"
        assertTrue(driver.getCurrentUrl().contains("/manager"));

        // Optionally, assert that the price field now contains the updated value
        WebElement priceUpdated = driver.findElement(By.xpath("/html/body/div[1]/div/table/tbody/tr[1]/td[4]"));
        assertEquals("5400.0 VND", priceUpdated.getText());
    }

    @Test
    public void testUpdatePriceWithSpaces() {
        navigateToManager();
        editFirstProduct();

        WebElement priceField = driver.findElement(By.name("price"));
        priceField.clear();
        priceField.sendKeys("60   000");

        // Submit the form
        WebElement submitButton = driver.findElement(By.xpath("//*[@id=\"editEmployeeModal\"]/div/div/form/div[3]/input"));
        submitButton.click();

        // Wait for redirection to manager page
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.urlContains("/manager"));

        // Assert that the current URL contains "/manager"
        assertTrue(driver.getCurrentUrl().contains("/manager"));

        // Optionally, assert that the price field now contains the updated value
        WebElement priceUpdated = driver.findElement(By.xpath("/html/body/div[1]/div/table/tbody/tr[1]/td[4]"));
        assertEquals("60000.0 VND", priceUpdated.getText());
    }

    @Test
    public void testUpdateValidProduct() {
        navigateToManager();
        editFirstProduct();

        // Input updated data
        WebElement nameField = driver.findElement(By.name("name"));
        nameField.clear();
        nameField.sendKeys("Updated Product");

        WebElement priceField = driver.findElement(By.name("price"));
        priceField.clear();
        priceField.sendKeys("50000");

        WebElement imageField = driver.findElement(By.name("image"));
        imageField.clear();
        imageField.sendKeys("https://media-cdn.tripadvisor.com/media/photo-s/0e/04/5c/aa/coffee-art.jpg");

        // Submit the form
        WebElement submitButton = driver.findElement(By.xpath("//*[@id=\"editEmployeeModal\"]/div/div/form/div[3]/input"));
        submitButton.click();

        // Wait for redirection to manager page
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.urlContains("/manager"));

        // Assert that the current URL contains "/manager"
        assertTrue(driver.getCurrentUrl().contains("/manager"));

        // Optionally, assert that the product was updated correctly
        WebElement updatedProductName = driver.findElement(By.xpath("/html/body/div[1]/div/table/tbody/tr[1]/td[2]"));
        assertEquals("Updated Product", updatedProductName.getText());

        WebElement updatedProductPrice = driver.findElement(By.xpath("/html/body/div[1]/div/table/tbody/tr[1]/td[4]"));
        assertEquals("50000.0 VND", updatedProductPrice.getText());
    }

    @Test
    public void testDuplicateProductName() {
        navigateToManager();
        editFirstProduct();

        // Input duplicate name
        WebElement nameField = driver.findElement(By.name("name"));
        nameField.clear();
        nameField.sendKeys("bạc xỉu");

        // Submit the form
        WebElement submitButton = driver.findElement(By.xpath("//*[@id=\"editEmployeeModal\"]/div/div/form/div[3]/input"));
        submitButton.click();

        // Wait for any potential error messages to appear
        WebElement errorMessage = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("error-message-class")));

        // Assert that the error message is displayed
        String errorText = errorMessage.getText();
        assertEquals("Product name already exists", errorText);
    }

    private void login() {
        try {
            WebElement usernameField = new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"logreg-forms\"]/form/div[2]/input")));
            usernameField.sendKeys("aaa");

            WebElement passwordField = driver.findElement(By.xpath("//*[@id=\"logreg-forms\"]/form/div[3]/input"));
            passwordField.sendKeys("123456");

            WebElement loginButton = driver.findElement(By.xpath("//*[@id=\"logreg-forms\"]/form/div[4]/button[1]"));
            loginButton.click();
        } catch (Exception e) {
            System.out.println("Error in login: " + e.getMessage());
        }
    }

    private void navigateToManager() {
        try {
            WebElement managerLink = new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"navbarSupportedContent\"]/ul/li[5]/a")));
            managerLink.click();
        } catch (Exception e) {
            System.out.println("Error in navigateToManager: " + e.getMessage());
        }
    }

    private void editFirstProduct() {
        try {
            WebElement editButton = new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div/table/tbody/tr[1]/td[5]/a[1]/i")));
            editButton.click();

            WebElement editField = new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"editEmployeeModal\"]/div/div/form/div[2]/div[4]/input")));
            editField.click();
        } catch (Exception e) {
            System.out.println("Error in editFirstProduct: " + e.getMessage());
        }
    }
}
