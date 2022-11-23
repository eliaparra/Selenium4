package org.selenium.pom.tests;

import org.selenium.pom.base.BaseTest;
import org.selenium.pom.dataproviders.MyDataProvider;
import org.selenium.pom.objects.Product;
import org.selenium.pom.pages.CartPage;
import org.selenium.pom.pages.HomePage;
import org.selenium.pom.pages.StorePage;
import org.selenium.pom.pages.components.ProductThumbnail;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class AddToCartTest extends BaseTest {

    @Test(description = "Add to cart from Store Page")
    public void addToCartFromStorePage() throws IOException {
        Product product = new Product(1215);
        StorePage storePage = new StorePage(getDriver());
        storePage.load();
        ProductThumbnail productThumbnail = storePage.getProductThumbnail();
        productThumbnail.clickAddToCartButton(product.getName());
        productThumbnail.clickViewCart();
        CartPage cartPage = new CartPage(getDriver());

        Assert.assertEquals(cartPage.getProductName(), product.getName());
    }


    @Test(dataProvider = "getFeaturedProducts", dataProviderClass = MyDataProvider.class, description = "Add to cart featured products")
    public void addToCartFeaturedProducts(Product product) {
        HomePage homePage = new HomePage(getDriver());
        homePage.load();
        ProductThumbnail productThumbnail = homePage.getProductThumbnail();
        productThumbnail.clickAddToCartButton(product.getName());
        productThumbnail.clickViewCart();
        CartPage cartPage = new CartPage(getDriver());
        Assert.assertEquals(cartPage.getProductName(), product.getName());


    }


}
