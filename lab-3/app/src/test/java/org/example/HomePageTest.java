package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.example.pages.HomePage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
    void openDemonListTest() {
        drivers.forEach(driver -> {
            HomePage page = PageFactory.initElements(driver, HomePage.class);
            page.open();
            page.consent();
            page.openDemonList();
            assertEquals("https://demonlist.org/classic", driver.getCurrentUrl());
        });
    }

    @Test
    void openPlayerListTest() {
        drivers.forEach(driver -> {
            HomePage page = PageFactory.initElements(driver, HomePage.class);
            page.open();
            page.consent();
            page.openPlayerList();
            assertEquals("https://demonlist.org/leaderboard/players", driver.getCurrentUrl());
        });
    }

    @Test
    void changeLanguageTest() {
        drivers.forEach(driver -> {
            HomePage page = PageFactory.initElements(driver, HomePage.class);
            page.open();
            page.consent();
            page.changeLanguage("Русский");
            assertEquals("Самый полный и авторитетный рейтинг сложнейших демонов Geometry Dash, поддерживаемый преданным сообществом.", page.getHeroHeading());
        });
    }
}
