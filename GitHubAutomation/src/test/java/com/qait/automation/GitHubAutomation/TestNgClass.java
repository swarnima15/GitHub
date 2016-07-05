package com.qait.automation.GitHubAutomation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import cloning.process.Cloning;

public class TestNgClass 
{
WebDriver driver;
String url;
SignInPage sign;
LogInPage logIn;
HomePage home;
CreateRepo repo;
Cloning clone;
RepoHome repoHomePage;
String UIcommit;
List<String> commit;
FetchTestData obj;
VerifyEditedText verify;
List<String> b;
private final static Logger logger = Logger.getLogger(TestNgClass.class.getName());
		
@BeforeClass
public void initialize() throws Exception
{
	obj=new FetchTestData();
	driver=new FirefoxDriver();
	url= (String)obj.fetchData("Url");                  
	sign=new SignInPage(driver);
	logIn=new LogInPage(driver);
	home=new HomePage(driver);
	repo=new CreateRepo(driver);
	clone=new Cloning(driver);
	repoHomePage=new RepoHome(driver);
	verify=new VerifyEditedText(driver);
	String workDir = System.getProperty("user.dir");
	String fil2 = "MyLogFile.log";
	File myFile = new File(workDir + fil2);
	String userPath = myFile.getPath();
	FileHandler  fh = new FileHandler(workDir +"/fil2");  
	logger.addHandler(fh);
	SimpleFormatter formatter = new SimpleFormatter();  
	fh.setFormatter(formatter);
}

@Test(priority=1)
public void case1_SignIn() throws Exception                   
{
	driver.get(url);
	driver.manage().window().maximize();
	String title1=sign.click2();
	Assert.assertEquals(title1,"Sign in to GitHub · GitHub");
	logger.info("Verifying sign in");
}

@Test(priority=2)
public void case2_LogIn() throws Exception
{
	String title2=logIn.enterDetails();
	Assert.assertEquals(title2,"GitHub");
	logger.info("Verifying user credentials");
}

@Test(priority=3)
public void case3_CreateRepo() throws Exception
{
	String title3=home.create_repo();
	Assert.assertEquals(title3,"Create a New Repository");
	repo.enter();
	logger.info("Verifying creation of new repo");
}
		
@Test(priority=4)
public void case4_repoCreated() throws Exception
{
	String title4=home.repo_creatd();
	String match=(String)obj.fetchData("username")+"/"+(String)obj.fetchData("repoName");
	Assert.assertEquals(title4,match);
	logger.info("Verifying whether the repo created or not");
}

@Test(priority=5)
public void case5_Clone_push_Validate() throws Exception               
{
	commit=clone.writeFile();
	Thread.sleep(2000);
	clone.refresh_browser();
	Assert.assertTrue(commit.get(1).contains("On branch master"));	
	logger.info("Verifying cloning and push process");
}
		
@Test(priority=6)
public void case6_commitMsg_Validate() throws IOException
{ 
	UIcommit=repoHomePage.getCommitMsg();
	Assert.assertEquals(UIcommit,commit.get(0));	
	logger.info("Verifying commit message");
}

@Test(priority=7)
public void case7_Nos_of_comits_done()
{
	String a=repoHomePage.getNosOfCommit();
	String no="2";
	Assert.assertEquals(a,no);
	logger.info("Verifying number of commits done so far");
}
		
@Test(priority=8)
public void case8_editReadMe() throws Exception
{
	 b=repoHomePage.editReadMe();
	 boolean b1=clone.pullCommand();
	 clone.pullCommand();
	 Assert.assertTrue(b1);
	 logger.info("Verifying pull operation");
}
		
@Test(priority=9)
public void case9_verify_edited() throws Exception
{
	String match=(String)obj.fetchData("repoName")+"/README.md at master · "+(String)obj.fetchData("username")+"/"+(String)obj.fetchData("repoName");
	Assert.assertEquals(b.get(1),match);
	logger.info("Verifying editing is done in readMe");
}

@Test(priority=10)
public void case10_verify_readMe() throws Exception
{
	String edit=verify.verifyFile();
	Assert.assertTrue(edit.contains(b.get(0)));
	logger.info("Verifying if readMe is edited or not");
}

@AfterClass
public void close()
{
	driver.quit();
}
}	

