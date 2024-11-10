package com.qa.api.mocking;

import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.put;
import static com.github.tomakehurst.wiremock.client.WireMock.delete;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;

public class APIMocks {

	/**
	 * this method is used to create a stub for GET calls 
	 * @param endpoint
	 * @param body
	 */
	//***********************create stub/mock for GET calls***********************
	public static void getStubUser(String endpoint, String body) {

		//endpoint = "/api/users"
		//body = 
		/*
		 * "{\r\n" + "    \"bookName\": \"sailesh Mock server\",\r\n" + "}"
		 */
		stubFor(get(urlEqualTo(endpoint))
							.willReturn(aResponse()
											.withStatus(200)
											.withHeader("contentType", "application/json")
											.withBody(body)
									)
							);
	}

	/**
	 * this method is used to create a stub for GET calls with json file which is located in __files folder
	 * @param endpoint
	 * @param body
	 */
	public static void getStubwithJsonFile(String endpoint, String fileName) {
		stubFor(get(urlEqualTo(endpoint))
							.willReturn(aResponse()
											.withStatus(200)
											.withHeader("contentType", "application/json")
											.withBodyFile(fileName)
									)
							);
	}

	
	
	/**
	 * this method is used to create a stub for GET calls with Query Param json file which is located in __files folder
	 * @param endpoint
	 * @param fileName
	 */
	public static void getStubwithJsonFileAndQueryParam(String endpoint, String fileName) {
		stubFor(get(urlPathEqualTo(endpoint))
				.withQueryParam("name", equalTo("sailesh"))
							.willReturn(aResponse()
											.withStatus(200)
											.withHeader("contentType", "application/json")
											.withBodyFile(fileName)
											.withStatusMessage("OK")
									)
							);
	}
	
	
	
	//***********************create stub/mock for POST calls***********************
	
	
	public static void postCreateUser(String endpoint,String body) {
		//body = 
				/*
				 * "{\r\n" + "    \"id\": \"101\",\r\n" + "}"
				 */
		
		stubFor(post(urlEqualTo(endpoint))
				.withHeader("content-Type", equalTo("application/json"))
					.willReturn(aResponse()
							.withStatus(201)
							.withStatusMessage("Created")
							.withBody(body)
							)
				);
		
	}
	
	
	public static void postCreateUserWithJsonFile(String endpoint,String body) {
		stubFor(post(urlEqualTo(endpoint))
				.withHeader("content-Type", equalTo("application/json"))
					.willReturn(aResponse()
							.withStatus(201)
							.withStatusMessage("Created")
							.withBodyFile(body)
							)
				);
		
	}
	
	
	//***********************create stub/mock for PUT calls***********************
	
	
		public static void putCreateUser(String endpoint,String body) {
			//body = 
					/*
					 * "{\r\n" + "    \"id\": \"101\",\r\n" + "}"
					 */
			
			stubFor(put(urlEqualTo(endpoint))
					.withHeader("content-Type", equalTo("application/json"))
						.willReturn(aResponse()
								.withStatus(201)
								.withStatusMessage("Created")
								.withBody(body)
								)
					);
			
		}
		
		
		public static void putCreateUserWithJsonFile(String endpoint,String body) {
			stubFor(put(urlEqualTo(endpoint))
					.withHeader("content-Type", equalTo("application/json"))
						.willReturn(aResponse()
								.withStatus(201)
								.withStatusMessage("Created")
								.withBodyFile(body)
								)
					);
			
		}
		
		
		//***********************create stub/mock for DELETE calls***********************

		public static void deleteUser(String endpoint) {
			
			stubFor(delete(urlEqualTo(endpoint))
					.willReturn(aResponse()
							.withStatus(204)
							.withStatusMessage("No Content")
							));
			
		}

	
}
