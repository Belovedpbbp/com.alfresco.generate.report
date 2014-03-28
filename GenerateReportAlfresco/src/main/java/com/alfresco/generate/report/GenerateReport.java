package com.alfresco.generate.report;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

import org.apache.log4j.Logger;

public class GenerateReport {
	@SuppressWarnings("unused")
	private static Logger logger = Logger.getLogger(GenerateReport.class);

	public String generateReport(Map<String, Object> param) throws JRException,
			IOException {
		GenerateDatasource generateDatasource = new GenerateDatasource();

		InputStream reportFile = getClass().getResourceAsStream(
				"/AlfrescoReport.jrxml");

		JasperReport compileReport = JasperCompileManager
				.compileReport(reportFile);

		JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport,
				param, generateDatasource.datasource());

		String outPutFile = "D:\\AlfrescoReport.pdf";

		String returnText = null;

		JasperExportManager.exportReportToPdfFile(jasperPrint, outPutFile);

		returnText = "Complete GenerateReport";
		return returnText;
	}

}
