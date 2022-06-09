import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import parser.UsersParser;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static java.time.Duration.ofMillis;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;


public class StepDefinitions extends runAppiumDriver{

    ElementsParser parser = new ElementsParser();
    private Logger logger = LoggerFactory.getLogger(getClass());

    UsersParser user = new UsersParser();

    @Given("Open Application")
    public void openAppication() throws MalformedURLException, InterruptedException {
        appiumDriver = createAppiumDriver();
    }

    @And("Send Keys Enter")
    public void sendKeysEnter() {
        appiumDriver.findElementByXPath("(//*[@class='android.view.ViewGroup'])[last()]").sendKeys(Keys.ENTER);
       // action.sendKeys(Keys.ENTER).build().perform();
    }

    @And("Wait {int} seconds")
    public void waitBySecond(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @And("Click on {string} element")
    public void clickByKey(String key) throws FileNotFoundException {
        findElementByXpath(key).click();
        logger.info(key + " elementine tiklandi");
    }

    @And("{string} verify if element exists")
    public void isExistElement(String key) throws FileNotFoundException {
        assertTrue(findElementByXpath(key).isDisplayed(), "Element sayfada bulunamadi !");
        logger.info(key + "  elementi sayfada var");
    }

    @And("{string} verify if doesnt element exists")
    public void isDoesntExistElement(String key) throws FileNotFoundException {
        assertFalse(findElementByXpath(key).isDisplayed(), "Element sayfada bulundu !");
        logger.info(key + "  elementi sayfada yok");
    }

    @And("{int},{int} koordinatlarina tikla")
    public void koordinataTikla(int x, int y) {
        TouchAction a2 = new TouchAction(appiumDriver);
        a2.tap(PointOption.point(x, y)).perform();
        logger.info(x + y + "koordinatlarina tiklandi");
    }

    @And("Find element by {string} clear and send keys {string}")
    public void sendKeysByKey(String key, String text) throws FileNotFoundException {
        MobileElement webElement = findElementByXpath(key);
        webElement.clear();
        webElement.setValue(text);
        logger.info(key + " elementine " + text + " texti yazildi ");

    }

    @And("If the {string} element exists, type {string}")
    public void isExistElementAndSendKeysByKey(String key, String text) throws FileNotFoundException {
        List<MobileElement> elements = appiumDriver.findElementsByXPath(key);
        if (elements.size() > 0) {
            sendKeysByKey(key, text);
        } else {
            waitBySecond(1);
        }
    }

    public MobileElement findElementByXpath(String key) throws FileNotFoundException {
        String elementValue = parser.getElementKey(key);
        return appiumDriver.findElementByXPath(elementValue);

    }

    public MobileElement pFindElementByXpath(String key) throws FileNotFoundException {
        return appiumDriver.findElementByXPath(key);
    }

    public List<MobileElement> pFindElementsByXpath(String key) {
        return appiumDriver.findElementsByXPath(key);
    }

    @And("Find element by {string} and send keys {string}")
    public void sendKeysByKeyNotClear(String key, String text) throws FileNotFoundException {
        findElementByXpath(key).sendKeys(text);
        logger.info(key + " elementine " + text + " texti yazildi ");
    }

    @And("{string} yonune swipe et")
    public void swipe(String yon) {

        Dimension d = appiumDriver.manage().window().getSize();
        int height = d.height;
        int width = d.width;

        if (yon.equals("RIGHT")) {

            int swipeStartWidth = (width * 80) / 100;
            int swipeEndWidth = (width * 30) / 100;

            int swipeStartHeight = height / 2;
            int swipeEndHeight = height / 2;

            //appiumDriver.swipe(swipeStartWidth, swipeStartHeight, swipeEndWidth, swipeEndHeight, 1000);
            new TouchAction(appiumDriver)
                    .press(PointOption.point(swipeStartWidth, swipeStartHeight))
                    .waitAction(WaitOptions.waitOptions(ofMillis(1000)))
                    .moveTo(PointOption.point(swipeEndWidth, swipeEndHeight))
                    .release()
                    .perform();
        } else if (yon.equals("LEFT")) {

            int swipeStartWidth = (width * 30) / 100;
            int swipeEndWidth = (width * 80) / 100;

            int swipeStartHeight = height / 2;
            int swipeEndHeight = height / 2;

            //appiumDriver.swipe(swipeStartWidth, swipeStartHeight, swipeEndWidth, swipeEndHeight, 1000);

            new TouchAction(appiumDriver)
                    .press(PointOption.point(swipeStartWidth, swipeStartHeight))
                    .waitAction(WaitOptions.waitOptions(ofMillis(1000)))
                    .moveTo(PointOption.point(swipeEndWidth, swipeEndHeight))
                    .release()
                    .perform();
        }
    }

    @And("Swipe times {int}")
    public void swipe(int times) {
        for (int i = 0; i < times; i++) {
            swipeDownAccordingToPhoneSize();
            waitBySecond(3);

            System.out.println("-----------------------------------------------------------------");
            System.out.println("SWIPE EDILDI");
            System.out.println("-----------------------------------------------------------------");

        }
    }

    @And("geri butonuna bas")
    public void clickBybackButton() {
        backPage();
    }


    @And("uygulamayi yeniden baslat")
    public void restart() {
        appiumDriver.closeApp();
        appiumDriver.launchApp();
        logger.info("uygulama yeniden baslatildi");
        waitBySecond(5);

    }

    private void backPage() {
        appiumDriver.navigate().back();
    }


    public void tapElementWithCoordinate(int x, int y) {
        TouchAction a2 = new TouchAction(appiumDriver);
        a2.tap(PointOption.point(x, y)).perform();
    }

    @And("{string} li elementin  merkezine tikla")
    public void tapElementWithKey(String key) throws FileNotFoundException {

        Point point = findElementByXpath(key).getCenter();
        TouchAction a2 = new TouchAction(appiumDriver);
        a2.tap(PointOption.point(point.x, point.y)).perform();
        logger.info(key + " elementinin merkezine tiklandi");

    }

    @And("{string} li elementin  merkezine  press ile cift tikla")
    public void pressElementWithKey(String key) throws FileNotFoundException {

        Point point = findElementByXpath(key).getCenter();
        TouchAction a2 = new TouchAction(appiumDriver);
        a2.press(PointOption.point(point.x, point.y)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(300)))
                .press(PointOption.point(point.x, point.y)).release().perform();
        logger.info(key + "li elementin  merkezine  press ile cift tiklandi");

    }

    @And("{string} li elementin  merkezine double tikla")
    public void pressElementWithKey2(String key) throws FileNotFoundException {
        Actions actions = new Actions(appiumDriver);
        actions.moveToElement(findElementByXpath(key));
        actions.doubleClick();
        actions.perform();
        appiumDriver.getKeyboard();
        logger.info(key + " elementin merkezine tiklandi");

    }

    @And("{string} li elementi rasgele sec")
    public void chooseRandomProduct(String key) {

        List<MobileElement> productList = new ArrayList<>();
        List<MobileElement> elements = pFindElementsByXpath(key);
        int elementsSize = elements.size();
        int height = appiumDriver.manage().window().getSize().height;
        for (int i = 0; i < elementsSize; i++) {
            MobileElement element = elements.get(i);
            int y = element.getCenter().getY();
            if (y > 0 && y < (height - 100)) {
                productList.add(element);
            }
        }
        Random random = new Random();
        int randomNumber = random.nextInt(productList.size());
        productList.get(randomNumber).click();
        logger.info("rasgele element secildi");

    }

    @And("Hide keyboard")
    public void hideAndroidKeyboard() {
        appiumDriver.hideKeyboard();
    }

    @And("Swipe up times {int}")
    public void swipeUP(int times) {
        for (int i = 0; i < times; i++) {
            swipeUpAccordingToPhoneSize();
            waitBySecond(1);

            System.out.println("-----------------------------------------------------------------");
            System.out.println("SWIPE EDILDI");
            System.out.println("-----------------------------------------------------------------");

        }
    }


    public void swipeUpAccordingToPhoneSize() {
        if (appiumDriver instanceof AndroidDriver) {
            Dimension d = appiumDriver.manage().window().getSize();
            int height = d.height;
            int width = d.width;
            System.out.println(width + " " + height);

            int swipeStartWidth = width / 2, swipeEndWidth = width / 2;
            int swipeStartHeight = (height * 20) / 100;
            int swipeEndHeight = (height * 80) / 100;
            //appiumDriver.swipe(swipeStartWidth, swipeStartHeight, swipeEndWidth, swipeEndHeight, 1000);
            new TouchAction(appiumDriver)
                    .press(PointOption.point(swipeStartWidth, swipeEndHeight))
                    .waitAction(WaitOptions.waitOptions(ofMillis(2000)))
                    .moveTo(PointOption.point(swipeEndWidth, swipeStartHeight))
                    .release()
                    .perform();
        } else {
            Dimension d = appiumDriver.manage().window().getSize();
            int height = d.height;
            int width = d.width;

            int swipeStartWidth = width / 2, swipeEndWidth = width / 2;
            int swipeStartHeight = (height * 35) / 100;
            int swipeEndHeight = (height * 75) / 100;
            //appiumDriver.swipe(swipeStartWidth, swipeStartHeight, swipeEndWidth, swipeEndHeight, 1000);
            new TouchAction(appiumDriver)
                    .press(PointOption.point(swipeStartWidth, swipeEndHeight))
                    .waitAction(WaitOptions.waitOptions(ofMillis(2000)))
                    .moveTo(PointOption.point(swipeEndWidth, swipeStartHeight))
                    .release()
                    .perform();
        }
    }


    public void swipeDownAccordingToPhoneSize() {
        if (appiumDriver instanceof AndroidDriver) {
            Dimension d = appiumDriver.manage().window().getSize();
            int height = d.height;
            int width = d.width;

            int swipeStartWidth = width / 2, swipeEndWidth = width / 2;
            int swipeStartHeight = (height * 90) / 100;
            int swipeEndHeight = (height * 50) / 100;
            //appiumDriver.swipe(swipeStartWidth, swipeStartHeight, swipeEndWidth, swipeEndHeight, 1000);
            new TouchAction(appiumDriver)
                    .press(PointOption.point(swipeStartWidth, swipeStartHeight))
                    .waitAction(WaitOptions.waitOptions(ofMillis(1000)))
                    .moveTo(PointOption.point(swipeEndWidth, swipeEndHeight))
                    .release()
                    .perform();
        } else {
            Dimension d = appiumDriver.manage().window().getSize();
            int height = d.height;
            int width = d.width;

            int swipeStartWidth = width / 2, swipeEndWidth = width / 2;
            int swipeStartHeight = (height * 90) / 100;
            int swipeEndHeight = (height * 40) / 100;
            // appiumDriver.swipe(swipeStartWidth, swipeStartHeight, swipeEndWidth, swipeEndHeight, 1000);
            new TouchAction(appiumDriver)
                    .press(PointOption.point(swipeStartWidth, swipeStartHeight))
                    .waitAction(WaitOptions.waitOptions(ofMillis(1000)))
                    .moveTo(PointOption.point(swipeEndWidth, swipeEndHeight))
                    .release()
                    .perform();
        }
    }


    @And("Find element by {string} swipe")
    public void findByKeyWithSwipe(String key) {
        int maxRetryCount = 10;
        while (maxRetryCount > 0) {
            List<MobileElement> elements = pFindElementsByXpath(key);
            if (elements.size() > 0) {
                if (!elements.get(0).isDisplayed()) {
                    maxRetryCount--;

                    swipeDownAccordingToPhoneSize();

                } else {
                    System.out.println(key + " element bulundu");
                    break;
                }
            } else {
                maxRetryCount--;
                swipeDownAccordingToPhoneSize();

            }

        }
    }

    @And("Login Account {string},{string}")
    public void loginAccount(String citizenId, String password) throws InterruptedException, FileNotFoundException {
        List<MobileElement> elementsOne = pFindElementsByXpath("//*[@resource-id='home_login_button']");
        if (elementsOne.size() > 0) {
            pFindElementByXpath("//*[@resource-id='home_login_button']").click();
            waitBySecond(1);
            pFindElementByXpath("//*[@resource-id='citizenId_input']").sendKeys(citizenId);
            waitBySecond(1);
            pFindElementByXpath("//*[@resource-id='password_input']").sendKeys(password);
            waitBySecond(1);
            pFindElementByXpath("//*[@resource-id='login_button']").click();
            waitBySecond(2);
            List<MobileElement> elementsOTP = pFindElementsByXpath("//*[contains(@resource-id,'cell')]");
            if (elementsOTP.size() > 0) {
                pFindElementByXpath("//*[@resource-id='cell_0']").sendKeys("1");
                pFindElementByXpath("//*[@resource-id='cell_1']").sendKeys("2");
                pFindElementByXpath("//*[@resource-id='cell_2']").sendKeys("3");
                pFindElementByXpath("//*[@resource-id='cell_3']").sendKeys("4");
                hideAndroidKeyboard();
                pFindElementByXpath("//*[@resource-id='verify_sms_button']").click();
            }
        } else {
            pFindElementByXpath("//*[@resource-id='password_input']").sendKeys(password);
            waitBySecond(1);
            pFindElementByXpath("//*[@resource-id='login_button']").click();
            waitBySecond(1);
            List<MobileElement> elementsOTP = pFindElementsByXpath("//*[@resource-id='cell_0']");
            if (elementsOTP.size() > 0) {
                pFindElementByXpath("//*[@resource-id='cell_0']").sendKeys("1");
                pFindElementByXpath("//*[@resource-id='cell_1']").sendKeys("2");
                pFindElementByXpath("//*[@resource-id='cell_2']").sendKeys("3");
                pFindElementByXpath("//*[@resource-id='cell_3']").sendKeys("4");
                hideAndroidKeyboard();
                pFindElementByXpath("//*[@resource-id='verify_sms_button']").click();
            }
        }
    }
    @And("Sign Out Account")
    public void signOutAccount() {

    }

    @And("click {string}")
    public void iClick(String elementKey) throws FileNotFoundException {
        findElementByXpath(elementKey).click();
    }

}



