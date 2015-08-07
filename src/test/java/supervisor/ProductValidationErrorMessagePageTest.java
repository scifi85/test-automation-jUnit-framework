package supervisor;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pages.BasePage;
import pages.auth.UserInfoPage;
import pages.ordering.AddProductPage;
import pages.ordering.ItemManagementPage;
import tools.BaseTest;
import tools.ColoredString;

import java.awt.*;

import static org.junit.Assert.assertEquals;

/**
 * This test case is designed for validation of error messages on Add Product page.
 */
public class ProductValidationErrorMessagePageTest extends BaseTest {
    private BasePage basePage;
    private ItemManagementPage itemManagementPage;
    private static final String SUPERVISOR_LOGIN = "supervisor1";
    private static final String SUPERVISOR_PASSWORD = "qwerty";
    private static final String PRODUCT_PRICE_RANGE_VALUE = "1765";
    private static final String PRODUCT_PRICE_CHARACTERS_VALUE = "#4rr";
    static Logger log = LoggerFactory.getLogger(ProductValidationErrorMessagePageTest.class);


    @Before
    public void setUp() {
        //initDataBase("data/productData.xml");
        super.setUp();
        basePage = new BasePage(driver);
        UserInfoPage userInfoPage = basePage.login(SUPERVISOR_LOGIN, SUPERVISOR_PASSWORD);
        itemManagementPage = userInfoPage.selectItemManagementTab();
    }

    @Test
    /**
     * This test verify that when product name field is empty, then after clicking on OK button the following
     * message should appear in red color: "Please enter product name!"
     */
    public void testProductNameErrorMessage() {
        AddProductPage addProductPage = itemManagementPage.goToAddProduct();
        addProductPage.clickOkButton();
        ColoredString actualColoredString = addProductPage.getProductNameErrorMessage();
        String expectedMessage = "Please enter product name!";
        Color expectedColor = Color.red;
        assertEquals(expectedMessage, actualColoredString.getString());
        assertEquals(expectedColor, actualColoredString.getColor());
    }

    @Test
    /**
     * When product price field is empty, then after clicking on OK button the following
     * message should appear in red color: "Please enter product price!"
     */
    public void testProductPriceEmptyErrorMessage() {
        AddProductPage addProductPage = itemManagementPage.goToAddProduct();
        addProductPage.clickOkButton();
        ColoredString actualColoredString = addProductPage.getProductPriceErrorMessage();
        String expectedMessage = "Please enter product price!";
        Color expectedColor = Color.red;
        assertEquals(expectedMessage, actualColoredString.getString());
        assertEquals(expectedColor, actualColoredString.getColor());
    }

    @Test // This test fails! Bug in Jira - https://ssu-jira.softserveinc.com/browse/CHZZZ-14
    /**
     * When entered invalid characters (symbols) on product price field, , then after clicking on OK button
     * the following error message will appear in red color "Please enter only numbers".
     */
    public void testProductPriceCharactersErrorMessage() {

        AddProductPage addProductPage = itemManagementPage.goToAddProduct();
        addProductPage.setProductPriceValue(PRODUCT_PRICE_CHARACTERS_VALUE);
        addProductPage.clickOkButton();
        ColoredString actualColoredString = addProductPage.getProductPriceErrorMessage();
        String expectedMessage = "Please enter only numbers!";
        Color expectedColor = Color.red;
        assertEquals(expectedMessage, actualColoredString.getString());
        assertEquals(expectedColor, actualColoredString.getColor());
    }

    @Test // This test fails! Bug in Jira - https://ssu-jira.softserveinc.com/browse/CHZZZ-15
    /**
     * This test verify that when entered text is >999 or <1 on product price field, then after clicking on OK button
     * the following error message will appear in red color "Please enter price in range of 1-999".
     */
    public void testProductPriceRangeErrorMessage() {
        AddProductPage addProductPage = itemManagementPage.goToAddProduct();
        addProductPage.setProductNameValue("checking");
        addProductPage.setProductPriceValue(PRODUCT_PRICE_RANGE_VALUE);
        addProductPage.clickOkButton();
        ColoredString actualColoredString = addProductPage.getProductPriceErrorMessage();
        String expectedMessage = "Please enter price in range of 1-999!";
        Color expectedColor = Color.red;
        assertEquals(expectedMessage, actualColoredString.getString());
        assertEquals(expectedColor, actualColoredString.getColor());

    }

    @After
    public void tearDown() {
        cleanDataBase();
    }
}
