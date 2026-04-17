package org.example;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.stream.Stream;

import org.example.pages.AuthorizationPage;
import org.example.utils.ArgumentSetup;
import org.example.utils.BrowserType;
import org.example.utils.DriverFactory;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

class AuthorizationPageTest {
    static Stream<BrowserType> authCases() {
        return ArgumentSetup.browsers();
    }

    @ParameterizedTest
    @MethodSource("authCases")
    void signInTest(BrowserType browser) {
        String username = System.getenv("TEST_USERNAME");
        String password = System.getenv("TEST_PASSWORD");
        WebDriver driver = DriverFactory.create(browser);
        try {
            AuthorizationPage page = PageFactory.initElements(driver, AuthorizationPage.class);
            page.open()
                .consent()
                .closePopup()
                .acceptCookies();
            page.signIn(username, password);
            assertTrue(page.isAuthorized());
        } finally {
            driver.quit();
        }
    }

    @ParameterizedTest
    @MethodSource("browserCases")
    void signOutTest(BrowserType browser) {
        String username = System.getenv("TEST_USERNAME");
        String password = System.getenv("TEST_PASSWORD");
        WebDriver driver = DriverFactory.create(browser);
        try {
            AuthorizationPage page = PageFactory.initElements(driver, AuthorizationPage.class);
            page.open()
                .consent()
                .closePopup()
                .acceptCookies();
            page.signIn(username, password).signOut();
            assertFalse(page.isAuthorized());
        } finally {
            driver.quit();
        }
    }
}
