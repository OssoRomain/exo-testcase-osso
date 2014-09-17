package MyPackage;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class calendarBase {
	/*
	 * All the method/function for the calendar test.
	 * 
	 */
	protected WebDriver driver;
	
	
	//   Calendar - Element
	public final By ELEMENT_GO_TO_CALENDAR_PAGE =By.xpath("//*[@data-original-title='Calendar']");
	public final By ELEMENT_CALENDAR_BUTTONPLUS = By.className("uiIconCalSimplePlus");
	public final String ELEMENT_CHECK_EVENT_NAME_CALENDAR="//*[@class='titleList']//*[text()='${name}']";
	public final By ELEMENT_CLOSE_EVENT_PREVIEW=By.xpath("//*[@id='UICalendarPopupWindow']//*[text()='Close']");
	public final String ELEMENT_CHECK_TITLE_EVENT_IN_LIST=".//*[text()='Event Details']/../..//*[@class='titleList']//*[text()='${name}']";
	public final By ELEMENT_LISTVIEW_CANCEL_BUTTON = By.xpath(".//*[@id='UIEventForm']//*[text()='Cancel']");
	public final By ELEMENT_EDIT_RIGHTCLIKC_EVENT_INCALENDAR=By.xpath("//*[@class='uiIconPreview uiIconLightGray']");
	
	public final By ELEMENT_CALENDAR_DAY_VIEW = By.xpath(".//*[@class='btn-group containerMoreItem']//*[text()='Day']");
	public final By ELEMENT_CALENDAR_WEEK_VIEW = By.xpath(".//*[@class='btn-group containerMoreItem']//*[text()='Week']");
	public final By ELEMENT_CALENDAR_MONTH_VIEW = By.xpath(".//*[@class='btn-group containerMoreItem']//*[text()='Month']");
	public final String ELEMENT_CALENDAR_TASK_OR_EVENT ="//*[@id='UIWeekViewGrid']//div[contains(text(),'${name}')]";
	//   Calendar - Categorie
	public final By ELEMENT_CALENDAR_BUTTONPLUS_ADD_EVENT_CATEGORIES =By.xpath("//*[contains(@id,'tmpMenuElement')]//a[contains(.,'Add Event Category')]");
	public final By ELEMENT_CALENDAR_ADD_CATEGORIE_NAME=By.id("btnEventCategoryFormContainer");
	public final By ELEMENT_CALENDAR_CLOSE_EVENT_CATEGORIE=By.xpath(".//*[@id='UICalendarPopupWindow']//*[text()='Close']");
	public final String ELEMENT_CATEGORIE_EXISTANT_DELETE="//*[@id='UIEventCategoryList']//*[text()='${name}']/../..//*[@data-original-title='Delete']";
	public final String ELEMENT_CATEGORIE_EXISTANT_EDIT="//*[@id='UIEventCategoryList']//*[text()='${name}']/../..//*[@data-original-title='Edit']";
	public final String ELEMENT_FIND_CATEGORIE_EXISTANT="//*[@id='UIEventCategoryList']//*[text()='${name}']";
	public final By ELEMENT_CATEGORIE_CONFIRM_DELETE=By.xpath("//*[@id='UIConfirmation']//*[text()=' Yes ']");
	public final By ELEMENT_CATEGORIE_CLOSE_POPUP_CONFIRMATION_DELETE_CATEGORIE=By.xpath("//*[@id='UICalendarPopupWindow']//a[@class='uiIconClose pull-right']");
	public final String ELEMENT_CALENDAR_DROPDOWM_LIST_CATEGORIE="//*[@class='selectbox' and @name='eventCategories']//*[text()='${name}']";
	public final By ELEMENT_FIND_CONFIRMATION_MESSAGE_DELETE_CATEGORIE=By.className("confirmationIcon");
	
	//   Calendar - Field
	public final By FIELD_CALENDAR_NAME_NEW_CATEGORIE = By.id("eventCategoryName");
	
	//Event - Element
	public final By ELEMENT_EVENT_OPEN_POP_UP=By.id("UIActionBarQuickAddEvent");
	public final By ELEMENT_EVENT_CATEGORIE_DROPLIST=By.xpath(".//*[@id='UIQuickAddEvent']//*[@class='selectbox' and @name='category' ]");
	public final By ELEMENT_EVENT_SAVE_POPUP=By.xpath("//*[@id='UIQuickAddEventPopupWindow']//*[text()='Save']");
	public final String ELEMENT_FIND_EVENT_LIST_BY_NAME="//*[@id='UIListUsers']//span[contains(text(),'${name}')]";
	
	//Event - Field
	public final By FIELD_ADD_EVENT_TITLE=By.id("eventName");
	public final By FIELD_EVENT_CATEGORY_DROPLIST_SELECT=By.xpath("//*[@id='UIQuickAddEvent']//*[@class='selectbox' and @name='category']");
	//Task - Element
	public final By ELEMENT_TASK_OPEN_POPUP=By.id("UIActionBarQuickAddTask");
	public final By ELEMENT_TASK_CATEGORIE_DROPLIST=By.xpath(".//*[@id='UIQuickAddTask']//*[@class='selectbox' and @name='category']");
	public final By ELEMENT_TASK_SAVE_POPUP=By.xpath("//*[@id='UIQuickAddTaskPopupWindow']//*[text()='Save']");
	//Task - Field
	public final By FIELD_ADD_TASK_TITTLE=By.xpath(".//*[@id='UIQuickAddTask']//*[@id='eventName']");
	public final By FIELD_TASK_CATEGORY_DROPLIST_SELECT=By.xpath("//*[@id='UIQuickAddTask']//*[@class='selectbox' and @name='category']");
	//list view
	public final By ELEMENT_CALENDAR_LIST_VIEW = By.xpath(".//*[@class='btn-group containerMoreItem']//*[text()='List']");
	public final By ELEMENT_BUTTON_EDIT_EVENT_IN_LISTVIEW=By.xpath(".//*[@class='uiActionContainer']//i[@class='uiIconEdit uiIconLightGray']");
	public final String ELEMENT_CHECK_CATEGORY_EXIST_IN_EVENT=".//*[@id='UICalendarPopupWindow']//*[@class='control-group']//*[@class='selectbox' and @name='category']//*[text()='${name}']";
	
	public calendarBase(){
		
	}
	
	public void goToCalendarPage(){
		driver.findElement(ELEMENT_GO_TO_CALENDAR_PAGE).click();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}
	
	public void addCategorie(String nameCategorie){
		// Add a categorie in the calendar 
		driver.findElement(ELEMENT_CALENDAR_BUTTONPLUS).click();
		driver.findElement(ELEMENT_CALENDAR_BUTTONPLUS_ADD_EVENT_CATEGORIES).click();
		driver.findElement(FIELD_CALENDAR_NAME_NEW_CATEGORIE).sendKeys(nameCategorie);	
		driver.findElement(ELEMENT_CALENDAR_ADD_CATEGORIE_NAME).click();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.findElement(ELEMENT_CALENDAR_CLOSE_EVENT_CATEGORIE).click();	
		driver.navigate().refresh();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		System.out.println("Category added with sucess");
	}
	
	public void addEvents(String eventName,String nameCategorie){
		driver.findElement(ELEMENT_EVENT_OPEN_POP_UP).click();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.findElement(FIELD_ADD_EVENT_TITLE).sendKeys(eventName);
		driver.findElement(ELEMENT_EVENT_CATEGORIE_DROPLIST).click();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);		
		driver.findElement(FIELD_EVENT_CATEGORY_DROPLIST_SELECT).sendKeys(nameCategorie);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	    driver.findElement(ELEMENT_EVENT_SAVE_POPUP).click();
	    driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);		
	}
	
	public void addTasks(String taskName, String nameCategorie){
		driver.findElement(ELEMENT_TASK_OPEN_POPUP).click();
		driver.findElement(FIELD_ADD_TASK_TITTLE).sendKeys(taskName);
		driver.findElement(ELEMENT_TASK_CATEGORIE_DROPLIST).click();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);		
		driver.findElement(FIELD_TASK_CATEGORY_DROPLIST_SELECT).sendKeys(nameCategorie);
	    driver.findElement(ELEMENT_TASK_SAVE_POPUP).click();
	}
	public void deleteCategorie(String nameCategorie){
		driver.findElement(ELEMENT_CALENDAR_BUTTONPLUS).click();
		driver.findElement(ELEMENT_CALENDAR_BUTTONPLUS_ADD_EVENT_CATEGORIES).click();
		driver.findElement(By.xpath(ELEMENT_CATEGORIE_EXISTANT_DELETE.replace("${name}",nameCategorie ))).click();
		Assert.assertTrue(driver.findElement(ELEMENT_FIND_CONFIRMATION_MESSAGE_DELETE_CATEGORIE).isDisplayed(), "test fail");
		driver.findElement(ELEMENT_CATEGORIE_CONFIRM_DELETE).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(ELEMENT_CATEGORIE_CLOSE_POPUP_CONFIRMATION_DELETE_CATEGORIE).click();
	}
	public void editCategoryName(String oldName,String newName){
		driver.findElement(ELEMENT_CALENDAR_BUTTONPLUS).click();
		driver.findElement(ELEMENT_CALENDAR_BUTTONPLUS_ADD_EVENT_CATEGORIES).click();
		driver.findElement(By.xpath(ELEMENT_CATEGORIE_EXISTANT_EDIT.replace("${name}", oldName))).click();
		driver.findElement(FIELD_CALENDAR_NAME_NEW_CATEGORIE).clear();
		driver.findElement(FIELD_CALENDAR_NAME_NEW_CATEGORIE).sendKeys(newName);
		driver.findElement(ELEMENT_CALENDAR_ADD_CATEGORIE_NAME).click();
		driver.findElement(ELEMENT_CALENDAR_CLOSE_EVENT_CATEGORIE).click();
	}
}
