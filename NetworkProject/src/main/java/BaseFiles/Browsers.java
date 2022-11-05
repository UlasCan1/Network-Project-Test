package BaseFiles;


import org.openqa.selenium.WebDriver;

public class Browsers {


    public static WebDriver prepareDriver() {
        BaseBrowser browser = Browser.prepareBrowser();
        WebDriver driver;
        if (!PropertiesFileReader.getDataInPropertiesFile("remote").equalsIgnoreCase("false")) {
            driver = browser.runRemote(getGridUrl());
        } else {
            driver = browser.runLocal();
        }

        return driver;
    }

    private static String getGridUrl() {

        return PropertiesFileReader.getDataInPropertiesFile("gridUrl")+"/wd/hub";
    }



}
