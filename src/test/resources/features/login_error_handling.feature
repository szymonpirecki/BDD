Feature: Login error handling

  Scenario: Log in as locked out user

    Given I am on the login page
    Given I enter credentials for locked out user
    When I click the 'LOGIN' button
    Then I am still on the login page
    Then Error message is displayed


