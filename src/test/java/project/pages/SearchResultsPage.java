package project.pages;

import common.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Page Object for the Wolters Kluwer Search Results Page.
 * All element interactions use WebDriverTools (tools.*).
 */
public class SearchResultsPage extends BasePage {

    @FindBy(xpath = "//h1 | //h2[contains(@class,'search-title') or contains(text(),'results')]")
    private WebElement searchHeading;

    @FindBy(xpath = "//input[@type='search' or contains(@class,'search-input')]")
    private WebElement searchInputField;

    @FindBy(xpath = "//ul[contains(@class,'search-results')]//li" +
                    " | //div[contains(@class,'search-result')]" +
                    " | //article[contains(@class,'result')]")
    private List<WebElement> resultItems;

    @FindBy(xpath = "//*[contains(text(),'No results') or contains(text(),'no results found')]")
    private WebElement noResultsMessage;

    public SearchResultsPage(WebDriver driver) {
        super(driver);
    }

    /** True when the current URL suggests we are on a search page. */
    public boolean isOnSearchResultsPage() {
        return getCurrentUrl().contains("search") || tools.isElementVisible(searchHeading);
    }

    /** True when at least one result item is rendered. */
    public boolean hasResults() {
        return !resultItems.isEmpty();
    }

    /** Returns the count of result items found on the page. */
    public int getResultCount() {
        return resultItems.size();
    }

    /** True when the "no results" message is displayed. */
    public boolean isNoResultsMessageDisplayed() {
        return tools.isElementVisible(noResultsMessage);
    }

    /** True when the search heading is present and visible. */
    public boolean isSearchHeadingDisplayed() {
        return tools.isElementVisible(searchHeading);
    }

    /** Returns the trimmed text of the search heading. */
    public String getSearchHeadingText() {
        tools.waitForElementVisible(searchHeading, DEFAULT_TIMEOUT);
        return searchHeading.getText().trim();
    }

    /** True when the given text appears anywhere on the page body. */
    public boolean isTextPresentOnPage(String text) {
        return tools.isTextPresentInPage(text);
    }
}
