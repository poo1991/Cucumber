package com.domain.steps;

import static org.testng.Assert.assertEquals;

import org.json.JSONObject;

import com.domain.framework.TestContext;
import com.domain.support.APICalls;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;

public class APISteps extends APICalls{

	public APISteps(TestContext context) {
		super(context);
	}
	
	@Given("Call Get Single User API")
	public void callSingleUserAPI() {
		getSingleUser();
		
	}
	
	@Then("Verify Response")
	public void verifySingleUserAPI() {
		Response res = (Response) testContext.scenarioContext.getContext("getSingleUserRes");
		System.out.println(res.getBody().asString());
		JSONObject jo = new JSONObject(res.getBody().asString());
		assertEquals(jo.getJSONObject("data").getString("email"), "janet.weaver@reqres.in");
		
	}
	

}
