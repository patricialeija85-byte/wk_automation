@api @regression
Feature: Wolters Kluwer Search API
  As a user, I want to verify that the search service returns valid compliance and testing results.

  @test @preprod
  Scenario: Validate search results for software testing
    Given the search API is available
    When I search for "software testing" via API
    Then the response status code must be 200
    And the load time should be less than 2 seconds


