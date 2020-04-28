package com.coinmarketcap.configuration;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public abstract class SeleniumPageUtils {
    public static Logger logger = LoggerFactory.getLogger(SeleniumPageUtils.class);
    protected static WebDriver driver;

    public static WebDriver getDriver() {
        return driver;
    }

    public static void setLogger(Logger log) {
        logger = log;
    }

    public static void info(String info) {
        logger.info(info);
    }

    public void error(String info) {
        logger.error(info);
    }

    public WebElement getElement(String selector) {
        return getDriver().findElement(By.cssSelector(selector));
    }

    public void refreshPage() {
        getDriver().navigate().refresh();
    }

    public boolean IfAlertPresent() {
        try {
            getDriver().switchTo().alert();
            return true;

        } catch (NoAlertPresentException ex) {
            return false;
        }
    }

    public void AcceptAllAlerts() {
        if (IfAlertPresent()) {
            do {
                driver.switchTo().alert().accept();
            } while (IfAlertPresent());
        }
    }

    public boolean AcceptWriteOnAlerts(String keyword) {
        try {
            getDriver().switchTo().alert().sendKeys(keyword);
            return true;
        } catch (NoAlertPresentException ex) {
            return false;
        }
    }

}
