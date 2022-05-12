@backend-task
Feature: Validate Currency API

  @backend-task1
  Scenario Outline: Validate currency conversion for given currency
    Given Fetch the id for "<Currency>"
    When convert amount "<Amount>" to "BOB"
    Examples:
      | Currency | Amount |
      | BTC      | 40     |
      | USDT     | 30     |
      | ETH      | 20     |

  @backend-task2
    Scenario: validate Technical Documentation of Currency
      Given get the information about "1027"