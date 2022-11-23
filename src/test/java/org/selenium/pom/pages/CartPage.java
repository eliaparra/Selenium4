package org.selenium.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.selenium.pom.base.BasePage;

public class CartPage extends BasePage {


    //private final By cartHeading = By.cssSelector(".has-text-align-center");

    @FindBy(css = "td[class='product-name'] a")
    private WebElement productName;

    @FindBy(how = How.CSS, using = ".checkout-button")
    @CacheLookup private WebElement checkoutButton;

    public CartPage(WebDriver driver) {
        super(driver);
    }

   /* public boolean isLoaded() {
        return wait.until(ExpectedConditions.textToBe(cartHeading, "Cart"));
    }*/

    public String getProductName() {
        return wait.until(ExpectedConditions.visibilityOf(productName)).getText();
    }

    public void clickCheckoutButton() {
        wait.until(ExpectedConditions.elementToBeClickable(checkoutButton)).click();

    }

}
