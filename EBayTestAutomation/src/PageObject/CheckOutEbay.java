package PageObject;

import EBayDesiredCapabilities.DesiredCapabilitiesEbay;
import Utilities.CaptureScreenshots;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;

public class CheckOutEbay {


    AppiumDriver<AndroidElement> appiumDriver = null;
    WebElement webElement = null;
    PageObjects pageObjects = null;
    Logger logger = null;
    CaptureScreenshots captureScreenshots =null;
    WebDriverWait webDriverWait = null;

    /**
     * Default constructor for initializing:
     * appiumDriuver, pageObjects, logger, capturescreenshots objects etc.
     *
     * @return null
     * @param null
     */

    public CheckOutEbay(){
        super();
        appiumDriver = DesiredCapabilitiesEbay.appiumDriver;
        webDriverWait = DesiredCapabilitiesEbay.webDriverWait;
        pageObjects = new PageObjects();
        PageFactory.initElements(appiumDriver, pageObjects);
        logger = Logger.getLogger(CheckOutEbay.class.getName());
        captureScreenshots =  new CaptureScreenshots();
    }

    /**
     * Validate product details:
     * verify the product name and price in product selection page and in checkout page
     *
     * @return true if the product name and price is same in both of the page else returns false
     * @param null
     *
     */

    public boolean validateProductDetails(){
        boolean result = true;
        try {

            if (pageObjects.CheckOutPageName.isDisplayed()) {
                if (!pageObjects.CheckOutPageName.getText().equalsIgnoreCase(ProductDetailsEbay.productPageName)) {
                    logger.error("Product name mismatch");
                    result = false;
                    captureScreenshots.captureScreenShots("product name mismatch");
                }
            }
            if (!pageObjects.CheckOutPageprice.isDisplayed()) {
                if (pageObjects.CheckOutPageprice.getText().equalsIgnoreCase(ProductDetailsEbay.productPagePrice)) {
                    logger.error("Product price mismatch");
                    result = false;

                    captureScreenshots.captureScreenShots("product price mismatch");
                }
            }
            return result;
        }catch(Exception ex){
            logger.error("Exception occurred in validateProductDetails method "+ex);
            captureScreenshots.captureScreenShots("validateProductDetails_Failure");
            result = false;
            return result;
        }
    }


    /**
     * checking out the product:
     * after validating the details checkoutProduct method clicks review and buy now option
     *
     * @return null
     * @param null
     */

    public boolean checkoutProduct(){
    	boolean result = false;
        try {
            if (validateProductDetails()) {
                if (pageObjects.ReviewProductDetails.isDisplayed()) {
                	captureScreenshots.captureScreenShots("Review Button Success");
                    pageObjects.ReviewProductDetails.click();
                    result = true;
                } else {
                    logger.error("Review Option is not available");
                    captureScreenshots.captureScreenShots("Review_Button_Failure");
                }

            } else {
                logger.error("CheckOut failure");
                captureScreenshots.captureScreenShots("CheckOut Failure");
            }
        }catch(Exception ex){
            logger.error("Exception occurred in checkoutProduct method "+ex);
            captureScreenshots.captureScreenShots("checkoutProduct Failure");
        }
        return result;
    }
}
