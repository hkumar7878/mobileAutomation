package com.cucumber.grid.pages;


import org.openqa.selenium.interactions.Actions;
import utils.SeleniumDriver;

public class Page_Home_CarDekho {
	
	
	
	public void moveToNewCarMenu()
	{
		try{
		System.out.println(SeleniumDriver.getDriver());
		Actions action = new Actions(SeleniumDriver.getDriver());
		//action.moveToElement(obj_locatorsCarDekho_HomePage.newCarMenu).perform();
		try {
			Thread.sleep(4000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Inside move to new car menu");
		}
		
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	public void clickSearchNewCarLink()
	{
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//obj_locatorsCarDekho_HomePage.searchNewCarLink.click();
	}

}
