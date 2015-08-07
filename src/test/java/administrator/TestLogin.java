package administrator;

import org.junit.Before;
import org.junit.Test;
import pages.auth.LoginPage;
import pages.auth.UserInfoPage;
import tools.BaseTest;
import tools.Browser;

import static org.junit.Assert.assertEquals;

/**
 * This test case is designed for validation of error messages on Login page and successful login with different roles.
 */
public class TestLogin extends BaseTest {
    private Browser browser;
    private static final String USER_NAME_FOR_CUSTOMER = "customer1";
    private static final String PASSWORD = "qwerty";
    private static final String USER_NAME_FOR_SUPERVISOR = "supervisor1";
    private static final String USER_NAME_FOR_ADMINISTRATOR = "admin1";
    private static final String USER_NAME_FOR_MERCHANDISER = "merch1";
    private static final String WRONG_USER_NAME_FOR_CUSTOMER = "lohghgbh";
    private static final String WRONG_PASSWORD_FOR_CUSTOMER = "qwerty4544";
    private static final String LINK_FOR_ORDERING = "Ordering";
    private static final String LINK_FOR_ADMINISTRATION = "Administration";
    private static final String LINK_FOR_ITEM_MANAGEMENT = "Item Management";

    @Before
    public void setUp() {
        super.setUp();
        browser = new Browser(driver);

    }

    @Test
    public void testLoginAsCustomer() throws Exception {

        LoginPage loginPage = new LoginPage(driver);
        UserInfoPage userInfoPage = loginPage.login(USER_NAME_FOR_CUSTOMER, PASSWORD);

        String elementInfoOnInfoPage = userInfoPage.getBrowser().findElementByTagName("legend").getText();
        assertEquals(elementInfoOnInfoPage, "User Info");

        String linkOnInfoPage = browser.findElementByLinkText(LINK_FOR_ORDERING).getText();
        assertEquals(linkOnInfoPage, "Ordering");
    }

    @Test
    public void testLoginAsSupervisor() throws Exception {

        LoginPage loginPage = new LoginPage(driver);
        UserInfoPage userInfoPage = loginPage.login(USER_NAME_FOR_SUPERVISOR, PASSWORD);

        String elementInfoOnInfoPage = userInfoPage.getBrowser().findElementByTagName("legend").getText();
        assertEquals(elementInfoOnInfoPage, "User Info");

        String linkOnInfoPage = browser.findElementByLinkText(LINK_FOR_ITEM_MANAGEMENT).getText();
        assertEquals(linkOnInfoPage, "Item Management");
    }

    @Test
    public void testLoginAsAdministrator() throws Exception {

        LoginPage loginPage = new LoginPage(driver);
        UserInfoPage userInfoPage = loginPage.login(USER_NAME_FOR_ADMINISTRATOR, PASSWORD);

        String elementInfoOnInfoPage = userInfoPage.getBrowser().findElementByTagName("legend").getText();
        assertEquals(elementInfoOnInfoPage, "User Info");

        String linkOnInfoPage = browser.findElementByLinkText(LINK_FOR_ADMINISTRATION).getText();
        assertEquals(linkOnInfoPage, "Administration");
    }

    @Test
    public void testLoginAsMerchandiser() throws Exception {

        LoginPage loginPage = new LoginPage(driver);
        UserInfoPage userInfoPage = loginPage.login(USER_NAME_FOR_MERCHANDISER, PASSWORD);

        String elementInfoOnInfoPage = userInfoPage.getBrowser().findElementByTagName("legend").getText();
        assertEquals(elementInfoOnInfoPage, "User Info");

        String linkOnInfoPage = browser.findElementByLinkText(LINK_FOR_ORDERING).getText();
        assertEquals(linkOnInfoPage, "Ordering");
    }

    @Test
    public void testWrongLogin() {

        LoginPage login = new LoginPage(driver);
        login.login(WRONG_USER_NAME_FOR_CUSTOMER, PASSWORD);
        assertEquals("Such user does not exist in the system – please try again.", browser.findElementByCssSelector("font").getText());
    }

    @Test
    public void testWrongPassword() {

        LoginPage login = new LoginPage(driver);
        login.login(USER_NAME_FOR_CUSTOMER, WRONG_PASSWORD_FOR_CUSTOMER);
        assertEquals("Password is incorrect – please try again.", browser.findElementByCssSelector("font").getText());
    }

    @Test
    public void testEmptyLogin() {

        LoginPage login = new LoginPage(driver);
        login.login("", PASSWORD);
        assertEquals("Such user does not exist in the system – please try again.", browser.findElementByCssSelector("font").getText());
    }

    @Test
    public void testEmptyPassword() {

        LoginPage login = new LoginPage(driver);
        login.login(USER_NAME_FOR_CUSTOMER, "");
        assertEquals("Password is incorrect – please try again.", browser.findElementByCssSelector("font").getText());
    }

}
