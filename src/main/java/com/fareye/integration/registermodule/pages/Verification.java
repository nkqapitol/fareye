package com.fareye.integration.registermodule.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Verification {

	@FindBy(xpath = "//center")
	private WebElement txtInstructions;
	
	private WebDriver driver;
	
	public Verification(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
	}

	public WebElement getTxtInstructions() {
		return txtInstructions;
	}
		
}