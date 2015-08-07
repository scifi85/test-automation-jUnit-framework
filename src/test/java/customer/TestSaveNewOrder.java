package customer;

import java.util.ArrayList;
import java.util.List;

import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import entity.OrderItem;
import pages.BasePage;
import pages.auth.LoginPage;
import pages.auth.UserInfoPage;
import pages.ordering.CustomerAddProductsToOrderPage;
import pages.ordering.CustomerCreateOrderPage;
import pages.ordering.CustomerOrderingPage;
import tools.BaseTest;
import tools.DBUnitConfig;
import tools.OrderItemService;
import static org.junit.Assert.assertEquals;

/**
 * This test case is designed for testing (step by step) the Save Order functionality.
 * @author Olesia
 *
 */
public class TestSaveNewOrder extends BaseTest {
	private static final String USER_NAME_FOR_CUSTOMER = "customer1";
	private static final String PASSWORD_FOR_CUSTOMER = "qwerty";
	private static final String SELECTED_ASSIGNEE = "merch1";
	private static final String ENTERED_PREFERABLE_DELIVERY_DATE = "10/07/2015";
	String productName = "product1";
	String productDescription = "product description";
	String productPrice = "100.0";
	CustomerOrderingPage ordering;
	
	 @Before
	    public void setUp() {
			initDataBase("data/initProduct.xml");
			super.setUp();
			basePage = new BasePage(driver);
	        UserInfoPage userInfoPage = basePage.login(USER_NAME_FOR_CUSTOMER, PASSWORD_FOR_CUSTOMER);
	        ordering = userInfoPage.switchToOrderingPage();
		}

	/*@Before
	public void setUp() throws Exception {	

		IDataSet orderData;
		try {
			orderData = getDataFromFile("data/initProduct.xml");
			beforeData = new IDataSet[] {orderData};
		} catch (DataSetException e) {
			
			e.printStackTrace();
		}
        		
		try {
			super.setUp();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
				
		LoginPage loginPage = new LoginPage(driver);
		UserInfoPage userInfo = loginPage.login(USER_NAME_FOR_CUSTOMER, PASSWORD_FOR_CUSTOMER);
		ordering = userInfo.switchToOrderingPage();		
	}*/

	@Test
	public void testSwitchToOrderingPage(){
		
		List <String> expectedValues = new ArrayList<String>();
		expectedValues.add("Order Name");
		expectedValues.add("Total price");
		expectedValues.add("Max Discount");
		expectedValues.add("Delivery date");
		expectedValues.add("Status");
		expectedValues.add("Assignee");
		expectedValues.add("Edit");
		expectedValues.add("Delete");				
		List <String> actualValues =  ordering.getValuesFromTableWithOrders("th");
	    assertEquals(expectedValues, actualValues);
	}
	
	@Test
	public void testSwitchToCreatingNewOrderPage(){
		
		CustomerCreateOrderPage createNewOrderPage = ordering.switchToCreatingNewOrderPage();
		List<String> expectedValues = new ArrayList<String>();
		expectedValues.add("Item Number");
		expectedValues.add("Item Name");
		expectedValues.add("Item Description");
		expectedValues.add("Dimension");
		expectedValues.add("Price");
		expectedValues.add("Quantity");
		expectedValues.add("Price per Line");
		expectedValues.add("Edit");
		expectedValues.add("Delete");
		List <String> actualValues =  createNewOrderPage.getItemFromTableInItemSelection("th");
		assertEquals(expectedValues, actualValues);
	}

	@Test
	public void testClickAddItemButton(){
		
		CustomerCreateOrderPage createNewOrderPage = ordering.switchToCreatingNewOrderPage();
		CustomerAddProductsToOrderPage addProductsPage = createNewOrderPage.clickAddItemButton();
		List<String> expectedValues = new ArrayList<String>();
		expectedValues.add("Item Name");
		expectedValues.add("Item Description");
		expectedValues.add("Add");	
		List <String> actualValues =  addProductsPage.getHeadersFromTableWithProducts();
	    assertEquals(expectedValues, actualValues);
	}
	
  @Test   
	public void testSelectProduct() {
	  
		CustomerCreateOrderPage createNewOrderPage = ordering.switchToCreatingNewOrderPage();
		CustomerAddProductsToOrderPage addProductsPage = createNewOrderPage.clickAddItemButton();
		addProductsPage.selectInitProduct();
		String actualName = addProductsPage.findNameOfSelectedProduct();
		assertEquals(productName, actualName);
		String actualPrice = addProductsPage.findPriceOfSelectedProduct();		
		assertEquals(productPrice, actualPrice);
	}

	@Test
	public void testClickDoneButton() { 
		
		CustomerCreateOrderPage createNewOrderPage = ordering.switchToCreatingNewOrderPage();
		CustomerAddProductsToOrderPage addProductsPage = createNewOrderPage.clickAddItemButton();
		addProductsPage.selectInitProduct();
		CustomerCreateOrderPage result = addProductsPage.clickDoneButton();
		List<String> expectedValues = new ArrayList<String>();
		expectedValues.add("1");
		expectedValues.add(productName);
		expectedValues.add(productDescription);
		expectedValues.add("Item");
		expectedValues.add(productPrice);
		expectedValues.add("1");
		expectedValues.add("100.0");
		expectedValues.add("Edit");
		expectedValues.add("Delete");	
		List <String> actualValues =  result.getItemFromTableInItemSelection("td");				
	   	assertEquals(expectedValues, actualValues);	   	

	   	}
	
	@Test
	public void testSelectAssignee() { 
		CustomerCreateOrderPage createNewOrderPage = ordering.switchToCreatingNewOrderPage();
		CustomerAddProductsToOrderPage addProductsPage = createNewOrderPage.clickAddItemButton();
		addProductsPage.selectInitProduct();
		addProductsPage.clickDoneButton();
		String result = createNewOrderPage.selectAssignee(SELECTED_ASSIGNEE);
		assertEquals("merch1",result);
	}
	
	@Test
	public void testClickSaveButton() { 
    	CustomerCreateOrderPage createNewOrderPage = ordering.switchToCreatingNewOrderPage();
		CustomerAddProductsToOrderPage addProductsPage = createNewOrderPage.clickAddItemButton();
		addProductsPage.selectInitProduct();
		addProductsPage.clickDoneButton();
		createNewOrderPage.enterPreferableDeliveryDate(ENTERED_PREFERABLE_DELIVERY_DATE);
		createNewOrderPage.selectAssignee(SELECTED_ASSIGNEE);
		createNewOrderPage.clickSaveButton();
		createNewOrderPage.switchToOrderingPage();
	
		List<String> expectedValues = new ArrayList<String>();
		expectedValues.add("OrderName1");
		expectedValues.add("100.0");
		expectedValues.add("0");
		expectedValues.add("");
		expectedValues.add("Created");  
		expectedValues.add("merch1"); 
		expectedValues.add("Edit");
		expectedValues.add("Delete");
		List <String> actualValues =  ordering.getValuesFromTableWithOrders("td");
	    assertEquals(expectedValues, actualValues);	
	}
	
	@After
	public void tearDown() {
		cleanDataBase();
	}
	
/*	@After
	public void tearDown() { 

		try{

			List<OrderItem>  orderItems = OrderItemService.getAll(); 
					
		  		for(OrderItem orderItem : orderItems){ 
				OrderItemService.delete(orderItem);			    	
			}
		    }
			catch (Exception e ){	
				System.out.println(e.getMessage());
			}
		
		try {
			DatabaseOperation.DELETE.execute(getConnection(), getDataSet());
		} catch 
			(Exception e ){	
				System.out.println(e.getMessage());			
		}		
	}	*/
}
