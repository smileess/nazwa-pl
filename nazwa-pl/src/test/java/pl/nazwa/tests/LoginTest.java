package pl.nazwa.tests;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.v85.page.Page;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pl.nazwa.framework.ConfigReader;
import pl.nazwa.framework.PageObject;
import pl.nazwa.pages.HomePage;
import pl.nazwa.pages.LoginPage;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Every Test Case might fail due to reCAPTCHA")
public class LoginTest extends BaseTest {
    private ConfigReader configReader;

    private HomePage homePage;
    private LoginPage loginPage;
    By twoFactorAuthPage =  By.cssSelector("h2.loginHeader:contains('Weryfikacja dwuetapowa')");
    private String invalidLogin = "00invalid_us3rnam36969";
    private String loginLessThanMinCharCount = "3re";
    private String passwordMinCharCount = "6igits";
    private String passwordLessThanMinCharCount = "5char";
    @BeforeEach
    public void setUp() {
        homePage = new HomePage(driver);
        loginPage = new LoginPage(getDriver());
        configReader = new ConfigReader();
        driver.get(PageObject.LOGINPAGEURL);
    }
    @AfterEach
    public void tearDown() {
        quitDriver();
        }

    @Test
    @DisplayName("Assertion might not work due to 2FA authentication, could be mocked")
    public void testValidLoginAndPasswordLoggedUsername() {
        homePage.acceptAllCookies();
        loginPage.sendKeysToLoginInput(configReader.getUsername());
        loginPage.sendKeysToPasswordInput(configReader.getPassword());
        String login = loginPage.getTextFromLogin();
        loginPage.clickLogin();
        Assertions.assertEquals(login, loginPage.getLoggedInUsername());
        }
    @Test
    @DisplayName("Should work if not reCAPTCHA")
    public void testValidLoginAndPassword2FA() {
        homePage.acceptAllCookies();
        loginPage.sendKeysToLoginInput(configReader.getUsername());
        loginPage.sendKeysToPasswordInput(configReader.getPassword());
        String login = loginPage.getTextFromLogin();
        loginPage.clickLogin();
        assertTrue(loginPage.getVisibleHeader(twoFactorAuthPage).isDisplayed(), "2FA Header not found");
    }

    @Test
    @DisplayName("Invalid login and minimum character count (6) for passwordInput")
    public void testInvalidLogin() {
        homePage.acceptAllCookies();
        loginPage.sendKeysToLoginInput(invalidLogin);
        loginPage.sendKeysToPasswordInput(passwordMinCharCount);
        loginPage.clickLogin();
        assertTrue(loginPage.invalidLoginPasswordAlert(), "Login should have not been successful");
    }
    @Test
    @DisplayName("Less than minimum password character count (5) for passwordInput")
    public void testLessThanMinimumPasswordCharCount() {
        homePage.acceptAllCookies();
        loginPage.sendKeysToPasswordInput(passwordLessThanMinCharCount);
        assertTrue(loginPage.clientPassError(),
                "5 and less should invoke error:" +
                " 'Podaj prawidłowe hasło.'" );
    }
    @Test
    @DisplayName("Less than minimum login character count (3) for loginInput")
    public void testLessThanMinimumLoginCharCount(){

        homePage.acceptAllCookies();
        loginPage.sendKeysToLoginInput(loginLessThanMinCharCount);
        assertTrue(loginPage.clientLoginError(),
                "3 and less should invoke error:" +
                        " 'Podaj prawidłowy login.'" );
        // Registration form requires at least 4 characters, so login form is not in line.
    }
    @Test
    @DisplayName("Placeholder for reCAPTCHA loophole")
    public void testLoginBypassRobotCheck() {
        homePage.acceptAllCookies();
        loginPage.sendKeysToLoginInput(configReader.getUsername());
        loginPage.sendKeysToPasswordInput(configReader.getPassword());
        String login = loginPage.getTextFromLogin();
        loginPage.clickLogin();
        //if loginPage.notARobotAlert visible -> get current GOOGLE_ABUSE_EXEMPTION/all cookies
        // slow down actions and try to move on
        Assertions.assertEquals(login, loginPage.getLoggedInUsername());
    }
}



