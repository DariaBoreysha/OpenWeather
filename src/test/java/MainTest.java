import com.codeborne.selenide.Selenide;
import jdk.jfr.Description;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pages.CreateAnAccountPage;
import settings.TestConfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.codeborne.selenide.Selenide.sleep;
import static settings.Constants.Variables.CITY_NAME;

public class MainTest extends TestConfig {

    private static final Logger logger = LoggerFactory.getLogger(MainTest.class);
    @Test
    @Description("Checking the correctness of data received according to the entered city (API + UI)")
    public void checkCityNamesViaApiAndUI() {

        logger.warn("checkCityNamesViaApiAndUI");

        mainPage.searchWeatherByCityName(CITY_NAME);
        sleep(4000);
        String cityFromUI = mainPage.getCityTitle();
        Assert.assertTrue(cityFromUI.contains(CITY_NAME));
        String cityFromAPI = getWeather.checkCityName(CITY_NAME);
        Assertions.assertTrue(cityFromUI.contains(cityFromAPI));
    }

    @Test
    @Description("Testing the registration form")
    public void RegisterWithValidCredentials() {
        logger.warn("RegisterWithValidCredentials");

        mainPage.openSignInPage();
        signIpPage.goToRegistryForm();
        createAnAccountPage.enterUsername(CreateAnAccountPage.generateUsername());
        String password = CreateAnAccountPage.generatePassword();
        createAnAccountPage.enterPassword(password);
        createAnAccountPage.reEnterPassword(password);
        createAnAccountPage.enterEmail(CreateAnAccountPage.generateEmail());
        createAnAccountPage.clickOnPrivacyCheckbox();
        createAnAccountPage.clickOnAgeCheckbox();
        Selenide.executeJavaScript("window.scroll(0,500);");
        createAnAccountPage.doCaptcha();

        /**
         * Капча не выкидывала никакого исключения, поэтому try-catch не могла использовать :(
         * Сделала условие на отображение капчи на странице
         * Если отображается капча с заданием -> делаем
         * Если нет -> кликаем на кнопку с подтверждением
         *
         * Нашла такую проверку в JUnit, чтобы по истечении минуты сообщение выходило:
         * Assertions.assertTimeout(Duration.ofMinutes(1), () -> {return "Waiting is too long";});
         * Но решила оставить if-else корявенький обходной путь, чтобы не ждать :)
         */

        if (createAnAccountPage.getCaptchaTaskFrame().isDisplayed()) {
            createAnAccountPage.checkCaptcha();
        } else {
            createAnAccountPage.clickSubmitButton();
            Assertions.assertEquals("How and where will you use our API?", createAnAccountPage.getModalWindowTitleText());
        }
    }

}

