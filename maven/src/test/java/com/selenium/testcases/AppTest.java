package com.selenium.testcases;

import org.testng.annotations.Test;
import java.io.IOException;

import org.testng.annotations.Test;

import com.selenium.base.*;

/**
 * Unit test for simple App.
 */
@Test
public class AppTest extends BaseTestUI {
    /**
     * Create the test case
     *
     * @param testName name of the test case
     * @throws IOException 
     */
	public void TestCase1() throws IOException
		{    
        navigateBrowser();
        
        sendText("Search.Textbox", "Predix_search");
        
        click("Login.SearchBtn");
        
        //driver.get(pros.getProperty("URL"));
    }

}