package merchandiser;

import org.junit.Before;
import org.junit.Test;
import pages.BasePage;
import pages.auth.UserInfoPage;
import pages.ordering.MerchandiserOrderingPage;
import tools.BaseTest;

import static org.junit.Assert.assertEquals;

/**
 * Testing merchandiser ordering page.
 * This test case was designed to find out if all lines/buttons/checkboxes were created similar to SRS.
 */
public class TestMerchandiserOrderingPage extends BaseTest {

    private static String MERCHANDISER_LOGIN = "merch1";
    private static String MERCHANDISER_PASSWORD = "qwerty";
    private static String HEADER_TEST_FROM_SRS = "Ordering Management System.";
    private static String UNDER_HEADER_TEXT_FROM_SRS = "Simple. Slim. Genius.";
    private static String USER_INFO_TAB_TEXT_FROM_SRS = "User Info";
    private static String ORDERING_TAB_TEXT_FROM_SRS = "Ordering";
    private static String SEARCH_FOR_ORDER_BY_TEXT_FROM_SRS = "Search for order by ";
    private static String COLUMN_ORDERNAME_TEXT_FROM_SRS = "Order Name";
    private static String COLUMN_TOTALPRICE_TEXT_FROM_SRS = "Total Price";
    private static String COLUMN_MAXDISCOUNT_TEXT_FROM_SRS = "MaxDiscount";
    private static String COLUMN_DELIVERYDATE_TEXT_FROM_SRS = "Delivery Date";
    private static String COLUMN_STATUS_TEXT_FROM_SRS = "Status";
    private static String COLUMN_EDIT_TEXT_FROM_SRS = "Edit";
    private static String COLUMN_DELETE_TEXT_FROM_SRS = "Delete";
    private static MerchandiserOrderingPage MERCH_ORDERING_PAGE;

    @Before
    public void setUp() {
        super.setUp();
        basePage = new BasePage(driver);
        UserInfoPage userInfoPage = basePage.login(MERCHANDISER_LOGIN, MERCHANDISER_PASSWORD);
        MERCH_ORDERING_PAGE = userInfoPage.selectOrderingTabByMerchandiser();
    }

    @Test
    public void testHeader() {
        assertEquals(HEADER_TEST_FROM_SRS,
                MERCH_ORDERING_PAGE.findHeaderLinkByTagNameLocatorAndGetText());
    }

    @Test
    public void testUnderHeaderLink() {
        assertEquals(UNDER_HEADER_TEXT_FROM_SRS,
                MERCH_ORDERING_PAGE
                        .findUnderHeaderLinkByTagNameLocatorAndGetText());
    }

    @Test
    public void testUserInfoTabText() {
        assertEquals(USER_INFO_TAB_TEXT_FROM_SRS,
                MERCH_ORDERING_PAGE.findUserInfoTabByXPathLocatorAndGetText());
    }

    @Test
    public void testOrderingTabText() {
        assertEquals(ORDERING_TAB_TEXT_FROM_SRS,
                MERCH_ORDERING_PAGE.findOrderingTabByXPathLocatorAndGetText());
    }

    @Test
    public void testSearchForOrderByText() {
        assertEquals(SEARCH_FOR_ORDER_BY_TEXT_FROM_SRS,
                MERCH_ORDERING_PAGE
                        .findSearhForOrderByLinkByXPathLocatorAndGetText());
    }

    @Test
    public void testOrderNameText() {
        assertEquals(COLUMN_ORDERNAME_TEXT_FROM_SRS,
                MERCH_ORDERING_PAGE.findPageCountLinkByXPathLocatorAndGetText());
    }

    @Test
    public void testTotalPriceText() {
        assertEquals(COLUMN_TOTALPRICE_TEXT_FROM_SRS,
                MERCH_ORDERING_PAGE.getTotalPriceItemText());
    }

    @Test
    public void testMaxDiscountText() {
        assertEquals(COLUMN_MAXDISCOUNT_TEXT_FROM_SRS,
                MERCH_ORDERING_PAGE.getMaxDiscountItemText());
    }

    @Test
    public void testDeliveryDateText() {
        assertEquals(COLUMN_DELIVERYDATE_TEXT_FROM_SRS,
                MERCH_ORDERING_PAGE.getDeliveryDateItemText());
    }

    @Test
    public void testStatusText() {
        assertEquals(COLUMN_STATUS_TEXT_FROM_SRS,
                MERCH_ORDERING_PAGE.getStatusItemText());
    }

    @Test
    public void testEditText() {
        assertEquals(COLUMN_EDIT_TEXT_FROM_SRS,
                MERCH_ORDERING_PAGE.getEditItemText());
    }

    @Test
    public void testDeleteText() {
        assertEquals(COLUMN_DELETE_TEXT_FROM_SRS,
                MERCH_ORDERING_PAGE.getDeleteItemText());
    }

}
