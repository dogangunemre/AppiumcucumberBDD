import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.After;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class runAppiumDriver {
    public static AppiumDriver<MobileElement> appiumDriver;

    public AppiumDriver<MobileElement> createAppiumDriver() throws MalformedURLException, InterruptedException {

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName", "emulator-5554");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("app", "/Applications/Emre/application/kocfinans.apk");
        appiumDriver = new AndroidDriver<MobileElement>(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
        TimeUnit.SECONDS.sleep(3);
        return appiumDriver;
    }

    @After
    public void afterScenario() {
        if (appiumDriver != null) {
            appiumDriver.quit();
        }
    }
}
