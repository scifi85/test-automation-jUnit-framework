package supervisor;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pages.auth.UserInfoPage;
import pages.ordering.ItemManagementPage;
import tools.BaseTest;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertNull;

/**
 * This test case is designed for testing the Delete Product functionality.
 */
public class DeleteProductTest extends BaseTest {
    private ItemManagementPage itemManagementPage;
    private static final String SUPERVISOR_LOGIN = "supervisor1";
    private static final String SUPERVISOR_PASSWORD = "qwerty";
    private static final String PRODUCT_NAME_DELETE = "lemon";

    @Before
    public void setUp() {
        initDataBase("data/productData.xml");
        super.setUp();
    }


    @Test
    public void testDeleteProduct() {
        UserInfoPage userInfoPage = basePage.login(SUPERVISOR_LOGIN, SUPERVISOR_PASSWORD);
        itemManagementPage = userInfoPage.selectItemManagementTab();
        itemManagementPage.clickDeleteLinkOnProductAndDismiss(PRODUCT_NAME_DELETE);
        assertNotNull(itemManagementPage.findProductByNameInTable(PRODUCT_NAME_DELETE));
        itemManagementPage.clickDeleteLinkOnProductAndAccept(PRODUCT_NAME_DELETE);
        assertNull(itemManagementPage.findProductByNameInTable(PRODUCT_NAME_DELETE));
    }

    @After
    public void tearDown() {
        cleanDataBase();
    }
}