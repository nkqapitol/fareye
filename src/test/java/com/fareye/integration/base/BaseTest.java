package com.fareye.integration.base;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;

import com.fareye.qa.core.Driver;
import com.fareye.qa.core.FarEyeAbstractTest;
import com.fareye.qa.core.api.utils.JsonUtils;
import com.fareye.qa.core.config.Config;
import com.fareye.qa.core.dataprovider.impl.ExcelReader;
import com.fareye.qa.core.dataprovider.impl.FileSystemResource;

public class BaseTest extends FarEyeAbstractTest{
	
	public WebDriver driver;
	public static JsonUtils jsonUtils;
	public static String URL_NORTH_AMERICA;
	public static String URL_EUROPE;
	
	 public BaseTest() {
		
		URL_NORTH_AMERICA = Config.getConfigProperty("na_server_url");
		URL_EUROPE = Config.getConfigProperty("eu_server_url");
	}

	@BeforeClass
	    public void config() {

	        driver = Driver.getWebDriver();
	        jsonUtils = new JsonUtils();      
	    }

	public Map<String,String> getCellDataFromExcel(String filePath, String sheet, int rowIndex) {
        
		Map<String,String> map = new HashMap<>();
		
		ExcelReader excelReader = new ExcelReader(new FileSystemResource(filePath));
		map = excelReader.getRowContentsWithHeader(sheet,rowIndex);
				
        return map;
    }
	
}