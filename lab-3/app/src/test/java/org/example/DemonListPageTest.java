package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.example.pages.DemonListPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;

class DemonListPageTest {
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
        "Main, 1",
        "Main, 24",
        "Main, 75",
        "Extended, 76",
        "Extended, 113",
        "Extended, 150",
        "Advanced, 151",
        "Advanced, 200",
        "Advanced, 300",
        "Unbounded, 301",
        "Unbounded, 757",
        "Unbounded, 1650",
    })
    void openDemonTest(String sublistName, int position) {
        drivers.forEach(driver -> {
            DemonListPage page = PageFactory.initElements(driver, DemonListPage.class);
            page.open();
            page.consent();
            page.acceptCookies();
            page.toggleViewVariant();
            page.openDemon(position);
            assertEquals("https://demonlist.org/classic/" + position, driver.getCurrentUrl());
        });
    }

    @ParameterizedTest
    @ValueSource(strings = {"limbo but uwu ig idk", "Shock Breaker", "Prismatic Haze"})
    void findDemonTest(String demonName) {
        drivers.forEach(driver -> {
            DemonListPage page = PageFactory.initElements(driver, DemonListPage.class);
            page.open();
            page.consent();
            page.findDemonByName(demonName);
            String actualDemonName = page.openDemon(demonName);
            assertEquals(demonName, actualDemonName);
        });
    }

    @Test
    void viewFutureListTest() {
        drivers.forEach(driver -> {
            DemonListPage page = PageFactory.initElements(driver, DemonListPage.class);
            page.open();
            page.consent();
            page.openFutureList();
            String actualDemonName = page.openDemon("Aeternus");
            assertEquals("Aeternus", actualDemonName);
        });
    }
}
