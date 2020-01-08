package webdriver_api;

import org.testng.annotations.Test;

import org.testng.annotations.BeforeTest;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class Topic_11_Windown_Tab {
	WebDriver driver;
	JavascriptExecutor je;
	Alert alert;

	@BeforeTest
	public void beforeTest() {
		driver = new FirefoxDriver();
		je = (JavascriptExecutor) driver;
		// Chờ cho element được hiển thị trước khi tương tác trong vòng 30s
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		// Phóng to trình duyệt
//		driver.manage().window().maximize();
	}

	
	public void TC_02_WindowsTabs() throws InterruptedException {
		driver.get("https://automationfc.github.io/basic-form/index.html");

		String parentID = driver.getWindowHandle();
		System.out.println("parent ID = " + parentID);
		// ID của tab đang đứng active

		driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
		switchToWindowByTitle("Google");
		Assert.assertEquals(driver.getTitle(), "Google");
		driver.switchTo().window(parentID);

		driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
		switchToWindowByTitle("Facebook");
		Assert.assertEquals(driver.getTitle(), "Facebook - Đăng nhập hoặc đăng ký");
		driver.switchTo().window(parentID);

		driver.findElement(By.xpath("//a[text()='TIKI']")).click();
		switchToWindowByTitle("TIKI");
		Assert.assertEquals(driver.getTitle(), "Mua Hàng Trực Tuyến Uy Tín với Giá Rẻ Hơn tại Tiki.vn");

		closeAllWindowsWithoutParent(parentID);
		System.out.println("URL hien tai: " + driver.getCurrentUrl());

//	driver.findElement(By.xpath("//a[text()='LAZADA']")).click();
		// List<String>: cho phép lưu dữ liệu trùng nhau
		// Set<String>: ko được phép, nếu trùng thì chỉ lấy 1

		// lấy tất cả các tab/window
//		Set<String> allIDs = driver.getWindowHandles();
//		for (String id : allIDs) {
//			System.out.println("ID=" + id);
//		}

	}

	
	public void TC_03_WindowsTabs() throws InterruptedException {
		
		//có sử dụng so sánh sự khác nhau của bảng mã
		driver.get("https://kyna.vn/");
		//sleep cứng để load toàn bộ web(mục đích chính là cho popup xuất hiện)
		Thread.sleep(5000);
		String parentID = driver.getWindowHandle();
		//check popup có hiển thị trên trang AUT
		List<WebElement> popupFancy = driver.findElements(By.xpath("//div[@class='fancybox-inner']"));
		System.out.println("Fancy popup number: "+popupFancy.size());
		if(popupFancy.size()>0) {
			Assert.assertTrue(popupFancy.get(0).isDisplayed());
			//close popup
			driver.findElement(By.cssSelector(".fancybox-close")).click();
		}
		
		//click vào trang facebook trên top window
		driver.findElement(By.xpath("//div[@id='k-footer']//div[@class='social']//img[@alt='facebook']"));
		switchToWindowByTitle("Kyna.vn - Trang chủ");
		System.out.println("1. url fanpage: "+driver.getCurrentUrl());
		//Assert.assertEquals(driver.getTitle(), "Kyna.vn - Trang chủ");
		driver.switchTo().window(parentID);

//		//click vào youtube
//		driver.findElement(By.xpath("//div[@id='k-footer']//div[@class='social']//img[@alt='youtube']")).click();
//		switchToWindowByTitle("Kyna.vn - YouTube");
//		System.out.println("1. url youtube: "+driver.getCurrentUrl());
//
//		//click vào zalo
//		driver.findElement(By.xpath("//div[@id='k-footer']//div[@class='social']//img[@alt='zalo']")).click();
//		System.out.println("1. url zalo: "+driver.getCurrentUrl());
//		
//		//Switch vào iframe đó trước thì mới tương tác được với các element trong frame đó
//		driver.switchTo().frame(driver.findElement(By.xpath("//div[@class='fanpage']//iframe")));
//		
//		//click vào iframe
//		driver.findElement(By.cssSelector("#facebook")).click();
//		switchToWindowByTitle("Kyna.vn - Trang chủ");
//		System.out.println("2. url fanpage: "+driver.getCurrentUrl());
//		
		//click vào gg play
//		clickElementByJS("//div[@id='k-footer']//div[@class='icon-app']//img[@alt='android-app-icon']");
		driver.findElement(By.xpath("//div[@id='k-footer']//div[@class='icon-app']//img[@alt='android-app-icon']")).click();
		switchToWindowByTitle("KYNA - Học online cùng chuyên gia - Ứng dụng trên Google Play");
		System.out.println("3. url fanpage: "+driver.getCurrentUrl());
	}
	
	@Test
	public void TC_4_WindowsTabs() throws InterruptedException {
		driver.get("http://live.guru99.com/index.php/");
		String parentID =  driver.getWindowHandle();
		
		driver.findElement(By.xpath("//a[contains(text(),'Mobile')]")).click();
		
		driver.findElement(By.xpath("//a[text()='Sony Xperia']/parent::h2[@class='product-name']/following-sibling::div[@class='actions']//a[text()='Add to Compare']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='The product Sony Xperia has been added to comparison list.']")).isDisplayed());
		
		driver.findElement(By.xpath("//a[text()='Samsung Galaxy']/parent::h2[@class='product-name']/following-sibling::div[@class='actions']//a[text()='Add to Compare']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='The product Samsung Galaxy has been added to comparison list.']")).isDisplayed());
		
		driver.findElement(By.xpath("//button[@title='Compare']")).click();
		switchToWindowByTitle("Products Comparison List - Magento Commerce");
		Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='Compare Products']")).isDisplayed());
		
		driver.switchTo().window(parentID);
		
		driver.findElement(By.xpath("//a[text()='Clear All']")).click();
		
		alert = driver.switchTo().alert();
		alert.accept();
		
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='The comparison list was cleared.']")).isDisplayed());
		
	}
	
	// switch to child windows (only 2 windows)
	public void switchTWindowByID(String parentID) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindow : allWindows) {
			if (runWindow.equals(parentID)) {
				driver.switchTo().window(runWindow);
				break;
			}
		}
	}

	// switch to child windows (greater than 2 windows and title of the page are
	// unique)
	public void switchToWindowByTitle(String title) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindow : allWindows) {
			driver.switchTo().window(runWindow);
			String currentWin = driver.getTitle();
			if (currentWin.equals(title)) {
				break;
			}
		}
	}

	// close all window without parent window
	public boolean closeAllWindowsWithoutParent(String parentWindow) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindow : allWindows) {
			if (!runWindow.equals(parentWindow)) {
				driver.switchTo().window(runWindow);
				driver.close();
			}
		}
		driver.switchTo().window(parentWindow);
		if (driver.getWindowHandles().size() == 1) {
			return true;
		} else
			return false;

	}
	
	//click by java script
	public void clickElementByJS(String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		je.executeScript("arguments[0].click();",element);
	}

	@AfterTest
	public void afterTest() {
		// check commit
//		driver.quit();
	}

}
