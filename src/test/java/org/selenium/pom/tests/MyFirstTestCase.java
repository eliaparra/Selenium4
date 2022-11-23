package org.selenium.pom.tests;


import org.selenium.pom.base.BaseTest;
import org.selenium.pom.objects.BillingAddress;
import org.selenium.pom.objects.Product;
import org.selenium.pom.objects.User;
import org.selenium.pom.pages.*;
import org.selenium.pom.pages.components.MyHeader;
import org.selenium.pom.pages.components.ProductThumbnail;
import org.selenium.pom.utils.ConfigLoader;
import org.selenium.pom.utils.JacksonUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;


public class MyFirstTestCase extends BaseTest {

    @Test(description = "Guest checkout using Direct bank transfer")
    public void guestCheckoutUsingDirectBankTransfer() throws IOException {
        BillingAddress billingAddress = JacksonUtils.deserializeJson("myBillingAddress.json", BillingAddress.class);
        Product product = new Product(1215);

        HomePage homePage = new HomePage(getDriver());
        homePage.load();
        MyHeader myHeader = homePage.getMyHeader();
        myHeader.clickStoreMenuLink();
        StorePage storePage = new StorePage(getDriver());
        storePage.enterTextIntoSearchField("Blue");
        storePage.clickSearchButton();

        Assert.assertEquals(storePage.getTitle(), "Search results: “Blue”");
        ProductThumbnail productThumbnail = homePage.getProductThumbnail();
        productThumbnail.clickAddToCartButton(product.getName());

        productThumbnail.clickViewCart();
        CartPage cartPage = new CartPage(getDriver());

        Assert.assertEquals(cartPage.getProductName(), product.getName());
        cartPage.clickCheckoutButton();
        CheckoutPage checkoutPage = new CheckoutPage(getDriver());
        checkoutPage.setBillingAddress(billingAddress);
        checkoutPage.selectDirectBankTransfer();
        checkoutPage.clickOrderButton();

        Assert.assertEquals(checkoutPage.getMsg(), "Thank you. Your order has been received.");

    }

    @Test(description = "Login and checkout using Direct bank transfer")
    public void loginAndCheckoutUsingDirectBankTransfer() throws IOException {
        BillingAddress billingAddress = JacksonUtils.deserializeJson("myBillingAddress.json", BillingAddress.class);
        Product product = new Product(1215);
        User user = new User(ConfigLoader.getInstance().getUsername(), ConfigLoader.getInstance().getPassword());

        HomePage homePage = new HomePage(getDriver());
        homePage.load();
        MyHeader myHeader = homePage.getMyHeader();
        myHeader.clickStoreMenuLink();
        StorePage storePage = new StorePage(getDriver());
        storePage.enterTextIntoSearchField("Blue");
        storePage.clickSearchButton();

        Assert.assertEquals(storePage.getTitle(), "Search results: “Blue”");
        ProductThumbnail productThumbnail = homePage.getProductThumbnail();
        productThumbnail.clickAddToCartButton(product.getName());

        productThumbnail.clickViewCart();

        CartPage cartPage = new CartPage(getDriver());

        Assert.assertEquals(cartPage.getProductName(), "Blue Shoes");
        cartPage.clickCheckoutButton();
        CheckoutPage checkoutPage = new CheckoutPage(getDriver());

        checkoutPage.clickLoginLink();

        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.login(user);
        loginPage.clickLoginButton();

        checkoutPage.setBillingAddress(billingAddress);
        checkoutPage.selectDirectBankTransfer();
        checkoutPage.clickOrderButton();

        Assert.assertEquals(checkoutPage.getMsg(), "Thank you. Your order has been received.");

    }
}
