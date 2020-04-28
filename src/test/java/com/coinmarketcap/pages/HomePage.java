package com.coinmarketcap.pages;

import com.coinmarketcap.pageObjects.CommonPageObjects;
import com.coinmarketcap.pageObjects.HomePageObjects;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class HomePage extends HomePageObjects {

    public HomePage(){
        PageFactory.initElements(driver, this);
    }

    public void navigateToApp() {
        driver.navigate().to(testCp.getSiteUrl());
    }

    public int getCryptoCurrenciesListSize(){
        System.out.println("the size is-" + cryptoCurrenciesList.size());
        return cryptoCurrenciesList.size();
    }

    public void clickOnLink(String linkName){
        System.out.println("******before clicked the links********************");
        switch(linkName){
            case "viewAll":
                waitUntilElementDisplayed(viewAllLink, getDriver());
                try {
                    viewAllLink.click();
                    waitUntilElementDisplayed(backToTop100Button, getDriver());
                    System.out.println("**********clicked the links*************");
                    break;
                } catch (Exception e) {
                    e.printStackTrace();
                }
        }
    }

    public String[] addToWatchList(String cryptoCurrencyName) {
        String[] cryptosArray = cryptoCurrencyName.split(",");
        for(String cryptoCurrency : cryptosArray){
            //scroll into view till the first option in the grid
            String firstElementPath = "//a[(text()='Bitcoin')]";
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", getDriver().findElement((By.xpath(firstElementPath))));
            //generate a dynamic xpath for each of the crypto currencies
            String  ellipsesOfCryptoCurrencyXpath = "//a[(text()='" + cryptoCurrency + "')]/../../../td[@class='cmc-table__cell cmc-table__cell--right']//span";
            System.out.println("********the crypto path is -" + ellipsesOfCryptoCurrencyXpath);
            WebElement ellipsesOfCryptoCurrency  =getDriver().findElement(By.xpath(ellipsesOfCryptoCurrencyXpath));
            waitUntilElementEnabled(ellipsesOfCryptoCurrency, getDriver());
            Actions actions = new Actions(getDriver());
            actions.moveToElement(ellipsesOfCryptoCurrency).click().perform();
            waitUntilElementDisplayed(addToWatchList, getDriver());
            addToWatchList.click();
        }
        return cryptosArray;
    }

    public WatchListPage switchToNewTab() {
        ((JavascriptExecutor)driver).executeScript("window.open()");
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        //open the watchlist page
        driver.get(testCp.getSiteUrl()+"/watchlist/");
        WatchListPage watchListPage = PageFactory.initElements(driver, WatchListPage.class);
        return watchListPage;

    }

    public void scrolltoDropDownMenu() {
        waitUntilElementDisplayed(homeLogo, getDriver());
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", homeLogo);
    }

    public void clickCryptoCurrenciesFullList(){
//        waitUntilElementDisplayed(cryptocurrenciesDropDown, getDriver());
       // Actions actions = new Actions(getDriver());
        //actions.moveToElement(cryptocurrenciesDropDown).click().perform();
        cryptocurrenciesDropDown.click();
        waitUntilElementDisplayed(cryptocurrenciesFullList, getDriver());
        cryptocurrenciesFullList.click();
    }

    public HashSet<String> recordSymbolsFromtheResults(){
        List<WebElement> cyptoCurrencyNameList = null;
        HashSet<String> resultCurrencyNameSet = new HashSet<String>();
        try {
            cyptoCurrencyNameList = getDriver().findElements(By.xpath("//div[@class='cmc-table__table-wrapper-outer']//tbody//tr/td[2]//a"));
            for (WebElement element : cyptoCurrencyNameList) {
                resultCurrencyNameSet.add(element.getText());
            }
        } catch(org.openqa.selenium.StaleElementReferenceException ex) {
             cyptoCurrencyNameList = getDriver().findElements(By.xpath("//div[@class='cmc-table__table-wrapper-outer']//tbody//tr/td[2]//a"));
            for (WebElement element : cyptoCurrencyNameList) {
                resultCurrencyNameSet.add(element.getText());
            }
        }
        return resultCurrencyNameSet;
    }

    public void applyRandomFilter(){
        waitUntilElementDisplayed(homeLogo, getDriver());
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", homeLogo);
        waitUntilElementDisplayed(filterButton, getDriver());
        filterButton.click();
        waitUntilElementDisplayed(filterPriceButton, getDriver());
        filterPriceButton.click();
        waitUntilElementDisplayed(filterPriceMin, getDriver());
        filterPriceMin.sendKeys("4");
        filterPriceMax.sendKeys("5");
        filterPriceApply.click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }
}
