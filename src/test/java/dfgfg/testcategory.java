/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dfgfg;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

/**
 *
 * @author admin
 */
public class testcategory {

    private static WebDriver driver;
    private static final String driverPath = "C:\\Users\\admin\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe";
    private static final String BASE_URL = "http://localhost:8080/AzanShop/home";

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
    public void testUpdateCategoryNameNull() {
        navigateToCategories();
        editFirstCategory();

        WebElement nameField = driver.findElement(By.name("name"));
        nameField.clear();

        WebElement submitButton = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='editEmployeeModal']//input[@type='submit']")));
        submitButton.click();

        WebElement validationMessageElement = driver.findElement(By.xpath("//*[@id='editEmployeeModal']//input[@name='name']"));
        String validationMessage = validationMessageElement.getAttribute("validationMessage");
        assertEquals("Please fill out this field.", validationMessage);
    }

    @Test
    public void testUpdateCategoryDescriptionNull() {
        navigateToCategories();
        editFirstCategory();

        WebElement descriptionField = driver.findElement(By.name("description"));
        descriptionField.clear();

        WebElement submitButton = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='editEmployeeModal']//input[@type='submit']")));
        submitButton.click();

        WebElement validationMessageElement = driver.findElement(By.xpath("//*[@id='editEmployeeModal']//input[@name='description']"));
        String validationMessage = validationMessageElement.getAttribute("validationMessage");
        assertEquals("Please fill out this field.", validationMessage);
    }

    @Test
    public void testUpdateCategoryValid() {
        navigateToCategories();
        editFirstCategory();

        WebElement nameField = driver.findElement(By.name("name"));
        nameField.clear();
        nameField.sendKeys("Updated Category");

        WebElement descriptionField = driver.findElement(By.name("description"));
        descriptionField.clear();
        descriptionField.sendKeys("This is an updated category description.");

        WebElement submitButton = driver.findElement(By.xpath("//*[@id='editEmployeeModal']//input[@type='submit']"));
        submitButton.click();

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.urlContains("/categories"));

        assertTrue(driver.getCurrentUrl().contains("/categories"));

        WebElement updatedCategoryName = driver.findElement(By.xpath("//td[text()='Updated Category']"));
        assertEquals("Updated Category", updatedCategoryName.getText());

        WebElement updatedCategoryDescription = driver.findElement(By.xpath("//td[text()='This is an updated category description.']"));
        assertEquals("This is an updated category description.", updatedCategoryDescription.getText());
    }

    private void login() {
        try {
            WebElement usernameField = new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.visibilityOfElementLocated(By.name("email")));
            usernameField.sendKeys("aaa");

            WebElement passwordField = driver.findElement(By.name("password"));
            passwordField.sendKeys("123456");

            WebElement loginButton = driver.findElement(By.xpath("//button[text()='Login']"));
            loginButton.click();
        } catch (Exception e) {
            System.out.println("Error in login: " + e.getMessage());
        }
    }

    private void navigateToCategories() {
        try {
            WebElement categoriesLink = new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Quản lý thể loại')]")));
            categoriesLink.click();
        } catch (Exception e) {
            System.out.println("Error in navigateToCategories: " + e.getMessage());
        }
    }

    private void editFirstCategory() {
        try {
            WebElement editButton = new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='editEmployeeModal']//button[contains(text(),'Edit')]")));
            editButton.click();

            new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='editEmployeeModal']//input[@type='submit']")));
        } catch (Exception e) {
            System.out.println("Error in editFirstCategory: " + e.getMessage());
        }
    }
}
