package com.qa.api.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class GetUserTest extends BaseTest {
	
	@Test
	public void getSingleUsertest() {
		Map<String, String> queryParam = new HashMap<String, String>();
		queryParam.put("name", "naveen");
		queryParam.put("status", "active");
		
		Response response = restclient.get(BASE_URL_GOREST,ENDPOINT_GOREST_USERS,null, queryParam, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		Assert.assertEquals(response.statusCode(), 200);
		
	}

}
