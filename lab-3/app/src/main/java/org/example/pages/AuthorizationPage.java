package org.example.pages;

import org.example.pages.Page;

public class AuthorizationPage extends Page {
    public CountryListPage(WebDriver driver) {
        super(driver, "https://demonlist.org/signin");
    }
}
