package org.demoqa.pages;

import org.demoqa.components.AutocompleteComponent;
import org.demoqa.components.ConfirmationDialogComponent;
import org.demoqa.components.DatePickerComponent;
import org.demoqa.components.GenderRadioComponent;
import org.openqa.selenium.*;

import java.util.Arrays;

public class RegistrationPage extends BasePage {

    private final GenderRadioComponent genderRadioComponent;
    private final AutocompleteComponent subjectsAutocompleteComponent;
    private final AutocompleteComponent stateAutocompleteComponent;
    private final AutocompleteComponent cityAutocompleteComponent;
    private final DatePickerComponent datePickerComponent;
    private final ConfirmationDialogComponent confirmationDialogComponent;


    //Locators
    private final By firstNameInputLocator = By.id("firstName");
    private final By lastNameInputLocator = By.id("lastName");
    private final By emailInputLocator = By.id("userEmail");
    private final By mobileInputLocator = By.id("userNumber");
    private final By currentAddressInputLocator = By.id("currentAddress");
    private final By fileUploadLocator = By.id("uploadPicture");
    private final By bannerLocator = By.id("adplus-anchor");
    private final By submitButtonLocator = By.id("submit");

    //DatePicker


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


    public RegistrationPage(WebDriver driver) {
        super(driver);
        genderRadioComponent = new GenderRadioComponent(driver);
        subjectsAutocompleteComponent = new AutocompleteComponent(driver, subjectsInputLocator, subjectsOptionsListLocator, subjectsOptionLocator);
        stateAutocompleteComponent = new AutocompleteComponent(driver, stateInputLocator, stateOptionsListLocator, stateOptionLocator);
        cityAutocompleteComponent = new AutocompleteComponent(driver, cityInputLocator, cityOptionsListLocator, cityOptionLocator);
        datePickerComponent = new DatePickerComponent(driver);
        confirmationDialogComponent = new ConfirmationDialogComponent(driver);
    }

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
    public void setFile(String filePath) {
        setInputValue(fileUploadLocator, filePath);
    }
    public void setCurrentAddress(String address) {
        setInputValue(currentAddressInputLocator, address);
    }

    public void switchGender(String gender) {
        genderRadioComponent.switchGender(gender);
    }

    public void confirmSelectionSubject(String subject) {
        subjectsAutocompleteComponent.selectOption(subject);
    }

    public void confirmSelectionState(String state) {
        stateAutocompleteComponent.selectOption(state);

    }

    public void confirmSelectionCity(String city) {
        cityAutocompleteComponent.selectOption(city);

    }

    public void setDateOfBirthDatePicker(String month, String year, String day) {
        datePickerComponent.setDateOfBirth(month,year,day);

    }

    public void removeBanner() {
        removeElement(bannerLocator);
    }

    public void clickSubmitButton() {
        scrollToElement(submitButtonLocator);
        nativeClick(submitButtonLocator);
    }

    public String getHeaderAtResponseForm() {
        return confirmationDialogComponent.getHeaderAtResponseForm();
    }

    public String getStudentNameAtResponseForm() {
        return confirmationDialogComponent.getValuesFromResponseForm("Student Name");
    }

    public String getStudentEmailAtResponseForm() {
        return confirmationDialogComponent.getValuesFromResponseForm("Student Email");
    }

    public String getGenderAtResponseForm() {
        return confirmationDialogComponent.getValuesFromResponseForm("Gender");
    }

    public String getMobileAtResponseForm() {
        return confirmationDialogComponent.getValuesFromResponseForm("Mobile");
    }

    public String getDateOfBirthAtResponseForm() {
        return confirmationDialogComponent.getValuesFromResponseForm("Date of Birth");
    }

    public String[] getSubjectsAtResponseForm() {
        return confirmationDialogComponent.getValuesFromResponseForm("Subjects").split(",");
    }

    public String getPictureNameAtResponseForm() {
        return confirmationDialogComponent.getValuesFromResponseForm("Picture");
    }

    public String getCurrentAddressAtResponseForm() {
        return confirmationDialogComponent.getValuesFromResponseForm("Address");
    }

    private String[] getStateAndCityWords() {
        return confirmationDialogComponent.getValuesFromResponseForm("State and City").split("\\s+");
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
