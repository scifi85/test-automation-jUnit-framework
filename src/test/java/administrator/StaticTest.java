package administrator;

import org.junit.Test;
import pages.administration.UsersPage;
import tools.BaseTest;

import static org.junit.Assert.assertEquals;

/**
 * This test case checks labels on Administration page.
 */
public class StaticTest extends BaseTest {
    private static final String ADMIN_NAME = "admin";
    private static final String ADMIN_PASS = "pass";

    @Test
    public void TestLogo() {
        UsersPage userspage = new UsersPage(driver);
        userspage.login(ADMIN_NAME, ADMIN_PASS);
        userspage.goHere();
        assertEquals("Ordering Management System.", userspage.getLogoText());
    }

    @Test
    public void TestPageDescription() {
        UsersPage userspage = new UsersPage(driver);
        userspage.login(ADMIN_NAME, ADMIN_PASS);
        userspage.goHere();
        assertEquals("This page is appointed for creating new and managing existing users", userspage.getDescriptionText());
    }

    @Test
    public void TestFoundUsersText() {
        UsersPage userspage = new UsersPage(driver);
        userspage.login(ADMIN_NAME, ADMIN_PASS);
        userspage.goHere();
        System.out.println(userspage.getFoundUsersText());
        assertEquals("Found users:", userspage.getFoundUsersText());
    }
}