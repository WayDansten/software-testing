package org.example.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class Page {
    protected final WebDriver driver;
    private final String url;

    public void open() {
        if (driver.getCurrentUrl() == null || !driver.getCurrentUrl().equals(url)) {
            driver.get(url);
        }
    }

    protected void safeClick(By locator) {
        new WebDriverWait(driver, Duration.ofSeconds(5))
            .until(ExpectedConditions.elementToBeClickable(locator))
            .click();
    }

    protected String safeGetText(By locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(5))
            .until(ExpectedConditions.visibilityOfElementLocated(locator))
            .getText();
    }
}
