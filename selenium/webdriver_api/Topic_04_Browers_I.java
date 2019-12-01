package webdriver_api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.logging.LogType;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_04_Browers_I {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();

		// Chờ cho element được hiển thị trước khi tương tác trong vòng 30s
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		// Phóng to trình duyệt
		driver.manage().window().maximize();
	}

	@Test
	public void f() {
		// Mở cái AUT (Application Under Test) -> required: http:// hoặc https://
				driver.get("http://live.demoguru99.com/index.php"); // (**)

				// Đóng browser -> Handle Windows/ Tabs
				// driver.close();

				// Đóng browser
				// driver.quit(); // (**)

				// Trả về Url của page hiện tại
				String homePageUrl = driver.getCurrentUrl(); // (*)
				System.out.println(homePageUrl);

				Assert.assertEquals(homePageUrl, "http://live.demoguru99.com/index.php");

				// Trả về title của page hiện tại
				Assert.assertEquals(driver.getTitle(), "Home page");  // (*)

				// Trả về source code của page hiện tại
				Assert.assertTrue(driver.getPageSource().contains("2015 Magento Demo Store. All Rights Reserved."));

				// Trả về cái Windows GUID (Handle Windows) của windows/ tab hiện tại
				String homePageTabID = driver.getWindowHandle(); // (**)
				System.out.println("Windows ID = " + homePageTabID);

				// Trả về Windows GUID của all tab/ windows đang có
				// Set<String> allWindowsID = driver.getWindowHandles(); // (**)
				//
				// for (int i = 0; i <= allWindowsID.size(); i++) {
				// System.out.println(i);
				// }
				//
				// for (String id : allWindowsID) {
				// System.out.println(id);
				// }
				
				// Khai báo 1 biến element là email textbox
				// WebElement emailTextbox = driver.findElement(By.xpath("")); // (**)

				// Khai báo 1 biến để lấy ra tất cả các link trên page hiện tại
				// List <WebElement> links = driver.findElements(By.xpath("a")); // (**)
				
				// Get ra các log của tab Network
				System.out.println(driver.manage().logs().get(LogType.PERFORMANCE));
				
				// Chờ cho element được stable để thao tác lên nó trong khoảng time bao nhiêu -> WebDriverWait
				driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS); // (**)
				
				// Chờ cho 1 page được load thành công trong khoảng time bao nhiêu
				driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS); // (*)
				
				// Dùng cho Javascript Executor (Asynch) -> Sync
				// driver.manage().timeouts().setScriptTimeout(15, TimeUnit.SECONDS);
				
				// F11
				// driver.manage().window().fullscreen();
				
				// Get ra vị trí
				System.out.println(driver.manage().window().getPosition());
				
				// Set vị trí
				Point point = new Point(100, 100);
				driver.manage().window().setPosition(point);
				
				// Get ra chiều rộng/ cao (size)
				System.out.println(driver.manage().window().getSize());
				
				Dimension dimension = new Dimension(1920, 1080);
				driver.manage().window().setSize(dimension);
				
				// Giống User sử dụng
				driver.manage().window().maximize(); // (**)
				
				driver.navigate().back();
				driver.navigate().forward();
				driver.navigate().refresh();
				
				// Tracking history/ gps/ location
				driver.navigate().to("http://live.demoguru99.com/index.php/customer/account/");
				
				// Alert/ Windows/ Frame (Iframe)
				
				// Alert // (**)
				driver.switchTo().alert().accept();
				driver.switchTo().alert().dismiss();
				driver.switchTo().alert().getText();
				driver.switchTo().alert().sendKeys("");
				
				// Windows // (**)
				driver.switchTo().window("Windows GUID");
				
				// Iframe/ frame // (**)
				driver.switchTo().frame(driver.findElement(By.xpath("")));
	}

	@AfterClass
	public void afterClass() {
	}

}
