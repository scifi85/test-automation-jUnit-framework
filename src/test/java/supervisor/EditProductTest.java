package supervisor;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pages.BasePage;
import pages.auth.UserInfoPage;
import pages.ordering.AddProductPage;
import pages.ordering.ItemManagementPage;
import tools.BaseTest;
import tools.TableRow;

import static org.junit.Assert.assertEquals;

/**
 * This test case is designed for testing the Edit Product functionality.
 */
public class EditProductTest extends BaseTest {
    private BasePage basePage;
    private ItemManagementPage itemManagementPage;
    private static final String EDIT_PRODUCT_DESCRIPTION = "NoDescription";
    private static final String EDIT_PRODUCT_PRICE = "111.0";
    private static final String SUPERVISOR_LOGIN = "supervisor1";
    private static final String SUPERVISOR_PASSWORD = "qwerty";
    private static final String PRODUCT_NAME = "lemon";
    private static final String EDIT_PRODUCT_NAME = "1NotName";
    private static final String PRODUCT_DESCRIPTION = "Very yellow";
    private static final String PRODUCT_PRICE = "14.0";

    @Before
    public void setUp() {
        initDataBase("data/productData.xml");
        super.setUp();
        basePage = new BasePage(driver);
        UserInfoPage userInfoPage = basePage.login(SUPERVISOR_LOGIN, SUPERVISOR_PASSWORD);
        itemManagementPage = userInfoPage.selectItemManagementTab();
    }

    @Test
    public void testEditName() {
        AddProductPage addProductPage = itemManagementPage.clickEditLinkOnProduct(PRODUCT_NAME);
        addProductPage.setProductNameValue(EDIT_PRODUCT_NAME);
        addProductPage.clickOkButton();
        TableRow row = itemManagementPage.findProductByNameInTable(EDIT_PRODUCT_NAME);
        assertEquals(PRODUCT_DESCRIPTION, row.getNthColumnValue(1));
        assertEquals(PRODUCT_PRICE, row.getNthColumnValue(2));
    }

    @Test
    public void testEditDescription() {
        AddProductPage addProductPage = itemManagementPage.clickEditLinkOnProduct(PRODUCT_NAME);
        addProductPage.setProductDescriptionValue(EDIT_PRODUCT_DESCRIPTION);
        addProductPage.clickOkButton();
        TableRow row = itemManagementPage.findProductByNameInTable(PRODUCT_NAME);
        assertEquals(EDIT_PRODUCT_DESCRIPTION, row.getNthColumnValue(1));
        assertEquals(PRODUCT_PRICE, row.getNthColumnValue(2));
    }

    @Test
    public void testEditPrice() {
        AddProductPage addProductPage = itemManagementPage.clickEditLinkOnProduct(PRODUCT_NAME);
        addProductPage.setProductPriceValue(EDIT_PRODUCT_PRICE);
        addProductPage.clickOkButton();
        TableRow row = itemManagementPage.findProductByNameInTable(PRODUCT_NAME);
        assertEquals(PRODUCT_DESCRIPTION, row.getNthColumnValue(1));
        assertEquals(EDIT_PRODUCT_PRICE, row.getNthColumnValue(2));
    }

    @After
    public void tearDown() {
        cleanDataBase();
    }
}