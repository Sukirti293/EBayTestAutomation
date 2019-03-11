package Utilities;


import org.apache.log4j.Logger;
import java.io.FileReader;
import java.util.Properties;

public class Resources {
	 static Logger logger = Logger.getLogger(Resources.class.getName());
	 static String value = null;
    

    /**
     * getCellData method is used for fetching data from excel sheet dynamically
     *
     *
     * @param rownum
     * @param col
     * @param fileName
     * @return string value of data mentioned in the Excel sheet
     */


	 
	 public String getPropValues(String filedName){
        try {
        	
        	FileReader reader=new FileReader("loginDetails.properties");  
        	
        	Properties prop = new Properties();
            prop.load(reader);
            
            String EmailID = prop.getProperty("EmailID");
            String Password = prop.getProperty("Password");
            String searchItem = prop.getProperty("searchItem");
            
            if(filedName.equalsIgnoreCase("EmailID")) {
            	value = EmailID;
            	
            }else if(filedName.equalsIgnoreCase("Password")) {
            	value = Password;
            	
            }else if(filedName.equalsIgnoreCase("searchItem")) {
            	value = searchItem;
            	
            }

        } catch (Exception e) {
            logger.error("Exception Occured while fetching data from excel sheet"+e);
            return "Exception Occured";
        }
		return value;

    }
	 
	 

}
