package webdriver_api;

import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class Topic_09_UserInteraction {
	WebDriver driver;
	Actions action;
	JavascriptExecutor je;

	@BeforeClass
	public void beforeClass() {

//		  làm mất cái profile khi get 1 page
		// capability (config browser)
		FirefoxProfile profile = new FirefoxProfile();
		profile.setPreference("dom.webnofitications.enabled", false);
		driver = new FirefoxDriver(profile);

//		System.setProperty("Webdriver.chrome.driver", ".\\libraries\\chromedriver.exe");
//		driver = new ChromeDriver();
		
		action = new Actions(driver);
		je = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	public void TC_01_MoveMouseToElement() {
		// hover
		driver.get("http://www.myntra.com/");

		action.moveToElement(driver.findElement(By.xpath("//div[@class='desktop-navContent']/div/a[text()='Discover']"))).perform();
		driver.findElement(By.xpath("//a[@class='desktop-categoryLink' and text()='GAP']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='title-container']/h1[@class='title-title' and text()='GAP Collection']")).isDisplayed());

		action.moveToElement(driver.findElement(By.xpath("//div[@class='desktop-navContent']/div/a[text()='Discover']"))).perform();
		driver.findElement(By.xpath("//a[@class='desktop-categoryLink' and text()='Adidas']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='title-container']/h1[@class='title-title' and text()='ADIDAS']")).isDisplayed());

	}

	public void TC_02_ClickAndHold() throws InterruptedException {
		driver.get("http://jqueryui.com/resources/demos/selectable/display-grid.html");

		List<WebElement> listItem = driver.findElements(By.xpath("//ol[@id='selectable']/li"));
		Actions moveItem = new Actions(driver);
		/*
		 * 
		 * chọn một list liên tục
		 * moveItem.clickAndHold(listItem.get(0)).moveToElement(listItem.get(3)).release
		 * ().perform();
		 */
		// chọn ngẫu nhiên các item
		action.keyDown(Keys.CONTROL).perform();
		listItem.get(0).click();
		listItem.get(5).click();
		listItem.get(3).click();
		action.keyUp(Keys.CONTROL).perform();
		Thread.sleep(3000);
		// kiem tra xem da chon duoc thanh cong
		List<WebElement> selectedItem = driver.findElements(By.xpath("//ol[@id='selectable']/li[contains(@class,'ui-selected')]"));
		for (WebElement item : selectedItem) {
			System.out.println("number duoc chon: " + item.getText());
		}
		Assert.assertEquals(selectedItem.size(), 3);

	}

	public void TC_03_DoubleClick() throws InterruptedException {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		WebElement buttonDouble = driver.findElement(By.xpath("//button[text()='Double click me']"));
		action = new Actions(driver);
		action.doubleClick(buttonDouble).perform();
		Assert.assertTrue(driver.findElement(By.xpath("//p[@id='demo' and text()='Hello Automation Guys!']")).isDisplayed());

	}

	 
	public void TC_04_RightClick() throws InterruptedException {
		driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
		
		WebElement buttonRightClcik = driver.findElement(By.xpath("//span[text()='right click me']"));

		action.contextClick(buttonRightClcik).perform();

		action.moveToElement(driver.findElement(By.xpath("//span[text()='Quit']"))).perform();
		
		Assert.assertTrue(driver.findElement(By.xpath("//li[contains(@class,'context-menu-visible') and contains(@class,'context-menu-hover')]/span[text()='Quit']")).isDisplayed());

 		action.click(driver.findElement(By.xpath("//span[text()='Quit']"))).perform();
		
		Assert.assertEquals(driver.switchTo().alert().getText(), "clicked: quit");
		driver.switchTo().alert().accept();
	}

	@Test
	public void TC_05_DragAndDrop() throws InterruptedException {

//		driver.get("http://demos.telerik.com/kendo-ui/dragdrop/angular");
//
//		WebElement targetDrop = driver.findElement(By.xpath("//div[@id='droptarget']"));
//		WebElement elementDrag = driver.findElement(By.xpath("//div[@id='draggable']"));
//
//		je.executeScript("arguments[0].scrollIntoView(true)", elementDrag);
//		Thread.sleep(5000);
//		
//		action.dragAndDrop(elementDrag, targetDrop).perform();
//		Thread.sleep(5000);
//
//		System.out.println(driver.findElement(By.xpath("//div[@id='droptarget']")).getText());
//		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='droptarget']")).getText(), "You did great!");

		//Step 01
		driver.get("http://demos.telerik.com/kendo-ui/dragdrop/angular");
		// Step 02
		WebElement sourceCircle = driver.findElement(By.xpath("//div[@id='droptarget']"));
		WebElement targetCircle = driver.findElement(By.xpath("//div[@id='draggable']"));
		// action.dragAndDrop(findByXpath("//div[@id='draggable']"),
		// findByXpath("//div[@id='droptarget']")).perform();
		action.dragAndDrop(sourceCircle, targetCircle).perform();
		Thread.sleep(3000);
		// Step 03
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='droptarget']")).getText(), "You did great!");
	}

	
	public void TC_06_DragAndDrop_HTML5() throws InterruptedException {
		driver.get("http://the-internet.herokuapp.com/drag_and_drop");

		WebElement elementDrop = driver.findElement(By.xpath("//div[@id='column-a']"));
		WebElement elementDrag = driver.findElement(By.xpath("//div[@id='column-b']"));

		action.dragAndDrop(elementDrag, elementDrop).perform();
		System.out.println(driver.findElement(By.xpath("//div[@id='column-a'']")).getText());
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='column-a']")).getText(), "A");
	}
	@AfterClass
	public void afterClass() {
		// check commit
		driver.quit();
	}

}
