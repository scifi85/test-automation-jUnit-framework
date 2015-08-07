package tools;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Selenium WebDriver wrapper which have a set of util method to help work with the web page.
 */
public class Browser {
    private WebDriver driver;

    private static final int TIME_OUT_SECONDS = 10;

    public Browser(WebDriver driver) {
        this.driver = driver;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public WebElement findElementByName(String locator) {
        WebElement element = (new WebDriverWait(driver, TIME_OUT_SECONDS))
                .until(ExpectedConditions.presenceOfElementLocated(By.name(locator)));
        return element;
    }

    public WebElement findElementById(String locator) {
        WebElement element = (new WebDriverWait(driver, TIME_OUT_SECONDS))
                .until(ExpectedConditions.presenceOfElementLocated(By.id(locator)));
        return element;
    }

    public WebElement findElementByLinkText(String locator) {
        WebElement element = (new WebDriverWait(driver, TIME_OUT_SECONDS))
                .until(ExpectedConditions.presenceOfElementLocated(By.linkText(locator)));
        return element;
    }

    public WebElement findElementByCssSelector(String locator) {
        WebElement element = (new WebDriverWait(driver, TIME_OUT_SECONDS))
                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(locator)));
        return element;
    }

    public WebElement findElementByXpath(String locator) {
        WebElement element = (new WebDriverWait(driver, TIME_OUT_SECONDS))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator)));
        return element;
    }

    public WebElement findElementByClassName(String locator) {
        WebElement element = (new WebDriverWait(driver, TIME_OUT_SECONDS))
                .until(ExpectedConditions.presenceOfElementLocated(By.className(locator)));
        return element;
    }

    public WebElement findElementByPartialLinkText(String locator) {
        WebElement element = (new WebDriverWait(driver, TIME_OUT_SECONDS))
                .until(ExpectedConditions.presenceOfElementLocated(By.partialLinkText(locator)));
        return element;
    }

    public WebElement findElementByTagName(String locator) {
        WebElement element = (new WebDriverWait(driver, TIME_OUT_SECONDS))
                .until(ExpectedConditions.presenceOfElementLocated(By.tagName(locator)));
        return element;
    }

    public List<WebElement> findElementsByTagName(String locator) {
        List<WebElement> element = (new WebDriverWait(driver, this.TIME_OUT_SECONDS))
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.tagName(locator)));
        return element;
    }

    public List<WebElement> findElementsByXpath(String locator) {
        List<WebElement> element = (new WebDriverWait(driver, this.TIME_OUT_SECONDS))
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(locator)));
        return element;
    }

    public void screenShot(String fileName) throws IOException {
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File(fileName));
    }

    public void alertAccept() {
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

    public void alertDismiss() {
        Alert alert = driver.switchTo().alert();
        alert.dismiss();
    }

    public void selectByVisibleText(WebElement e, String text) {
        new Select(e).selectByVisibleText(text);
    }

}
