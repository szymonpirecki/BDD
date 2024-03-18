Feature: Login error handling

  Scenario Outline: Log in providing partial credentials
    Given I am on the login page
    And I <action>
    When I click the 'LOGIN' button
    Then I am still on the login page
    And Error message "Epic sadface: <error message content>" is displayed

    Examples:
      | action                                | error message content                                       |
      | enter invalid credentials             | Username and password do not match any user in this service |
      | enter only password                   | Username is required                                        |
      | enter only username                   | Password is required                                        |
      | enter credentials for locked out user | Sorry, this user has been locked out.                       |
