package project.steps;

import common.steps.Steps;
import common.steps.World;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import project.pages.HomePage;

/**
 * Step definitions for the Wolters Kluwer Home page.
 */
public class HomeSteps extends Steps {

    private HomePage homePage;

    public HomeSteps(World world) {
        super(world);
    }

    // ── Given ─────────────────────────────────────────────────────────────────

    @Given("I navigate to the Wolters Kluwer home page")
    public void iNavigateToTheWoltersKluwerHomePage() {
        homePage = new HomePage(world.driver);
        homePage.goToHomePage();
    }

    // ── When ──────────────────────────────────────────────────────────────────

    @When("I click on the Solutions and Products menu")
    public void iClickOnTheSolutionsMenu() {
        homePage = new HomePage(world.driver);
        homePage.clickSolutionsMenu();
    }

    @When("I click on About Us in the header")
    public void iClickOnAboutUsInTheHeader() {
        homePage = new HomePage(world.driver);
        homePage.clickAboutUs();
    }

    @When("I click on Insights in the header")
    public void iClickOnInsightsInTheHeader() {
        homePage = new HomePage(world.driver);
        homePage.clickInsights();
    }

    @When("I click on Careers in the header")
    public void iClickOnCareersInTheHeader() {
        homePage = new HomePage(world.driver);
        homePage.clickCareers();
    }

    @When("I navigate to Health from the solutions menu")
    public void iNavigateToHealth() {
        homePage = new HomePage(world.driver);
        homePage.navigateToHealth();
    }

    @When("I navigate to Tax and Accounting from the solutions menu")
    public void iNavigateToTaxAndAccounting() {
        homePage = new HomePage(world.driver);
        homePage.navigateToTaxAndAccounting();
    }

    @When("I navigate to Financial and Corporate Compliance from the solutions menu")
    public void iNavigateToFinancialCompliance() {
        homePage = new HomePage(world.driver);
        homePage.navigateToFinancialCompliance();
    }

    @When("I navigate to Legal and Regulatory from the solutions menu")
    public void iNavigateToLegalAndRegulatory() {
        homePage = new HomePage(world.driver);
        homePage.navigateToLegalRegulatory();
    }

    @When("I navigate to Corporate Performance and ESG from the solutions menu")
    public void iNavigateToCorporatePerformance() {
        homePage = new HomePage(world.driver);
        homePage.navigateToCorporatePerformance();
    }

    @When("I click on the Privacy link in the footer")
    public void iClickOnPrivacyLink() {
        homePage = new HomePage(world.driver);
        homePage.clickPrivacyFooterLink();
    }

    @When("I click on the Sitemap link in the footer")
    public void iClickOnSitemapLink() {
        homePage = new HomePage(world.driver);
        homePage.clickSitemapFooterLink();
    }

    // ── Then ──────────────────────────────────────────────────────────────────

    @Then("the home page title should contain Wolters Kluwer")
    public void theHomePageTitleShouldContainWoltersKluwer() {
        homePage = new HomePage(world.driver);
        String title = homePage.getPageTitle();
        Assertions.assertTrue(
            title.toLowerCase().contains("wolters kluwer"),
            "Page title should contain 'Wolters Kluwer', but was: " + title
        );
    }

    @Then("the Wolters Kluwer logo should be visible on the home page")
    public void theLogoShouldBeVisible() {
        homePage = new HomePage(world.driver);
        Assertions.assertTrue(homePage.isLogoDisplayed(), "WK logo should be visible");
    }

    @Then("the Solutions and Products menu should be visible")
    public void theSolutionsMenuShouldBeVisible() {
        homePage = new HomePage(world.driver);
        Assertions.assertTrue(homePage.isSolutionsMenuDisplayed(), "Solutions menu should be visible");
    }

    @Then("the current URL should contain {string}")
    public void theCurrentUrlShouldContain(String fragment) {
        homePage = new HomePage(world.driver);
        String url = homePage.getCurrentUrl();
        Assertions.assertTrue(
            url.contains(fragment),
            "URL should contain '" + fragment + "', but was: " + url
        );
    }

    @Then("the privacy footer link should be visible")
    public void thePrivacyFooterLinkShouldBeVisible() {
        homePage = new HomePage(world.driver);
        Assertions.assertTrue(homePage.isPrivacyFooterLinkDisplayed(), "Privacy footer link should be visible");
    }
}
