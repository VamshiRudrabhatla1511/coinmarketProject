package com.coinmarketcap.pages;

import com.coinmarketcap.pageObjects.WatchListPageObjects;

import java.util.ArrayList;

public class WatchListPage extends WatchListPageObjects {

   public void switchToHomePage() {
       ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
       driver.switchTo().window(tabs.get(0));
   }

}
