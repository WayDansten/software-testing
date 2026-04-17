package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;

import org.example.pages.DemonListPage;
import org.example.utils.ArgumentSetup;
import org.example.utils.BrowserType;
import org.example.utils.DriverFactory;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

class DemonListPageTest {
    static Stream<Arguments> openDemonCases() {
        return ArgumentSetup.withBrowsersArgs(Stream.of(
            Arguments.of("Main", 1),
            Arguments.of("Main", 24),
            Arguments.of("Main", 75),
            Arguments.of("Extended", 76),
            Arguments.of("Extended", 113),
            Arguments.of("Extended", 150),
            Arguments.of("Advanced", 151),
            Arguments.of("Advanced", 200),
            Arguments.of("Advanced", 300),
            Arguments.of("Unbounded", 301),
            Arguments.of("Unbounded", 757),
            Arguments.of("Unbounded", 1650)
        ));
    }

    static Stream<Arguments> findDemonCases() {
        return ArgumentSetup.withBrowsers(Stream.of("limbo but uwu ig idk", "Shock Breaker", "Prismatic Haze"));
    }

    static Stream<Arguments> futureListCases() {
        return ArgumentSetup.withBrowsers(Stream.of("Aeternus"));
    }

    @ParameterizedTest
    @MethodSource("openDemonCases")
    void openDemonTest(BrowserType browser, String sublistName, int position) {
        WebDriver driver = DriverFactory.create(browser);
        try {
            DemonListPage page = PageFactory.initElements(driver, DemonListPage.class);
            page.open()
                .consent()
                .closePopup()
                .acceptCookies();
            page.toggleViewVariant()
                .enableFilter(sublistName)
                .openDemon(position);
            assertEquals("https://demonlist.org/classic/" + position, driver.getCurrentUrl());
        } finally {
            driver.quit();
        }
    }

    @ParameterizedTest
    @MethodSource("findDemonCases")
    void findDemonTest(BrowserType browser, String demonName) {
        WebDriver driver = DriverFactory.create(browser);
        try {
            DemonListPage page = PageFactory.initElements(driver, DemonListPage.class);
            page.open()
                .consent()
                .closePopup()
                .acceptCookies();
            String actualDemonName = page.findDemonByName(demonName)
                                        .openDemon(demonName);
            assertEquals(demonName, actualDemonName);
        } finally {
            driver.quit();
        }
    }

    @ParameterizedTest
    @MethodSource("futureListCases")
    void viewFutureListTest(BrowserType browser, String demonName) {
        WebDriver driver = DriverFactory.create(browser);
        try {
            DemonListPage page = PageFactory.initElements(driver, DemonListPage.class);
            page.open()
                .consent()
                .closePopup()
                .acceptCookies();
            String actualDemonName = page.openFutureList()
                                        .openDemon(demonName);
            assertEquals(demonName, actualDemonName);
        } finally {
            driver.quit();
        }
    }
}
