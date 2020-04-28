package com.coinmarketcap.configuration.capabilities.drivers;

import com.coinmarketcap.configuration.capabilities.DriverCapabilities;
import org.openqa.selenium.remote.DesiredCapabilities;

public class ChromeWindowsCapabilities implements DriverCapabilities {

    @Override
    public DesiredCapabilities getDriverCapabilities() {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("os", "Windows");
        caps.setCapability("os_version", "10");
        caps.setCapability("browser", "Chrome");
        caps.setCapability("browser_version", "78.0");
        return caps;
    }
}
