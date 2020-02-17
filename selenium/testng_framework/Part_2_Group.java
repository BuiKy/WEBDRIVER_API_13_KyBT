package testng_framework;

import org.testng.annotations.Test;
public class Part_2_Group {
	
  @Test(groups = "user")
  public void TC_01() {
	  System.out.println("Run test 01");
  }
  
  @Test(groups = "user")
  public void TC_02() {
	  System.out.println("Run test 02");
  }
  
  @Test(groups = "pay")
  public void TC_03() {
	  System.out.println("Run test 03");
  }
  
  @Test(groups = "pay")
  public void TC_04() {
	  System.out.println("Run test 02");
  }
  
  @Test(groups = "shop")
  public void TC_05() {
	  System.out.println("Run test 02");
  }
  
  @Test(groups = "shop")
  public void TC_06() {
	  System.out.println("Run test 02");
  }
  
}
