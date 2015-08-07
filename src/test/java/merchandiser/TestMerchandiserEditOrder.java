package merchandiser;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pages.auth.UserInfoPage;
import pages.ordering.MerchandiserOrderingPage;
import tools.BaseTest;

import static org.junit.Assert.assertEquals;

public class TestMerchandiserEditOrder extends BaseTest {

    private static String MERCHANDISER_LOGIN = "merch1";
    private static String MERCHANDISER_PASSWORD = "qwerty";
    private static String TEST_ORDER_NAME = "OrderName12";
    private static String TEST_DELIVERY_DATE = "30/06/2015";
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
    public void testEditOrderChangeDeliveryDate() {
        merchOrderingPage
                .findSearchForOrderByValueLinkAndSenKey(TEST_ORDER_NAME);
        merchOrderingPage.findApplyButtonByXPathLocatorAndClick();
        System.out.println(merchOrderingPage
                .findDeliveryDate1OrderAndGetText());
        merchOrderingPage.findAndClickFirstEditOrder();
        merchOrderingPage
                .findDeliveryDateFieldInEditAndSendNewKey(TEST_DELIVERY_DATE);
        merchOrderingPage.findSaveButtonAndClick();
        assertEquals(TEST_DELIVERY_DATE,
                merchOrderingPage.findDeliveryDate1OrderAndGetText());

    }

    @After
    public void tearDown() {
        cleanDataBase();
    }
}
