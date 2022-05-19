@web-task @ui
Feature: Web Tasks - Verify Filter Functionalities of coin Market Cap

  @web-task1 @ui
  Scenario: Web Task 1 - Validate Row counts on selecting number of rows
    Given user navigates to "coinMarket"
    And scrolls to the table
    When size of row is set to "50"
    Then verify if 50 rows are displayed

  @web-task2 @ui
  Scenario: Web Task 2 - Validate Filter Application on Table
    Given user navigates to "coinMarket"
    And scrolls to the table
    When below filters are applied
      | filter           | value         |
      | Market Cap Range | $1B - $10B    |
      | Price            | $101 - $1,000 |
    Then the table should have rows with below values
      | filter           | min        | max         |
      | Market Cap Range | 1000000000 | 10000000000 |
      | Price            | 101        | 1000        |