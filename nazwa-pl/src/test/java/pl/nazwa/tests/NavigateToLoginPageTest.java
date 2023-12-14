package pl.nazwa.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pl.nazwa.framework.ConfigReader;
import pl.nazwa.framework.PageObject;
import pl.nazwa.pages.HomePage;
import pl.nazwa.tests.BaseTest;

import static pl.nazwa.framework.PageObject.LOGINPAGEURL;

public class NavigateToLoginPageTest extends BaseTest {

    private HomePage homePage;
    @BeforeEach
    public void setUp() {
        homePage = new HomePage(driver);
        driver.get(PageObject.HOMEPAGE);
    }
    @AfterEach
    public void tearDown() {
        quitDriver();
    }
    @Test
    public void testAcceptAllCookies() {
        homePage.acceptAllCookies();
        homePage.clickClientPanel();
        Assertions.assertTrue(driver.getCurrentUrl().contains(LOGINPAGEURL));
        System.out.println(driver.getCurrentUrl());
    }
    @Test
    public void testAcceptNecessaryCookies() {
        homePage.showCookiesSettings();
        homePage.testClickDoesNotChangePageState();
        homePage.acceptCookies();
        homePage.clickClientPanel();
        Assertions.assertTrue(driver.getCurrentUrl().contains(LOGINPAGEURL));
        System.out.println(driver.getCurrentUrl());


    }

}

