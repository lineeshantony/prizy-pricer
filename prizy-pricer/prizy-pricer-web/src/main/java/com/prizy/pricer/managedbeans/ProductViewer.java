package com.prizy.pricer.managedbeans;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.prizy.pricer.dao.ProductDao;
import com.prizy.pricer.dao.ProductSurveyDao;
import com.prizy.pricer.entities.Product;
import com.prizy.pricer.entities.ProductSurvey;
import com.prizy.pricer.price.calculator.ProductPriceCalculator;
import com.prizy.pricer.util.ProductSurveyPriceComparator;

@ManagedBean
public class ProductViewer {

	private String productBarCode;
	private String productDescription;
	private Double productAveragePrice;
	private Double productLowestPrice;
	private Double productHighestPrice;
	private Double productIdealPrice;
	private ProductDao productDao = new ProductDao();
	private ProductSurveyDao productSurveyDao = new ProductSurveyDao();
	private ProductPriceCalculator productPriceCalculator = new ProductPriceCalculator();

	public ProductViewer() {

	}

	private Product retrieveProduct(String productBarCode) {
		return productDao.getSingleItemWithBarCode(productBarCode);
	}

	private List<ProductSurvey> retrieveProductSurveys(Integer productId) {
		return productSurveyDao.getAllItemsForProductId(productId);
	}

	public String getProductBarCode() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
		productBarCode = (String) session.getAttribute("selectedBarCode");
		Product product = retrieveProduct(productBarCode);
		productDescription = product.getProductDescription();
		List<ProductSurvey> productSurveysDB = retrieveProductSurveys(product.getProductId());
		if (productSurveysDB == null || productSurveysDB.isEmpty()) {
			productLowestPrice = 0.0;
			productHighestPrice = 0.0;
			productAveragePrice = 0.0;
			productIdealPrice = 0.0;
		} else {
			List<ProductSurvey> productSurveys = new ArrayList<ProductSurvey>(productSurveysDB);
			Collections.copy(productSurveys, productSurveysDB);
			Collections.sort(productSurveys, new ProductSurveyPriceComparator());
			productLowestPrice = productSurveys.get(0).getProductPrice();
			productHighestPrice = productSurveys.get(productSurveys.size() - 1).getProductPrice();
			productAveragePrice = productPriceCalculator.calculateAveragePrice(productSurveys);
			productIdealPrice = productPriceCalculator.calculateProductIdealPrice(productSurveys);
		}
		return productBarCode;
	}

	public void setProductBarCode(String productBarCode) {
		this.productBarCode = productBarCode;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public Double getProductAveragePrice() {
		return productAveragePrice;
	}

	public Double getProductLowestPrice() {
		return productLowestPrice;
	}

	public Double getProductHighestPrice() {
		return productHighestPrice;
	}

	public Double getProductIdealPrice() {
		return productIdealPrice;
	}

	public ProductDao getProductDao() {
		return productDao;
	}

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	public ProductSurveyDao getProductSurveyDao() {
		return productSurveyDao;
	}

	public void setProductSurveyDao(ProductSurveyDao productSurveyDao) {
		this.productSurveyDao = productSurveyDao;
	}

	public ProductPriceCalculator getProductPriceCalculator() {
		return productPriceCalculator;
	}

	public void setProductPriceCalculator(ProductPriceCalculator productPriceCalculator) {
		this.productPriceCalculator = productPriceCalculator;
	}

}
