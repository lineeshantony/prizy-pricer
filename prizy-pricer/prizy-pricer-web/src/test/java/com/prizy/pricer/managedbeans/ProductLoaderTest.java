package com.prizy.pricer.managedbeans;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.prizy.pricer.dao.ProductDao;
import com.prizy.pricer.dao.ProductSurveyDao;
import com.prizy.pricer.entities.Product;
import com.prizy.pricer.entities.ProductSurvey;

@RunWith(PowerMockRunner.class)
@PrepareForTest(ProductLoader.class)
public class ProductLoaderTest {

	@Mock
	private ProductDao productDaoMock;
	@Mock
	private ProductSurvey productSurveyMock;
	@Mock
	private ProductSurveyDao productSurveyDaoMock;

	@InjectMocks
	private ProductLoader productLoader;

	@Test
	public void testSubmitProductLoader() {
		String productBarCode = "0000000001";
		productLoader.setNotes("notes");
		productLoader.setProductBarCode(productBarCode);
		productLoader.setProductPrice(100.00);
		productLoader.setStoreId(1);
		PowerMockito.when(productDaoMock.getSingleItemWithBarCode(productBarCode)).thenReturn(mockProduct(1, "0000000001", "Productname1", "ProductDesc1"));
		PowerMockito.doNothing().when(productSurveyDaoMock).addProductSurvey(productSurveyMock);
		Assert.assertEquals("submitacknowledgement", productLoader.submitProductLoader());
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
