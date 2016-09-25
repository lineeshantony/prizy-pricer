package com.prizy.pricer.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesFileReader {

	private static PropertiesFileReader instance;
	private InputStream inputStream;

	private PropertiesFileReader() {
	}

	public static synchronized PropertiesFileReader getInstance() {
		if (instance == null)
			instance = new PropertiesFileReader();

		return instance;
	}

	public Properties readPropertiesFromFile(String fileName) throws IOException {
		Properties prop = null;

		try {
			prop = new Properties();
			inputStream = getClass().getClassLoader().getResourceAsStream(fileName);

			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + fileName + "' not found in the classpath");
			}
		} finally {
			if (inputStream != null) {
				inputStream.close();
			}
		}
		return prop;
	}
}
