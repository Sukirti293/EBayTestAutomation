package TestAutomationMain;

import org.testng.annotations.Test;
import EBayDesiredCapabilities.DesiredCapabilitiesEbay;
import PageObject.CheckOutEbay;
import PageObject.ProductDetailsEbay;
import PageObject.ProductSearchEbay;
import PageObject.SignInEbay;
import Utilities.CaptureScreenshots;
import io.appium.java_client.AppiumDriver;
import org.testng.annotations.BeforeTest;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import io.appium.java_client.android.AndroidElement;

public class EBayTestMain {
	
	  AppiumDriver<AndroidElement> appiumDriver = null;
	  WebDriverWait webDriverWait = null;
	  WebElement webElement = null;
	  DesiredCapabilitiesEbay desiredCapabilitiesEbay = null;
	  Logger logger = null;
	  CaptureScreenshots captureScreenshots =null;
	  
	  
	  /**
	   * @Before annotation enables the method execution before executing the test case
	   * initialisation method performs:
	   * Setting up the Desired Capabilities
	   * Retrieving webdriverWait and appiumDriver instance
	   *
	   *
	   * @return null
	   * @param
	   */

	  @BeforeTest
	  public void initialisation(){
	      try{

	          desiredCapabilitiesEbay = new DesiredCapabilitiesEbay();
	          desiredCapabilitiesEbay.setDesiredCapabilitues();
	          webDriverWait = desiredCapabilitiesEbay.getWebDriverWait();
	          appiumDriver = desiredCapabilitiesEbay.getDriver();
	          logger = Logger.getLogger(EBayTestMain.class.getName());
	          logger.info("Initialisation has successfully completed");
	          captureScreenshots =  new CaptureScreenshots();

	      }
	      catch (Exception exception){
	          logger.error("Exception occurred in initialisation method "+ exception);
	      }

	  }
	  
	  
	  /**
	   * @Test annotation enables the method to be executed as the testcase
	   * execution method performs:
	   * Initialization of sub classes
	   * Calls SignIn, search product, validate product details and checkout methods
	   *
	   * @return null
	   * @param
	   */

	  @Test
	  public void execution(){
	      try{

	          SignInEbay signInEbay = new SignInEbay();
	          boolean sign_in_status = signInEbay.SignInToEbay();
	          Assert.assertTrue("Assertion Failed : Login Unsuccessful",sign_in_status);

	          ProductSearchEbay productSearchEbay = new ProductSearchEbay();
	          boolean search_status = productSearchEbay.searchProduct();
	          Assert.assertTrue("Assertion Failed : Product Search Unsuccessful",search_status);

	          ProductDetailsEbay productDetailsEbay = new ProductDetailsEbay();
	          productDetailsEbay.getProductDetails();
	          boolean buynow_status = productDetailsEbay.buyNow();
	          Assert.assertTrue("Assertion Failed : validation of product details Unsuccessful",buynow_status);
	          

	          CheckOutEbay checkOutEbay = new CheckOutEbay();
	          boolean checkout_status = checkOutEbay.checkoutProduct();
	          Assert.assertTrue("Assertion Failed : Checkout Unsuccessful",checkout_status);

	      }

	      catch (Exception e){
	          logger.error("Exception occurred in execution method "+ e);
	          captureScreenshots.captureScreenShots("BUYNOW_Failure");}
	      }
	  


	  /**
	   * @After annotation enables the method execution after executing the test case
	   * cleanUp method closes the appium driver
	   *
	   *
	   * @return null
	   * @param
	   */

	  @AfterTest
	  public void cleanUp(){
	      try {
	          appiumDriver.quit();
	          logger.info("CleanUP has successfully completed");
	      }
	      catch (Exception exception){
	          logger.error("Exception occurred in cleanUp method "+ exception);
	      }
	  }

}
