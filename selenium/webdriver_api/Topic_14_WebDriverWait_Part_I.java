package webdriver_api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;

public class Topic_14_WebDriverWait_Part_I {
	WebDriver driver;
	WebDriverWait waitExplicit;

	boolean status;

	@BeforeTest
	public void beforeTest() {
		driver = new FirefoxDriver();
		waitExplicit = new WebDriverWait(driver, 15);

		// Chờ cho element được hiển thị trước khi tương tác trong vòng 30s
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		// Phóng to trình duyệt
		driver.manage().window().maximize();

	}

	public void TC_01_ElementDisplayedOrVisible() {
		driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
		// DK 1 - Element có hiên thị trên UI + Có trong DOM
		waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@id='SubmitLogin']"))); // chờ cho element displayed/visible
		status = driver.findElement(By.xpath("//button[@id='SubmitLogin']")).isDisplayed(); // kiểm tra element có hiển thị ko?
		System.out.println("Element có hiên thị trên UI + Có trong DOM = " + status);

		// ĐK 2 - Element ko hiển thị trên UI + có trong DOM
		waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='create_account_error']"))); // chờ cho element undisplayed/unvisible
		status = driver.findElement(By.xpath("//div[@id='create_account_error']")).isDisplayed();
		System.out.println("Element ko hiển thị trên UI + có trong DOM = " + status);

		// ĐK 3 - Element ko hiển thị trên UI + ko có trong DOM
		waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='create_account_error']")));
		status = driver.findElement(By.xpath("//inpput[@id='id_order']")).isDisplayed();
		System.out.println("Element ko hiển thị trên UI + ko có trong DOM  = " + status);

	}

	
	public void TC_02_ElementUndisplayedOrInvisible() {
		driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
		// DK 1 - Element có hiên thị trên UI + Có trong DOM
		waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//button[@id='SubmitLogin']")));
		// ĐK 2 - Element ko hiển thị trên UI + có trong DOM
		waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='create_account_error']")));
		// ĐK 3 - Element ko hiển thị trên UI + ko có trong DOM
		waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='create_account_error']")));

	}

	@Test
	public void TC_03_ElementPresence() {
		driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
		// DK 1 - Element có hiên thị trên UI + Có trong DOM
		waitExplicit.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@id='SubmitLogin']")));
		// ĐK 2 - Element ko hiển thị trên UI + có trong DOM
		waitExplicit.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='create_account_error']")));
		// ĐK 3 - Element ko hiển thị trên UI + ko có trong DOM
		waitExplicit.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='create_account_error']")));

	}

	public String getcurrentTime() {
		return "";
	}

	@AfterTest
	public void afterTest() {
		// check commit
		driver.quit();
	}

}
