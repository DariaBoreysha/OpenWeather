package API;

import io.restassured.response.Response;
import static Constants.Config.ApiVariables.*;
import static io.restassured.RestAssured.given;

public class GetWeather {
   // private final static String URL = "https://api.openweathermap.org/data/2.5/weather";


    public String checkCityName(String cityName) {
        Response response = given()
                .params("q", cityName, "appid", KEY)
                .when()
                .get(GET_WEATHER_BY_CITY)
                .then().extract().response();
        String cityFromResponse = response.jsonPath().getString("name");
        return cityFromResponse;
    }
}

