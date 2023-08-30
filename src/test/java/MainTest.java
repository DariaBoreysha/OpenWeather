import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.github.javafaker.Faker;
import jdk.jfr.Description;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.switchTo;

public class MainTest extends BaseTest {

    /**
     * Введите любой город
     */

    String cityName = "Oxford";

    @Test
    @Description("Checking the correctness of data received according to the entered city (API + UI)")
    public void checkCityNamesViaApiAndUI() throws InterruptedException {
        mainPage.searchWeatherByCityName(cityName);
        Thread.sleep(4000);
        String cityFromUI = mainPage.getCityTitle();
        Assert.assertTrue(cityFromUI.contains(cityName));
        String cityFromAPI = getWeather.checkCityName(cityName);
        Assertions.assertTrue(cityFromUI.contains(cityFromAPI));
    }

    @Test
    @Description("Testing the registration form")
    public void RegisterWithValidCredentials() throws InterruptedException {

        /**
         * В поле ввода пароля есть условие, чтобы пароль включал более 8 символов
         * Добавила условие на прибавление символов, чтобы случайная ошибка не появлялась
         */

        Faker f = new Faker();
        String pass = f.internet().password();
        if (pass.length() < 8) {
            pass = pass + "12345678";
        }
        mainPage.openSignInPage();
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
         *
         * Поэтому добавила проверку на то, можно ли с кнопкой на самой капче взаимодействовать
         */

        if ($x(".//div[@class='g-recaptcha-bubble-arrow']/following-sibling::div/child::iframe").isDisplayed()) {
            switchTo().frame($x(".//div[@class='g-recaptcha-bubble-arrow']/following-sibling::div/child::iframe"));
            $x(".//button[@id='recaptcha-verify-button']").is(Condition.interactable);
        } else {
            createAnAccountPage.clickSubmitButton();
            Assertions.assertEquals("How and where will you use our API?", createAnAccountPage.getModalWindowTitleText());
        }
    }

}

