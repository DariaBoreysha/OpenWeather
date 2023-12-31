package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

public class MainPage {

    protected static SelenideElement searchInput = $x(".//div[@id='weather-widget']//input");
    protected static SelenideElement searchButton = $x(".//div[@id='weather-widget']//button");
    protected static SelenideElement searchDropdownOption1 = $x(".//ul[@class='search-dropdown-menu']/li[1]");
    protected static SelenideElement cityTitleText = $x(".//div[@class='grid-container grid-4-5']//h2");
    protected static SelenideElement signInLink = $x(".//div[@id='desktop-menu']//li[@class='user-li']/a");

    public MainPage searchWeatherByCityName(String cityName) {
        searchInput.sendKeys(cityName);
        sleep(4000);
        searchButton.click();
        sleep(4000);
        searchDropdownOption1.click();
        return new MainPage();
    }

    public String getCityTitle() {
        String cityTitle = cityTitleText.getText();
        return cityTitle;
    }

    public SignIpPage openSignInPage() {
        sleep(5000);
        signInLink.shouldBe(Condition.interactable);
        signInLink.click();
        return page(new SignIpPage());
    }


}
