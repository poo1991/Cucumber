package com.domain.restAssured;

import java.util.HashMap;
import java.util.Map;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class RestApi {
	
	public Response testGet(String url,String schema, Map<String, String> headers, Integer statusCode) {
		RequestSpecBuilder reqbuilder = new RequestSpecBuilder();
		ResponseSpecBuilder resBuilder = new ResponseSpecBuilder();
		if(headers==null)
			headers= new HashMap<>();
		if(headers!=null && !headers.isEmpty()) {
			reqbuilder.addHeaders(headers);
		}
		resBuilder.expectStatusCode(statusCode);
//		if(schema!=null)
//			resBuilder.expectBody(matchesJsonSchemaClasspath(schema));
		
		return given(reqbuilder.build(), resBuilder.build()).get(url).thenReturn();
	}
	
	public Response testDelete(String url,String schema, Map<String, String> headers, Integer statusCode,Object body) {
		RequestSpecBuilder reqbuilder = new RequestSpecBuilder();
		ResponseSpecBuilder resBuilder = new ResponseSpecBuilder();
		if(headers==null)
			headers= new HashMap<>();
		if(headers!=null && !headers.isEmpty()) {
			reqbuilder.addHeaders(headers);
		}
		if(body!=null)
			reqbuilder.setBody(body);
		resBuilder.expectStatusCode(statusCode);
//		if(schema!=null)
//			resBuilder.expectBody(matchesJsonSchemaClasspath(schema));
		
		return given(reqbuilder.build(), resBuilder.build()).delete(url).thenReturn();
	}
	
	public Response testPut(String url,String schema, Map<String, String> headers, Integer statusCode,Object body) {
		RequestSpecBuilder reqbuilder = new RequestSpecBuilder();
		ResponseSpecBuilder resBuilder = new ResponseSpecBuilder();
		if(headers==null)
			headers= new HashMap<>();
		if(headers!=null && !headers.isEmpty()) {
			reqbuilder.addHeaders(headers);
		}
		if(body!=null)
			reqbuilder.setBody(body);
		resBuilder.expectStatusCode(statusCode);
//		if(schema!=null)
//			resBuilder.expectBody(matchesJsonSchemaClasspath(schema));
		
		return given(reqbuilder.build(), resBuilder.build()).put(url).thenReturn();
	}
	
	public Response testPost(String url,String schema, Map<String, String> headers, Integer statusCode,Object body) {
		RequestSpecBuilder reqbuilder = new RequestSpecBuilder();
		ResponseSpecBuilder resBuilder = new ResponseSpecBuilder();
		if(headers==null)
			headers= new HashMap<>();
		if(headers!=null && !headers.isEmpty()) {
			reqbuilder.addHeaders(headers);
		}
		if(body!=null)
			reqbuilder.setBody(body);
		resBuilder.expectStatusCode(statusCode);
//		if(schema!=null)
//			resBuilder.expectBody(matchesJsonSchemaClasspath(schema));
		
		return given(reqbuilder.build(), resBuilder.build()).post(url).thenReturn();
	}
	
}
