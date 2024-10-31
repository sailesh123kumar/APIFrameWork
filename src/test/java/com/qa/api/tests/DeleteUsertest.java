package com.qa.api.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.pojo.User;
import com.qa.api.utils.UtilString;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class DeleteUsertest  extends BaseTest{
	
	@Test
	public void deleteUserWithBuiderTest() {
		User user = User.builder()
				.name("sailesh")
				.email(UtilString.getRandomEmailId())
				.gender("male")
				.status("inactive")
				.build();
		
		//1.POST: Create the user
		Response postresponse = restclient.post(BASE_URL_GOREST,ENDPOINT_GOREST_USERS, user, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		Assert.assertEquals(postresponse.getStatusCode(), 201);
		
		
		//fetch userId
		String userId = postresponse.jsonPath().getString("id");
		System.out.println("User ID ===> "+userId);
		
		//2.GET: fetch the same user using userId
		Response getresponse = restclient.get(BASE_URL_GOREST,ENDPOINT_GOREST_USERS+userId, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		Assert.assertEquals(getresponse.statusCode(), 200);
		Assert.assertEquals(getresponse.jsonPath().getString("id"), userId);
		
		//update the user detail using the setter
		user.setEmail(UtilString.getRandomEmailId());
		
		//3.DELETE: update the same user using the same userid
		Response delresponse = restclient.delete(BASE_URL_GOREST,ENDPOINT_GOREST_USERS+userId, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		Assert.assertEquals(delresponse.statusCode(), 204);
		
		//4.GET: recheck and fetch the user with the same user id
		Response regetresponse = restclient.get(BASE_URL_GOREST,ENDPOINT_GOREST_USERS+userId, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		Assert.assertEquals(regetresponse.statusCode(), 404);
		Assert.assertEquals(regetresponse.jsonPath().getString("message"), "Resource not found");
		
	
	}

}
