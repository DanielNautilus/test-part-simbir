package org.demoqa.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public class RegistrationPage extends BasePage {

    public RegistrationPage(WebDriver driver) {
        super(driver);
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
    private final By formResponseLocator = By.className("modal-content");
    private final By formResponseHeaderLocator = By.id("example-modal-sizes-title-lg");

    //Helpers




//drop-down
    private void selectOptionInDropdown(By dropdownLocator, String option) {
        WebElement dropdown = driver.findElement(dropdownLocator);
        Select dropdownOptions = new Select(dropdown);
        dropdownOptions.selectByVisibleText(option);
    }
//datepicker
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





    // Main Methods
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

    public void switchGender(String gender) {
        switch (gender) {
            case ("Male") -> this.driver.findElement(maleGenderRadioLabelLocator).click();
            case ("Female") -> this.driver.findElement(femaleGenderRadioLabelLocator).click();
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
        setInputValue(fileUploadLocator,filePath);
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

    private WebElement getVisibleResponseForm(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(formResponseLocator));
    }

    public String getHeaderAtResponseForm(){
        return getVisibleResponseForm().findElement(formResponseHeaderLocator).getText();
    }

    private String getValuesFromResponseForm(String property) {
        return getVisibleResponseForm()
                .findElement(By.xpath("//td[text()='" + property + "']/following-sibling::td"))
                .getText();
    }

    public String getStudentNameAtResponseForm(){
        return getValuesFromResponseForm("Student Name");
    }

    public String getStudentEmailAtResponseForm(){
        return getValuesFromResponseForm("Student Email");
    }

    public String getGenderAtResponseForm(){
        return getValuesFromResponseForm("Gender");
    }

    public String getMobileAtResponseForm(){
        return getValuesFromResponseForm("Mobile");
    }

    public String getDateOfBirthAtResponseForm(){
        return getValuesFromResponseForm("Date of Birth");
    }

    public String[] getSubjectsAtResponseForm(){
        return getValuesFromResponseForm("Subjects").split(",");
    }

    public String getPictureNameAtResponseForm(){
        return getValuesFromResponseForm("Picture");
    }

    public String getCurrentAddressAtResponseForm(){
        return getValuesFromResponseForm("Address");
    }

    private String[] getStateAndCityWords() {
        return getValuesFromResponseForm("State and City").split("\\s+");
    }

    public String getStateAtResponseForm() {
        var words = getStateAndCityWords();
        if (words.length >= 2) {
            return String.join(" ", Arrays.copyOfRange(words, 0, words.length - 1));
        } else {
            return "";
        }
    }

    public String getCityAtResponseForm() {
        var words = getStateAndCityWords();
        if (words.length >= 1) {
            return words[words.length - 1];
        } else {
            return "";
        }
    }

}
