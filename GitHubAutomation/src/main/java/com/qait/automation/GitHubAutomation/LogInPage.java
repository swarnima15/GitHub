package com.qait.automation.GitHubAutomation;

import java.io.FileNotFoundException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LogInPage {
WebDriver driver;
public LogInPage(WebDriver driver)
{
		this.driver=driver;
		PageFactory.initElements(driver, this);
}
@FindBy(name="login")
public WebElement username;
		
@FindBy(name="password")
public WebElement password;

@FindBy(name="commit")
public WebElement logIn;
FetchTestData obj=new FetchTestData();

		
public String enterDetails() throws Exception
{
	    String a=(String)obj.fetchData("username");
	    String b=(String)obj.fetchData("password");
		username.sendKeys(a);
		password.sendKeys(b);
		logIn.click();
		Thread.sleep(2000);
		return driver.getTitle();
}
}
