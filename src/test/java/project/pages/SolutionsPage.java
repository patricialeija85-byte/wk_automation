package project.pages;

import common.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Page Object for Wolters Kluwer solution-category landing pages.
 * Covers: Health, Tax & Accounting, Financial Compliance, Legal & Regulatory,
 *         Corporate Performance & ESG.
 *
 * All element interactions use WebDriverTools (tools.*).
 */
public class SolutionsPage extends BasePage {

    @FindBy(xpath = "//h1")
    private WebElement pageH1;

    @FindBy(xpath = "//a[contains(@href,'/en/solutions/uptodate')]")
    private WebElement upToDateLink;

    @FindBy(xpath = "//a[contains(@href,'/en/solutions/cch-axcess')]")
    private WebElement cchAxcessLink;

    @FindBy(xpath = "//a[contains(@href,'/en/solutions/ct-corporation')]")
    private WebElement ctCorporationLink;

    @FindBy(xpath = "//a[contains(@href,'/en/solutions/cch-tagetik')]")
    private WebElement cchTageticLink;

    @FindBy(xpath = "//a[contains(@href,'/en/solutions/enablon')]")
    private WebElement enablonLink;

    @FindBy(xpath = "//a[contains(@href,'/en/solutions/vitallaw')]")
    private WebElement vitalLawLink;

    @FindBy(xpath = "//div[contains(@class,'card')] | //article | //li[contains(@class,'product')]")
    private List<WebElement> productCards;

    public SolutionsPage(WebDriver driver) {
        super(driver);
    }

    /** True when the URL matches a known solution-category path. */
    public boolean isOnSolutionsPage() {
        String url = getCurrentUrl();
        return url.contains("/health") || url.contains("/tax")
                || url.contains("/compliance") || url.contains("/legal")
                || url.contains("/esg") || url.contains("/solutions");
    }

    public boolean isH1Displayed() {
        return tools.isElementVisible(pageH1);
    }

    public String getH1Text() {
        tools.waitForElementVisible(pageH1, DEFAULT_TIMEOUT);
        return pageH1.getText().trim();
    }

    public int getProductCardCount() {
        return productCards.size();
    }

    public void clickUpToDate() {
        tools.waitForElementVisible(upToDateLink, DEFAULT_TIMEOUT);
        tools.clickableClick(upToDateLink);
        tools.waitForLoad();
    }

    public void clickCCHAxcess() {
        tools.waitForElementVisible(cchAxcessLink, DEFAULT_TIMEOUT);
        tools.clickableClick(cchAxcessLink);
        tools.waitForLoad();
    }

    public void clickCTCorporation() {
        tools.waitForElementVisible(ctCorporationLink, DEFAULT_TIMEOUT);
        tools.clickableClick(ctCorporationLink);
        tools.waitForLoad();
    }

    public void clickEnablon() {
        tools.waitForElementVisible(enablonLink, DEFAULT_TIMEOUT);
        tools.clickableClick(enablonLink);
        tools.waitForLoad();
    }

    public void clickVitalLaw() {
        tools.waitForElementVisible(vitalLawLink, DEFAULT_TIMEOUT);
        tools.clickableClick(vitalLawLink);
        tools.waitForLoad();
    }

    /** True when a specific product name is visible anywhere on the page. */
    public boolean isProductMentioned(String productName) {
        return tools.isTextPresentInPage(productName);
    }
}
