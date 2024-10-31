package com.qa.schemavalidation.api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.pojo.User;
import com.qa.api.utils.SchemaValidator;
import com.qa.api.utils.UtilString;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class GorestSchemaTest extends BaseTest {

	@Test
	public void userSchemaAPITest() {

		User user = User.builder().name("sailesh").email(UtilString.getRandomEmailId()).gender("male")
				.status("inactive").build();

		// POST
		Response postresponse = restclient.post(BASE_URL_GOREST, ENDPOINT_GOREST_USERS, user, null, null,
				AuthType.BEARER_TOKEN, ContentType.JSON);
		Assert.assertEquals(postresponse.getStatusCode(), 201);

		// fetch user
		String userId = postresponse.jsonPath().getString("id");
		System.out.println("User ID ===> " + userId);

		// GET
		Response getresponse = restclient.get(BASE_URL_GOREST, ENDPOINT_GOREST_USERS + userId, null, null,
				AuthType.BEARER_TOKEN, ContentType.JSON);
		
		Assert.assertTrue(SchemaValidator.validateschema(getresponse, "schema/user-schema.json"));

	}

}
