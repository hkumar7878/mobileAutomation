package com.cucumber.parallel.baseSteps.steps;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.*;
//import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.log4testng.Logger;

import com.cucumber.grid.utilities.DriverFactory;
import com.cucumber.parallel.extent.ExtentTestManager;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class BaseSteps {
	
	public String browserName;
	DesiredCapabilities cap=null;
	public static Properties prop;
	protected WebDriver driver;
	public static ThreadLocal<WebDriver> dr = new ThreadLocal<WebDriver>();
	public static ThreadLocal<ExtentTest> exTest= new ThreadLocal<ExtentTest>(); 
	//public ThreadLocal<WebDriver> dr = new ThreadLocal<WebDriver>();
	//public ThreadLocal<ExtentTest> exTest= new ThreadLocal<ExtentTest>();
	public static ExtentTest test;
	public static String screenshotPath;
	public static String screenshotName;
	public static Logger logger = Logger.getLogger("devpinoyLogger");
	
	//public static Logger logger=Logger.getLogger("")

	public FileInputStream fis;
	private Properties Config = new Properties();
	public boolean gridExecution=false;
		
	
		public void setUpFramework()
		{
			DriverFactory.setGridPath("http://localhost:4444/wd/hub");
			DriverFactory.setConfigPropertyFile(System.getProperty("user.dir") + "//src//main//java//com//cucumber//grid//configuration//configuration.properties");
			DriverFactory.setChromeDriverExePath(System.getProperty("user.dir") + "//Browser Exes//chromedriver.exe");
			DriverFactory.setGeckoDriverExePath(System.getProperty("user.dir") + "//Browser Exes//geckodriver.exe");
			try {
				fis= new FileInputStream(DriverFactory.getConfigPropertyFile());
			} catch (FileNotFoundException e) {
				
				e.printStackTrace();
			}
			
			try {
				Config.load(fis);
				logger.info("Property file loaded!!!!");
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			configureLogging();
		}
		
		public void openBrowser(String browserType) {
				
				String browser=browserType;
				
				
				if(System.getenv("ExecutionType")!=null && System.getenv("ExecutionType").equalsIgnoreCase("Grid")){
					gridExecution=true;
				}
				DriverFactory.setRemote(false);
				if(DriverFactory.isRemote())		
				{		
					if (browserType.equalsIgnoreCase("firefox")) 
						{										
							System.out.println("Local Thread- Docker--Launching firefox browser");
							logger.info("Creating a object of Firefox Browser");
							//logger.info("Navigating to " + app_Url + "for Firefox browser");		
							cap = DesiredCapabilities.firefox();
							cap.setBrowserName("firefox");
							cap.setPlatform(Platform.ANY);													
						}			
						else if (browserType.equalsIgnoreCase("chrome")) 
						{	
							logger.info("Creating a object of Chrome Browser");
							System.out.println("Local Thread--Docker - Config- Launching Chrome browser......");			
							ChromeOptions chromeOptions = new ChromeOptions();
							chromeOptions.addArguments("disable-gpu");
							cap = DesiredCapabilities.chrome();
							cap.setCapability(ChromeOptions.CAPABILITY, chromeOptions);						
						}
						try {
							driver=new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),cap);
						} catch (MalformedURLException e) {
							e.printStackTrace();
						}
						logger.info("Starting the grid session");
				}
				
				else 
				{
					if (browserType.equalsIgnoreCase("chrome")) 
					{
						System.out.println("Launching Chrome browser");
						System.setProperty("webdriver.chrome.driver", DriverFactory.getChromeDriverExePath());
						driver= new ChromeDriver();	
						logger.info("Chrome browwer launched");
					}
					else if(browserType.equalsIgnoreCase("firefox")) 
					{
						System.out.println("Launching Firefox browser");
						FirefoxOptions firefox_options  = new FirefoxOptions();
						firefox_options.setCapability("marionette", true);
						System.setProperty("webdriver.gecko.driver", DriverFactory.getGeckoDriverExePath());
						driver= new FirefoxDriver(firefox_options);	
						logger.info("Firefox browser launched");
					}
						
				}
				setWebDriver(driver);
				getDriver().manage().window().maximize();
				System.out.println(dr.get());
				System.out.println("Application opened successfully for " + browser);
				System.out.println("Application opened successfully for " + browser);
				logger.info("inside the base class method browser initilization");
						
			} 
		
		public void configureLogging()
		{
			String log4jConfigFile=System.getProperty("user.dir") +"//src//test//resources//properties//log4j1.properties";
			PropertyConfigurator.configure(log4jConfigFile);
		}
		
		
		public static WebDriver getDriver() {
	        return dr.get();
	    }
		public static void setWebDriver(WebDriver driver){
			dr.set(driver);
		}
		
		public ExtentTest getExtTest() {
	        return exTest.get();
	    }
		
		public void setExtentTest(ExtentTest et){
			exTest.set(et);
		}	
		public void reportPass(String msg){
			getExtTest().log(LogStatus.PASS, msg);
		}	
		public void reportFailure(String msg){
			getExtTest().log(LogStatus.FAIL, msg);
			captureScreenshot();
			
		}
		
		public void captureScreenshot()  {

			File scrFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
			Date d = new Date();
			screenshotName = d.toString().replace(":", "_").replace(" ", "_") + ".jpg";
			try {
				FileUtils.copyFile(scrFile,new File(System.getProperty("user.dir") + "\\reports\\" + screenshotName));
			} catch (IOException e) {
		
			}
			getExtTest().log(LogStatus.INFO, " Screen shot--> " + test.addScreenCapture(System.getProperty("user.dir") + "\\reports\\"+screenshotName));

		}
		
		public void addLog(String msg,String browser1)
		{
			logger.debug("Running thread value is : " + getThreadValue(dr.get())+   "   for Browser : " + "  "+ browser1 + "" +msg);	
			logger.info("Browser : " + browser1 + "" +msg);
		}
		public void passInfo(String messaage)
		{
			ExtentTestManager.testReport.get().pass(messaage);
		}
		
		public void failInfo(String messaage)
		{
			ExtentTestManager.testReport.get().fail(messaage);
		}
		
		public String getThreadValue(Object value)
		{
			String text=value.toString();
			String [] nextText=text.split(" ");
			String text2=nextText[nextText.length-1].replace("(", "").replace(")","");
			String [] newText2=text2.split("-");	
			String reqText=newText2[newText2.length-1];
			System.out.println("Thread value is " + reqText);
			
			return reqText;
		}
		public void logInfo(String messaage)
		{
			try{
				ExtentTestManager.testReport.get().info(messaage);
			}
			catch(Exception e){
				System.out.println("Error is" + e.getMessage());
			}
		}
		
		public void quitWebDriver()
		{
			getDriver().quit();
		}
	}

