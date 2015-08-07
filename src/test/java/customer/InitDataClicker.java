package customer;

import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.database.QueryDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import tools.DBUnitConfig;

import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Created by lumberjack85 on 6/21/15.
 * Used for creating an initial data xml
 */
public class InitDataClicker extends DBUnitConfig{
/*    private static WebDriver driver;
    private static final String HOME_PAGE = "http://localhost:8080/OMS/login.htm";
    String LOGIN = "supervisor1";
    String PASS = "qwerty";
    String LOGIN2 = "customer1";*/


    public void initTest() throws Exception{
        InitDataClicker init = new InitDataClicker();
        //init.initProduct();

        Class driverClass = Class.forName("org.hsqldb.jdbcDriver");
        Connection jdbcConnection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/oms2", "root", "root");
        IDatabaseConnection connection = new DatabaseConnection(jdbcConnection);

        System.out.println("connected");

        QueryDataSet partialDataSet = new QueryDataSet(connection);
        System.out.println("query");
        partialDataSet.addTable("Products");
        System.out.println("add1");
        partialDataSet.addTable("Orders");
        partialDataSet.addTable("OrderItems");
        System.out.println("add3");
        FlatXmlDataSet.write(partialDataSet, new FileOutputStream("partial.xml"));
        System.out.println(System.getProperty("user.dir"));
    }


  /*  public  void initProduct() throws Exception{
        System.setProperty("webdriver.chrome.driver", "./OMS_test/chromedriver");
        driver = new ChromeDriver();
        //driver = new FirefoxDriver();

        navigation = new Navigation(driver);
        navigation.goToUrl(HOME_PAGE);
        UserInfoPage userInfoPage = navigation.login(LOGIN, PASS);
        ItemManagementPage itemManagementPage = userInfoPage.selectItemManagementTab();

        AddProductPage addProductPage = new ItemManagementPage(driver).goToAddProduct();
        addProductPage.setProductNameValue("product1");
        addProductPage.setProductDescriptionValue("product description");
        addProductPage.setProductPriceValue("100");
        addProductPage.clickOkButton();
        navigation.logout();

        UserInfoPage userInfoPage2 = navigation.login(LOGIN2, PASS);
        CustomerOrderingPage  customerOrderingPage = userInfoPage2.switchToOrderingPage();
        CustomerCreateOrderPage customerCreateOrderPage = customerOrderingPage.switchToCreatingNewOrderPage();
        CustomerAddProductsToOrderPage customerAddProductsToOrderPage = customerCreateOrderPage.clickAddItemButton();
        customerAddProductsToOrderPage.selectInitProduct();
        CustomerCreateOrderPage customerCreateOrderPage2 = customerAddProductsToOrderPage.clickDoneButton();
        customerCreateOrderPage2.enterPreferableDeliveryDate("28/07/2015");
        customerCreateOrderPage2.selectAssignee("merch1");
        customerCreateOrderPage2.clickSaveButton();
        navigation.logout();

        driver.quit();
    }*/

    public static void main(String[] args) throws Exception{

        /**
         * for Sasha's tests
         */
        InitDataClicker initDataClicker = new InitDataClicker();
        initDataClicker.initTest();
    }


}
