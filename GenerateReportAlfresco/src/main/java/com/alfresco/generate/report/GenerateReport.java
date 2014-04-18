package com.alfresco.generate.report;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;

import org.alfresco.model.ContentModel;
import org.alfresco.repo.processor.BaseProcessorExtension;
import org.alfresco.service.cmr.repository.ContentService;
import org.alfresco.service.cmr.repository.ContentWriter;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.NodeService;
import org.alfresco.service.namespace.NamespaceService;
import org.alfresco.service.namespace.QName;
import org.apache.chemistry.opencmis.commons.enums.VersioningState;
import org.apache.log4j.Logger;

public class GenerateReport extends BaseProcessorExtension {
	@SuppressWarnings("unused")
	private static Logger logger = Logger.getLogger(GenerateReport.class);

	private NodeService nodeService;
	private ContentService contentService;
	private JREmptyDataSource emptyDataSource;

	public void setEmptyDataSource(JREmptyDataSource emptyDataSource) {
		this.emptyDataSource = emptyDataSource;
	}

	public void setNodeService(NodeService nodeService) {
		this.nodeService = nodeService;
	}

	public void setContentService(ContentService contentService) {
		this.contentService = contentService;
	}

	public NodeRef generateAlfrescoReport(Map<String, Object> param,
			NodeRef parentOutputNodeRef, String output) throws IOException,
			JRException {

		NodeRef reportNodeRef = null;

		InputStream jasperReportIS = getClass().getResourceAsStream(
				"/AlfrescoReport.jrxml");
		JasperReport jasperReport = null;

		try {
			jasperReport = JasperCompileManager.compileReport(jasperReportIS);
		} finally {
			jasperReportIS.close();
		}

		JasperPrint print = JasperFillManager.fillReport(jasperReport, param,
				emptyDataSource);

		JRExporter exporter = null;

		Calendar calender = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String currentDate = dateFormat.format(calender.getTime());
		String reportNodeName = "AlfrescoReport " + currentDate;
		
		if (output.equalsIgnoreCase("HTML")) {
			exporter = new JRHtmlExporter();
			reportNodeName += ".html";
		} else if (output.equalsIgnoreCase("XLS")) {
			exporter = new JRXlsExporter();
			reportNodeName += ".xls";
		} else if (output.equalsIgnoreCase("DOCX")) {
			exporter = new JRDocxExporter();
			reportNodeName += ".docx";
		} else if (output.equalsIgnoreCase("PDF")) {
			exporter = new JRPdfExporter();
			reportNodeName += ".pdf";
		} else {
			throw new JRException("Unknown output format.");
		}

		HashMap<QName, Serializable> contentProperties = new HashMap<QName, Serializable>();
		contentProperties.put(ContentModel.PROP_NAME, reportNodeName);
		QName assocQName = QName.createQName(
				NamespaceService.CONTENT_MODEL_1_0_URI,
				QName.createValidLocalName(reportNodeName));
		
		reportNodeRef = nodeService.createNode(parentOutputNodeRef,
				ContentModel.ASSOC_CONTAINS, assocQName,
				ContentModel.PROP_CONTENT, contentProperties).getChildRef();

		ContentWriter contentWriter = contentService.getWriter(reportNodeRef,
				ContentModel.PROP_CONTENT, true);
		OutputStream reportOS = contentWriter.getContentOutputStream();
		
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, reportOS);

		try {
			exporter.exportReport();
		} catch (JRException e) {
			nodeService.deleteNode(reportNodeRef);
			throw e;
		} finally {
			reportOS.close();
		}
		return reportNodeRef;
	}

}
