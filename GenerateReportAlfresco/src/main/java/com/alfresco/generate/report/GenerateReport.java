package com.alfresco.generate.report;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

import org.apache.log4j.Logger;

public class GenerateReport {
	@SuppressWarnings("unused")
	private static Logger logger = Logger.getLogger(GenerateReport.class);

	public String generateReport(Map<String, Object> param) throws JRException,
			IOException {
		GenerateDatasource generateDatasource = new GenerateDatasource();

		File fileReport = new File(
				"C:/Alfresco/tomcat/shared/classes/alfresco/extension/DataTemplate/AlfrescoReport.jasper");

		FileInputStream reportFileName = new FileInputStream(fileReport);

		JasperPrint jasperPrint = JasperFillManager.fillReport(reportFileName,
				param, generateDatasource.datasource());

		String destFileName = "C:/Alfresco/tomcat/shared/classes/alfresco/extension/DataTemplate/AlfrescoReport.pdf";

		String returnText = null;
		
		if (jasperPrint == null) {
			System.out
					.println("Cannot Export Report Because No File JasperReport");
		} else {

			JasperExportManager
					.exportReportToPdfFile(jasperPrint, destFileName);

			returnText = "GenerateReport Complete !! in " + destFileName;
		}
		return returnText;
	}

}
