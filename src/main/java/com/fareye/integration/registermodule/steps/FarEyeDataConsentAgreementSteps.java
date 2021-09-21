package com.fareye.integration.registermodule.steps;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.fareye.integration.registermodule.pages.FarEyeDataConsentAgreement;
import com.fareye.integration.registermodule.pages.Verification;
import com.fareye.qa.core.util.PageFunctions;

import io.qameta.allure.Step;

public class FarEyeDataConsentAgreementSteps {

	private volatile static FarEyeDataConsentAgreementSteps fareyeDataConsentAgreementSteps = null;
	private FarEyeDataConsentAgreement fareyeDataConsentAgreement;
	private final PageFunctions pageFunctions;

	private FarEyeDataConsentAgreementSteps(WebDriver driver) {
		pageFunctions = PageFunctions.getInstance();
		fareyeDataConsentAgreement = new FarEyeDataConsentAgreement(driver);
	}
	
	/**
     * Returns instance of RegisterCarrierSteps type
     * 
     * @author Harsh Sengar
     */
	public static FarEyeDataConsentAgreementSteps getInstance(WebDriver driver) {
		if (fareyeDataConsentAgreementSteps == null) {
			synchronized (FarEyeDataConsentAgreementSteps.class) {
	            if (fareyeDataConsentAgreementSteps == null)fareyeDataConsentAgreementSteps = new FarEyeDataConsentAgreementSteps(driver);
	        }
	    }
	    return fareyeDataConsentAgreementSteps;
	}
	
	/**
     * Get Instructions
     * 
     * @author Harsh Sengar
     */
	@Step("Get Instructions")
	public WebElement getDownloadButton() {
		return fareyeDataConsentAgreement.getBtnDownload();
	}
	
}