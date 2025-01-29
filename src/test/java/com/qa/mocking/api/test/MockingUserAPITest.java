package com.qa.mocking.api.test;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.mocking.APIMocks;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class MockingUserAPITest extends BaseTest {

	@Test
	public void getDummyUserwitMockServerTest() {
		APIMocks.getStubUser(ENDPOINT_MOCK_USERS_GET, "{\r\n" + "    \"bookName\": \"sailesh Mock server\",\r\n" + "}");
		
		Response response = restclient.get(BASE_URL_LOCALHOST_PORT, ENDPOINT_MOCK_USERS_GET,null, null, null, AuthType.NO_AUTH, ContentType.JSON);
		Assert.assertEquals(response.statusCode(), 200);
	}
	
	
	@Test
	public void getDummyUserwitJsonFileMockServerTest() {
		APIMocks.getStubwithJsonFile(ENDPOINT_MOCK_USERS_GET, "Users.json");
		
		Response response = restclient.get(BASE_URL_LOCALHOST_PORT, ENDPOINT_MOCK_USERS_GET,null, null, null, AuthType.NO_AUTH, ContentType.JSON);
		Assert.assertEquals(response.statusCode(), 200);
	}
	
	
	@Test
	public void getDummyUserwitJsonFileandQueryParamTest() {
		APIMocks.getStubwithJsonFileAndQueryParam(ENDPOINT_MOCK_USERS_GET, "Users.json");
		
		Map<String, String> queryParam = Map.of("name","sailesh");
		
		Response response = restclient.get(BASE_URL_LOCALHOST_PORT, ENDPOINT_MOCK_USERS_GET,null, queryParam, null, AuthType.NO_AUTH, ContentType.JSON);
		Assert.assertEquals(response.statusCode(), 200);
		Assert.assertEquals(response.statusLine(), "HTTP/1.1 200 OK");
	}
	
	
}
