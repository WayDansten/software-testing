package org.example.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CountryListPage extends Page {
    public CountryListPage(WebDriver driver) {
        super(driver, "https://demonlist.org/leaderboard/countries");
    }

    public CountryListPage openCountryProfile(String countryName) {
        safeClick(By.xpath(String.format("//button[contains(@class,'country-card') and ./span[text()='%s']]", countryName)));

        return this;
    }

    public CountryListPage findCountryByName(String countryName) {
        safeClick(By.xpath("//input[contains(@class,'search-input')]"));
        WebElement searchInput = driver.findElement(By.xpath("//input[contains(@class,'search-input')]"));
        searchInput.sendKeys(countryName);
        searchInput.sendKeys(Keys.ENTER);

        return this;
    }

    public PlayerProfilePage openPlayerProfile(String playerName) {
        safeClick(By.xpath(String.format("//a[contains(@class,'player-row') and ./span[text()='%s']]", playerName)));
        
        WebElement playerRow = driver.findElement(By.xpath(String.format("//a[contains(@class,'player-row') and ./span[text()='%s']]", playerName)));
        int id = Integer.parseInt(playerRow.getAttribute("href").substring("https://demonlist.org/profile/".length()));
        
        List<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size() - 1));

        return new PlayerProfilePage(driver, id);
    }

    public String getSelectedCountryName() {
        return safeGetText(By.xpath("//a[contains(@class,'country-hero-name')]"));
    }
}
