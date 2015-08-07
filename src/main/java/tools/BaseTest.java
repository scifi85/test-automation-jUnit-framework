package tools;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.runner.Description;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.BasePage;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.concurrent.TimeUnit;

/**
 * This class have logic that used in all tests. It is parent class for all tests.
 */

public class BaseTest {

    private DBUnitConfig dbUnitConfig;
    private static final String BASEURL = PropertiesProvider.getProperty("base.url");
    private static final int TIMEOUT = Integer.parseInt(PropertiesProvider.getProperty("time.out"));
    private static Logger LOG = LoggerFactory.getLogger(BaseTest.class);
    protected static WebDriver driver;
    protected BasePage basePage;
    protected static EntityManager em = Persistence.createEntityManagerFactory(
            "persistence").createEntityManager();

    static {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
    }
    private static String webdriverName = PropertiesProvider.getProperty("webdriver.name");

    @BeforeClass
    public static void beforeClassSetUp() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        driver = (WebDriver) Class.forName(webdriverName).newInstance();
    }

    @Before
    public void setUp() {
        driver.manage().timeouts().implicitlyWait(TIMEOUT, TimeUnit.SECONDS);
        driver.get(BASEURL);
        basePage = new BasePage(driver);
    }

    /**
     * This method adds initial data required for testing to the database
     * @param dataFile it is a list of file names with initial data required for testing
     */
    public void initDataBase(String... dataFile) {
        dbUnitConfig = new DBUnitConfig();
        dbUnitConfig.initDataBase(dataFile);
    }

    public DBUnitConfig getDbUnitConfig() {
        return dbUnitConfig;
    }

    public void setDbUnitConfig(DBUnitConfig dbUnitConfig) {
        this.dbUnitConfig = dbUnitConfig;
    }

    /*
    A screenshot rule for failed test.
    Overriding finished method to logout after every test.
    Starting and finished methods writing a log message on the beginning and the end of test.
     */
    @Rule
    public ScreenShotRule screenShootRule = new ScreenShotRule(driver) {
        @Override
        protected void finished(Description description) {
            LOG.info("------" + description.getMethodName() + " finished! ------");
            super.finished(description);
            basePage.logout();
        }

        @Override
        protected void starting(Description description) {
            super.starting(description);
            LOG.info("------" + description.getMethodName() + "------");
        }
    };


    /**
     * Removes data from Data Base with was added by initDataBase method.
     */
    public void cleanDataBase() {
        getDbUnitConfig().tearDown();
    }

    @AfterClass
    public static void afterClassTearDown() {
        driver.quit();
    }
}
