package com.prizy.pricer.managedbeans;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.prizy.pricer.dao.ProductDao;
import com.prizy.pricer.entities.Product;

import org.junit.Assert;

@RunWith(PowerMockRunner.class)
@PrepareForTest(ProductList.class)
public class ProductListTest {

	@Mock
	private ProductDao productDaoMock;

	@InjectMocks
	private ProductList productList;

	@Test
	@PrepareForTest(ProductDao.class)
	public void testGetProductsForSearchBarCodeIsNull() {
		productList.setSearchBarCode(null);
		List<Product> products = getListOfFiveProducts();
		PowerMockito.when(productDaoMock.getAllItems()).thenReturn(products);
		Assert.assertEquals(5, productList.getProducts().size());
	}

	@Test
	@PrepareForTest(ProductDao.class)
	public void testGetProductsForSearchBarCodeIsBlank() {
		productList.setSearchBarCode("");
		List<Product> products = getListOfFiveProducts();
		PowerMockito.when(productDaoMock.getAllItems()).thenReturn(products);
		Assert.assertEquals(5, productList.getProducts().size());
	}

	@Test
	@PrepareForTest(ProductDao.class)
	public void testGetProductsForSearchBarCodeValue() {
		String barCode = "0000000001";
		productList.setSearchBarCode(barCode);
		List<Product> products = getListOfProductsForBarCode();
		PowerMockito.when(productDaoMock.getItemsWithBarCode(barCode)).thenReturn(products);
		Assert.assertEquals(1, productList.getProducts().size());
	}

	private List<Product> getListOfProductsForBarCode() {
		List<Product> products = new ArrayList<Product>();
		products.add(mockProduct(1, "0000000001", "Product1", "ProductDescription1"));
		return products;
	}

	private List<Product> getListOfFiveProducts() {
		List<Product> products = new ArrayList<Product>();
		products.add(mockProduct(1, "0000000001", "Product1", "ProductDescription1"));
		products.add(mockProduct(2, "0000000002", "Product2", "ProductDescription2"));
		products.add(mockProduct(3, "0000000003", "Product3", "ProductDescription3"));
		products.add(mockProduct(4, "0000000004", "Product4", "ProductDescription4"));
		products.add(mockProduct(5, "0000000005", "Product5", "ProductDescription5"));
		return products;
	}

	private Product mockProduct(int productId, String productBarCode, String productName, String productDescription) {
		Product product = new Product();
		product.setProductId(productId);
		product.setProductBarCode(productBarCode);
		product.setProductName(productName);
		product.setProductDescription(productDescription);
		return product;
	}
}
