package Pages;

import com.codeborne.selenide.*;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.webdriver;

public class MainPage {

    protected static SelenideElement searchInput = $x(".//div[@id='weather-widget']//input");
    protected static SelenideElement searchButton = $x(".//div[@id='weather-widget']//button");
    protected static SelenideElement searchDropdownOption1 = $x(".//ul[@class='search-dropdown-menu']/li[1]");
    protected static SelenideElement cityTitletext = $x(".//div[@class='grid-container grid-4-5']//h2");
    protected static SelenideElement SignInLink = $x(".//div[@id='desktop-menu']//li[@class='user-li']/a");

 /*   public MainPage openMainPage() {
        Selenide.open("https://openweathermap.org/");
        return this;
    }*/



    public MainPage searchWeatherByCityName(String cityName) {
        searchInput.sendKeys(cityName);
        searchButton.shouldBe(Condition.interactable);
        searchButton.click();
        searchDropdownOption1.shouldBe(Condition.visible);
        searchDropdownOption1.click();
        return new MainPage();
    }
    public String getCityTitle(){
        String cityTitle = cityTitletext.getText();
        return cityTitle;
    }

    public String getCityIdAfterSearchUI(String cityName){
        this.searchWeatherByCityName(cityName);
        String URL = WebDriverRunner.getWebDriver().getCurrentUrl();
        String uiId = URL.substring(31, URL.length());
        return  uiId;
    }



}
