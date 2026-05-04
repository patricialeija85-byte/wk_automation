package common.steps;

import org.openqa.selenium.WebDriver;

/**
 * Shared state object injected into every step definition class by Cucumber PicoContainer.
 * Holds the WebDriver instance and any global test configuration.
 */
public class World {

    public WebDriver driver;
    public static final String BASE_URL = "https://www.wolterskluwer.com/en";

    public World() {
        // Instantiated fresh per scenario by Cucumber PicoContainer
    }
}
