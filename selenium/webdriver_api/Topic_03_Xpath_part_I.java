package webdriver_api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;

import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public class Topic_03_Xpath_part_I {
	WebDriver driver;
	String firstName = "Automation";
	String lastName = "Advanced";
	String validatePassword = "123123";
	String validateEmail = "automation" +randomNumber() + "@gmail.com";
	String middleename = "Online";

	@BeforeClass(description = "Chạy trước cho tất cả các test bên dưới")
	public void beforeTest() {
		driver = new FirefoxDriver();

		// Chờ cho element được hiển thị trước khi tương tác trong vòng 30s
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		// Phóng to trình duyệt
		driver.manage().window().maximize();
	}

	@BeforeMethod(description = "chạy trước cho mỗi test bên dưới")
	public void BeforeMethod() {
		System.out.println("Mở ra trang AUT");
		driver.get("http://live.demoguru99.com");

		System.out.println("Click vào myaccount link ở dưới footer");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
	}

	public void TC_01_ValidateEmptyTextbox() {
		/*
		 * System.out.println("Click vào myaccount link ở header");
		 * driver.findElement(By.xpath("//span[text()='Account']")).click();
		 * driver.findElement(By.
		 * xpath("//div[@id=\"header-account\"]//a[@title='My Account']")).click();
		 */
		// click button login để verify error message tại 2 field email and
		// password:"this is a required field
		driver.findElement(By.xpath("//button[@id='send2']")).click();
		String errorMessage = driver.findElement(By.xpath("//div[@id='advice-required-entry-pass']")).getText();
		Assert.assertEquals(errorMessage, "This is a required field.");
	}

	public void TC_02_CheckErrorMessage() {
		// verify message error when enter an email invalid 1234384@4924829
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("1234384@4924829");
		driver.findElement(By.xpath("//button[@id='send2']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='advice-validate-email-email']")).isDisplayed());
	}

	public void TC_03_CheckErrorMessage() {
		// verify message error when enter a password less then 6 character
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("automation@gmail.com");
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("123");
		driver.findElement(By.xpath("//button[@id='send2']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='advice-validate-password-pass']")).isDisplayed());
	}

	public void TC_04_CheckErrorMessage() {
		// verify message error when enter a password less than 6 character
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("automation@gmail.com");
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("123456");
		driver.findElement(By.xpath("//button[@id='send2']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Invalid login or password.']")).isDisplayed());
	}

	@Test
	public void TC_05_CreateAccount() {
		// tạo email random
		System.out.println("email dk: "+validateEmail);
		// click button create account
		driver.findElement(By.xpath("//div[@class='buttons-set']//span[contains(text(),'Create')]")).click();

		// enter informattion to register
		driver.findElement(By.xpath("//input[@id='firstname']")).sendKeys(firstName);
		driver.findElement(By.xpath("//input[@id='middlename']")).sendKeys(middleename);
		driver.findElement(By.xpath("//input[@id='lastname']")).sendKeys(lastName);
		driver.findElement(By.xpath("//input[@id='email_address']")).sendKeys(validateEmail);
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys(validatePassword);
		driver.findElement(By.xpath("//input[@id='confirmation']")).sendKeys(validatePassword);

		driver.findElement(By.xpath("//button[@title='Register']")).click();

		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='success-msg']//span[text()='Thank you for registering with Main Website Store.']")).isDisplayed());

		String hello = driver.findElement(By.xpath("//p[@class='hello']")).getText();
		System.out.println(hello);
		Assert.assertTrue(driver.findElement(By.xpath("//strong[text()='Hello, " + firstName + " " + middleename + " " + lastName + "!']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(.,'" + firstName + " " + middleename + " " + lastName + "')]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='box-content']//p[contains(.,'" + validateEmail + "')]")).isDisplayed());

		driver.findElement(By.xpath("//div[@class='account-cart-wrapper']//span[text()='Account']")).click();
		driver.findElement(By.xpath("//a[@title='Log Out']")).click();
	}

	@Test
	public void TC_06_LoginandCheckDisplayed() {

		// Login into webpage
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys(validateEmail);
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys(validatePassword);
		driver.findElement(By.xpath("//button[@id='send2']")).click();
//		String hello = driver.findElement(By.xpath("//p[@class='hello']/strong")).getText();
		String hello = driver.findElement(By.xpath("//div[@class='page-title']/h1")).getText();
		System.out.println(hello);

		// verify elements is displayed?
		// Cách 1: Dùng hàm assertTrue(điều kiện) -> locator được hiển thị(isDisplayed)
		Assert.assertTrue(driver.findElement(By.xpath("//strong[text()='Hello, " + firstName + " " + middleename + " " + lastName + "!']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(.,'" + firstName + " " + middleename + " " + lastName + "')]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='box-content']//p[contains(.,'" + validateEmail + "')]")).isDisplayed());
		// Cách 2: dùng hàm assert equarls(điều kiện 1, điều kiện 2)->dùng get text();
		Assert.assertEquals(driver.findElement(By.xpath("//h1")).getText(), "MY DASHBOARD");

	}

	@AfterTest
	public void afterTest() {
		driver.quit();

	}
//tạo ham random
	public int randomNumber() {
		Random rand = new Random(100);
		int n = rand.nextInt();
		return n;
	}
}
