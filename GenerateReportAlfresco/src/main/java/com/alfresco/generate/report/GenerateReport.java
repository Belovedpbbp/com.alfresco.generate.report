package com.alfresco.generate.report;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

import org.apache.log4j.Logger;

public class GenerateReport {
	@SuppressWarnings("unused")
	private static Logger logger = Logger.getLogger(GenerateReport.class);

	public void generateReport(int totalFile) throws JRException, IOException {

		File fileReport = new File(
				"C:/Alfresco/tomcat/shared/classes/alfresco/extension/DataTemplate/AlfrescoReport.jasper");

		FileInputStream reportFileName = new FileInputStream(fileReport);

		HashMap<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("countFile", totalFile);

		JREmptyDataSource dataSource = new JREmptyDataSource();

		JasperPrint jasperPrint = JasperFillManager.fillReport(reportFileName,
				parameters, dataSource);

		ExportReport exportReport = new ExportReport();
		exportReport.exportReport(jasperPrint);

	}

}
