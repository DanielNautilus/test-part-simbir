package org.demoqa.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.demoqa.pages.RegistrationPage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.time.Duration;

public class RegistrationTests {
    private static WebDriver driver;
    private static String urlRegistrationPage;
    private static RegistrationPage registrationPage;
    private static final String AD_BLOCK_ENTENSION_PATH = "src/test/resources/Adblock-Plus-extension.crx";

    @BeforeAll
    static void setupAll() {
        WebDriverManager.chromedriver()
                .avoidResolutionCache() //Problem without - driver manager don't work
                .avoidResolutionCache() //Problem without - driver manager don't work
                .setup();
        driver = new ChromeDriver(new ChromeOptions()
                //.addArguments("--headless")
                .addArguments("--disable-gpu")
                .addArguments("--disable-dev-shm-usage")
                .addArguments("--disable-extensions")
                .addArguments("--no-sandbox")
                .addArguments("--start-maximized")
                .addArguments("--window-size=800,2000")
                .addArguments("--ignore-certificate-errors")
        );
        driver.manage()
                .window()
                .maximize();
        driver.manage()
                .timeouts()
                .implicitlyWait(Duration.ofSeconds(10));
        registrationPage = new RegistrationPage(driver);
        urlRegistrationPage = "https://demoqa.com/automation-practice-form";
    }

    @BeforeEach
    void setup() {

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
        driver.get(urlRegistrationPage);
        //Arrange
        var expectedFirstName = "FirstName";
        var expectedLastName = "LastName";
        var expectedEmailAddress = "name@example.com";
        var expectedGender = "male";
        var expectedMobile = "0123456789";
        var expectedSubject = "Maths";
        var expectedCurrentAddress = "CurrentAddress";
        var expectedState = "NCR";
        var expectedCity = "Delhi";
        var expectedFileName = "testFile.png";
        var relativeFilePath = "src/test/resources/"+expectedFileName+"";
        var absoluteFilePath = new File(relativeFilePath).getAbsolutePath();


        //Act
        registrationPage.removeBanner();
        registrationPage.setFirstName(expectedFirstName);
        registrationPage.setLastName(expectedLastName);
        registrationPage.setEmail(expectedEmailAddress);
        registrationPage.switchGender(expectedGender);
        registrationPage.setMobile(expectedMobile);
        registrationPage.confirmSelectionSubject(expectedSubject);
        registrationPage.setDateOfBirthDatePicker("January", "2000", "1");
        registrationPage.setFile(absoluteFilePath);
        registrationPage.setCurrentAddress(expectedCurrentAddress);
        registrationPage.confirmSelectionState(expectedState);
        registrationPage.confirmSelectionCity(expectedCity);
        registrationPage.clickSubmitButton();
        var actualFirstName = registrationPage.getFirstName();

        //Assert
        Assertions.assertEquals(expectedFirstName, actualFirstName);
    }
}
