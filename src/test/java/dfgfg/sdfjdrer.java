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

    private static WebDriver drive;
    private static final String driverPath = "D:\\swt301\\chromedriver-win64\\chromedriver.exe";
    private static final String BASE_URL = "http://localhost:8080/AzanShop/manager";

    @BeforeAll
    public static void setUpClass() {
        System.setProperty("webdriver.chrome.driver", driverPath);
        drive = new ChromeDriver();
        drive.manage().window().maximize();
    }

    @AfterAll
    public static void tearDownClass() {
        if (drive != null) {
            drive.quit();
        }
    }

    @BeforeEach
    public void setUp() throws InterruptedException {
        drive.navigate().to(BASE_URL);
        Thread.sleep(2000);
        login();
    }

    @AfterEach
    public void tearDown() {
        // Optionally: add code to reset the state if needed.
    }

    @Test
    public void testUpdateNameNull() throws InterruptedException {
        navigateToManager();
        editFirstProduct();

        WebElement nameField = drive.findElement(By.name("name"));
        nameField.clear();
        Thread.sleep(2000);
        WebDriverWait wait = new WebDriverWait(drive, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"editEmployeeModal\"]/div/div/form/div[3]/input"))).click();
        Thread.sleep(2000);
        String validationMessage = nameField.getAttribute("validationMessage");
        assertEquals("Please fill out this field.", validationMessage);
    }

    @Test
    public void testUpdatelinkanhNull() throws InterruptedException {
        navigateToManager();
        editFirstProduct();
        WebElement linkanhField = drive.findElement(By.name("image"));
        linkanhField.clear();
        Thread.sleep(2000);
        WebDriverWait wait = new WebDriverWait(drive, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"editEmployeeModal\"]/div/div/form/div[3]/input"))).click();
        Thread.sleep(2000);
        String validationMessage = linkanhField.getAttribute("validationMessage");
        assertEquals("Please fill out this field.", validationMessage);
    }

    @Test
    public void testUpdatePriceNull() throws InterruptedException {
        navigateToManager();
        editFirstProduct();

        WebElement priceField = drive.findElement(By.name("price"));
        priceField.clear();
        Thread.sleep(2000);
        WebDriverWait wait = new WebDriverWait(drive, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"editEmployeeModal\"]/div/div/form/div[3]/input"))).click();
        Thread.sleep(2000);
        String validationMessage = priceField.getAttribute("validationMessage");
        assertEquals("Please fill out this field.", validationMessage);
    }

    @Test
    public void testUpdateTittleNull() throws InterruptedException {
//        navigateToManager();
        editFirstProduct();
        WebElement tittleField = drive.findElement(By.name("title"));
        tittleField.clear();
        Thread.sleep(2000);
        WebDriverWait wait = new WebDriverWait(drive, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"editEmployeeModal\"]/div/div/form/div[3]/input"))).click();
        Thread.sleep(2000);
        String validationMessage = tittleField.getAttribute("validationMessage");
        assertEquals("Please fill out this field.", validationMessage);
    }

    @Test
    public void testUpdatePriceSpecialCharacters() throws InterruptedException {
        navigateToManager();
        editFirstProduct();

        // Input price with special characters
        WebElement priceField = drive.findElement(By.name("price"));
        priceField.clear();
        priceField.sendKeys("5400$%");
        Thread.sleep(2000);
        // Submit the form
        WebElement submitButton = drive.findElement(By.xpath("//*[@id=\"editEmployeeModal\"]/div/div/form/div[3]/input"));
        submitButton.click();
        // Wait for redirection to manager page
        WebDriverWait wait = new WebDriverWait(drive, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("/manager"));
        // Assert that the current URL contains "/manager"
        assertTrue(drive.getCurrentUrl().contains("/manager"));
//     Optionally, assert that the price field now contains "45000" after processing
        WebElement priceUpdated = drive.findElement(By.xpath("/html/body/div[1]/div/table/tbody/tr[1]/td[4]"));
//        String updatedPrice = priceField.getAttribute("value");
        assertEquals("5400.0 VND", priceUpdated.getText());
    }

    @Test
    public void testUpdatePricedaucach() throws InterruptedException {
        navigateToManager();
        editFirstProduct();
        // Input price with special characters
        WebElement priceField = drive.findElement(By.name("price"));
        priceField.clear();
        priceField.sendKeys("60   000");
        Thread.sleep(2000);
        // Submit the form
        WebElement submitButton = drive.findElement(By.xpath("//*[@id=\"editEmployeeModal\"]/div/div/form/div[3]/input"));
        submitButton.click();
        // Wait for redirection to manager page
        WebDriverWait wait = new WebDriverWait(drive, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("/manager"));
        // Assert that the current URL contains "/manager"
        assertTrue(drive.getCurrentUrl().contains("/manager"));
//     Optionally, assert that the price field now contains "45000" after processing
        WebElement priceUpdated = drive.findElement(By.xpath("/html/body/div[1]/div/table/tbody/tr[1]/td[4]"));
//        String updatedPrice = priceField.getAttribute("value");
        assertEquals("60000.0 VND", priceUpdated.getText());
    }

    @Test
    public void testUpdateValidProduct() throws InterruptedException {
        // Navigate to manager page and edit first product
        navigateToManager();
        editFirstProduct();
        // Input updated data
        WebElement nameField = drive.findElement(By.name("name"));
        nameField.clear();
        nameField.sendKeys("Updated Product");
        Thread.sleep(2000);
        WebElement priceField = drive.findElement(By.name("price"));
        priceField.clear();
        priceField.sendKeys("50000");
        Thread.sleep(2000);
        WebElement linkangField = drive.findElement(By.name("image"));
        linkangField.clear();
        linkangField.sendKeys("https://media-cdn.tripadvisor.com/media/photo-s/0e/04/5c/aa/coffee-art.jpg");
        Thread.sleep(2000);

// Submit the form
        WebElement submitButton = drive.findElement(By.xpath("//*[@id=\"editEmployeeModal\"]/div/div/form/div[3]/input"));
        submitButton.click();
        // Wait for redirection to manager page
        WebDriverWait wait = new WebDriverWait(drive, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("/manager"));
        // Assert that the current URL contains "/manager"
        assertTrue(drive.getCurrentUrl().contains("/manager"));
        // Optionally, assert that the product was updated correctly
        WebElement updatedProductName = drive.findElement(By.xpath("/html/body/div[1]/div/table/tbody/tr[1]/td[2]")); // Adjust the locator based on your application
        assertEquals("Updated Product", updatedProductName.getText());
        WebElement updatedProductPrice = drive.findElement(By.xpath("/html/body/div[1]/div/table/tbody/tr[1]/td[4]")); // Adjust the locator based on your application
        assertEquals("50000.0 VND", updatedProductPrice.getText());
    }

    @Test
    public void testDuplicateProductName() throws InterruptedException {
        // Navigate to manager page and edit first product
        navigateToManager();
        editFirstProduct();
        // Input duplicate name
        WebElement nameField = drive.findElement(By.name("name"));
        nameField.clear();
        nameField.sendKeys("bạc xỉu");
        // Submit the form
        WebElement submitButton = drive.findElement(By.xpath("//*[@id=\"editEmployeeModal\"]/div/div/form/div[3]/input"));
        submitButton.click();
        // Wait for any potential error messages to appear
        WebDriverWait wait = new WebDriverWait(drive, Duration.ofSeconds(10));
        WebElement errorMessage; // Adjust the locator based on your application
        errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("error-message-class")));
        // Assert that the error message is displayed
        String errorText = errorMessage.getText();
        assertEquals("Product name already exists", errorText); // Adjust the expected error message to match your application's behavior
    }

    private static void login() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(drive, Duration.ofSeconds(10));
        try {
            WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"logreg-forms\"]/form/div[2]/input")));
            usernameField.sendKeys("aaa");
            Thread.sleep(2000);
            WebElement passwordField = drive.findElement(By.xpath("//*[@id=\"logreg-forms\"]/form/div[3]/input"));
            passwordField.sendKeys("123456");
            Thread.sleep(2000);
            WebElement loginButton = drive.findElement(By.xpath("//*[@id=\"logreg-forms\"]/form/div[4]/button[1]"));
            loginButton.click();
            Thread.sleep(2000);
        } catch (Exception e) {
            System.out.println("Error in login: " + e.getMessage());
        }
    }

    private void navigateToManager() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(drive, Duration.ofSeconds(10));
        try {
            WebElement managerLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"navbarSupportedContent\"]/ul/li[5]/a")));
            managerLink.click();
            Thread.sleep(2000);
        } catch (Exception e) {
            System.out.println("Error in navigateToManager: " + e.getMessage());
        }
    }

    private void editFirstProduct() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(drive, Duration.ofSeconds(10));
        try {
            WebElement editButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div/table/tbody/tr[1]/td[5]/a[1]/i")));
            editButton.click();
            Thread.sleep(2000);
            WebElement editField = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"editEmployeeModal\"]/div/div/form/div[2]/div[4]/input")));
            editField.click();
            Thread.sleep(2000);
        } catch (Exception e) {
            System.out.println("Error in editFirstProduct: " + e.getMessage());
        }
    }
}
