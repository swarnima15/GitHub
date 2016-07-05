package com.qait.automation.GitHubAutomation;

import java.io.FileNotFoundException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateRepo {
WebDriver driver;
FetchTestData obj=new FetchTestData();
public CreateRepo(WebDriver driver)
{
		this.driver=driver;
		PageFactory.initElements(driver, this);
}
@FindBy(id="repository_name")
public WebElement repo_name;
	
@FindBy(id="repository_auto_init")
public WebElement readMe;
	
@FindBy(className="btn-primary")
public WebElement make;
	
public void enter() throws Exception
{
	    String a=(String)obj.fetchData("repoName");
	    Thread.sleep(5000);
		repo_name.sendKeys(a);
		readMe.click();
		make.click();
}
}
