package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.example.pages.HomePage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;

class HomePageTest {
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

    @Test
    void openDemonListPageTest() {
        drivers.forEach(driver -> {
            HomePage page = PageFactory.initElements(driver, HomePage.class);
            page.open();
            page.openDemonListPage();
            assertEquals("https://demonlist.org/classic", driver.getCurrentUrl());
        });
    }

    @Test
    void openPlayerListPageTest() {
        drivers.forEach(driver -> {
            HomePage page = PageFactory.initElements(driver, HomePage.class);
            page.open();
            page.openPlayerListPage();
            assertEquals("https://demonlist.org/leaderboard/players", driver.getCurrentUrl());
        });
    }

    @Test
    void openAuthorizationPageTest() {
        drivers.forEach(driver -> {
            HomePage page = PageFactory.initElements(driver, HomePage.class);
            page.open();
            page.openAuthorizationPage();
            assertEquals("https://demonlist.org/signin", driver.getCurrentUrl());
        });
    }

    @ParameterizedTest
    @CsvSource({
        "English, The most complete and trusted ranking of the hardest Geometry Dash demons, maintained by a dedicated community.",
        "Русский, Самый полный и авторитетный рейтинг сложнейших демонов Geometry Dash, поддерживаемый преданным сообществом.",
        "Español, El ranking más completo y confiable de los demonios más difíciles de Geometry Dash, mantenido por una comunidad dedicada."
    })
    void changeLanguageTest(String language, String title) {
        drivers.forEach(driver -> {
            HomePage page = PageFactory.initElements(driver, HomePage.class);
            page.open();
            page.changeLanguage(language);
            assertEquals(title, page.getHeroHeading());
        });
    }
}
