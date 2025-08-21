package stepDefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.CartPage;
import pages.HomePage;
import pages.ProductPage;

import java.time.Duration;

import static org.junit.Assert.*;

public class DemoblazeSteps {
    WebDriver driver;
    WebDriverWait wait;
    HomePage homePage;
    ProductPage productPage;
    CartPage cartPage;

    @Given("I am on the Demoblaze homepage")
    public void i_am_on_the_demoblaze_homepage() {
        ChromeOptions options = new ChromeOptions();
        if (System.getenv("CI") != null) {
            options.addArguments("--headless");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
        }
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        homePage = new HomePage(driver);
        productPage = new ProductPage(driver);
        cartPage = new CartPage(driver);

        driver.manage().window().maximize();
        homePage.open();

        // wait for products to load (first card visible)
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".card-title a")));
    }

    @Then("I should see the Demoblaze homepage loaded")
    public void i_should_see_the_homepage_loaded() {
        assertTrue(homePage.isHomePageDisplayed());
        driver.quit();
    }

    @When("I click on a product {string}")
    public void i_click_on_a_product(String product) {
        homePage.clickProduct(product);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".name")));
    }

    @Then("I should see the product details for {string}")
    public void i_should_see_the_product_details(String product) {
        assertEquals(product, productPage.getProductTitle());
        driver.quit();
    }

    @And("I add the product to the cart")
    public void i_add_the_product_to_cart() {
        productPage.addToCart();
        wait.until(ExpectedConditions.alertIsPresent());
    }

    @Then("I should see a confirmation alert")
    public void i_should_see_a_confirmation_alert() {
        Alert alert = driver.switchTo().alert();
        assertEquals("Product added", alert.getText());
        alert.accept();
        driver.quit();
    }

    @When("I log in with username {string} and password {string}")
    public void i_log_in_with_username_and_password(String username, String password) {
        homePage.clickLogin();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loginusername")));
        homePage.enterUsername(username);
        homePage.enterPassword(password);
        homePage.submitLogin();
    }

    @Then("I should be logged in successfully")
    public void i_should_be_logged_in_successfully() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nameofuser")));
        assertTrue(homePage.isLoggedIn());
        driver.quit();
    }

    @Given("I have a product in the cart")
    public void i_have_a_product_in_the_cart() {
        homePage.clickProduct("Samsung galaxy s6");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".name")));
        productPage.addToCart();

        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();

        cartPage.openCart();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".success")));
    }

    @When("I place an order with name {string} and credit card {string}")
    public void i_place_an_order(String name, String card) {
        cartPage.clickPlaceOrder();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("orderModal")));

        cartPage.enterName(name);
        cartPage.enterCard(card);
        cartPage.clickPurchase();
    }

    @Then("I should see a purchase confirmation")
    public void i_should_see_a_purchase_confirmation() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".sweet-alert h2")));
        assertTrue(cartPage.isPurchaseConfirmed());
        driver.quit();
    }

    @Then("I should see a login error alert with message {string}")
    public void i_should_see_a_login_error_alert_with_message(String expectedMessage) {
        wait.until(ExpectedConditions.or(
                ExpectedConditions.alertIsPresent()
        ));
        Alert alert = driver.switchTo().alert();
        assertEquals(expectedMessage, alert.getText());
        alert.accept();
        driver.quit();
    }
}
