package common.driver;

import org.apache.commons.io.FileUtils;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class WebDriverTools {

    private WebDriver driver;
    private SearchContext searchContext;

    public WebDriverTools(WebDriver driver) {
        this(driver, driver);
    }

    public WebDriverTools(WebDriver driver, SearchContext searchContext) {
        if (driver == null) {
            throw new IllegalArgumentException("WebDriver is NULL");
        }
        this.driver = driver;
        this.searchContext = searchContext;
    }

    /**
     * Wait for Javascript document readyState is complete.
     */
    public void waitForLoad() {
        new WebDriverWait(driver, Duration.ofSeconds(360)).until((ExpectedCondition<Boolean>) wd ->
                ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
    }

    public boolean waitForJStoLoad() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                try {
                    return ((Long) js.executeScript("return jQuery.active") == 0);
                } catch (Exception e) {
                    return true;
                }
            }
        };

        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return js.executeScript("return document.readyState")
                        .toString().equals("complete");
            }
        };

        return wait.until(jQueryLoad) && wait.until(jsLoad);
    }

    public boolean isElementPresent(By by) {
        try {
            searchContext.findElement(by);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean isElementPresent(WebElement element) {
        try {
            element.getLocation();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean isElementPresent(WebElement element, By by) {
        try {
            element.findElement(by);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean isElementVisible(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void waitForElementVisible(final By by, final int maximumSeconds) {
        (new WebDriverWait(driver, Duration.ofSeconds(maximumSeconds))).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver error1) {
                try {
                    return isElementVisible(searchContext.findElement(by));
                } catch (Exception e) {
                    return false;
                }
            }
        });
    }

    public void waitForElementVisible(final WebElement element, final int maximumSeconds) {
        (new WebDriverWait(driver, Duration.ofSeconds(maximumSeconds))).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver error1) {
                return isElementVisible(element);
            }
        });
    }

    public boolean isElementVisible(By by) {
        try {
            return isElementVisible(searchContext.findElement(by));
        } catch (Exception e) {
            return false;
        }
    }

    public WebElement waitForElementPresent(final By by, final int maximumSeconds) {
        (new WebDriverWait(driver, Duration.ofSeconds(maximumSeconds))).until(new ExpectedCondition<WebElement>() {
            @Override
            public WebElement apply(WebDriver error1) {
                return searchContext.findElement(by);
            }
        });
        return searchContext.findElement(by);
    }

    public WebElement waitForElementPresent(final SearchContext mySearchContext, final By by, final int maximumSeconds) {
        (new WebDriverWait(driver, Duration.ofSeconds(maximumSeconds))).until(new ExpectedCondition<WebElement>() {
            @Override
            public WebElement apply(WebDriver error1) {
                return mySearchContext.findElement(by);
            }
        });
        return searchContext.findElement(by);
    }

    public void waitForElementPresent(final WebElement element, final int maximumSeconds) {
        (new WebDriverWait(driver, Duration.ofSeconds(maximumSeconds))).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver error1) {
                return isElementPresent(element);
            }
        });
    }

    public void waitForElementEnabled(final By by, final int maximumSeconds) {
        (new WebDriverWait(driver, Duration.ofSeconds(maximumSeconds))).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver error1) {
                try {
                    return isElementEnabled(searchContext.findElement(by));
                } catch (Exception e) {
                    return false;
                }
            }
        });
    }

    public void waitForElementEnabled(final WebElement element, final int maximumSeconds) {
        (new WebDriverWait(driver, Duration.ofSeconds(maximumSeconds))).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver error1) {
                return isElementEnabled(element);
            }
        });
    }

    public void waitForElementInvisible(final By by, final int maximumSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(maximumSeconds));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    public void waitForElementInvisible(final WebElement element, final int maximumSeconds) {
        (new WebDriverWait(driver, Duration.ofSeconds(maximumSeconds))).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver error1) {
                return !isElementVisible(element);
            }
        });
    }

    public void waitForTextPresent(final String text, final int maximumSeconds) {
        (new WebDriverWait(driver, Duration.ofSeconds(maximumSeconds))).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver error1) {
                WebElement error = searchContext.findElement(By.tagName("body"));
                return error.getText().contains(text);
            }
        });
    }

    public void waitForElementToContainText(final WebElement element, final String text, final int maximumSeconds) {
        (new WebDriverWait(driver, Duration.ofSeconds(maximumSeconds))).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver error1) {
                try {
                    return element.getText().contains(text);
                } catch (Exception e) {
                    return false;
                }
            }
        });
    }

    public WebElement waitForElementClickable(final By by, final int maximumSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(maximumSeconds));
        return wait.until(ExpectedConditions.elementToBeClickable(waitForElementPresent(by, maximumSeconds)));
    }

    public WebElement waitForElementClickable(final WebElement element, final int maximumSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(maximumSeconds));
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public Boolean clickableClick(final By by) {
        WebElement element = waitForElementPresent(by, 5);
        return clickableClick(element);
    }

    public Boolean clickableClick(final WebElement element) {
        try {
            waitForElementEnabled(element, 5);
        } catch (Exception e) {
            Assertions.assertThat(false).as("Unable to find " + element.toString() + " enabled for interaction").isTrue();
        }
        for (int i = 0; i < 5; i++) {
            try {
                element.click();
                return true;
            } catch (WebDriverException e) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e1) {
                    //
                }
                continue;
            }
        }
        return false;
    }

    public boolean isTextPresentInPage(String text) {
        List<WebElement> list = searchContext.findElements(By.xpath("//*[contains(text(),'" + text + "')]"));
        return (list.size() > 0);
    }



    public String getSelectedValue(By dropDown) {
        return getSelectedValue(searchContext.findElement(dropDown));
    }

    public String getSelectedValue(WebElement dropDown) {
        Select select = new Select(dropDown);
        return select.getFirstSelectedOption().getText();
    }

    public String getSelectedValueAttribute(WebElement dropDown) {
        Select select = new Select(dropDown);
        return getValueAttribute(select.getFirstSelectedOption());
    }



    //* Radio buttons //

    public void selectRadioButtonByValue(String radioButtonName, String value) {
        List<WebElement> month1 = searchContext.findElements(By.name(radioButtonName));
        for (WebElement rMonth : month1) {
            if (getValueAttribute(rMonth).equalsIgnoreCase(value)) {
                rMonth.click();
                break;
            }
        }
    }

    public boolean isRadioButtonValueSelected(String radioButtonName, String value) {
        List<WebElement> month1 = searchContext.findElements(By.name(radioButtonName));
        for (WebElement rMonth : month1) {
            if (getValueAttribute(rMonth).equalsIgnoreCase(value) && rMonth.isSelected()) {
                return true;
            }
        }
        return false;
    }

    public void selectRadioButton(String radioButtonName, String attribute, String value) {
        List<WebElement> webElements = searchContext.findElements(By.name(radioButtonName));
        for (WebElement webElement : webElements) {
            if (webElement.getAttribute(attribute).equalsIgnoreCase(value)) {
                webElement.click();
                break;
            }
        }
    }

    public boolean selectRadioButton(WebElement radioButton, String attribute, String value) {
        List<WebElement> webElements = searchContext.findElements(By.name(radioButton.getAttribute("name")));
        for (WebElement webElement : webElements) {
            if (webElement.getAttribute(attribute).equalsIgnoreCase(value)) {
                webElement.click();
                return true;
            }
        }
        return false;
    }

    public boolean isRadioButtonSelected(String radioButtonName, String attribute, String value) {
        List<WebElement> webElements = searchContext.findElements(By.name(radioButtonName));
        for (WebElement webElement : webElements) {
            if (webElement.getAttribute(attribute).equalsIgnoreCase(value) && webElement.isSelected()) {
                return true;
            }
        }
        return false;
    }

    public String getSelectedRadioButtonAttributeValue(String radioButtonName) {
        List<WebElement> webElements = searchContext.findElements(By.name(radioButtonName));
        for (WebElement webElement : webElements) {
            if (webElement.isSelected()) {
                return getValueAttribute(webElement);
            }
        }
        return null;
    }

    public boolean isRadioButtonSelected(WebElement radioButton, String attribute, String value) {
        List<WebElement> webElements = searchContext.findElements(By.name(radioButton.getAttribute("name")));
        for (WebElement webElement : webElements) {
            if (webElement.getAttribute(attribute).equalsIgnoreCase(value) && webElement.isSelected()) {
                return true;
            }
        }
        return false;
    }

    //* Fields //

    public boolean getBooleanFieldValue(String fieldName) {
        WebElement webElement = searchContext.findElement(By.name(fieldName));
        String value = getValueAttribute(webElement);
        return ("true".equalsIgnoreCase(value)) || ("1".equals(value));
    }

    public String getInputFieldValue(String fieldName) {
        WebElement webElement = searchContext.findElement(By.name(fieldName));
        return getInputFieldValue(webElement);
    }

    public String getInputFieldValue(WebElement webElement) {
        return getValueAttribute(webElement);
    }

    public String getValueAttribute(WebElement webElement) {
        return webElement.getAttribute("value");
    }


    //* Checkboxes //

    public void setCheckBox(WebElement element, boolean value) {
        boolean selected = element.isSelected();
        if (value) {
            if (!selected) {
                element.click();
            }
        } else {
            if (selected) {
                element.click();
            }
        }
    }

    public void setCheckBox(By by, boolean value) {
        setCheckBox(waitForElementPresent(by, 5), value);
    }

    public void selectCheckBoxByValue(WebElement checkBox, String value) {
        WebElement checkBoxFound = getCheckBoxByValue(checkBox, value);
        if (checkBoxFound != null && !checkBoxFound.isSelected()) {
            checkBoxFound.click();
        }
    }

    public WebElement getCheckBoxByValue(WebElement checkBox, String value) {
        List<WebElement> elements = searchContext.findElements(By.name(checkBox.getAttribute("name")));
        for (WebElement element : elements) {
            if (getValueAttribute(element).equalsIgnoreCase(value)) {
                return element;
            }
        }
        return null;
    }

    //* Alerts //

    public boolean isAlertPresent() {
        return (getAlertText() == null) ? false : true;
    }

    public String getAlertText() {
        try {
            return driver.switchTo().alert().getText();
        } catch (NoAlertPresentException e) {
            return null;
        }
    }

    public void acceptAlert() {
        try {
            driver.switchTo().alert().accept();
        } catch (NoAlertPresentException e) {
            //
        }
    }

    public void dismissAlert() {
        try {
            driver.switchTo().alert().dismiss();
        } catch (NoAlertPresentException e) {
            //
        }
    }

    //* Dropdowns //

    public void deselectAllDropDownOptions(By dropdown) {
        new Select(searchContext.findElement(dropdown)).deselectAll();
    }

    public void deselectAllDropDownOptions(WebElement dropdown) {
        new Select(dropdown);
    }

    public void selectOptionFromDropdownByIndex(WebElement dropDown, int index) {
        Select select = new Select(dropDown);
        select.selectByIndex(index);
    }

    public void selectOptionFromDropdownByIndex(By dropDown, int index) {
        Select select = new Select(searchContext.findElement(dropDown));
        select.selectByIndex(index);
    }

    public List<String> getAllDropdownOptions(final WebElement dropDown) {
        Select select = new Select(dropDown);
        List<WebElement> elements = select.getOptions();
        return getTextInElements(elements);
    }

    public List<String> getAllDropdownOptionValues(final WebElement dropDown) {
        Select select = new Select(dropDown);
        List<WebElement> elements = select.getOptions();
        List<String> values = new ArrayList<>();
        for (WebElement e : elements) {
            values.add(getValueAttribute(e));
        }
        return values;
    }

    public void selectOptionFromDropdownByValue(By dropDown, String value) {
        selectOptionFromDropdownByValue(searchContext.findElement(dropDown), value);
    }

    public void selectOptionFromDropdownByValue(WebElement dropDown, String value) {
        Select select = new Select(dropDown);
        select.selectByValue(value);
    }

    public void selectOptionFromDropdownByDisplayText(By dropDown, String displayText) {
        selectOptionFromDropdownByDisplayText(searchContext.findElement(dropDown), displayText);
    }

    public void selectFromDropdownByDisplayText(WebElement dropDown, String displayText) {
        Select select = new Select(dropDown);
        select.selectByVisibleText(displayText);
    }

    public void selectOptionFromDropdownByDisplayText(WebElement dropDown, String displayText) {
        selectOptionFromDropdown(dropDown, displayText, true);
    }

    public void selectOptionFromDropdownContainingText(WebElement dropDown, String displayText) {
        selectOptionFromDropdown(dropDown, displayText, false);
    }

    private void selectOptionFromDropdown(WebElement dropDown, String displayText, boolean strict) {
        waitForElementPresent(dropDown, 15);
        List<WebElement> options = dropDown.findElements(By.tagName("option"));

        if ("all".equalsIgnoreCase(displayText)) {
            for (WebElement option : options) {
                option.click();
            }
        } else {
            for (WebElement option : options) {
                String optionText = option.getText().trim();
                if ((strict && optionText.equals(displayText)) || (!strict && optionText.contains(displayText))) {
                    waitForElementPresent(option, 15);
                    option.click();
                    break;
                }
            }
        }
    }

    public void selectOptionFromDropDownByElement(WebElement dropDownElement, String dropDownValue) {
        new Select(dropDownElement).selectByVisibleText(dropDownValue);
    }

    //* Click on Elements //

    public void clickOnElement(final By identifier) {
        WebElement element = (new WebDriverWait(driver, Duration.ofSeconds(20))).until(new ExpectedCondition<WebElement>() {
            @Override
            public WebElement apply(WebDriver d) {
                return searchContext.findElement(identifier);
            }
        });
        element.click();
    }

    public void clickOnElement(WebElement element, int timeOutInSeconds) {
        waitForElementEnabled(element, timeOutInSeconds);
        element.click();
    }

    public boolean isElementEnabled(WebElement button) {
        return button.isEnabled();
    }

    public List<String> getTextInElements(final List<WebElement> elements) {
        List<String> text = new ArrayList<>();
        for (WebElement e : elements) {
            text.add(e.getText());
        }
        return text;
    }


    public boolean isAttributePresent(WebElement element, String attribute) {
        Boolean result = false;
        try {
            String value = element.getAttribute(attribute);
            if (value != null) {
                result = true;
            }
        } catch (Exception e) {
            //
        }
        return result;
    }

    public WebElement getElementByText(String tagName, String label) {
        return searchContext.findElement(By.xpath("//" + tagName + "[contains(text(),'" + label + "')]"));
    }

    public void clickOnElement(String tagName, String label) {
        WebElement element = searchContext.findElement(By.xpath("//" + tagName + "[contains(text(),'" + label + "')]"));
        element.click();
    }

    public void enterDataToInput(WebElement element, String data, int maximumSeconds) {
        waitForElementEnabled(element, maximumSeconds);
        element.clear();
        while (element.getAttribute("value").equals("")) ;
        element.click();
        element.sendKeys(data);
    }

    public void clearTextbox(WebElement element, int maximumSeconds) {
        waitForElementEnabled(element, maximumSeconds);
        element.clear();
    }

    public boolean enterData(WebElement userName, String data) {
        boolean bReturn = false;
        try {
            userName.clear();
            userName.click();
            userName.sendKeys(data);
            bReturn = true;
        } catch (Exception e) {
        }
        return bReturn;
    }

    public boolean enterData(By elem, Keys key) throws IOException, ClassNotFoundException {
        boolean bReturn = false;
        try {
            searchContext.findElement(elem).sendKeys(key);
            bReturn = true;
        } catch (Exception e) {
        }
        return bReturn;
    }

    public List<WebElement> getElementsInList(By elemData) throws IOException, ClassNotFoundException {
        List<WebElement> elemList = null;
        try {
            elemList = searchContext.findElements(elemData);
        } catch (Exception e) {
        }
        return elemList;
    }

    public void rightClick(WebElement element) {
        try {
            Actions action = new Actions(driver).contextClick(element);
            action.build().perform();
        } catch (Exception e) {
        }
    }

    public void rightClick(By by) {
        try {
            WebElement element = driver.findElement(by);
            Actions action = new Actions(driver).contextClick(element);
            action.build().perform();
        } catch (Exception e) {
        }
    }

    public void scrollToMiddle(WebElement element) {
        String scrollElementIntoMiddle = "var viewPortHeight = Math.max(document.documentElement.clientHeight, window.innerHeight || 0);" +
                "var elementTop = arguments[0].getBoundingClientRect().top;" +
                "window.scrollBy(0, elementTop-(viewPortHeight/2));";
        ((JavascriptExecutor) driver).executeScript(scrollElementIntoMiddle, element);
    }

    public void scrollIntoView(WebElement element) {
        String scrollElementIntoView = "arguments[0].scrollIntoView()";
        ((JavascriptExecutor) driver).executeScript(scrollElementIntoView, element);
    }

    public void scrollIntoView(By by) {
        WebElement element = driver.findElement(by);
        String scrollElementIntoView = "arguments[0].scrollIntoView()";
        ((JavascriptExecutor) driver).executeScript(scrollElementIntoView, element);
    }

    public void scrollToCenterView(WebElement element) {
        String scrollElementIntoMiddle = "arguments[0].scrollIntoView({block: 'center', inline: 'nearest'})";
        ((JavascriptExecutor) driver).executeScript(scrollElementIntoMiddle, element);
    }

    public void scrollToBottom() {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    public void mouseOver(WebElement element) {
        Actions builder = new Actions(driver);
        builder.moveToElement(element);
        builder.build().perform();
    }

    public void mouseOver(By by) {
        WebElement element = driver.findElement(by);
        Actions builder = new Actions(driver);
        builder.moveToElement(element);
        builder.build().perform();
    }

    public boolean switchToTab(String url) {
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        for (String tab : tabs) {
            driver.switchTo().window(tab);
            String currentUrl = driver.getCurrentUrl();
            if (currentUrl.contains(url))
                return true;
        }
        Assertions.assertThat(false).as("Unable to find tab with " + url + " URL").isTrue();
        return false;
    }


    public void waitNMilliseconds(int n) {
        try {
            TimeUnit.MILLISECONDS.sleep(n);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void highlightElementGreen(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('style','border:4px solid green;')", element);
    }

    public void highlightElementRed(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('style','border:4px solid red;')", element);
    }

    public void clickOnAllElements(By by) {
        try {
            List<WebElement> elements = driver.findElements(by);
            for (WebElement element : elements) {
                element.click();
            }
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
    }

    public void highlightElementGreen(By by) {
        WebElement element = driver.findElement(by);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('style','border:4px solid green;')", element);
    }

    public void retryClickOnElement(WebElement element, By by) {
        int attempts = 0;
        while (attempts < 5) {
            try {
                clickOnElement(element, 30);
                break;
            } catch (StaleElementReferenceException e) {
                element = driver.findElement(by);
            } catch (Exception e) {
                e.printStackTrace();
                waitNMilliseconds(5000);
            }
            attempts++;
        }
    }
}
