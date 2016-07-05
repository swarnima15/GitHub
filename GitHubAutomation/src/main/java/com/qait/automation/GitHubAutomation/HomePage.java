package com.qait.automation.GitHubAutomation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
WebDriver driver;
public HomePage(WebDriver driver)
{
		this.driver=driver;
		PageFactory.initElements(driver, this);
} 
@FindBy(linkText="New repository")
public WebElement new_repo;
	
	
public String create_repo()
{
		new_repo.click();
		return driver.getTitle();
}

public String repo_creatd()
{
	return driver.getTitle();
}
}
