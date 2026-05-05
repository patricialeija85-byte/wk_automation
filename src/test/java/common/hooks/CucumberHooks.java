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
     * Captures a screenshot if a UI or API test fails.
     */
//    @After(order = 1)
//    public void captureScreenshotOnFailure(Scenario scenario) {
//        if (scenario.isFailed() && world.driver != null) {
//            try {
//                byte[] screenshot = ((TakesScreenshot) world.driver).getScreenshotAs(OutputType.BYTES);
//                scenario.attach(screenshot, "image/png", "Failure - " + scenario.getName());
//            } catch (Exception e) {
//                System.err.println("Could not capture screenshot: " + e.getMessage());
//            }
//        }
//    }

//    @After
//    public void tearDown(Scenario scenario) {
//        if (scenario.isFailed()) {
//            // Take screenshot as Base64 string
//            final byte[] screenshot = ((TakesScreenshot) world.driver).getScreenshotAs(OutputType.BYTES);
//            // Attach it to the scenario - Extent Adapter will embed it automatically
//            scenario.attach(screenshot, "image/png", "Screenshot of Failure");
//        }
//    }

//    @After
//    public void tearDown(Scenario scenario) {
//        if (scenario.isFailed()) {
//            try {
//                // 1. Get the driver instance from your configuration/driver class.
//                // Note: Ensure you are using the correct reference (e.g., world.driver or DriverManager.getDriver()).
//                byte[] screenshot = ((TakesScreenshot) world.driver).getScreenshotAs(OutputType.BYTES);
//
//                // 2. Attach the screenshot to the Cucumber scenario.
//                // The Extent Reports adapter will detect this and embed the image directly into the report.
//                scenario.attach(screenshot, "image/png", "Screenshot of Failure");
//
//            } catch (Exception e) {
//                System.err.println("Could not take screenshot: " + e.getMessage());
//            }
//        }
//
//        // Close the driver instance if it is not handled elsewhere in your framework.
//        if (world.driver != null) {
//            world.driver.quit();
//        }
//    }

//    @After(order = 1)
//    public void captureScreenshotOnFailure(Scenario scenario) {
//        if (scenario.isFailed()) {
//            // If it is a UI error (active driver), attach a screenshot
//            if (world.driver != null) {
//                try {
//                    byte[] screenshot = ((TakesScreenshot) world.driver).getScreenshotAs(OutputType.BYTES);
//                    scenario.attach(screenshot, "image/png", "Screenshot of Failure");
//                } catch (Exception e) {
//                    scenario.log("Could not take screenshot: " + e.getMessage());
//                }
//            } else {
//                // If it is an API error, add a log to the Extent report
//                scenario.log("API Scenario Failed - No browser driver active.");
//            }
//        }
//    }


    @AfterStep
    public void takeScreenshotAfterEachStep(Scenario scenario) {
        try {
            // Capturing the screenshot for every step (Passed or Failed)
            byte[] screenshot = ((TakesScreenshot) world.driver).getScreenshotAs(OutputType.BYTES);

            // Attaching it to the scenario using Base64 to avoid broken images in Jenkins
            scenario.attach(screenshot, "image/png", "Step Screenshot");

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
