package com.qa.basicauth.api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class BasicAuthTest extends BaseTest{
	
	@Test
	public void authTest() {
		
		Response response = restclient.get(BASE_URL_BASIC_AUTH, ENDPOINT_BASIC_AUTH, null, null, AuthType.BASIC_AUTH, ContentType.ANY);
		boolean flag = response.asString().contains("Congratulations! You must have the proper credentials");
		Assert.assertTrue(flag);
	}

}
