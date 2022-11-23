package org.selenium.pom.api.actions;

import io.qameta.allure.Step;
import io.restassured.http.Cookies;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.selenium.pom.utils.ConfigLoader;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class CartApi {

    private Cookies cookies;

    //para añadir productos sin hacer login
    public CartApi() {
    }

    //para añadir productos después de hacer login
    public CartApi(Cookies cookies) {
        this.cookies = cookies;
    }

    public Cookies getCookies() {
        return this.cookies;
    }

    @Step("add to the cart the product {0} in {1} quantity")
    public Response addToCart(int product_id, int quantiy) {

        Header header = new Header("content-type", "application/x-www-form-urlencoded");
        Headers headers = new Headers(header);
        HashMap<String, Object> formParams = new HashMap<>();
        formParams.put("product_sku", "");
        formParams.put("product_id", product_id);
        formParams.put("quantity", quantiy);

        if (cookies == null) {
            cookies = new Cookies();
        }

        Response response =
                given().
                        baseUri(ConfigLoader.getInstance().getBaseUrl()).
                        headers(headers).
                        formParams(formParams).
                        cookies(cookies).
                        log().all().
                        when().
                        post("/?wc-ajax=add_to_cart").
                        then().
                        log().all().
                        extract().
                        response();

        if (response.getStatusCode() != 200) {
            throw new RuntimeException("Failed to add product : " + product_id + ", HTTP Status Code: " + response.getStatusCode());
        }
        this.cookies = response.getDetailedCookies();
        return response;
    }


}
