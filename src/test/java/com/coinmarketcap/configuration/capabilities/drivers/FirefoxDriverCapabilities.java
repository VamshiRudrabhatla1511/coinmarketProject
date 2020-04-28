package com.coinmarketcap.configuration.capabilities.drivers;

import com.coinmarketcap.configuration.capabilities.DriverCapabilities;
import org.openqa.selenium.remote.DesiredCapabilities;

public class FirefoxDriverCapabilities implements DriverCapabilities {

    @Override
    public DesiredCapabilities getDriverCapabilities() {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("os", "OS X");
        caps.setCapability("os_version", "High Sierra");
        caps.setCapability("browser", "Firefox");
        caps.setCapability("browser_version", "69.0 beta");
        return caps;
    }
}
