package com.fareye.integration.registermodule.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PasswordSetup {

	@FindBy(xpath = "//img[@alt='App Logo']")
	private WebElement logoFarEye;
	
	@FindBy(xpath = "//input[@id='user_password']//preceding::span")
	private WebElement lblFirstName;	
	
	@FindBy(xpath = "//input[@id='user_password']//preceding::div/..")
	private WebElement lblPwdMetaInformation;
	
	@FindBy(id = "user_password")
	private WebElement inpPassword;
	
	@FindBy(id = "confirm_user_password")
	private WebElement inpConfirmPassword;
	
	@FindBy(id = "passwordHelp")
	private WebElement lblPasswordHelp;
	
	@FindBy(id = "confirmUserPasswordHelp")
	private WebElement lblConfirmPasswordHelp;
	
	@FindBy(id = "submit")
	private WebElement btnVerifyAccount;
	
	private WebDriver driver;
	
	public PasswordSetup(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
	}

	public WebElement getLogoFarEye() {
		return logoFarEye;
	}

	public WebElement getLblFirstName() {
		return lblFirstName;
	}

	public WebElement getLblPwdMetaInformation() {
		return lblPwdMetaInformation;
	}

	public WebElement getInpPassword() {
		return inpPassword;
	}

	public WebElement getInpConfirmPassword() {
		return inpConfirmPassword;
	}

	public WebElement getLblPasswordHelp() {
		return lblPasswordHelp;
	}

	public WebElement getLblConfirmPasswordHelp() {
		return lblConfirmPasswordHelp;
	}

	public WebElement getBtnVerifyAccount() {
		return btnVerifyAccount;
	}	
}