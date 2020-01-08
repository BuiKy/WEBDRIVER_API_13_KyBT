package webdriver_api;

import org.testng.annotations.Test;

import com.sun.corba.se.impl.oa.poa.ActiveObjectMap.Key;

import org.testng.annotations.BeforeTest;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class Topic_07_DropdownList_II {
	WebDriver driver;
	WebDriverWait waitExplicit;
	Select select;
	Actions action;
	JavascriptExecutor javaScript;
	By numberSelect = By.xpath("//ul[@id='number-menu']/li");
	By localDataSelect = By.xpath("//ul[@id='games_options']/li");
	By vueJSDropdown = By.xpath("//li[@class='dropdown-toggle']/following-sibling::ul/li");
	By reactDropdown = By.xpath("//h3[@id='selection']/ancestor::div[@class='equal width row']/following-sibling::div//div[@role='listbox']//div[@role='option']");

	@BeforeTest
	public void beforeTest() {
		driver = new FirefoxDriver();
		javaScript = (JavascriptExecutor) driver;
		action = new Actions(driver);
		waitExplicit = new WebDriverWait(driver, 10);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	public void TC_01_jquery() throws InterruptedException {
		// jquery
		driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");
		// select item
		selectItemInCustomDropdown("//span[@id='number-button']", numberSelect, "5");
		Assert.assertTrue(driver.findElement(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text' and text()='5']")).isDisplayed());

		selectItemInCustomDropdown("//span[@id='number-button']", numberSelect, "2");
		Assert.assertTrue(driver.findElement(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text' and text()='2']")).isDisplayed());

		selectItemInCustomDropdown("//span[@id='number-button']", numberSelect, "1");
		Assert.assertTrue(driver.findElement(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text' and text()='1']")).isDisplayed());

		selectItemInCustomDropdown("//span[@id='number-button']", numberSelect, "18");
		Assert.assertTrue(driver.findElement(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text' and text()='18']")).isDisplayed());

		selectItemInCustomDropdown("//span[@id='number-button']", numberSelect, "5");
		Assert.assertTrue(driver.findElement(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text' and text()='5']")).isDisplayed());

		Thread.sleep(3000);

	}

	public void TC_02_Angular() throws InterruptedException {
		// angular
		driver.get("https://ej2.syncfusion.com/angular/demos/?_ga=2.262049992.437420821.1575083417-524628264.1575083417#/material/drop-down-list/data-binding");
		// select item
		selectItemInCustomDropdown("//ejs-dropdownlist[@id='games']", localDataSelect, "Basketball");
		String expectTxt = getTextByJS("#games_hidden>option");
		System.out.println("text = " + expectTxt);
		Assert.assertEquals(expectTxt, "Basketball");
	}

	public void TC_03_VueJS() throws InterruptedException {
		// VueJS
		driver.get("https://mikerodham.github.io/vue-dropdowns/");
		selectItemInCustomDropdown("//li[@class='dropdown-toggle']", vueJSDropdown, "Second Option");
		String selectedText = driver.findElement(By.xpath("//li[@class='dropdown-toggle']")).getText();
		System.out.println("Text duoc chon: " + selectedText);
		Assert.assertEquals(selectedText, "Second Option");
	}

	public void TC_04_React() throws InterruptedException {
		// React
		driver.get("https://react.semantic-ui.com/modules/dropdown/");
		selectItemInCustomDropdown("//h3[@id='selection']/ancestor::div[@class='equal width row']/following-sibling::div//div[@role='listbox']/i", reactDropdown, "Justen Kitsune");
		checkElementIsDisplayed("//h3[@id='selection']/ancestor::div[@class='equal width row']/following-sibling::div//div[@role='listbox']/div[@class='text' and text()='Justen Kitsune']");

		selectItemInCustomDropdown("//h3[@id='selection']/ancestor::div[@class='equal width row']/following-sibling::div//div[@role='listbox']/i", reactDropdown, "Stevie Feliciano");
		checkElementIsDisplayed("//h3[@id='selection']/ancestor::div[@class='equal width row']/following-sibling::div//div[@role='listbox']/div[@class='text' and text()='Stevie Feliciano']");

		selectItemInCustomDropdown("//h3[@id='selection']/ancestor::div[@class='equal width row']/following-sibling::div//div[@role='listbox']/i", reactDropdown, "Matt");
		checkElementIsDisplayed("//h3[@id='selection']/ancestor::div[@class='equal width row']/following-sibling::div//div[@role='listbox']/div[@class='text' and text()='Matt']");
	}

	@Test
	public void TC_05_Editable() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");
		String countryName = "Albania";

		inputTextInDropdownList("//i[@class='dropdown icon']", "//input[@class='search']", countryName);
		Assert.assertTrue(checkElementIsDisplayed("//div[@class='ui fluid search selection dropdown']//div[@class='text' and text()='Albania']"));

		inputTextInDropdownList("//i[@class='dropdown icon']", "//input[@class='search']", "American Samoa");
		Assert.assertTrue(checkElementIsDisplayed("//div[@class='ui fluid search selection dropdown']//div[@class='text' and text()='American Samoa']"));
	}

	@AfterTest
	public void afterTest() {
		// check commit
		driver.quit();
	}

	/* =============================common function==================== */
	// chọn item trong dropdownlist có thẻ input
	public void inputTextInDropdownList(String locator, String inputXpath, String expectedText) {
		// 1- Click vào thẻ chứa dropdownlist
		driver.findElement(By.xpath(locator)).click();
		// 2- Dùng hàm senkey để nhập text vào thẻ input
		senkeys(inputXpath, expectedText);
		// 3- truyền phím enter vào input text
		action.sendKeys(driver.findElement(By.xpath(inputXpath)), Keys.ENTER).perform();
		;

	}

	public void selectItemInCustomDropdown(String parentDropdown, By locator, String text) {

		// 1- click vào thẻ chứa dropdown list
		driver.findElement(By.xpath(parentDropdown)).click();

		// 2- Khai báo một list WebElement chứa tất cả các element bên trong
		List<WebElement> allItems = driver.findElements(locator);

		// 3-wait cho tất cả item được xuất hiện trong DOM
		waitExplicit.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));

		// 4-get text từng item và sau đó ss với từng item mình cần chọn
		for (WebElement item : allItems) {
			System.out.println("cac item đã được đưa vào trong list: " + item.getText());

			// 5-Kiểm tra item nào đúng với cái mình chọn thì click vào
			if (item.getText().equals(text)) {
				item.click();
				break;
			}
		}
	}

	public boolean checkElementIsDisplayed(String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		if (element.isDisplayed()) {
			return true;
		} else
			return false;
	}

	public String elementGetText(String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		return element.getText();
	}

	public String getTextByJS(String locator) {
		return (String) javaScript.executeScript("return document.querySelector('" + locator + "').text");
	}

	// senkeys to an input tag
	public void senkeys(String locator, String key) {
		WebElement element = driver.findElement(By.xpath(locator));
		element.sendKeys(key);
	}
}