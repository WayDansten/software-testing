package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;

import org.example.pages.CountryListPage;
import org.example.pages.PlayerProfilePage;
import org.example.utils.ArgumentSetup;
import org.example.utils.BrowserType;
import org.example.utils.DriverFactory;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

class CountryListPageTest {
    static Stream<Arguments> countryCases() {
        return ArgumentSetup.withBrowsersArgs(Stream.of(
            Arguments.of("United States", "Zoink"),
            Arguments.of("Canada", "Zeronium"),
            Arguments.of("Netherlands", "knobbelboy")
        ));
    }

    @ParameterizedTest
    @MethodSource("countryCases")
    void findCountryByNameTest(BrowserType browser, String countryName, String playerName) {
        WebDriver driver = DriverFactory.create(browser);
        try {
            CountryListPage listPage = PageFactory.initElements(driver, CountryListPage.class);
            listPage.open()
                .consent()
                .closePopup()
                .acceptCookies();
            PlayerProfilePage profilePage = listPage.findCountryByName(countryName)
                                                .openCountryProfile(countryName)
                                                .openPlayerProfile(playerName);
            assertEquals(playerName, profilePage.getPlayerName());
        } finally {
            driver.quit();
        }
    }
}
