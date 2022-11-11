package utils;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

import java.io.File;

public class CommonUtil {

    public AppiumDriverLocalService service;
    public AppiumDriverLocalService startAppiumServer(String ipAddress, Integer port){
        service = new AppiumServiceBuilder().withAppiumJS(new File("/usr/local/lib/node_modules/appium/build/lib/main.js")).withIPAddress(ipAddress).usingPort(port).build();
        service.start();
        return service;
    }
    
}
