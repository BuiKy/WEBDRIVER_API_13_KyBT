package webdriver_api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_05_WebElement_I {
	WebDriver driver;
	By emailLabel = By.xpath("//label[contains(text(),'Email:')]");
	By emailInput = By.xpath("//label[contains(text(),'Email:')]");
	By ageLabel = By.xpath("//label[contains(text(),'Age')]");
	By ageRadiobuttonUnder18 = By.xpath("//input[@id='under_18']");
	By educationLabel = By.xpath("//label[contains(text(),'Education:')]");
	By educationAreaInput = By.xpath("//textarea[@id='edu']");
	By jobrole01Label = By.xpath("//label[contains(text(),'Job Role 01:')]");
	By job1DropDownList = By.xpath("//select[@id='job1']");
	By interestsLabel = By.xpath("//label[contains(text(),'Interests:')]");
	By interestDev = By.xpath("//input[@id='development']");
	By slider1Input = By.xpath("//input[@id='slider-1']");
	By passwordInput = By.xpath("//input[@id='password']");
	By slider2Input = By.xpath("//input[@id='slider-2']");

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
		driver.get("https://automationfc.github.io/basic-form/index.html");

	}

	@Test
	public void TC_01_ElementisDisplayed() throws InterruptedException {

		if (isElementDisplayed(emailInput)) {
			senkeys(emailInput, "buithiky@gmail.com");
		}
		if (isElementDisplayed(educationAreaInput)) {
			senkeys(educationAreaInput, "phai hoc that tap trung vào");
		}

		if (isElementDisplayed(ageRadiobuttonUnder18)) {
			clickonElement(ageRadiobuttonUnder18);
		}
		// Thread.sleep(5000);

	}

	@Test
	public void TC_02_isEnabled() {
		assertTrue(isElementEnale(emailInput));
		assertTrue(isElementEnale(ageRadiobuttonUnder18));
		assertTrue(isElementEnale(educationAreaInput));
		assertTrue(isElementEnale(job1DropDownList));
		assertTrue(isElementEnale(interestDev));
		assertTrue(isElementEnale(slider1Input));
		assertFalse(isElementEnale(passwordInput));
		assertFalse(isElementEnale(slider2Input));

		if (isElementEnale(emailInput) || isElementEnale(ageRadiobuttonUnder18)) {
			System.out.println("element is enable");
		} else
			System.out.println("element is disable");

		if (isElementEnale(passwordInput) || isElementEnale(slider2Input)) {
			System.out.println("element is enable");
		} else
			System.out.println("element is disable");

	}

	@Test
	public void TC_03_Navigation() {
		clickonElement(interestDev);
		clickonElement(ageRadiobuttonUnder18);

		assertTrue(isElementSelected(interestDev));
		assertTrue(isElementSelected(ageRadiobuttonUnder18));

		if (isElementSelected(interestDev)) {
			clickonElement(interestDev);
		}

		assertFalse(isElementSelected(interestDev));
	}

//	Check Element is displayed 
	public boolean isElementDisplayed(By by) {
		try {
			WebElement locator;
			locator = driver.findElement(by);
			return locator.isDisplayed();
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	// Check Element is enable
	public boolean isElementEnale(By by) {
		try {
			WebElement locator;
			locator = driver.findElement(by);
			return locator.isEnabled();
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	// senkeys to an input tag
	public void senkeys(By by, String key) {
		WebElement element = driver.findElement(by);
		element.sendKeys(key);
	}

	// click to an element
	public void clickonElement(By by) {
		WebElement element = driver.findElement(by);
		element.click();
	}

	// check element is selected
	public boolean isElementSelected(By by) {
		try {
			WebElement locator;
			locator = driver.findElement(by);
			return locator.isSelected();
		} catch (NoSuchElementException e) {
			return false;
		}
	}
	/*
	 * =============================================================================
	 * ==================
	 */
	// TESCASE STUDY

	public void TC_00_lecture() {
		/*
		 * Assert.assertTrue(driver.findElement(emailInput).isEnabled());
		 * Assert.assertTrue(driver.findElement(ageRadiobuttonUnder18).isEnabled());
		 * Assert.assertTrue(driver.findElement(ageRadiobuttonUnder18).isEnabled());
		 * Assert.assertTrue(driver.findElement(educationAreaInput).isEnabled());
		 * Assert.assertTrue(driver.findElement(interestDev).isEnabled());
		 */
		// get text trong attribute của element
		WebElement passwordInput = driver.findElement(By.xpath("//input[@id='password']"));
		String passwwordTextBoxHint = passwordInput.getAttribute("placeholder");
		System.out.println("Text trong attribute: " + passwwordTextBoxHint);

		// get thuộc tính màu sắc của element
		WebElement buttonDisable = driver.findElement(By.id("button-disabled"));
		String buttonColor = buttonDisable.getCssValue("background-color");
		System.out.println("Background cua button disable: " + buttonColor);

		// lấy ra vị trí của nó so với độ phân giải màn hình
		System.out.println("size cua element: " + buttonDisable.getSize());
		System.out.println("get location cua element: " + buttonDisable.getLocation());

		// Lấy tagName, thường dùng các loại locator như id/css/class/name
		System.out.println("Tag name: " + buttonDisable.getTagName());
		System.out.println("Text hien thị trong the: " + buttonDisable.getText());

		// kiêm tra một phần tử có được hiển thị ko?
		WebElement imageHover = driver.findElement(By.xpath("//img[@alt='User Avatar 05']/following-sibling::div/h5"));
		System.out.println("Check image is display: " + imageHover.isDisplayed());
		assertFalse(imageHover.isDisplayed());

		// isEnable, selected... tương tự
		// work cho cái element là một cái form
		imageHover.submit();
	}
	/*
	 * =============================================================================
	 * ==================
	 */

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
