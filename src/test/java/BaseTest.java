import API.GetWeather;
import Pages.CreateAnAccountPage;
import Pages.MainPage;
import Pages.SignIpPage;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.open;


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
        open("https://openweathermap.org/");
        WebDriverRunner.getWebDriver().manage().window().maximize();
    }

    @AfterAll
    public static void tearDown(){
        Selenide.closeWebDriver();
    }

}
