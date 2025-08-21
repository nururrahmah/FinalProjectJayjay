package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductPage {
    WebDriver driver;

    By addToCartButton = By.linkText("Add to cart");
    By productTitle = By.cssSelector(".name");

    public ProductPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getProductTitle() {
        return driver.findElement(productTitle).getText();
    }

    public void addToCart() {
        driver.findElement(addToCartButton).click();
    }
}
