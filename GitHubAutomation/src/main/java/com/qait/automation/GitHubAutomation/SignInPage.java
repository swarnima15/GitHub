package com.qait.automation.GitHubAutomation;

import java.io.FileNotFoundException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignInPage {
WebDriver driver;
public SignInPage(WebDriver driver)
{
		this.driver=driver;
		PageFactory.initElements(driver, this);
}
		
@FindBy (css=".btn.site-header-actions-btn.mr-2")
public WebElement signIn;

public String click2() throws FileNotFoundException
{
	    signIn.click();
		return driver.getTitle();
}
}

