package common.hooks;

import common.driver.WebDriverFactory;
import common.steps.Steps;
import common.steps.World;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

/**
 * Cucumber lifecycle hooks.
 *
 * @Before (order 0) – creates the WebDriver before every scenario.
 * @After  (order 1) – captures a screenshot on failure.
 * @After  (order 0) – quits the WebDriver after every scenario.
 *
 * No tag filter is applied, so every scenario gets a fresh browser session.
 */
public class CucumberHooks extends Steps {

    public CucumberHooks(World world) {
        super(world);
    }

    @Before(order = 0)
    public void setUp() {
        world.driver = new WebDriverFactory().getDriver();
    }

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

    @After(order = 0)
    public void tearDown() {
        if (world.driver != null) {
            world.driver.quit();
            world.driver = null;
        }
    }
}
