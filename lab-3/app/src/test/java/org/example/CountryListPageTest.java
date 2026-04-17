package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.example.pages.CountryListPage;
import org.example.pages.PlayerListPage;
import org.example.pages.PlayerProfilePage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;

class CountryListPageTest {
    private final List<WebDriver> drivers = new ArrayList<>();

    @BeforeEach
    void setUp() {
        drivers.add(new ChromeDriver());
        // drivers.add(new FirefoxDriver());
    }

    @AfterEach
    void tearDown() {
        drivers.forEach(WebDriver::quit);
    }

    
    @ParameterizedTest
    @CsvSource({
        "United States, Zoink",
        "Canada, Zeronium",
        "Netherlands, knobbelboy"
    })
    void findCountryByNameTest(String countryName, String playerName) {
        drivers.forEach(driver -> {
            CountryListPage listPage = PageFactory.initElements(driver, CountryListPage.class);
            listPage.open()
                .consent()
                .closePopup()
                .acceptCookies();
            PlayerProfilePage profilePage = listPage.findCountryByName(countryName)
                                                .openCountryProfile(countryName)
                                                .openPlayerProfile(playerName);
            assertEquals(playerName, profilePage.getPlayerName());
        });
    }
}
