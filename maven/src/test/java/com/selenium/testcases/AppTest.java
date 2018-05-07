package com.selenium.testcases;

import java.io.IOException;

import org.testng.annotations.Test;

import com.database.connection.DatabaseTesting;
import com.selenium.base.BaseTestUI;
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
		
		DatabaseTesting db = new DatabaseTesting();
	
	public void TestCase1() throws IOException
		{    		
				
		navigateBrowser();
        
        //sendText("Search.Textbox", "Predix_search");
        
        //click("Login.SearchBtn");
        
        //sendTextWithEnter("Search.Textbox", "Predix_search");
		
		click("Lookup.Icon");
		
		checkExist("Lookup.Box");
		
		sendTextWithEnter("Lookup.Box", "Predix_search");
		
		db.compareResultSetDb("query_actor", "exp_query_actor");
		
	}
		
}