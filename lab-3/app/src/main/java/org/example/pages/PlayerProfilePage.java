package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PlayerProfilePage extends Page {
    public PlayerProfilePage(WebDriver driver, int id) {
        super(driver, "https://demonlist.org/profile/" + id);
    }

    public String getPlayerName() {
        return safeGetText(By.xpath("//h1[contains(@class,'profile-username')]"));
    }
}
