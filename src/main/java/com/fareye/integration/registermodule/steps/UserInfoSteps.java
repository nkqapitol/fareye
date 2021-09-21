package com.fareye.integration.registermodule.steps;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.fareye.integration.registermodule.pages.UserInfo;
import com.fareye.qa.core.util.PageFunctions;

import io.qameta.allure.Step;

public class UserInfoSteps {

	private volatile static UserInfoSteps userinfoSteps = null;
	private UserInfo userInfo;
	private final PageFunctions pageFunctions;

	private UserInfoSteps(WebDriver driver) {
		pageFunctions = PageFunctions.getInstance();
		userInfo = new UserInfo(driver);
	}
	
	/**
     * Returns instance of RegisterCarrierSteps type
     * 
     * @author Harsh Sengar
     */
	public static UserInfoSteps getInstance(WebDriver driver) {
		if (userinfoSteps == null) {
			synchronized (UserInfoSteps.class) {
	            if (userinfoSteps == null)userinfoSteps = new UserInfoSteps(driver);
	        }
	    }
	    return userinfoSteps;
	}
	
	/**
     * Get Email Address field
     * 
     * @author Harsh Sengar
     */
	@Step("Get Email Address field")
	public WebElement getEmailAddress() {
		return userInfo.getInpEmailAddress();
	}
	
    /**
     * Input value in Email Address field
     * 
     * @param emailAddress
     * @author Harsh Sengar
     */
	@Step("Enter Email Address {emailAddress}")
	public void setEmailAddress(String emailAddress) {
		userInfo.getInpEmailAddress().sendKeys(emailAddress);
	}
	
	/**
     * Get Country Selection field
     * 
     * @author Harsh Sengar
     */
	@Step("Get Country Code field")
	public WebElement getCountryCode() {
		return userInfo.getDivSelectCountry();
	}
	
	/**
     * Get Country Code Input field
     * 
     * @author Harsh Sengar
     */
	@Step("Get Country Code Input field")
	public WebElement getCountryCodeInput() {
		return userInfo.getInpCountry();
	}
	
    /**
     * Input value in Country Code Input field
     * 
     * @param country
     * @author Harsh Sengar
     */
	@Step("Enter Country Code {country}")
	public void setCountryCodeInput(String country) {
		userInfo.getInpCountry().sendKeys(country);
	}
	
	/**
     * Select Country from dropdown field
     * 
     * @param country
     * @author Harsh Sengar
     */
	@Step("Select Country from dropdown field")
	public void selectCountryFromDropdown(String country) {
		 selectDropdownValue(userInfo.getDrpdwnSelectCountry(),country);
	}
	
	/**
     * Get Phone No field
     * 
     * @author Harsh Sengar
     */
	@Step("Get Phone No field")
	public WebElement getPhoneNo() {
		return userInfo.getInpPhoneNo();
	}
	
	/**
     * Input value in Phone No Input field
     * 
     * @param phone
     * @author Harsh Sengar
     */
	@Step("Enter Phone No {phone}")
	public void setPhoneNo(String phone) {
		userInfo.getInpPhoneNo().sendKeys(phone);
	}
	
	/**
     * Get First Name field
     * 
     * @author Harsh Sengar
     */
	@Step("Get First Name field")
	public WebElement getFirstName() {
		return userInfo.getInpFirstName();
	}
	
	/**
     * Input value in First Name field
     * 
     * @param firstName
     * @author Harsh Sengar
     */
	@Step("Enter First Name {firstName}")
	public void setFirstName(String firstName) {
		userInfo.getInpFirstName().sendKeys(firstName);
	}
	
	/**
     * Get Last Name field
     * 
     * @author Harsh Sengar
     */
	@Step("Get First Last field")
	public WebElement getLastName() {
		return userInfo.getInpLastName();
	}
	
	/**
     * Input value in Last Name field
     * 
     * @param lastName
     * @author Harsh Sengar
     */
	@Step("Enter Last Name {lastName}")
	public void setLastName(String lastName) {
		userInfo.getInpLastName().sendKeys(lastName);
	}
	
	/**
     * Get CheckBox Agreement field
     * 
     * @author Harsh Sengar
     */
	@Step("Get CheckBox Agreement field")
	public WebElement getCheckBox() {
		return userInfo.getChkboxAgreement();
	}
	
	/**
     * Set CheckBox Agreement field
     * 
     * @author Harsh Sengar
     */
	@Step("Select Checkbox Agreement")
	public void setCheckBox() {
		userInfo.getChkboxAgreement().click();
	}
	
	/**
     * Get Agreement link
     * 
     * @author Harsh Sengar
     */
	@Step("Get Agreement link")
	public WebElement getAgreementLink() {
		return userInfo.getLnkAgreement();
	}
	
	/**
     * Click Agreement link
     * 
     * @author Harsh Sengar
     */
	@Step("Click Agreement link")
	public void clickAgreementLink() {
		userInfo.getLnkAgreement().click();
	}
	
	/**
     * Click Go Back
     * 
     * @author Harsh Sengar
     */
	@Step("Click Go Back")
	public void clickGoBack() {
		userInfo.getBtnGoBack().click();
	}
	
	/**
     * Click Submit
     * 
     * @author Harsh Sengar
     */
	@Step("Click Submit")
	public void clickSubmit() {
		userInfo.getBtnSubmit().click();
	}
	
	/**
     * Get Email is required warning
     * 
     * @author Harsh Sengar
     */
	@Step("Get Email is required warning")
	public WebElement getEmailIsRequiredWarning1() {
		return userInfo.getLblEmailAddressRequiredWarning1();
	}
	
	/**
     * Get Email is required warning
     * 
     * @author Harsh Sengar
     */
	@Step("Get Email is required warning")
	public WebElement getEmailIsRequiredWarning2() {
		return userInfo.getLblEmailAddressRequiredWarning2();
	}
	
	/**
     * Get Phone No is required warning
     * 
     * @author Harsh Sengar
     */
	@Step("Get Phone No is required warning")
	public WebElement getPhoneNoIsRequiredWarning1() {
		return userInfo.getLblPhoneNumRequiredWarning1();
	}
	
	/**
     * Get Phone No is required warning
     * 
     * @author Harsh Sengar
     */
	@Step("Get Phone No is required warning")
	public WebElement getPhoneNoIsRequiredWarning2() {
		return userInfo.getLblPhoneNumRequiredWarning2();
	}
	
	/**
     * Get First Name is required warning
     * 
     * @author Harsh Sengar
     */
	@Step("Get First Name is required warning")
	public WebElement getFirstNameIsRequiredWarning1() {
		return userInfo.getLblFirstNameRequiredWarning1();
	}
	
	/**
     * Get First Name is required warning
     * 
     * @author Harsh Sengar
     */
	@Step("Get First Name is required warning")
	public WebElement getFirstNameIsRequiredWarning2() {
		return userInfo.getLblFirstNameRequiredWarning2();
	}
	
	/**
     * Get Last Name is required warning
     * 
     * @author Harsh Sengar
     */
	@Step("Get Last Name is required warning")
	public WebElement getLastNameIsRequiredWarning1() {
		return userInfo.getLblLastNameRequiredWarning1();
	}
	
	/**
     * Get Last Name is required warning
     * 
     * @author Harsh Sengar
     */
	@Step("Get Last Name is required warning")
	public WebElement getLastNameIsRequiredWarning2() {
		return userInfo.getLblLastNameRequiredWarning2();
	}
	
	private void selectDropdownValue(List<WebElement> listWebElement,String value) {
		
		for(WebElement el:listWebElement) {
			if(el.getText().endsWith(value))el.click();
		}
	}
}
