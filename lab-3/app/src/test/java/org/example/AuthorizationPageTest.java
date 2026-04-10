package org.example;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.example.pages.AuthorizationPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;

class AuthorizationPageTest {
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
    void signInTest() {
        String username = System.getenv("TEST_USERNAME");
        String password = System.getenv("TEST_PASSWORD");
        drivers.forEach(driver -> {
            AuthorizationPage page = PageFactory.initElements(driver, AuthorizationPage.class);
            page.open();
            page.signIn(username, password);
            assertTrue(page.isAuthorized());
        });
    }

    @Test
    void signOutTest() {
        String username = System.getenv("TEST_USERNAME");
        String password = System.getenv("TEST_PASSWORD");
        drivers.forEach(driver -> {
            AuthorizationPage page = PageFactory.initElements(driver, AuthorizationPage.class);
            page.open();
            page.signIn(username, password).signOut();
            assertFalse(page.isAuthorized());
        });
    }
}
