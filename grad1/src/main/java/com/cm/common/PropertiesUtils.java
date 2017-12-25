package com.cm.common;

import java.io.IOException;
import java.util.Properties;

public class PropertiesUtils {

	private static Properties p = new Properties();
	
	
	
	public static String getValue(String fileName,String key){
		try {
			p.load(PropertiesUtils.class.getClassLoader().getResourceAsStream(fileName));
			return p.getProperty(key);  
		} catch (IOException e) {
			System.out.println("∂¡»°≈‰÷√Œƒº˛ ß∞‹"+e.getMessage());
		}
		return "";
	}
}
