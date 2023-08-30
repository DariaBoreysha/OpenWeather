package Pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class SignIpPage {


    protected static SelenideElement createAnAccountLink = $x(".//div[@class='col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2']//p/a");

    public CreateAnAccountPage goToRegistryForm() {
        createAnAccountLink.shouldBe(Condition.visible);
        createAnAccountLink.shouldBe(Condition.interactable);
        createAnAccountLink.click();
        return new CreateAnAccountPage();
    }


}
