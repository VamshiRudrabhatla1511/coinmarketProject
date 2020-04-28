package com.coinmarketcap.pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

public class WatchListPageObjects extends CommonPageObjects {
    @FindBy(how = How.CSS, using = "div[class^='cmc-table-listing cmc-table-listing--is-tab-selected'] h1")
    public WebElement watchListPageAssert;

    @FindBy(how = How.CSS, using = "td[class^= 'cmc-table__cell cmc-table__cell--sticky cmc-table__cell--sortable'] a")
    public List<WebElement> listOfcryptoCurrenciesNames;
}
