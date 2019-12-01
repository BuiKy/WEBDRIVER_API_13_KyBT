package webdriver_api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;

import static org.junit.Assert.assertTrue;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class Topic_06_TextBoxAreaBox {
	WebDriver driver;
	String customerID;

	/*
	 * ĐĂNG NHẬP BẰNG ID VÀ MẬT KHẨU NÀY User ID : mngr235443 Password : tEbyvEd
	 */
	String userID = "mngr235443";
	String password = "tEbyvEd";

	// input in new customer
	String customerName = "ky bui";
	String gender = "female";
	String dateofBirth = "1995-09-09";
	String address = "Mohamet";
	String city = "New York";
	String state = "USA";
	String pin = "222333";
	String phone = "0333222444";
	String pass = "0333222444";
	String email = "kybuixinhdep" + randomNumber() + "@gmail.com";

	// input in edit customer
	String addressEdit = "kuba";
	String cityEdit = "Ha Lan";
	String stateEdit = "USA-USB";
	String pinEdit = "222334";
	String phoneEdit = "0333222445";
	String passEdit = "0333222445";
	String emailEdit = "donaxinhdep" + randomNumber() + "@gmail.com";

	By customerNameInput = By.name("name");
	By genderRadiobtn = By.xpath("//input[@value='f']");
	By genderRadioTxb = By.name("gender");
	By dobInput = By.name("dob");
	By addressTextArea = By.name("addr");
	By cityInput = By.name("city");
	By sateInput = By.name("state");
	By pinInput = By.name("pinno");
	By telInput = By.name("telephoneno");
	By emailInput = By.name("emailid");
	By passwordInput = By.name("password");

	@BeforeTest
	public void beforeTest() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://demo.guru99.com/v4/");

		// login
		driver.findElement(By.name("uid")).sendKeys(userID);
		driver.findElement(By.name("password")).sendKeys(password);
		driver.findElement(By.name("btnLogin")).click();

		// veryfy login
		String welcomeText = driver.findElement(By.tagName("marquee")).getText();
		Assert.assertEquals(welcomeText, "Welcome To Manager's Page of Guru99 Bank");

		Assert.assertTrue(driver.findElement(By.xpath("//tr[@class='heading3']/td[text()='Manger Id : " + userID + "']")).isDisplayed());

	}

	@Test
	public void TC_01_NewCustomer() throws InterruptedException {
		driver.findElement(By.xpath("//a[text()='New Customer']")).click();
		senkeysToElement(customerNameInput, customerName);
		driver.findElement(genderRadiobtn).click();
		senkeysToElement(dobInput, dateofBirth);
		senkeysToElement(addressTextArea, address);
		senkeysToElement(cityInput, city);
		senkeysToElement(sateInput, state);
		senkeysToElement(pinInput, pin);
		senkeysToElement(telInput, phone);
		senkeysToElement(emailInput, email);
		senkeysToElement(passwordInput, pass);

		driver.findElement(By.name("sub")).click();
		// verify nhập dữ liệu mapping thành công
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='heading3' and text()='Customer Registered Successfully!!!']")).isDisplayed());
		Assert.assertEquals(customerName, driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText());
		Assert.assertEquals(gender, driver.findElement(By.xpath("//td[text()='Gender']/following-sibling::td")).getText());
		Assert.assertEquals(dateofBirth, driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText());
		Assert.assertEquals(address, driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText());
		Assert.assertEquals(city, driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText());
		Assert.assertEquals(state, driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText());
		Assert.assertEquals(pin, driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText());
		Assert.assertEquals(phone, driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText());
		Assert.assertEquals(email, driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText());
		// get ra id customer
		customerID = driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling :: td")).getText();
		System.out.println("ID của khách hàng: " + customerID);

		Thread.sleep(10000);
	}

	@Test
	public void TC_02_EditCustomer() {
		// edit thong tin khách hàng vừa đăng kí
		driver.findElement(By.xpath("//a[text()='Edit Customer']")).click();
		driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td/input")).sendKeys(customerID);
		driver.findElement(By.name("AccSubmit")).click();;

		// verify cac fields bi enable
		Assert.assertFalse(checkElementEnable(customerNameInput));
		Assert.assertFalse(checkElementEnable(genderRadioTxb));
		Assert.assertFalse(checkElementEnable(dobInput));

		// verify output at edit customer form = input at new customer form
		Assert.assertEquals(customerName, driver.findElement(customerNameInput).getAttribute("value"));
		Assert.assertEquals(gender, driver.findElement(genderRadioTxb).getAttribute("value"));
		Assert.assertEquals(dateofBirth, driver.findElement(dobInput).getAttribute("value"));
		Assert.assertEquals(address, driver.findElement(addressTextArea).getText());
		Assert.assertEquals(city, driver.findElement(cityInput).getAttribute("value"));
		Assert.assertEquals(state, driver.findElement(sateInput).getAttribute("value"));
		Assert.assertEquals(pin, driver.findElement(pinInput).getAttribute("value"));
		Assert.assertEquals(phone, driver.findElement(telInput).getAttribute("value"));
		Assert.assertEquals(email, driver.findElement(emailInput).getAttribute("value"));

		// edit data at customer form
		driver.findElement(addressTextArea).clear();
		senkeysToElement(addressTextArea, addressEdit);
		driver.findElement(cityInput).clear();
		senkeysToElement(cityInput, cityEdit);
		driver.findElement(sateInput).clear();
		senkeysToElement(sateInput, stateEdit);
		driver.findElement(pinInput).clear();
		senkeysToElement(pinInput, pinEdit);
		driver.findElement(telInput).clear();
		senkeysToElement(telInput, phoneEdit);
		driver.findElement(emailInput).clear();
		senkeysToElement(emailInput, emailEdit);

		driver.findElement(By.name("sub")).click();
		// verify nhập dữ liệu mapping thành công
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='heading3' and text()='Customer details updated Successfully!!!']")).isDisplayed());
		Assert.assertEquals(customerID, driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).getText());
		Assert.assertEquals(customerName, driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText());
		Assert.assertEquals(gender, driver.findElement(By.xpath("//td[text()='Gender']/following-sibling::td")).getText());
		Assert.assertEquals(dateofBirth, driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText());
		
		Assert.assertEquals(addressEdit, driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText());
		Assert.assertEquals(cityEdit, driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText());
		Assert.assertEquals(stateEdit, driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText());
		Assert.assertEquals(pinEdit, driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText());
		Assert.assertEquals(phoneEdit, driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText());
		Assert.assertEquals(emailEdit, driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText());
	}


	@AfterTest
	public void afterTest() {
		// check commit
		driver.quit();
	}

	// random mot so
	public int randomNumber() {
		Random rand = new Random();
		return rand.nextInt(100);
	}

	// enter text to element input tag
	public void senkeysToElement(By by, String key) {
		WebElement element = driver.findElement(by);
		element.sendKeys(key);
	}

	// check element is enable
	public boolean checkElementEnable(By by) {
		WebElement element = driver.findElement(by);
		return element.isEnabled();
	}
}
