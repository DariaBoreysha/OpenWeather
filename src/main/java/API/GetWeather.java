package API;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class GetWeather {
    private final static String URL = "https://api.openweathermap.org/data/2.5/weather";
    private final static String KEY = "802403cdec12a4d2507590f3e48dc407";

    public String checkCityName(String cityName) {
        Response response = given()
                .params("q", cityName, "appid", KEY)
                .when()
                .get(URL)
                .then().extract().response();
        String cityFromResponse = response.jsonPath().getString("name");
        return cityFromResponse;
    }

    public String checkCityId(String cityName) {
        Response response = given()
                .params("q", cityName, "appid", KEY)
                .when()
                .get(URL)
                .then().extract().response();
        String idFromResponse = response.jsonPath().getString("id");
        return idFromResponse;
    }

    public String checkCountryName(String cityName) {
        Response response = given()
                .params("q", cityName, "appid", KEY)
                .when()
                .get(URL)
                .then().extract().response();
        String countryFromResponse = response.jsonPath().getString("sys.country");
        return countryFromResponse;
    }
}

