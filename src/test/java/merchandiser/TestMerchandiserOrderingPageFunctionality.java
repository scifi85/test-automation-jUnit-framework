package merchandiser;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pages.auth.UserInfoPage;
import pages.ordering.MerchandiserOrderingPage;
import tools.BaseTest;

import static org.junit.Assert.assertEquals;

public class TestMerchandiserOrderingPageFunctionality extends BaseTest {

    private static String MERCHANDISER_LOGIN = "merch1";
    private static String MERCHANDISER_PASSWORD = "qwerty";
    private static String TEST_ORDER_NAME = "OrderName11";
    private MerchandiserOrderingPage merchOrderingPage;

    @Before
    public void setUp() {
        initDataBase("data/testOrdersDataForMerchTemp.xml");
        super.setUp();
        UserInfoPage userInfoPage = basePage.login(MERCHANDISER_LOGIN,
                MERCHANDISER_PASSWORD);
        merchOrderingPage = userInfoPage.selectOrderingTabByMerchandiser();
    }


    @Test
    /**
     * This text verify if we click on link "Show 10 items", ten orders will be shown on ordering page.
     * Name of this link will change from "Show 10 items" to "Show 5 items".
     * Click on "Show 5 items" will show 5 orders on ordering page
     */
    public void testOrderingShowItems() {
        assertEquals("Show 10 items",
                merchOrderingPage.findShow10ItemsLinkByXpathAndGetText());
        merchOrderingPage.findShowItemsLinkByXPathAndClick();
        assertEquals("2",
                merchOrderingPage.findPageCountLinkByXPathLocatorAndGetText());
        merchOrderingPage.findShowItemsLinkByXPathAndClick();
        assertEquals("3",
                merchOrderingPage.findPageCountLinkByXPathLocatorAndGetText());

    }

    @Test
    public void testSearchOrderByOrderName() throws Exception {
        merchOrderingPage
                .findSearchForOrderByValueLinkAndSenKey(TEST_ORDER_NAME);
        merchOrderingPage.findApplyButtonByXPathLocatorAndClick();
        assertEquals(TEST_ORDER_NAME,
                merchOrderingPage.getFoundedOrderNameText());
    }

    @After
    public void tearDown() {
        cleanDataBase();
    }

}
