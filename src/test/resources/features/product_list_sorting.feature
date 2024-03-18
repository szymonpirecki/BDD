Feature: Product list sorting

  Scenario Outline: Successful product sorting
    Given I am logged in as a standard user
    When I select sorting option by <sortBy> in <sortOrder> order
    Then Products are displayed in <sortOrder> order by <sortBy>

    Examples:
      | sortBy | sortOrder |
      | PRICE  | ASC       |
      | PRICE  | DESC      |
      | NAME   | ASC       |
      | NAME   | DESC      |

