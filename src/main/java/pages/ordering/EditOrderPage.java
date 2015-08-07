/**
 * author: Alexander Melnychuk
 */

package pages.ordering;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import pages.BasePage;

public class EditOrderPage extends BasePage {


    public EditOrderPage(WebDriver driver) {
        super(driver);
    }

    public AddItemPage addItemClick(){
        browser.findElementByCssSelector("input[value=\"Add Item\"]").click();
        return new AddItemPage(browser.getDriver()) ;
    }

    public Boolean isAddItem(){
        WebElement element = browser.findElementByCssSelector("input[value=\"Add Item\"]");
        return element!=null ? true:false;
    }

    public void editItemClick(int itemNumber){
        browser.findElementByXpath("//form[@id='edit[" + itemNumber + "']/a").click();
    }

    public void deleteItemClick(int itemNumber){
        browser.findElementByXpath("//form[@id='deleteOrderItem["+itemNumber+"']/a").click();
    }

    public void setOrderNumber(String orderNumQty){
        browser.findElementById("orderNum").clear();
        browser.findElementById("orderNum").sendKeys(orderNumQty);
    }

    public String checkOrderNumber(){
        String value = browser.findElementById("orderNum").getAttribute("value");
        return value;
    }

    public void setPreferableDate(String date){
        browser.findElementById("dateDays").clear();
        browser.findElementById("dateDays").sendKeys(date);
    }

    public String checkPreferableDate(){
        String date = browser.findElementById("dateDays").getAttribute("value");
        return date;
    }

    public void setAssignee(String assignee){
        WebElement e = browser.findElementById("assignee");
        browser.selectByVisibleText(e, assignee);
    }

    public String checkAssignee(){
        Select select = new Select(browser.findElementById("assignee"));
        String option = select.getFirstSelectedOption().getText().replaceAll("\\s", "");
        return option;
    }

    public void setCardType(String cardType){
        WebElement e = browser.findElementById("cardTypes");
        browser.selectByVisibleText(e, cardType);
    }

    public void setCardNumber(String cardNum){
        browser.findElementById("cardNum").clear();
        browser.findElementById("cardNum").sendKeys(cardNum);
    }

    public void setCVV2(String cvv2){
        browser.findElementById("cvv2").clear();
        browser.findElementById("cvv2").sendKeys(cvv2);
    }

    public void setExpiryDate(String month, String year){
        WebElement e = browser.findElementById("newCreditCardMonth");
        browser.selectByVisibleText(e, month);
        WebElement e2 = browser.findElementById("newCreditCardYear");
        browser.selectByVisibleText(e2, year);
    }

    public void setMaestroDate(String date){
        browser.findElementById("startDate2").clear();
        browser.findElementById("startDate2").sendKeys(date);

    }

    public void setMaestroIssue(String issueNum){
        browser.findElementById("issueNumber2").clear();
        browser.findElementById("issueNumber2").sendKeys(issueNum);
    }

    public EditOrderPage clickCancel(){
        browser.findElementByXpath("//form[@id='cancelButton']/input[@value='Cancel']").click();
        return new EditOrderPage(browser.getDriver());
    }

    public EditOrderPage clickSave(){
        browser.findElementById("save").click();
        return new EditOrderPage(browser.getDriver()) ;
    }

    public OrderPage clickOrder(){
        browser.findElementByXpath("//form[@id='orderButton']/input[@value='Order']").click();
        return new OrderPage(browser.getDriver()) ;
    }

}
