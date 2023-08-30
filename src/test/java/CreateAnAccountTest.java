import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.switchTo;

public class CreateAnAccountTest extends BaseTest {

    
    @Test
    public void RegisterWithValidCredentials() throws InterruptedException {
        Faker f = new Faker();
        String pass = f.internet().password();
        if (pass.length() < 8) {
            pass = pass + 12345678;
        }
        signIpPage.openSignInPage();
        signIpPage.goToRegistryForm();
        createAnAccountPage.enterUsername(f.name().firstName());
        createAnAccountPage.enterPassword(pass);
        createAnAccountPage.reEnterPassword(pass);
        createAnAccountPage.enterEmail(f.internet().emailAddress());
        createAnAccountPage.clickOnPrivacyCheckbox();
        createAnAccountPage.clickOnAgeCheckbox();
        Selenide.executeJavaScript("window.scroll(0,500);");
        createAnAccountPage.doCaptcha();

        /**
         * Капча не выкидывала никакого исключения, поэтому try-catch не могла использовать :(
         * Сделала условие на отображение капчи на странице
         * Думала еще о таймерах, чтобы закрыть метод после определенного времени, но не поняла, как к моему случаю адаптировать
         */

        if($x(".//div[@class='g-recaptcha-bubble-arrow']/following-sibling::div/child::iframe").isDisplayed()){
            switchTo().frame($x(".//div[@class='g-recaptcha-bubble-arrow']/following-sibling::div/child::iframe"));
            $x(".//button[@id='recaptcha-verify-button']").is(Condition.interactable);
        }else{
            createAnAccountPage.clickSubmitButton();
            Assertions.assertEquals("How and where will you use our API?", createAnAccountPage.getModalWindowTitleText());
        }
    }

}

