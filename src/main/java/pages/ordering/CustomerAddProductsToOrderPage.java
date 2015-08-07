package pages.ordering;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import pages.BasePage;
import tools.Browser;

/**
 * This class describe 'Add item' page (of 'Ordering' tab for Customer) functionality
 * and provides a way to use it.
 * @author Olesia
 *
 */

public class CustomerAddProductsToOrderPage extends BasePage {

	private static final String DONE_BUTTON_VALUE_LOCATOR  = "Done"; 
	//private static final String QUANTITY_FIELD_ID_LOCATOR = "quantity";

	public CustomerAddProductsToOrderPage(WebDriver driver) { 		
		super(driver);
	}

    public List<String> getHeadersFromTableWithProducts() { 
		
		List<String> names = new ArrayList<String>();
		List<WebElement> headers = browser.findElementById("list").findElements(By.tagName("th"));
		for (WebElement header : headers) {
			names.add(header.getText());
		}
		return names;
	}
   
    public void selectInitProduct() {
		browser.findElementByLinkText("Select").click();
	}
    
    public String findNameOfSelectedProduct(){    
		
    	return browser.findElementByXpath("//form[@id = 'doneForm']/table/tbody/tr[1]/td[2]").getText();
	}
    
    public String findPriceOfSelectedProduct(){    
		
    	return browser.findElementByXpath("//form[@id = 'doneForm']/table/tbody/tr[3]/td[2]").getText();
	}
    
    public CustomerCreateOrderPage clickDoneButton() { 
    	browser.findElementByXpath("//input[@type='submit'][@value = '"+DONE_BUTTON_VALUE_LOCATOR+"']").click();  
    	return new CustomerCreateOrderPage(browser.getDriver());   	
    }
    
}
