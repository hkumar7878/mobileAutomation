package tests;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class BaseClass extends ExtentReportDemo{
	AppiumDriver<MobileElement> driver;
	
	
	@BeforeTest
	public void setUp() throws MalformedURLException
	{
		try {
			DesiredCapabilities cap= new DesiredCapabilities();
			cap.setCapability("deviceName", "Galaxy J7 Max");
			cap.setCapability("uuid", "42003a0fd3148479");
			//cap.setCapability("platformName", "Android");
			cap.setCapability(CapabilityType.PLATFORM_NAME, "Android");
			cap.setCapability("platformVersion", "8.1.0");
			//cap.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 60);
			//cap.setCapability(MobileCapabilityType.APP, 60);
			
			cap.setCapability("appPackage", "com.sec.android.app.popupcalculator");
			cap.setCapability("appActivity", "com.sec.android.app.popupcalculator.Calculator");
			URL url= new URL("http://127.0.0.1:4723/wd/hub");
			driver= new AppiumDriver<MobileElement>(url,cap);
			//driver= new AndroidDriver<MobileElement>(url,cap);
			//driver= new IOSDriver<MobileElement>(url,cap);
			System.out.println("Application started");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Cause is "+ e.getCause());
			e.printStackTrace();
		}
	}
	@AfterTest
	public void tearDown()
	{
		driver.quit();
	}

}
