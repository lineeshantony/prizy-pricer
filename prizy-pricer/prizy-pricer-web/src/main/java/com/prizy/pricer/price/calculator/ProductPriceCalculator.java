package com.prizy.pricer.price.calculator;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import com.prizy.pricer.entities.ProductSurvey;
import com.prizy.pricer.util.ProductSurveyPriceComparator;
import com.prizy.pricer.util.PropertiesFileReader;

public class ProductPriceCalculator {

	public Double calculateProductIdealPrice(List<ProductSurvey> productSurveys) {
		if (productSurveys == null || productSurveys.isEmpty()) {
			return 0.0;
		}
		Integer numberOfHighestValuesToBeRemoved = 0;
		Integer numberOfLowestValuesToBeRemoved = 0;
		Double percentageToAdd = 0.0;
		try {
			Properties pricingProperties = PropertiesFileReader.getInstance().readPropertiesFromFile("pricing-rules.properties");
			numberOfHighestValuesToBeRemoved = Integer.valueOf(pricingProperties.getProperty("numberOfHighestValuesToBeRemoved"));
			numberOfLowestValuesToBeRemoved = Integer.valueOf(pricingProperties.getProperty("numberOfLowestValuesToBeRemoved"));
			percentageToAdd = Double.valueOf(pricingProperties.getProperty("percentageToAdd"));
		} catch (IOException e) {
			logError(e);
			return 0.0;
		}

		// validateProperties
		if (numberOfHighestValuesToBeRemoved < 0) {
			numberOfHighestValuesToBeRemoved = 0;
		}
		if (numberOfLowestValuesToBeRemoved < 0) {
			numberOfLowestValuesToBeRemoved = 0;
		}
		if (percentageToAdd < -100.00) {
			percentageToAdd = 0.0;
		}

		if (productSurveys.size() <= (numberOfHighestValuesToBeRemoved + numberOfLowestValuesToBeRemoved)) {
			Double averagePrice = calculateAveragePrice(productSurveys);
			return calculatePercentage(percentageToAdd, averagePrice);
		} else {
			Collections.sort(productSurveys, new ProductSurveyPriceComparator());
			List<ProductSurvey> productSurveysForAverage = productSurveys.subList(numberOfLowestValuesToBeRemoved, productSurveys.size() - numberOfHighestValuesToBeRemoved);
			Double averagePrice = calculateAveragePrice(productSurveysForAverage);
			Double productIdealPrice = calculatePercentage(percentageToAdd, averagePrice);
			if (Math.abs(productIdealPrice) != 0) {
				productIdealPrice = Math.round(productIdealPrice * 100.0) / 100.0;
			}
			return productIdealPrice;
		}
	}

	private Double calculatePercentage(Double percentageToAdd, Double averagePrice) {
		if (Math.abs(percentageToAdd) == 0) {
			return averagePrice;
		} else {
			Double percentage = Math.abs(percentageToAdd);
			Double percentageValue = (Double) (averagePrice * (percentage / 100.0f));
			if (percentageToAdd > 0.0) {
				return averagePrice + percentageValue;
			} else {
				return averagePrice - percentageValue;
			}
		}
	}

	public Double calculateAveragePrice(List<ProductSurvey> productSurveys) {
		if (productSurveys == null || productSurveys.isEmpty()) {
			return 0.0;
		}
		Double total = 0.0;
		for (ProductSurvey productSurvey : productSurveys) {
			total = total + productSurvey.getProductPrice();
		}
		if (total > 0.0) {
			Double productAveragePrice = total / productSurveys.size();
			if (Math.abs(productAveragePrice) != 0) {
				productAveragePrice = Math.round(productAveragePrice * 100.0) / 100.0;
			}
			return productAveragePrice;
		}
		return 0.0;
	}

	private void logError(IOException e) {
		e.printStackTrace();
	}

}
