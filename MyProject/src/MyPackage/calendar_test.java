package MyPackage;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import com.thoughtworks.selenium.Wait;
import com.thoughtworks.selenium.webdriven.commands.Open;
import com.thoughtworks.selenium.webdriven.commands.WaitForCondition;

/*
 * All the test cases about the calendar section 
 * 
 */

public class calendar_test extends calendarBase {

	public calendar_test(WebDriver driver){
		this.driver=driver;

	}
			/*
			 * Test id : 99791
			 * Title : Delete event category
			 * Step 1 : Add new category and create an event with this category
			 * Step 2 : Delete this category
			 */
			public void calendar_test_delete_event_category(String nameCategorie){
			/* Step 1 : Create the category */
				goToCalendarPage();
				driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
				addCategorie(nameCategorie);
				// Create the events with the new category
				addEvents(nameCategorie,nameCategorie);
				driver.navigate().refresh();
			/* Step 2 : Delete this cateory */
				deleteCategorie(nameCategorie);
				driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
				driver.navigate().refresh();
				
				// check if the categories is delete 
				Assert.assertFalse(driver.findElement(By.xpath(ELEMENT_FIND_CATEGORIE_EXISTANT.replace("${name}",""+nameCategorie))).isDisplayed(), "test fail");
				
				// Check if the event category is All 
				driver.findElement(ELEMENT_CALENDAR_LIST_VIEW).click();
				driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
				driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
				Assert.assertTrue(driver.findElement(By.xpath(ELEMENT_FIND_EVENT_LIST_BY_NAME.replace("${name}",""+nameCategorie))).isDisplayed(), "test fail");
				driver.findElement(By.xpath(ELEMENT_FIND_EVENT_LIST_BY_NAME.replace("${name}",""+nameCategorie))).click();
				driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
				driver.findElement(ELEMENT_BUTTON_EDIT_EVENT_IN_LISTVIEW).click();
				Assert.assertTrue(driver.findElement(By.xpath(ELEMENT_CHECK_CATEGORY_EXIST_IN_EVENT.replace("${name}","All"))).isDisplayed(), "test fail");
				driver.findElement(ELEMENT_LISTVIEW_CANCEL_BUTTON).click();
				driver.findElement(ELEMENT_CALENDAR_WEEK_VIEW).click();
				driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
			}
		
			
			/*
			 * Test id : 99888
			 * Title : Add new category with valid value
			 * Step 1 : Show the add category form
			 * Step 2 : Add a category with the form
			 */
			
			public void add_new_category_with_valid_value(String nameCategorie){
				goToCalendarPage();
			/* Step 1 & 2 : Add the category in the form */
				addCategorie(nameCategorie);
				driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
				
			// check if the categories is displayed in the list on the right top.
				Assert.assertTrue(driver.findElement(By.xpath(ELEMENT_CALENDAR_DROPDOWM_LIST_CATEGORIE.replace("${name}",nameCategorie))).isDisplayed(), "test fail");
			// check if the category is add in the event category form
				driver.findElement(ELEMENT_CALENDAR_BUTTONPLUS).click();
				driver.findElement(ELEMENT_CALENDAR_BUTTONPLUS_ADD_EVENT_CATEGORIES).click();				
				Assert.assertTrue(driver.findElement(By.xpath(ELEMENT_FIND_CATEGORIE_EXISTANT.replace("${name}",""+nameCategorie))).isDisplayed(), "test fail");
				driver.findElement(ELEMENT_CALENDAR_CLOSE_EVENT_CATEGORIE).click();	
			// Clean the data
				deleteCategorie(nameCategorie);
			}
		
			
			/*
			 * Id : 99889
			 * Title : edit category with valid value
			 * Step 1 : Create a category
			 * Step 2 : Show the add category form
			 * Step 3 : Edit this category into change the name 
			 */
			
			public void edit_category_with_valid_value(String nameCategorie,String newName){
				goToCalendarPage();
			/* Step 1 : Add a category */
				addCategorie(nameCategorie);
				driver.navigate().refresh();
			/* Step 2 & 3 : Change the name of this category */
				editCategoryName(nameCategorie,newName);
				driver.navigate().refresh();
				driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
			// Check if the category has change it name
				Assert.assertTrue(driver.findElement(By.xpath(ELEMENT_CALENDAR_DROPDOWM_LIST_CATEGORIE.replace("${name}",newName))).isDisplayed(), "test fail");
			// Clear data
				deleteCategorie(newName);
			}
			
			/*
			 * Id : 99222
			 * Title : View a specific event in list view
			 * Step 1 : Add event
			 * Step 2 : View event from right click menu
			 * Step 3 : View event by selecting from list
			 */
			
			public void view_a_specific_event_in_List_view(String eventName){
				goToCalendarPage();
			/* Step 1 : Add the event */
				addEvents(eventName,"All");
				WebElement elementToRightClick = driver.findElement(By.xpath(ELEMENT_CALENDAR_TASK_OR_EVENT.replace("${name}", eventName)));
			/* Step 2 : View event from right click menu */
				Actions action = new Actions(driver);
				action.contextClick(elementToRightClick);
				action.perform();
				driver.findElement(ELEMENT_EDIT_RIGHTCLIKC_EVENT_INCALENDAR).click();
			// Check the event which is click is the good one 
				Assert.assertTrue(driver.findElement(By.xpath(ELEMENT_CHECK_EVENT_NAME_CALENDAR.replace("${name}",eventName))).isDisplayed(), "test fail");
				driver.findElement(ELEMENT_CLOSE_EVENT_PREVIEW).click();
				driver.navigate().refresh();
			/* Step 3 : View the event in list view */
				driver.findElement(ELEMENT_CALENDAR_LIST_VIEW).click();
				driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
				driver.findElement(By.xpath(ELEMENT_FIND_EVENT_LIST_BY_NAME.replace("${name}",""+eventName))).click();
			// Check the event which is click is the good one 
				Assert.assertTrue(driver.findElement(By.xpath(ELEMENT_CHECK_TITLE_EVENT_IN_LIST.replace("${name}",eventName))).isDisplayed(), "test fail");	
			}
		
			/*
			 * Id :99923
			 * Title : View a specific event in pop up
			 * Step 1 : Add event
			 * Step 2 : View content of the event
			 */
			
			public void view_a_specific_event_in_pop_up(String eventName){
				goToCalendarPage();
			/* Step 1 : Add the event */
				addEvents(eventName,"Meeting");
			// click on the Day/Month and week view
				driver.findElement(ELEMENT_CALENDAR_DAY_VIEW).click();
				driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
				driver.findElement(ELEMENT_CALENDAR_MONTH_VIEW).click();
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				driver.findElement(ELEMENT_CALENDAR_WEEK_VIEW).click();
				driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
			/* Step 2 : View the content of the event */
			// Check it by the pop up event 
				WebElement elementToRightClick = driver.findElement(By.xpath(ELEMENT_CALENDAR_TASK_OR_EVENT.replace("${name}", eventName)));
				Actions action = new Actions(driver);
				action.contextClick(elementToRightClick);
				action.perform();
				driver.findElement(ELEMENT_EDIT_RIGHTCLIKC_EVENT_INCALENDAR).click();
				Assert.assertTrue(driver.findElement(By.xpath(ELEMENT_CHECK_EVENT_NAME_CALENDAR.replace("${name}",eventName))).isDisplayed(), "test fail");
				driver.findElement(ELEMENT_CLOSE_EVENT_PREVIEW).click();
			}
}
