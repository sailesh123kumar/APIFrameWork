package com.qa.mocking.api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.mocking.APIMocks;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class MockCreateUserAPITest extends BaseTest{

	@Test
	public void createUserMockAPITest() {
		
		APIMocks.postCreateUser(ENDPOINT_MOCK_USERS_GET, "{\r\n"
				+ "    \"bookId\": 102,\r\n"
				+ "    \"bookName\": \"comics\"\r\n"
				+ "}");
		
		String dummyJson="{\"bookName\": \"comics\"}";
		
		Response post = restclient.post(BASE_URL_LOCALHOST_PORT, ENDPOINT_MOCK_USERS_GET, dummyJson,null, null, null, AuthType.NO_AUTH, ContentType.JSON);
		Assert.assertEquals(post.statusCode(), 201);
		Assert.assertEquals(post.statusLine(), "HTTP/1.1 201 Created");
	}
	
	
	@Test
	public void createUserMockAPIWithJsonFileTest() {
		
		APIMocks. postCreateUserWithJsonFile(ENDPOINT_MOCK_USERS_GET, "Users.json");
		
		String dummyJson="{\"bookName\": \"comics\"}";
		
		Response post = restclient.post(BASE_URL_LOCALHOST_PORT, ENDPOINT_MOCK_USERS_GET, dummyJson,null, null, null, AuthType.NO_AUTH, ContentType.JSON);
		Assert.assertEquals(post.statusCode(), 201);
		Assert.assertEquals(post.statusLine(), "HTTP/1.1 201 Created");
	}
}
