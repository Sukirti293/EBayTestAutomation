package Utilities;

import org.openqa.selenium.Dimension;
import io.appium.java_client.TouchAction;
import static EBayDesiredCapabilities.DesiredCapabilitiesEbay.appiumDriver;


public class TouchActionToScroll {

    /**
     * Default constructor to be invoked every time the object has been created.
     *
     * @return null
     * @param null
     */

    public TouchActionToScroll(){
        Dimension size = appiumDriver.manage().window().getSize();
        int anchor = (int) (size.width * 0.50);
        int startPoint = (int) (size.height * 0.50);
        int endPoint = (int) (size.height * 0.03);

        TouchAction touchAction = new TouchAction(appiumDriver)
                .longPress(anchor, startPoint)
                .waitAction(500)
                .moveTo(anchor, endPoint);
        touchAction.release().perform();
    }

}
