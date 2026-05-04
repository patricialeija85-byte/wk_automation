package project.steps;

import common.steps.Steps;
import common.steps.World;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import project.pages.HomePage;
import project.pages.SearchResultsPage;

/**
 * Step definitions for Search functionality on the Wolters Kluwer website.
 */
public class SearchSteps extends Steps {

    private SearchResultsPage searchResultsPage;

    public SearchSteps(World world) {
        super(world);
    }

    // ── When ──────────────────────────────────────────────────────────────────

    @When("I search for {string}")
    public void iSearchFor(String searchTerm) {
        HomePage homePage = new HomePage(world.driver);
        homePage.performSearch(searchTerm);
        searchResultsPage = new SearchResultsPage(world.driver);
    }

    // ── Then ──────────────────────────────────────────────────────────────────

    @Then("the search results page should be displayed")
    public void theSearchResultsPageShouldBeDisplayed() {
        Assertions.assertTrue(
            searchResultsPage.isOnSearchResultsPage(),
            "Search results page should be displayed. Current URL: " + searchResultsPage.getCurrentUrl()
        );
    }

    @Then("search results should be returned")
    public void searchResultsShouldBeReturned() {
        Assertions.assertTrue(
            searchResultsPage.hasResults(),
            "Expected search results to be present on the page"
        );
    }

    @Then("the no results message should be displayed")
    public void theNoResultsMessageShouldBeDisplayed() {
        Assertions.assertTrue(
            searchResultsPage.isNoResultsMessageDisplayed(),
            "Expected a 'no results' message on the page"
        );
    }

    @Then("the number of search results should be greater than {int}")
    public void theNumberOfResultsShouldBeGreaterThan(int minCount) {
        int actual = searchResultsPage.getResultCount();
        Assertions.assertTrue(
            actual > minCount,
            "Expected more than " + minCount + " results, but found: " + actual
        );
    }

    @Then("the search results page should contain {string}")
    public void theSearchResultsPageShouldContain(String text) {
        Assertions.assertTrue(
            searchResultsPage.isTextPresentOnPage(text),
            "Expected text '" + text + "' to be present on the search results page"
        );
    }
}
