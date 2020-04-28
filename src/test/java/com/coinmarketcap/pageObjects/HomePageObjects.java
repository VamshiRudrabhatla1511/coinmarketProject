package com.coinmarketcap.pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import java.util.List;

public class HomePageObjects extends CommonPageObjects {

    @FindBy(how = How.CSS, using = "div[class^='cmc-bottom-margin'] a[title='Go to homepage']")
    public WebElement homeLogo;
    @FindBy(how = How.XPATH, using = "//div[@class='cmc-table-listing__pagination']/div[contains(@class,'cmc-button-group')]/a[@href='/all/views/all/']")
    public WebElement viewAllLink;
    @FindBy(how = How.XPATH, using = "//div[@class='cmc-table-listing__pagination']/a[contains(@class, 'cmc-table-listing__back-button')]")
    public WebElement backToTop100Button;
    @FindBy(how = How.CSS, using = "tr[class='cmc-table-row']")
    public List<WebElement> cryptoCurrenciesList;
    @FindBy(how = How.XPATH, using = "//ul[@role='menu']/li[1]//span")
    public WebElement addToWatchList;
    @FindBy(how = How.CSS, using = "a[href='/watchlist/']")
    public WebElement watchListTab;
    @FindBy(how = How.XPATH, using = "//div[@class='cmc-popover__trigger']/span[contains(text(),'Cryptocurrencies')]")
    public WebElement cryptocurrenciesDropDown;
    @FindBy(how = How.XPATH, using = "//ul[@role='menu']//a[contains(@href,'all/views/all')]")
    public WebElement cryptocurrenciesFullList;
    @FindBy(how = How.XPATH, using = "//button[@data-qa-id='table-listing-filters-toggle']")
    public WebElement filterButton;
    @FindBy(how = How.XPATH, using = "//div[@data-qa-id='range-filter-price']//button[@data-qa-id='filter-dd-toggle']")
    public WebElement filterPriceButton;
    @FindBy(how = How.XPATH, using = "//input[@data-qa-id='range-filter-input-min']")
    public WebElement filterPriceMin;
    @FindBy(how = How.XPATH, using = "//input[@data-qa-id='range-filter-input-max']")
    public WebElement filterPriceMax;
    @FindBy(how = How.XPATH, using = "//button[@data-qa-id='filter-dd-button-apply']")
    public WebElement filterPriceApply;
    @FindBy(how = How.XPATH, using = "//div[@class='cmc-table__table-wrapper-outer']//tbody//tr")
    public List<WebElement> cryptoCurrenciesAllResultsList;






}
