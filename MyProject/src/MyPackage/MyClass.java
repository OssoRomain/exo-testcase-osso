package MyPackage;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;
@Test
public class MyClass {
	public static void main(String[] args) {
        // declaration and instantiation of objects/variables
        WebDriver driver = new FirefoxDriver();
        String baseUrl = "http://localhost:8080/portal/"; 
        Log lo = new Log(driver,baseUrl);
        calendar_test calendar = new calendar_test(driver);
        
        
        lo.leLog("root","gtn");
        
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        //driver.findElement(By.xpath("//*[@data-original-title='Calendar']")).click();
        calendar.calendar_test_delete_event_category();
        //close Firefox
       // driver.close();
        System.exit(0);
    }
 
	
	
}
