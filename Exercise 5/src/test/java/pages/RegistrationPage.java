package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class RegistrationPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    private By firstName = By.id("firstName");
    private By lastName = By.id("lastName");
    private By email = By.id("userEmail");
    private By genderMale = By.xpath("//label[text()='Male']");
    private By mobile = By.id("userNumber");
    private By submitButton = By.id("submit");
    private By modalTitle = By.id("example-modal-sizes-title-lg");

    public void navigate() {
        driver.get("https://demoqa.com/automation-practice-form");
        ((JavascriptExecutor) driver).executeScript(
                "let ads = document.querySelectorAll('iframe, .adsbygoogle, #adplus-anchor'); ads.forEach(e => e.remove());"
        );
    }

    public void fillForm(String fname, String lname, String mail, String phone) {
        driver.findElement(firstName).sendKeys(fname);
        driver.findElement(lastName).sendKeys(lname);
        driver.findElement(email).sendKeys(mail);
        WebElement gender = driver.findElement(genderMale);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", gender);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", gender);
        driver.findElement(mobile).sendKeys(phone);
    }

    public void submitForm() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", driver.findElement(submitButton));
    }

    public boolean isSubmitted() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(modalTitle)).isDisplayed();
    }

    public String getModalTitle() {
        return driver.findElement(modalTitle).getText();
    }
}