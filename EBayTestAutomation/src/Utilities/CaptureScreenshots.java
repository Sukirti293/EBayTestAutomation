package Utilities;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;

import static EBayDesiredCapabilities.DesiredCapabilitiesEbay.appiumDriver;

public class CaptureScreenshots {

    String folder_name = null;
    DateFormat dateFormat = null;
    Logger logger = Logger.getLogger(CaptureScreenshots.class.getName());


    /**
     * Methods captures the screenshots and stores in a folder named "screenshot"
     *
     * @return null
     * @param file_name
     */

    public void captureScreenShots(String file_name) {
        try {
            folder_name = "screenshots";
            dateFormat = DateFormat.getDateInstance();
            File f = ((TakesScreenshot) appiumDriver).getScreenshotAs(OutputType.FILE);
            //create dir with given folder name
            new File(folder_name).mkdir();
            //Setting file name
            file_name = file_name+" "+dateFormat.format(new Date()) + ".png";
            //coppy screenshot file into screenshot folder.
            FileUtils.copyFile(f, new File(folder_name + "/" + file_name));
        }catch (IOException exp){
            logger.error("Error Occurred in Taking screenshots"+exp);
        }
    }

}

