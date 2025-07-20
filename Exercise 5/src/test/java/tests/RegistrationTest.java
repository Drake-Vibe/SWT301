package tests;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import pages.RegistrationPage;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("Registration Form Test")
public class RegistrationTest extends BaseTest {
    static RegistrationPage regPage;

    @BeforeAll
    public static void initPage() {
        regPage = new RegistrationPage(driver);
    }

    @Test
    @Order(1)
    @DisplayName("Should submit form with valid data")
    void testValidRegistration() {
        regPage.navigate();
        regPage.fillForm("Nguyen", "Van A", "nva@example.com", "0911222333");
        regPage.submitForm();
        assertTrue(regPage.isSubmitted());
        assertTrue(regPage.getModalTitle().contains("Thanks"));
    }
    @DisplayName("Submit registration form using CSV data (valid & invalid)")
    @ParameterizedTest(name = "Test case: {0} {1} - Expect: {4}")
    @CsvFileSource(resources = "/login-data.csv", numLinesToSkip = 1)
    void testValidRegistration(String firstName, String lastName, String email, String mobile, String expected) {
        regPage.navigate();

        try {
            regPage.fillForm(firstName, lastName, email, mobile);
            regPage.submitForm();

            boolean isSuccess = regPage.isSubmitted();

            if (expected.equalsIgnoreCase("success")) {
                assertTrue(isSuccess, "Expected success but form was not submitted.");
                assertTrue(regPage.getModalTitle().contains("Thanks"));
            } else {
                assertFalse(isSuccess, "Expected error but form submitted successfully.");
            }
        } catch (Exception e) {
            if (expected.equalsIgnoreCase("success")) {
                Assertions.fail("Unexpected error for valid input: " + e.getMessage());
            } else {
                assertTrue(true);
            }
        }
    }

}