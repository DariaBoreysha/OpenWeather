import API.GetWeather;
import Pages.CreateAnAccountPage;
import Pages.MainPage;
import Pages.SignIpPage;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
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
        WebDriverManager.chromedriver().setup();
    }

    /**
     * WebDriverRunner.getWebDriver().manage().window().maximize(); - Jenkins не видит элементы теста с такими настройками
     * Тест с регистрацией не падает только если не открывать окно браузера, т.е. "Configuration.headless = true;"
     */

    @BeforeEach
    public void openPages() {
        Configuration.headless = Config.HEADLESS;
        open(Config.URL);
        Configuration.browserSize = Config.WINDOW_SIZE;
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
