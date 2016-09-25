package com.prizy.pricer.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "product_survey")
@NamedQuery(name = "ProductSurvey.findAll", query = "SELECT ps FROM ProductSurvey ps")
public class ProductSurvey implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "survey_id")
	private int surveyId;

	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;

	@Column(name = "store_id")
	private int storeId;

	@Column(name = "product_price")
	private Double productPrice;

	@Column(name = "notes")
	private String notes;

	public int getSurveyId() {
		return surveyId;
	}

	public void setSurveyId(int surveyId) {
		this.surveyId = surveyId;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getStoreId() {
		return storeId;
	}

	public void setStoreId(int storeId) {
		this.storeId = storeId;
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

}
