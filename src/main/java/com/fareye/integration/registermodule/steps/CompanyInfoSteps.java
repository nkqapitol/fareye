package com.fareye.integration.registermodule.steps;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.fareye.integration.registermodule.pages.CompanyInfo;
import com.fareye.qa.core.util.PageFunctions;

import io.qameta.allure.Step;

public class CompanyInfoSteps {

	private volatile static CompanyInfoSteps companyinfoSteps = null;
	private CompanyInfo companyInfo;
	private final PageFunctions pageFunctions;
		
	private CompanyInfoSteps(WebDriver driver) {
		pageFunctions = PageFunctions.getInstance();
		companyInfo = new CompanyInfo(driver);
	}
	
	/**
     * Returns instance of CompanyInfoSteps type
     * 
     * @author Harsh Sengar
     */
	public static CompanyInfoSteps getInstance(WebDriver driver) {
		if (companyinfoSteps == null) {
			synchronized (CompanyInfoSteps.class) {
	            if (companyinfoSteps == null) companyinfoSteps = new CompanyInfoSteps(driver);
	        }
	    }
	    return companyinfoSteps;
	}
	
	/**
     * Waiting
     * 
     * @author Harsh Sengar
     */
	@Step("Waiting")
	public void pause(double time) {
		pageFunctions.timeToWaitUntil(time);
	}
	
	/**
     * Get Company Info Step
     * 
     * @author Harsh Sengar
     */
	@Step("Get Company Info Step")
	public String getCompanyInfoStep() {
		return companyInfo.getLblCompanyInfoStep().getText();
	}
	
	/**
     * Get User Info Step
     * 
     * @author Harsh Sengar
     */
	@Step("Get User Info Step")
	public String getUserInfoStep() {
		return companyInfo.getLblUserInfoStep().getText();
	}
	
	/**
     * Get User Info Step
     * 
     * @author Harsh Sengar
     */
	@Step("Get Verification Step")
	public String getVerificationStep() {
		return companyInfo.getLblVerificationStep().getText();
	}
	
	/**
     * Get FarEye logo attribute
     * 
     * @author Harsh Sengar
     */
	@Step("Get FarEye Logo Attribute")
	public String getFarEyeLogo() {
		return companyInfo.getLogoFarEye().getAttribute("alt");
	}
	
	/**
     * Get Company Info Label
     * 
     * @author Harsh Sengar
     */
	@Step("Get Company Info Label")
	public String getCompanyInfoLabel() {
		return companyInfo.getLblCompanyInfo().getText();
	}
	
	/**
     * Get Scac No/VAT No field
     * 
     * @author Harsh Sengar
     */
	@Step("Get Scac No/VAT No field")
	public WebElement getScacNoVATNo() {
		return companyInfo.getInpSCACNoVatNo();
	}
	
	/**
     * Input value in Scac No/VAT No field
     * 
     * @param scacvatNo
     * @author Harsh Sengar
     */
	@Step("Enter Scac No {scacvatNo}")
	public void setScacNoVATNo(String scacvatNo) {
		 companyInfo.getInpSCACNoVatNo().sendKeys(scacvatNo);
	}
	
	/**
     * Get Scac No field
     * 
     * @author Harsh Sengar
     */
	@Step("Get Scac No field")
	public WebElement getScacNo() {
		return companyInfo.getInpSCACNo();
	}
	
    /**
     * Input value in Scac No field
     * 
     * @param scacNo
     * @author Harsh Sengar
     */
	@Step("Enter Scac No {scacNo}")
	public void setScacNo(String scacNo) {
		 companyInfo.getInpSCACNo().sendKeys(scacNo);
	}
	
	/**
     * Get DOT No field
     * 
     * @author Harsh Sengar
     */
	@Step("Get DOT No field")
	public WebElement getDOTNo() {
		return companyInfo.getInpDOTNo();
	}
	
	/**
     * Input value in DOT No field
     * 
     * @param dotNo
     * @author Harsh Sengar
     */
	@Step("Enter DOT No {dotNo}")
	public void setDotNo(String dotNo) {
		companyInfo.getInpDOTNo().sendKeys(dotNo);
	}
	
	/**
     * Get Company Name field
     * 
     * @author Harsh Sengar
     */
	@Step("Get Company Name field")
	public WebElement getCompanyName() {
		return companyInfo.getInpCompanyName();
	}
	
	/**
     * Input value in Company Name field
     * 
     * @param companyName
     * @author Harsh Sengar
     */
	@Step("Enter Company Name {companyName}")
	public void setCompanyName(String companyName) {
		companyInfo.getInpCompanyName().sendKeys(companyName);
	}
	
	/**
     * Get Address Line 1 field
     * 
     * @author Harsh Sengar
     */
	@Step("Get Address Line 1 field")
	public WebElement getAddressLine() {
		return companyInfo.getInpAddressLine();
	}
	
	/**
     * Input value in Address Line 1 field
     * 
     * @param addressLine
     * @author Harsh Sengar
     */
	@Step("Enter Address Line 1 Name {addressLine}")
	public void setAddressLine(String addressLine) {
		companyInfo.getInpAddressLine().sendKeys(addressLine);
	}
	
	/**
     * Get Zipcode field
     * 
     * @author Harsh Sengar
     */
	@Step("Get Zipcode field")
	public WebElement getZipCode() {
		return companyInfo.getInpZipcode();
	}
	
	/**
     * Input value in Zipcode field
     * 
     * @param zipcode
     * @author Harsh Sengar
     */
	@Step("Enter Address Line 1 Name {zipcode}")
	public void setZipCode(String zipcode) {
		companyInfo.getInpZipcode().sendKeys(zipcode);
	}
	
	/**
     * Get Country field
     * 
     * @author Harsh Sengar
     */
	@Step("Get Country field")
	public WebElement getCountry() {
		return companyInfo.getInpSelectCountry();
	}
	
	/**
     * Select Country from Country dropdown
     * 
     * @param country
     * @author Harsh Sengar
     */
	@Step("Select Country {country}")
	public void setCountry(String country) {
		companyInfo.getInpSelectCountry().sendKeys(country);
		selectDropdownValue(companyInfo.getDrpdwnSelect(),country);
	}
	
	/**
     * Get State field
     * 
     * @author Harsh Sengar
     */
	@Step("Get State field")
	public WebElement getState() {
		return companyInfo.getInpSelectState();
	}
	
	/**
     * Select State from State dropdown
     * 
     * @param state
     * @author Harsh Sengar
     */
	@Step("Select State {state}")
	public void setState(String state) {
		companyInfo.getInpSelectState().sendKeys(state);
		selectDropdownValue(companyInfo.getDrpdwnSelect(),state);
	}
	
	/**
     * Get City field
     * 
     * @author Harsh Sengar
     */
	@Step("Get City field")
	public WebElement getCity() {
		return companyInfo.getInpSelectCity();
	}
	
	/**
     * Select City from City dropdown
     * 
     * @param city
     * @author Harsh Sengar
     */
	@Step("Select City {city}")
	public void setCity(String city) {
		companyInfo.getInpSelectCity().sendKeys(city);
		selectDropdownValue(companyInfo.getDrpdwnSelect(),city);
	}
	
	/**
     * Get State is required warning
     * 
     * @author Harsh Sengar
     */
	@Step("Get State is required warning")
	public WebElement getStateRequiredWarning() {
		return companyInfo.getLblStateRequiredWarning();
	}
	
	/**
     * Get City is required warning
     * 
     * @author Harsh Sengar
     */
	@Step("Get City is required warning")
	public WebElement getCityRequiredWarning() {
		return companyInfo.getLblCityRequiredWarning();
	}
	
	/**
     * Click Next
     * 
     * @author Harsh Sengar
     */
	@Step("Click Next")
	public void clickNext() {
		companyInfo.getBtnNext().click();
	}
	
	/**
     * Get Scac No already exists warning
     * 
     * @author Harsh Sengar
     */
	@Step("Get Scac No already exists warning")
	public WebElement getScacNoAlreadyExistsWarning1() {
		return companyInfo.getLblSCACNoWarning1();
	}
	
	/**
     * Get Scac No already exists warning
     * 
     * @author Harsh Sengar
     */
	@Step("Get Scac No already exists warning")
	public WebElement getScacNoAlreadyExistsWarning2() {
		return companyInfo.getLblSCACNoWarning2();
	}
	
	/**
     * Get Organization Already Exists warning
     * 
     * @author Harsh Sengar
     */
	@Step("Get Organization Already Exists warning")
	public WebElement getOrgAlreadyExistsWarning() {
		return companyInfo.getLblOrgExistsNoWarning();
	}
	
	
	private void selectDropdownValue(List<WebElement> listWebElement,String value) {
		
		for(WebElement el:listWebElement) {
			if(el.getText().endsWith(value))el.click();
		}
	}
}
