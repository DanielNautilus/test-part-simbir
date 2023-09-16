package org.demoqa.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.demoqa.pages.RegistrationPage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegistrationTests {
    private static WebDriver driver;
    private static String urlRegistrationPage;
    private static RegistrationPage registrationPage;

    @BeforeAll
    static void setupAll() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(new ChromeOptions()
                //.addArguments("--headless")
                //.addArguments("--disable-gpu")
                //.addArguments("--disable-dev-shm-usage")
                //.addArguments("--disable-extensions")
                //.addArguments("--no-sandbox")
                //.addArguments("--start-maximized")
                //.addArguments("--window-size=800,2000")
                //.addArguments("--ignore-certificate-errors")
        );
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        registrationPage = new RegistrationPage(driver);
        urlRegistrationPage = "https://demoqa.com/automation-practice-form";
    }

    @BeforeEach
    void setup() {
        driver.get(urlRegistrationPage);
    }

    @AfterEach
    void teardown() {
        driver.close();
    }

    @AfterAll
    static void teardownAll() {
        driver.quit();
    }

    @Test
    public void testRegistration() {
        //Arrange
        var expectedName = "Fname";

        //Act
        registrationPage.setFirstName(expectedName);
        var actualName = registrationPage.getFirstName();

        //Assert
        Assertions.assertEquals(expectedName, actualName);
    }
}
