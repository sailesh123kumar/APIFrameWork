package com.qa.api.tests;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.utils.UtilString;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserAPITestwithDynamicJsonFiletest extends BaseTest {
	
	
	@Test
	public void createUserWithJsonFileTest() {
		
		String jsonFilePath="src/resources/jsons/Users.json";
		
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			JsonNode userNode = mapper.readTree(Files.readAllBytes(Paths.get(jsonFilePath)));
			//update the email id:
			ObjectNode obj = ((ObjectNode)userNode);
			obj.put("email",UtilString.getRandomEmailId() );
			
			//convert jsonnode o jsonString
			String updatedJsonstring = mapper.writeValueAsString(userNode);
			System.out.println(updatedJsonstring);
			
			Response postResponse = restclient.post(BASE_URL_GOREST, ENDPOINT_GOREST_USERS, updatedJsonstring,null, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
			Assert.assertEquals(postResponse.statusCode(), 201);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
