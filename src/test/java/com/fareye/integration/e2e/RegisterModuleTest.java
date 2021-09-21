package com.fareye.integration.e2e;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.fareye.integration.base.BaseTest;
import com.fareye.integration.registermodule.steps.CompanyInfoSteps;
import com.fareye.integration.registermodule.steps.FarEyeDataConsentAgreementSteps;
import com.fareye.integration.registermodule.steps.PasswordSetupSteps;
import com.fareye.integration.registermodule.steps.UserInfoSteps;
import com.fareye.integration.registermodule.steps.VerificationSteps;
import com.fareye.integration.utils.MailUtils;
import com.fareye.qa.core.config.Config;
import com.fareye.qa.core.util.PageFunctions;

import io.qameta.allure.TmsLink;

public class RegisterModuleTest extends BaseTest{

	private String filePath = null;
	private Map<String,String> mapCompanyInfo = null;
	private Map<String,String> mapUserInfo = null;
	private Map<String,String> mapPwd = null;
	private CompanyInfoSteps companyinfoSteps = null;
	private UserInfoSteps userinfoSteps = null;
	private VerificationSteps verificationSteps = null;
	private FarEyeDataConsentAgreementSteps fareyeDataConsentAgreementSteps = null;
	private PasswordSetupSteps passwordSetupSteps = null;
	private PageFunctions pageFunctions = null;
	private WebDriverWait wait =null;
	
	@BeforeClass
	public void init() {
		
		filePath = System.getProperty("user.dir")+"/testdata/UserData.xlsx";
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(950, TimeUnit.MILLISECONDS);
        driver.get("https://stagingfareyeconnect.com/register");
        mapCompanyInfo = getCellDataFromExcel(filePath,"CompanyInfo",1);
        mapUserInfo = getCellDataFromExcel(filePath,"UserInfo",1);
        mapPwd = getCellDataFromExcel(filePath,"Password",1);
        companyinfoSteps = CompanyInfoSteps.getInstance(driver);
        userinfoSteps = UserInfoSteps.getInstance(driver);
        verificationSteps = VerificationSteps.getInstance(driver);
        fareyeDataConsentAgreementSteps = FarEyeDataConsentAgreementSteps.getInstance(driver);
        passwordSetupSteps = PasswordSetupSteps.getInstance(driver);
        pageFunctions = PageFunctions.getInstance();
        wait = new WebDriverWait(driver,4);
	}

	@TmsLink("INT-2381")
	@Test(description ="INT-2381:Verify carrier onboarding page is loaded.")
	public void verifyCarrierOnboardingPageLoadedTest() {
		
		Assert.assertEquals(companyinfoSteps.getFarEyeLogo(), "FarEye Logo");
		Assert.assertEquals(companyinfoSteps.getCompanyInfoLabel(), "Company Info");
	}
	
	@TmsLink("INT-2382")
	@Test(dependsOnMethods = {"verifyCarrierOnboardingPageLoadedTest"},description ="INT-2382:Verify Step number with Step is present correctly.")
	public void verifyStepNumberTest() {
		
		Assert.assertEquals(companyinfoSteps.getCompanyInfoStep(), "Company Info");
		Assert.assertEquals(companyinfoSteps.getUserInfoStep(), "User Info");
		Assert.assertEquals(companyinfoSteps.getVerificationStep(), "Verification");
	}
	
	@TmsLink("INT-2383")
	@Test(dependsOnMethods = {"verifyStepNumberTest"},description ="INT-2383:Verify default UI elements On Company Info Screen for NA region.")
	public void verifyUIElementsOnCompanyInfoScreenNARegionTest() {
		
		Assert.assertTrue(companyinfoSteps.getScacNo().isDisplayed());
		Assert.assertTrue(companyinfoSteps.getDOTNo().isDisplayed());
		Assert.assertTrue(companyinfoSteps.getCompanyName().isDisplayed());
		Assert.assertTrue(companyinfoSteps.getAddressLine().isDisplayed());
		Assert.assertTrue(companyinfoSteps.getZipCode().isDisplayed());
		Assert.assertTrue(companyinfoSteps.getCountry().isDisplayed());
	}
	
	@TmsLink("INT-2384")
	@Test(dependsOnMethods = {"verifyUIElementsOnCompanyInfoScreenNARegionTest"},description ="INT-2384:Verify carrier onboarding page is loaded for NA region by default.")
	public void verifyDefaultUIElementsOnCompanyInfoScreenNARegionTest() {
		
		verifyUIElementsOnCompanyInfoScreenNARegionTest();
	}
	
	@TmsLink("INT-2385")
	@Test(dependsOnMethods = {"verifyDefaultUIElementsOnCompanyInfoScreenNARegionTest"},description ="INT-2385:Verify default UI elements On Company Info for EU region.")
	public void verifyDefaultUIElementsOnCompanyInfoScreenEURegionTest() {
		
		driver.navigate().to("https://stagingfareyeconnect.com/register?region=europe");
		
		Assert.assertEquals(driver.getCurrentUrl(),"https://stagingfareyeconnect.com/register?region=europe");
		Assert.assertTrue(companyinfoSteps.getScacNoVATNo().isDisplayed());
		Assert.assertTrue(companyinfoSteps.getCompanyName().isDisplayed());
		Assert.assertTrue(companyinfoSteps.getAddressLine().isDisplayed());
		Assert.assertTrue(companyinfoSteps.getZipCode().isDisplayed());
		Assert.assertTrue(companyinfoSteps.getCountry().isDisplayed());
		
		driver.navigate().back();
	}
	
	@TmsLink("INT-2437")
	@Test(dependsOnMethods = {"verifyDefaultUIElementsOnCompanyInfoScreenEURegionTest"},description ="INT-2437:API - Verify Pre-Registered SCAC No.")
	public void verifyPreRegisteredSCACNoTest() {
		
		companyinfoSteps.setScacNo(mapCompanyInfo.get("PreRegisteredSCACNo"));
		companyinfoSteps.getDOTNo().click();
		companyinfoSteps.getZipCode().click();
		
		Assert.assertTrue(wait.until(ExpectedConditions.visibilityOf(companyinfoSteps.getScacNoAlreadyExistsWarning1())).isDisplayed());
		Assert.assertTrue(wait.until(ExpectedConditions.visibilityOf(companyinfoSteps.getScacNoAlreadyExistsWarning2())).isDisplayed());
		Assert.assertTrue(wait.until(ExpectedConditions.visibilityOf(companyinfoSteps.getOrgAlreadyExistsWarning())).isDisplayed());
		
		companyinfoSteps.getScacNo().click();
		companyinfoSteps.getScacNo().sendKeys(Keys.CONTROL+"a"+Keys.DELETE);
		companyinfoSteps.getScacNo().clear();
	}
	
	@TmsLink("INT-2438")
	@Test(dependsOnMethods = {"verifyPreRegisteredSCACNoTest"},description ="INT-2438:API - Verify New SCAC No.")
	public void verifyNewSCACNoTest() {
		
		companyinfoSteps.setScacNo(mapCompanyInfo.get("NewSCACNo"));
		companyinfoSteps.getDOTNo().click();
		companyinfoSteps.getZipCode().click();
		
		Assert.assertTrue(wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//h2[text()='Company Info']//preceding::div[contains(text(),'Organization Already Exists.')]"))));
		Assert.assertTrue(wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//h2[text()='Company Info']//preceding::div[text()='Scac No already exists.']"))));
		Assert.assertTrue(wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//h2[text()='Company Info']//following::div[text()='Scac No already exists.']"))));
		
		companyinfoSteps.getScacNo().click();
		companyinfoSteps.getScacNo().sendKeys(Keys.CONTROL+"a"+Keys.DELETE);
	}
	
	@TmsLink("INT-2484")
	@Test(dependsOnMethods = {"verifyNewSCACNoTest"},description ="INT-2484:Verify Country, State, City are logically tied with each other.")
	public void verifyCountryStateCityLogicallyTiedTest() {
		
		companyinfoSteps.setCountry(mapCompanyInfo.get("Country"));
		companyinfoSteps.setState(mapCompanyInfo.get("State"));
		companyinfoSteps.setCity(mapCompanyInfo.get("City"));
		
		companyinfoSteps.getZipCode().click();
		companyinfoSteps.getCountry().clear();
		companyinfoSteps.getCountry().sendKeys(Keys.CONTROL+"a");
		companyinfoSteps.getCountry().sendKeys(Keys.DELETE);
		
		
		companyinfoSteps.setCountry(mapCompanyInfo.get("Country1"));
		
		Assert.assertTrue(companyinfoSteps.getState().getAttribute("value").isEmpty());
			
		Assert.assertTrue(companyinfoSteps.getStateRequiredWarning().isDisplayed());
	
		Assert.assertTrue(companyinfoSteps.getCityRequiredWarning().isDisplayed());
		
		companyinfoSteps.getCountry().sendKeys(Keys.CONTROL+"a");
		companyinfoSteps.getCountry().sendKeys(Keys.DELETE);
		
		companyinfoSteps.getDOTNo().click();
		companyinfoSteps.getCountry().click();
	}
	
	@TmsLink("INT-2488")
	@Test(dependsOnMethods = {"verifyCountryStateCityLogicallyTiedTest"},description ="INT-2488:Null value shouldn't be present in State and City dropdown.")
	public void verifyNullValueNotPresentInStateCityDropdownTest() {
		
		companyinfoSteps.setCountry(mapCompanyInfo.get("Country"));
		companyinfoSteps.getDOTNo().click();
		companyinfoSteps.setState(mapCompanyInfo.get("State"));
		companyinfoSteps.getDOTNo().click();
		companyinfoSteps.setCity("null");
		companyinfoSteps.getState().click();
		Assert.assertTrue(companyinfoSteps.getCity().getAttribute("value").isEmpty());
	
		companyinfoSteps.getCountry().sendKeys(Keys.CONTROL+"a"+Keys.DELETE);
		
		companyinfoSteps.setCountry(mapCompanyInfo.get("Country1"));
		companyinfoSteps.getDOTNo().click();
		companyinfoSteps.setState("null");
		companyinfoSteps.getDOTNo().click();
		companyinfoSteps.getZipCode().click();
		Assert.assertTrue(companyinfoSteps.getState().getAttribute("value").isEmpty());
		
		Assert.assertTrue(companyinfoSteps.getCityRequiredWarning().isDisplayed());
		
		Assert.assertTrue(companyinfoSteps.getStateRequiredWarning().isDisplayed());
		
		Assert.assertTrue(companyinfoSteps.getCityRequiredWarning().isDisplayed());

		companyinfoSteps.getCountry().sendKeys(Keys.CONTROL+"a"+Keys.DELETE);
		
	}
	
	@TmsLink("INT-2420")
	@Test(dependsOnMethods = {"verifyNullValueNotPresentInStateCityDropdownTest"},description ="INT-2420:Fill up company info screen.")
	public void setCompanyInfoFieldsTest() {
		
		companyinfoSteps.setScacNo(mapCompanyInfo.get("NewSCACNo"));
		Assert.assertEquals(companyinfoSteps.getScacNo().getAttribute("value"),mapCompanyInfo.get("NewSCACNo"));
		
		companyinfoSteps.setDotNo(mapCompanyInfo.get("DOT No"));
		Assert.assertEquals(companyinfoSteps.getDOTNo().getAttribute("value"),mapCompanyInfo.get("DOT No"));
		
		companyinfoSteps.setCompanyName(mapCompanyInfo.get("Company Name"));
		Assert.assertEquals(companyinfoSteps.getCompanyName().getAttribute("value"),mapCompanyInfo.get("Company Name"));
		
		companyinfoSteps.setAddressLine(mapCompanyInfo.get("Address Line 1"));
		Assert.assertEquals(companyinfoSteps.getAddressLine().getAttribute("value"),mapCompanyInfo.get("Address Line 1"));
		
		companyinfoSteps.setZipCode(mapCompanyInfo.get("Zipcode"));
		Assert.assertEquals(companyinfoSteps.getZipCode().getAttribute("value"),mapCompanyInfo.get("Zipcode"));
		
		companyinfoSteps.setCountry(mapCompanyInfo.get("Country"));
		Assert.assertEquals(companyinfoSteps.getCountry().getAttribute("value"),mapCompanyInfo.get("Country"));
		
		companyinfoSteps.setState(mapCompanyInfo.get("State"));
		Assert.assertEquals(companyinfoSteps.getState().getAttribute("value"),mapCompanyInfo.get("State"));
		
		companyinfoSteps.setCity(mapCompanyInfo.get("City"));
		Assert.assertEquals(companyinfoSteps.getCity().getAttribute("value"),mapCompanyInfo.get("City"));
		
		companyinfoSteps.clickNext();
	}

	@TmsLink("INT-2386")
	@Test(dependsOnMethods = {"setCompanyInfoFieldsTest"},description ="INT-2386:Verify UI elements On User Info Screen.")
	public void verifyUIElementsOnUserInfoScreenTest() {
		
		Assert.assertTrue(wait.until(ExpectedConditions.visibilityOf(userinfoSteps.getEmailAddress())).isDisplayed());
		Assert.assertTrue(userinfoSteps.getCountryCode().isDisplayed());
		Assert.assertTrue(userinfoSteps.getPhoneNo().isDisplayed());
		Assert.assertTrue(userinfoSteps.getFirstName().isDisplayed());
		Assert.assertTrue(userinfoSteps.getLastName().isDisplayed());
		Assert.assertTrue(userinfoSteps.getCheckBox().isDisplayed());
		Assert.assertTrue(userinfoSteps.getAgreementLink().isDisplayed());
	}
	
	@TmsLink("INT-2483")
	@Test(dependsOnMethods = {"verifyUIElementsOnUserInfoScreenTest"},description ="INT-2483:Verify Email, Phone Num, First Name, Last Name Field Validations.")
	public void verifyEmailPhoneFirstNameLastNameFieldValidationsTest() {
		
		userinfoSteps.setEmailAddress(mapUserInfo.get("EmailAddress"));
		userinfoSteps.getEmailAddress().click();
		userinfoSteps.getEmailAddress().sendKeys(Keys.chord(Keys.CONTROL+"a"));
		userinfoSteps.getEmailAddress().sendKeys(Keys.DELETE);
		userinfoSteps.getPhoneNo().click();
		
		Assert.assertTrue(wait.until(ExpectedConditions.visibilityOf(userinfoSteps.getEmailIsRequiredWarning1())).isDisplayed());
		Assert.assertTrue(wait.until(ExpectedConditions.visibilityOf(userinfoSteps.getEmailIsRequiredWarning2())).isDisplayed());
		
		userinfoSteps.getCountryCode().click();
		userinfoSteps.setCountryCodeInput(mapUserInfo.get("Country"));
		userinfoSteps.selectCountryFromDropdown(mapUserInfo.get("CountryCode"));
		userinfoSteps.getPhoneNo().click();
		userinfoSteps.setPhoneNo(mapUserInfo.get("PhoneNum"));
		userinfoSteps.getPhoneNo().sendKeys(Keys.chord(Keys.CONTROL+"a"));
		userinfoSteps.getPhoneNo().sendKeys(Keys.DELETE);
		userinfoSteps.getFirstName().click();
		userinfoSteps.getLastName().click();
		
		Assert.assertTrue(wait.until(ExpectedConditions.visibilityOf(userinfoSteps.getPhoneNoIsRequiredWarning1())).isDisplayed());
		Assert.assertTrue(wait.until(ExpectedConditions.visibilityOf(userinfoSteps.getPhoneNoIsRequiredWarning2())).isDisplayed());
		
		userinfoSteps.setFirstName(mapUserInfo.get("FirstName"));
		userinfoSteps.getFirstName().click();
		userinfoSteps.getFirstName().sendKeys(Keys.chord(Keys.CONTROL+"a"));
		userinfoSteps.getFirstName().sendKeys(Keys.DELETE);
		userinfoSteps.getLastName().click();
		
		Assert.assertTrue(wait.until(ExpectedConditions.visibilityOf(userinfoSteps.getFirstNameIsRequiredWarning1())).isDisplayed());
		Assert.assertTrue(wait.until(ExpectedConditions.visibilityOf(userinfoSteps.getFirstNameIsRequiredWarning2())).isDisplayed());
		
		userinfoSteps.setLastName(mapUserInfo.get("LastName"));
		userinfoSteps.getLastName().click();
		userinfoSteps.getLastName().sendKeys(Keys.chord(Keys.CONTROL+"a"));
		userinfoSteps.getLastName().sendKeys(Keys.DELETE);
		userinfoSteps.getCheckBox().click();
		userinfoSteps.getEmailAddress().click();
		
		Assert.assertTrue(wait.until(ExpectedConditions.visibilityOf(userinfoSteps.getLastNameIsRequiredWarning1())).isDisplayed());
		Assert.assertTrue(wait.until(ExpectedConditions.visibilityOf(userinfoSteps.getLastNameIsRequiredWarning2())).isDisplayed());
	}
	
	@TmsLink("INT-2489")
	@Test(dependsOnMethods = {"verifyEmailPhoneFirstNameLastNameFieldValidationsTest"},description ="INT-2489:FarEye Data Consent Agreement should be downloadable.")
	public void verifyFarEyeDataConsentAgreementShouldDownloadTest() {
		
		userinfoSteps.getAgreementLink().click();
		
		pageFunctions.switchBrowserTab(1);
		pageFunctions.timeToWaitUntil(10);
		
		wait.until(ExpectedConditions.visibilityOf(fareyeDataConsentAgreementSteps.getDownloadButton())).click();
		
		pageFunctions.timeToWaitUntil(5);
		
   	    if(Config.getConfigProperty("browser").equalsIgnoreCase("chrome")) {
   	    	File file = new File(System.getProperty("user.home")+"/Downloads/fareye-data-consent-agreement.pdf");
   	    	Assert.assertTrue(file.exists());	
   	    	file.delete();
   	    }else if(Config.getConfigProperty("browser").equalsIgnoreCase("firefox")){
   			pageFunctions.switchBrowserTab(2);   			
   			Assert.assertTrue(driver.getCurrentUrl().contains("fareye-data-consent-agreement.pdf"));
   			pageFunctions.switchBrowserTab(1);  
   	    }		
   	    
   	    driver.close();
		pageFunctions.switchBrowserTab(0);
	}
	
	@TmsLink("INT-2422")
	@Test(dependsOnMethods = {"verifyFarEyeDataConsentAgreementShouldDownloadTest"},description ="INT-2422:Fill up company info screen.")
	public void setUserInfoFieldsTest() {
		
		userinfoSteps.setEmailAddress(mapUserInfo.get("EmailAddress"));
		
		userinfoSteps.getCountryCode().click();
		userinfoSteps.setCountryCodeInput(mapUserInfo.get("Country"));
		userinfoSteps.selectCountryFromDropdown(mapUserInfo.get("CountryCode"));
		userinfoSteps.getPhoneNo().click();
		userinfoSteps.setPhoneNo(mapUserInfo.get("PhoneNum"));
		
		userinfoSteps.setFirstName(mapUserInfo.get("FirstName"));
		
		userinfoSteps.setLastName(mapUserInfo.get("LastName"));
		
		userinfoSteps.clickSubmit();
	}
	
	@TmsLink("INT-2387")
	@Test(dependsOnMethods = {"setUserInfoFieldsTest"},description ="INT-2387:Verify Verification Screen.")
	public void verifyVerificationScreenTest() {
		
		String strExpected = "You should be receiving an email from us shortly. Click on \"Verify Account\" in the email to continue the registration process. Please make sure to check the spam folder if in case it doesn’t arrive in your inbox.";
		
		Assert.assertTrue(wait.until(ExpectedConditions.visibilityOf(verificationSteps.getInstructions())).isDisplayed());
		Assert.assertEquals(verificationSteps.getInstructions().getText().trim(), strExpected);
	}
	
	@TmsLink("INT-2388")
	@Test(dependsOnMethods = {"verifyVerificationScreenTest"},description ="INT-2388:Verify self-onboarded mail in mailbox.")
	public void verifySelfOnboardedMailTest() {

		Map<String,String> map = new LinkedHashMap<>();
		
		map = MailUtils.getCarrierDetails(mapUserInfo.get("EmailAddress"), "gEET2&%&", "Staging - Welcome To FarEye "+mapUserInfo.get("FirstName"), System.currentTimeMillis(),"COP");
		Assert.assertTrue(map.get("Name").contains(mapUserInfo.get("FirstName")) && map.get("Name").contains(mapUserInfo.get("LastName")));
		Assert.assertTrue(map.get("Email").equals(mapUserInfo.get("EmailAddress")));
		Assert.assertTrue(map.get("Phone").contains(mapUserInfo.get("CountryCode")));
		Assert.assertTrue(map.get("Phone").contains(mapUserInfo.get("PhoneNum")));
		Assert.assertTrue(map.get("Organization").equals(mapCompanyInfo.get("Company Name")));
		Assert.assertTrue(map.get("Dot No").equals(mapCompanyInfo.get("DOT No")));
		Assert.assertTrue(map.get("Scac No").equals(mapCompanyInfo.get("NewSCACNo")));
		Assert.assertTrue(map.get("Address").contains(mapCompanyInfo.get("Address Line 1"))
							&& map.get("Address").contains(mapCompanyInfo.get("City"))
							&& map.get("Address").contains(mapCompanyInfo.get("State"))
							&& map.get("Address").contains(mapCompanyInfo.get("Country"))
							&& map.get("Address").contains(mapCompanyInfo.get("Zipcode")));
	}
	
	@TmsLink("INT-2389")
	@Test(dependsOnMethods = {"verifySelfOnboardedMailTest"},description ="INT-2389:Verify account verification mail.")
	public void verifyAccountVerificationMailTest() {
		
		String url = null;
		Assert.assertTrue(MailUtils.isMailPresent(mapUserInfo.get("EmailAddress"), "gEET2&%&", "Staging - Welcome To FarEye "+mapUserInfo.get("FirstName"), System.currentTimeMillis(),"INBOX"));
		
		url = MailUtils.getAccountVerificationURL(mapUserInfo.get("EmailAddress"), "gEET2&%&", "Staging - Welcome To FarEye "+mapUserInfo.get("FirstName"), System.currentTimeMillis(),"INBOX");
		driver.navigate().to(url);
		
		Assert.assertTrue(driver.getCurrentUrl().equalsIgnoreCase(url));
	}
	
	@TmsLink("INT-2390")
	@Test(dependsOnMethods = {"verifyAccountVerificationMailTest"},description ="INT-2390:Verify Account from account verification mail.")
	public void verifyAccountFromAccountVerificationMailTest() {
		
		Assert.assertTrue(wait.until(ExpectedConditions.visibilityOf(passwordSetupSteps.getLogo())).isDisplayed());
		Assert.assertTrue(passwordSetupSteps.getFirstNameLabel().getText().equals(mapUserInfo.get("FirstName")));
		Assert.assertTrue(passwordSetupSteps.getPwdMetaInformationLabel().isDisplayed());
		Assert.assertTrue(passwordSetupSteps.getPasswordInput().isDisplayed());
		Assert.assertTrue(passwordSetupSteps.getConfirmPasswordInput().isDisplayed());
	}
	
	@TmsLink("INT-2391")
	@Test(dependsOnMethods = {"verifyAccountFromAccountVerificationMailTest"},description ="INT-2391:Verify different valid password entry.")
	public void verifyDifferentValidPasswordEntryTest() {
		
		passwordSetupSteps.getPasswordInput().clear();
		passwordSetupSteps.getConfirmPasswordInput().clear();
		passwordSetupSteps.setPassword(mapPwd.get("Valid Pwd"));
		passwordSetupSteps.setConfirmPassword(mapPwd.get("Valid Confim Pwd"));
		Assert.assertFalse(passwordSetupSteps.getVerifyAccountButton().isEnabled());
	}
	
	@TmsLink("INT-2392")
	@Test(dependsOnMethods = {"verifyDifferentValidPasswordEntryTest"},description ="INT-2392:Verify different less than minimum password entry.")
	public void verifyDifferentLessThanMinimumPasswordEntryTest() {

		passwordSetupSteps.getPasswordInput().clear();
		passwordSetupSteps.getConfirmPasswordInput().clear();
		passwordSetupSteps.setPassword(mapPwd.get("Minimum Pwd"));
		passwordSetupSteps.setConfirmPassword(mapPwd.get("Minimum Confirm Pwd"));
		Assert.assertTrue(passwordSetupSteps.getPasswordHelpLabel().isDisplayed());
		Assert.assertFalse(passwordSetupSteps.getVerifyAccountButton().isEnabled());
	}
	
	@TmsLink("INT-2393")
	@Test(dependsOnMethods = {"verifyDifferentLessThanMinimumPasswordEntryTest"},description ="INT-2393:Verify less than minimum password entry.")
	public void verifyLessThanMinimumPasswordEntryTest() {
		
		passwordSetupSteps.getPasswordInput().clear();
		passwordSetupSteps.getConfirmPasswordInput().clear();
		passwordSetupSteps.setPassword(mapPwd.get("Minimum Pwd"));
		passwordSetupSteps.setConfirmPassword(mapPwd.get("Minimum Pwd"));
		Assert.assertFalse(passwordSetupSteps.getVerifyAccountButton().isEnabled());
	}
	
	@TmsLink("INT-2490")
	@Test(dependsOnMethods = {"verifyLessThanMinimumPasswordEntryTest"},description ="INT-2490:Info message should displayed when password and confirm password doesn't match.")
	public void verifyInfoMessageDisplayedPasswordAndConfirmPasswordNotMatchingTest() {
		
		passwordSetupSteps.getPasswordInput().clear();
		passwordSetupSteps.getConfirmPasswordInput().clear();
		passwordSetupSteps.setPassword(mapPwd.get("Valid Pwd"));
		passwordSetupSteps.setConfirmPassword(mapPwd.get("Valid Confim Pwd"));
		passwordSetupSteps.getPasswordInput().click();
		Assert.assertTrue(passwordSetupSteps.getConfirmPasswordHelpLabel().isDisplayed());
	}
	
	@TmsLink("INT-2393")
	@Test(dependsOnMethods = {"verifyLessThanMinimumPasswordEntryTest"},description ="INT-2394:Verify valid password entry.")
	public void verifyValidPasswordEntryTest() {
		
		passwordSetupSteps.getPasswordInput().clear();
		passwordSetupSteps.getConfirmPasswordInput().clear();
		passwordSetupSteps.setPassword(mapPwd.get("Valid Pwd"));
		passwordSetupSteps.setConfirmPassword(mapPwd.get("Valid Pwd"));
		Assert.assertTrue(passwordSetupSteps.getVerifyAccountButton().isEnabled());
		
		passwordSetupSteps.clickVerifyAccount();
	}
}
