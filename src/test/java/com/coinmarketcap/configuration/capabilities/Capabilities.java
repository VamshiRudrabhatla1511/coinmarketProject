package com.coinmarketcap.configuration.capabilities;

import org.openqa.selenium.remote.DesiredCapabilities;

public class Capabilities {

    private DriverCapabilities capabilities;

    public DesiredCapabilities getCapabilities() {
        return capabilities.getDriverCapabilities();
    }

    public void setCapabilities(DriverCapabilities capabilities) {
        this.capabilities = capabilities;
    }
}
