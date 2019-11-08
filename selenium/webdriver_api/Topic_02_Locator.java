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

		// ID
		driver.findElement(By.id("email")).sendKeys("kingandqueen123.qn@gmail.com");
		driver.findElement(By.id("pass")).sendKeys("123456");

		//Name 
		driver.findElement(By.name("send")).click();

		// CLASS (Newsletter)
		driver.findElement(By.className("validate-email")).clear();
		driver.findElement(By.className("validate-email")).click();

		// TagName (tìm xem có bao nhiêu đường link và in ra giá trị)
		// Đếm xem có bao nhiêu element trên page
		List<WebElement> links = driver.findElements(By.tagName("a"));
		int linkNumber = links.size();
		System.out.println("Tong so link tren page: " + linkNumber);
		for (WebElement link : links) {
			System.out.println("Value = " + link.getAttribute("href"));
		}

		// LinkText
		driver.findElement(By.linkText("ORDERS AND RETURNS")).click();

		// Partial LinkText driver.findElement(By.linkText("ORDER AND")).click();
		driver.findElement(By.partialLinkText("AND RETURNS")).click();

		// CSS css[attribute-value]
		driver.findElement(By.cssSelector("#oar_order_id")).sendKeys("123456");
		driver.findElement(By.cssSelector(".input-text.required-entry")).sendKeys("css_class@gmail.com");
		driver.findElement(By.cssSelector("input[name='oar_email']")).sendKeys("css_name@gmail.com");
		
		System.out.println("The a dung css=" + driver.findElements(By.cssSelector("a")).size());
		driver.findElement(By.cssSelector("a[href='http://live.demoguru99.com/index.php/sales/guest/form/']")).click();

		// xpath[attribute-value]
		driver.findElement(By.linkText("MY ACCOUNT")).click();
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("xpath_id@gmail.com");
		driver.findElement(By.xpath("//input[@class='input-text required-entry validate-password']")).sendKeys("123class");
		driver.findElement(By.xpath("//button[@name='send']")).click();
		System.out.println("The a dung css=" + driver.findElements(By.xpath("//a")).size());
		driver.findElement(By.xpath("//a[text()='Advanced Search']")).click();

	}
	@Test
	public void TC_02_Dupplicate() throws InterruptedException	 {
		
		driver.findElement(By.xpath("//input")).sendKeys("ngu nhu heo vai mai");
		Thread.sleep(5000);
	}

	@AfterTest
	public void afterTest() {
		driver.quit();

	}

}
