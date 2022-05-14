@backend-task @backend
Feature: Backend Task - Validate Currency API

  @backend-task1 @backend
  Scenario Outline: Backend Task 1 - Validate currency conversion for given currency
    Given Fetch the id for "<Currency>"
    When convert amount "<Amount>" to "BOB"
    Examples:
      | Currency | Amount |
      | BTC      | 40     |
      | USDT     | 30     |
      | ETH      | 20     |

  @backend-task2 @backend
  Scenario: Backend Task 3 - validate Technical Documentation of Single Currency
    Given get the information about "1027"
    Then verify following details
      | id   | logo                                                         | tech_doc                                         | symbol | date_added               | tags     |
      | 1027 | https://s2.coinmarketcap.com/static/img/coins/64x64/1027.png | https://github.com/thereum/wiki/wiki/White-Paper | ETH    | 2015-08-07T00:00:00.000Z | mineable |


  @backend-task3
  Scenario: Backend Task 2 - validate Technical Documentation of Multiple Currency
    Given get the information about "1,2,3,4,5,6,7,8,9,10"
    Then verify following details
      | id | symbol | tags     |
      | 1  | BTC    | mineable |
      | 2  | LTC    | mineable |
      | 3  | NMC    | mineable |
      | 4  | TRC    | mineable |
      | 5  | PPC    | mineable |
      | 6  | NVC    | mineable |
      | 7  | DVC    | mineable |
      | 8  | FTC    | mineable |
      | 9  | MNC    | mineable |
      | 10 | FRC    | mineable |
