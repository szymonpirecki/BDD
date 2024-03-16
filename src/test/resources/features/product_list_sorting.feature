Feature: Product list sorting

  Scenario: Successful product sorting by price
    Given I am logged in as a standard user
    When I select sorting option 'Price low to high'
    Then The products are displayed in ascending order by price
