package web.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MenuPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By menuBtn = By.id("react-burger-menu-btn");
    private final By logoutLink = By.id("logout_sidebar_link");
    private final By loginBtn = By.id("login-button"); // marker halaman login

    public MenuPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void logout() {
        wait.until(ExpectedConditions.elementToBeClickable(menuBtn)).click();
        wait.until(ExpectedConditions.elementToBeClickable(logoutLink)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginBtn));
    }

    public boolean onLoginPage() {
        return !driver.findElements(loginBtn).isEmpty();
    }
}
