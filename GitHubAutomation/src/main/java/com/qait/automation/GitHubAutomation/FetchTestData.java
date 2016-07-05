package com.qait.automation.GitHubAutomation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Map;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.Yaml;

public class FetchTestData 
{
Object obj;
Map map;
Yaml yaml;
InputStream ios;
	 
public Object fetchData(String key) throws FileNotFoundException
{
	  String s=System.getProperty("os.name");
	  String current;
	  if(s.equalsIgnoreCase("Linux"))
	  {
		  current = System.getProperty("user.dir")+"/src/main/java/Resourse/testData.yml";
	  }
	 else
	  {
		  current = System.getProperty("user.dir")+"\\src\\main\\java\\Resourse\\testData.yml";
	  }
	 
	  ios = new FileInputStream(new File(current));
	  yaml=new Yaml();
	  obj=yaml.load(ios);
	  map=(Map)obj;
	  return  map.get(key);      
		 
}
}
