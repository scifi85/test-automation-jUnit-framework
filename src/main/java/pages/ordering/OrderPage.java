package pages.ordering;

import org.openqa.selenium.WebDriver;
import pages.BasePage;

public class OrderPage extends BasePage {

    public OrderPage(WebDriver driver) {
        super(driver);
    }

    public EditOrderPage goTo1EditOrder(){
        browser.findElementByXpath("//div[@id='list']/table//tr[2]//a[contains(text(),'Edit')]").click();
        return new EditOrderPage(browser.getDriver());
    }

}
