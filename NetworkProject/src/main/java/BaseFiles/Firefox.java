package BaseFiles;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.TestException;

import java.net.MalformedURLException;
import java.net.URL;

public class Firefox implements BaseBrowser {



    @Override
    public WebDriver runLocal() {
        WebDriver driver=null;
        try {
            WebDriverManager.firefoxdriver().setup();
            driver= new FirefoxDriver();

        }catch (Exception e){
            Assert.fail(e.getMessage());
        }
       return driver;
    }

    @Override
    public WebDriver runRemote(String gridUrl) {

        //System.setProperty("webdriver.gecko.driver", driverPath);
        DesiredCapabilities capabilities =  DesiredCapabilities.firefox();
        FirefoxOptions options = new FirefoxOptions();
        capabilities.setCapability("marionette", true);
        capabilities.setCapability("networkConnectionEnabled", true);
        capabilities.setCapability("browserConnectionEnabled", true);

        // Creating firefox profile
        FirefoxProfile profile = new FirefoxProfile();

        // Instructing firefox to use custom download location
        profile.setPreference("browser.download.folderList", 2);


        // Skipping Save As dialog box for types of files with their MIME
        profile.setPreference("browser.helperApps.neverAsk.saveToDisk",
                "text/csv,application/java-archive, application/x-msexcel,application/excel,application/vnd.openxmlformats-officedocument.wordprocessingml.document,application/x-excel,application/vnd.ms-excel,image/png,image/jpeg,text/html,text/plain,application/msword,application/xml,application/vnd.microsoft.portable-executable");

        // Creating FirefoxOptions to set profile
        options.setProfile(profile);
        capabilities.merge(options);

        try {
            WebDriver webDriver = new RemoteWebDriver(new URL(gridUrl), capabilities);
            return webDriver;
        } catch (Exception e) {
            throw new TestException(e);
        }

    }
}
