Feature: Positive simple smoke test

  Scenario: Positive simple smoke test
    Given I am at login with email page
    When I login with active user
    Then I should be logged in