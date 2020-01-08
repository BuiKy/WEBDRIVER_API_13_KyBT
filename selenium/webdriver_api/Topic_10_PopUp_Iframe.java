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

public class Topic_10_PopUp_Iframe {
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
  public void TC_01_PopUp() throws InterruptedException {
	driver.get("https://kyna.vn/");
	Thread.sleep(5000);
	//check poup có hiển thị trên trang AUT
	List<WebElement> popupFancy = driver.findElements(By.xpath("//div[@class='fancybox-inner']"));
	System.out.println("Fancy popup number: "+popupFancy.size());
	if(popupFancy.size()>0) {
		Assert.assertTrue(popupFancy.get(0).isDisplayed());
		driver.findElement(By.cssSelector(".fancybox-close")).click();
	}

	//Switch vào iframe đó trước thì mới tương tác được với các element trong frame đó
	driver.switchTo().frame(driver.findElement(By.xpath("//div[@class='fanpage']//iframe")));
	
	boolean faceboolIframe = driver.findElement(By.cssSelector("#facebook")).isDisplayed();
	System.out.println("Is facebool iframe displaed: "+faceboolIframe);
	Assert.assertTrue(faceboolIframe);
	
	String numberLike = driver.findElement(By.xpath("//a[text()='Kyna.vn']/parent::div/following-sibling::div")).getText();
	Assert.assertEquals(numberLike, "170K likes");

	driver.switchTo().defaultContent();
	driver.findElement(By.xpath("//div[@class='container']//ul//a[@class='button-login']")).click();
	driver.findElement(By.xpath("//input[@id='user-login']")).sendKeys("automationfc.vn@gmail.com");
	driver.findElement(By.xpath("//input[@id='user-password']")).sendKeys("automationfc.vn@gmail.com");
	driver.findElement(By.xpath("//button[@id='btn-submit-login']")).click();
	
	Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Automation FC']")).isDisplayed());
	
	
  }
  

  @AfterTest
  public void afterTest() {
	  //check commit
	  driver.quit();
  }

}
