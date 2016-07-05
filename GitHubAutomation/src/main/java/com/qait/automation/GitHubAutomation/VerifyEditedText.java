package com.qait.automation.GitHubAutomation;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class VerifyEditedText 
{
WebDriver driver;
FetchTestData obj=new FetchTestData();	
public VerifyEditedText(WebDriver driver)
{
		this.driver=driver;
		PageFactory.initElements(driver, this);
}	

public String verifyFile() throws Exception
{
		String filepath=(String)obj.fetchData("read_me_file_path");
		StringBuffer fileData = new StringBuffer();
	    BufferedReader br = new BufferedReader(new FileReader(filepath));
        { 
        	for(String line; (line = br.readLine()) != null; ) 
            fileData.append(line);
        }
        br.close();
        return fileData.toString();
}
}
