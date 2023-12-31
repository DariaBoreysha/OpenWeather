package settings;

import API.GetWeather;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.selenide.AllureSelenide;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import pages.CreateAnAccountPage;
import pages.MainPage;
import pages.SignIpPage;

import static com.codeborne.selenide.Selenide.*;
import static settings.Constants.ApiVariables.WEATHER_PATH;
import static settings.Constants.ApiVariables.WEATHER_URL;
import static settings.Constants.Variables.*;


public class TestConfig {

    public GetWeather getWeather = new GetWeather();
    public MainPage mainPage = new MainPage();
    public SignIpPage signIpPage = new SignIpPage();
    public CreateAnAccountPage createAnAccountPage = new CreateAnAccountPage();

    @BeforeAll
    public static void setUp() {

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());

        WebDriverManager.chromedriver().setup();
        Configuration.headless = HEADLESS;

        ResponseSpecification responseSpecificationForWeatherApi = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .build();

        RequestSpecification requestSpecificationForWeatherApi = new RequestSpecBuilder()
                .setBaseUri(WEATHER_URL)
                .setBasePath(WEATHER_PATH)
                .build();

        RestAssured.responseSpecification = responseSpecificationForWeatherApi;
        RestAssured.requestSpecification = requestSpecificationForWeatherApi;
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
