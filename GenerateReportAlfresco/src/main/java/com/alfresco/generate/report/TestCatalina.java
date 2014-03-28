package com.alfresco.generate.report;

public class TestCatalina {
	public String catalinaBaseTest() {
		String catalinaBase = System.getProperty("catalina.base");
		System.out.println(catalinaBase);
		return catalinaBase;
	}
	
	public String catalinaHomeTest() {
		String catalinaHome = System.getProperty("catalina.home");
		System.out.println(catalinaHome);
		return catalinaHome;
	}

}
