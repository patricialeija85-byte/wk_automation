package common.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

/**
 * Factory that creates WebDriver instances.
 *
 * System properties:
 *   -Dbrowser=chrome|firefox   (default: chrome)
 *   -Dheadless=true|false      (default: false)
 */
public class WebDriverFactory {

    private final String browser;
    private final boolean headless;

    public WebDriverFactory() {
        this.browser   = System.getProperty("browser", "chrome").toLowerCase();
        this.headless  = Boolean.parseBoolean(System.getProperty("headless", "false"));
    }

    public WebDriver getDriver() {
        switch (browser) {
            case "firefox": return createFirefoxDriver();
            default:        return createChromeDriver();
        }
    }

    private WebDriver createChromeDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions opts = new ChromeOptions();
        if (headless) opts.addArguments("--headless=new");
        opts.addArguments("--start-maximized", "--disable-notifications", "--disable-popup-blocking");
        return new ChromeDriver(opts);
    }
//
//    private WebDriver createChromeDriver() {
//        WebDriverManager.chromedriver().setup();
//        ChromeOptions opts = new ChromeOptions();
//
//        if (headless) {
//            // Use the modern headless mode for better resolution support
//            opts.addArguments("--headless=new");
//        }
//
//        // 1. Force the resolution via arguments
//        opts.addArguments("--window-size=1920,1080");
//        opts.addArguments("--force-device-scale-factor=1");
//
//        // 2. BYPASS AUTOMATION DETECTION (Crucial for Wolters Kluwer)
//        // This hides the "Chrome is being controlled by automated software" flag
//        opts.addArguments("--disable-blink-features=AutomationControlled");
//        opts.setExperimentalOption("excludeSwitches", java.util.Collections.singletonList("enable-automation"));
//        opts.setExperimentalOption("useAutomationExtension", false);
//
//        // 3. Set a real Desktop User-Agent
//        opts.addArguments("--user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/124.0.0.0 Safari/537.36");
//
//        // 4. General stability arguments
//        opts.addArguments("--disable-infobars", "--disable-notifications", "--disable-popup-blocking");
//
//        WebDriver driver = new ChromeDriver(opts);
//
//        // 5. Hard-code the window size into the active driver instance
//        driver.manage().window().setSize(new org.openqa.selenium.Dimension(1920, 1080));
//
//        return driver;
//    }

    private WebDriver createFirefoxDriver() {
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions opts = new FirefoxOptions();
        if (headless) opts.addArguments("--headless");
        opts.addArguments("--start-maximized", "--disable-notifications", "--disable-popup-blocking");
        return new FirefoxDriver(opts);
    }
}
