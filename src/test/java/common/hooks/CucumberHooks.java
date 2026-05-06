package common.hooks;

import common.config.ConfigReader;
import common.driver.WebDriverFactory;
import common.steps.Steps;
import common.steps.World;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
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
        ConfigReader config = new ConfigReader();
        world.driver.get(config.getUiBaseUrl());
    }

    @Before(value = "@api", order = 0)
    public void setUpAPI() {
        ConfigReader config = new ConfigReader();
        RestAssured.baseURI = config.getApiBaseUrl();
    }


//    @AfterStep
//    public void takeScreenshotAfterEachStep(Scenario scenario) {
//        try {
//            // Capturing the screenshot for every step (Passed or Failed)
//            byte[] screenshot = ((TakesScreenshot) world.driver).getScreenshotAs(OutputType.BYTES);
//
//            // Attaching it to the scenario using Base64 to avoid broken images in Jenkins
//            scenario.attach(screenshot, "image/png", "Step Screenshot");
//
//        } catch (Exception e) {
//            System.err.println("Failed to capture step screenshot: " + e.getMessage());
//        }
//    }

    @AfterStep
    public void takeScreenshotAfterEachStep(Scenario scenario) {
        try {
            // Only attempt screenshot if the driver is NOT null
            if (world.driver != null) {
                byte[] screenshot = ((TakesScreenshot) world.driver).getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot, "image/png", "Step Screenshot");
            }
        } catch (Exception e) {
            System.err.println("Failed to capture step screenshot: " + e.getMessage());
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
