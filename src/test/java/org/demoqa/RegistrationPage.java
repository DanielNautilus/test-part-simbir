package org.demoqa;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegistrationPage {
    public RegistrationPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
    @FindBy(id = "firstName")
    private WebElement firstNameInput;

    @FindBy(id = "lastName")
    private WebElement lastNameInput;

    @FindBy(id = "userEmail")
    private WebElement emailInput;

    @FindBy(id = "gender-radio-1")
    private WebElement maleGenderRadio;

    @FindBy(id = "gender-radio-2")
    private WebElement femaleGenderRadio;

    @FindBy(id = "gender-radio-3")
    private WebElement otherGenderRadio;

    @FindBy(id = "userNumber")
    private WebElement mobileInput;


    //DatePicker
    @FindBy(id = "dateOfBirthInput")
    private WebElement dateOfBirthInput;

    @FindBy(css = ".react-datepicker__month-select")
    private WebElement monthSelectDatePicker;

    @FindBy(xpath = "//*[@id=\"dateOfBirth\"]/div[2]/div[2]/div/div/div[2]/div[1]/div[2]/div[1]/select/option[1]")
    private WebElement firstMonthOptionDatePicker;

    @FindBy(css = ".react-datepicker__year-select")
    private WebElement yearSelectDatePicker;

    @FindBy(xpath = "//*[@id=\"dateOfBirth\"]/div[2]/div[2]/div/div/div[2]/div[1]/div[2]/div[2]/select/option[1]")
    private WebElement firstYearOptionDatePicker;

    @FindBy(css = ".react-datepicker__day react-datepicker__day--001")
    private WebElement firstDayInDatePicker;
    ///


    @FindBy(css = ".subjects-auto-complete__control")
    private WebElement subjectsInput;

    @FindBy(id = "uploadPicture")
    private WebElement pictureInput;
    @FindBy(id = "currentAddress")
    private WebElement currentAddressInput;

    @FindBy(id = "state")
    private WebElement stateDropdown;

    @FindBy(id = "city")
    private WebElement cityDropdown;

    @FindBy(id = "submit")
    private WebElement submitButton;

    // Методы для взаимодействия с элементами
    public void enterFirstName(String firstName) {
        firstNameInput.sendKeys(firstName);
    }

    public void enterLastName(String lastName) {
        lastNameInput.sendKeys(lastName);
    }

    public void clickSubmitButton() {
        submitButton.click();
    }
}
