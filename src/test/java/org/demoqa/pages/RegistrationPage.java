package org.demoqa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class RegistrationPage {
    private final WebDriver driver;
    public RegistrationPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    private final By firstNameInputLocator = By.id("firstName");

    private final By lastNameInputLocator = By.id("lastName");

    private final By emailInputLocator = By.id("userEmail");

    private final By maleGenderRadioLocator = By.id("gender-radio-1");

    private final By femaleGenderRadioLocator = By.id("gender-radio-2");

    private final By otherGenderRadioLocator = By.id("gender-radio-3");

    private final By mobileInputLocator = By.id("userNumber");

    //DatePicker
    private final By dateOfBirthInputLocator = By.id("dateOfBirthInput");

    private final By monthSelectDatePickerLocator = By.cssSelector(".react-datepicker__month-select");

    private final By firstMonthOptionDatePickerLocator = By.xpath("//*[@id=\"dateOfBirth\"]/div[2]/div[2]/div/div/div[2]/div[1]/div[2]/div[1]/select/option[1]");

    private final By yearSelectDatePickerLocator = By.cssSelector(".react-datepicker__year-select");

    private final By firstYearOptionDatePickerLocator = By.xpath("//*[@id=\"dateOfBirth\"]/div[2]/div[2]/div/div/div[2]/div[1]/div[2]/div[2]/select/option[1]");

    private final By firstDayInDatePickerLocator = By.cssSelector(".react-datepicker__day react-datepicker__day--001");
    ///

    //Subject AutoComplete
    //@FindBy(css = ".subjects-auto-complete__control")
    //private WebElement subjectsControl;
    private final By subjectsInputLocator = By.id("subjectsInput");
    private final By subjectsOptionLocator = By.cssSelector(".subjects-auto-complete__value");
    private final By pictureInputLocator = By.id("uploadPicture");
    private final By currentAddressInputLocator = By.id("currentAddress");
    private final By stateDropdownLocator = By.id("state");
    private final By cityDropdownLocator = By.id("city");
    private final By submitButtonLocator = By.id("submit");

    // Методы для взаимодействия с элементами
    public void setFirstName(String firstName) {
        this.driver.findElement(firstNameInputLocator).sendKeys(firstName);
    }
    public String getFirstName() {
        return this.driver.findElement(firstNameInputLocator).getText();
    }

    public void enterLastName(String lastName) {
        this.driver.findElement(lastNameInputLocator).sendKeys(lastName);
    }

    public void enterEmailInput(String email) {
        this.driver.findElement(emailInputLocator).sendKeys(email);
    }

    public void switchGender(String gender) {
        switch (gender) {
            case ("male") -> this.driver.findElement(maleGenderRadioLocator).click();
            case ("female") -> this.driver.findElement(femaleGenderRadioLocator).click();
            default -> this.driver.findElement(otherGenderRadioLocator).click();
        }
    }

    public void enterMobileInput(String mobile) {
        this.driver.findElement(mobileInputLocator).sendKeys(mobile);
    }

    public void enterSubject(String subject) {
        this.driver.findElement(subjectsInputLocator).sendKeys(subject);
    }

    public void selectOptionAtSubject(String subject) {
        enterSubject(subject);
    }

    public void clickSubmitButton() {
        this.driver.findElement(submitButtonLocator).click();
    }
}
