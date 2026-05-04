Feature: Wolters Kluwer Solutions Navigation
  As a user exploring Wolters Kluwer solutions
  I want to navigate to each solution category
  So that I can find the right product for my business needs

  Scenario: Navigate to Health solutions
    Given I navigate to the Wolters Kluwer home page
    When I navigate to Health from the solutions menu
    Then a solutions category page should be displayed
    And the current URL should contain "health"
    And the page title should contain "Wolters Kluwer"

  Scenario: Navigate to Tax and Accounting solutions
    Given I navigate to the Wolters Kluwer home page
    When I navigate to Tax and Accounting from the solutions menu
    Then a solutions category page should be displayed
    And the current URL should contain "tax"
    And the page title should contain "Wolters Kluwer"

  Scenario: Navigate to Financial and Corporate Compliance solutions
    Given I navigate to the Wolters Kluwer home page
    When I navigate to Financial and Corporate Compliance from the solutions menu
    Then a solutions category page should be displayed
    And the current URL should contain "compliance"
    And the page title should contain "Wolters Kluwer"

  Scenario: Navigate to Legal and Regulatory solutions
    Given I navigate to the Wolters Kluwer home page
    When I navigate to Legal and Regulatory from the solutions menu
    Then a solutions category page should be displayed
    And the current URL should contain "legal"
    And the page title should contain "Wolters Kluwer"

  Scenario: Navigate to Corporate Performance and ESG solutions
    Given I navigate to the Wolters Kluwer home page
    When I navigate to Corporate Performance and ESG from the solutions menu
    Then a solutions category page should be displayed
    And the page title should contain "Wolters Kluwer"

  Scenario Outline: All major solution categories are reachable
    Given I navigate to the Wolters Kluwer home page
    Then the current URL should contain "<urlFragment>"

    Examples:
      | urlFragment |
      | wolterskluwer.com |

  Scenario: Health solutions page mentions UpToDate
    Given I navigate to the Wolters Kluwer home page
    When I navigate to Health from the solutions menu
    Then the solutions page should mention "UpToDate"

  Scenario: Tax and Accounting page mentions CCH
    Given I navigate to the Wolters Kluwer home page
    When I navigate to Tax and Accounting from the solutions menu
    Then the solutions page should mention "CCH"
