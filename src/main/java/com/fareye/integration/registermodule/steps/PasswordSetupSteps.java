package com.fareye.integration.registermodule.steps;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.fareye.integration.registermodule.pages.PasswordSetup;
import com.fareye.integration.registermodule.pages.Verification;
import com.fareye.qa.core.util.PageFunctions;

import io.qameta.allure.Step;

public class PasswordSetupSteps {

	private volatile static PasswordSetupSteps passwordSetupSteps = null;
	private PasswordSetup passwordSetup;
	private final PageFunctions pageFunctions;

	private PasswordSetupSteps(WebDriver driver) {
		pageFunctions = PageFunctions.getInstance();
		passwordSetup = new PasswordSetup(driver);
	}
	
	/**
     * Returns instance of RegisterCarrierSteps type
     * 
     * @author Harsh Sengar
     */
	public static PasswordSetupSteps getInstance(WebDriver driver) {
		if (passwordSetupSteps == null) {
			synchronized (PasswordSetupSteps.class) {
	            if (passwordSetupSteps == null)passwordSetupSteps = new PasswordSetupSteps(driver);
	        }
	    }
	    return passwordSetupSteps;
	}
	
	/**
     * Get FarEyeLogo
     * 
     * @author Harsh Sengar
     */
	@Step("Get FarEyeLogo")
	public WebElement getLogo() {
		return passwordSetup.getLogoFarEye();
	}
	
	/**
     * Get First Name Label
     * 
     * @author Harsh Sengar
     */
	@Step("Get First Name Label")
	public WebElement getFirstNameLabel() {
		return passwordSetup.getLblFirstName();
	}
	
	/**
     * Get Password Meta Information
     * 
     * @author Harsh Sengar
     */
	@Step("Get Password Meta Information")
	public WebElement getPwdMetaInformationLabel() {
		return passwordSetup.getLblPwdMetaInformation();
	}
	
	/**
     * Get Password Input field
     * 
     * @author Harsh Sengar
     */
	@Step("Get Password Input field")
	public WebElement getPasswordInput() {
		return passwordSetup.getInpPassword();
	}
	
	/**
     * Input value in Password field
     * 
     * @param password
     * @author Harsh Sengar
     */
	@Step("Enter Password {password}")
	public void setPassword(String password) {
		passwordSetup.getInpPassword().sendKeys(password);
	}
	
	/**
     * Get Confirm Password Input field
     * 
     * @author Harsh Sengar
     */
	@Step("Get Confirm Password Input field")
	public WebElement getConfirmPasswordInput() {
		return passwordSetup.getInpConfirmPassword();
	}
	
	/**
     * Input value in Confirm Password field
     * 
     * @param companyName
     * @author Harsh Sengar
     */
	@Step("Enter Confirm Password {password}")
	public void setConfirmPassword(String password) {
		passwordSetup.getInpConfirmPassword().sendKeys(password);
	}
	
	/**
     * Get Password Help Label
     * 
     * @author Harsh Sengar
     */
	@Step("Get Password Help Label")
	public WebElement getPasswordHelpLabel() {
		return passwordSetup.getLblPasswordHelp();
	}
	
	/**
     * Get Confirm Password Help Label
     * 
     * @author Harsh Sengar
     */
	@Step("Get Confirm Password Help Label")
	public WebElement getConfirmPasswordHelpLabel() {
		return passwordSetup.getLblConfirmPasswordHelp();
	}
	
	/**
     * Get Verify Account Button
     * 
     * @author Harsh Sengar
     */
	@Step("Get Verify Account Button")
	public WebElement getVerifyAccountButton() {
		return passwordSetup.getBtnVerifyAccount();
	}
	
	/**
     * Click Verify Account
     * 
     * @author Harsh Sengar
     */
	@Step("Click Verify Account")
	public void clickVerifyAccount() {
		passwordSetup.getBtnVerifyAccount().click();
	}
}