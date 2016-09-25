package com.prizy.pricer.managedbeans;

import javax.faces.bean.ManagedBean;

import com.prizy.pricer.dao.ProductDao;
import com.prizy.pricer.dao.ProductSurveyDao;
import com.prizy.pricer.entities.Product;
import com.prizy.pricer.entities.ProductSurvey;

@ManagedBean
public class ProductLoader {

	private Integer storeId;
	private String productBarCode;
	private Double productPrice;
	private String notes;
	private ProductDao productDao = new ProductDao();
	private ProductSurveyDao productSurveyDao = new ProductSurveyDao();

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public String getProductBarCode() {
		return productBarCode;
	}

	public void setProductBarCode(String productBarCode) {
		this.productBarCode = productBarCode;
	}

	public Double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(Double productPrice) {
		this.productPrice = productPrice;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String submitProductLoader() {
		ProductSurvey productSurvey = new ProductSurvey();
		productSurvey.setSurveyId(productSurveyDao.selectMaxSurveyId() + 1);
		Product product = productDao.getSingleItemWithBarCode(productBarCode);
		productSurvey.setProduct(product);
		if (Math.abs(productPrice) != 0) {
			productPrice = Math.round(productPrice * 100.0) / 100.0;
		}
		productSurvey.setProductPrice(productPrice);
		productSurvey.setStoreId(storeId);
		productSurvey.setNotes(notes);

		productSurveyDao.addProductSurvey(productSurvey);
		return "submitacknowledgement";
	}
}
