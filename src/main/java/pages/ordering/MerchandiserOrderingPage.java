package pages.ordering;

import org.openqa.selenium.WebDriver;
import pages.BasePage;

public class MerchandiserOrderingPage extends BasePage {
	
	public MerchandiserOrderingPage(WebDriver driver) {
		super(driver);
		
	}
	/** Locators for all items on ordering page*/
	private static String headerLinkTagNameLocator = "h1";
	private static String underHeaderLinkTagNameLocator = "h2";
	private static String orderingTabXPathLocator = "//*[@id=\"nav\"]/li[1]/a";
	private static String showItemsXPathLocator = "//*[@id=\"searchFilter\"]/p/a";
	private static String pageCountLinkXPathLocator = "//*[@id=\"pageCount\"]";	
	private static String userInfoTabXPathLocator = "//*[@id=\"nav\"]/li[2]/a";
	private static String searchForOrdersByLinkXPathLocator = "//*[@id=\"searchFilter\"]/table/tbody/tr/td[1]";
	private static String searchForOrdersByFilterLinkXPathLocator = "//*[@id=\"search\"]";
	private static String searchForOrderByValueLinkXPathLocator = "//*[@id=\"searchValue\"]";
	private static String applyBottomXPathLocator = "//*[@id=\"searchFilter\"]/table/tbody/tr/td[4]/input";
	private static String orderNameLinkXPathLocator = "//*[@id=\"list\"]/table/tbody/tr[1]/th[1]/a";
	private static String totalPriceLinkXPathLocator = "//*[@id=\"list\"]/table/tbody/tr[1]/th[2]/a";
	private static String maxDiscountLinkXPathLocator = "//*[@id=\"list\"]/table/tbody/tr[1]/th[3]/a";
	private static String deliveryDateLinkXPathLocator = "//*[@id=\"list\"]/table/tbody/tr[1]/th[4]/a";
	private static String statusLinkXPathLocator = "//*[@id=\"list\"]/table/tbody/tr[1]/th[5]/a";
	private static String editLinkXPathLocator = "//*[@id=\"list\"]/table/tbody/tr[1]/th[6]";
	private static String deleteLinkXPathlocator = "//*[@id=\"list\"]/table/tbody/tr[1]/th[7]";
	private static String orderNameColumnFirstElementXPathLocator = "//*[@id=\"list\"]/table/tbody/tr[2]/td[1]";
	private static String editOrder1LinkXPathLocator = "//*[@id=\"list\"]/table/tbody/tr[2]/td[6]/a";
	private static String deliveryDateFieldXPathLocator = "//*[@id=\"dateDays\"]";
	private static String saveButtonXPathLocator = "//*[@id=\"edit\"]/div/form[1]/input";
	private static String deliveryDateOfTestOrderXPathLocator = "//*[@id=\"list\"]/table/tbody/tr[2]/td[4]";
	
	public void login(){
		
	}
	public void findOrderingTabAndClick(){
		browser.findElementByXpath(orderingTabXPathLocator).click();		
	}
	
	public String findShow10ItemsLinkByXpathAndGetText(){
		return browser.findElementByXpath(showItemsXPathLocator).getText();
	}
	
	public void findShowItemsLinkByXPathAndClick(){
		browser.findElementByXpath(showItemsXPathLocator).click();
	}
	
	public String findPageCountLinkByXPathLocatorAndGetText(){
		return browser.findElementByXpath(pageCountLinkXPathLocator).getText();
	}
	
	public String findHeaderLinkByTagNameLocatorAndGetText(){
		return browser.findElementByTagName(headerLinkTagNameLocator).getText();
	}
	
	public String findUnderHeaderLinkByTagNameLocatorAndGetText(){
		return browser.findElementByTagName(underHeaderLinkTagNameLocator).getText();
	}
	
	public String findUserInfoTabByXPathLocatorAndGetText(){
		return browser.findElementByXpath(userInfoTabXPathLocator).getText();
	}
	
	public String findOrderingTabByXPathLocatorAndGetText(){
		return browser.findElementByXpath(orderingTabXPathLocator).getText();
	}
	//for testing Search for order by line
	public String findSearhForOrderByLinkByXPathLocatorAndGetText(){
		return browser.findElementByXpath(searchForOrdersByLinkXPathLocator).getText();
	}
	
	public void findSearchForOrderByFilterLinkByXPathLocator(){
		browser.findElementByXpath(searchForOrdersByFilterLinkXPathLocator);
	}
	
	public void findSearchForOrderByValueLinkByXPathLocator(){
		browser.findElementByXpath(searchForOrderByValueLinkXPathLocator);
	}
	
	public void findSearchForOrderByValueLinkAndSenKey(String orderName){
		browser.findElementByXpath(searchForOrderByValueLinkXPathLocator).clear();
		browser.findElementByXpath(searchForOrderByValueLinkXPathLocator).sendKeys(orderName);
	}
	
	
	public void findApplyButtonByXPathLocator(){
		browser.findElementByXpath(applyBottomXPathLocator);
	}
	
	public void findApplyButtonByXPathLocatorAndClick(){
		browser.findElementByXpath(applyBottomXPathLocator).click();
	}
	
	public String findApplyButtonByXPathLocatorAndGetText(){
		return browser.findElementByXpath(applyBottomXPathLocator).getText();
	}
	//For testing OrderName column
	public void findOrderNameLinkByXPathLocator(){
		browser.findElementByXpath(orderNameLinkXPathLocator);
	}
	
	public void findOrderNameLinkByXPathLocatorAndClick(){
		browser.findElementByXpath(orderNameLinkXPathLocator).click();
	}
	
	public String getOrderNameItemText(){
		return browser.findElementByXpath(orderNameLinkXPathLocator).getText();
	}
	
	//For testing TotalPrice column	
	public void findTotalPriceLinkByXPathLocator(){
		browser.findElementByXpath(totalPriceLinkXPathLocator);
	}
	
	public void findTotalPriceLinkByXPathLocatorAndClick(){
		browser.findElementByXpath(totalPriceLinkXPathLocator).click();
	}
	
	public String getTotalPriceItemText(){
		return browser.findElementByXpath(totalPriceLinkXPathLocator).getText();
	}
	
	//For testing MaxDiscount column
	public void findMaxDiscountLinkByXPathLocator(){
		browser.findElementByXpath(maxDiscountLinkXPathLocator);
	}
	
	public void findMaxDiscountLinkByXPathLocatorAndClick(){
		browser.findElementByXpath(maxDiscountLinkXPathLocator).click();
	}
	
	public String getMaxDiscountItemText(){
		return browser.findElementByXpath(maxDiscountLinkXPathLocator).getText();
	}
	
	//For testing DeliveryDate column
	public void findDeliveryDateLinkByXPathLocator(){
		browser.findElementByXpath(deliveryDateLinkXPathLocator);
	}
	
	public void findDeliveryDateLinkByXPathLocatorAndClick(){
		browser.findElementByXpath(deliveryDateLinkXPathLocator).click();
	}
	
	public String getDeliveryDateItemText(){
		return browser.findElementByXpath(deliveryDateLinkXPathLocator).getText();
	}
	
	public String findDeliveryDate1OrderAndGetText(){
		return browser.findElementByXpath(deliveryDateOfTestOrderXPathLocator).getText();
	}
	
	public void findDeliveryDateFieldInEditAndSendNewKey(String testDate){
		browser.findElementByXpath(deliveryDateFieldXPathLocator).clear();
		browser.findElementByXpath(deliveryDateFieldXPathLocator).sendKeys(testDate);
	}
	
	//for testing Status column
	public void findStatusLinkByXPathLocator(){
		browser.findElementByXpath(statusLinkXPathLocator);
	}
	
	public void findStatusLinkByXPathLocatorAndClick(){
		browser.findElementByXpath(statusLinkXPathLocator).click();
	}
	
	public String getStatusItemText(){
		return browser.findElementByXpath(statusLinkXPathLocator).getText();
	}
	
	//for testing Edit column
	public String getEditItemText(){
		return browser.findElementByXpath(editLinkXPathLocator).getText();
	}
	
	public void findAndClickFirstEditOrder(){
		browser.findElementByXpath(editOrder1LinkXPathLocator).click();
	}
	
	//for testing Delete column
	public String getDeleteItemText(){
		return browser.findElementByXpath(deleteLinkXPathlocator).getText();
	}
	
	//testSearchforOrderByOrderName
	public String getFoundedOrderNameText(){
		return browser.findElementByXpath(orderNameColumnFirstElementXPathLocator).getText();
	}
	
	
	//testing Edit Order
	public void findSaveButtonAndClick(){
		browser.findElementByXpath(saveButtonXPathLocator).click();
	}
	
	
}
