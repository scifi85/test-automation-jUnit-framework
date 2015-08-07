/**
 * author: Alexander Melnychuk
 */

package pages.ordering;

import org.openqa.selenium.WebDriver;
import pages.BasePage;
import tools.Browser;

public class AddItemPage extends BasePage {

    public AddItemPage(WebDriver driver) {
        super(driver);
    }

    public EditOrderPage clickDoneButton(){
        browser.findElementByCssSelector("input[value=\"Done\"]").click();
        return new EditOrderPage(browser.getDriver());
    }

    public void clickCancelButton(){
        browser.findElementByCssSelector("input[value=\"Cancel\"]").click();
    }

    public void clickResetButton(){
        browser.findElementByCssSelector("input[value=\"Reset\"]").click();
    }

    public void selectFirstItem(){
        browser.findElementByXpath("//form[@id='selectFrom1']//a").click();
    }

    public void setItemQuantity(String qty){
        browser.findElementByXpath("//form[@id='doneForm']//table//input[@name='quantity']").clear();
        browser.findElementByXpath("//form[@id='doneForm']//table//input[@name='quantity']").sendKeys(qty);
    }



}