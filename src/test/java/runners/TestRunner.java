package runners;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

/**
 * Cucumber JUnit 5 Test Runner.
 *
 * Run all tests:
 *   mvn test
 *
 * Run headless:
 *   mvn test -Dheadless=true
 *
 * Run with Firefox:
 *   mvn test -Dbrowser=firefox
 *
 * Run specific feature:
 *   mvn test -Dcucumber.features="src/test/resources/project/features/Home.feature"
 *
 * Run by tag:
 *   mvn test -Dcucumber.filter.tags="@smoke"
 */

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("project/features")
@ConfigurationParameter(key = "cucumber.glue", value = "common.hooks,project.steps")

@ConfigurationParameter(
        key = "cucumber.plugin",
        value = "pretty, html:target/cucumber-reports/report.html, com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
)
@ConfigurationParameter(key = "cucumber.publish.quiet", value = "true")
@ConfigurationParameter(key = "cucumber.execution.parallel.enabled", value = "false")
public class TestRunner {
}
