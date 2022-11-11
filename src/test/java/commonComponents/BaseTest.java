package commonComponents;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.InterfaceAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import utils.CommonUtil;

public class BaseTest extends CommonUtil{

    public AndroidDriver driver;
    public IOSDriver driver1;


    @BeforeClass
    public void initializeDriver() throws IOException {

        Properties property = new Properties();
        FileInputStream stream = new FileInputStream("System.dir" + "/src/main/java/resources/GlobalData.properties");
        property.load(stream);

        String ipAddress = property.getProperty("ipAddress");
        String port = property.getProperty("port");
        String os = property.getProperty("os");

        service = startAppiumServer(ipAddress, Integer.parseInt(port));

        if (os.equalsIgnoreCase("Android")){
            UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName(property.getProperty("androidDeviceName"));
        options.setApp("/Users/aashimathakral/Documents/AppiumFramework/src/test/java/resources/ApiDemos-debug.apk");
         driver = new AndroidDriver(service.getUrl(), options);
        }
        else if(os.equalsIgnoreCase("ios")){
            XCUITestOptions options = new XCUITestOptions();
            options.setDeviceName(property.getProperty("iosDeviceName"));
            options.setApp("/Users/aashimathakral/Desktop/UIKitCatalog.app");
            options.setPlatformVersion("16.1");
            options.setWdaLaunchTimeout(null); // Appium Installs Webdriver Agent in IOS device

            driver1 = new IOSDriver(service.getUrl(), options);
        }
        
    }

    @AfterClass
    public void close(){
        driver.quit();
        service.stop();
    }
}