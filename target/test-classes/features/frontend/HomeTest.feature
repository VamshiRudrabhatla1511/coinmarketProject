Feature: CoinMarket Home Page Automation Tests

  Background:
    Given User Launches coin market cap app

  @sanity
  Scenario: Clicking on Coin Market Home Page view all tab should display the full set of results for Crytocurrencies

    When User clicks on viewAll button on coin market home page
    Then User verifies that more than 100 results are displayed

  @sanity
  Scenario Outline: watch list tab tests

    When User adds <cryptoCurrencies> to the watchlist by clicking on ellipses options menu on coin market home page
    And Opens the watchlist in a different browser tab
    Then verify the options selected <cryptoCurrencies> on coin market page is added to the watchlist page

    Examples:
      | cryptoCurrencies                                        |
      | Ethereum,XRP,Tether,Bitcoin Cash,Bitcoin SV |

  @sanity
  Scenario: Filtering tests
    When Display the dropdown menu on the Cryptocurrencies tab.
    And Click any of the three Full List options on this menu.
    And Record the data on the current page.
    And Apply any combination of filters, displayed in the three dropdown menus above the tabs.
    Then Check against the data recorded in Step 4.