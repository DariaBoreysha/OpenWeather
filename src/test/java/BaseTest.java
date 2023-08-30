import API.GetWeather;
import Pages.CreateAnAccountPage;
import Pages.MainPage;
import Pages.SignIpPage;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.*;


public class BaseTest {

    GetWeather getWeather = new GetWeather();
    MainPage mainPage = new MainPage();
    SignIpPage signIpPage = new SignIpPage();
    CreateAnAccountPage createAnAccountPage = new CreateAnAccountPage();

    @BeforeAll
    public static void setup() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        Configuration.timeout = 1000000;
        WebDriverManager.chromedriver().setup();
    }

    /**
     * Configuration.browserSize = "1920x1080";
     * WebDriverRunner.getWebDriver().manage().window().maximize(); - Jenkins не видит элементы теста с такими настройками
     */

    @BeforeEach
    public void openPages() {
        open("https://openweathermap.org/");
        WebDriverRunner.getWebDriver().manage().window().fullscreen();
        clearBrowserCookies();
    }

    @AfterEach
    public void closePages() {
        closeWindow();
    }

    @AfterAll
    public static void tearDown() {
        Selenide.closeWebDriver();
    }

}
