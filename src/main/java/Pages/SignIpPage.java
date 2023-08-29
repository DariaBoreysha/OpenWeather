package Pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class SignIpPage {


    protected static SelenideElement createAnAccountLink = $x(".//div[@class='col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2']//p/a");

    public SignIpPage openSignInPage(){
        SignInLink.click();
        return this;
    }

    public CreateAnAccountPage goToRegistryForm(){
        createAnAccountLink.click();
        return new CreateAnAccountPage();
    }


}
