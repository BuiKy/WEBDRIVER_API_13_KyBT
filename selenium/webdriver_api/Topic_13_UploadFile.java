package webdriver_api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;

import java.util.List;
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
		System.setProperty("webdriver.gecko.driver", ".\\libraries\\geckodriver.exe");
		driver = new FirefoxDriver();
		
//		System.setProperty("webdriver.chrome.driver", ".\\libraries\\chromedriver.exe");
//		driver = new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Sendkeys() throws Exception {
		driver.get("http://blueimp.github.com/jQuery-File-Upload/");
		// sử dụng phương thức sendkeys để upload nhiều file cùng lúc chạy cho cả hai trình duyệt Chrome và FireFox
		WebElement inputFile = driver.findElement(By.xpath("//input[@type='file']"));
		String filePath ="D:\\AutomationTest-OnlineSelenium\\02. WebDriver API\\UploadFile\\filegif.gif";
		
		String filePath1 = "D:\\AutomationTest-OnlineSelenium\\02. WebDriver API\\UploadFile\\kkk.jpg";
		
		String filePath2 = "D:\\AutomationTest-OnlineSelenium\\02. WebDriver API\\UploadFile\\kybuiimg.jpg";
		System.out.println(filePath);
		
		//send a file one time
//		inputFile.sendKeys(filePath);
		
		//send multiple file one time
		inputFile.sendKeys(filePath + "\n" +filePath1 +"\n"+filePath2);

		Thread.sleep(5000);

		// Click Start button để upload cho cả 3 file
		List<WebElement> startButtons = driver.findElements(By.xpath("//table//button[@class='btn btn-primary start']"));
		for (WebElement start : startButtons) {
			start.click();
		}
//		driver.findElement(By.xpath("//table//button[@class='btn btn-primary start']")).click();
		Thread.sleep(5000);

		// Kểm tra file đã được upload thành công
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']//a[text()='filegif.gif']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']//a[text()='kkk.jpg']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']//a[text()='kybuiimg.jpg']")).isDisplayed());

		// Sau khi updload thành công verify cả 3 file đã được upload
	}

	@AfterTest
	public void afterTest() {
		// check commit
		driver.quit();
	}

}
