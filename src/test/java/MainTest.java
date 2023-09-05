import Pages.CreateAnAccountPage;
import com.codeborne.selenide.Selenide;
import jdk.jfr.Description;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MainTest extends BaseTest {


    @Test
    @Description("Checking the correctness of data received according to the entered city (API + UI)")
    public void checkCityNamesViaApiAndUI(){
        mainPage.searchWeatherByCityName(Config.CITY_NAME);
        try{Thread.sleep(4000);}
        catch (InterruptedException e){
            e.getMessage();
            System.out.println("Rerun tests");
        }
        String cityFromUI = mainPage.getCityTitle();
        Assert.assertTrue(cityFromUI.contains(Config.CITY_NAME));
        String cityFromAPI = getWeather.checkCityName(Config.CITY_NAME);
        Assertions.assertTrue(cityFromUI.contains(cityFromAPI));
    }

    @Test
    @Description("Testing the registration form")
    public void RegisterWithValidCredentials() {
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
         * Думала еще о таймерах, чтобы закрыть метод после определенного времени, но не поняла, как к моему случаю адаптировать
         *
         * Поэтому добавила проверку на то, можно ли с кнопкой на самой капче взаимодействовать
         */

        if (createAnAccountPage.getCaptchaTaskFrame().isDisplayed()) {
            createAnAccountPage.checkCaptcha();
        } else {
            createAnAccountPage.clickSubmitButton();
            Assertions.assertEquals("How and where will you use our API?", createAnAccountPage.getModalWindowTitleText());
        }
    }

}

