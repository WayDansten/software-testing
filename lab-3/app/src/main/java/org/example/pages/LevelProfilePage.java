package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LevelProfilePage extends Page {
    public LevelProfilePage(WebDriver driver, int position) {
        super(driver, "https://demonlist.org/classic/" + position);
    }

    public LevelProfilePage playVideoCompletion() {
        safeClick(By.xpath("//button[@class='ytmCuedOverlayPlayButton']"));

        return this;
    }

    public boolean isVideoPlaying() {
        return !isElementPresent(By.xpath("//button[@class='ytmCuedOverlayPlayButton']"));
    }
}
