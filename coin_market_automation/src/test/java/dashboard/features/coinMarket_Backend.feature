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
    Then verify following details
      | id   | logo                                                         | tech_doc                                         | symbol | date_added               | tags     |
      | 1027 | https://s2.coinmarketcap.com/static/img/coins/64x64/1027.png | https://github.com/thereum/wiki/wiki/White-Paper | ETH    | 2015-08-07T00:00:00.000Z | mineable |

#  @backend-task3
#  Scenario Outline: validate Technical Documentation of Currency
#    Given get the information about "1020,1021,1022,1023,1024,1025,1026,1027,1028,1029"
#    Then verify following details
#      | id   | symbol   | tags   |
#      | <id> | <symbol> | <tags> |
#    Examples:
#      | id   | symbol | tags     |
#      | 1020 | AXIOM  | mineable |
#      | 1021 | CHIP   | mineable |
#      | 1022 | BTU    | mineable |
#      | 1023 | EGD    | mineable |
#      | 1024 | SPC    | mineable |
#      | 1025 | CS     | mineable |
#      | 1026 | AEON   | mineable |
#      | 1027 | ETH    | mineable |
#      | 1028 | SJW    | mineable |
#      | 1029 | TENNET | mineable |

  @backend-task3
  Scenario: validate Technical Documentation of Currency
    Given get the information about "1020,1021,1022,1023,1024,1025,1026,1027,1028,1029"
    Then verify following details
      | id   | symbol   | tags   |
      | 1020 | AXIOM  | mineable |
      | 1021 | CHIP   | mineable |
      | 1022 | BTU    | mineable |
      | 1023 | EGD    | mineable |
      | 1024 | SPC    | mineable |
      | 1025 | CS     | mineable |
      | 1026 | AEON   | mineable |
      | 1027 | ETH    | mineable |
      | 1028 | SJW    | mineable |
      | 1029 | TENNET | mineable |
