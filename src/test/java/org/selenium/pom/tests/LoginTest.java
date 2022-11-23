package org.selenium.pom.tests;


import org.selenium.pom.api.actions.CartApi;
import org.selenium.pom.api.actions.SignUpApi;
import org.selenium.pom.base.BaseTest;
import org.selenium.pom.objects.Product;
import org.selenium.pom.objects.User;
import org.selenium.pom.pages.CheckoutPage;
import org.selenium.pom.pages.LoginPage;
import org.selenium.pom.utils.FakerUtils;

import org.testng.Assert;

import org.testng.annotations.Test;

import java.io.IOException;

public class LoginTest extends BaseTest {


    @Test(description = "Login during checkout")
    public void loginDuringCheckout() throws IOException, InterruptedException {
        // genero un usuario nuevo en la bbdd
        String username = "demouser" + new FakerUtils().generateRandomNumber();
        User user = new User();
        user.setUsername(username);
        user.setPassword("demopass");
        user.setEmail(username + "@askomdch.com");
        SignUpApi signUpApi = new SignUpApi();
        signUpApi.register(user);

        //creo la cookie de la api de cart, pasandole el usuario
        CartApi cartApi = new CartApi(signUpApi.getCookies());
        //instancio un producto cuyo identificado es 1215
        Product product = new Product(1215);
        //añado el producto al carrito, una unidad
        cartApi.addToCart(product.getId(), 1);
        //aquí se carga por primera vez la página porque es donde se inicializa el driver. Aquí empieza realmente el test
        CheckoutPage checkoutPage = new CheckoutPage(getDriver());
        checkoutPage.load();
        //se injectan las cookies de carrito al navegador. el método injectCookiesToBrowser transforma las cookies de RestAssured en Cookies de Selenium
        injectCookiesToBrowser(cartApi.getCookies());
        //recargo la pagina de checkout
        checkoutPage.load();

        checkoutPage.clickLoginLink();
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.login(user);
        loginPage.clickLoginButton();

        Assert.assertTrue(checkoutPage.getProductName().contains(product.getName()));


    }

}
