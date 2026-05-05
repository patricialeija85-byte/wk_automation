@ui
Feature: Wolters Kluwer About Us Page
  As a user interested in learning about Wolters Kluwer as a company
  I want to visit the About Us section
  So that I can understand their mission, leadership, and values

  @test1
  Scenario: About Us page loads from header navigation
    Given I navigate to the Wolters Kluwer home page
#    When I click on About Us in the header
#    Then the About Us page should be displayed
#    And the About Us page heading should be visible

  Scenario: About Us page contains company keyword
    Given I navigate to the Wolters Kluwer home page
    When I click on About Us in the header
    Then the About Us page should contain the keyword "Wolters Kluwer"

  Scenario: About Us page URL is correct
    Given I navigate to the Wolters Kluwer home page
    When I click on About Us in the header
    Then the current URL should contain "about-us"
