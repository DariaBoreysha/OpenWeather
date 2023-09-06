package API;

import io.restassured.response.Response;

import static Constants.Config.ApiVariables.*;
import static io.restassured.RestAssured.given;

public class GetWeather {


    public String checkCityName(String cityName) {
        Response response = given()
                .log().uri()
                .params("q", cityName, "appid", KEY)
                .when()
                .get(GET_WEATHER_BY_CITY)
                .then()
                .log().body()
                .extract().response();
        String cityFromResponse = response.jsonPath().getString("name");
        return cityFromResponse;
    }
}

