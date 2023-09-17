package org.demoqa.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class RegistrationPage {
    private final WebDriver driver;
    public static WebDriverWait wait;

    public RegistrationPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    //Locators
    private final By firstNameInputLocator = By.id("firstName");
    private final By lastNameInputLocator = By.id("lastName");
    private final By emailInputLocator = By.id("userEmail");
    private final By maleGenderRadioLabelLocator = By.cssSelector("label[for='gender-radio-1']");
    private final By femaleGenderRadioLabelLocator = By.cssSelector("label[for='gender-radio-2']");
    private final By otherGenderRadioLabelLocator = By.cssSelector("label[for='gender-radio-3']");
    private final By mobileInputLocator = By.id("userNumber");

    //DatePicker
    private final By dateOfBirthInputLocator = By.id("dateOfBirthInput");
    private final By datePickerLocator = By.className("react-datepicker");
    private final By monthSelectAtDatePickerLocator = By.cssSelector(".react-datepicker__month-select");
    private final By yearSelectDatePickerLocator = By.cssSelector(".react-datepicker__year-select");
    private final By daysInDatePickerLocator = By.cssSelector(".react-datepicker__day");

    //Autocomplete
    private final By subjectsInputLocator = By.id("subjectsInput");
    private final By subjectsOptionsListLocator = By.cssSelector(".subjects-auto-complete__menu");
    private final By subjectsOptionLocator = By.cssSelector(".subjects-auto-complete__option"); //react-select-2-option-0

    private final By stateInputLocator = By.id("react-select-3-input");
    private final By stateOptionsListLocator = By.cssSelector(".css-26l3qy-menu");
    private final By stateOptionLocator = By.cssSelector("[id^='react-select-3-option-']");

    private final By cityInputLocator = By.id("react-select-4-input");
    private final By cityOptionsListLocator = By.cssSelector(".css-26l3qy-menu");
    private final By cityOptionLocator = By.cssSelector("[id^='react-select-4-option-']");


    private final By submitButtonLocator = By.id("submit");

    private final By currentAddressInputLocator = By.id("currentAddress");

    private final By fileUploadLocator = By.id("uploadPicture");

    private final By bannerLocator = By.id("adplus-anchor");
    private final By footerLocator = By.tagName("footer");

    //Helpers
    private void setInputValue(By locator, String value) {
        driver.findElement(locator).sendKeys(value);
    }

    private void clickElement(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    private String getInputValue(By locator) {
        return driver.findElement(locator).getAttribute("value");
    }

    private void selectOptionInDropdown(By dropdownLocator, String option) {
        WebElement dropdown = driver.findElement(dropdownLocator);
        Select dropdownOptions = new Select(dropdown);
        dropdownOptions.selectByVisibleText(option);
    }

    private String getDaySuffix(String dayStr) {
        int dayInt = Integer.parseInt(dayStr);

        if (dayInt >= 11 && dayInt <= 13) {
            return "th";
        }
        return switch (dayInt % 10) {
            case 1 -> "st";
            case 2 -> "nd";
            case 3 -> "rd";
            default -> "th";
        };
    }

    public void scrollToElement(By locator) {
        WebElement element = driver.findElement(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    private void selectOptionInAutocomplete(
            By autocompleteInputLocator,
            By autocompleteOptionsListLocator,
            By autocompleteOptionsLocator,
            String optionText) {
        scrollToElement(autocompleteInputLocator);
        setInputValue(autocompleteInputLocator, optionText);
        scrollToElement(autocompleteOptionsListLocator);
        WebElement autocompleteOptions = wait.until(ExpectedConditions.visibilityOfElementLocated(autocompleteOptionsListLocator));
        List<WebElement> options = autocompleteOptions.findElements(autocompleteOptionsLocator);
        for (WebElement option : options) {
            if (option.getText().equals(optionText)) {
                option.click();
                return;
            }
        }
        throw new NoSuchElementException("Option with text: '" + optionText + "' not found in autocomplete.");
    }

    private void removeElement(By locator) {
        try {
            WebElement adBanner = driver.findElement(locator);
            ((JavascriptExecutor) driver).executeScript("arguments[0].remove();", adBanner);
        } catch (NoSuchElementException e) {
            // TBD
        }
    }

    public void nativeClick(By locator) {
        var submitButton = driver.findElement(locator);
        var executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", submitButton);
    }

    // Методы для взаимодействия с элементами
    public void setFirstName(String firstName) {
        setInputValue(firstNameInputLocator, firstName);
    }

    public void setLastName(String lastName) {
        setInputValue(lastNameInputLocator, lastName);
    }

    public void setEmail(String email) {
        setInputValue(emailInputLocator, email);
    }

    public void setMobile(String mobile) {
        setInputValue(mobileInputLocator, mobile);
    }

    public String getFirstName() {
        return getInputValue(firstNameInputLocator);
    }

    public String getLastName() {
        return getInputValue(lastNameInputLocator);
    }

    public void switchGender(String gender) {
        switch (gender) {
            case ("male") -> this.driver.findElement(maleGenderRadioLabelLocator).click();
            case ("female") -> this.driver.findElement(femaleGenderRadioLabelLocator).click();
            default -> this.driver.findElement(otherGenderRadioLabelLocator).click();
        }
    }

    public void confirmSelectionSubject(String subject) {
        selectOptionInAutocomplete(
                subjectsInputLocator,
                subjectsOptionsListLocator,
                subjectsOptionLocator,
                subject
        );
    }

    public void confirmSelectionState(String state) {
        selectOptionInAutocomplete(
                stateInputLocator,
                stateOptionsListLocator,
                stateOptionLocator,
                state
        );
    }

    public void confirmSelectionCity(String city) {
        selectOptionInAutocomplete(
                cityInputLocator,
                cityOptionsListLocator,
                cityOptionLocator,
                city
        );
    }

    private void clickDateOfBirthInput() {
        clickElement(dateOfBirthInputLocator);
    }

    private void waitForDatePicker() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(datePickerLocator));
    }

    private WebElement findDayByAriaLabel(List<WebElement> dayElements, String dayToSelectLabelContains) {
        for (WebElement dayElement : dayElements) {
            String dayLabel = dayElement.getAttribute("aria-label");
            if (dayLabel != null && dayLabel.contains(dayToSelectLabelContains)) {
                return dayElement;
            }
        }
        return null;
    }

    private void selectDateOfBirth(String month, String year, String day) {
        String daySuffix = getDaySuffix(day);
        String dayToSelectLabelContains = month + " " + day + daySuffix + ", " + year;
        List<WebElement> dayElements = driver.findElements(daysInDatePickerLocator);

        WebElement dayToSelect = findDayByAriaLabel(dayElements, dayToSelectLabelContains);
        if (dayToSelect != null) {
            dayToSelect.click();
        } else {
            throw new NoSuchElementException("Day : '" + dayToSelectLabelContains + "' not found in DatePicker.");
        }
    }

    public void setDateOfBirthDatePicker(String month, String year, String day) {
        clickDateOfBirthInput();
        waitForDatePicker();
        selectOptionInDropdown(monthSelectAtDatePickerLocator, month);
        selectOptionInDropdown(yearSelectDatePickerLocator, year);
        selectDateOfBirth(month, year, day);
    }

    public void setFile(String filePath) {
        driver.findElement(fileUploadLocator).sendKeys(filePath);
    }

    public void setCurrentAddress(String address) {
        setInputValue(currentAddressInputLocator, address);
    }

    public void removeBanner() {
        removeElement(bannerLocator);
    }

    public void clickSubmitButton() {
        scrollToElement(submitButtonLocator);
        nativeClick(submitButtonLocator);
    }
}
