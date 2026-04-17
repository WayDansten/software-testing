package org.example.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PlayerListPage extends Page {
    public PlayerListPage(WebDriver driver) {
        super(driver, "https://demonlist.org/leaderboard/players");
    }

    public PlayerProfilePage openPlayerProfile(String playerName) {
        safeClick(By.xpath(String.format("//button[./span[text()='%s']]", playerName)));
        safeClick(By.xpath("//a[contains(@class,'player-username')]"));

        WebElement playerLink = driver.findElement(By.xpath("//a[contains(@class,'player-username')]"));
        int id = Integer.parseInt(playerLink.getAttribute("href").substring("https://demonlist.org/profile/".length()));
        
        List<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size() - 1));

        return new PlayerProfilePage(driver, id);
    }

    public PlayerListPage findPlayerByName(String playerName) {
        safeClick(By.xpath("//input[contains(@class,'search-input')]"));
        WebElement searchInput = driver.findElement(By.xpath("//input[contains(@class,'search-input')]"));
        searchInput.sendKeys(playerName);
        searchInput.sendKeys(Keys.ENTER);

        return this;
    }
}
