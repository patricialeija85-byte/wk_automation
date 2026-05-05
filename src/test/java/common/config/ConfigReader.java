package common.config;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {
    private static Properties properties;

    static {
        try {
            // This reads the -Denv parameter from Jenkins/Maven
            String env = System.getProperty("env", "test");
            String path = "src/test/resources/environments/" + env + ".properties";
            FileInputStream input = new FileInputStream(path);
            properties = new Properties();
            properties.load(input);
            input.close();
        } catch (Exception e) {
            System.err.println("Configuration file not found!");
            e.printStackTrace();
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}