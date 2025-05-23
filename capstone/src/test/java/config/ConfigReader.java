package config;
import java.io.FileInputStream;
import java.util.Properties;

import org.apache.log4j.PropertyConfigurator;

public class ConfigReader {
    static Properties prop;

    public static String get(String key) {
        if (prop == null) {
            try {
                FileInputStream fis = new FileInputStream("testData/config.properties");
                prop = new Properties();
                prop.load(fis);
                PropertyConfigurator.configure("testdata/log4j.properties");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return prop.getProperty(key);
    }
}
