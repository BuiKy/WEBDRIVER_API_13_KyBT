package webdriver_api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class Topic_02_Locator {
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
  public void TC_01_Locator() {
		
		  driver.get("http://live.demoguru99.com/index.php/customer/account/login/");
		
		  //ID
		  driver.findElement(By.id("email")).sendKeys("kingandqueen123.qn@gmail.com");
		  driver.findElement(By.id("pass")).sendKeys("123456");
		  
		  //Name driver.findElement(By.name("send")).click();
		  
		  //CLASS (Newsletter)
		  driver.findElement(By.className("validate-email")).click();
		 
	  
		  //TagName (tìm xem có bao nhiêu đường link và in ra giá trị)
		  //Đếm xem có bao nhiêu element trên page
		  List<WebElement> links = driver.findElements(By.tagName("a"));
		  int linkNumber = links.size();
		  System.out.println("Tong so link tren page: " +linkNumber);
		  for(WebElement link : links) {
			  System.out.println("Value = " +link.getAttribute("href"));
		  }
		
		//LinkText 
		driver.findElement(By.linkText("ORDERS AND RETURNS")).click();
		  
		//Partial LinkText driver.findElement(By.linkText("ORDER AND")).click();
		driver.findElement(By.partialLinkText("AND RETURNS")).click();
		 
		//CSS css[attribute-value]
		//driver.findElement(By.cssSelector("#oar_order_id")).sendKeys("buithiky");
		driver.findElement(By.cssSelector(".validate-email")).sendKeys("ngu nhu heo");
		System.out.println("The a dung css="+driver.findElements(By.cssSelector("a")).size());
		
		//xpath[attribute-value]
		driver.findElement(By.linkText("MY ACCOUNT")).click();
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("donangu@gmail.com");
		driver.findElement(By.xpath("//a[text()='Advanced Search']"));
	  
  }
  
	
	/*
	 * @Test public void TC_02_ValidateCurrentUrl() {
	 * 
	 * }
	 * 
	 * @Test public void TC_03_LoginFormDisplayed() {
	 * 
	 * }
	 */
  @AfterTest
  public void afterTest() {
	  driver.quit();
	  
  }

}
