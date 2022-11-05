package stepDefinitions;

import GeneralFiles.BasePage;
import io.cucumber.java.en.And;

public class CommonMethods {

    @And("{string} ms bekle")
    public void sleep(String ms){
        BasePage basePage=new BasePage();
        basePage.sleep(Integer.parseInt(ms));
    }
}
