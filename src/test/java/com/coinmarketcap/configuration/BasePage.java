package com.coinmarketcap.configuration;


import com.coinmarketcap.configuration.capabilities.Capabilities;
import com.coinmarketcap.configuration.capabilities.drivers.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
public abstract class BasePage extends SeleniumPageUtils {

    protected static final ConfigProperties testCp = ConfigProperties.getInstance();

    protected BasePage() {
        System.out.println("browser-" + testCp.getBrowserName());
        System.out.println("env-" + testCp.getTestEnvironment());
        try {
            if (driver == null) {
                driver = "local".equalsIgnoreCase(testCp.getTestEnvironment()) ?
                        getLocalDriver()
                        : getRemoteDriver();
                ;
                driver.manage().timeouts().implicitlyWait(Long.parseLong(testCp.getImplicitWait()),
                        TimeUnit.SECONDS);
                driver.manage().window().maximize();
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    protected WebDriver getLocalDriver() {
        switch (testCp.getBrowserName()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            case "safari":
                WebDriver driver = new SafariDriver();
        }
        return driver;
    }


    protected WebDriver getRemoteDriver() {
        try {
            Capabilities capabilities = new Capabilities();
            DesiredCapabilities desiredCapabilities;
            switch (testCp.getBrowserName()) {
                case "chrome":
                    capabilities.setCapabilities(new ChromeDriverCapabilities());
                    break;
                case "safari":
                    capabilities.setCapabilities(new SafariDriverCapabilities());
                    break;
                case "ie":
                    capabilities.setCapabilities(new InternetExplorerDriverCapabilities());
                    break;
                case "chromeWindows":
                    capabilities.setCapabilities((new ChromeWindowsCapabilities()));
                    break;
                case "firefoxWindows":
                    capabilities.setCapabilities((new FirefoxDriverWindowsCapabilities()));
                    break;
                default:
                    String msg = testCp.getBrowserName() + " is unsupported, kindly try googlechrome, chrome, firefox, safari, microsoftedge or ie";
                    throw new IllegalArgumentException(msg);
            }
            desiredCapabilities = capabilities.getCapabilities();
            desiredCapabilities.setCapability("browserstack.local", "false");
            desiredCapabilities.setCapability("browserstack.selenium_version", "3.5.2");
            desiredCapabilities.setCapability("browserstack.geoLocation", testCp.getGeoLocationCountryCode());
            desiredCapabilities.setCapability("browserstack.networkLogs", "true");
            desiredCapabilities.setCapability("browserstack.debug", "true");
            return new RemoteWebDriver(new URL(testCp.getServerUrl()), desiredCapabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

