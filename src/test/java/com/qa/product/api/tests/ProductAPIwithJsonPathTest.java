package com.qa.product.api.tests;

import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.utils.JsonPathUtil;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ProductAPIwithJsonPathTest extends BaseTest {
	
	
	@Test
	public void getProductTest() {
		Response response = restclient.get(BASE_URL_PRODUCTS,ENDPOINT_PRODUCTS,null, null, null, AuthType.NO_AUTH, ContentType.JSON);
		Assert.assertEquals(response.statusCode(), 200);
		
		List<Number> prices = JsonPathUtil.readList(response, "$[?(@.price > 50)].price");
		System.out.println(prices);
		
		List<Number> id = JsonPathUtil.readList(response, "$[?(@.price > 50)].id");
		System.out.println(id);
		
		List<Double> rate = JsonPathUtil.readList(response, "$[?(@.price > 50)].rating.rate");
		System.out.println(rate);
		
		List<Integer> count = JsonPathUtil.readList(response, "$[?(@.price > 50)].rating.count");
		System.out.println(count);
		
		System.out.println("============================");

		
		//get Map
		List<Map<String, Object>> jewelrylist = JsonPathUtil.readListofMap(response, "$[?(@.category=='jewelery')].['title','price']");
		System.out.println(jewelrylist );
		System.out.println(jewelrylist.size());
		
		
		for (Map<String, Object> map : jewelrylist) {
			String title =(String) map.get("title");
			Number price =(Number) map.get("price");
			System.out.println("title:" +title);
			System.out.println("price:" +price);
			System.out.println("============================");
		}
		
		//get min price
		Double minPrice = JsonPathUtil.read(response, "min($[*].price)");
		System.out.println("Minimum price ===>"+minPrice);
	}

}
