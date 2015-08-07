package pages.ordering;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import pages.BasePage;

/**
 * This class describe 'Create new order' page (of 'Ordering' tab for Customer) functionality
 * and provides a way to use it.
 * @author Olesia
 *
 */

public class CustomerCreateOrderPage extends BasePage {

	private static final String VALUE_OF_ADD_ITEM_BUTTON = "Add Item"; 
	private static final String VALUE_OF_SAVE_BUTTON = "Save"; 
	private static final String LINK_FOR_ORDERING = "Ordering"; 
	private static final String ID_OF_PREFERABLE_DELIVERY_DATE = "dateDays";
	private static final String ID_OF_ASSIGNEE_DROPDOWN = "assignee";
	
	public CustomerCreateOrderPage(WebDriver driver) {
		super(driver);
	}

    public List<String> getItemFromTableInItemSelection(String tagName) {
		
		List<String> names = new ArrayList<String>();
		List<WebElement> values = browser.findElementById("list").findElements(By.tagName(tagName));
		for (WebElement value : values) {
			names.add(value.getText());
		}
		return names;
	}
	
	public CustomerAddProductsToOrderPage clickAddItemButton() {
		
		browser.findElementByXpath("//input[@type='submit'][@value = '"+VALUE_OF_ADD_ITEM_BUTTON+"']").click(); 
		return new CustomerAddProductsToOrderPage(browser.getDriver());
	}
	
	public String enterPreferableDeliveryDate (String date) {
	
		 browser.findElementById(ID_OF_PREFERABLE_DELIVERY_DATE).sendKeys(date);
		 String enteredDate = browser.findElementById(ID_OF_PREFERABLE_DELIVERY_DATE).getText();
		 return enteredDate;
	}
	/**
	 * Selecting of Merchandiser who will be assigned to the order
	 * @param selectedAssignee
	 * @return value of option (with index = 1) in Assignee drop-down 
	 */
	public String selectAssignee(String selectedAssignee) {
		int index=1;
		WebElement dropDownListBox = browser.findElementById(ID_OF_ASSIGNEE_DROPDOWN);
		Select clickThis = new Select(dropDownListBox);
		clickThis.selectByVisibleText(selectedAssignee);

		List<WebElement> options = clickThis.getOptions();
		//index of option 'merch1' for 'Assignee' drop-down 
		String option = options.get(index).getText();
		return option;
	}
	
	public CustomerOrderingPage switchToOrderingPage() {

		browser.findElementByLinkText(LINK_FOR_ORDERING).click();
		return new CustomerOrderingPage(browser.getDriver());
	}
	
	public CustomerCreateOrderPage clickSaveButton() {
		
		browser.findElementByXpath("//input[@type='submit'][@value = '"+VALUE_OF_SAVE_BUTTON+"']").click(); 
		return new CustomerCreateOrderPage(browser.getDriver());
	}
	
}
