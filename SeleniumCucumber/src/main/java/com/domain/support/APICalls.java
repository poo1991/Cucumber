package com.domain.support;

import com.domain.framework.Settings;
import com.domain.framework.TestContext;
import com.domain.restAssured.RestApi;

import io.restassured.response.Response;

public class APICalls extends BaseClass{

	String applicationUrl = System.getProperty("ApplicationUrl")==null? Settings.getProperty("ApplicationUrl"):  System.getProperty("ApplicationUrl");
	public APICalls(TestContext context) {
		super(context);
		
	}
	
	public RestApi restAPI = new RestApi();
	
	public void getSingleUser() {
		String url = applicationUrl+"/api/users/2";
		Response getSingleUserRes = restAPI.testGet(url, null, null, 200);
		testContext.scenarioContext.setContext("getSingleUserRes", getSingleUserRes);
	}

}
