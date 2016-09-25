package com.prizy.pricer.util;

import java.util.Comparator;
import com.prizy.pricer.entities.ProductSurvey;

public class ProductSurveyPriceComparator implements Comparator<ProductSurvey> {

	@Override
	public int compare(ProductSurvey productSurvey1, ProductSurvey productSurvey2) {
		if (productSurvey1.getProductPrice() > productSurvey2.getProductPrice()) {
			return 1;
		} else {
			return -1;
		}
	}

}
