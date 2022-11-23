package org.selenium.pom.tests;

import org.selenium.pom.base.BaseTest;
import org.selenium.pom.pages.StorePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SearchTest extends BaseTest {

    @Test(description = "Search with partial match")
    public void searchWithPartialMatch() {
        StorePage storePage = new StorePage(getDriver());
        storePage.load();
        storePage.enterTextIntoSearchField("Blue");
        storePage.clickSearchButton();

        Assert.assertEquals(storePage.getTitle(), "Search results: “Blue”");
    }

}
