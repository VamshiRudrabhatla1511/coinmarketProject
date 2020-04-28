package com.coinmarketcap.configuration;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigProperties {
    private static ConfigProperties _instance = null;
    private Properties properties;

    private ConfigProperties() throws RuntimeException {
        properties = new Properties();
        try {
            System.out.println("******test this*****");
            properties.load(new FileInputStream("src/test/resources/config/test.properties"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static ConfigProperties getInstance() {
        if (_instance == null) {
            _instance = new ConfigProperties();
        }
        return _instance;
    }

    public String getServerUrl() {
        String userName = "test";
        String userAccessKey = "test";
        String serverUrl = properties.getProperty("server_url");
        return serverUrl.replace("${AUTO_BS_USER_NAME}", userName)
                .replace("${AUTO_BS_ACCESS_KEY}", userAccessKey);
    }
    public String getPlatformName() {
        return properties.getProperty("platform");
    }

    public String getBrowserName() {
        return properties.getProperty("browser_name").replace("_", " ");
    }

    public String getTestEnvironment() {
        return properties.getProperty("test_env");
    }

    public String getSiteUrl() {
        return properties.getProperty("site_url");
    }

    public String getImplicitWait() {
        return properties.getProperty("implicit_wait");
    }

    public String getGeoLocationCountryCode() {
        return properties.getProperty("geo_location_country");
    }
}
