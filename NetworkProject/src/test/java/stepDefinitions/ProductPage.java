package stepDefinitions;

import GeneralFiles.BasePage;
import com.opencsv.exceptions.CsvValidationException;
import io.cucumber.java.en.And;
import org.openqa.selenium.By;
import org.testng.Assert;

import java.io.IOException;

public class ProductPage {


    By jacketPage_ShowMore_Btn=By.xpath("//div[contains(@class,'productList__moreContent ')]/button[contains(text(),'Daha fazla göster')]");

    By jacketPage_discountedProducts=By.xpath("//div[contains(@class,'product__discountPercent')]/parent::div/parent::div/preceding-sibling::div[@class='product__header']");
    By getJacketPage_shownProductNumbers=By.xpath("//strong[@class='js-product-total-count']");
    By getJacketPage_TotalProductNumber=By.xpath("//div[@class='productCount']");

    By getRandomSize_Product=By.xpath("/html/body/div[3]/div/div[2]/div[2]/div[1]/div[2]/div[1]/div/div[1]/div/div/div");

    By goToBasket = By.xpath("//a[@class='button -primary header__basket--checkout header__basketModal -checkout']");

    By continueButton=By.xpath("//button[@class='continueButton n-button large block text-center -primary']");

    By sendEmail=By.xpath("//input[@class='input js-trim']");

    By sendPassword=By.xpath("//input[@class='input']");

    @And("Daha Fazla Goster butonunu gorene kadar ekrani asagi kaydir")
    public void scrollPageTillShowMoreButtonIsVisible(){
        BasePage basePage=new BasePage();
        basePage.scrollToButtonTillElementDisplayed(jacketPage_ShowMore_Btn);
    }
    @And("Daha Fazla Goster butonuna tiklanir")
    public void clickEnter(){
        BasePage basePage=new BasePage();
        basePage.clickElement(jacketPage_ShowMore_Btn);
    }

    @And("Daha Fazla Goster butonuna tiklanir ve sayfalarin acildigi kontrol edilir")
    public void clickShowMoreButtonAndCheckPages(){
        BasePage basePage=new BasePage();
        // get current number of products
        String first=basePage.getTextInElement(getJacketPage_shownProductNumbers);
        String totalNumber=basePage.getTextInElement(getJacketPage_TotalProductNumber);
        String array[]=totalNumber.split("/");
        totalNumber=array[0].trim();
        // scroll page till show more button is displayed
        scrollPageTillShowMoreButtonIsVisible();
         // check the number of listed products with  total products
        if (first.equals(totalNumber)){
            Assert.fail("Listelenen urun sayisi ile toplam urun sayisi ilk durumda aynidir!");
        }

        // click show more button
        basePage.clickElement(jacketPage_ShowMore_Btn);
        basePage.sleep(3000);

        //check the number of listed products after the show more button was clicked
        String second=basePage.getTextInElement(getJacketPage_shownProductNumbers);
        if (!(Integer.parseInt(second)>Integer.parseInt(first))){
            Assert.fail("Daha Fazla Goster butonuna tiklandiktan sonra gosterilen urun sayisinda bir degisiklik olmamistir!");
        }
    }


    @And("Indirimli ilk urune hover edilir")
    public void discountProductHover(){
        BasePage basePage=new BasePage();
        basePage.mauseHover(jacketPage_discountedProducts);
    }

    @And("Rastgele urun secimi yapilir")
    public void randomClickProduct(){
        BasePage basePage=new BasePage();
        basePage.randomSizeClick(getRandomSize_Product);
    }

    @And("Sepete Git Tiklanilir")
    public void goToBasket(){
        BasePage basePage=new BasePage();
        basePage.clickElement(goToBasket);
    }


    @And("Devam Et Butonuna Tiklanir")
    public void clickContinueBotton(){
        BasePage basePage=new BasePage();
        basePage.clickEnter(continueButton);
    }

    /*@And("Mail ve sifre doldurulur")
    public void emailAndPassword() throws CsvValidationException, IOException {
        BasePage basePage=new BasePage();
        basePage.dataReadForCsv(sendEmail);
        basePage.dataReadForCsv(sendPassword);



    }*/





}
