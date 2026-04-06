package org.example.pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DemonListPage extends Page {
    public DemonListPage(WebDriver driver) {
        super(driver, "https://demonlist.org/classic");
    }

    public void openDemon(int position) {
        safeClick(By.xpath(String.format("//a[@href='/classic/%d']", position)));
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(driver -> driver.getWindowHandles().size() > 1);
        
        List<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size() - 1));
    }

    public String openDemon(String demonName) {
        safeClick(By.xpath(String.format("//a[.//h3[text()='%s']]", demonName)));
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(driver -> driver.getWindowHandles().size() > 1);
        
        List<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size() - 1));

        return safeGetText(By.xpath("//h1[contains(@class,'level-title')]"));
    }

    public void findDemonByName(String demonName) {
        safeClick(By.xpath("//input[contains(@class,'search-input')]"));
        WebElement searchInput = driver.findElement(By.xpath("//input[contains(@class,'search-input')]"));
        searchInput.sendKeys(demonName);
        searchInput.sendKeys(Keys.ENTER);
    }

    public void enableFilter(String filterName) {
        safeClick(By.xpath("//button[contains(@class,'filter-trigger')]"));
        safeClick(By.xpath(String.format("//button[contains(@class,'filter-chip') and ./span[text()='%s']]", filterName)));
    }

    public void toggleViewVariant() {
        WebElement detailedViewButton = driver.findElement(By.xpath("//button[@title='Detailed view' and contains(@class,'view-toggle-btn')]"));
        if (detailedViewButton.getAttribute("class").contains("active")) {
            safeClick(By.xpath("//button[@title='Compact view' and contains(@class,'view-toggle-btn')]"));
        } else {
            safeClick(By.xpath("//button[@title='Detailed view' and contains(@class,'view-toggle-btn')]"));
        }
    }

    public void openFutureList() {
        safeClick(By.xpath("//button[contains(@class,'nav-trigger') and ./span[text()='Lists']]"));
        safeClick(By.xpath("//a[@href='/future']"));
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.urlContains("/future"));
    }
}
