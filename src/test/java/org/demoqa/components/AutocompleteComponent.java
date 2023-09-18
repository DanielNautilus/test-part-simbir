package org.demoqa.components;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class AutocompleteComponent extends BaseComponent {
    private final By autocompleteInputLocator;
    private final By autocompleteOptionsListLocator;
    private final By autocompleteOptionsLocator;

    public AutocompleteComponent(WebDriver driver, By autocompleteInputLocator, By autocompleteOptionsListLocator, By autocompleteOptionsLocator) {
        super(driver);
        this.autocompleteInputLocator = autocompleteInputLocator;
        this.autocompleteOptionsListLocator = autocompleteOptionsListLocator;
        this.autocompleteOptionsLocator = autocompleteOptionsLocator;
    }

    public void selectOption(String optionText) {
        scrollToAutocompleteInput();
        setInputValue(autocompleteInputLocator, optionText);
        scrollToAutocompleteOptionsList();
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

    private void scrollToAutocompleteInput() {
        scrollToElement(autocompleteInputLocator);
    }

    private void scrollToAutocompleteOptionsList() {
        scrollToElement(autocompleteOptionsListLocator);
    }

}
