package tools;

import java.io.IOException;
import java.util.Properties;

/**
 * This class reads data from properties files.
 * Provided a single entry point for other classes for getting properties values.
 */
public class PropertiesProvider {
    private static Properties prop;

    static {
        prop = new Properties();
        try {
            prop.load(Thread.currentThread()
                    .getContextClassLoader().getResourceAsStream("db.config.properties"));
            prop.load(Thread.currentThread()
                    .getContextClassLoader().getResourceAsStream("test.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Return property value for property with name "propertyName" .
     *
     * @param propertyName it is the property name.
     * @return property value.
     */
    public static String getProperty(String propertyName) {
        return prop.getProperty(propertyName);
    }
}