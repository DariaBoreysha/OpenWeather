package Pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import com.github.javafaker.Faker;

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
    protected static SelenideElement captchaTaskFrame = $x(".//div[@class='g-recaptcha-bubble-arrow']/following-sibling::div/child::iframe");

    public SelenideElement getCaptchaTaskFrame() {
        return captchaTaskFrame;
    }

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

    public void doCaptcha(){
        String originalWindow = WebDriverRunner.getWebDriver().getWindowHandle();
        try{Thread.sleep(3000);
        switchTo().frame(captchaFrame);
        Thread.sleep(3000);
        captchaBox.click();
        Thread.sleep(1000);}
        catch (InterruptedException e){
            e.getMessage();
            System.out.println("Rerun the tests");
        }
        switchTo().window(originalWindow);
    }


    public boolean checkCaptcha(){
        switchTo().frame($x(".//div[@class='g-recaptcha-bubble-arrow']/following-sibling::div/child::iframe"));
        boolean result = $x(".//button[@id='recaptcha-verify-button']").is(Condition.interactable);
        return result;
    }
    public static String generatePassword() {
        Faker f = new Faker();
        String pass = f.internet().password();
        if (pass.length() < 8) {
            pass = pass + "12345678";
        }
        return pass;
    }

    public static String generateEmail() {
        Faker f = new Faker();
        String email = f.internet().emailAddress();
        return email;
    }

    public static String generateUsername() {
        Faker f = new Faker();
        String username = f.name().firstName();
        return username;
    }


}
