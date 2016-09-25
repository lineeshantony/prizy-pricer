package com.prizy.pricer.managedbeans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

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
import com.prizy.pricer.price.calculator.ProductPriceCalculator;

@RunWith(PowerMockRunner.class)
@PrepareForTest(ProductViewer.class)
public class ProductViewerTest {

	@Mock
	private FacesContext facesContextMock;
	@Mock
	private ExternalContext externalContextMock;
	@Mock
	private HttpSession httpSession;
	@Mock
	private ProductDao productDaoMock;
	@Mock
	private ProductSurveyDao productSurveyDaoMock;
	@Mock
	private ProductPriceCalculator productPriceCalculatorMock;

	@InjectMocks
	private ProductViewer productViewer;

	@Test
	@PrepareForTest({ ProductViewer.class, FacesContext.class })
	public void testProductViewerConstructor() {
		String productBarCode = "0000000001";
		PowerMockito.mockStatic(FacesContext.class);
		PowerMockito.when(FacesContext.getCurrentInstance()).thenReturn(facesContextMock);
		PowerMockito.when(facesContextMock.getExternalContext()).thenReturn(externalContextMock);
		PowerMockito.when(externalContextMock.getSession(true)).thenReturn(httpSession);
		PowerMockito.when(httpSession.getAttribute("selectedBarCode")).thenReturn(productBarCode);
		PowerMockito.when(productDaoMock.getSingleItemWithBarCode(productBarCode)).thenReturn(mockProduct(1, "0000000001", "Productname1", "ProductDesc1"));
		PowerMockito.when(productSurveyDaoMock.getAllItemsForProductId(1)).thenReturn(createListOfFiveProductSurveys());
		PowerMockito.when(productPriceCalculatorMock.calculateAveragePrice(createListOfFiveProductSurveys())).thenReturn(300.0);
		PowerMockito.when(productPriceCalculatorMock.calculateProductIdealPrice(createListOfFiveProductSurveys())).thenReturn(360.0);
		Assert.assertEquals(productBarCode, productViewer.getProductBarCode());
	}

	private Product mockProduct(int productId, String productBarCode, String productName, String productDescription) {
		Product product = new Product();
		product.setProductId(productId);
		product.setProductBarCode(productBarCode);
		product.setProductName(productName);
		product.setProductDescription(productDescription);
		return product;
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
}
