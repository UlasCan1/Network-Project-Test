package BaseFiles;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.TestException;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Chrome implements BaseBrowser {



    public WebDriver runLocal() {
        WebDriverManager.chromedriver().setup();
    	
        DesiredCapabilities capabilities = new DesiredCapabilities();
        Map<String, Object> prefs = new HashMap();
        prefs.put("profile.default_content_setting_values.notifications", 2);
        prefs.put("profile.default_content_settings.popups", 0);
        prefs.put("download.prompt_for_download", false);

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments(new String[]{"--disable-notifications"});
        chromeOptions.addArguments(new String[]{"--disable-cache"});
        chromeOptions.setAcceptInsecureCerts(true);

        chromeOptions.setExperimentalOption("prefs", prefs);
        capabilities.setCapability(ChromeOptions.CAPABILITY,chromeOptions);
        return new ChromeDriver(capabilities);
    }
    
    
    public DesiredCapabilities capabilitiesForRemote() {
    	
    	DesiredCapabilities capabilities = new DesiredCapabilities();
    	try {
    		
            Map<String, Object> prefs = new HashMap();

          prefs.put("browser.download.folderList", 2);
          prefs.put("profile.default_content_settings.popups", 0);
          prefs.put("browser.helperApps.neverAsk.saveToDisk", "application/zip;");
          prefs.put( "browser.download.manager.showWhenStarting", false );
          prefs.put( "pdfjs.disabled", true );
          ChromeOptions chromeOptions = new ChromeOptions();
          chromeOptions.addArguments(new String[]{"--disable-notifications"});
          chromeOptions.addArguments(new String[]{"--disable-cache"});
          chromeOptions.setAcceptInsecureCerts(true);

          chromeOptions.setExperimentalOption("prefs", prefs);
          capabilities.setCapability(ChromeOptions.CAPABILITY,chromeOptions);
    		
			
		} catch (Exception e) {
			
		}    	
    	
    	return capabilities;
    	
    }

    public WebDriver runRemote(String gridUrl) {
        
        try {
        	DesiredCapabilities capabilities = capabilitiesForRemote();
            WebDriver driver = new RemoteWebDriver(new URL(gridUrl), capabilities);

            return driver;
        } catch (Exception e) {
            throw new TestException(e);
        }
    }


}
