package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.example.pages.PlayerListPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;

class PlayerListPageTest {
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
    @ValueSource(strings = {"Zoink", "Zeronium", "Technical"})
    void findPlayerByNameTest(String playerName) {
        drivers.forEach(driver -> {
            PlayerListPage page = PageFactory.initElements(driver, PlayerListPage.class);
            page.open()
                .consent()
                .acceptCookies()
                .findPlayerByName(playerName)
                .openPlayerProfile(playerName);
            assertEquals(playerName, page.getSelectedPlayerName());
        });
    }
}
