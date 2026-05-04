package project.steps;

import common.steps.Steps;
import common.steps.World;
import io.cucumber.java.en.Then;
import org.junit.jupiter.api.Assertions;
import project.pages.AboutUsPage;
import project.pages.SolutionsPage;

/**
 * Step definitions for navigation verification across Wolters Kluwer pages.
 */
public class NavigationSteps extends Steps {

    public NavigationSteps(World world) {
        super(world);
    }

    // ── Solutions pages ───────────────────────────────────────────────────────

    @Then("a solutions category page should be displayed")
    public void aSolutionsCategoryPageShouldBeDisplayed() {
        SolutionsPage page = new SolutionsPage(world.driver);
        Assertions.assertTrue(
            page.isOnSolutionsPage(),
            "Expected a solutions page URL, but got: " + page.getCurrentUrl()
        );
    }

    @Then("the solutions page heading should be visible")
    public void theSolutionsPageHeadingShouldBeVisible() {
        SolutionsPage page = new SolutionsPage(world.driver);
        Assertions.assertTrue(page.isH1Displayed(), "Solutions page H1 should be visible");
    }

    @Then("the solutions page should mention {string}")
    public void theSolutionsPageShouldMention(String productName) {
        SolutionsPage page = new SolutionsPage(world.driver);
        Assertions.assertTrue(
            page.isProductMentioned(productName),
            "Expected '" + productName + "' to appear on the solutions page"
        );
    }

    @Then("the page title should contain {string}")
    public void thePageTitleShouldContain(String expected) {
        SolutionsPage page = new SolutionsPage(world.driver);
        String title = page.getPageTitle();
        Assertions.assertTrue(
            title.toLowerCase().contains(expected.toLowerCase()),
            "Page title should contain '" + expected + "', but was: " + title
        );
    }

    // ── About Us page ─────────────────────────────────────────────────────────

    @Then("the About Us page should be displayed")
    public void theAboutUsPageShouldBeDisplayed() {
        AboutUsPage page = new AboutUsPage(world.driver);
        Assertions.assertTrue(
            page.isAboutUsPageLoaded(),
            "Expected About Us page URL, but got: " + page.getCurrentUrl()
        );
    }

    @Then("the About Us page heading should be visible")
    public void theAboutUsPageHeadingShouldBeVisible() {
        AboutUsPage page = new AboutUsPage(world.driver);
        Assertions.assertTrue(page.isH1Displayed(), "About Us H1 heading should be visible");
    }

    @Then("the About Us page should contain the keyword {string}")
    public void theAboutUsPageShouldContainKeyword(String keyword) {
        AboutUsPage page = new AboutUsPage(world.driver);
        Assertions.assertTrue(
            page.isKeywordPresentOnPage(keyword),
            "Expected keyword '" + keyword + "' on About Us page"
        );
    }
}
