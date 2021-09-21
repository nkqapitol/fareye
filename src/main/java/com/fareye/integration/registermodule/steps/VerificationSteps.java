package com.fareye.integration.registermodule.steps;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.fareye.integration.registermodule.pages.Verification;
import com.fareye.qa.core.util.PageFunctions;

import io.qameta.allure.Step;

public class VerificationSteps {

	private volatile static VerificationSteps verificationSteps = null;
	private Verification verification;
	private final PageFunctions pageFunctions;

	private VerificationSteps(WebDriver driver) {
		pageFunctions = PageFunctions.getInstance();
		verification = new Verification(driver);
	}
	
	/**
     * Returns instance of RegisterCarrierSteps type
     * 
     * @author Harsh Sengar
     */
	public static VerificationSteps getInstance(WebDriver driver) {
		if (verificationSteps == null) {
			synchronized (VerificationSteps.class) {
	            if (verificationSteps == null)verificationSteps = new VerificationSteps(driver);
	        }
	    }
	    return verificationSteps;
	}
	
	/**
     * Get Instructions
     * 
     * @author Harsh Sengar
     */
	@Step("Get Instructions")
	public WebElement getInstructions() {
		return verification.getTxtInstructions();
	}
	
}