package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    WebDriver driver;

    By productLink(String product) {
        return By.linkText(product);
    }

    By loginButton = By.id("login2");
    By usernameField = By.id("loginusername");
    By passwordField = By.id("loginpassword");
    By submitLoginButton = By.xpath("//button[text()='Log in']");
    By loggedInUser = By.id("nameofuser");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get("https://demoblaze.com");
    }

    public boolean isHomePageDisplayed() {
        return driver.getTitle().contains("STORE");
    }

    public void clickProduct(String product) {
        driver.findElement(productLink(product)).click();
    }

    public void clickLogin() {
        driver.findElement(loginButton).click();
    }

    public void enterUsername(String username) {
        driver.findElement(usernameField).sendKeys(username);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    public void submitLogin() {
        driver.findElement(submitLoginButton).click();
    }

    public boolean isLoggedIn() {
        return driver.findElement(loggedInUser).isDisplayed();
    }
}
