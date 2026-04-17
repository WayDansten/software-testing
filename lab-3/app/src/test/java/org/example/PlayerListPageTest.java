package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;

import org.example.pages.PlayerListPage;
import org.example.pages.PlayerProfilePage;
import org.example.utils.ArgumentSetup;
import org.example.utils.BrowserType;
import org.example.utils.DriverFactory;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

class PlayerListPageTest {
    static Stream<Arguments> playerCases() {
        return ArgumentSetup.withBrowsers(Stream.of("Zoink", "Zeronium", "Technical"));
    }
    
    @ParameterizedTest
    @MethodSource("playerCases")
    void findPlayerByNameTest(BrowserType browser, String playerName) {
        WebDriver driver = DriverFactory.create(browser);
        try {
            PlayerListPage listPage = PageFactory.initElements(driver, PlayerListPage.class);
            listPage.open()
                .consent()
                .closePopup()
                .acceptCookies();
            PlayerProfilePage profilePage = listPage.findPlayerByName(playerName)
                                                .openPlayerProfile(playerName);
            assertEquals(playerName, profilePage.getPlayerName());
        } finally {
            driver.quit();
        }
    }
}
