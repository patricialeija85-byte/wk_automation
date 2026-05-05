package common.hooks;

import common.config.ConfigReader;
import common.driver.WebDriverFactory;
import common.steps.Steps;
import common.steps.World;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.restassured.RestAssured;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class CucumberHooks extends Steps {

    public CucumberHooks(World world) {
        super(world);
    }

    /**
     * This hook only runs for scenarios tagged with @ui.
     * It initializes the WebDriver for browser-based testing.
     */
    @Before(value = "@ui", order = 0)
    public void setUpUI() {
        world.driver = new WebDriverFactory().getDriver();
    }

    /**
     * This hook only runs for scenarios tagged with @api.
     * It configures RestAssured using settings from the active environment properties.
     */
    @Before(value = "@api", order = 0)
    public void setUpAPI() {
        // Dynamically sets the RestAssured base URI based on the -Denv parameter
        RestAssured.baseURI = ConfigReader.getProperty("base.url.api");
    }

    /**
     * Captures a screenshot if a UI test fails.
     */
    @After(order = 1)
    public void captureScreenshotOnFailure(Scenario scenario) {
        if (scenario.isFailed() && world.driver != null) {
            try {
                byte[] screenshot = ((TakesScreenshot) world.driver).getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot, "image/png", "Failure - " + scenario.getName());
            } catch (Exception e) {
                System.err.println("Could not capture screenshot: " + e.getMessage());
            }
        }
    }

    /**
     * Closes the browser only if a driver was actually instantiated.
     */
    @After(order = 0)
    public void tearDown() {
        if (world.driver != null) {
            world.driver.quit();
            world.driver = null;
        }
    }
}
