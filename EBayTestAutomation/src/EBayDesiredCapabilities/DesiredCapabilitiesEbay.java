  package EBayDesiredCapabilities;

import org.apache.log4j.Logger;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import Utilities.IDsToRead;
import java.net.URL;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import io.appium.java_client.android.AndroidElement;

public class DesiredCapabilitiesEbay {
	
	public static AppiumDriver<AndroidElement> appiumDriver = null;
    DesiredCapabilities desiredCapabilities;
    public static WebDriverWait webDriverWait = null;
    Logger logger = null;
    
    
    /**
     * Default constructor for initialising logger
     *
     * @return null
     * @param null
     */

    public DesiredCapabilitiesEbay(){
        super();
        logger = Logger.getLogger(DesiredCapabilitiesEbay.class.getName());
    }


    /**
     * sets desiredCapabilities such as deviceName, appPackage, appActivity, platformName
     *
     * @return null
     * @param null
     */

    public void setDesiredCapabilitues(){
        try{
            desiredCapabilities = new org.openqa.selenium.remote.DesiredCapabilities();
            desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, IDsToRead.Nexus25);
            desiredCapabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE,IDsToRead.eBayPackage);
            desiredCapabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY,IDsToRead.eBayActivity);
            desiredCapabilities.setCapability("testName", "DesiredCapabilitiesEbay");
            desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
            appiumDriver = new AndroidDriver<AndroidElement>(new URL(IDsToRead.AppiumServerURL),desiredCapabilities);
            webDriverWait = new WebDriverWait(appiumDriver,10);
        }
        catch (Exception exception){
        	logger.error("Exception occurred in initialisation method "+ exception);
        }
    }

    public AppiumDriver<AndroidElement> getDriver(){
        return appiumDriver;
    }

    public WebDriverWait getWebDriverWait(){
        return webDriverWait;
    }

}
