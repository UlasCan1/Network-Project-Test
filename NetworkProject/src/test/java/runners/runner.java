package runners;

import BaseFiles.Browsers;
import BaseFiles.DriverManager;
import BaseFiles.PropertiesFileReader;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;


import java.util.concurrent.TimeUnit;

@CucumberOptions(
        features = {"src/test/java/features"},
        glue = {"stepDefinitions"},
        tags = "@AnasayfaURLKontrolu",
        plugin =   {
                "pretty",
                "json:test-output/cucumber-reports/cucumber.json",
                "junit:test-output/cucumber-reports/cucumber.xml",
                "html:test-output/cucumber-reports.html",
                "rerun:test-output/cucumber-reports/newFailed_scenarios.txt"
        },
        monochrome = true
)

public class runner extends AbstractTestNGCucumberTests {
    static WebDriver driver = null;
    String browserName= "";
    String url="";

    @BeforeSuite
    public void beforeSuite() {
        browserName= PropertiesFileReader.getDataInPropertiesFile("browserName");
        url=PropertiesFileReader.getDataInPropertiesFile("url");
    }


    @BeforeMethod
    public void beforeMethod() {
        try {
            DriverManager.setDriver(Browsers.prepareDriver());
            DriverManager.getDriver().navigate().to(url);
            DriverManager.getDriver().manage().window().maximize();
            DriverManager.getDriver().manage().timeouts().pageLoadTimeout(120L, TimeUnit.SECONDS);

        }catch (Exception e){
            Assert.fail(e.getMessage());
        }

    }

    @AfterMethod
    public void afterMethod() {

        if (DriverManager.getDriver() != null && !DriverManager.getDriver().toString().contains("null")) {
            DriverManager.getDriver().quit();
        }

    }



}
