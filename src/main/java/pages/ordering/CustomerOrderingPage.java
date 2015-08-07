package pages.ordering;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.BasePage;

/**
 * This class describe 'Ordering' page (tab) for Customer functionality
 * and provides a way to use it. 
 * @author Olesia
 *
 */

public class CustomerOrderingPage extends BasePage {

	private static final String LINK_FOR_CREATING_NEW_ORDER = "Create new order"; 
	private static final String LINK_FOR_SHOW_10_ITEMS = "Show 10 items"; 
	private static final String LINK_FOR_SHOW_5_ITEMS = "Show 5 items"; 
	private static final String TAG_NAME_OF_TABLE_ROW = "tr";
	
	public CustomerOrderingPage(WebDriver driver) { 
		
		super(driver);
	}

	public CustomerCreateOrderPage switchToCreatingNewOrderPage() {
		
		browser.findElementByLinkText(LINK_FOR_CREATING_NEW_ORDER).click();		
		return new CustomerCreateOrderPage(browser.getDriver());   
	}
	
	public List<String> getValuesFromTableWithOrders(String tagName) {
		
		List<String> names = new ArrayList<String>();
		List<WebElement> headers = browser.findElementById("list").findElements(By.tagName(tagName));
		for (WebElement header : headers) {
			names.add(header.getText());
		}
		return names;
	}
	
	public void clickShowTenItems(){  
		
		browser.findElementByLinkText(LINK_FOR_SHOW_10_ITEMS).click();	
	}
	
    public void clickShowFiveItems(){  
		
		browser.findElementByLinkText(LINK_FOR_SHOW_5_ITEMS).click();	
	}
	
    public boolean findShowTenItemsLink(){     
		
		 if (browser.findElementByLinkText(LINK_FOR_SHOW_10_ITEMS).isDisplayed()) return true ;
				 else return false;
	}
    
    public boolean findShowFiveItemsLink(){     
		
		 if (browser.findElementByLinkText(LINK_FOR_SHOW_5_ITEMS).isDisplayed()) return true ;
				 else return false;
	}
    
    public int getCountOfOrdersInTable() { 
    
    		List<WebElement> rows = browser.findElementsByTagName(TAG_NAME_OF_TABLE_ROW); 
   		 return rows.size() - 2;
    }
    
  }
