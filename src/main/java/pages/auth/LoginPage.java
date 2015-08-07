package pages.auth;

import org.openqa.selenium.WebDriver;

import pages.BasePage;

public class LoginPage extends BasePage {

    private static final String MESSAGE_LOCATOR = "font";

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public UserInfoPage login(String userName, String password) {
        return super.login(userName, password);
    }

    public UserInfoPage logout() {
        return super.logout();
    }

    public String findMessageText() {
        return browser.findElementByCssSelector(MESSAGE_LOCATOR).getText();
    }
}
