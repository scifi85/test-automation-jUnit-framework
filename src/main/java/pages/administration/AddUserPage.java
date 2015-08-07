package pages.administration;

import org.openqa.selenium.WebDriver;
import pages.BasePage;
import tools.Browser;

public class AddUserPage extends BasePage {

  private Browser browser;

  private final static String LOGIN = "user";
  private final static String PASSWORD = "pass";
  private final static String FIRSTNAME = "Vasia";
  private final static String LASTNAME = "Pupkin";
  private final static String EMAIL = "fake@mail.com";

  public AddUserPage(WebDriver driver) {
    super(driver);
  }

  public UsersPage AddUser() {
    browser.findElementById("login").clear();
    browser.findElementById("login").sendKeys("user1");
    browser.findElementById("firstName").clear();
    browser.findElementById("firstName").sendKeys("name");
    browser.findElementById("lastName").clear();
    browser.findElementById("lastName").sendKeys("lastname");
    browser.findElementById("password").clear();
    browser.findElementById("password").sendKeys("pass");
    browser.findElementById("confirmPassword").clear();
    browser.findElementById("confirmPassword").sendKeys("pass");
    browser.findElementById("email").clear();
    browser.findElementById("email").sendKeys("someemail@example.com");
    browser.findElementByCssSelector("input[type=\"submit\"]").click();

    return new UsersPage(browser.getDriver());
  }

}
