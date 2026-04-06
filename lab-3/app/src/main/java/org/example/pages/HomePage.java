package org.example.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends Page {
    public HomePage(WebDriver driver) {
        super(driver, "https://demonlist.org/");
    }

    public String getHeroHeading() {
        return safeGetText(By.xpath("//h1[contains(@class, 'hero-heading')]"));
    }

    public void openDemonList() {
        safeClick(By.xpath("//a[@href='/classic']"));
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.urlContains("/classic"));
    }

    public void openPlayerList() {
        safeClick(By.xpath("//button[./span[text()='More']]"));
        safeClick(By.xpath("//a[@href='/leaderboard/players']"));
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.urlContains("/leaderboard/players"));
    }

    public void changeLanguage(String language) {
        safeClick(By.xpath("//nav[contains(@class,'desktop-nav')]/div[last()]/button"));
        safeClick(By.xpath(String.format("//button[contains(@class,'dropdown-item') and ./span[text()='%s']]", language)));
    }
}
