package com.domain.framework;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.configuration.PropertiesConfiguration.PropertiesReader;

public class Settings {
	static BufferedReader reader ;
	private static Properties properties = loadFromPropertiesFile();

	private Settings() {
		//prevent outside instantiation
	}

	public static Properties loadFromPropertiesFile() {
		Properties props = new Properties();
		BufferedReader reader;
		InputStream is =null;
		String configFileName="config.properties";
		is = PropertiesReader.class.getClassLoader().getResourceAsStream(configFileName);
		//reader= new BufferedReader(new FileReader("config.properties"));

		try {
			props.load(is);
			is.close();
			//reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return props;
	}

	public static synchronized String getProperty(String name) {
		return properties.getProperty(name);
	}

	public static synchronized String setProperty(String name,String value) {
		properties.setProperty(name,value);
		return null;
	}

	public static synchronized String getProperty(String name,String defaultValue) {
		return properties.getProperty(name,defaultValue);
	}


}
