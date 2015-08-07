package supervisor;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pages.BasePage;
import pages.auth.UserInfoPage;
import pages.ordering.AddProductPage;
import pages.ordering.ItemManagementPage;
import tools.BaseTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * This test case is designed for testing of the Item Management view.
 */
public class SupervisorMainPageTest extends BaseTest {
    private ItemManagementPage itemManagementPage;
    private static final String SUPERVISOR_LOGIN = "supervisor1";
    private static final String SUPERVISOR_PASSWORD = "qwerty";


    @Before
    public void setUp() {
        initDataBase("data/productData.xml");
        super.setUp();
        basePage = new BasePage(driver);
        UserInfoPage userInfoPage = basePage.login(SUPERVISOR_LOGIN, SUPERVISOR_PASSWORD);
        itemManagementPage = userInfoPage.selectItemManagementTab();
    }

    @Test
    /**
     * This test verify that "Search by" drop-down list on ItemManagement page
     * have two options – "Name" and "Description" (according to SRS).
     */
    public void testFilterByValues() throws Exception {
        List<String> expectedValues = new ArrayList<String>();
        expectedValues.add("Name");
        expectedValues.add("Description");
        List<String> actualValues = itemManagementPage.getFilterValues();
        assertEquals(expectedValues.size(), actualValues.size());
        for (String actualValue : actualValues) {
            assertTrue(expectedValues.contains(actualValue));
        }
    }

    @Test
    /**
     * This test verify that in "Search by" drop-down list on ItemManagement page
     * by default set value to "Name" (according to SRS).
     */
    public void testFilterByDefaultValue() throws Exception {
        String expected = "Name";
        String actual = itemManagementPage.getFilterCurrentValue();
        assertEquals(expected, actual);
    }

    @Test
    /**
     * This test verify that columns of product table on Item Management page have
     * such labels: "Name", "Description", "Price", "Edit" and "Delete" (according to SRS).
     */
    public void testProductTableHeaders() throws Exception {
        List<String> expected = Arrays.asList("Name", "Description", "Price", "Edit", "Delete");
        List<String> actual = itemManagementPage.getProductTableHeadersNames();
        assertEquals(expected.size(), actual.size());
        for (String actualName : actual) {
            assertTrue(expected.contains(actualName));
        }
    }

    @Test
    /**
     * This test verify a correct work of "Show 10 items/Show 5 items" links (according to SRS).
     */
    public void testClickShowItems() throws Exception {
        String expectedShowItemsLabel = "Show 10 items";
        int expectedNumberOfProducts = 5;
        String actualShowItemsLabel = itemManagementPage.getShowItemText();
        int actualNumberOfProduct = itemManagementPage.getProductTableElementSize();
        assertEquals(expectedShowItemsLabel, actualShowItemsLabel);
        assertEquals(expectedNumberOfProducts, actualNumberOfProduct);

        itemManagementPage.clickShowItemLink();
        expectedShowItemsLabel = "Show 5 items";
        expectedNumberOfProducts = 10;
        actualShowItemsLabel = itemManagementPage.getShowItemText();
        actualNumberOfProduct = itemManagementPage.getProductTableElementSize();
        assertEquals(expectedShowItemsLabel, actualShowItemsLabel);
        assertEquals(expectedNumberOfProducts, actualNumberOfProduct);

        itemManagementPage.clickShowItemLink();
        expectedShowItemsLabel = "Show 10 items";
        expectedNumberOfProducts = 5;
        actualShowItemsLabel = itemManagementPage.getShowItemText();
        actualNumberOfProduct = itemManagementPage.getProductTableElementSize();
        assertEquals(expectedShowItemsLabel, actualShowItemsLabel);
        assertEquals(expectedNumberOfProducts, actualNumberOfProduct);
    }

    @Test
    /**
     * This test verify that after clicking on AddProduct link Supervisor can access "Add Product" page.
     */
    public void testClickAddProduct() throws Exception {
        AddProductPage addProductPage = itemManagementPage.goToAddProduct();
        assertNotNull(addProductPage);
    }


    @After
    public void tearDown() {
        cleanDataBase();
    }
}