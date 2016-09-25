package com.prizy.pricer.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest(PropertiesFileReader.class)
public class PropertiesFileReaderTest {

	@InjectMocks
	private PropertiesFileReader propertiesFileReader;

	@Test
	public void testReadPropertiesFromFilePositiveScenario() {
		String fileName = "pricing-rules.properties";
		Properties properties = null;
		try {
			properties = propertiesFileReader.readPropertiesFromFile(fileName);
		} catch (IOException e) {
			logError(e);
		}
		Assert.assertTrue(checkProperties(properties, mockPricingProperties("2", "2", "20")));
	}

	@Test(expected = FileNotFoundException.class)
	public void testReadPropertiesFromFileFileNotFound() throws FileNotFoundException, IOException {
		String fileName = "pricing-rules1.properties";
		propertiesFileReader.readPropertiesFromFile(fileName);
	}

	private boolean checkProperties(Properties properties, Properties mockPricingProperties) {
		if (!properties.getProperty("numberOfHighestValuesToBeRemoved").equals(mockPricingProperties.getProperty("numberOfHighestValuesToBeRemoved"))) {
			return false;
		} else if (!properties.getProperty("numberOfLowestValuesToBeRemoved").equals(mockPricingProperties.getProperty("numberOfLowestValuesToBeRemoved"))) {
			return false;
		} else if (!properties.getProperty("percentageToAdd").equals(mockPricingProperties.getProperty("percentageToAdd"))) {
			return false;
		} else {
			return true;
		}

	}

	private Properties mockPricingProperties(String numberOfHighestValuesToBeRemoved, String numberOfLowestValuesToBeRemoved, String percentageToAdd) {
		Properties pricingPropertiesMock = new Properties();
		pricingPropertiesMock.setProperty("numberOfHighestValuesToBeRemoved", numberOfHighestValuesToBeRemoved);
		pricingPropertiesMock.setProperty("numberOfLowestValuesToBeRemoved", numberOfLowestValuesToBeRemoved);
		pricingPropertiesMock.setProperty("percentageToAdd", percentageToAdd);
		return pricingPropertiesMock;
	}

	private void logError(IOException e) {
		e.printStackTrace();
	}
}
