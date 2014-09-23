package MyPackage;
import org.openqa.selenium.*;

public class MyLog {
	
	private String Url = null;
	private String pass = null;
	private String user = null;
	private WebDriver driver= null;
	
	public MyLog (WebDriver driver,String Url){
		this.Url = Url;
		this.driver = driver;
		driver.get(Url);
		driver.manage().window().maximize();
	}
	
	public void leLog(String user, String pass){

	    WebElement tagName = driver.findElement(By.id("username"));
	    tagName.sendKeys(user);
	    
	    WebElement tagName2 = driver.findElement(By.name("password"));
	    tagName2.sendKeys(pass);
	    
	    driver.findElement(By.xpath("//*[@id='UIPortalLoginFormAction']/*[text()='Sign In']")).click();
	}

	
	
}