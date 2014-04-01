package com.alfresco.generate.report;

public class CatalinaPath {
	public String catalinaBase() {
		String catalinaBase = System.getProperty("catalina.base");
		return catalinaBase;
	}
}
