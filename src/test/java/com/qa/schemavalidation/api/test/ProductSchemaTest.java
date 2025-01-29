package com.qa.schemavalidation.api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.utils.SchemaValidator;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

public class ProductSchemaTest extends BaseTest {
	
	
	@Test
	public void productAPISchematest() {
		
		/*
		 * RestAssured.given() .baseUri(BASE_URL_PRODUCT) .when() .get("/products")
		 * .then() .assertThat() .statusCode(200)
		 * .body(JsonSchemaValidator.matchesJsonSchemaInClasspath(
		 * "schema/product-schema.json"));
		 */
		
		
		Response response = restclient.get(BASE_URL_PRODUCTS,ENDPOINT_PRODUCTS,null, null, null, AuthType.NO_AUTH, ContentType.ANY);
		Assert.assertTrue(SchemaValidator.validateschema(response, "schema/product-schema.json"));
	}

}
