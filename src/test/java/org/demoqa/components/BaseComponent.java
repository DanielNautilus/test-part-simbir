package org.demoqa.components;

import org.demoqa.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BaseComponent extends BasePage {

    public BaseComponent(WebDriver driver) {
        super(driver);
    }
}
