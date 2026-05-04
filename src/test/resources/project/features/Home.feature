Feature: Wolters Kluwer Home Page
  As a visitor to the Wolters Kluwer website
  I want the home page to load correctly with all key elements visible
  So that I can trust and navigate the site effectively

  Scenario: Home page loads with correct title
    Given I navigate to the Wolters Kluwer home page
    Then the home page title should contain Wolters Kluwer

  Scenario: Wolters Kluwer logo is visible on the home page
    Given I navigate to the Wolters Kluwer home page
    Then the Wolters Kluwer logo should be visible on the home page

  Scenario: Solutions and Products menu is visible in the header
    Given I navigate to the Wolters Kluwer home page
    Then the Solutions and Products menu should be visible

  Scenario: Navigate to About Us from the home page header
    Given I navigate to the Wolters Kluwer home page
    When I click on About Us in the header
    Then the About Us page should be displayed
    And the About Us page heading should be visible

  Scenario: Navigate to Careers from the home page header
    Given I navigate to the Wolters Kluwer home page
    When I click on Careers in the header
    Then the current URL should contain "careers"

  Scenario: Navigate to Insights from the home page header
    Given I navigate to the Wolters Kluwer home page
    When I click on Insights in the header
    Then the current URL should contain "insights"

  Scenario: Privacy link is visible in the footer
    Given I navigate to the Wolters Kluwer home page
    Then the privacy footer link should be visible

  Scenario: Navigate to Privacy page from the footer
    Given I navigate to the Wolters Kluwer home page
    When I click on the Privacy link in the footer
    Then the current URL should contain "privacy"
