package org.example.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class Page {
    protected final WebDriver driver;
    private final String url;

    public Page open() {
        if (driver.getCurrentUrl() == null || !driver.getCurrentUrl().equals(url)) {
            driver.get(url);
        }

        return this;
    }

    protected void safeClick(By locator) {
        new WebDriverWait(driver, Duration.ofSeconds(10))
            .until(ExpectedConditions.elementToBeClickable(locator))
            .click();
    }

    protected String safeGetText(By locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(10))
            .until(ExpectedConditions.visibilityOfElementLocated(locator))
            .getText();
    }

    protected boolean isElementPresent(By locator) {
        try {
            return new WebDriverWait(driver, Duration.ofSeconds(10))
            .until(ExpectedConditions.visibilityOfElementLocated(locator)).isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    public Page consent() {
        safeClick(By.xpath("//button[@aria-label='Consent']"));

        return this;
    }

    public Page acceptCookies() {
        safeClick(By.xpath("//button[contains(@class,'cookie-accept')]"));

        return this;
    }

    public Page closePopup() {
        safeClick(By.xpath("//button[contains(@class,'dismiss-btn')]"));

        return this;
    }
}
