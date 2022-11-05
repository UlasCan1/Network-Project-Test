package BaseFiles;

import org.openqa.selenium.WebDriver;

public interface BaseBrowser {

    WebDriver runLocal();
    WebDriver runRemote(String var1);


}
