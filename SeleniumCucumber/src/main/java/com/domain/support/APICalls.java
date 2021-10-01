package com.domain.support;

import com.domain.framework.Settings;
import com.domain.framework.TestContext;

public class APICalls extends BaseClass{

	String applicationUrl = System.getProperty("ApplicationUrl")==null? Settings.getProperty("ApplicationUrl"):  System.getProperty("ApplicationUrl");
	public APICalls(TestContext context) {
		super(context);
		
	}

}
