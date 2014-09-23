package MyPackage;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
/*
 * All the test cases about the calendar section 
 * 
 */
import org.apache.log4j.PatternLayout;


public class calendar_test extends calendarBase {
	static
	{
	    Logger rootLogger = Logger.getRootLogger();
	    rootLogger.setLevel(Level.INFO);
	    rootLogger.addAppender(new ConsoleAppender(
	               new PatternLayout("%-6r [%p] %c - %m%n")));
	}

	public calendar_test(WebDriver driver){
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public calendar_test(){
		super(driver);
		// TODO Auto-generated constructor stub
	}

	static WebDriver driver;
	calendarBase leCal;
	
//	private static Logger logger = Logger.getLogger(calendar_test.class);
	private static Logger log = Logger.getLogger(Log4j.class.getName());
	
	@BeforeTest
	public void initialize(){
		driver = new FirefoxDriver();
		String baseUrl = "http://localhost:8080/portal/"; 
		MyLog lo = new MyLog(driver,baseUrl);
		lo.leLog("root","gtn");
		System.out.print("before test");
		leCal= new calendarBase(driver);
		log.info("Connexion ok");
		goToCalendarPage();
	}

	/*	@AfterTest
			public void toFinish(){
			    driver.close();
			    System.exit(0);
			}

	 */
	
	/*
	 * Test id : 99791
	 * Title : Delete event category
	 * Step 1 : Add new category and create an event with this category
	 * Step 2 : Delete this category
	 */
	@Test
	(priority = 1)
	
	public void calendar_test_delete_event_category(){
		String nameCategorie="Test 99791";
		String nameEvent =" Event 99791";
		/* Step 1 : Create the category */
		goToCalendarPage();
		log.info("Go to calendar page");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		addCategorie(nameCategorie);
		log.info("Add categorie : "+nameCategorie);
		// Create the events with the new category
		addEvents(nameEvent,nameCategorie);
		log.info("Add Event : "+nameEvent);
		driver.navigate().refresh();
		/* Step 2 : Delete this category */
		deleteCategorie(nameCategorie);
		log.info("Delete category : "+nameCategorie);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.navigate().refresh();
		// check if the categories is delete 
		Assert.assertFalse(driver.findElement(By.xpath(ELEMENT_FIND_CATEGORIE_EXISTANT.replace("${name}",""+nameCategorie))).isDisplayed());
		// Check if the event category is All 
		driver.findElement(ELEMENT_CALENDAR_LIST_VIEW).click();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		Assert.assertTrue(driver.findElement(By.xpath(ELEMENT_FIND_EVENT_LIST_BY_NAME.replace("${name}",""+nameCategorie))).isDisplayed());
		driver.findElement(By.xpath(ELEMENT_FIND_EVENT_LIST_BY_NAME.replace("${name}",""+nameCategorie))).click();
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		driver.findElement(ELEMENT_BUTTON_EDIT_EVENT_IN_LISTVIEW).click();
		Assert.assertTrue(driver.findElement(By.xpath(ELEMENT_CHECK_CATEGORY_EXIST_IN_EVENT.replace("${name}","All"))).isDisplayed(), "test fail");
		driver.findElement(ELEMENT_LISTVIEW_CANCEL_BUTTON).click();
		driver.findElement(ELEMENT_CALENDAR_WEEK_VIEW).click();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		//Clear data
		deleteEvents(nameCategorie);
		log.info("delete event : "+nameEvent);
	}


	/*
	 * Test id : 99888
	 * Title : Add new category with valid value
	 * Step 1 : Show the add category form
	 * Step 2 : Add a category with the form
	 */
	@Test
	(priority = 2)
	public void add_new_category_with_valid_value(){
		String nameCategorie="Categorie99888";
		goToCalendarPage();
		log.info("Go to calendar page");
		/* Step 1 & 2 : Add the category in the form */
		addCategorie(nameCategorie);
		log.info("Add category : "+nameCategorie);
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
		log.info("Delete category : "+nameCategorie);
	}


	/*
	 * Id : 99889
	 * Title : edit category with valid value
	 * Step 1 : Create a category
	 * Step 2 : Show the add category form
	 * Step 3 : Edit this category into change the name 
	 */
	@Test
	(priority = 3)
	public void edit_category_with_valid_value(){
		String nameCategorie="categorie99889";
		String newName="Newcategorie99889";
		goToCalendarPage();
		log.info("Go to calendar page");
		/* Step 1 : Add a category */
		addCategorie(nameCategorie);
		log.info("add category : "+nameCategorie);
		driver.navigate().refresh();
		/* Step 2 & 3 : Change the name of this category */
		editCategoryName(nameCategorie,newName);
		log.info("Edit category : "+nameCategorie+" by the name "+newName);
		driver.navigate().refresh();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		// Check if the category has change it name
		Assert.assertTrue(driver.findElement(By.xpath(ELEMENT_CALENDAR_DROPDOWM_LIST_CATEGORIE.replace("${name}",newName))).isDisplayed(), "test fail");
		// Clear data
		deleteCategorie(newName);
		log.info("Delete category : "+newName);
	}

	/*
	 * Id : 99222
	 * Title : View a specific event in list view
	 * Step 1 : Add event
	 * Step 2 : View event from right click menu
	 * Step 3 : View event by selecting from list
	 */
	@Test
	(priority = 4)
	public void view_a_specific_event_in_List_view(){
		String eventName="event99222";
		goToCalendarPage();
		log.info("Go to calendar page");
		/* Step 1 : Add the event */
		addEvents(eventName,"All");
		log.info("Add event : "+eventName);
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
		// Clear data 
		driver.findElement(ELEMENT_CALENDAR_WEEK_VIEW).click();
		deleteEvents(eventName);
		log.info("Delete event : "+eventName);
	}

	/*
	 * Id :99923
	 * Title : View a specific event in pop up
	 * Step 1 : Add event
	 * Step 2 : View content of the event
	 */
	@Test
	(priority = 5)
	public void view_a_specific_event_in_pop_up(){
		String eventName="event99923";
		goToCalendarPage();
		log.info("Go to calendar page");
		/* Step 1 : Add the event */
		addEvents(eventName,"Meeting");
		log.info("Add event : "+eventName);
		// click on the Day/Month and week view
		driver.findElement(ELEMENT_CALENDAR_DAY_VIEW).click();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.findElement(ELEMENT_CALENDAR_MONTH_VIEW).click();
		driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
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
		// Clear data
		deleteEvents(eventName);
		log.info("Delete event : "+eventName);
	}
}
