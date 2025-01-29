package com.qa.product.api.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.pojo.Product;
import com.qa.api.utils.JSONMapperUtil;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ProductAPIWithDeserializationtest extends BaseTest {
	
	
	@Test
	public void getProductAPIwithDeserialization() {
		
		
		Response response = restclient.get(BASE_URL_PRODUCTS,ENDPOINT_PRODUCTS,null, null, null, AuthType.NO_AUTH, ContentType.JSON);
		Assert.assertEquals(response.statusCode(), 200);
		
		Product[] product = JSONMapperUtil.deserialization(response, Product[].class);
		
		for (Product p : product) {
			System.out.println(p.getId());
			System.out.println(p.getCategory());
			System.out.println(p.getDescription());
			System.out.println(p.getImage());
			System.out.println(p.getTitle());
			System.out.println(p.getRating().getRate());
			System.out.println(p.getRating().getCount());
			
			System.out.println("============================"); 
		}
	}

}
