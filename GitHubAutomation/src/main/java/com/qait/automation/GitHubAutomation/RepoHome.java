package com.qait.automation.GitHubAutomation;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class RepoHome {
WebDriver driver;
String comitMsg;
FetchTestData obj=new FetchTestData();	

public RepoHome(WebDriver driver)
{
	this.driver=driver;
	PageFactory.initElements(driver, this);
}

@FindBy(xpath="(//td[@class='message'])[1]//a")
public WebElement commit;
	
@FindBy(linkText="README.md")
public WebElement ReadMe;
	
@FindBy(css=".octicon-pencil")
public WebElement Pencil;
	
@FindBy(css=".ace_text-input")
public WebElement type;
	
@FindBy(id="submit-file")
public WebElement submit;

@FindBy(css=".num.text-emphasized")
public WebElement no_of_commits;
	
public String getCommitMsg()
{
	   comitMsg=commit.getText();
	   return comitMsg;
}
	
public List<String> editReadMe() throws Exception
{
	List<String> list = new ArrayList<String>();
	String a=(String)obj.fetchData("editReadMeFolder");
	ReadMe.click();
	Thread.sleep(3000);
	Pencil.click();
	Thread.sleep(3000);
	type.sendKeys(a);
	submit.click();
	list.add(a);
	list.add(driver.getTitle());
	return list;
}

public String getNosOfCommit()
{
	String a=no_of_commits.getText();
	return a;
}
}
