package pages.ordering;


import org.openqa.selenium.WebDriver;
import pages.BasePage;
import tools.ColoredString;
/**
 * This class describe Add Product Page functionality and provides a way to use it.
 * @author Olya.
 */
public class AddProductPage extends BasePage {

    private static final String PRODUCT_NAME_ERROR_ID_LOCATOR = "productName.errors";
    private static final String PRODUCT_PRICE_ERROR_ID_LOCATOR = "productPrice.errors";
    private static final String PRODUCT_DESCRIPTION_ERROR_ID_LOCATOR = "productDescription.errors";
    private static final String OK_BUTTON_CSS_LOCATOR = "input[type=\"submit\"]";
    private static final String CANCEL_BUTTON_CSS_LOCATOR = "input[type=\"button\"]";
    private static final String PRODUCT_NAME_VALUE_ID_LOCATOR = "name";
    private static final String PRODUCT_DESCRIPTION_VALUE_ID_LOCATOR = "description";
    private static final String PRODUCT_PRICE_VALUE_ID_LOCATOR = "price";
    private final String VALUE_ATTRIBUTE_SELECTOR = "value";
    private final String COLOR_STYLE_SELECTOR = "color";

    public AddProductPage(WebDriver driver) {
        super(driver);

    }

     /**
     * This method clicks on the "Ok" button.
     */
    public void clickOkButton(){
        browser.findElementByCssSelector(OK_BUTTON_CSS_LOCATOR).click();
    }

    /**
     * This method clicks on the "Cancel" button.
     */
    public void clickCancelButton(){
        browser.findElementByCssSelector(CANCEL_BUTTON_CSS_LOCATOR).click();
    }

    /**
     * Sets the value in product name field.
     * @param value the product name value.
     */
    public void setProductNameValue(String value){
        browser.findElementById(PRODUCT_NAME_VALUE_ID_LOCATOR).clear();
        browser.findElementById(PRODUCT_NAME_VALUE_ID_LOCATOR).sendKeys(value);
    }

    /**
     * Sets the value in product description field.
     * @param value the description value.
     */
    public void setProductDescriptionValue (String value){
        browser.findElementById(PRODUCT_DESCRIPTION_VALUE_ID_LOCATOR).clear();
        browser.findElementById(PRODUCT_DESCRIPTION_VALUE_ID_LOCATOR).sendKeys(value);
    }

    /**
     * Sets the value in product price field.
     * @param value the price value.
     */
    public void setProductPriceValue (String value){
        browser.findElementById(PRODUCT_PRICE_VALUE_ID_LOCATOR).clear();
        browser.findElementById(PRODUCT_PRICE_VALUE_ID_LOCATOR).sendKeys(value);
    }

    /**
     * Gets the value in product price field.
     * @return product price value.
     */
    public String getProductPriceValue () {
        return browser.findElementById(PRODUCT_PRICE_VALUE_ID_LOCATOR).getAttribute(VALUE_ATTRIBUTE_SELECTOR);
    }

    /**
     * Gets the value in product description field.
     * @return product description value.
     */
    public String getProductDescriptionValue () {
        return browser.findElementById(PRODUCT_DESCRIPTION_VALUE_ID_LOCATOR).getAttribute(VALUE_ATTRIBUTE_SELECTOR);
    }

    /**
     * Gets the value in product name field.
     * @return product name value.
     */
    public String getProductNameValue () {
        return browser.findElementById(PRODUCT_NAME_VALUE_ID_LOCATOR).getAttribute(VALUE_ATTRIBUTE_SELECTOR);
    }

    /**
     * Returns color and text value of the product name error message.
     * @return {@link tools.ColoredString}
     */
    public ColoredString getProductNameErrorMessage() {
        ColoredString coloredString = new ColoredString();
        coloredString.setString(browser.findElementById(PRODUCT_NAME_ERROR_ID_LOCATOR).getText());
        coloredString.setColorFromString(browser.findElementById(PRODUCT_NAME_ERROR_ID_LOCATOR).getCssValue(COLOR_STYLE_SELECTOR));
        return  coloredString;
    }

    /**
     * Returns color and text value of the product description error message.
     * @return {@link tools.ColoredString}
     */
    public ColoredString getProductDescriptionErrorMessage() {
        ColoredString coloredString = new ColoredString();
        coloredString.setString(browser.findElementById(PRODUCT_DESCRIPTION_ERROR_ID_LOCATOR).getText());
        coloredString.setColorFromString(browser.findElementById(PRODUCT_DESCRIPTION_ERROR_ID_LOCATOR).getCssValue(COLOR_STYLE_SELECTOR));
        return coloredString;
    }

    /**
     * Returns color and text value of the product price error message.
     * @return {@link tools.ColoredString}
     */
    public ColoredString getProductPriceErrorMessage() {
        ColoredString coloredString = new ColoredString();
        coloredString.setString(browser.findElementById(PRODUCT_PRICE_ERROR_ID_LOCATOR).getText());
        coloredString.setColorFromString(browser.findElementById(PRODUCT_PRICE_ERROR_ID_LOCATOR).getCssValue(COLOR_STYLE_SELECTOR));
        return coloredString;
    }
}
