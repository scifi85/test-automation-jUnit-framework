package pages.ordering;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import pages.BasePage;
import tools.TableRow;

import java.util.ArrayList;
import java.util.List;

/**
 * This class describe Item Management Page functionality and provides a way to use it.
 *
 * @author Olya.
 */
public class ItemManagementPage extends BasePage {

    private static final String FILTER_BY_SELECT_BOX_ID_LOCATOR = "field";
    private static final String TABLE_ROW_TAG_NAME_LOCATOR = "tr";
    private static final String TABLE_HEADER_TAG_NAME_LOCATOR = "th";
    private static final String SHOW_ITEM_LINK_X_PATH_LOCATOR = "//div[@id='list']/p/a";
    private static final String ADD_PRODUCT_LINK_TEXT_LOCATOR = "Add Product";
    private static final int DELETE_COLUMN_INDEX = 4;
    private static final String A_TAG_NAME = "a";
    private static final int EDIT_COLUMN_INDEX = 3;
    private static final int NAME_INDEX_NUMBER = 0;


    public ItemManagementPage(WebDriver driver) {
        super(driver);

    }

    /**
     * This method returns the values from drop down list in "Search by" section.
     *
     * @return list which contains the values from drop down list in "Search by" section.
     */
    public List<String> getFilterValues() {
        List<String> values = new ArrayList<String>();
        Select select = new Select(browser.findElementById(FILTER_BY_SELECT_BOX_ID_LOCATOR));
        for (WebElement option : select.getOptions()) {
            values.add(option.getText());
        }
        return values;
    }

    /**
     * This method returns the currently selected value from drop down list in "Search by" section.
     *
     * @return the currently selected value from drop down list in "Search by" section.
     */
    public String getFilterCurrentValue() {
        Select select = new Select(browser.findElementById(FILTER_BY_SELECT_BOX_ID_LOCATOR));
        return select.getFirstSelectedOption().getText();

    }

    /**
     * This method returns names of "Products" table columns.
     *
     * @return list which contains the names of table columns.
     */
    public List<String> getProductTableHeadersNames() {
        List<String> names = new ArrayList<String>();
        List<WebElement> headers = browser.findElementsByTagName(TABLE_HEADER_TAG_NAME_LOCATOR);
        for (WebElement header : headers) {
            names.add(header.getText());
        }
        return names;
    }

    /**
     * This method returns the row count of "Products" table.
     *
     * @return the row count of "Products" table.
     */
    public Integer getProductTableElementSize() {
        List<WebElement> rows = browser.findElementsByTagName(TABLE_ROW_TAG_NAME_LOCATOR);
        return rows.size() - 1;
    }

    /**
     * This method returns the text value of the show item link.
     *
     * @return the text value of the show item link.
     */
    public String getShowItemText() {
        return browser.findElementByXpath(SHOW_ITEM_LINK_X_PATH_LOCATOR).getText();
    }

    /**
     * This method makes a click on the show item link.
     */
    public void clickShowItemLink() {
        browser.findElementByXpath(SHOW_ITEM_LINK_X_PATH_LOCATOR).click();
    }

    /**
     * This method find and returns the row with given product name from "Product" table.
     *
     * @param productName the value you need to find.
     * @return {@link tools.TableRow} when found and null if not found.
     */
    public TableRow findProductByNameInTable(String productName) {
        List<WebElement> rows = browser.findElementsByTagName(TABLE_ROW_TAG_NAME_LOCATOR);
        for (int i = 1; i < rows.size(); i++) {
            WebElement webElement = rows.get(i);

            TableRow tableRow = new TableRow(webElement);
            if (tableRow.getNthColumnValue(NAME_INDEX_NUMBER).equals(productName)) {
                return tableRow;
            }
        }
        return null;
    }

    /**
     * This method clicks on the Add product link.
     *
     * @return {@link pages.ordering.AddProductPage}.
     */
    public AddProductPage goToAddProduct() {
        browser.findElementByLinkText(ADD_PRODUCT_LINK_TEXT_LOCATOR).click();
        return new AddProductPage(browser.getDriver());
    }

    /**
     * Clicks on the Edit link on the product with name productName.
     *
     * @param productName it is the name of the product we need to edit.
     * @return {@link pages.ordering.AddProductPage}.
     */
    public AddProductPage clickEditLinkOnProduct(String productName) {
        findProductByNameInTable(productName).getNthColumnElement(EDIT_COLUMN_INDEX).findElement(By.tagName(A_TAG_NAME)).click();
        return new AddProductPage(browser.getDriver());
    }

    /**
     * Clicks on the Delete link on the product with name productName and confirm deletion.
     *
     * @param productName it is the name of the product we need to delete.
     */
    public void clickDeleteLinkOnProductAndAccept(String productName) {
        findProductByNameInTable(productName).getNthColumnElement(DELETE_COLUMN_INDEX).findElement(By.tagName(A_TAG_NAME)).click();
        browser.alertAccept();
    }

    /**
     * Clicks on the Delete link on the product with name productName and do not confirm deletion.
     *
     * @param productName it is the name of the product we need to delete.
     */
    public void clickDeleteLinkOnProductAndDismiss(String productName) {
        findProductByNameInTable(productName)
                .getNthColumnElement(DELETE_COLUMN_INDEX)
                .findElement(By.tagName(A_TAG_NAME))
                .click();
        browser.alertDismiss();
    }
}


