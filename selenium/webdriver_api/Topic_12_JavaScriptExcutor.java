package webdriver_api;

import org.testng.annotations.Test;

import org.testng.annotations.BeforeTest;

import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;

public class Topic_12_JavaScriptExcutor {
	WebDriver driver;
	String customerID;
	JavascriptExecutor jsExecutor;
	WebElement element;
	/*
	 * ĐĂNG NHẬP BẰNG ID VÀ MẬT KHẨU NÀY User ID : mngr235443 Password : tEbyvEd
	 */
	String userID = "mngr238966";
	String password = "emYmEqe";

	// input in new customer
	String customerName = "ky bui";
	String gender = "female";
	String dateofBirth = "2019-09-09";
	String address = "Nha cua tui";
	String city = "New York";
	String state = "USA";
	String pin = "222333";
	String phone = "0333222444";
	String pass = "0333222444";
	String email = "kybuixinhdep" + randomNumber() + "@gmail.com";

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
	
		@BeforeClass
		public void beforeClass() {
//			driver = new FirefoxDriver();
			// Set driver for JE lib
			System.setProperty("webdriver.chrome.driver", ".\\libraries\\chromedriver.exe");
			
			driver = new ChromeDriver();
						
			jsExecutor = (JavascriptExecutor) driver;

			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.manage().window().maximize();
		}

		
		public void TC_01_JS() {
			//step 01 - sử dụng js để truy cập vào trang web
			navigateToUrlByJS("http://live.guru99.com/");
			
			//step 02 03- get domain của page và verify
			String liveDomain =(String)executeForBrowser("return document.domain");
			Assert.assertEquals(liveDomain,"live.demoguru99.com" );
			
			//Step04 05- open link
			highlightElement("//a[contains(text(),'Mobile')]");
			clickToElementByJS("//a[contains(text(),'Mobile')]");
			highlightElement("//a[text()='Sony Xperia']/parent::h2[@class='product-name']/following-sibling::div[@class='actions']//a[text()='Add to Compare']");
			clickToElementByJS("//a[text()='Sony Xperia']/parent::h2[@class='product-name']/following-sibling::div[@class='actions']//a[text()='Add to Compare']");
			
			//Step 06 - compare text
			String pageInnerText = (String)executeForBrowser("return document.documentElement.innerText;");
			Assert.assertTrue(pageInnerText.contains("The product Sony Xperia has been added to comparison list."));
			
			//Step 07 - verify title
			highlightElement("//a[text()='Customer Service']");
			clickToElementByJS("//a[text()='Customer Service']");
			String customerServiceTitle = (String)executeForBrowser("return document.title");
			Assert.assertEquals(customerServiceTitle, "Customer Service");
			
			//Step 08 - Scroll to bottom page
			scrollToBottomPage();
			
			//Step 09 - verify text C2
			verifyTextInInnerText("Praesent ipsum libero, auctor ac, tempus nec, tempor nec, justo.");
			
			//Step 10 - navigate to another link
			navigateToUrlByJS("http://live.guru99.com/v4/");
			
			String demoDomain =(String)executeForBrowser("return document.domain");
			Assert.assertEquals(demoDomain,"live.demoguru99.com" );
		}
		
		@Test
		public void TC_02_RemoveAttribute() throws InterruptedException {
			navigateToUrlByJS("http://demo.guru99.com/v4");
			// login
			driver.findElement(By.name("uid")).sendKeys(userID);
			driver.findElement(By.name("password")).sendKeys(password);
			clickToElementByJS("//input[@name='btnLogin']");
			
			//New customer
			driver.findElement(By.xpath("//a[text()='New Customer']")).click();
			sendkeyToElementByJS(customerNameInput, customerName);
			driver.findElement(genderRadiobtn).click();
			
			//Remove attribute
			removeAttributeInDOM("//input[@id='dob']", "type");
			sendkeyToElementByJS(dobInput, dateofBirth);
			Thread.sleep(3000);
			
			driver.findElement(addressTextArea).sendKeys(address);
//			senkeysToElement(addressTextArea, address);
			sendkeyToElementByJS(cityInput, city);
			sendkeyToElementByJS(sateInput, state);
			sendkeyToElementByJS(pinInput, pin);
			sendkeyToElementByJS(telInput, phone);
			sendkeyToElementByJS(emailInput, email);
			sendkeyToElementByJS(passwordInput, pass);

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
		}
		// Browser
		public Object executeForBrowser(String javaSript) {
			return jsExecutor.executeScript(javaSript);
		}

		public boolean verifyTextInInnerText(String textExpected) {
			String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0]");
			System.out.println("Text actual = " + textActual);
			return textActual.equals(textExpected);
		}

		
		public void scrollToBottomPage() {
			jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
		}

		//navigate
		public void navigateToUrlByJS(String url) {
			jsExecutor.executeScript("window.location = '" + url + "'");
		}

		//hightligh Element
		public void highlightElement(String locator) {
			element = driver.findElement(By.xpath(locator));
			String originalStyle = element.getAttribute("style");
			jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 5px solid red; border-style: dashed;");
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);

		}

		//click element
		public void clickToElementByJS(String locator) {
			element = driver.findElement(By.xpath(locator));
			jsExecutor.executeScript("arguments[0].click();", element);
		}

		//scroll
		public void scrollToElement(String locator) {
			element = driver.findElement(By.xpath(locator));
			jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
		}

		//sendkey
		public void sendkeyToElementByJS(By locator, String value) {
			element = driver.findElement(locator);
			jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", element);
		}

		//Remove
		public void removeAttributeInDOM(String locator, String attributeRemove) {
			element = driver.findElement(By.xpath(locator));
			jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", element);
		}
		
		// random mot so
		public int randomNumber() {
			Random rand = new Random();
			return rand.nextInt(100);
		}
		
	@AfterTest
	public void afterTest() {
		// check commit
//		driver.quit();
	}

}
