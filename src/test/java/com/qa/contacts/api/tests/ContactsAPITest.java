package com.qa.contacts.api.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.manager.ConfigManager;
import com.qa.api.pojo.ContactsCredentials;
import com.qa.api.pojo.ContactsCredentials.ContactsCredentialsBuilder;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ContactsAPITest extends BaseTest{
	
	private String tokenId;
	
	@BeforeMethod
	public void generateToken() {
		
		ContactsCredentials contactsCred = ContactsCredentials.builder()
																	.email("saileshkumar17893@gmail.com")
																	.password("123456789")
																	.build();
		
		Response postReponse = restclient.post(BASE_URL_CONTACTS,ENDPOINT_CONTACTS_LOGIN, contactsCred,null, null, null, AuthType.NO_AUTH, ContentType.JSON);
		tokenId = postReponse.jsonPath().getString("token");
		System.out.println("Token ID is ====>"+tokenId);
		ConfigManager.set("contacts_bearer_token", tokenId);
		
	}
	
	
	@Test
	public void getContacts() {
		
		Response getResponse = restclient.get(BASE_URL_CONTACTS,ENDPOINT_CONTACTS_CONTACTS,null, null, null, AuthType.CONTACTS_BEARER_TOKEN, ContentType.JSON);
		getResponse.prettyPrint();
		
	}

}
