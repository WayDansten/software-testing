package org.example.pages;

import org.openqa.selenium.WebDriver;

public class CountryListPage extends Page {
    public CountryListPage(WebDriver driver) {
        super(driver, "https://demonlist.org/leaderboard/countries");
    }
}
