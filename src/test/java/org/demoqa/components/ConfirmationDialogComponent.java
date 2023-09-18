package org.demoqa.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ConfirmationDialogComponent extends BaseComponent {
    private final By formResponseLocator = By.className("modal-content");
    private final By formResponseHeaderLocator = By.id("example-modal-sizes-title-lg");

    public ConfirmationDialogComponent(WebDriver driver) {
        super(driver);
    }


    private WebElement getVisibleResponseForm() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(formResponseLocator));
    }

    public String getValuesFromResponseForm(String property) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(formResponseLocator))
                .findElement(By.xpath("//td[text()='" + property + "']/following-sibling::td"))
                .getText();
    }

    public String getHeaderAtResponseForm() {
        return getVisibleResponseForm().findElement(formResponseHeaderLocator).getText();
    }
}
