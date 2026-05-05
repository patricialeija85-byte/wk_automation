package common.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private Properties properties;

    public ConfigReader() {
        // Retrieves 'env' from Maven command line: mvn test -Denv=test
        // Defaults to 'test' if not specified
        String env = System.getProperty("env", "test");
        String configPath = "src/test/resources/environments/" + env + ".properties";

        try (FileInputStream fis = new FileInputStream(configPath)) {
            properties = new Properties();
            properties.load(fis);
        } catch (IOException e) {
            throw new RuntimeException("Environment configuration file not found at: " + configPath);
        }
    }

    public String getUiBaseUrl() {
        return properties.getProperty("base.url.ui"); // Matches your .properties file
    }

    public String getApiBaseUrl() {
        return properties.getProperty("base.url.api"); // Matches your .properties file
    }

//    public String getEnvName() {
//        return properties.getProperty("env.name");
//    }
}