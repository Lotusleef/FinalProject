package web.utils;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Hooks {
    public static WebDriver driver;

    @Before("@web")
    public void setUp() {
        io.github.bonigarcia.wdm.WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");


        boolean headless = System.getenv("CI") != null || Boolean.getBoolean("headless");
        if (headless) {
            options.addArguments("--headless=new", "--disable-gpu",
                    "--no-sandbox", "--disable-dev-shm-usage", "--window-size=1920,1080");

            String chromeBin = System.getenv("CHROME_PATH");
            if (chromeBin != null && !chromeBin.isBlank()) {
                options.setBinary(chromeBin);
            }
        }

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(java.time.Duration.ofSeconds(30));
        driver.manage().timeouts().implicitlyWait(java.time.Duration.ZERO); // tetap 0
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
