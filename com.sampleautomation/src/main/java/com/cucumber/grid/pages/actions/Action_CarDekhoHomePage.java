package com.cucumber.grid.pages.actions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

import com.cucumber.grid.pages.locators.LocatorsCarDekho_HomePage;
//import com.cucumber.parallel.baseSteps.steps.BaseSteps;
import com.cucumber.parallel.baseSteps.steps.BaseSteps;

public class Action_CarDekhoHomePage extends BaseSteps{
	
	LocatorsCarDekho_HomePage obj_locatorsCarDekho_HomePage=new LocatorsCarDekho_HomePage();
	
	public Action_CarDekhoHomePage()
	{
		this.obj_locatorsCarDekho_HomePage= new LocatorsCarDekho_HomePage();
		PageFactory.initElements(BaseSteps.getDriver(), obj_locatorsCarDekho_HomePage);
	}
	
	
	public void moveToNewCarMenu()
	{
		try{
		System.out.println(getDriver());
		Actions action = new Actions(getDriver());
		action.moveToElement(obj_locatorsCarDekho_HomePage.newCarMenu).perform();
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
		obj_locatorsCarDekho_HomePage.searchNewCarLink.click();
	}

}
