package tools;

import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.CompositeDataSet;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

/**
 * Helper class which contains data base configuration and a set of methods for manipulation on the data base.
 */

public class DBUnitConfig {

    private Properties prop;
    protected IDataSet[] beforeData;
    private static final Logger logger = LoggerFactory.getLogger(DBUnitConfig.class);
    private IDatabaseTester tester;
    private final String DB_DRIVER_CLASS = PropertiesProvider.getProperty("db.driver");
    private final String DB_CONNECTION_URL = PropertiesProvider.getProperty("db.url");
    private final String DB_USERNAME = PropertiesProvider.getProperty("db.username");
    private final String DB_PASSWORD = PropertiesProvider.getProperty("db.password");

    /**
     * Reads Xml file and create FlatXmlDataSet.
     *
     * @param fileName it is the name of xml file.
     * @return FlatXmlDataSet
     */
    protected FlatXmlDataSet getDataFromFile(String fileName) {
        try {
            return new FlatXmlDataSetBuilder().build(
                    Thread.currentThread().getContextClassLoader()
                            .getResourceAsStream(fileName));
        } catch (DataSetException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Returns the test dataset.
     */

    protected IDataSet getDataSet() throws Exception {
        return new CompositeDataSet(beforeData);
    }

    /**
     * Returns the database operation executed in test cleanup.
     */

    protected DatabaseOperation getTearDownOperation() throws Exception {
        return DatabaseOperation.DELETE;
    }

    /**
     * Returns the test database connection.
     */

    protected IDatabaseConnection getConnection() throws Exception {
        logger.debug("getConnection() - start");

        final IDatabaseTester databaseTester = getDatabaseTester();
        IDatabaseConnection connection = databaseTester.getConnection();
        // Ensure that users have the possibility to configure the connection's configuration
        setUpDatabaseConfig(connection.getConfig());
        return connection;
    }

    /**
     * Creates a IDatabaseTester for this testCase.
     *
     * @throws Exception
     */
    protected IDatabaseTester newDatabaseTester() throws Exception {
        return new JdbcDatabaseTester(DB_DRIVER_CLASS, DB_CONNECTION_URL, DB_USERNAME, DB_PASSWORD);
    }

    /**
     * Designed to be overridden by subclasses in order to set additional configuration
     * parameters for the {@link IDatabaseConnection}.
     *
     * @param config The settings of the current {@link IDatabaseConnection} to be configured
     */
    protected void setUpDatabaseConfig(DatabaseConfig config) {
        // Designed to be overridden.
    }

    /**
     * Gets the IDatabaseTester for this testCase.
     * If the IDatabaseTester is not set yet, this method calls
     * newDatabaseTester() to obtain a new instance.
     *
     * @throws Exception
     */
    protected IDatabaseTester getDatabaseTester() throws Exception {
        if (this.tester == null) {
            this.tester = newDatabaseTester();
        }
        return this.tester;
    }

    /**
     * Returns the database operation executed in test setup.
     */
    protected DatabaseOperation getSetUpOperation() throws Exception {
        return DatabaseOperation.CLEAN_INSERT;
    }

    /**
     * Adds initial data required for testing to the database.
     *
     * @param dataFile it is a list of file names with initial data required for testing.
     */
    public void initDataBase(String... dataFile) {
        if (dataFile.length != 0) {
            IDataSet[] data = new IDataSet[dataFile.length];
            for (int i = 0; i < dataFile.length; i++) {
                data[i] = getDataFromFile(dataFile[i]);
            }
            beforeData = data;
            setUp();
        } else {
            logger.info("Incorrect use of initDataBase(String ...) method");
        }
    }

    /**
     * Writes initial data for testing to the database.
     */
    public void setUp() {
        try {
            logger.debug("setUp() - start");
            final IDatabaseTester databaseTester = getDatabaseTester();
            databaseTester.setSetUpOperation(getSetUpOperation());
            databaseTester.setDataSet(getDataSet());
            databaseTester.onSetup();
        } catch (Exception e) {
            logger.error("setUp error", e);
        }
    }

    /**
     * Cleans the data base based on the initial data.
     */
    public void tearDown() {
        logger.debug("tearDown() - start");

        try {
            final IDatabaseTester databaseTester = getDatabaseTester();
            databaseTester.setTearDownOperation(getTearDownOperation());
            databaseTester.setDataSet(getDataSet());
            databaseTester.onTearDown();
        } catch (Exception e) {
            logger.error("Error in DatabaseTester", e);
        } finally {
            tester = null;
        }
    }
}



