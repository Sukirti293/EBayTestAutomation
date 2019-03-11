package PageObject;


import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import EBayDesiredCapabilities.DesiredCapabilitiesEbay;
import TestAutomationMain.EBayTestMain;
import Utilities.CaptureScreenshots;
import Utilities.IDsToRead;
import Utilities.Resources;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;

public class SignInEbay extends DesiredCapabilitiesEbay {

    AppiumDriver<AndroidElement> appiumDriver = null;
    PageObjects pageObjects = null;
    CaptureScreenshots captureScreenshots =null;
    WebDriverWait webDriverWait = null;
    String username = null;
    String password = null;
    Logger logger = null;

    /**
     * Default constructor for initializing:
     * appiumDriuver, pageObjects, logger, capturescreenshots objects etc.
     *
     * @return null
     * @param null
     */


    public SignInEbay(){
        super();
        appiumDriver = DesiredCapabilitiesEbay.appiumDriver;
        webDriverWait = DesiredCapabilitiesEbay.webDriverWait;
        pageObjects = new PageObjects();
        PageFactory.initElements(appiumDriver, pageObjects);
        logger = Logger.getLogger(EBayTestMain.class.getName());
        captureScreenshots =  new CaptureScreenshots();
    }


    /**
     * Parent Method to make a call to the sub-methods such as validatdeSignInPage and SignIntoEbayApp
     *
     * @return none as the return type is void
     * @param null
     */

    public boolean SignInToEbay(){
    	boolean Finalresult = false;

         // Checking the application has signed in
         Assert.assertTrue("Verifying the display of Home Page",pageObjects.Home.isDisplayed());
         pageObjects.Home.click();

         try {
             if(pageObjects.SignOutStatus.isDisplayed()) {
                 Assert.assertTrue("Verifying the signIn status",pageObjects.SignOutStatus.isDisplayed());
                 pageObjects.SignOutStatus.click();

                 if(validateSignInPage()){
                     boolean result = signIntoEbayApp();
                     Assert.assertTrue("Verifying signIn page validation result",result);
                     captureScreenshots.captureScreenShots("Login Success");
                     Finalresult = true;
                     logger.info("SignIn has successfully completed - PASS");
                 }else{
                     logger.error("SignIn Page Validation Failed");
                     captureScreenshots.captureScreenShots("SIGNIN_Validation_Failure");
                 }

             }else{
                 captureScreenshots.captureScreenShots("Already_SignedIN");
                 logger.info("Application has already Signed IN");
             }
         }catch (Exception exp) {
             logger.error("SignIn Failed"+exp);
             captureScreenshots.captureScreenShots("SignIn Error");

         }

         webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id(IDsToRead.EbayLogo)));
         return Finalresult;

     }

    /**
     * Validating each field before signIn
     *
     * @return boolean value true if all the signIn fields are available else return false
     * @param null
     */

    public boolean validateSignInPage(){
        boolean flag = false;
        try{
            if(pageObjects.SignUserName.isDisplayed()){
                if(pageObjects.SignInPassword.isDisplayed()){
                    if(pageObjects.SignInButton.isDisplayed()){
                        flag = true;
                        logger.info("username and password field displayed");
                    }else{
                        logger.error("username is not available in the screen");
                        captureScreenshots.captureScreenShots("Username_Failure"); }
                }else{
                    logger.error("password is not available in the screen");
                    captureScreenshots.captureScreenShots("Password_Failure"); }
            }else{
                logger.error("sign in button is not available in the screen");
                captureScreenshots.captureScreenShots("SignInButton_Failure"); }

            return flag;
        }catch (Exception ex){
            logger.error("Exception occurred in validateSignInPage method "+ex);
            captureScreenshots.captureScreenShots("Validate_SignIn_Failure");
            return flag;
            }
    }

    /**
     * Method to perform signIn functionality
     *
     * @return boolean value true if signIn is successful else returns false
     * @param AppiumDriver
     */

    public boolean signIntoEbayApp(){
        boolean result = true;
        try {

            username = new Resources().getPropValues("EmailID");
            password = new Resources().getPropValues("Password");

            Assert.assertTrue("Verifying the username field",pageObjects.SignUserName.isDisplayed());
            pageObjects.SignUserName.sendKeys(username);

            Assert.assertTrue("Verifying the password field",pageObjects.SignInPassword.isDisplayed());
            pageObjects.SignInPassword.sendKeys(password);

            Assert.assertTrue("Verifying the signIn button",pageObjects.SignInButton.isDisplayed());
            pageObjects.SignInButton.click();


            webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id(IDsToRead.GoogleDeny)));

            Assert.assertTrue("Verifying the GoogleStaySignIn deny field",pageObjects.GoogleDeny.isDisplayed());
            pageObjects.GoogleDeny.click();

            return result;
        }catch (Exception ex){
            result = false;
            return result;
        }


    }



}
