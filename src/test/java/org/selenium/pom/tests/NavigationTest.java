package org.selenium.pom.tests;

import org.selenium.pom.base.BaseTest;
import org.selenium.pom.pages.HomePage;
import org.selenium.pom.pages.StorePage;
import org.selenium.pom.pages.components.MyHeader;
import org.selenium.pom.pages.components.ProductThumbnail;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NavigationTest extends BaseTest {

    @Test(description = "Navigate from home to store using main menu")
    public void NavigateFromHomeToStoreUsingMainMenu() {
        StorePage storePage = new StorePage(getDriver());
        HomePage homePage = new HomePage(getDriver());
        homePage.load();
        MyHeader myHeader = homePage.getMyHeader();
        myHeader.clickStoreMenuLink();

        Assert.assertEquals(storePage.getTitle(), "Store");
    }
}
