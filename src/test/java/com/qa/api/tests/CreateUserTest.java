package com.qa.api.tests;

import java.io.File;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.pojo.User;
import com.qa.api.utils.UtilString;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class CreateUserTest extends BaseTest {
	
	@DataProvider
	public Object[][] getUserData() {
		return new Object[][] {
			{"sailesh","male","active"},
			{"asha","male","inactive"},
			{"prathiksha","male","active"},
			{"viniksha","male","active"}
		};
	}
	
	@Test(dataProvider = "getUserData")
	public void createUserTest(String name, String gender,String status) {

		User user = new User(null,name,UtilString.getRandomEmailId(),gender,status);
		Response response = restclient.post(BASE_URL_GOREST,"/public/v2/users", user, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		Assert.assertEquals(response.getStatusCode(), 201);
	}

	
	@Test
	public void createUserWithBuildeTest() {
		
		User user = User.builder()
				.name("sailesh")
				.email(UtilString.getRandomEmailId())
				.gender("male")
				.status("inactive")
				.build();
		
		//POST
		Response postresponse = restclient.post(BASE_URL_GOREST,ENDPOINT_GOREST_USERS, user, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		Assert.assertEquals(postresponse.getStatusCode(), 201);
		
		
		//fetch user
		String userId = postresponse.jsonPath().getString("id");
		System.out.println("User ID ===> "+userId);
		
		//GET
		Response getresponse = restclient.get(BASE_URL_GOREST,ENDPOINT_GOREST_USERS+userId, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		Assert.assertEquals(getresponse.statusCode(), 200);
		Assert.assertEquals(getresponse.jsonPath().getString("id"), userId);
		Assert.assertEquals(getresponse.jsonPath().getString("name"), user.getName());
		Assert.assertEquals(getresponse.jsonPath().getString("email"), user.getEmail());
	}
	
	@Test(enabled = false)
	public void createUserWithJsonFileTest() {
		
		File userJsonFile = new File(".\\src\\resources\\jsons\\Users.json");
		
		Response response = restclient.post(BASE_URL_GOREST,ENDPOINT_GOREST_USERS, userJsonFile, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		Assert.assertEquals(response.getStatusCode(), 201);
	}
}
