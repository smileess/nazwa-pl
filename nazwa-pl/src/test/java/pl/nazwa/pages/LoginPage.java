package pl.nazwa.pages;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pl.nazwa.framework.ConfigReader;
import pl.nazwa.framework.PageObject;

public class LoginPage extends PageObject {
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    ConfigReader configReader = new ConfigReader();

    By loginInput = By.id("clientLogin");
    By passwordInput = By.id("clientPass");
    By loginButton = By.id("__submit_PK_M0118");
    By loggedUserName = By.id("login_user_name");
    By invalidLoginPasswordAlert = By.className("ERROR_0118A1");
    By notARobotAlert = By.className("ERROR_0118A1");

    By clientPassError = By.id("clientPass_error");

    By clientLoginError = By.id("clientLogin_error");

    public void sendKeysToLoginInput(String text) {
        enterTextToInput(loginInput, text);
    }

    public void sendKeysToPasswordInput(String text) {
        enterTextToInput(passwordInput, text);
    }

    public String getTextFromLogin() {
        return getTextFromInput(loginInput);

    }

    public void clickLogin() {
        clickOnWebElement(loginButton);
    }

    public String getLoggedInUsername() {
        return getText(loggedUserName);
    }

    public WebElement getVisibleHeader(By by) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public boolean invalidLoginPasswordAlert() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(invalidLoginPasswordAlert)).isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean clientLoginError() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(clientLoginError)).isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean clientPassError() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(clientPassError)).isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

}
