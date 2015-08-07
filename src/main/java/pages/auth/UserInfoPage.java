package pages.auth;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import pages.BasePage;
import pages.administration.UsersPage;
import pages.ordering.CustomerOrderingPage;
import pages.ordering.ItemManagementPage;
import pages.ordering.MerchandiserOrderingPage;
import pages.ordering.OrderPage;

public class UserInfoPage extends BasePage{


    private static final String itemManagementTabLinkTextLocator = "Item Management";
    private static final String USERS_LOCATOR = "Administration";
    private static final String ORDERING = "Ordering";
    private static final String LINK_FOR_ORDERING = "Ordering";  
    private static final String USER_INFO_TEXT_LOCATOR = "legend";


    public UserInfoPage(WebDriver driver) {
        super(driver);
    }

    public ItemManagementPage selectItemManagementTab() {
        browser.findElementByLinkText(itemManagementTabLinkTextLocator).click();
        return PageFactory.initElements(browser.getDriver(), ItemManagementPage.class);
    }
    
    public CustomerOrderingPage switchToOrderingPage() {
		browser.findElementByLinkText(LINK_FOR_ORDERING).click();
		return new CustomerOrderingPage(browser.getDriver());
	}
    
    public MerchandiserOrderingPage selectOrderingTabByMerchandiser() {
		browser.findElementByLinkText(LINK_FOR_ORDERING).click();
		return new MerchandiserOrderingPage(browser.getDriver());
	} 

    public UsersPage gotoUsers() {
        browser.findElementByLinkText(USERS_LOCATOR).click();

        return new UsersPage(browser.getDriver());
    }

    public OrderPage goToOrderingTab(){
        browser.findElementByLinkText(ORDERING).click();
        return new OrderPage(browser.getDriver());
    }
    
     public String findLink(String link){
         return browser.findElementByLinkText(link).getText();
    }
    
    public String findUserInfoText(){
    	return browser.findElementByTagName(USER_INFO_TEXT_LOCATOR).getText();
    }

}
