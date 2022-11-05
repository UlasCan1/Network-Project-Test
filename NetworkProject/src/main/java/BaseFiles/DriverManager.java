package BaseFiles;

import org.openqa.selenium.WebDriver;

public class DriverManager {

    private static ThreadLocal<WebDriver> driverThread = new ThreadLocal();

    public DriverManager() {
    }

    public static synchronized WebDriver getDriver() {
        return (WebDriver)driverThread.get();
    }

    public static synchronized void setDriver(WebDriver driver) {
        driverThread.set(driver);
    }

}