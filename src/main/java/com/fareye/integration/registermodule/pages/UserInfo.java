package com.fareye.integration.registermodule.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class UserInfo {

	@FindBy(xpath = "//h2[text()='User Info']")
	private WebElement lblUserInfo;
	
	@FindBy(xpath = "//label[contains(text(),'Email Address')]//following-sibling::input")
	private WebElement inpEmailAddress;

	@FindBy(xpath = "//label[contains(text(),'Email Address')]//following::div[@role='combobox']")
	private WebElement divSelectCountry;
	
	@FindBy(xpath = "//label[contains(text(),'Email Address')]//following::div[@role='combobox']//following-sibling::input[@type='text']")
	private WebElement inpCountry;
	
	@FindBy(xpath = "(//div[@role='listbox'])[last()]//following::div[contains(@class,'title')]")
	private List<WebElement> drpdwnSelectCountry;
	
	@FindBy(xpath = "//label[contains(text(),'Phone No')]//following-sibling::input")
	private WebElement inpPhoneNo;
	
	@FindBy(xpath = "//label[contains(text(),'First Name')]//following-sibling::input")
	private WebElement inpFirstName;
	
	@FindBy(xpath = "//label[contains(text(),'Last Name')]//following-sibling::input")
	private WebElement inpLastName;
	
	@FindBy(xpath = "(//label[contains(text(),'Last Name')]//following::div[contains(@class,'input')])[last()-1]")
	private WebElement chkboxAgreement;
	
	@FindBy(xpath = "//a[contains(text(),'Agreement')]")
	private WebElement lnkAgreement;
	
	@FindBy(xpath = "//span[contains(text(),'Go Back')]//parent::button")
	private WebElement btnGoBack;
	
	@FindBy(xpath = "//span[contains(text(),'Submit')]//parent::button")
	private WebElement btnSubmit;
	
	@FindBy(xpath = "//h2[text()='User Info']//preceding::div[text()='Email is required']")
	private WebElement lblEmailAddressRequiredWarning1;
	
	@FindBy(xpath = "//h2[text()='User Info']//following::div[text()='Email is required']")
	private WebElement lblEmailAddressRequiredWarning2;
	
	@FindBy(xpath = "//h2[text()='User Info']//preceding::div[text()='Phone No. is required']")
	private WebElement lblPhoneNumRequiredWarning1;
	
	@FindBy(xpath = "//h2[text()='User Info']//following::div[text()='Phone No.* is required']")
	private WebElement lblPhoneNumRequiredWarning2;
	
	@FindBy(xpath = "//h2[text()='User Info']//preceding::div[text()='First Name is required']")
	private WebElement lblFirstNameRequiredWarning1;
	
	@FindBy(xpath = "//h2[text()='User Info']//following::div[text()='First Name is required']")
	private WebElement lblFirstNameRequiredWarning2;
	
	@FindBy(xpath = "//h2[text()='User Info']//preceding::div[text()='Last Name is required']")
	private WebElement lblLastNameRequiredWarning1;
	
	@FindBy(xpath = "//h2[text()='User Info']//following::div[text()='Last Name is required']")
	private WebElement lblLastNameRequiredWarning2;
	
	@FindBy(xpath = "//div[text()='Please enter a valid email address']")
	private WebElement lblInValidEmailWarning;
	
	private WebDriver driver;
	
	public UserInfo(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
	}

	public WebElement getLblUserInfo() {
		return lblUserInfo;
	}

	public WebElement getInpEmailAddress() {
		return inpEmailAddress;
	}

	public WebElement getDivSelectCountry() {
		return divSelectCountry;
	}

	public WebElement getInpCountry() {
		return inpCountry;
	}

	public List<WebElement> getDrpdwnSelectCountry() {
		return drpdwnSelectCountry;
	}

	public WebElement getInpPhoneNo() {
		return inpPhoneNo;
	}

	public WebElement getInpFirstName() {
		return inpFirstName;
	}

	public WebElement getInpLastName() {
		return inpLastName;
	}

	public WebElement getChkboxAgreement() {
		return chkboxAgreement;
	}

	public WebElement getLnkAgreement() {
		return lnkAgreement;
	}

	public WebElement getBtnGoBack() {
		return btnGoBack;
	}

	public WebElement getBtnSubmit() {
		return btnSubmit;
	}

	public WebElement getLblEmailAddressRequiredWarning1() {
		return lblEmailAddressRequiredWarning1;
	}

	public WebElement getLblEmailAddressRequiredWarning2() {
		return lblEmailAddressRequiredWarning2;
	}

	public WebElement getLblPhoneNumRequiredWarning1() {
		return lblPhoneNumRequiredWarning1;
	}

	public WebElement getLblPhoneNumRequiredWarning2() {
		return lblPhoneNumRequiredWarning2;
	}

	public WebElement getLblFirstNameRequiredWarning1() {
		return lblFirstNameRequiredWarning1;
	}

	public WebElement getLblFirstNameRequiredWarning2() {
		return lblFirstNameRequiredWarning2;
	}

	public WebElement getLblLastNameRequiredWarning1() {
		return lblLastNameRequiredWarning1;
	}

	public WebElement getLblLastNameRequiredWarning2() {
		return lblLastNameRequiredWarning2;
	}

	public WebElement getLblInValidEmailWarning() {
		return lblInValidEmailWarning;
	}

}
