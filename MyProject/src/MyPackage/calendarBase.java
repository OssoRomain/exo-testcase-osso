package MyPackage;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.*;

public class calendarBase {

	protected WebDriver driver;
	
	public calendarBase(){
		
	}
	
	public void goToCalendarPage(){
		driver.findElement(By.xpath("//*[@data-original-title='Calendar']")).click();
	}
	
	public void addCategorie(String nameCategorie){
		driver.findElement(By.className("uiIconCalSimplePlus")).click();
		driver.findElement(By.xpath("//*[contains(@id,'tmpMenuElement')]//a[contains(.,'Add Event Category')]")).click();
		driver.findElement(By.id("eventCategoryName")).sendKeys(nameCategorie);
		
	}
	
}
