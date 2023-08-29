import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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
        createAnAccountPage.createAnAccount(f.name().firstName(), pass, pass, f.internet().emailAddress());
        Assertions.assertEquals("How and where will you use our API?",createAnAccountPage.getModalWindowTitleText());
    }

    @Test
    public void RegisterWithAllEmptyFields() {
        Faker f = new Faker();
        signIpPage.openSignInPage();
        signIpPage.goToRegistryForm();
        Selenide.executeJavaScript("window.scroll(0,500);");
        createAnAccountPage.getSubmitButton().shouldBe(Condition.disabled);
    }
}
