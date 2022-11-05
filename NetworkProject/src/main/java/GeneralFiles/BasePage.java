package GeneralFiles;

import BaseFiles.DriverManager;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import io.cucumber.java.Scenario;
import io.cucumber.java.en_old.Ac;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import org.openqa.selenium.interactions.Actions;

public class BasePage {
    WebDriver driver = null;

    public BasePage() {
        driver = DriverManager.getDriver();
    }

    public WebElement waitUntilVisibleByLocator(By locator) {
        WebElement element = null;

        try {
            Wait<WebDriver> wait = (new FluentWait(this.driver)).withTimeout(Duration.ofSeconds(30L)).pollingEvery(Duration.ofSeconds(1L)).ignoring(NoSuchElementException.class);
            element = (WebElement) wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }

        return element;
    }

    public WebElement waitUntilPresenceOfElement(By locator) {
        WebElement element = null;

        try {
            Wait<WebDriver> wait = (new FluentWait(this.driver)).withTimeout(Duration.ofSeconds(30L)).pollingEvery(Duration.ofMillis(1L)).ignoring(StaleElementReferenceException.class).ignoring(NoSuchElementException.class);
            element = (WebElement) wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
        return element;
    }

    public boolean checkElementIsExist(By locator) {
        try {
            WebElement element = (WebElement) driver.findElement(locator);

            if (element.isDisplayed() || element.isEnabled()) {
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {

            return false;
        }
    }

    public void clickElement(By locator) {
        WebElement element = this.waitUntilVisibleByLocator(locator);
        element.click();
    }

    public boolean waitElementWithThreadSleep(By locator, int second) {

        boolean elementExist = false;
        try {
            elementExist = checkElementIsExist(locator);

            if (!elementExist) {

                for (int i = 0; i < 2 * second; i++) {
                    Thread.sleep(500);
                    elementExist = checkElementIsExist(locator);
                    if (elementExist) {
                        elementExist = true;
                        break;
                    }
                }
            }

            Thread.sleep(1000);
        } catch (Exception e) {
            //report.Report_Fail(e.getMessage());
        }

        return elementExist;
    }

    public void clickElementIfItExists(By locator, String second) {
        boolean elementExist = waitElementWithThreadSleep(locator, Integer.valueOf(second));
        if (elementExist) {
            clickElement(locator);
        }
    }

    public String getDate(String dateFormat) {
        DateFormat sdf = new SimpleDateFormat(dateFormat);
        Date date = new Date();
        return sdf.format(date);
    }

    public void takeScreenshot(Scenario scenario) {
        try {
            final byte[] screenshot = ((TakesScreenshot) driver)
                    .getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "screenshot-" + getDate("ddMMyyyy-HHmmss"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }


    public void sendKeysToElement(By locator, String text) {
        WebElement element = this.waitUntilVisibleByLocator(locator);
        element = this.waitUntilPresenceOfElement(locator);
        element.clear();
        element.sendKeys(new CharSequence[]{text});
    }

    public String getCurrentURL() {
        try {
            return driver.getCurrentUrl();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
        return null;
    }

    public void clickEnter(By locator) {
        try {
            WebElement element = this.waitUntilVisibleByLocator(locator);
            element.sendKeys(Keys.ENTER);

        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    public void scrollToBottom() {

        try {

            int x = driver.manage().window().getSize().width / 2;
            int start_y = (int) (driver.manage().window().getSize().height * 0.8);
            int end_y = (int) (driver.manage().window().getSize().height * 0.2);

            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollBy(0," + (start_y - end_y) + ")", "");

        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }

    }

    public boolean checkElementDisplayed(By locator) {
        try {
            return driver.findElement(locator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }


    public void scrollToButtonTillElementDisplayed(By locator) {
        try {

            boolean elementExist = checkElementDisplayed(locator);

            if (!elementExist) {
                for (int i = 0; i < 20; i++) {

                    scrollToBottom();
                    sleep(1000);
                    if (checkElementDisplayed(locator)) {
                        break;
                    }
                }
            }

//            WebElement element = driver.findElement(locator);
//            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
//            Thread.sleep(1000);

        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    public void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }


    public String getTextInElement(By locator) {
        try {
            return driver.findElement(locator).getText();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
        return null;
    }


    public void mauseHover(By locator) {


        WebElement ele = driver.findElement(locator);

        Actions action = new Actions(driver);

        action.moveToElement(ele).perform();

    }


    public void randomSizeClick(By locator) {
        List<WebElement> allLinks = driver.findElements(locator);
        Random random = new Random();
        WebElement randomLink = allLinks.get(random.nextInt(allLinks.size()));
        randomLink.click();

    }


 /*   public void dataReadForCsv(By locator) throws IOException, CsvValidationException {

        CSVReader csvReader = new CSVReader(new FileReader("user.csv"));


        String[] csvCell;

        while ((csvCell = csvReader.readNext()) != null) {
            String UserMail = csvCell[0];
            String UserPassword = csvCell[1];

            driver.findElement(locator).sendKeys(UserMail);
            driver.findElement(locator).sendKeys(UserPassword);

        }


    }*/


}
