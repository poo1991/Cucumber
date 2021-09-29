package com.domain.framework;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Settings {
	static BufferedReader reader ;
	private static Properties properties = loadFromPropertiesFile();

	private Settings() {
		//prevent outside instantiation
	}

	public static Properties loadFromPropertiesFile() {
		Properties props = new Properties();
		BufferedReader reader;
		try {
			reader= new BufferedReader(new FileReader("configuration/global-settings.properties"));

			try {
				props.load(reader);
				reader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("global settis file is not gound in the mentioned location");
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
