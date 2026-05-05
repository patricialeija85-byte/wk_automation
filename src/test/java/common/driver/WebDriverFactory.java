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

//    private WebDriver createChromeDriver() {
//        WebDriverManager.chromedriver().setup();
//        ChromeOptions opts = new ChromeOptions();
//        if (headless) opts.addArguments("--headless=new");
//        opts.addArguments("--start-maximized", "--disable-notifications", "--disable-popup-blocking");
//        return new ChromeDriver(opts);
//    }

    private WebDriver createChromeDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions opts = new ChromeOptions();

        if (headless) {
            opts.addArguments("--headless=new");
        }

        opts.addArguments("--window-size=1920,1080");
        opts.addArguments("--force-device-scale-factor=1");
        opts.addArguments("--start-maximized", "--disable-notifications", "--disable-popup-blocking");

        WebDriver driver = new ChromeDriver(opts);

        // ADD THIS LINE: This forces the size directly into the driver instance
        driver.manage().window().setSize(new org.openqa.selenium.Dimension(1920, 1080));

        return driver;
    }

    private WebDriver createFirefoxDriver() {
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions opts = new FirefoxOptions();
        if (headless) opts.addArguments("--headless");
        return new FirefoxDriver(opts);
    }
}
