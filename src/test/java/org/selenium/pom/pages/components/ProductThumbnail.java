package org.selenium.pom.pages.components;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.selenium.pom.base.BasePage;

public class ProductThumbnail extends BasePage {

    public ProductThumbnail(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "a[title='View cart']")
    private WebElement viewCartLink;

    public void clickAddToCartButton(String productName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a[aria-label='Add “" + productName + "” to your cart']"))).click();
        //driver.findElement(By.cssSelector("a[aria-label='Add “" + productName + "” to your cart']")).click();

    }

    public void clickViewCart() {
        wait.until(ExpectedConditions.visibilityOf(viewCartLink)).click();
        //viewCartLink.click();
    }
}
