package webdriver_api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_04_Browers_II {
	WebDriver driver;
	// By urlPageLogin = By.xpath(xpathExpression)

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();

		// Chờ cho element được hiển thị trước khi tương tác trong vòng 30s
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		// Phóng to trình duyệt
		driver.manage().window().maximize();
	}

	@BeforeMethod(description = "chạy trước cho mỗi test bên dưới")
	public void BeforeMethod() {
		driver.get("http://live.demoguru99.com");

	}

	public void TC_01_Url() {
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		String urlPageLogin = driver.getCurrentUrl();
		// verify url
		Assert.assertEquals(urlPageLogin, "http://live.demoguru99.com/index.php/customer/account/login/");

		driver.findElement(By.xpath("//div[@class='col2-set']//span[contains(text(),'Create an Account')]")).click();
		String urlPageCreate = driver.getCurrentUrl();
		// verify url
		Assert.assertEquals(urlPageCreate, "http://live.demoguru99.com/index.php/customer/account/create/");

	}

	public void TC_02_Title() {
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		String titlePageLogin = driver.getTitle();
		// verify title
		Assert.assertEquals(titlePageLogin, "Customer Login");

		driver.findElement(By.xpath("//div[@class='col2-set']//span[contains(text(),'Create an Account')]")).click();
		String titlePageCreate = driver.getTitle();
		// verify title
		Assert.assertEquals(titlePageCreate, "Create New Customer Account");
	}

	public void TC_03_Navigation() {
		// go to create account page
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		driver.findElement(By.xpath("//div[@class='col2-set']//span[contains(text(),'Create an Account')]")).click();
		String urlPageCreate = driver.getCurrentUrl();
		// verify url
		Assert.assertEquals(urlPageCreate, "http://live.demoguru99.com/index.php/customer/account/create/");
		// Back to Login page
		driver.navigate().back();
		String urlPageLogin = driver.getCurrentUrl();
		// verify url
		Assert.assertEquals(urlPageLogin, "http://live.demoguru99.com/index.php/customer/account/login/");
		// Forward to create account page
		driver.navigate().forward();
		// verify title
		String titlePageCreate = driver.getTitle();
		Assert.assertEquals(titlePageCreate, "Create New Customer Account");
	}

	@Test
	public void TC_04_PageSource() {

		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		System.out.println(driver.getCurrentUrl());
		Assert.assertTrue(driver.getPageSource().contains("Login or Create an Account"));

		Assert.assertTrue(driver.getPageSource().contains("Login or Create an Account"));
		driver.findElement(By.xpath("//div[@class='col2-set']//span[contains(text(),'Create an Account')]")).click();
		Assert.assertTrue(driver.getPageSource().contains("Create an Account"));

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
