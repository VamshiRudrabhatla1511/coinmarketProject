package com.coinmarketcap.service;

import com.coinmarket.vo.bitcoin.CurrencyConveryResponse;
import com.coinmarket.vo.bitcoin.MapRefference;
import com.coinmarketcap.restAssuredUtil.RestService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;

import java.util.logging.Logger;

public class RestAssuredApi {
    private static final Logger LOGGER = Logger.getLogger(RestAssuredApi.class.getName());

    public RestAssuredApi(String endPoint) {
        RestAssured.baseURI = endPoint;
    }

    public MapRefference currencyMap(String apiPath, String place) {
        Response response = RestService.getMethod("json", apiPath + place);
        int statusCode = response.getStatusCode();
        LOGGER.info("Search Response : " + response.asString());
        //Assert.assertEquals(statusCode, 200);
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(response.asString(), new TypeReference<MapRefference>() {
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public CurrencyConveryResponse getCurrencyConversionResponse(String apiPath, String place) {
        Response response = RestService.getMethod("json", apiPath + place);
        int statusCode = response.getStatusCode();
        LOGGER.info("Search Response : " + response.asString());
        //Assert.assertEquals(statusCode, 200);
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(response.asString(), new TypeReference<CurrencyConveryResponse>() {
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Response cryptoCurrency(String apiPath, String place) {
        Response response = RestService.getMethod("json", apiPath + place);
        int statusCode = response.getStatusCode();
        LOGGER.info("Search Response : " + response.asString());
        //Assert.assertEquals(statusCode, 200);
        return response;
    }
}
