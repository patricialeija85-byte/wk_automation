package project.pages;

import common.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Page Object for the Wolters Kluwer About Us page.
 * URL: https://www.wolterskluwer.com/en/about-us
 *
 * All element interactions use WebDriverTools (tools.*).
 */
public class AboutUsPage extends BasePage {

    @FindBy(xpath = "//h1")
    private WebElement pageH1;

    @FindBy(xpath = "//a[contains(@href,'/en/about-us/our-organization') or contains(text(),'Our Organization')]")
    private WebElement ourOrganizationLink;

    @FindBy(xpath = "//a[contains(@href,'/en/about-us/leadership') or contains(text(),'Leadership')]")
    private WebElement leadershipLink;

    @FindBy(xpath = "//a[contains(@href,'/en/investor-relations') or contains(text(),'Investor Relations')]")
    private WebElement investorRelationsLink;

    @FindBy(xpath = "//a[contains(@href,'/en/about-us/sustainability') or contains(text(),'Sustainability')]")
    private WebElement sustainabilityLink;

    public AboutUsPage(WebDriver driver) {
        super(driver);
    }

    /** True when the URL confirms we are on the About Us section. */
    public boolean isAboutUsPageLoaded() {
        return getCurrentUrl().contains("/about-us");
    }

    public boolean isH1Displayed() {
        return tools.isElementVisible(pageH1);
    }

    public String getH1Text() {
        tools.waitForElementVisible(pageH1, DEFAULT_TIMEOUT);
        return pageH1.getText().trim();
    }

    public void clickOurOrganization() {
        tools.waitForElementVisible(ourOrganizationLink, DEFAULT_TIMEOUT);
        tools.clickableClick(ourOrganizationLink);
        tools.waitForLoad();
    }

    public void clickLeadership() {
        tools.waitForElementVisible(leadershipLink, DEFAULT_TIMEOUT);
        tools.clickableClick(leadershipLink);
        tools.waitForLoad();
    }

    public void clickInvestorRelations() {
        tools.waitForElementVisible(investorRelationsLink, DEFAULT_TIMEOUT);
        tools.clickableClick(investorRelationsLink);
        tools.waitForLoad();
    }

    public void clickSustainability() {
        tools.waitForElementVisible(sustainabilityLink, DEFAULT_TIMEOUT);
        tools.clickableClick(sustainabilityLink);
        tools.waitForLoad();
    }

    /** True when a given keyword appears anywhere on the page body. */
    public boolean isKeywordPresentOnPage(String keyword) {
        return tools.isTextPresentInPage(keyword);
    }
}
