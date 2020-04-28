package com.coinmarketcap.pages;

import com.coinmarketcap.pageObjects.CommonPageObjects;


public class NationalityPage extends CommonPageObjects {

   /* @FindBy(id = "response")
    WebElementFacade nationalities;
    @FindBy(css="label[class='govuk-label govuk-label--m']")
    WebElementFacade nationalityPageTextAssert;

    public void selectNationality(String nationalityName){
        selectFromDropdown(nationalities,nationalityName);
    }
*//*    public void assertNationalityPageText(String expected){
        String actual = nationalityPageTextAssert.getText();
        Assert.assertEquals("Text Did Not Match --Error in Loading Page",actual,expected);
    }*//*

    public String isNationalityPageDisplayed() {
        nationalityPageTextAssert.waitUntilVisible();
        return nationalityPageTextAssert.getText();
    }*/
}
