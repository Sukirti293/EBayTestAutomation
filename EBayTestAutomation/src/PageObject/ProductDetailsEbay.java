package PageObject;

import Utilities.CaptureScreenshots;
import EBayDesiredCapabilities.DesiredCapabilitiesEbay;
import Utilities.IDsToRead;
import Utilities.TouchActionToScroll;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;

public class ProductDetailsEbay {


    AppiumDriver<AndroidElement> appiumDriver = null;
    PageObjects pageObjects = null;
    Logger logger = null;
    CaptureScreenshots captureScreenshots =null;
    WebDriverWait webDriverWait = null;
    static String productPageName = null;
    static String productPagePrice = null;

    /**
     * Default constructor for initializing:
     * appiumDriuver, pageObjects, logger, capturescreenshots objects etc.
     *
     * @return null
     * @param null
     */


    public ProductDetailsEbay(){
        super();
        appiumDriver = DesiredCapabilitiesEbay.appiumDriver;
        webDriverWait = DesiredCapabilitiesEbay.webDriverWait;
        pageObjects = new PageObjects();
        PageFactory.initElements(appiumDriver, pageObjects);
        logger = Logger.getLogger(ProductDetailsEbay.class.getName());
        captureScreenshots =  new CaptureScreenshots();
    }


    /**
     * Method to get product details such as:
     * product name, product price
     * and store those details for further verification
     *
     * @return null
     * @param null
     */

    public void getProductDetails(){
        try {
            webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id(IDsToRead.Layout)));

            if (pageObjects.ProductPageName.isDisplayed()) {
                productPageName = pageObjects.ProductPageName.getText();
                logger.info("productPageName :: " + productPageName);
            } else {
                logger.error("Product Name is not displaying");
                captureScreenshots.captureScreenShots("ProductName Error");
            }
            if (pageObjects.ProductPagePrice.isDisplayed()) {
                productPagePrice = pageObjects.ProductPagePrice.getText();
                logger.info("productPagePrice :: " + productPagePrice);
            } else {
                logger.error("Product price is not displaying");
                captureScreenshots.captureScreenShots("ProductPrice Error");
            }
        }catch(Exception ex){
            logger.error("Exception occurred in getProductDetails method "+ex);
            captureScreenshots.captureScreenShots("GetProductDetails Failure");
        }


    }

    /**
     * Method to perform "BUY IT NOW" operation:
     * check for "BUY IT NOW" text if not available then scroll down and recheck
     * click "BUY IT NOW" option
     *
     * @return null
     * @param null
     */

    public boolean buyNow(){
    	boolean result = false;
        try {

            if(pageObjects.BuyNowButton.isDisplayed()){
                pageObjects.BuyNowButton.click();
                result = true;

            }else{
                logger.info("Seraching for BUY IT NOW text");
                
            }
        }catch (Exception ex) {
            new TouchActionToScroll();
            Assert.assertTrue("Verifying the BUY IT NOW button",pageObjects.BuyNowButton.isDisplayed());
            if(pageObjects.BuyNowButton.isDisplayed()){
                pageObjects.BuyNowButton.click();
                result = true;

            }else{
                logger.error("BUY IT NOW option is not available");
                captureScreenshots.captureScreenShots("BUYNOW Failure");}
        }
        return result;
    }

}
