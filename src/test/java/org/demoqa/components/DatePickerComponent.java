package org.demoqa.components;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class DatePickerComponent extends BaseComponent {

    private final By dateOfBirthInputLocator = By.id("dateOfBirthInput");
    private final By datePickerLocator = By.className("react-datepicker");
    private final By monthSelectAtDatePickerLocator = By.cssSelector(".react-datepicker__month-select");
    private final By yearSelectDatePickerLocator = By.cssSelector(".react-datepicker__year-select");
    private final By daysInDatePickerLocator = By.cssSelector(".react-datepicker__day");

    public DatePickerComponent(WebDriver driver) { super(driver); }

    public void setDateOfBirth(String month, String year, String day) {
        clickDateOfBirthInput();
        waitForDatePicker();
        selectOptionInDropdown(monthSelectAtDatePickerLocator, month);
        selectOptionInDropdown(yearSelectDatePickerLocator, year);
        selectDateOfBirth(month, year, day);
    }

    private void clickDateOfBirthInput() {
        clickElement(dateOfBirthInputLocator);
    }

    private void waitForDatePicker() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(datePickerLocator));
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

    private WebElement findDayByAriaLabel(List<WebElement> dayElements, String dayToSelectLabelContains) {
        for (WebElement dayElement : dayElements) {
            String dayLabel = dayElement.getAttribute("aria-label");
            if (dayLabel != null && dayLabel.contains(dayToSelectLabelContains)) {
                return dayElement;
            }
        }
        return null;
    }
}
