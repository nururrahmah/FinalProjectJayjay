package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage {
    WebDriver driver;

    By cartLink = By.id("cartur");
    By placeOrderButton = By.xpath("//button[text()='Place Order']");
    By nameField = By.id("name");
    By cardField = By.id("card");
    By purchaseButton = By.xpath("//button[text()='Purchase']");
    By confirmationText = By.cssSelector(".sweet-alert h2");

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public void openCart() {
        driver.findElement(cartLink).click();
    }

    public void clickPlaceOrder() {
        driver.findElement(placeOrderButton).click();
    }

    public void enterName(String name) {
        driver.findElement(nameField).sendKeys(name);
    }

    public void enterCard(String card) {
        driver.findElement(cardField).sendKeys(card);
    }

    public void clickPurchase() {
        driver.findElement(purchaseButton).click();
    }

    public boolean isPurchaseConfirmed() {
        return driver.findElement(confirmationText).isDisplayed();
    }
}
