package framework;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.io.File;


public class BaseTest {

    protected static Browser browser = Browser.getInstance();
    public static ResourcePropertiesManager resources;
    private static final String PROPERTIES_FILE = "config.properties";
    private static final String BASE_URL = "url";


    @BeforeTest
    public static void initializeWebdriver(){
        resources = new ResourcePropertiesManager(PROPERTIES_FILE);
        browser.getInstance();
        browser.navigate(resources.getProperty(BASE_URL));
    }





    @AfterTest
   public static void tearDown(){ browser.closeBrowser();
   }


}
