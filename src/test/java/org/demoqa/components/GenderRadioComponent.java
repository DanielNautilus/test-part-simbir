package org.demoqa.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class GenderRadioComponent extends BaseComponent {

    private final By maleGenderRadioLabelLocator = By.cssSelector("label[for='gender-radio-1']");
    private final By femaleGenderRadioLabelLocator = By.cssSelector("label[for='gender-radio-2']");
    private final By otherGenderRadioLabelLocator = By.cssSelector("label[for='gender-radio-3']");
    public GenderRadioComponent(WebDriver driver) {
        super(driver);
    }

    public void switchGender(String gender) {
        switch (gender) {
            case "Male" -> driver.findElement(maleGenderRadioLabelLocator).click();
            case "Female" -> driver.findElement(femaleGenderRadioLabelLocator).click();
            default -> driver.findElement(otherGenderRadioLabelLocator).click();
        }
    }
}
