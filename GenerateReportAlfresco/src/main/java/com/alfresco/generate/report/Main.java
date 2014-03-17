package com.alfresco.generate.report;

import java.io.IOException;

import net.sf.jasperreports.engine.JRException;

public class Main {
	
	public static void main(String[] args) throws JRException, IOException{
		
		GenerateReport generateReport = new GenerateReport();
		generateReport.generateReport(2351);
	}

}
