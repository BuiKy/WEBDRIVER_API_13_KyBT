package webdriver_api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class Topic_08_Button_Radio_CheckBox_Alert {
	WebDriver driver;
	Actions action;
	JavascriptExecutor je;
	Alert alert;

	@BeforeTest
	public void beforeTest() {
		driver = new FirefoxDriver();
		Actions action = new Actions(driver);
		je = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	public void TC_01_Button() {
		//sử dụng javascipt executor code để click vào link my account
		driver.get("http://live.guru99.com/");
		
		clickElementByJS("//div[@id='header-account']//ul//a[text()='My Account']");
		
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/login/");
		
		clickElementByJS("//a[@title='Create an Account']");
		
		Assert.assertEquals(driver.getCurrentUrl(),"http://live.demoguru99.com/index.php/customer/account/create/" );
		System.out.println(driver.getCurrentUrl());
	}
	
	public void TC_02_CheckBox() {
		driver.get("http://demos.telerik.com/kendo-ui/styling/checkboxes");
		//Nếu checkbox hoặc radio bị ần thì user ko thể click vào được
		String checkBoxInput = "//label[@class='k-checkbox-label' and contains(text(),'Dual')]/preceding-sibling::input";
		String checkBoxLabel = "//label[@class='k-checkbox-label' and contains(text(),'Dual')]";
		
		if(isElementSelected(checkBoxInput)) {
			checkElementIsSelected(checkBoxInput);
		} else {
			driver.findElement(By.xpath(checkBoxLabel)).click();
		}
		Assert.assertTrue(isElementSelected(checkBoxInput));
	}
	
	public void TC_03_RadioButton() {
		driver.get("https://demos.telerik.com/kendo-ui/styling/radios");
		
		//driver.findElement(By.xpath("//label[@class='k-radio-label' and text()='2.0 Petrol, 147kW']/preceding-sibling::input")).click();
		clickElementByJS("//label[@class='k-radio-label' and text()='2.0 Petrol, 147kW']");
		
		Assert.assertTrue(isElementSelected("//label[@class='k-radio-label' and text()='2.0 Petrol, 147kW']/preceding-sibling::input"));
	}
	
	public void TC_04_HandleAlert() throws InterruptedException {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		String resultMessage = "//p[@id='result']";
		String enterString = "I am automation tester";
				
		//Accept An Alert
		driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
		alert = driver.switchTo().alert();
		
		Assert.assertEquals(alert.getText(), "I am a JS Alert");
		alert.accept();
		Thread.sleep(5000);
		
		Assert.assertTrue(isElementDisplayed(resultMessage));
		Assert.assertEquals(driver.findElement(By.xpath(resultMessage)).getText(), "You clicked an alert successfully");
		
		//Cancel Alert
		driver.navigate().refresh(); //phải reload lại page
		driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
		alert = driver.switchTo().alert();
		Assert.assertEquals(alert.getText(), "I am a JS Confirm");
		alert.dismiss();
		Thread.sleep(5000);
		
		Assert.assertEquals(driver.findElement(By.xpath(resultMessage)).getText(), "You clicked: Cancel");
		
		//Send Key to Alert
		driver.navigate().refresh(); //phải reload lại page
		driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
		
		alert = driver.switchTo().alert();
		
		alert.sendKeys(enterString);
		Assert.assertEquals(alert.getText(), "I am a JS prompt");
		alert.accept();
		Thread.sleep(5000);
		
		Assert.assertEquals(driver.findElement(By.xpath(resultMessage)).getText(), "You entered: "+enterString);
		
		
	}
	
	@Test
	public void TC_05_AuthenticationAlert() {
		driver.get("http://the-internet.herokuapp.com");
		WebElement basicAuthenLink = driver.findElement(By.xpath("//a[text()='Basic Auth']"));
		String url = basicAuthenLink.getAttribute("href");
		
		driver.get(byPassauthentication(url, "admin", "admin"));
	}
	/*common function*/
	public String byPassauthentication(String url, String username, String password) {
		System.out.println("old url = "+url);
		
		String [] splitUrl = url.split("//");
		
		url = splitUrl[0] + "//" + username + ":" +password + "@" + splitUrl[1];
		System.out.println("New url = "+url);
		
		return url;
	}
	public boolean isElementDisplayed(String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		return element.isDisplayed();
	}
	public void clickElementByJS(String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		je.executeScript("arguments[0].click();",element);
	}
	public void checkElementisenable(String locator) {
		WebElement element =driver.findElement(By.xpath(locator));
		if(element.isEnabled()) {
			System.out.println("This is enable element!");
		} else {
			System.out.println("This is disable element!");
		}
	}
	public void checkElementIsSelected(String locator) {
		WebElement element =driver.findElement(By.xpath(locator));
		if(element.isSelected()) {
			System.out.println("This is Selected element!");
		} else {
			System.out.println("This is deselected element!");
		}
	}
	
	public boolean isElementSelected(String locator) {
		WebElement element =driver.findElement(By.xpath(locator));
		if(element.isSelected()) {
			return true;
		} else {
			return false;
		}
	}
	@AfterTest
	public void afterTest() {
		// check commit
		driver.quit();
	}

}
