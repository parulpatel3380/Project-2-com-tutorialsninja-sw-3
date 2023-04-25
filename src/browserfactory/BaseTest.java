package browserfactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class BaseTest {
    public static String baseUrl ="http://tutorialsninja.com/demo/index.php?";
    public static WebDriver driver;

    public static void openBrowser(String baseUrl){
        driver = new ChromeDriver();//launch the driver
        driver.get(baseUrl);//pass the baseUrl
        driver.manage().window().maximize();
        //Implicit TimeOut
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    }

    public void closeBrowser(){
        //close all selenium instance browser
        driver.quit();
    }



}
