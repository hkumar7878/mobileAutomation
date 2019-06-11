package tests;

import io.appium.java_client.MobileElement;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;

public class Tests extends BaseClass {
	
	ExtentTest test;
	
	@Test
	public void testOne() throws InterruptedException
	{
		test= extent.createTest("Test one");
		MobileElement one = driver.findElement(By.id("com.sec.android.app.popupcalculator:id/bt_01"));
		MobileElement plus=driver.findElement(By.id("com.sec.android.app.popupcalculator:id/bt_add"));
		plus.click();
		MobileElement three=driver.findElement(By.id("com.sec.android.app.popupcalculator:id/bt_03"));
		three.click();
		MobileElement equals=driver.findElement(By.id("com.sec.android.app.popupcalculator:id/bt_equal"));
		equals.click();
		MobileElement results=driver.findElement(By.id("com.sec.android.app.popupcalculator:id/txtCalc"));
		String result=results.getText();
		
		System.out.println("Result is ===>" +result);
		Thread.sleep(5000);
		one.click();
	}

}
