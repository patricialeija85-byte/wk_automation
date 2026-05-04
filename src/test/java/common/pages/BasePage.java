package common.pages;

import common.driver.WebDriverTools;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * Base class for all Page Objects.
 *
 * Every page object receives a WebDriver and immediately creates a
 * WebDriverTools instance that is used for ALL element interactions.
 * Direct use of WebDriver inside page methods is intentionally avoided;
 * use the `tools` wrapper instead.
 */
public abstract class BasePage {

    protected WebDriver driver;
    protected WebDriverTools tools;

    /** Default explicit-wait timeout used across all page methods. */
    protected static final int DEFAULT_TIMEOUT = 15;

    public BasePage(WebDriver driver) {
        if (driver == null) {
            throw new IllegalArgumentException("WebDriver cannot be null");
        }
        this.driver = driver;
        this.tools  = new WebDriverTools(driver);
        PageFactory.initElements(driver, this);
    }

    /** Navigate the browser to the given URL. */
    public void navigateTo(String url) {
        driver.get(url);
    }

    /** Return the current browser page title. */
    public String getPageTitle() {
        return driver.getTitle();
    }

    /** Return the current browser URL. */
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}
