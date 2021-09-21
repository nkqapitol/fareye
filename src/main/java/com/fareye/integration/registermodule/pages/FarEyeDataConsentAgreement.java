package com.fareye.integration.registermodule.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FarEyeDataConsentAgreement {

	@FindBy(xpath = "//button[contains(@class,'green')]")
	private WebElement btnDownload;
	
	private WebDriver driver;
	
	public FarEyeDataConsentAgreement(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
	}

	public WebElement getBtnDownload() {
		return btnDownload;
	}	
}