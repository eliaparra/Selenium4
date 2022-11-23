package org.selenium.pom.tests;

import io.qameta.allure.*;
import org.selenium.pom.api.actions.CartApi;
import org.selenium.pom.api.actions.SignUpApi;
import org.selenium.pom.base.BaseTest;
import org.selenium.pom.objects.BillingAddress;
import org.selenium.pom.objects.Product;
import org.selenium.pom.objects.User;
import org.selenium.pom.pages.CheckoutPage;
import org.selenium.pom.pages.LoginPage;
import org.selenium.pom.utils.FakerUtils;
import org.selenium.pom.utils.JacksonUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

@Epic("Checkout page")
@Feature("Checkout")
public class CheckoutTest extends BaseTest {

    @Story("Guest checkout")
    @Link("https://example.org")
    @Link(name = "allure", type = "mylink")
    @TmsLink("12345")
    @Issue("1234567")
    @Description("esta es la descripcion del test")
    @Test(description = "Guest checkout using Direct bank transfer")
    public void guestCheckoutUsingDirectBankTransfer() throws IOException {
        CheckoutPage checkoutPage = new CheckoutPage(getDriver());
        checkoutPage.load();
        CartApi cartApi = new CartApi();
        cartApi.addToCart(1215, 1);
        //quiero que injecte la cookie de carrito, sin estar logueado
        injectCookiesToBrowser(cartApi.getCookies());

        checkoutPage.load();

        BillingAddress billingAddress = JacksonUtils.deserializeJson("myBillingAddress.json", BillingAddress.class);
        checkoutPage.setBillingAddress(billingAddress);
        checkoutPage.selectDirectBankTransfer();
        checkoutPage.clickOrderButton();

        Assert.assertEquals(checkoutPage.getMsg(), "Thank you. Your order has been received.");
    }

    @Story("Logged checkout")
    @Test(description = "Login and checkout using Direct bank transfer")
    public void loginAndCheckoutUsingDirectBankTransfer() throws IOException {

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

        CheckoutPage checkoutPage = new CheckoutPage(getDriver());
        checkoutPage.load();
        //quiero que cargue la página de checkout estando ya logueado, por eso primero injecto las cookies de signApi y luego recargo.
        injectCookiesToBrowser(signUpApi.getCookies());
        //recargo la pagina de checkout
        checkoutPage.load();

        BillingAddress billingAddress = JacksonUtils.deserializeJson("myBillingAddress.json", BillingAddress.class);
        checkoutPage.setBillingAddress(billingAddress);
        checkoutPage.selectDirectBankTransfer();
        checkoutPage.clickOrderButton();

        Assert.assertEquals(checkoutPage.getMsg(), "Thank you. Your order has been received.");

    }
}
