package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;

import org.example.pages.HomePage;
import org.example.utils.ArgumentSetup;
import org.example.utils.BrowserType;
import org.example.utils.DriverFactory;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

class HomePageTest {
    static Stream<BrowserType> browserCases() {
        return ArgumentSetup.browsers();
    }

    static Stream<Arguments> languageCases() {
        return ArgumentSetup.withBrowsersArgs(Stream.of(
            Arguments.of("English", "The most complete and trusted ranking of the hardest Geometry Dash demons, maintained by a dedicated community."),
            Arguments.of("Русский", "Самый полный и авторитетный рейтинг сложнейших демонов Geometry Dash, поддерживаемый преданным сообществом."),
            Arguments.of("Español", "El ranking más completo y confiable de los demonios más difíciles de Geometry Dash, mantenido por una comunidad dedicada.")
        ));
    }

    @ParameterizedTest
    @MethodSource("browserCases")
    void openDemonListPageTest(BrowserType browser) {
        WebDriver driver = DriverFactory.create(browser);
        try {
            HomePage page = PageFactory.initElements(driver, HomePage.class);
            page.open()
                .consent()
                .closePopup()
                .acceptCookies();
            page.openDemonListPage();
            assertEquals("https://demonlist.org/classic", driver.getCurrentUrl());
        } finally {
            driver.quit();
        }
    }

    @ParameterizedTest
    @MethodSource("browserCases")
    void openPlayerListPageTest(BrowserType browser) {
        WebDriver driver = DriverFactory.create(browser);
        try {
            HomePage page = PageFactory.initElements(driver, HomePage.class);
            page.open()
                .consent()
                .closePopup()
                .acceptCookies();
            page.openPlayerListPage();
            assertEquals("https://demonlist.org/leaderboard/players", driver.getCurrentUrl());
        } finally {
            driver.quit();
        }
    }

    @ParameterizedTest
    @MethodSource("browserCases")
    void openAuthorizationPageTest(BrowserType browser) {
        WebDriver driver = DriverFactory.create(browser);
        try {
            HomePage page = PageFactory.initElements(driver, HomePage.class);
            page.open()
                .consent()
                .closePopup()
                .acceptCookies();
            page.openAuthorizationPage();
            assertEquals("https://demonlist.org/signin", driver.getCurrentUrl());
        } finally {
            driver.quit();
        }
    }

    @ParameterizedTest
    @MethodSource("languageCases")
    void changeLanguageTest(BrowserType browser, String language, String title) {
        WebDriver driver = DriverFactory.create(browser);
        try {
            HomePage page = PageFactory.initElements(driver, HomePage.class);
            page.open()
                .consent()
                .closePopup()
                .acceptCookies();
            page.changeLanguage(language);
            assertEquals(title, page.getHeroHeading());
        } finally {
            driver.quit();
        }
    }
}
