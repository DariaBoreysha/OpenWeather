import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class WeatherCheckTest extends BaseTest {

    String cityName = "Oxford";

    @Test
    public void checkCityNamesViaApiAndUI() throws InterruptedException {
        mainPage.searchWeatherByCityName(cityName);
        Thread.sleep(4000);
        Assertions.assertEquals((mainPage.getCityTitle()), (getWeather.checkCityName(cityName) + ", " + getWeather.checkCountryName(cityName)));
    }
}
