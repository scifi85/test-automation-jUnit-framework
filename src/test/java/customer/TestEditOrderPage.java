/**
 * author: Alexander Melnychuk
 * This is the test class for testing Edit Order's Save and Order functions.
 */

package customer;

import org.junit.*;
import pages.BasePage;
import pages.auth.UserInfoPage;
import pages.ordering.*;
import tools.BaseTest;
import tools.CheckTableValue;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestEditOrderPage extends BaseTest {
    private OrderPage orderPage;
    private static final String LOGIN = "customer1";
    private static final String PASS = "qwerty";


    @Before
    public  void setUp(){
        initDataBase("data/partial.xml");
        super.setUp();
        basePage = new BasePage(driver);
        UserInfoPage userInfoPage = basePage.login(LOGIN, PASS);
        orderPage = userInfoPage.goToOrderingTab();
    }

    /**
     * Checks if there is editable orders
     */

    @Test
    public  void testEditOrderStatus() throws javax.script.ScriptException{
        EditOrderPage eo = orderPage.goTo1EditOrder();
        assertTrue("Can't edit order", eo.isAddItem() == true);
    }

    /**
     * Checks if added item's quantity is right
     */

    @Test
    public void testAddItem() throws Exception{
        EditOrderPage eo = orderPage.goTo1EditOrder();
        AddItemPage add = eo.addItemClick();
        add.selectFirstItem();
        String itemQuantity = "2";
        add.setItemQuantity(itemQuantity);
        add.clickDoneButton();
        CheckTableValue tableValue = new CheckTableValue(driver);
        assertEquals("Item was added, quantity is wrong", itemQuantity, tableValue.findValue("list", 2, 6));
    }

    /**
     * Checks if order saves properly
     */

    @Test
    public void testSaveButton() throws Exception{
        EditOrderPage editOrderPage = orderPage.goTo1EditOrder();
        String expectedOrderNumber = "100";
        editOrderPage.setOrderNumber(expectedOrderNumber);
        String expectedPreferableDate = "10/05/2015";
        editOrderPage.setPreferableDate(expectedPreferableDate);
        String expectedAssignee = "merch1";
        editOrderPage.setAssignee(expectedAssignee);
        editOrderPage.clickSave();

        assertEquals("Order number is wrong", expectedOrderNumber, editOrderPage.checkOrderNumber());
        assertEquals("Preferable date is wrong", expectedPreferableDate, editOrderPage.checkPreferableDate());
        assertEquals("Assignee value is wrong", expectedAssignee, editOrderPage.checkAssignee());
    }

    /**
     * Checks ordering by using Visa
     */

    @Test
    public void testVisaOrderButton() throws Exception{
        EditOrderPage editOrderPage = orderPage.goTo1EditOrder();
        editOrderPage.setOrderNumber("100");
        editOrderPage.setPreferableDate("10/05/2015");
        editOrderPage.setAssignee("merch1");
        editOrderPage.clickSave();
        editOrderPage.setCardType("Visa");
        editOrderPage.setCardNumber("4323355300847977");
        editOrderPage.setCVV2("111");
        editOrderPage.setExpiryDate("02", "2017");
        editOrderPage.clickOrder();

        CheckTableValue tableValue = new CheckTableValue(driver);
        assertEquals("Order doesn't have ordered status", "Ordered", tableValue.findValue("list", 2, 5));
    }

    /**
     * Checks ordering by using MasterCard
     */

    @Test
    public void testMasterCardOrderButton() throws Exception{
        EditOrderPage editOrderPage = orderPage.goTo1EditOrder();
        editOrderPage.setOrderNumber("101");
        editOrderPage.setPreferableDate("10/05/2015");
        editOrderPage.setAssignee("merch1");
        editOrderPage.clickSave();
        editOrderPage.setCardType("MasterCard");
        editOrderPage.setCardNumber("4323355300847977");
        editOrderPage.setCVV2("111");
        editOrderPage.setExpiryDate("02", "2017");
        editOrderPage.clickOrder();
        Thread.sleep(3000);

        CheckTableValue tableValue = new CheckTableValue(driver);
        assertEquals("Order doesn't have ordered status", "Ordered", tableValue.findValue("list", 2, 5));
    }

    /**
     * Checks ordering by using AmericanExpress
     */

    @Test
    public void testAmericanExpressOrderButton() throws Exception{
        EditOrderPage editOrderPage = orderPage.goTo1EditOrder();
        editOrderPage.setOrderNumber("102");
        editOrderPage.setPreferableDate("10/05/2015");
        editOrderPage.setAssignee("merch1");
        editOrderPage.clickSave();
        editOrderPage.setCardType("American Express");
        editOrderPage.setCardNumber("4323355300847977");
        editOrderPage.setCVV2("111");
        editOrderPage.setExpiryDate("02", "2017");
        editOrderPage.clickOrder();

        CheckTableValue tableValue = new CheckTableValue(driver);
        assertEquals("Order doesn't have ordered status", "Ordered", tableValue.findValue("list", 2, 5));
    }

    /**
     * Checks ordering by using Maestro Card
     */

    @Test
    public void testMaestroOrderButton() throws Exception{
        EditOrderPage editOrderPage = orderPage.goTo1EditOrder();

        editOrderPage.setOrderNumber("103");
        editOrderPage.setPreferableDate("10/05/2015");
        editOrderPage.setAssignee("merch1");
        editOrderPage.clickSave();
        editOrderPage.setCardType("Maestro");
        editOrderPage.setCardNumber("4323355300847977");
        editOrderPage.setCVV2("111");
        editOrderPage.setExpiryDate("02", "2017");
        editOrderPage.setMaestroDate("10/05/2015");
        editOrderPage.setMaestroIssue("2");
        editOrderPage.clickOrder();

        CheckTableValue tableValue = new CheckTableValue(driver);
        assertEquals("Order doesn't have ordered status", "Ordered", tableValue.findValue("list", 2, 5));
    }


    @After
    public void tearDown() {
        cleanDataBase();
    }


}