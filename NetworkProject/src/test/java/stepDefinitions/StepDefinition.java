package stepDefinitions;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;


import BaseFiles.DriverManager;
import GeneralFiles.BasePage;
import org.openqa.selenium.WebDriver;



public class StepDefinition {

	WebDriver driver=null;

	    public StepDefinition(){
	    	driver= DriverManager.getDriver();
	    }
	
    @Before
    public void before(Scenario scenario){
	
    }


    @After
    public void after(Scenario scenario) { 
    	

  	  BasePage basePage=new BasePage();
    	
        try {        	  
        	
        	if (DriverManager.getDriver() != null && !DriverManager.getDriver().toString().contains("null")) {
        		
        		 if (scenario.isFailed()) {
                     basePage.takeScreenshot(scenario);
                }

        	}  

        }catch (Exception e){
			e.printStackTrace();
        }
    }

    
    
    
}
