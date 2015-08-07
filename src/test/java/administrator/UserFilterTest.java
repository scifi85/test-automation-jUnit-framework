package administrator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pages.administration.UsersPage;
import pages.auth.LoginPage;
import pages.auth.UserInfoPage;
import tools.BaseTest;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;


@RunWith(Parameterized.class)
public class UserFilterTest extends BaseTest {
    private String column;
    private String match;
    private String value;
    private int expected;

    private static final String ADMIN_NAME = "admin";
    private static final String ADMIN_PASS = "pass";


    public UserFilterTest(String column, String match, String value, int expected) throws Exception {
        this.column = column;
        this.match = match;
        this.value = value;
        this.expected = expected;
    }

    /**
     * Three parameters: column to match, match type, value;
     * expected: number of found results, taken from "FoundUsers" field.
     */
    @Parameterized.Parameters
    public static Collection Filters() {
        return Arrays.asList(new Object[][]{
                {"All Columns", "equals", "", 5},

                {"All Columns", "equals", "Nelly", 2},
                {"All Columns", "not equals to", "Nelly", 3},
                {"All Columns", "starts with", "Ne", 2},
                {"All Columns", "contains", "B", 1},
                {"All Columns", "does not contain", "B", 4},

                {"First Name", "equals", "Nelly", 2},
                {"First Name", "not equals to", "Nelly", 3},
                {"First Name", "starts with", "Ne", 2},
                {"First Name", "contains", "A", 2},
                {"First Name", "does not contain", "A", 3},

                {"Last Name", "equals", "Lee", 1},
                {"Last Name", "not equals to", "Lee", 4},
                {"Last Name", "starts with", "M", 2},
                {"Last Name", "contains", "e", 3},
                {"Last Name", "does not contain", "e", 2},

                {"Login", "equals", "admin", 1},
                {"Login", "not equals to", "admin", 4},
                {"Login", "starts with", "ad", 2},
                {"Login", "contains", "1", 4},
                {"Login", "does not contain", "1", 1},

                {"Role", "equals", "Administrator", 2},
                {"Role", "not equals to", "Administrator", 3},
                {"Role", "starts with", "A", 2},
                {"Role", "contains", "or", 3},
                {"Role", "does not contain", "or", 2},

                {"Region", "equals", "North", 3},
                {"Region", "not equals to", "North", 2},
                {"Region", "starts with", "N", 3},
                {"Region", "contains", "o", 4},
                {"Region", "does not contain", "o", 1},

        });
    }


    /**
     * login, goto Users Page
     * <p/>
     * Then matching count of users from "Found Users" with expected value
     */
    @Test
    public void testUserFilter() {
        LoginPage loginpage = new LoginPage(driver);
        UserInfoPage userinfopage = loginpage.login(ADMIN_NAME, ADMIN_PASS);
        UsersPage userpage = userinfopage.gotoUsers();

        userpage.setFilter(this.column, this.match, this.value);

        int actual = userpage.getFoundUsers();
        assertEquals(expected, actual);
    }
}