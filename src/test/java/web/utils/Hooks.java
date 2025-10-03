package web.utils;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Hooks {
    public static WebDriver driver;

    // web/utils/Hooks.java
    @Before("@web")
    public void setUp() {
        io.github.bonigarcia.wdm.WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();

        driver.manage().timeouts().pageLoadTimeout(java.time.Duration.ofSeconds(30));
        driver.manage().timeouts().implicitlyWait(java.time.Duration.ZERO); // <-- matikan implicit
    }


    @After("@web")
    public void tearDown(Scenario scenario) {
        if (driver != null) {
            driver.quit();
        }
    }

    public static WebDriver getDriver() {
        return driver;
    }
}
