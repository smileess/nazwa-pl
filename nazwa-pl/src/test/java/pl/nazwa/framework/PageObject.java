package pl.nazwa.framework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pl.nazwa.pages.HomePage;
import pl.nazwa.tests.BaseTest;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import static java.awt.SystemColor.text;

public abstract class PageObject{
    public static final String HOMEPAGE = "https://nazwa.pl";
    public static final String LOGINPAGEURL = "https://www.nazwa.pl/panel/zaloguj-sie.html";
    public static final Duration TIMEOUT = Duration.ofSeconds(3);
    protected WebDriver driver;
    protected WebDriverWait wait;

    ConfigReader configReader = new ConfigReader();
    protected PageObject(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, TIMEOUT);
    }
    public final WebElement waitUntilElementIsVisible(By by) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }
    protected void clickOnWebElement(By by) {
        WebElement element = waitUntilElementIsVisible(by);
        element.click();
    }
    protected void enterTextToInput(By by, String text) {
       WebElement element = waitUntilElementIsVisible(by);
        element.sendKeys(text);
    }
    protected String getText(By by) {
        WebElement element = waitUntilElementIsVisible(by);
        return element.getText();
    }
    protected String getTextFromInput(By by) {
        WebElement element = waitUntilElementIsVisible(by);
        return element.getAttribute("value");
    }


}
