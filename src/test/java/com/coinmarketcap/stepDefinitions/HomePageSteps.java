package com.coinmarketcap.stepDefinitions;

import com.coinmarketcap.pageObjects.HomePageObjects;
import com.coinmarketcap.pageObjects.WatchListPageObjects;
import com.coinmarketcap.pages.HomePage;
import com.coinmarketcap.pages.WatchListPage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class HomePageSteps {
    HomePage homePage;
    WatchListPage watchListPage;
    HashSet<String> viewAllResultsCurrencyNameSet;
    HashSet<String> filteredResultsCurrencyNameSet;

    @Given("^User Launches coin market cap app$")
    public void launchCoinMarketApp(){
        homePage = new HomePage();
        homePage.navigateToApp();
        Assert.assertTrue("Home Page is not displayed",homePage.homeLogo.isDisplayed());
        //int gridResultsSize = homePage.getCryptoCurrenciesListSize();
        //Assert.assertEquals("count not equal to 100",100,gridResultsSize);

    }

    @When("^User clicks on (.*) button on coin market home page$")
    public void clickOnViewAllButton(String buttonName){
       homePage.clickOnLink("viewAll");
    }

    @Then("^User verifies that more than 100 results are displayed$")
    public void verifyViewAllResults(){
        int actualCryptoCurrencyCount = homePage.getCryptoCurrenciesListSize();
        Assert.assertTrue("grid result size is not more than 100",actualCryptoCurrencyCount>100);
    }

    @When ("^User adds (.*) to the watchlist by clicking on ellipses options menu on coin market home page$")
    public void clickEllipses(String cryptoCurrencyName){
        homePage.addToWatchList(cryptoCurrencyName);
    }

    @And ("^Opens the watchlist in a different browser tab$")
    public void openWatchListInNewTab(){
        watchListPage = homePage.switchToNewTab();
        Assert.assertEquals("watchList Tab is not displayed",watchListPage.watchListPageAssert.getText(),"Watchlist");
    }

    @Then("^verify the options selected (.*) on coin market page is added to the watchlist page$")
    public void verifyTheOptionsSelectedCryptoCurrenciesOnCoinMarketPageIsAddedToTheWatchlistPage(String expected) {
        String[] expectedCrytoCurrencyWatchArray = expected.split(",");
        List<String> expectedCrytoCurrencyWatchList = Arrays.asList(expectedCrytoCurrencyWatchArray);
        for (WebElement webElement : watchListPage.listOfcryptoCurrenciesNames) {
            String name = webElement.getText();
            System.out.println(name);
            boolean elementFound = expectedCrytoCurrencyWatchList.contains(name);
            Assert.assertTrue("the given currency doesnt exist", elementFound);
        }
        watchListPage.switchToHomePage();
    }

    @When("^Display the dropdown menu on the Cryptocurrencies tab\\.$")
    public void displayTheDropdownMenuOnTheCryptocurrenciesTab() {
        homePage.scrolltoDropDownMenu();
    }

    @And("^Click any of the three Full List options on this menu\\.$")
    public void clickAnyOfTheThreeFullListOptionsOnThisMenu() {
        homePage.clickCryptoCurrenciesFullList();
    }

    @And("^Record the data on the current page\\.$")
    public void recordTheDataOnTheCurrentPage() {
        viewAllResultsCurrencyNameSet = homePage.recordSymbolsFromtheResults();
    }


    @And("^Apply any combination of filters, displayed in the three dropdown menus above the tabs\\.$")
    public void applyAnyCombinationOfFiltersDisplayedInTheThreeDropdownMenusAboveTheTabs() {
        homePage.applyRandomFilter();
        filteredResultsCurrencyNameSet =homePage.recordSymbolsFromtheResults();
    }

    @Then("^Check against the data recorded in Step (\\d+)\\.$")
    public void checkAgainstTheDataRecordedInStep(int arg0) {
        System.out.println("the expected size is - " + viewAllResultsCurrencyNameSet.size());
        System.out.println("the actual size is - " + filteredResultsCurrencyNameSet.size());
        Assert.assertTrue(viewAllResultsCurrencyNameSet.size() >filteredResultsCurrencyNameSet.size());
    }
}
