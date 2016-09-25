package com.prizy.pricer.price.calculator;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.prizy.pricer.entities.Product;
import com.prizy.pricer.entities.ProductSurvey;
import com.prizy.pricer.util.PropertiesFileReader;

@RunWith(PowerMockRunner.class)
@PrepareForTest(ProductPriceCalculator.class)
public class ProductPriceCalculatorTest {
	@Mock
	private PropertiesFileReader propertiesFileReaderMock;
	@Mock
	private Properties pricingPropertiesMock;

	@InjectMocks
	private ProductPriceCalculator productPriceCalculator;

	@Test
	@PrepareForTest(PropertiesFileReader.class)
	public void testCalculateProductIdealPriceMockPricingPropertiesAsDefinedInRequirements() {
		List<ProductSurvey> productSurveys = createListOfFiveProductSurveys();
		Properties pricingPropertiesMock = mockPricingProperties("2", "2", "20");
		PowerMockito.mockStatic(PropertiesFileReader.class);
		try {
			PowerMockito.when(PropertiesFileReader.getInstance()).thenReturn(propertiesFileReaderMock);
			PowerMockito.when(propertiesFileReaderMock.readPropertiesFromFile("pricing-rules.properties")).thenReturn(pricingPropertiesMock);
		} catch (IOException e) {
			logError(e);
		}
		Double productIdealPrice = productPriceCalculator.calculateProductIdealPrice(productSurveys);
		Assert.assertEquals(productIdealPrice, 360.0, 0.0);
	}

	@Test
	@PrepareForTest(PropertiesFileReader.class)
	public void testCalculateProductIdealPriceMockFileNotFoundException() {
		List<ProductSurvey> productSurveys = createListOfFiveProductSurveys();
		PowerMockito.mockStatic(PropertiesFileReader.class);
		try {
			PowerMockito.when(PropertiesFileReader.getInstance()).thenReturn(propertiesFileReaderMock);
			PowerMockito.when(propertiesFileReaderMock.readPropertiesFromFile("pricing-rules.properties")).thenThrow(new FileNotFoundException());
		} catch (IOException e) {
			logError(e);
		}
		Double productIdealPrice = productPriceCalculator.calculateProductIdealPrice(productSurveys);
		Assert.assertEquals(productIdealPrice, 0.0, 0.0);
	}

	@Test
	@PrepareForTest(PropertiesFileReader.class)
	public void testCalculateProductIdealPriceMockIOException() {
		List<ProductSurvey> productSurveys = createListOfFiveProductSurveys();
		PowerMockito.mockStatic(PropertiesFileReader.class);
		try {
			PowerMockito.when(PropertiesFileReader.getInstance()).thenReturn(propertiesFileReaderMock);
			PowerMockito.when(propertiesFileReaderMock.readPropertiesFromFile("pricing-rules.properties")).thenThrow(new IOException());
		} catch (IOException e) {
			logError(e);
		}
		Double productIdealPrice = productPriceCalculator.calculateProductIdealPrice(productSurveys);
		Assert.assertEquals(productIdealPrice, 0.0, 0.0);
	}

	@Test
	@PrepareForTest(PropertiesFileReader.class)
	public void testCalculateProductIdealPriceMockNullSurveys() {
		List<ProductSurvey> productSurveys = null;
		Properties pricingPropertiesMock = mockPricingProperties("2", "2", "20");
		PowerMockito.mockStatic(PropertiesFileReader.class);
		try {
			PowerMockito.when(PropertiesFileReader.getInstance()).thenReturn(propertiesFileReaderMock);
			PowerMockito.when(propertiesFileReaderMock.readPropertiesFromFile("pricing-rules.properties")).thenReturn(pricingPropertiesMock);
		} catch (IOException e) {
			logError(e);
		}
		Double productIdealPrice = productPriceCalculator.calculateProductIdealPrice(productSurveys);
		Assert.assertEquals(productIdealPrice, 0.0, 0.0);
	}

	@Test
	@PrepareForTest(PropertiesFileReader.class)
	public void testCalculateProductIdealPriceMockEmptySurveys() {
		List<ProductSurvey> productSurveys = new ArrayList<ProductSurvey>();
		Properties pricingPropertiesMock = mockPricingProperties("2", "2", "20");
		PowerMockito.mockStatic(PropertiesFileReader.class);
		try {
			PowerMockito.when(PropertiesFileReader.getInstance()).thenReturn(propertiesFileReaderMock);
			PowerMockito.when(propertiesFileReaderMock.readPropertiesFromFile("pricing-rules.properties")).thenReturn(pricingPropertiesMock);
		} catch (IOException e) {
			logError(e);
		}
		Double productIdealPrice = productPriceCalculator.calculateProductIdealPrice(productSurveys);
		Assert.assertEquals(productIdealPrice, 0.0, 0.0);
	}

	@Test
	@PrepareForTest(PropertiesFileReader.class)
	public void testCalculateProductIdealPriceMockNumberOfElementsToRemoveBiggerThanSurveys() {
		List<ProductSurvey> productSurveys = createListOfFiveProductSurveys();
		Properties pricingPropertiesMock = mockPricingProperties("4", "4", "20");
		PowerMockito.mockStatic(PropertiesFileReader.class);
		try {
			PowerMockito.when(PropertiesFileReader.getInstance()).thenReturn(propertiesFileReaderMock);
			PowerMockito.when(propertiesFileReaderMock.readPropertiesFromFile("pricing-rules.properties")).thenReturn(pricingPropertiesMock);
		} catch (IOException e) {
			logError(e);
		}
		Double productIdealPrice = productPriceCalculator.calculateProductIdealPrice(productSurveys);
		Assert.assertEquals(productIdealPrice, 360.0, 0.0);
	}

	@Test
	@PrepareForTest(PropertiesFileReader.class)
	public void testCalculateProductIdealPriceMockNumberOfElementsToRemoveEqualToSurveys() {
		List<ProductSurvey> productSurveys = createListOfFiveProductSurveys();
		Properties pricingPropertiesMock = mockPricingProperties("2", "3", "20");
		PowerMockito.mockStatic(PropertiesFileReader.class);
		try {
			PowerMockito.when(PropertiesFileReader.getInstance()).thenReturn(propertiesFileReaderMock);
			PowerMockito.when(propertiesFileReaderMock.readPropertiesFromFile("pricing-rules.properties")).thenReturn(pricingPropertiesMock);
		} catch (IOException e) {
			logError(e);
		}
		Double productIdealPrice = productPriceCalculator.calculateProductIdealPrice(productSurveys);
		Assert.assertEquals(productIdealPrice, 360.0, 0.0);
	}

	@Test
	@PrepareForTest(PropertiesFileReader.class)
	public void testCalculateProductIdealPriceMockNumberOfHigherElementsToRemoveEqualToZero() {
		List<ProductSurvey> productSurveys = createListOfFiveProductSurveys();
		Properties pricingPropertiesMock = mockPricingProperties("0", "3", "20");
		PowerMockito.mockStatic(PropertiesFileReader.class);
		try {
			PowerMockito.when(PropertiesFileReader.getInstance()).thenReturn(propertiesFileReaderMock);
			PowerMockito.when(propertiesFileReaderMock.readPropertiesFromFile("pricing-rules.properties")).thenReturn(pricingPropertiesMock);
		} catch (IOException e) {
			logError(e);
		}
		Double productIdealPrice = productPriceCalculator.calculateProductIdealPrice(productSurveys);
		Assert.assertEquals(productIdealPrice, 540.0, 0.0);
	}

	@Test
	@PrepareForTest(PropertiesFileReader.class)
	public void testCalculateProductIdealPriceMockNumberOfHigherElementsToRemoveIsNegative() {
		List<ProductSurvey> productSurveys = createListOfFiveProductSurveys();
		Properties pricingPropertiesMock = mockPricingProperties("-2", "3", "20");
		PowerMockito.mockStatic(PropertiesFileReader.class);
		try {
			PowerMockito.when(PropertiesFileReader.getInstance()).thenReturn(propertiesFileReaderMock);
			PowerMockito.when(propertiesFileReaderMock.readPropertiesFromFile("pricing-rules.properties")).thenReturn(pricingPropertiesMock);
		} catch (IOException e) {
			logError(e);
		}
		Double productIdealPrice = productPriceCalculator.calculateProductIdealPrice(productSurveys);
		Assert.assertEquals(productIdealPrice, 540.0, 0.0);
	}

	@Test
	@PrepareForTest(PropertiesFileReader.class)
	public void testCalculateProductIdealPriceMockNumberOfLowerElementsToRemoveEqualToZero() {
		List<ProductSurvey> productSurveys = createListOfFiveProductSurveys();
		Properties pricingPropertiesMock = mockPricingProperties("2", "0", "20");
		PowerMockito.mockStatic(PropertiesFileReader.class);
		try {
			PowerMockito.when(PropertiesFileReader.getInstance()).thenReturn(propertiesFileReaderMock);
			PowerMockito.when(propertiesFileReaderMock.readPropertiesFromFile("pricing-rules.properties")).thenReturn(pricingPropertiesMock);
		} catch (IOException e) {
			logError(e);
		}
		Double productIdealPrice = productPriceCalculator.calculateProductIdealPrice(productSurveys);
		Assert.assertEquals(productIdealPrice, 240.0, 0.0);
	}

	@Test
	@PrepareForTest(PropertiesFileReader.class)
	public void testCalculateProductIdealPriceMockNumberOfLowerElementsToRemoveIsNegative() {
		List<ProductSurvey> productSurveys = createListOfFiveProductSurveys();
		Properties pricingPropertiesMock = mockPricingProperties("2", "-3", "20");
		PowerMockito.mockStatic(PropertiesFileReader.class);
		try {
			PowerMockito.when(PropertiesFileReader.getInstance()).thenReturn(propertiesFileReaderMock);
			PowerMockito.when(propertiesFileReaderMock.readPropertiesFromFile("pricing-rules.properties")).thenReturn(pricingPropertiesMock);
		} catch (IOException e) {
			logError(e);
		}
		Double productIdealPrice = productPriceCalculator.calculateProductIdealPrice(productSurveys);
		Assert.assertEquals(productIdealPrice, 240.0, 0.0);
	}

	@Test
	@PrepareForTest(PropertiesFileReader.class)
	public void testCalculateProductIdealPriceMockNumberOfHigherAndLowerElementsToRemoveIsZero() {
		List<ProductSurvey> productSurveys = createListOfFiveProductSurveys();
		Properties pricingPropertiesMock = mockPricingProperties("0", "0", "20");
		PowerMockito.mockStatic(PropertiesFileReader.class);
		try {
			PowerMockito.when(PropertiesFileReader.getInstance()).thenReturn(propertiesFileReaderMock);
			PowerMockito.when(propertiesFileReaderMock.readPropertiesFromFile("pricing-rules.properties")).thenReturn(pricingPropertiesMock);
		} catch (IOException e) {
			logError(e);
		}
		Double productIdealPrice = productPriceCalculator.calculateProductIdealPrice(productSurveys);
		Assert.assertEquals(productIdealPrice, 360.0, 0.0);
	}

	@Test
	@PrepareForTest(PropertiesFileReader.class)
	public void testCalculateProductIdealPriceMockNumberOfHigherAndLowerElementsToRemoveIsNegative() {
		List<ProductSurvey> productSurveys = createListOfFiveProductSurveys();
		Properties pricingPropertiesMock = mockPricingProperties("-2", "-3", "20");
		PowerMockito.mockStatic(PropertiesFileReader.class);
		try {
			PowerMockito.when(PropertiesFileReader.getInstance()).thenReturn(propertiesFileReaderMock);
			PowerMockito.when(propertiesFileReaderMock.readPropertiesFromFile("pricing-rules.properties")).thenReturn(pricingPropertiesMock);
		} catch (IOException e) {
			logError(e);
		}
		Double productIdealPrice = productPriceCalculator.calculateProductIdealPrice(productSurveys);
		Assert.assertEquals(productIdealPrice, 360.0, 0.0);
	}

	@Test
	@PrepareForTest(PropertiesFileReader.class)
	public void testCalculateProductIdealPriceMockPercentageToAddIsNegative() {
		List<ProductSurvey> productSurveys = createListOfFiveProductSurveys();
		Properties pricingPropertiesMock = mockPricingProperties("1", "1", "-20");
		PowerMockito.mockStatic(PropertiesFileReader.class);
		try {
			PowerMockito.when(PropertiesFileReader.getInstance()).thenReturn(propertiesFileReaderMock);
			PowerMockito.when(propertiesFileReaderMock.readPropertiesFromFile("pricing-rules.properties")).thenReturn(pricingPropertiesMock);
		} catch (IOException e) {
			logError(e);
		}
		Double productIdealPrice = productPriceCalculator.calculateProductIdealPrice(productSurveys);
		Assert.assertEquals(productIdealPrice, 240.0, 0.0);
	}

	@Test
	@PrepareForTest(PropertiesFileReader.class)
	public void testCalculateProductIdealPriceMockPercentageToAddIsNegativeLessThanHundred() {
		List<ProductSurvey> productSurveys = createListOfFiveProductSurveys();
		Properties pricingPropertiesMock = mockPricingProperties("1", "1", "-120");
		PowerMockito.mockStatic(PropertiesFileReader.class);
		try {
			PowerMockito.when(PropertiesFileReader.getInstance()).thenReturn(propertiesFileReaderMock);
			PowerMockito.when(propertiesFileReaderMock.readPropertiesFromFile("pricing-rules.properties")).thenReturn(pricingPropertiesMock);
		} catch (IOException e) {
			logError(e);
		}
		Double productIdealPrice = productPriceCalculator.calculateProductIdealPrice(productSurveys);
		Assert.assertEquals(productIdealPrice, 300.0, 0.0);
	}

	@Test
	@PrepareForTest(PropertiesFileReader.class)
	public void testCalculateProductIdealPriceMockPercentageToAddGreaterThanHundred() {
		List<ProductSurvey> productSurveys = createListOfFiveProductSurveys();
		Properties pricingPropertiesMock = mockPricingProperties("1", "1", "120");
		PowerMockito.mockStatic(PropertiesFileReader.class);
		try {
			PowerMockito.when(PropertiesFileReader.getInstance()).thenReturn(propertiesFileReaderMock);
			PowerMockito.when(propertiesFileReaderMock.readPropertiesFromFile("pricing-rules.properties")).thenReturn(pricingPropertiesMock);
		} catch (IOException e) {
			logError(e);
		}
		Double productIdealPrice = productPriceCalculator.calculateProductIdealPrice(productSurveys);
		Assert.assertEquals(productIdealPrice, 660.0, 0.0);
	}

	private Properties mockPricingProperties(String numberOfHighestValuesToBeRemoved, String numberOfLowestValuesToBeRemoved, String percentageToAdd) {
		Properties pricingPropertiesMock = new Properties();
		pricingPropertiesMock.setProperty("numberOfHighestValuesToBeRemoved", numberOfHighestValuesToBeRemoved);
		pricingPropertiesMock.setProperty("numberOfLowestValuesToBeRemoved", numberOfLowestValuesToBeRemoved);
		pricingPropertiesMock.setProperty("percentageToAdd", percentageToAdd);
		return pricingPropertiesMock;
	}

	private List<ProductSurvey> createListOfFiveProductSurveys() {
		List<ProductSurvey> productSurveys = new ArrayList<ProductSurvey>();
		Product product = new Product();
		productSurveys.add(createProductSurvey(1, product, 1, 100.00, "test"));
		productSurveys.add(createProductSurvey(1, product, 1, 200.00, "test"));
		productSurveys.add(createProductSurvey(1, product, 1, 300.00, "test"));
		productSurveys.add(createProductSurvey(1, product, 1, 400.00, "test"));
		productSurveys.add(createProductSurvey(1, product, 1, 500.00, "test"));

		return productSurveys;
	}

	private ProductSurvey createProductSurvey(int surveyId, Product product, int storeId, double productPrice, String notes) {
		ProductSurvey productSurvey = new ProductSurvey();
		productSurvey.setSurveyId(surveyId);
		productSurvey.setProduct(product);
		productSurvey.setStoreId(storeId);
		productSurvey.setProductPrice(productPrice);
		productSurvey.setNotes(notes);
		return productSurvey;
	}

	private void logError(IOException e) {
		e.printStackTrace();
	}

}
