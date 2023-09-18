package org.demoqa.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
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

    @BeforeAll
    static void setupAll() {
        WebDriverManager.chromedriver()
                .avoidResolutionCache() //Problem without - driver manager don't work
                .avoidResolutionCache() //Problem without - driver manager don't work
                .setup();
        driver = new ChromeDriver(new ChromeOptions()
                .addArguments("--headless")
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
    @Story("User submits registration form successfully")
    @Severity(SeverityLevel.NORMAL)
    public void testRegistration() {
        driver.get(urlRegistrationPage);
        //Arrange
        var expectedHeaderResponseForm = "Thanks for submitting the form";

        var expectedFirstName = "FirstName";
        var expectedLastName = "LastName";
        var expectedFullName = expectedFirstName + " " + expectedLastName;

        var expectedEmail = "name@example.com";
        var expectedGender = "Male";
        var expectedMobile = "0123456789";

        var expectedSubject = "Maths";
        var expectedSubjects = new String[]{expectedSubject};

        var expectedDay = "11";
        var expectedMonth = "September";
        var expectedYear = "2001";
        var expectedDate = expectedDay + " " + expectedMonth + "," + expectedYear;

        var expectedCurrentAddress = "CurrentAddress";
        var expectedState = "NCR";
        var expectedCity = "Delhi";
        var expectedPictureName = "testFile.png";
        var relativeFilePath = "src/test/resources/" + expectedPictureName + "";
        var absoluteFilePath = new File(relativeFilePath).getAbsolutePath();

        //Act
        registrationPage.removeBanner();
        registrationPage.setFirstName(expectedFirstName);
        registrationPage.setLastName(expectedLastName);
        registrationPage.setEmail(expectedEmail);
        registrationPage.switchGender(expectedGender);
        registrationPage.setMobile(expectedMobile);
        registrationPage.confirmSelectionSubject(expectedSubject);
        registrationPage.setDateOfBirthDatePicker(expectedMonth, expectedYear, expectedDay);
        registrationPage.setFile(absoluteFilePath);
        registrationPage.setCurrentAddress(expectedCurrentAddress);
        registrationPage.confirmSelectionState(expectedState);
        registrationPage.confirmSelectionCity(expectedCity);
        registrationPage.clickSubmitButton();

        var actualHeaderResponseForm = registrationPage.getHeaderAtResponseForm();
        var actualFullName = registrationPage.getStudentNameAtResponseForm();
        var actualEmail = registrationPage.getStudentEmailAtResponseForm();
        var actualMobile = registrationPage.getMobileAtResponseForm();
        var actualGender = registrationPage.getGenderAtResponseForm();
        var actualSubjects = registrationPage.getSubjectsAtResponseForm();
        var actualDate = registrationPage.getDateOfBirthAtResponseForm();
        var actualPictureName = registrationPage.getPictureNameAtResponseForm();
        var actualCurrentAddress = registrationPage.getCurrentAddressAtResponseForm();
        var actualState = registrationPage.getStateAtResponseForm();
        var actualCity = registrationPage.getCityAtResponseForm();

        //Assert
        Assertions.assertEquals(expectedHeaderResponseForm, actualHeaderResponseForm);
        Assertions.assertEquals(expectedFullName, actualFullName);
        Assertions.assertEquals(expectedEmail, actualEmail);
        Assertions.assertEquals(expectedGender, actualGender);
        Assertions.assertEquals(expectedMobile, actualMobile);
        Assertions.assertArrayEquals(expectedSubjects, actualSubjects);
        Assertions.assertEquals(expectedDate, actualDate);
        Assertions.assertEquals(expectedPictureName, actualPictureName);
        Assertions.assertEquals(expectedCurrentAddress, actualCurrentAddress);
        Assertions.assertEquals(expectedState, actualState);
        Assertions.assertEquals(expectedCity, actualCity);
    }
}
