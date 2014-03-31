package com.alfresco.generate.report;

public class CatalinaPath {
	public String catalinaBase() {
		String catalinaBase = System.getProperty("catalina.base") + "\\webapps";
		return catalinaBase;
	}
	
	
//	public String catalinaHomeTest() {
//		String catalinaHome = System.getProperty("catalina.home");
//		System.out.println(catalinaHome);
//		return catalinaHome;
//	}
}
