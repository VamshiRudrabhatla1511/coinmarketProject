package com.coinmarketcap.stepDefinitions;

import com.coinmarket.vo.bitcoin.CurrencyConveryResponse;
import com.coinmarket.vo.bitcoin.Datum;
import com.coinmarket.vo.bitcoin.MapRefference;
import com.coinmarketcap.service.RestAssuredApi;
import com.jayway.restassured.response.Response;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;

import java.util.*;

public class CoinMarketRestApiSteps {

    private static final String API = "https://pro-api.coinmarketcap.com/v1";

    private static final String CRYPTO_CURRENCY_MAP = "/cryptocurrency/map";

    private static final String PRICE_CONVERSION = "/tools/price-conversion";

    private static final String CRYPTO_CURRENCY_INFO = "/cryptocurrency/info?id=";

    private  TreeSet<String> mineableCurrencyTreeSetActual = new TreeSet<String>();

    private  TreeSet<String> mineableCurrencyTreeSetExpected = new TreeSet<String>();

    RestAssuredApi restAssuredApi;
    String mapPath;
    String currencyConveryApi;
    Map<String, Integer> priceMap = new HashMap<String, Integer>();
    MapRefference mapRefference;
    List<String> symbolList;
    Map<String, CurrencyConveryResponse> currencyConveryResponseMap = new HashMap<String, CurrencyConveryResponse>();

    Response response;
    Integer cryptoCurrencyId;

    Map<Integer, Response> resposneMap = new TreeMap<Integer, Response>();

    @Given("^I make a /cryptocurrency/map call by passing the symbols \"([^\"]*)\"$")
    public void iMakeACryptocurrencyMapCallByPassingTheSymbols(String symbol) throws Throwable {
        symbolList = Arrays.asList(symbol.split(","));
        restAssuredApi = new RestAssuredApi(API + CRYPTO_CURRENCY_MAP);
        mapRefference = restAssuredApi.currencyMap(API, CRYPTO_CURRENCY_MAP);
        Assert.assertNotNull(mapRefference);
    }

    @Then("^I assert the response and retrieve the ID of each of the currency$")
    public void iAssertTheResponseAndRetrieveTheIDOfEachOfTheCurrency() {
        for(Datum datum : mapRefference.getData()){
            if(symbolList.contains(datum.getSymbol())) {
                priceMap.put(datum.getSymbol(), datum.getId());
            }
        }
        Assert.assertEquals(priceMap.size(), 3);
    }

    @And("^I make a /tools/price-conversion call using the IDs$")
    public void iMakeAToolsPriceConversionCallUsingTheIDs() {
        for(String source : priceMap.keySet()){
            CurrencyConveryResponse currencyConveryResponse = restAssuredApi.getCurrencyConversionResponse(API, PRICE_CONVERSION+"?id="+priceMap.get(source)+"&amount=10&convert=BOB");
            currencyConveryResponseMap.put(source, currencyConveryResponse);
        }
        Assert.assertEquals(currencyConveryResponseMap.size(), 3);
    }

    @Then("^I assert the response and convert the IDs to Bolivian Boliviano$")
    public void iAssertTheResponseAndConvertTheIDsToBolivianBoliviano() {
        for(String source : currencyConveryResponseMap.keySet()){
            String SYMBOL = currencyConveryResponseMap.get(source).getData().getSymbol();
            double currency = currencyConveryResponseMap.get(source).getData().getQuote().getBOB().getPrice();
            if(StringUtils.equals("SYMBOL", currencyConveryResponseMap.get(source).getData().getSymbol())){
                Assert.assertEquals(currency, 531423.0195715594);
            }
            if(StringUtils.equals("SYMBOL", currencyConveryResponseMap.get(source).getData().getSymbol())){
                Assert.assertEquals(currency, 13344.889072076563);
            }
            if(StringUtils.equals("SYMBOL", currencyConveryResponseMap.get(source).getData().getSymbol())){
                Assert.assertEquals(currency, 69.41243333281544);
            }
        }
    }

    @Given("^I Retrieve the Ethereum ID \"([^\"]*)\" technical documentation website from the cryptocurrency/info call\\.$")
    public void iRetrieveTheEthereumIDIDTechnicalDocumentationWebsiteFromTheCryptocurrencyInfoCall(Integer ID) {
        cryptoCurrencyId = ID;
        restAssuredApi = new RestAssuredApi(API + CRYPTO_CURRENCY_INFO+ID);
        response = restAssuredApi.cryptoCurrency(API, CRYPTO_CURRENCY_INFO+ID);
        System.out.println(response);
    }

    @Then("^I assert the response status and confirm that the following logo url \"([^\"]*)\" is present$")
    public void iAssertTheResponseStatusAndConfirmThatTheFollowingLogoUrlIsPresent(String logoUrl) throws Throwable {
        Assert.assertEquals(response.jsonPath().getString("data."+cryptoCurrencyId+".logo"), logoUrl);
    }

    @Then("^I confirm that the following technical doc url \"([^\"]*)\" is present$")
    public void iConfirmThatTheFollowingTechnicalDocUrlIsPresent(String technicalDoc) throws Throwable {
        //System.out.println(StringUtils.contains(response.jsonPath().getString("data."+cryptoCurrencyId+".urls.technical_doc"), technicalDoc));
        //System.out.println(technicalDoc);
        //System.out.println(response.jsonPath().getString("data."+cryptoCurrencyId+".urls.technical_doc"));
        Assert.assertEquals(StringUtils.contains(response.jsonPath().getString("data."+cryptoCurrencyId+".urls.technical_doc"), technicalDoc), true);
    }

    @And("^I confirm that the symbol of the currency is ETH$")
    public void iConfirmThatTheSymbolOfTheCurrencyIsETH() {
        //System.out.println(StringUtils.contains(response.jsonPath().getString("data."+cryptoCurrencyId+".symbol"), "ETH"));
        //Assert.assertEquals(response.jsonPath().getString("data."+cryptoCurrencyId+".symbol"), "ETH");
        Assert.assertEquals(StringUtils.contains(response.jsonPath().getString("data."+cryptoCurrencyId+".symbol"), "ETH"), true);
    }

    @And("^I confirm that the date added as date \"([^\"]*)\"$")
    public void iConfirmThatTheDateAddedAsDate(String dateAdded) throws Throwable {
        //System.out.println(StringUtils.contains(response.jsonPath().getString("data."+cryptoCurrencyId+".date_added"), dateAdded));
        Assert.assertEquals(response.jsonPath().getString("data."+cryptoCurrencyId+".date_added"), dateAdded);
    }

    @And("^I confirm that the platform is null$")
    public void iConfirmThatThePlatformIsNull() {
        //System.out.println(StringUtils.equals(response.jsonPath().getString("data."+cryptoCurrencyId+".platform"), null));
        Assert.assertEquals(response.jsonPath().getString("data."+cryptoCurrencyId+".platform"), null);
    }

    @And("^I confirm that the currency has the mineable tag associated with it$")
    public void iConfirmThatTheCurrencyHasTheMineableTagAssociatedWithIt() {
         Assert.assertEquals(StringUtils.contains(response.jsonPath().getString("data."+cryptoCurrencyId+".tags"), "mineable"), true);
    }

    @Given("^I make a /cryptocurrency/map call for the first (\\d+) currencies$")
    public void iMakeACryptocurrencyMapCallForTheFirstCurrencies(int max) {
        restAssuredApi = new RestAssuredApi(API + CRYPTO_CURRENCY_INFO);
        for(int i = 1;i<=max;i++) {
            response = restAssuredApi.cryptoCurrency(API, CRYPTO_CURRENCY_INFO + i);
            resposneMap.put(Integer.parseInt(response.jsonPath().getString("data."+i+".id")), response);
        }
    }

    @Then("^I check which currencies have the mineable tag associated with them$")
    public void iCheckWhichCurrenciesHaveTheMineableTagAssociatedWithThem() {
        for(int id:  resposneMap.keySet()) {
            response = resposneMap.get(id);
            if(StringUtils.contains(response.jsonPath().getString("data."+id+".tags"), "mineable")) {
                mineableCurrencyTreeSetActual.add(response.jsonPath().getString("data."+id+".id"));
            }

        }
    }

    @Then("^I verify that the correct cryptocurrencies have been printed out$")
    public void iVerifyThatTheCorrectCryptocurrenciesHaveBeenPrintedOut() {
        for(int i=1; i<11; i++) {
            mineableCurrencyTreeSetExpected.add(""+i);
        }
        Assert.assertEquals("both sets are not matching", mineableCurrencyTreeSetExpected, mineableCurrencyTreeSetActual );
    }
}
