package com.qa.mocking.api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.mocking.APIMocks;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class MockingProductAPITest extends BaseTest {

	
	@Test
	public void getDummyProductswithJsonFileMockServertest() {
		APIMocks.getStubwithJsonFile(ENDPOINT_MOCK_PRODUCTS_GET, "product.json");
		
		Response response = restclient.get(BASE_URL_LOCALHOST_PORT, ENDPOINT_MOCK_PRODUCTS_GET,null, null, null, AuthType.NO_AUTH, ContentType.JSON);
		Assert.assertEquals(response.statusCode(), 200);
	} 

}
