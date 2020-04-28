package com.coinmarketcap.Runner;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.Before;
import org.junit.runner.RunWith;

import java.util.logging.Logger;

@RunWith(Cucumber.class)
@CucumberOptions(
        features="src/test/resources/features",
        glue = {"com.coinmarketcap.stepDefinitions"},format={"pretty"})
public class BaseRunner {

    private final static Logger LOGGER = Logger.getLogger(BaseRunner.class.getName());

    @Before
    public static void setup() {
        LOGGER.info("This will have implementation after later commits");
    }

    //@After
    public static void exitSuite() {

    }

}
