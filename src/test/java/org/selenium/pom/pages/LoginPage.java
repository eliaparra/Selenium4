package org.selenium.pom.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.selenium.pom.base.BasePage;
import org.selenium.pom.objects.User;

public class LoginPage extends BasePage {


    @FindBy(css = "#username")
    private WebElement username;
    @FindBy(css = "#password")
    private WebElement password;
    @FindBy(css = "button[value='Login']")
    private WebElement loginButton;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void writeUserName(String value) {
        wait.until(ExpectedConditions.visibilityOf(username));
        username.sendKeys(value);
    }

    public void writePassword(String value) {
        password.sendKeys(value);
    }

    public void clickLoginButton() {
        loginButton.click();
    }

    public void login(User user) {
        writeUserName(user.getUsername());
        writePassword(user.getPassword());
    }
}
