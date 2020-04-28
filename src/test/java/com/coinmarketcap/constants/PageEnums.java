package com.coinmarketcap.constants;

/**
 * This constant is to find the values of the constant keywords
 *
 */

public enum PageEnums {
    MORE("more"), LESS("less"), SIX_MONTHS_OR_LESS("six_months_or_less"),
    LONGER_THAN_SIX_MONTHS("longer_than_six_months");
    private String name = "";

    private PageEnums(String name) {
        this.name = name;
    }

    public String getValue() {
        return name;
    }
}
