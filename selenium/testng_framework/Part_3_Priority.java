package testng_framework;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class Part_3_Priority {
	
  @Test(priority=1)
  public void Create_New_Customer() {
	  System.out.println("Run test 01");
  }
  
  @Test(priority=2)
  public void Edit_Customer() {
	  System.out.println("Run test 02");
  }
  
  @Test(priority=3)
  public void Create_New_Account() {
	  System.out.println("Run test 02");
  }
  
  @Test(priority=4)
  public void Edit_Account() {
	  System.out.println("Run test 02");
  }
  
  @Test(priority=5)
  public void Delete_Customer() {
	  System.out.println("Run test 02");
  }
  
  @Test(priority=6)
  public void Delete_Account() {
	  System.out.println("Run test 02");
  }

}
