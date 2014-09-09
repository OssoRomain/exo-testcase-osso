package MyPackage;
import org.openqa.selenium.*;

public class calendar_test extends calendarBase {

	public calendar_test(WebDriver driver){
		this.driver=driver;
		
	}
	
	public void calendar_test_delete_event_category(){
		goToCalendarPage();
		addCategorie("Test1");
	}
	
}
