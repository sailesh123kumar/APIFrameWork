package com.qa.api.base;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.qa.api.client.RestClient;
import com.qa.api.mocking.WireMockSetup;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;

public class BaseTest {
	
	//***********************APP BASE URLs***********************************//
	protected static final String BASE_URL_GOREST="https://gorest.co.in"; 
	protected static final String BASE_URL_REQ_RES="https://reqres.in"; 
	protected static final String BASE_URL_PRODUCTS="https://fakestoreapi.com"; 
	protected static final String BASE_URL_CONTACTS="https://thinking-tester-contact-list.herokuapp.com"; 
	protected static final String BASE_URL_BASIC_AUTH="https://the-internet.herokuapp.com"; 
	protected static final String BASE_URL_AMADEUS="https://test.api.amadeus.com"; 
	protected static final String BASE_URL_LOCALHOST_PORT="http://localhost:8089"; 
	
	//***********************APP ENDPOINTS***********************************//
	protected static final String ENDPOINT_GOREST_USERS="/public/v2/users/"; 
	protected static final String ENDPOINT_BASIC_AUTH="/basic_auth"; 
	protected static final String ENDPOINT_PRODUCTS="/products"; 
	protected static final String ENDPOINT_CONTACTS_LOGIN="/users/login"; 
	protected static final String ENDPOINT_CONTACTS_CONTACTS="/contacts"; 
	protected static final String ENDPOINT_REQRES_USERS="/api/users"; 
	protected static final String ENDPOINT_MOCK_USERS_GET="/api/users"; 
	protected static final String ENDPOINT_MOCK_USERS_DELETE="/api/users/1"; 
	protected static final String ENDPOINT_MOCK_PRODUCTS_GET="/api/products"; 
	
	


	protected RestClient restclient;

	@BeforeSuite
	public void initiateAllure() {
		RestAssured.filters(new AllureRestAssured());
	}
	
	
	@BeforeTest
	public void setUp() {
		restclient = new RestClient();
		WireMockSetup.startWireMockServer();
		
	}
	
	
	@AfterTest
	public void stopMockServer() {
		WireMockSetup.stopWireMockServer();
	}
	
	
}
