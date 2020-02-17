package testng_framework;

import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Part_5_Loop {
	WebDriver driver;
	By emailTextBox = By.xpath("//input[@id='email']");
	By passwordTextBox = By.xpath("//input[@id='pass']");
	By loginButton = By.xpath("//button[@id='send2']");	
	
	@BeforeClass
	  public void beforeClass() {
		
		System.setProperty("webdriver.chrome.driver", ".\\libraries\\chromedriver.exe");
		driver = new ChromeDriver();	
	
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	  }

	  @AfterClass()
	  public void afterClass() {
		  driver.quit();
	  }

	  @Test(dataProvider = "user_pass", invocationCount = 2 )
	  public void TC_01_LogintoSystem(String userName, String password) throws InterruptedException {
		  driver.get("http://live.demoguru99.com/index.php/customer/account/login/");
		  driver.findElement(emailTextBox).sendKeys(userName);
		  driver.findElement(passwordTextBox).sendKeys(password);
		  driver.findElement(loginButton).click();
		  
		  Thread.sleep(5000);
		  Assert.assertTrue(driver.findElement(By.xpath("//div[@class='col-1']//p")).getText().contains(userName));
		  driver.findElement(By.xpath("//header[@id='header']//span[text()='Account']")).click();
		  driver.findElement(By.xpath("//a[text()='Log Out']")).click();
		  driver.get("http://live.demoguru99.com/index.php/customer/account/login/");
	  }


	  @DataProvider(name="user_pass")
	  public Object[][] UserAndPassworData(){
		  return new Object[][] {
			  {"selenium_11_01@gmail.com","111111"},
			  {"selenium_11_02@gmail.com","111111"},
			  {"selenium_11_03@gmail.com","111111"}};
		  }
}
