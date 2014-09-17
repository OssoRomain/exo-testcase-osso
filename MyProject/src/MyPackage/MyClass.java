package MyPackage;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;
@Test
public class MyClass {
	public static void main(String[] args) {
        // declaration and instantiation of objects/variables
		System.setProperty("webdriver.chrome.driver", "/home/rosso/Desktop/chromedriver");
		//WebDriver driver = new ChromeDriver();
		
		WebDriver driver = new FirefoxDriver();
        String baseUrl = "http://localhost:8080/portal/"; 
        Log lo = new Log(driver,baseUrl);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        calendar_test calendar = new calendar_test(driver);
        lo.leLog("root","gtn");       
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        calendar.calendar_test_delete_event_category("undeux");
        calendar.add_new_category_with_valid_value("Jean Jean");
        calendar.edit_category_with_valid_value("Pring meeting","Sprint meeting"); 
        calendar.view_a_specific_event_in_List_view("Mon event");
        calendar.view_a_specific_event_in_pop_up("Mon event");
        //close Firefox
        driver.close();
        System.exit(0);
    }
 
	
	
}
