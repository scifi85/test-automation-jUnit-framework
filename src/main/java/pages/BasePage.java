package pages;

import org.openqa.selenium.WebDriver;
import pages.auth.UserInfoPage;
import tools.Browser;
import tools.PropertiesProvider;

import java.io.IOException;

public class BasePage {
//    protected WebDriver driver;
    protected Browser browser;

    private static final String BASEURL = PropertiesProvider.getProperty("base.url");
    private static final String LOGIN_INPUT_NAME_LOCATOR = "j_username";
    private static final String PASSWORD_INPUT_NAME_LOCATOR = "j_password";
    private static final String SUBMIT_BUTTON_NAME_LOCATOR = "submit";
    private static final String LOGOUT_ID = "logout";
   
    public BasePage(WebDriver driver) {
        browser = new Browser(driver);
    }

    public Browser getBrowser() {
        return this.browser;
    }

    public UserInfoPage gotoRoot() {
        browser.getDriver().get(BASEURL);
        return new UserInfoPage(browser.getDriver());
    }

    public void goToUrl(String url) {
        browser.getDriver().get(url);
    }

    public UserInfoPage logout() {
        browser.findElementById(LOGOUT_ID).click();
        browser.alertAccept();
        return new UserInfoPage(browser.getDriver());
    }

    /**
     * hint: it's not necessary to return UserInfoPage
     * You may use just any_page.login(name, pass)
     */
    public UserInfoPage login(String userName, String password) {
        browser.findElementByName(LOGIN_INPUT_NAME_LOCATOR).sendKeys(userName);
        browser.findElementByName(PASSWORD_INPUT_NAME_LOCATOR).sendKeys(password);
        browser.findElementByName(SUBMIT_BUTTON_NAME_LOCATOR).click();
        return new UserInfoPage(browser.getDriver());
    }
    public void screenShot(String fileName) {
        try {
            browser.screenShot(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
   }

