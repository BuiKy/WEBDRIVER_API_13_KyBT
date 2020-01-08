package webdriver_api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class Topic_13_UploadFile {
	WebDriver driver;

	// Get đường dẫn trong file selenium
	String projectPath = System.getProperty("user.dir");

	@BeforeTest
	public void beforeTest() {
		// Set driver for JE lib
		System.setProperty("webdriver.chrome.driver", ".\\libraries\\chromedriver.exe");

		driver = new ChromeDriver();

		// Chờ cho element được hiển thị trước khi tương tác trong vòng 30s
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		// Phóng to trình duyệt
		driver.manage().window().maximize();

	}

	@Test
	public void TC_01_Sendkeys() throws Exception {
		// Truy cập vào trang AUT
		driver.get("http://blueimp.github.com/jQuery-File-Upload/");

		// sử dụng phương thức sendkeys để upload nhiều file cùng lúc chạy cho cả hai
		// trình duyệt Chrome và FireFox
		WebElement inputFile = driver.findElement(By.xpath("//input[@type='file']"));
		String filePath = projectPath + "‪UploadFile\\filegif.gif";
		inputFile.sendKeys(filePath);

		Thread.sleep(5000);

		driver.findElement(By.xpath("//table//button[@class='btn btn-primary start']")).click();

		Thread.sleep(5000);

		// Kểm tra file đã được upload thành công
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']//a[text()='filegif.gif']")).isDisplayed());

		// Click Start button để upload cho cả 3 file
		// Sau khi updload thành công verify cả 3 file đã được upload
	}

	@AfterTest
	public void afterTest() {
		// check commit
		driver.quit();
	}

}
