package org.selenium.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.selenium.pom.base.BasePage;
import org.selenium.pom.pages.components.ProductThumbnail;

public class StorePage extends BasePage {



    private ProductThumbnail productThumbnail;

    private final By overlay = By.cssSelector(".blockUI.blockOverlay");

    @FindBy(css = "#woocommerce-product-search-field-0")
    private WebElement searchField;
    @FindBy(css = "button[value='Search']")
    private WebElement searchButton;
    @FindBy(xpath = "//h1")
    private WebElement title;


    public StorePage(WebDriver driver) {
        super(driver);
        productThumbnail = new ProductThumbnail(driver);
    }

    public ProductThumbnail getProductThumbnail() {
        return productThumbnail;
    }

    public void enterTextIntoSearchField(String value) {
        wait.until(ExpectedConditions.visibilityOf(searchField)).sendKeys(value);
    }

    public void clickSearchButton() {
        searchButton.click();
    }

    public boolean isLoaded() {
        return wait.until(ExpectedConditions.urlContains("/store"));
    }

    public String getTitle() {
        waitForOverlaysToDissapear(overlay);
        return wait.until(ExpectedConditions.visibilityOf(title)).getText();

    }

    public void load() {
        load("/store");
    }


}
