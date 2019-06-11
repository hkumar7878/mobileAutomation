package com.sampleautomation;

import java.net.MalformedURLException;
import java.net.URL;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;

public class TC_01_SampleTest {
	
	AppiumDriver<MobileElement> driver;
	ExtentTest test;
	
	@Test
	public void basicTest() throws MalformedURLException, InterruptedException
	{
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability("deviceName", "Galaxy J7 Max");
		cap.setCapability("uuid", "42003a0fd3148479");
		cap.setCapability("platformName", "Android");
		cap.setCapability("platformVersion", "8.1.0");
		cap.setCapability("appPackage", "com.sec.android.app.popupcalculator");
		cap.setCapability("appActivity", "com.sec.android.app.popupcalculator.Calculator");
		URL url= new URL("http://127.0.0.1:4723/wd/hub");
		driver= new AppiumDriver<MobileElement>(url,cap);
		System.out.println("Application started");
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

		driver.quit();
	}

}
