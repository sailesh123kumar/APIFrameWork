package com.qa.reqres.api.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ReqResTest extends BaseTest {
	
	@Test
	public void getSingleUsertest() {
		
		Map<String, String> queryParam = Map.of("page","2");
		
		Response response = restclient.get(BASE_URL_REQ_RES,"/api/users",null, queryParam, null, AuthType.NO_AUTH, ContentType.JSON);
		Assert.assertEquals(response.statusCode(), 200);
		
	}

}
