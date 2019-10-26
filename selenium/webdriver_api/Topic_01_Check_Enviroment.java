package webdriver_api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class Topic_01_Check_Enviroment {
	WebDriver driver;
	
	@BeforeTest
	  public void beforeTest() {
		driver = new FirefoxDriver();
		
		// Chờ cho element được hiển thị trước khi tương tác trong vòng 30s
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		// Phóng to trình duyệt
		driver.manage().window().maximize();
		
		// Mở ra 1 trang web (AUT: Application Under Test)
		driver.get("http://demo.guru99.com/v4/");
	  }
	
  @Test
  public void TC_01_ValidateCurrentUrl() {
	// Lấy ra Url của page hiện tại và gán nó vào cái biến loginPageUrl
			String loginPageUrl = driver.getCurrentUrl();
			
			// In ra console cho người dùng (Coder) thấy được kết quả chạy test
			System.out.println(loginPageUrl);
			
			// Các hàm để verify dữ liệu của TestNG (true/ false/ equals)
			Assert.assertEquals(loginPageUrl, "http://demo.guru99.com/v4/");
  }
  
  @Test
  public void TC_02_ValidateCurrentUrl() {
	/// Lấy ra title của page hiện tại và gán nó vào biến loginPageTitle
		String loginPageTitle = driver.getTitle();
		
		// Verify dữ liệu của biến loginPageTitle này bằng vs giá trị mình mong muốn
		Assert.assertEquals(loginPageTitle, "Guru99 Bank Home Page");
  }
  @Test
	public void TC_03_LoginFormDisplayed() {
		// Verify login form được hiển thị ở trang Login
		Assert.assertTrue(driver.findElement(By.xpath("//form[@name='frmLogin']")).isDisplayed());
	}
  @AfterTest
  public void afterTest() {
	  driver.quit();
  }

}
