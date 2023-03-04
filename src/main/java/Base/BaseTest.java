package Base;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

public class BaseTest extends AbstractTestNGCucumberTests {

    public static AppiumDriver driver;
    static Properties config ;
    static{
        config= readProperties(System.getProperty("user.dir")+"/src/test/resources/Config.properties");
    }

    @BeforeClass
    public void setUp() throws MalformedURLException {

        DesiredCapabilities Caps = new DesiredCapabilities();
        Caps.setCapability("platformName",config.getProperty("platformName"));
        Caps.setCapability("automationName",config.getProperty("automationName"));
        Caps.setCapability("platformVersion",config.getProperty("platformVersion"));
        Caps.setCapability("deviceName",config.getProperty("deviceName"));
        Caps.setCapability("autoGrantPermissions", config.getProperty("autoGrantPermissions"));
        Caps.setCapability("autoAcceptAlerts", config.getProperty("autoAcceptAlerts"));
        Caps.setCapability("noReset", config.getProperty("noReset"));
        Caps.setCapability("app",System.getProperty("user.dir")+"/apps/flutter-UI-debug.apk");

        driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"),Caps);

    }

    @AfterClass
    public void TearDown(){
        if (null != driver ){
            driver.quit();
        }
    }

    public static Properties readProperties(String filePath) {

        try (FileInputStream testProperties = new FileInputStream(filePath)) {
            Properties tempProp = new Properties();
            tempProp.load(testProperties);
            System.out.println("Properties file reading done: " + filePath);
            return tempProp;
        } catch (IOException e) {
            System.out.println("Properties file error: " + e.getMessage());
        }
        return null;
    }
}
