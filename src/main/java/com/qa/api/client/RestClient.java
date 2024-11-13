package com.qa.api.client;

import java.io.File;
import java.util.Base64;
import java.util.Map;
import static org.hamcrest.Matchers.*;
import com.qa.api.constants.AuthType;
import com.qa.api.exceptions.FrameWorkException;
import com.qa.api.manager.ConfigManager;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.expect;

public class RestClient {
	
	//Define Response specification
	private ResponseSpecification responseSpec200 = expect().statusCode(200);
	
	private ResponseSpecification responseSpec200or404 = expect().statusCode(anyOf(equalTo(200),equalTo(404)));
	private ResponseSpecification responseSpec200or201 = expect().statusCode(anyOf(equalTo(200),equalTo(201)));
	
	private ResponseSpecification responseSpec201 = expect().statusCode(201);
	private ResponseSpecification responseSpec204 = expect().statusCode(204);
	private ResponseSpecification responseSpec400 = expect().statusCode(400);
	private ResponseSpecification responseSpec401 = expect().statusCode(401);
	private ResponseSpecification responseSpec403 = expect().statusCode(403);
	private ResponseSpecification responseSpec404 = expect().statusCode(404);
	private ResponseSpecification responseSpec500 = expect().statusCode(500);
	private ResponseSpecification responseSpec501 = expect().statusCode(501);

	//private String baseUrl = ConfigManager.get("baseUrl");

	private RequestSpecification setupRequest(String baseUrl,AuthType authType, ContentType contentType) {

		RequestSpecification request = RestAssured.given().log().all()
											.baseUri(baseUrl)
												.contentType(contentType)
													.accept(contentType);

		switch (authType) {
		case BEARER_TOKEN:
			request.header("Authorization", "Bearer " + ConfigManager.get("bearerToken"));
			break;
			
		case CONTACTS_BEARER_TOKEN:
			request.header("Authorization", "Bearer " + ConfigManager.get("contacts_bearer_token"));
			break;

		case OAUTH2:
			request.header("Authorization", "Bearer " + generateOAUTH2Token());
			break;

		case BASIC_AUTH:
			request.header("Authorization", "Basic " +generateBasicAuthToken());
			break;

		case API_KEY:
			request.header("x-api-key", ConfigManager.get("apiKey"));
			break;

		case NO_AUTH:
			System.out.println("===AUTH is not required===");
			break;

		default:
			System.out.println("This Auth is not Supported...please pass the right auth type");
			throw new FrameWorkException("===AUTH NOT SUPPORTED===");
		}
		
		return request;

	}
	
	private RequestSpecification setUpAuthandContentType(String baseUrl,AuthType authType, ContentType contentType) {
		return setupRequest(baseUrl , authType, contentType);
	}
	
	private void applyParams(RequestSpecification request,Map<String, String> queryParam,Map<String, String> pathParam) {
		if(queryParam != null) {
			request.queryParams(queryParam);
		}
		
		if(pathParam != null) {
			request.pathParams(pathParam);
		}
	}

	private String generateOAUTH2Token() {

		  String token = RestAssured.given()
						.formParam("client_id", ConfigManager.get("clientId"))
						.formParam("client_secret", ConfigManager.get("clientSecret"))
						.formParam("grant_type", ConfigManager.get("grantType"))
						.post(ConfigManager.get("tokenUrl"))
						.then()
						.extract()
						.path("access_token");
		  
		  System.out.println("Access Token is ===>"+token);
		  
		  return token;
	}
	
	/**
	 * this method is used to generate the Base64 encoded String
	 * @return
	 */
	private String generateBasicAuthToken() {
		
		String credentials = ConfigManager.get("basicUsername")+":"+ConfigManager.get("basicPassword");
		return Base64.getEncoder().encodeToString(credentials.getBytes());
		
	}
	
	
	
	
	
	//***************************CRUD METHOD***************************//

	/**
	 * This method is used to call the GET APIs.
	 * @param endpoint
	 * @param queryParam
	 * @param pathParam
	 * @param authType
	 * @param contentType
	 * @return it returns the get api response
	 */
	
	public Response get(String baseUrl ,String endpoint, Map<String, String> queryParam,Map<String, String> pathParam ,
						AuthType authType, ContentType contentType) {
		
		RequestSpecification request = setUpAuthandContentType(baseUrl,authType, contentType);
		applyParams(request, queryParam, pathParam);
				
		Response response = request.get(endpoint).then().spec(responseSpec200or404).extract().response();
		response.prettyPrint();
		return response;

	}
	
	
	/**
	 * This method is used to call the POST APIs
	 * @param <T>
	 * @param endpoint
	 * @param body
	 * @param queryParam
	 * @param pathParam
	 * @param authType
	 * @param contentType
	 * @return it returns the post api response
	 */
	public <T>Response post(String baseUrl,String endpoint,T body, 
			Map<String, String> queryParam,	Map<String, String> pathParam ,
			AuthType authType, ContentType contentType) {
		
		RequestSpecification request = setUpAuthandContentType(baseUrl,authType, contentType);
		applyParams(request, queryParam, pathParam);
		
		Response response = request.body(body).post(endpoint).then().spec(responseSpec200or201).extract().response();
		response.prettyPrint();
		return response;
		
		
	}
	
	
	/**
	 * This method is used to call the POST APIs for the JSON file body
	 * @param endpoint
	 * @param body
	 * @param queryParam
	 * @param pathParam
	 * @param authType
	 * @param contentType
	 * @return it returns the post api response
	 */
	public Response post(String baseUrl,String endpoint,File body, 
			Map<String, String> queryParam,	Map<String, String> pathParam ,
			AuthType authType, ContentType contentType) {
		
		RequestSpecification request = setUpAuthandContentType(baseUrl,authType, contentType);
		applyParams(request, queryParam, pathParam);
		
		Response response = request.body(body).post(endpoint).then().spec(responseSpec200or201).extract().response();
		response.prettyPrint();
		return response;
		
		
	}
	
	/**
	 * This method is used to call the PUT APIs
	 * @param <T>
	 * @param endpoint
	 * @param body
	 * @param queryParam
	 * @param pathParam
	 * @param authType
	 * @param contentType
	 * @return it returns the put api response
	 */
	public <T>Response put(String baseUrl,String endpoint,T body, 
			Map<String, String> queryParam,	Map<String, String> pathParam ,
			AuthType authType, ContentType contentType) {
		
		RequestSpecification request = setUpAuthandContentType(baseUrl,authType, contentType);
		applyParams(request, queryParam, pathParam);
		
		Response response = request.body(body).put(endpoint).then().spec(responseSpec200).extract().response();
		response.prettyPrint();
		return response;
		
	}
	
	/**
	 * This method is used to call the PATCH APIs
	 * @param <T>
	 * @param endpoint
	 * @param body
	 * @param queryParam
	 * @param pathParam
	 * @param authType
	 * @param contentType
	 * @return it returns the patch api response
	 */
	public <T>Response patch(String baseUrl,String endpoint,T body, 
			Map<String, String> queryParam,	Map<String, String> pathParam ,
			AuthType authType, ContentType contentType) {
		
		RequestSpecification request = setUpAuthandContentType(baseUrl,authType, contentType);
		applyParams(request, queryParam, pathParam);
		
		Response response = request.body(body).patch(endpoint).then().spec(responseSpec200).extract().response();
		response.prettyPrint();
		return response;
		
	}
	
	/**
	 * This method is used to call the DELETE APIs
	 * @param endpoint
	 * @param queryParam
	 * @param pathParam
	 * @param authType
	 * @param contentType
	 * @return it returns the delete apis response
	 */
	public Response delete(String baseUrl,String endpoint, 
			Map<String, String> queryParam,	Map<String, String> pathParam ,
			AuthType authType, ContentType contentType) {
		
		RequestSpecification request = setUpAuthandContentType(baseUrl,authType, contentType);
		applyParams(request, queryParam, pathParam);
		
		Response response = request.delete(endpoint).then().spec(responseSpec204).extract().response();
		response.prettyPrint();
		return response;
		
		
	}
	

}
