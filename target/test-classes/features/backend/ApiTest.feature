Feature: CoinMarket Home Page Automation Tests

  Background:
    Given User Launches coin market cap app

  @backendsanity
  Scenario Outline: retrieve the IDs of the currencies and convert them to Bolivian Boliviano

    Given I make a /cryptocurrency/map call by passing the symbols "<symbols>"
    Then I assert the response and retrieve the ID of each of the currency
    And I make a /tools/price-conversion call using the IDs
    Then I assert the response and convert the IDs to Bolivian Boliviano
    Examples:
      | symbols |
      | BTC,ETH,USDT |


  @backendsanity
  Scenario Outline: Retrieve the Ethereum (ID 1027) technical documentation

    Given I Retrieve the Ethereum ID "<ID>" technical documentation website from the cryptocurrency/info call.
    Then I assert the response status and confirm that the following logo url "<logoURL>" is present
    Then I confirm that the following technical doc url "<technical_doc_URL>" is present
    And I confirm that the symbol of the currency is ETH
    And I confirm that the date added as date "<date>"
    And I confirm that the platform is null
    And I confirm that the currency has the mineable tag associated with it

    Examples:
      | ID   | logoURL                                                      |technical_doc_URL                                |date                    |
      | 1027 | https://s2.coinmarketcap.com/static/img/coins/64x64/1027.png |https://github.com/ethereum/wiki/wiki/White-Paper|2015-08-07T00:00:00.000Z|


  @backendsanity
  Scenario: Retrieve the first 10 currencies info

    Given I make a /cryptocurrency/map call for the first 10 currencies
    Then I check which currencies have the mineable tag associated with them
    Then I verify that the correct cryptocurrencies have been printed out
