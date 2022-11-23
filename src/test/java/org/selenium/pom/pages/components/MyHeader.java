package org.selenium.pom.pages.components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.selenium.pom.base.BasePage;

public class MyHeader extends BasePage {

    @FindBy(css = "#menu-item-1227 > a")
    private WebElement storeMenuLink;

    public MyHeader(WebDriver driver) {
        super(driver);
    }

    public void clickStoreMenuLink() {
        wait.until(ExpectedConditions.elementToBeClickable(storeMenuLink)).click();
    }

}
