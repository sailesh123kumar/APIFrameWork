package com.qa.product.api.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ProductsAPIest extends BaseTest {
	@Test
	public void getProductsAPITest() {
		Response response = restclient.get(BASE_URL_PRODUCTS,ENDPOINT_PRODUCTS, null, null, AuthType.NO_AUTH, ContentType.JSON);
		Assert.assertEquals(response.statusCode(), 200);

	}

	@Test
	public void getproductLimitTest() {
		Map<String, String> queryParam = Map.of("limit" , "5");
		
		Response response = restclient.get(BASE_URL_PRODUCTS,ENDPOINT_PRODUCTS, queryParam, null, AuthType.NO_AUTH, ContentType.JSON);
		Assert.assertEquals(response.statusCode(), 200);
	}
}
