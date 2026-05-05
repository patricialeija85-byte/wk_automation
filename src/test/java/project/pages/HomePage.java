package project.pages;

import common.config.ConfigReader;
import common.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Page Object for the Wolters Kluwer Home Page.
 * URL: https://www.wolterskluwer.com/en
 *
 * All element interactions are delegated to WebDriverTools (tools.*).
 */
public class HomePage extends BasePage {

    private ConfigReader config = new ConfigReader();
    private final String HOME_URL = config.getUiBaseUrl();

    // ── Header ────────────────────────────────────────────────────────────────

    @FindBy(css = "a.logo, a[href='/en'] img, header a[aria-label*='Wolters']")
    private WebElement wkLogo;

    @FindBy(id ="Main-Menu")
    private WebElement mainMenu;

    @FindBy(xpath = "//button[contains(.,'Solutions') or contains(.,'Products')]")
    private WebElement solutionsMenuButton;

    @FindBy(xpath = "//button[contains(text(),'About Us')]")
    private WebElement aboutUsNavLink;

    @FindBy(xpath = "//nav//a[contains(@href,'/en/insights') or contains(text(),'Insights')]")
    private WebElement insightsNavLink;

    @FindBy(xpath = "//nav//a[contains(@href,'/en/careers') or contains(text(),'Careers')]")
    private WebElement careersNavLink;

    // ── Search ────────────────────────────────────────────────────────────────

    @FindBy(xpath = "//button[contains(@aria-label,'Search') or contains(@class,'search-toggle')]")
    private WebElement searchToggleButton;

    @FindBy(xpath = "//input[@type='search' or @aria-label='Search' or contains(@class,'search-input')]")
    private WebElement searchInput;

    @FindBy(xpath = "//button[@type='submit'][ancestor::*[contains(@class,'search')]]")
    private WebElement searchSubmitButton;

    // ── Solutions menu links (visible after hovering / clicking the menu) ─────

    @FindBy(xpath = "//a[contains(@href,'/en/health') and not(contains(@href,'solutions'))]" +
                    "| //a[contains(text(),'Health') and not(contains(@href,'solutions'))]")
    private WebElement healthMenuLink;

    @FindBy(xpath = "//a[contains(@href,'/en/tax-and-accounting')]" +
                    "| //a[contains(text(),'Tax & Accounting') or contains(text(),'Tax and Accounting')]")
    private WebElement taxAccountingMenuLink;

    @FindBy(xpath = "//a[contains(@href,'/en/compliance')]" +
                    "| //a[contains(text(),'Financial') and contains(text(),'Compliance')]")
    private WebElement financialComplianceMenuLink;

    @FindBy(xpath = "//a[contains(@href,'/en/legal') and not(contains(@href,'solutions'))]" +
                    "| //a[contains(text(),'Legal') and contains(text(),'Regulatory')]")
    private WebElement legalRegulatoryMenuLink;

    @FindBy(xpath = "//a[contains(@href,'/en/esg') or (contains(text(),'Corporate Performance'))]")
    private WebElement corporatePerformanceMenuLink;

    // ── Hero / Banner ─────────────────────────────────────────────────────────

    @FindBy(xpath = "//h1[ancestor::*[contains(@class,'hero') or contains(@class,'banner')]] | //h1[1]")
    private WebElement heroHeading;

    // ── Footer ────────────────────────────────────────────────────────────────

    @FindBy(xpath = "//footer//a[contains(@href,'/en/privacy') or contains(text(),'Privacy')]")
    private WebElement privacyFooterLink;

    @FindBy(xpath = "//footer//a[contains(@href,'/en/sitemap') or contains(text(),'Sitemap')]")
    private WebElement sitemapFooterLink;

    // ── Constructor ───────────────────────────────────────────────────────────

    public HomePage(WebDriver driver) {
        super(driver);
    }

    // ── Navigation actions ────────────────────────────────────────────────────

    /** Open the Wolters Kluwer home page. */
    public void goToHomePage() {
        navigateTo(HOME_URL);
        tools.waitForLoad(); // Ensure page is ready
        driver.manage().window().maximize();
        tools.waitForElementVisible(mainMenu, DEFAULT_TIMEOUT);
    }

    /** Click the Solutions & Products menu button in the header. */
    public void clickSolutionsMenu() {
        tools.waitForElementVisible(solutionsMenuButton, DEFAULT_TIMEOUT);
        tools.clickableClick(solutionsMenuButton);
    }

    /** Navigate to About Us via the header. */
    public void clickAboutUs() {
        tools.waitForElementVisible(aboutUsNavLink, DEFAULT_TIMEOUT);
        tools.clickableClick(aboutUsNavLink);
        tools.waitForLoad();
    }

    /** Navigate to Insights via the header. */
    public void clickInsights() {
        tools.waitForElementVisible(insightsNavLink, DEFAULT_TIMEOUT);
        tools.clickableClick(insightsNavLink);
        tools.waitForLoad();
    }

    /** Navigate to Careers via the header. */
    public void clickCareers() {
        tools.waitForElementVisible(careersNavLink, DEFAULT_TIMEOUT);
        tools.clickableClick(careersNavLink);
        tools.waitForLoad();
    }

    // ── Solutions menu navigation ─────────────────────────────────────────────

    /** Open the solutions menu and click Health. */
    public void navigateToHealth() {
        clickSolutionsMenu();
        tools.waitForElementVisible(healthMenuLink, DEFAULT_TIMEOUT);
        tools.clickableClick(healthMenuLink);
        tools.waitForLoad();
    }

    /** Open the solutions menu and click Tax & Accounting. */
    public void navigateToTaxAndAccounting() {
        clickSolutionsMenu();
        tools.waitForElementVisible(taxAccountingMenuLink, DEFAULT_TIMEOUT);
        tools.clickableClick(taxAccountingMenuLink);
        tools.waitForLoad();
    }

    /** Open the solutions menu and click Financial & Corporate Compliance. */
    public void navigateToFinancialCompliance() {
        clickSolutionsMenu();
        tools.waitForElementVisible(financialComplianceMenuLink, DEFAULT_TIMEOUT);
        tools.clickableClick(financialComplianceMenuLink);
        tools.waitForLoad();
    }

    /** Open the solutions menu and click Legal & Regulatory. */
    public void navigateToLegalRegulatory() {
        clickSolutionsMenu();
        tools.waitForElementVisible(legalRegulatoryMenuLink, DEFAULT_TIMEOUT);
        tools.clickableClick(legalRegulatoryMenuLink);
        tools.waitForLoad();
    }

    /** Open the solutions menu and click Corporate Performance & ESG. */
    public void navigateToCorporatePerformance() {
        clickSolutionsMenu();
        tools.waitForElementVisible(corporatePerformanceMenuLink, DEFAULT_TIMEOUT);
        tools.clickableClick(corporatePerformanceMenuLink);
        tools.waitForLoad();
    }

    // ── Search ────────────────────────────────────────────────────────────────

    /**
     * Open the search bar, type the term and submit.
     *
     * @param searchTerm text to search for
     */
    public void performSearch(String searchTerm) {
        tools.waitForElementVisible(searchToggleButton, DEFAULT_TIMEOUT);
        tools.clickableClick(searchToggleButton);
        tools.waitForElementVisible(searchInput, DEFAULT_TIMEOUT);
        tools.enterData(searchInput, searchTerm);
        tools.clickableClick(searchSubmitButton);
        tools.waitForLoad();
    }

    // ── Footer ────────────────────────────────────────────────────────────────

    /** Click the Privacy link in the footer. */
    public void clickPrivacyFooterLink() {
        tools.scrollToBottom();
        tools.waitForElementVisible(privacyFooterLink, DEFAULT_TIMEOUT);
        tools.clickableClick(privacyFooterLink);
        tools.waitForLoad();
    }

    /** Click the Sitemap link in the footer. */
    public void clickSitemapFooterLink() {
        tools.scrollToBottom();
        tools.waitForElementVisible(sitemapFooterLink, DEFAULT_TIMEOUT);
        tools.clickableClick(sitemapFooterLink);
        tools.waitForLoad();
    }

    // ── Verifications ─────────────────────────────────────────────────────────

    public boolean isLogoDisplayed() {
        return tools.isElementVisible(wkLogo);
    }

    public boolean isSolutionsMenuDisplayed() {
        return tools.isElementVisible(solutionsMenuButton);
    }

    public boolean isHeroHeadingDisplayed() {
        return tools.isElementVisible(heroHeading);
    }

    public String getHeroHeadingText() {
        tools.waitForElementVisible(heroHeading, DEFAULT_TIMEOUT);
        return heroHeading.getText().trim();
    }

    public boolean isPrivacyFooterLinkDisplayed() {
        tools.scrollToBottom();
        return tools.isElementVisible(privacyFooterLink);
    }

    public boolean isSitemapFooterLinkDisplayed() {
        tools.scrollToBottom();
        return tools.isElementVisible(sitemapFooterLink);
    }
}
