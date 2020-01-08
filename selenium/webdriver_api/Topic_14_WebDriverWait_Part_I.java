package webdriver_api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class Topic_14_WebDriverWait_Part_I {
	WebDriver driver;
	
	@BeforeTest
	  public void beforeTest() {
		driver = new FirefoxDriver();
		
		// Chờ cho element được hiển thị trước khi tương tác trong vòng 30s
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		// Phóng to trình duyệt
		driver.manage().window().maximize();
		
	  }
	
  @Test
  public void TC_01_StaticWait() {
	  driver.get("");
	  System.out.println("Start sleep: "+getcurrentTime());
	
  }
  public String getcurrentTime() {
	  return "";
  }
  
  @Test
  public void TC_02_ImplicitWait() {
	  driver.get("");
	  //1 mở app
	  //2 chờ cho element start button để thao tác được
	  //3 click vào start button
	  //4 hiển thị inprogress bar (loading bar)
	  // Để loading bày biến mất thì phải mất 5s
	  //5 Hello word text được hiển thị
	  //div[@id='start']\\button
	  //div[@id='loading']\\button
	  //div[@id='finish']\\button
	  
	  //Check cho element được hiển thị
	  WebElement startButton = driver.findElement(By.xpath(""));
	  Assert.assertTrue(true);
	  
	  //Click vào button
	  
	  //Loading icon được hiển thị mất tới 5s để biến mất
	  
	  //Check cho helloword text được hiển thị
	  WebElement helloWordTxt = driver.findElement(By.xpath(""));
	  Assert.assertTrue(true);
  }
  @Test
	public void TC_03_LoginFormDisplayed() {
		// Verify login form được hiển thị ở trang Login
		Assert.assertFalse(driver.findElement(By.xpath("//form[@name='frmLogin']")).isDisplayed());
	}
  @AfterTest
  public void afterTest() {
	  //check commit
	  driver.quit();
  }

}
