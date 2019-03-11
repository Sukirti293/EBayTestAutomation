package PageObject;

import Utilities.CaptureScreenshots;
import EBayDesiredCapabilities.DesiredCapabilitiesEbay;
import Utilities.IDsToRead;
import Utilities.Resources;
import Utilities.TouchActionToScroll;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;


public class ProductSearchEbay {


    AppiumDriver<AndroidElement> appiumDriver = null;
    WebElement webElement = null;
    PageObjects pageObjects = null;
    Logger logger = null;
    CaptureScreenshots captureScreenshots =null;
    WebDriverWait webDriverWait = null;
    String productToSearch = null;


    /**
     * Default constructor for initializing:
     * appiumDriuver, pageObjects, logger, capturescreenshots objects etc.
     *
     * @return null
     * @param null
     */


    public ProductSearchEbay(){
        super();
        appiumDriver = DesiredCapabilitiesEbay.appiumDriver;
        webDriverWait = DesiredCapabilitiesEbay.webDriverWait;
        pageObjects = new PageObjects();
        PageFactory.initElements(appiumDriver, pageObjects);
        logger = Logger.getLogger(ProductSearchEbay.class.getName());
        captureScreenshots =  new CaptureScreenshots();
    }


    /**
     * Method performs actions such as:
     * Fetch the searchItem name form Excel sheet dynamically
     * clicking on search bar
     * Typing product name
     * Scrolling and selecting a random product
     *
     * @return null
     * @param null
     */

    public boolean searchProduct(){
    	boolean result = false;
        try {

            productToSearch = new Resources().getPropValues("searchItem")+"\n";

            Assert.assertTrue("Verifying the searchbox field", pageObjects.SearchBox.isDisplayed());
            pageObjects.SearchBox.click();

            Assert.assertTrue("Verifying the searchbox text field", pageObjects.SearchText.isDisplayed());
            pageObjects.SearchText.sendKeys(productToSearch);

            webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id(IDsToRead.Sort)));

            new TouchActionToScroll();

            List<AndroidElement> allelementwithsameid = appiumDriver.findElements(By.id(IDsToRead.ResultImage));
            int count = IDsToRead.ElementToChoose;
            if ((allelementwithsameid.size() > 0)) {
                logger.info("Size greater than zero");
                if (count < allelementwithsameid.size())
                    webElement = allelementwithsameid.get(count);
                else
                    webElement = allelementwithsameid.get(1);
                Assert.assertTrue("Verifying the search item list is displayed", webElement.isDisplayed());
                webElement.click();
                captureScreenshots.captureScreenShots("Search Result Found");
                result = true;
                logger.info("PASSED: Product Search");

            } else {
                logger.error("There are not serach result");
                captureScreenshots.captureScreenShots("No Result Found");
            }
        }catch(Exception ex){
            logger.error("Exception occurred in searchProduct method "+ex);
            captureScreenshots.captureScreenShots("SearchProduct_Failure");
        }
        return result;
    }
}
