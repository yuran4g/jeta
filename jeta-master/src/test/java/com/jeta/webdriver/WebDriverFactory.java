package com.jeta.webdriver;

import io.github.bonigarcia.wdm.*;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * Created by mariaklimenko on 04.03.2017.
 */

public class WebDriverFactory {
    private final static Logger logger = Logger.getLogger(WebDriverFactory.class);

    public static WebDriver createInstance(String browserName){
        WebDriver driver;

        switch (Browser.valueOf(browserName.toUpperCase())) {
            case FIREFOX:
                FirefoxDriverManager.getInstance().setup();
                driver = new FirefoxDriver();
                break;
            case EDGE:
                EdgeDriverManager.getInstance().setup();
                driver = new EdgeDriver();
                break;
            case CHROME:
                ChromeDriverManager.getInstance().setup();
                driver = new ChromeDriver();
                break;
            case IE:
                DesiredCapabilities dc = new DesiredCapabilities();
                dc.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, true);
                dc.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
                InternetExplorerDriverManager.getInstance().setup();
                driver = new InternetExplorerDriver(dc);
                break;
            case OPERA:
                OperaDriverManager.getInstance().setup();
                driver = new OperaDriver();
                break;
            default:
                ChromeDriverManager.getInstance().setup();
                driver = new ChromeDriver();
                break;
        }
        // maximize browser's window on start
        driver.manage().window().maximize();
        return driver;
    }

    private enum Browser {
        FIREFOX,
        CHROME,
        EDGE,
        IE,
        OPERA
    }
}