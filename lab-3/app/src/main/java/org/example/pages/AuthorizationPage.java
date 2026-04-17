package org.example.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AuthorizationPage extends Page {
    public AuthorizationPage(WebDriver driver) {
        super(driver, "https://demonlist.org/signin");
    }

    public AuthorizationPage signIn(String email, String password) {
        safeClick(By.xpath("//input[@id='login']"));
        WebElement loginInput = driver.findElement(By.xpath("//input[@id='login']"));
        loginInput.sendKeys(email);

        safeClick(By.xpath("//input[@id='password']"));
        WebElement passwordInput = driver.findElement(By.xpath("//input[@id='password']"));
        passwordInput.sendKeys(password);

        safeClick(By.xpath("//button[contains(@class,'auth-submit')]"));
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'overlay')]")));
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(@class,'overlay')]")));

        return this;
    }

    public AuthorizationPage signOut() {
        safeClick(By.xpath("//button[@title='Sign out']"));

        return this;
    }

    public boolean isAuthorized() {
        return !isElementPresent(By.xpath("//a[@href='/signin']"));
    }

    public String getUsername() {
        return safeGetText(By.xpath("//a[contains(@class,'user-btn')]/span"));
    }
}
