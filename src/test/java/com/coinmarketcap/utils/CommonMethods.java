package com.coinmarketcap.utils;

import com.coinmarketcap.pageObjects.CommonPageObjects;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class CommonMethods extends CommonPageObjects {

    /*public void selectRadioButton(String valueOfRadioButton){
        try {
            TimeUnit.SECONDS.sleep(2);
            List<WebElement> radioButtonList= getDriver().findElements(By.xpath("//*[@class='govuk-radios__input']"));
            for (WebElement radioButonOption : radioButtonList) {
                String value = radioButonOption.getAttribute("value");
                if (value.trim().equalsIgnoreCase(valueOfRadioButton)) {
                    radioButonOption.click();
                    break;
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }*/
}
