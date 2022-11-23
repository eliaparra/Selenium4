package org.selenium.pom.pages;


import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.selenium.pom.base.BasePage;
import org.selenium.pom.objects.BillingAddress;


public class CheckoutPage extends BasePage {

    private final By overlay = By.cssSelector(".blockUI.blockOverlay");

    @FindBy(id = "billing_first_name")
    private WebElement firstName;
    @FindBy(id = "billing_last_name")
    private WebElement lastName;
    @FindBy(id = "billing_address_1")
    private WebElement address;
    @FindBy(id = "billing_city")
    private WebElement city;
    @FindBy(id = "billing_postcode")
    private WebElement postcode;
    @FindBy(id = "billing_email")
    private WebElement email;

    @FindBy(id = "place_order")
    private WebElement orderButton;

    @FindBy(css = ".woocommerce-notice")
    private WebElement confirmMsg;
    @FindBy(css = ".showlogin")
    private WebElement loginLink;
    @FindBy(id = "billing_country")
    private WebElement countryDropDown;
    @FindBy(id = "billing_state")
    private WebElement stateDropDown;
    @FindBy(id = "payment_method_bacs")
    private WebElement directBankTransferRadioBtn;
    @FindBy(css = "td[class='product-name']")
    private WebElement product_name;

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public void load() {
        load("/checkout/");
    }

    public void writeFirstName(String value) {
        WebElement e = waitForElementToBeVisible(firstName);
        e.clear();
        e.sendKeys(value);
    }

    public void writeLastName(String value) {
        lastName.clear();
        lastName.sendKeys(value);
    }

    public void writeAddress(String value) {
        address.clear();
        address.sendKeys(value);
    }

    public void writeCity(String value) {
        city.clear();
        city.sendKeys(value);
    }

    public void writePostCode(String value) {
        postcode.clear();
        postcode.sendKeys(value);

    }

    public void writeEmail(String value) {
        email.clear();
        email.sendKeys(value);

    }

    public void clickOrderButton() {
        waitForOverlaysToDissapear(overlay);

        wait.until(ExpectedConditions.elementToBeClickable(orderButton)).click();
    }

    public String getMsg() {
        wait.until(ExpectedConditions.visibilityOf(confirmMsg));
        return confirmMsg.getText();

    }

    public void clickLoginLink() {
        loginLink.click();

    }

    public void setBillingAddress(BillingAddress billingAddress) {
        writeFirstName(billingAddress.getFirstName());
        writeLastName(billingAddress.getLastName());
        selectCountry(billingAddress.getCountry());
        writeAddress(billingAddress.getAddressLineOne());
        writeCity(billingAddress.getCity());
        selectState(billingAddress.getState());
        writePostCode(billingAddress.getPostalCode());
        writeEmail(billingAddress.getEmail());
    }

    public void selectCountry(String countryName) {
        Select select = new Select(countryDropDown);
        select.selectByVisibleText(countryName);
    }

    public void selectState(String stateName) {
        Select select = new Select(stateDropDown);
        select.selectByVisibleText(stateName);
    }

    public void selectDirectBankTransfer() {

        WebElement e = wait.until(ExpectedConditions.elementToBeClickable(directBankTransferRadioBtn));
        if (!e.isSelected()) {
            e.click();
        }
    }

    public String getProductName() {
        return wait.until(ExpectedConditions.visibilityOf(product_name)).getText();
    }
}
