package org.example.utils;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public final class DriverFactory {
    private DriverFactory() {
    }

    public static WebDriver create(BrowserType browserType) {
        return switch (browserType) {
            case CHROME -> setUpDriver(new ChromeDriver());
            case FIREFOX -> setUpDriver(new FirefoxDriver());
        };
    }

    private static WebDriver setUpDriver(WebDriver driver) {
        driver.manage().window().setSize(new Dimension(1920, 1080));

        return driver;
    }
}
