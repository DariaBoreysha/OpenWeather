package Pages;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.switchTo;

public class CreateAnAccountPage {
    protected static SelenideElement usernameInput = $x(".//input[@id='user_username']");
    protected static SelenideElement passwordInput = $x(".//input[@id='user_password']");
    protected static SelenideElement repeatPasswordInput = $x(".//input[@id='user_password_confirmation']");
    protected static SelenideElement emailInput = $x(".//input[@id='user_email']");
    protected static SelenideElement i16Checkbox = $x(".//input[@id='agreement_is_age_confirmed']");
    protected static SelenideElement privacyPolicyCheckbox = $x(".//input[@id='agreement_is_accepted']");
    protected static SelenideElement captchaBox = $x(".//*[@id='recaptcha-anchor']");
    protected static SelenideElement submitButton = $x(".//input[@name='commit']");
    protected static SelenideElement modalWindowTitle = $x(".//div[@class='modal-header']/h4");
    protected static SelenideElement captchaFrame = $x(".//iframe[@title='reCAPTCHA']");


    public String getModalWindowTitleText() {
        String ModalWindowTitleText = modalWindowTitle.getText();
        return ModalWindowTitleText;
    }

    public void clickSubmitButton() {
        submitButton.click();
    }

    public void enterUsername(String username) {
        usernameInput.sendKeys(username);
    }

    public void clickOnAgeCheckbox() {
        i16Checkbox.click();
    }

    public void clickOnPrivacyCheckbox() {
        privacyPolicyCheckbox.click();
    }

    public void enterEmail(String email) {
        emailInput.sendKeys(email);
    }

    public void enterPassword(String password) {
        passwordInput.sendKeys(password);
    }

    public void reEnterPassword(String repassword) {
        repeatPasswordInput.sendKeys(repassword);
    }

    public void doCaptcha() throws InterruptedException {
        String originalWindow = WebDriverRunner.getWebDriver().getWindowHandle();
        Thread.sleep(3000);
        switchTo().frame(captchaFrame);
        Thread.sleep(3000);
        captchaBox.click();
        Thread.sleep(1000);
        switchTo().window(originalWindow);
    }

}
