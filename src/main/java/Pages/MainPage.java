package Pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class MainPage {

    protected static SelenideElement searchInput = $x(".//div[@id='weather-widget']//input");
    protected static SelenideElement searchButton = $x(".//div[@id='weather-widget']//button");
    protected static SelenideElement searchDropdownOption1 = $x(".//ul[@class='search-dropdown-menu']/li[1]");
    protected static SelenideElement cityTitletext = $x(".//div[@class='grid-container grid-4-5']//h2");
    protected static SelenideElement SignInLink = $x(".//div[@id='desktop-menu']//li[@class='user-li']/a");

    public MainPage openMainPage() {
        Selenide.open("https://openweathermap.org/");
        return this;
    }

    public void enterCityNameIntoTheField(String cityName) {
        searchInput.sendKeys(cityName);
        searchButton.click();
        searchDropdownOption1.click();
    }

    public String getSearchResultTitle() {
        String cityTitle = cityTitletext.getText();
        return cityTitle;
    }

}
