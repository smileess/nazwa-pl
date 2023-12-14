package pl.nazwa.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pl.nazwa.framework.PageObject;
import pl.nazwa.tests.BaseTest;

import java.time.Duration;

public class HomePage extends PageObject {
    public HomePage(WebDriver driver) {
        super(driver);
    }
    By acceptCookiesSaveAllButton = By.className("modal-box__buttons--save-all");
    By clientPanelButton = By.className("login_pk_link");

    By cookiesSettings = By.className("modal-box__buttons--settings");

    By necessaryCookiesToggle = By.cssSelector("input.option__toggle-checkbox[data-option=\"security_storage\"]");

    By saveCookies = By.className("modal-box__buttons--save");

    By loginUserName = By.id("login_user_name");

    public void acceptAllCookies() {
        clickOnWebElement(acceptCookiesSaveAllButton);
    }
    public void acceptCookies() {
        clickOnWebElement(saveCookies);
    }
    public void showCookiesSettings() {
        clickOnWebElement(cookiesSettings);
    }
    public void testClickDoesNotChangePageState() {
        String initialState = driver.getPageSource();
        clickOnWebElement(necessaryCookiesToggle);
        String currentState = driver.getPageSource();
        Assertions.assertEquals(initialState, currentState, "Page state has changed.");
        // to be moved to PageObject
        // to be Refactored with different solution as Tests tends to fail due to PageState changing at
        // document.querySelector("head > script:nth-child(15)")
        // <meta> tag

    }
    public void clickClientPanel(){
    clickOnWebElement(clientPanelButton);
    }
}