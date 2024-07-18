/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dfgfg;

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 *
 * @author YEN
 */
public class ordertest {

    private static WebDriver driver;
    private static final String driverPath = "D:\\swt301\\chromedriver-win64\\chromedriver.exe";
    private static final String BASE_URL = "http://localhost:8080/AzanShop/carts";

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
    public void setUp() throws InterruptedException {
        driver.navigate().to(BASE_URL);
        Thread.sleep(2000);
        login();
    }

    @AfterEach
    public void tearDown() {
        // Optionally: add code to reset the state if needed.
    }

    @Test
    public void testAddProductToCart() throws InterruptedException {
        navigateToHome();
        addFirstProductToCart();
        navigateToCart();

        // Verify product is in cart
        WebElement productName = driver.findElement(By.xpath("//table/tbody/tr/td[2]"));
        assertEquals("bạc xỉu", productName.getText());

        WebElement productPrice = driver.findElement(By.xpath("//table/tbody/tr/td[4]"));
        assertEquals("50000.0", productPrice.getText());
    }

    @Test
    public void testUpdateProductQuantity() throws InterruptedException {
        navigateToHome();
        addFirstProductToCart();
        navigateToCart();

        // Update product quantity
        WebElement quantityField = driver.findElement(By.xpath("//table/tbody/tr/td[5]/input"));
        quantityField.clear();
        quantityField.sendKeys("2");
        Thread.sleep(2000);

        // Verify total price is updated
        WebElement totalPrice = driver.findElement(By.xpath("//table/tbody/tr/td[7]"));
        assertEquals("100000.0", totalPrice.getText());
    }

    @Test
    public void testRemoveProductFromCart() throws InterruptedException {
        navigateToHome();
        addFirstProductToCart();
        navigateToCart();

        // Remove product from cart
        WebElement removeButton = driver.findElement(By.xpath("//table/tbody/tr/td[8]/button"));
        removeButton.click();
        Thread.sleep(2000);

        // Verify cart is empty
        WebElement cartEmptyMessage = driver.findElement(By.xpath("//div[@id='cart-empty']"));
        assertEquals("Your cart is empty.", cartEmptyMessage.getText());
    }

    private static void login() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"logreg-forms\"]/form/div[2]/input")));
            usernameField.sendKeys("aaa");
            Thread.sleep(2000);
            WebElement passwordField = driver.findElement(By.xpath("//*[@id=\"logreg-forms\"]/form/div[3]/input"));
            passwordField.sendKeys("123456");
            Thread.sleep(2000);
            WebElement loginButton = driver.findElement(By.xpath("//*[@id=\"logreg-forms\"]/form/div[4]/button[1]"));
            loginButton.click();
            Thread.sleep(2000);
        } catch (Exception e) {
            System.out.println("Error in login: " + e.getMessage());
        }
    }

    private void navigateToHome() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            WebElement homeLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"navbarSupportedContent\"]/ul/li[1]/a")));
            homeLink.click();
            Thread.sleep(2000);
        } catch (Exception e) {
            System.out.println("Error in navigateToHome: " + e.getMessage());
        }
    }

    private void addFirstProductToCart() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            WebElement addButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='product']/button")));
            addButton.click();
            Thread.sleep(2000);
        } catch (Exception e) {
            System.out.println("Error in addFirstProductToCart: " + e.getMessage());
        }
    }

    private void navigateToCart() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            WebElement cartLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"navbarSupportedContent\"]/ul/li[4]/a")));
            cartLink.click();
            Thread.sleep(2000);
        } catch (Exception e) {
            System.out.println("Error in navigateToCart: " + e.getMessage());
        }
    }
}
