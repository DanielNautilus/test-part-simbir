package org.demoqa.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    protected void clickElement(By locator) {
        WebElement element = driver.findElement(locator);
        element.click();
    }

    protected void setInputValue(By locator, String value) {
        WebElement element = driver.findElement(locator);
        element.clear();
        element.sendKeys(value);
    }
    protected void scrollToElement(By locator) {
        WebElement element = driver.findElement(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    protected void removeElement(By locator) {
        try {
            WebElement adBanner = driver.findElement(locator);
            ((JavascriptExecutor) driver).executeScript("arguments[0].remove();", adBanner);
        } catch (NoSuchElementException e) {
            // TBD
        }
    }
    protected void nativeClick(By locator) {
        var submitButton = driver.findElement(locator);
        var executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", submitButton);
    }
    protected void selectOptionInDropdown(By dropdownLocator, String option) {
        WebElement dropdown = driver.findElement(dropdownLocator);
        Select dropdownOptions = new Select(dropdown);
        dropdownOptions.selectByVisibleText(option);
    }
}
