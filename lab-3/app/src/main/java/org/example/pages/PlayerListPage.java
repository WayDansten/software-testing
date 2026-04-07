package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PlayerListPage extends Page {
    public PlayerListPage(WebDriver driver) {
        super(driver, "https://demonlist.org/leaderboard/players");
    }

    public PlayerListPage openPlayerProfile(String playerName) {
        safeClick(By.xpath(String.format("//button[./span[text()='%s']]", playerName)));

        return this;
    }

    public PlayerListPage findPlayerByName(String playerName) {
        safeClick(By.xpath("//input[contains(@class,'search-input')]"));
        WebElement searchInput = driver.findElement(By.xpath("//input[contains(@class,'search-input')]"));
        searchInput.sendKeys(playerName);
        searchInput.sendKeys(Keys.ENTER);

        return this;
    }

    public String getSelectedPlayerName() {
        return safeGetText(By.xpath("//a[contains(@class,'player-username')]"));
    }
}
