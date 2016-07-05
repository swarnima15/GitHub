package cloning.process;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.qait.automation.GitHubAutomation.FetchTestData;

public class Cloning
{
	WebDriver driver;
	String comment;
	String RepoName;
	String s;
	FetchTestData obj=new FetchTestData();	
	String sys_user;
public Cloning(WebDriver driver)
{
	this.driver=driver;
	PageFactory.initElements(driver, this);
}

public List<String> writeFile() throws IOException, InterruptedException
{
	String line1 = null;
	String line2 = null;
	String text=null; 
	File file;
	FileWriter fw;
	PrintWriter pw;
	Process p;
    List<String> list = new ArrayList<String>();
    sys_user=System.getProperty("user.name");
    
	String UserName=(String)obj.fetchData("username");                          
    RepoName=(String)obj.fetchData("repoName");
    String txtFile=(String)obj.fetchData("txtFileName");
    String Passwd=(String)obj.fetchData("password");
    String CloneUrl="https://github.com/"+UserName+"/"+RepoName+".git";
    String shFilePath=(String)obj.fetchData("clone_shFile_path");  
    comment=(String)obj.fetchData("commitMessage")+System.currentTimeMillis();
    s=System.getProperty("os.name");
    String batFilePath=(String)obj.fetchData("clone_batFile_path"); 
    
    if(s.equalsIgnoreCase("Linux"))
    {
    file = new File(shFilePath);
	file.createNewFile();
    file.setExecutable(true);
	fw = new FileWriter(file.getAbsoluteFile());
	pw = new PrintWriter(fw);
    pw.println("#!/bin/bash"); 
    pw.println("cd ~");
    pw.println("cd Desktop");
    pw.println("git clone "+CloneUrl);
    pw.println("cd "+RepoName);
    pw.println("touch "+txtFile);
    pw.println("git add "+txtFile);
    pw.println("git status");
    pw.println("git commit -m \""+comment+"\"");
    pw.println("git status");
    pw.println("git push http://"+UserName+":"+Passwd+"@github.com/"+UserName+"/"+RepoName+".git");
    pw.close();	
    p=new ProcessBuilder(shFilePath).start();
    int rc = p.waitFor();
    }
    else 
    {
    	file = new File(batFilePath);
    	file.createNewFile();
        file.setExecutable(true);
    	fw = new FileWriter(file.getAbsoluteFile());
    	pw = new PrintWriter(fw);
    	pw.println("C:");
    	pw.println("cd users");
        pw.println("cd "+sys_user);
        pw.println("cd Desktop");
        pw.println("git clone "+CloneUrl);
        pw.println("cd "+RepoName);
        pw.println("type nul > "+txtFile);
        pw.println("git add "+txtFile);
        pw.println("git status");
        pw.println("git commit -m \""+comment+"\"");
        pw.println("git status");
        pw.println("git push http://"+UserName+":"+Passwd+"@github.com/"+UserName+"/"+RepoName+".git");
        pw.close();	
        p=new ProcessBuilder(batFilePath).start();
        int rc = p.waitFor();
    }
   
	InputStream is = p.getInputStream();
	BufferedReader reader = new BufferedReader(new InputStreamReader(is));
	while ((line1 = reader.readLine()) != null) 
	{
		break;
	}
	while ((line2 = reader.readLine()) != null) 
	{
		text=reader.readLine();
	}
	list.add(comment);
	list.add(line1);
	list.add(text);
	return list;
 }

public void refresh_browser()
{
	driver.navigate().refresh();
}
public boolean pullCommand() throws Exception
{
	String shFilePath2=(String)obj.fetchData("pull_shFile_path");  
	String batFilePath2=(String)obj.fetchData("pull_batFile_path");  
	File file1;
	FileWriter fw1;
	PrintWriter pw1;
	Process p1;
	
	 if(s.equalsIgnoreCase("Linux"))
	{
    file1 = new File(shFilePath2);
	file1.createNewFile();
    file1.setExecutable(true);
	fw1 = new FileWriter(file1.getAbsoluteFile());
	pw1 = new PrintWriter(fw1);
    pw1.println("#!/bin/bash"); 
    pw1.println("cd ~");
    pw1.println("cd Desktop");
    pw1.println("cd "+RepoName);
    pw1.println("git pull ");
    pw1.println("git status");
    pw1.close();
    p1=new ProcessBuilder(shFilePath2).start();
    int rc = p1.waitFor();
   
	}
	else
	{
		file1 = new File(batFilePath2);
		file1.createNewFile();
	    file1.setExecutable(true);
		fw1 = new FileWriter(file1.getAbsoluteFile());
		pw1 = new PrintWriter(fw1);
		pw1.println("C:");
        pw1.println("cd users");
        pw1.println("cd "+sys_user);
	    pw1.println("cd Desktop");
	    pw1.println("cd "+RepoName);
	    pw1.println("git pull ");
	    pw1.println("git status");
	    pw1.close();
	    p1=new ProcessBuilder(batFilePath2).start();
	    int rc = p1.waitFor();
	}
	
    InputStream is = p1.getInputStream();
	BufferedReader reader = new BufferedReader(new InputStreamReader(is));
	String line = null;
	while ((line = reader.readLine()) != null)
	System.out.println(line);
	return true;
}
}
   


	
	
