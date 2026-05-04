Feature: Wolters Kluwer Search Functionality
  As a user on the Wolters Kluwer website
  I want to search for specific topics or products
  So that I can quickly find relevant information without navigating menus

  Scenario: Search for UpToDate returns results
    Given I navigate to the Wolters Kluwer home page
    When I search for "UpToDate"
    Then the search results page should be displayed
    And search results should be returned

  Scenario: Search for compliance returns results
    Given I navigate to the Wolters Kluwer home page
    When I search for "compliance"
    Then the search results page should be displayed
    And search results should be returned

  Scenario: Search for tax accounting returns results
    Given I navigate to the Wolters Kluwer home page
    When I search for "tax accounting"
    Then the search results page should be displayed
    And search results should be returned

  Scenario: Search for CCH Tagetik returns relevant results
    Given I navigate to the Wolters Kluwer home page
    When I search for "CCH Tagetik"
    Then the search results page should be displayed
    And the search results page should contain "CCH Tagetik"

  Scenario Outline: Search for different product and topic keywords
    Given I navigate to the Wolters Kluwer home page
    When I search for "<keyword>"
    Then the search results page should be displayed

    Examples:
      | keyword       |
      | health        |
      | legal         |
      | audit         |
      | Enablon       |
      | VitalLaw      |
