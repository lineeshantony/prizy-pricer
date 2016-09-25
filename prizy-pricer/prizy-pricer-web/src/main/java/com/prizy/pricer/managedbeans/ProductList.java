package com.prizy.pricer.managedbeans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.prizy.pricer.dao.ProductDao;
import com.prizy.pricer.entities.Product;

@ManagedBean
public class ProductList {

	private List<Product> products;
	private String searchBarCode;
	private String selectedBarCode;
	private ProductDao productDao = new ProductDao();

	public String searchProductsWithBarCode() {
		return "productlist";
	}

	public List<Product> getProducts() {
		if (getSearchBarCode() == null || ("").equals(getSearchBarCode().trim())) {
			return retrieveAllProducts();
		}
		return retrieveProductsForBarCode();
	}

	private List<Product> retrieveAllProducts() {
		return productDao.getAllItems();
	}

	private List<Product> retrieveProductsForBarCode() {
		products = productDao.getItemsWithBarCode(searchBarCode);
		return products;
	}

	public String viewProduct() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
		session.setAttribute("selectedBarCode", selectedBarCode);
		return "productviewer";
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public String getSearchBarCode() {
		return searchBarCode;
	}

	public void setSearchBarCode(String searchBarCode) {
		this.searchBarCode = searchBarCode;
	}

	public String getSelectedBarCode() {
		return selectedBarCode;
	}

	public void setSelectedBarCode(String selectedBarCode) {
		this.selectedBarCode = selectedBarCode;
	}
}
