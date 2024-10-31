package com.qa.api.utils;

import java.util.List;
import java.util.Map;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ReadContext;

import io.restassured.response.Response;

public class JsonPathUtil {

	private static String getJsonReponseAsString(Response response) {
		return response.getBody().asString();
	}

	public static <T> T read(Response response, String jsonPath) {
		String jsonResponse = getJsonReponseAsString(response);
		ReadContext ctx = JsonPath.parse(jsonResponse);
		return ctx.read(jsonPath);
	}
	
	
	public static <T> List<T> readList(Response response, String jsonPath) {
		String jsonResponse = getJsonReponseAsString(response);
		ReadContext ctx = JsonPath.parse(jsonResponse);
		return ctx.read(jsonPath);
	}
	
	public static <T> List<Map<String,T>> readListofMap(Response response, String jsonPath) {
		String jsonResponse = getJsonReponseAsString(response);
		ReadContext ctx = JsonPath.parse(jsonResponse);
		return ctx.read(jsonPath);
	}

}
