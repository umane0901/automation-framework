package com.orangehrm.utils;

import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
	private static Properties prop = new Properties();
	
	static {
		try {
			InputStream input = ConfigReader.class
					.getClassLoader().getResourceAsStream("config/config.properties");
			prop.load(input);
		} catch (Exception e) {
			throw new RuntimeException("Unable to read config.....");
		}
	}
	
	public static String get(String key) {
		return prop.getProperty(key);
	}
}
