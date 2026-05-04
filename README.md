# Wolters Kluwer — Selenium / Cucumber / Java Framework

Automated UI test framework for **https://www.wolterskluwer.com/en**

| Technology | Version |
|---|---|
| Java | 11 |
| Selenium | 4.18.1 |
| Cucumber | 7.15.0 |
| JUnit | 5.10.1 |
| Maven | 3.6+ |
| WebDriverManager | 5.7.0 |
| AssertJ | 3.25.1 |

---

## Project Structure

```
src/test/
├── java/
│   ├── common/
│   │   ├── driver/
│   │   │   ├── WebDriverFactory.java     # Creates Chrome / Firefox driver
│   │   │   └── WebDriverTools.java       # Wrapper with all Selenium utilities
│   │   ├── hooks/
│   │   │   └── CucumberHooks.java        # @Before / @After — no tag filter
│   │   ├── pages/
│   │   │   └── BasePage.java             # Base page: holds driver + tools instance
│   │   └── steps/
│   │       ├── Steps.java                # Base class for step defs (World injection)
│   │       └── World.java                # Shared state: WebDriver + baseUrl
│   ├── project/
│   │   ├── pages/
│   │   │   ├── HomePage.java             # Home page — uses tools.* for all actions
│   │   │   ├── SearchResultsPage.java    # Search results page
│   │   │   ├── SolutionsPage.java        # Solutions category pages
│   │   │   └── AboutUsPage.java          # About Us page
│   │   └── steps/
│   │       ├── HomeSteps.java            # Home page step definitions
│   │       ├── SearchSteps.java          # Search step definitions
│   │       └── NavigationSteps.java      # Navigation / verification step defs
│   └── runners/
│       └── TestRunner.java               # JUnit 5 Cucumber runner
└── resources/
    └── project/
        └── features/
            ├── Home.feature              # 8 home page scenarios
            ├── Solutions.feature         # 8 solutions navigation scenarios
            ├── Search.feature            # 9 search scenarios (incl. Scenario Outline)
            └── AboutUs.feature           # 3 About Us scenarios
```

---

## How WebDriverTools is used

Every page object extends `BasePage`, which creates a `WebDriverTools` instance:

```java
// BasePage.java
this.tools = new WebDriverTools(driver);
```

All page methods then call `tools.*` instead of calling Selenium directly:

```java
// Example from HomePage.java
public void clickCareers() {
    tools.waitForElementVisible(careersNavLink, DEFAULT_TIMEOUT);
    tools.clickableClick(careersNavLink);   // retries up to 5x on WebDriverException
    tools.waitForLoad();                    // waits for document.readyState == "complete"
}
```

---

## How to Run

### Prerequisites
- Java 11+
- Maven 3.6+
- Google Chrome installed

### Commands

```bash
# All tests
mvn test

# Headless mode (no browser window)
mvn test -Dheadless=true

# Firefox instead of Chrome
mvn test -Dbrowser=firefox

# Single feature file
mvn test -Dcucumber.features="src/test/resources/project/features/Home.feature"

# By tag
mvn test -Dcucumber.filter.tags="@smoke"
```

### HTML Report

```
target/cucumber-reports/report.html
```

---

## Key Design Decisions

| Decision | Reason |
|---|---|
| `WebDriverTools` in `BasePage` | Single shared wrapper; all pages use `tools.*` consistently |
| No `@setupBrowser` tag | `@Before` runs for **every** scenario automatically |
| Pages instantiated inside step methods | Driver is guaranteed to be initialized by the time any step runs |
| Cucumber PicoContainer for `World` | Clean shared state, no static fields, thread-safe |
| `tools.clickableClick()` | Retries clicks up to 5x — handles transient overlay/animation issues |
| `tools.waitForLoad()` | Waits for `document.readyState == "complete"` after navigation |
