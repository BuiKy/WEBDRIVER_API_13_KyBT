package webdriver_api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class Topic_07_DropdownList_I {
	WebDriver driver;
	Select select, selectDay, selectMonth, selectYear;

	By registerLink = By.xpath("//a[text()='Register']");
	By genderMale = By.xpath("//input[@id='gender-male']");
	By genderFemale = By.xpath("//input[@id='gender-female']");
	By firstNameTxt = By.name("FirstName");
	By lastNameTxt = By.name("LastName");
	By emailTxt = By.name("Email");
	By companyNameTxt = By.name("Company");
	By newsletterCheckBox = By.name("Newsletter");
	By passwordTxt = By.name("Password");
	By confirmpasswordTxt = By.name("ConfirmPassword");

	// Input data to register
	String gender = "Male";
	String firstName = "Jame";
	String lastName = "David";
	String email = "dontbeshy@gmail.com";
	String company = "ABS soft";
	String password = "123456";

	@BeforeTest
	public void beforeTest() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_DefaultTagDropdownList() {
		// Mở ra 1 trang web (AUT: Application Under Test)
		driver.get("https://automationfc.github.io/basic-form/index.html");
		// check single select
		select = new Select(driver.findElement(By.xpath("//select[@id='job1']")));
		Assert.assertFalse(select.isMultiple());
		// select by text visible
		select.selectByVisibleText("Mobile Testing");
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Mobile Testing");
		// select by value
		select.selectByValue("manual");
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Manual Testing");
		// select by index
		select.selectByIndex(9);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Functional UI Testing");

		Assert.assertEquals(select.getOptions().size(), 10);

		// check multiple select
		select = new Select(driver.findElement(By.xpath("//select[@id='job2']")));
		Assert.assertTrue(select.isMultiple());
		select.selectByVisibleText("Adhoc");
		select.selectByVisibleText("Manual");
		select.selectByVisibleText("Unit");
		select.selectByVisibleText("Functional UI");

		List<WebElement> optionSelected = select.getAllSelectedOptions();
		List<String> arraySelected = new ArrayList<String>();
		for (WebElement select : optionSelected) {
			arraySelected.add(select.getText());
		}

		Assert.assertTrue(arraySelected.contains("Adhoc"));
		Assert.assertTrue(arraySelected.contains("Functional UI"));
		Assert.assertTrue(arraySelected.contains("Unit"));
		Assert.assertTrue(arraySelected.contains("Manual"));

		select.deselectAll();
		Assert.assertEquals(select.getAllSelectedOptions().size(), 0);
	}

	@Test
	public void TC_02_DefaultTagDropdownList() throws InterruptedException {
		// Mở ra 1 trang web (AUT: Application Under Test)
		driver.get("https://demo.nopcommerce.com");
		driver.findElement(By.xpath("//a[text()='Register']")).click();
		
		selectDay = new Select(driver.findElement(By.name("DateOfBirthDay")));
		selectMonth = new Select(driver.findElement(By.name("DateOfBirthMonth")));
		selectYear = new Select(driver.findElement(By.name("DateOfBirthYear")));
		

		driver.findElement(genderFemale).click();
		senkeysToElement(firstNameTxt, firstName);
		senkeysToElement(lastNameTxt, lastName);
		
		Assert.assertEquals(selectDay.getOptions().size(), 32 );
		selectDay.selectByVisibleText("1");
		
		Assert.assertEquals(selectMonth.getOptions().size(), 13 );
		selectMonth.selectByVisibleText("May");
		
		Assert.assertEquals(selectYear.getOptions().size(), 112 );
		selectYear.selectByVisibleText("1980");
		
		senkeysToElement(emailTxt, email);
		senkeysToElement(companyNameTxt, company);
		if(!driver.findElement(newsletterCheckBox).isEnabled()) {
			driver.findElement(newsletterCheckBox).click();
		}
		senkeysToElement(passwordTxt, password);
		senkeysToElement(confirmpasswordTxt, password);
		driver.findElement(By.name("register-button")).click();
		Thread.sleep(30000);
	}
	
	@AfterTest
	public void afterTest() {
		// check commit
		driver.quit();
	}

	/* =============================common function==================== */
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