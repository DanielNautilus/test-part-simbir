package org.demoqa;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class RegistrationTests {
    private WebDriver driver;
    private RegistrationPage registrationPage;

    @BeforeAll
    static void setupAll() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setup() {
        driver = new ChromeDriver();
        registrationPage = new RegistrationPage(driver);
    }

    @AfterEach
    void teardown() {
        driver.quit();
    }


    @Test
    public void testRegistration() {
        // Открыть страницу
        driver.get("https://demoqa.com/automation-practice-form");
    }
}
