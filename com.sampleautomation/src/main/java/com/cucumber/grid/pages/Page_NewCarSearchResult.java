package com.cucumber.grid.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class Page_NewCarSearchResult {
	
	@FindBy(how=How.XPATH,using="//input[@id='budget']")
	public WebElement dropDwn_budget;
	
	@FindBy(how=How.XPATH,using="//input[@id='vehicleTypeName']")
	public WebElement dropDwn_VehicleType;
	
	@FindBy(how=How.XPATH,using="(//button[text()='Search'])[1]")
	public WebElement btn_Search;

}
