package com.cucumber.grid.pages.locators;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class LocatorsCarDekho_HomePage {

	@FindBy(how=How.XPATH,using="//span[text()='NEW CAR ']")
	public WebElement newCarMenu;
	
	@FindBy(how=How.XPATH,using="//span[text()='Search New Cars']")
	public WebElement searchNewCarLink;
	
}
