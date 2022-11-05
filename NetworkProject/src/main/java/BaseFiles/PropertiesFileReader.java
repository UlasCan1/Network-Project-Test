package BaseFiles;

import org.testng.Assert;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesFileReader {
	
	public Properties getProperties() throws IOException {
		
		Properties properties= new Properties();
		
		try {
			properties.load(new FileInputStream("./src/main/resources/config.properties"));
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		
		return properties;
	}

	public static String getDataInPropertiesFile(String key){
		String data="";
		try {
			PropertiesFileReader propertiesFileReader=new PropertiesFileReader();
			data= propertiesFileReader.getProperties().getProperty(key);
		}catch (Exception e){
			Assert.fail(e.getMessage());
		}
		return  data;
	}

}
