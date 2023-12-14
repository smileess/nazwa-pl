package pl.nazwa.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pl.nazwa.framework.ConfigReader;

public abstract class BaseTest {
    protected WebDriver driver;
    public BaseTest() {
        initDriver();

    }
    private void initDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments(
                "--disable-gpu",
                "--start-maximized",
                "--ignore-certificate-errors",
                "--disable-extensions",
                "--no-sandbox",
                "--disable-dev-shm-usage",
                "--remote-allow-origins=*"
        );
        driver = new ChromeDriver(options);
    }
    public void quitDriver() {
        if (driver != null) {
            driver.quit();
        }
    }
    public WebDriver getDriver() {
        return driver;
    }
}
