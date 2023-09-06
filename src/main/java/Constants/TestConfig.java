package Constants;

import API.GetWeather;
import Pages.CreateAnAccountPage;
import Pages.MainPage;
import Pages.SignIpPage;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.selenide.AllureSelenide;
import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static Constants.Config.ApiVariables.WEATHER_PATH;
import static Constants.Config.ApiVariables.WEATHER_URL;
import static Constants.Config.Variables.*;
import static com.codeborne.selenide.Selenide.*;


public class TestConfig {

    public GetWeather getWeather = new GetWeather();
    public MainPage mainPage = new MainPage();
    public SignIpPage signIpPage = new SignIpPage();
    public CreateAnAccountPage createAnAccountPage = new CreateAnAccountPage();

    @BeforeAll
    public static void setUp() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        WebDriverManager.chromedriver().setup();
        RestAssured.baseURI = WEATHER_URL;
        RestAssured.basePath = WEATHER_PATH;
        Configuration.headless = HEADLESS;
    }

    /**
     * WebDriverRunner.getWebDriver().manage().window().maximize(); - Jenkins не видит элементы теста с такими настройками
     * Тест с регистрацией не падает только если не открывать окно браузера, т.е. "Configuration.headless = true;"
     */

    @BeforeEach
    public void openPages() {
        open(URL);
        Configuration.browserSize = WINDOW_SIZE;
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
