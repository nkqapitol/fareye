package com.fareye.integration.registermodule.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CompanyInfo {
	
	@FindBy(xpath = "//span[contains(text(),'1')]/following-sibling::div[contains(text(),'Company Info')]")
	private WebElement lblCompanyInfoStep;
	
	@FindBy(xpath = "//span[contains(text(),'2')]/following-sibling::div[contains(text(),'User Info')]")
	private WebElement lblUserInfoStep;

	@FindBy(xpath = "//span[contains(text(),'3')]/following-sibling::div[contains(text(),'Verification')]")
	private WebElement lblVerificationStep;
	
	@FindBy(xpath = "//img[@alt='FarEye Logo']")
	private WebElement logoFarEye;
	
	@FindBy(xpath = "//h2[text()='Company Info']")
	private WebElement lblCompanyInfo;
	
	@FindBy(xpath = "//label[contains(text(),'SCAC No./VAT No.')]//following-sibling::input")
	private WebElement inpSCACNoVatNo;
	
	@FindBy(xpath = "//label[contains(text(),'SCAC No')]//following-sibling::input")
	private WebElement inpSCACNo;
	
	@FindBy(xpath = "//label[contains(text(),'DOT No')]//following-sibling::input")
	private WebElement inpDOTNo;
	
	@FindBy(xpath = "//label[contains(text(),'Company Name')]//following-sibling::input")
	private WebElement inpCompanyName;
	
	@FindBy(xpath = "//label[contains(text(),'Address Line 1')]//following-sibling::input")
	private WebElement inpAddressLine;
	
	@FindBy(xpath = "//label[contains(text(),'Zipcode')]//following-sibling::input")
	private WebElement inpZipcode;
	
	@FindBy(xpath = "//label[contains(text(),'Select Country')]//following-sibling::input[@type='text']")
	private WebElement inpSelectCountry;
	
	@FindBy(xpath = "//label[contains(text(),'Select State')]//following-sibling::input[@type='text']")
	private WebElement inpSelectState;
	
	@FindBy(xpath = "//label[contains(text(),'Select City')]//following-sibling::input[@type='text']")
	private WebElement inpSelectCity;
	
	@FindBy(xpath = "//div[@role='listbox']//span[contains(@class,'mask')]//parent::div")
	private List<WebElement> drpdwnSelect;
	
	@FindBy(xpath = "//span[contains(text(),'Next')]//parent::button")
	private WebElement btnNext;
	
	@FindBy(xpath = "//div[text()='State is required']")
	private WebElement lblStateRequiredWarning;
	
	@FindBy(xpath = "//div[text()='City is required']")
	private WebElement lblCityRequiredWarning;
	
	@FindBy(xpath = "//h2[text()='Company Info']//preceding::div[text()='Scac No already exists.']")
	private WebElement lblSCACNoWarning1;
	
	@FindBy(xpath = "//h2[text()='Company Info']//following::div[text()='Scac No already exists.']")
	private WebElement lblSCACNoWarning2;
	
	@FindBy(xpath = "//h2[text()='Company Info']//preceding::div[contains(text(),'Organization Already Exists.')]")
	private WebElement lblOrgExistsNoWarning;
	
	@FindBy(xpath = "//a[text()='Click here to Login']")
	private WebElement lnkLogin;
	
	private WebDriver driver;
	
	
	public CompanyInfo(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
	}


	public WebElement getLblCompanyInfoStep() {
		return lblCompanyInfoStep;
	}


	public WebElement getLblUserInfoStep() {
		return lblUserInfoStep;
	}


	public WebElement getLblVerificationStep() {
		return lblVerificationStep;
	}


	public WebElement getLogoFarEye() {
		return logoFarEye;
	}


	public WebElement getLblCompanyInfo() {
		return lblCompanyInfo;
	}


	public WebElement getInpSCACNoVatNo() {
		return inpSCACNoVatNo;
	}


	public WebElement getInpSCACNo() {
		return inpSCACNo;
	}


	public WebElement getInpDOTNo() {
		return inpDOTNo;
	}


	public WebElement getInpCompanyName() {
		return inpCompanyName;
	}


	public WebElement getInpAddressLine() {
		return inpAddressLine;
	}


	public WebElement getInpZipcode() {
		return inpZipcode;
	}


	public WebElement getInpSelectCountry() {
		return inpSelectCountry;
	}


	public WebElement getInpSelectState() {
		return inpSelectState;
	}


	public WebElement getInpSelectCity() {
		return inpSelectCity;
	}


	public List<WebElement> getDrpdwnSelect() {
		return drpdwnSelect;
	}


	public WebElement getBtnNext() {
		return btnNext;
	}


	public WebElement getLblStateRequiredWarning() {
		return lblStateRequiredWarning;
	}


	public WebElement getLblCityRequiredWarning() {
		return lblCityRequiredWarning;
	}


	public WebElement getLblSCACNoWarning1() {
		return lblSCACNoWarning1;
	}


	public WebElement getLblSCACNoWarning2() {
		return lblSCACNoWarning2;
	}


	public WebElement getLblOrgExistsNoWarning() {
		return lblOrgExistsNoWarning;
	}


	public WebElement getLnkLogin() {
		return lnkLogin;
	}	
	
}



