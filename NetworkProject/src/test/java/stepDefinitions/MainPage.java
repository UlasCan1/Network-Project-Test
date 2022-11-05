package stepDefinitions;

import GeneralFiles.BasePage;
import io.cucumber.java.en.And;
import org.openqa.selenium.By;
import org.slf4j.MDC;
import org.testng.Assert;

public class MainPage {

    By cerezKabul_Btn=By.id("onetrust-accept-btn-handler");
    By search_Btn=By.id("search");

    @And("Cerez kabul pop up ini max {string} sn bekle.Gorunur olunca kabul et butonuna tikla")
    public void clickRizaMetniKabulBtn(String second){
        BasePage basePage=new BasePage();
        basePage.clickElementIfItExists(cerezKabul_Btn,second);
    }

    @And("Anasayfadaki URL {string} seklinde mi kontrol edilir")
    public void anasayfadaUrunAra(String url){
        BasePage basePage=new BasePage();
        String getURL=basePage.getCurrentURL();
        if (!getURL.equals(url)){
            Assert.fail("Anasayfadaki url ile istenen url farklidir. Beklenen URL:"+url+", Tespit edilen URL: "+ getURL);
        }
    }

    @And("Anasayfa arama alaninda {string} aratilir")
    public void searchProduct(String productName){
        BasePage basePage=new BasePage();
        basePage.sendKeysToElement(search_Btn,productName);
    }

    @And("Anasayfa arama alaninda ENTER tusuna basilir")
    public void clickEnter(){
        BasePage basePage=new BasePage();
        basePage.clickEnter(search_Btn);
    }











}
