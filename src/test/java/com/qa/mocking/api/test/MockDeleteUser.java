package com.qa.mocking.api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.mocking.APIMocks;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class MockDeleteUser extends BaseTest {
	
	
	
	@Test
	public void deleteUserMockAPITest() {
		
		APIMocks.deleteUser(ENDPOINT_MOCK_USERS_DELETE);
		Response response = restclient.delete(BASE_URL_LOCALHOST_PORT, ENDPOINT_MOCK_USERS_DELETE, null, null, AuthType.NO_AUTH, ContentType.JSON);
		
		
		response.then()
				.assertThat()
				.statusCode(204);
		
	}
}
