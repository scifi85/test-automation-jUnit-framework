package supervisor;

import org.junit.Test;
import pages.auth.UserInfoPage;
import pages.ordering.AddProductPage;
import pages.ordering.ItemManagementPage;
import tools.BaseTest;
import tools.TableRow;

import static org.junit.Assert.assertEquals;

/**
 * This test case is designed for testing the Add Product functionality.
 */
public class AddProductTest extends BaseTest {

    private ItemManagementPage itemManagementPage;
    private static final String SUPERVISOR_LOGIN = "supervisor1";
    private static final String SUPERVISOR_PASSWORD = "qwerty";
    private static final String PRODUCT_NAME = "Test Product";
    private static final String PRODUCT_DESCRIPTION = "Function test";
    private static final String PRODUCT_PRICE = "65.0";

    @Test
    /**
     * This test verify that new product with name PRODUCT_NAME, description PRODUCT_DESCRIPTION
     * and price PRODUCT_PRICE is created and is visible in product table on Item Management page.
     */
    public void testAddProducts() throws Exception {
        UserInfoPage userInfoPage = basePage.login(SUPERVISOR_LOGIN, SUPERVISOR_PASSWORD);
        itemManagementPage = userInfoPage.selectItemManagementTab();
        AddProductPage addProductPage = itemManagementPage.goToAddProduct();
        addProductPage.setProductNameValue(PRODUCT_NAME);
        assertEquals(PRODUCT_NAME, addProductPage.getProductNameValue());
        addProductPage.setProductDescriptionValue(PRODUCT_DESCRIPTION);
        assertEquals(PRODUCT_DESCRIPTION, addProductPage.getProductDescriptionValue());
        addProductPage.setProductPriceValue(PRODUCT_PRICE);
        assertEquals(PRODUCT_PRICE, addProductPage.getProductPriceValue());
        addProductPage.clickOkButton();
        TableRow row = itemManagementPage.findProductByNameInTable(PRODUCT_NAME);
        assertEquals(PRODUCT_DESCRIPTION, row.getNthColumnValue(1));
        assertEquals(PRODUCT_PRICE, row.getNthColumnValue(2));
    }
}

