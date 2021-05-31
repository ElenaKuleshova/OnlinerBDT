package framework.elements;

import framework.Browser;
import framework.BrowserFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.io.File;
import java.util.List;
import java.util.Set;


import static framework.Browser.getTimeoutForPageLoad;
import static framework.Browser.getTimeoutForCondition;

public class BaseElement {
    protected static Browser browser = Browser.getInstance();
    protected WebElement webElement;
    protected List<WebElement> webElements;
    protected By locator;
    protected Actions actions = new Actions(browser.getDriver());

    public BaseElement(By locator) {
        this.locator = locator;
            }

    public WebElement getElement() {
        return new WebDriverWait(Browser.getDriver(), Long.parseLong(getTimeoutForCondition()))
                .until(driver -> driver.findElement(locator));
    }

    public WebElement getElementByLocator(By locator) {
        return new WebDriverWait(Browser.getDriver(), Long.parseLong(getTimeoutForCondition()))
                .until(driver -> driver.findElement(locator));
    }

    public List<WebElement> getElements() {
        return new WebDriverWait(Browser.getDriver(), Long.parseLong(getTimeoutForCondition()))
                .until(driver -> driver.findElements(locator));
    }

    public void moveMouse() {
        waitElementBeVisible();
        actions.moveToElement(getElement()).perform();
        if (browser.getDriver() instanceof JavascriptExecutor) {
            ((JavascriptExecutor) browser.getDriver()).executeScript("arguments[0].style.border='3px solid red'", getElement());
        }
    }

    public void waitInvisibilityText(String text) {
        WebDriverWait wait = new WebDriverWait(Browser.getDriver(), Long.parseLong(getTimeoutForCondition()));
        wait.until(ExpectedConditions.invisibilityOfElementWithText(locator, text));
    }

    public void clickElement() {
        waitElementBeClickable();
        if (browser.getDriver() instanceof JavascriptExecutor) {
            ((JavascriptExecutor) browser.getDriver()).executeScript("arguments[0].style.border='3px solid red'", getElement());
        }
        getElement().click();
    }

    public void clickAndWait() {
        clickElement();
        waitForPageToLoad();
    }

    public void clickElementJS() {
        JavascriptExecutor executor = (JavascriptExecutor) Browser.getDriver();
        executor.executeScript("arguments[0].click();", getElement());
    }

    public void waitElementBeClickable() {
       WebDriverWait wait = new WebDriverWait(Browser.getDriver(), Long.parseLong(getTimeoutForCondition()));
        wait.until(ExpectedConditions.elementToBeClickable(getElement()));
    }

    public void waitElementBeVisible() {
        WebDriverWait wait = new WebDriverWait(Browser.getDriver(), Long.parseLong(getTimeoutForCondition()));
        wait.until(ExpectedConditions.visibilityOf(getElement()));
    }
    public void waitElementBePresent(By locator){
        WebDriverWait wait = new WebDriverWait(Browser.getDriver(),  Long.parseLong(getTimeoutForCondition()));
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public String getText() {
        waitElementBeClickable();
        return getElement().getText();
    }
    public void sendKeys(String key)
    {
        waitElementBeClickable();
        getElement().sendKeys(key);
    }







    public boolean isElementContainsGivenValue(int givenValue){
        String elementText = getElement().getText();
        elementText = elementText.substring(1,elementText.length()-1);
        return Integer.parseInt(elementText) == givenValue;
    }

    public boolean isElementPresentedOnPage(){
        boolean elementPresence = true;
        try {
            getElementByLocator(locator);
        } catch (Exception e){
            elementPresence = false;
        }
    return elementPresence;
    }

    public void waitForPageToLoad() {
        WebDriverWait wait = new WebDriverWait(browser.getDriver(), Long.parseLong(getTimeoutForPageLoad()));
        wait.until((ExpectedCondition<Boolean>) new ExpectedCondition<Boolean>() {
            public Boolean apply(final WebDriver d) {
                Object result = ((JavascriptExecutor) d)
                        .executeScript("return document['readyState'] ? 'complete' == document.readyState : true");

                if (result != null && result instanceof Boolean && (Boolean) result) {
                    return true;
                }
                return false;
            }
        });
    }

}
