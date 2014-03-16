package com.alfresco.generate.report;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;

public class ExportReport {
	public String exportReport(JasperPrint jasperPrint) throws JRException {

		String destFileName = "C:/Alfresco/tomcat/shared/classes/alfresco/extension/DataTemplate/ReportJasper.html";

		if (jasperPrint == null) {

			System.out.println("Cannot Export Report Because No File Report");
		} else {

			JasperExportManager.exportReportToHtmlFile(jasperPrint,
					destFileName);
			System.out.println(destFileName);
			System.out.println("Report Complete Export !!");

		}

		return destFileName;

	}

}
