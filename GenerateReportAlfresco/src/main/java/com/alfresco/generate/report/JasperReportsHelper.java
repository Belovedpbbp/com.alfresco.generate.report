package com.alfresco.generate.report;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

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

public class JasperReportsHelper extends BaseProcessorExtension {
	private NodeService nodeService;
	private ContentService contentService;

	public void setNodeService(NodeService nodeService) {
		this.nodeService = nodeService;
	}

	public void setContentService(ContentService contentService) {
		this.contentService = contentService;
	}

	public NodeRef generateAlfrescoReport(Map<String, Object> param,
			NodeRef parentOutputNodeRef, String output) throws IOException,
			JRException {

		GenerateDatasource generateDatasource = new GenerateDatasource();

		NodeRef reportNodeRef = null;

		InputStream jasperReportIS = getClass().getResourceAsStream(
				"/AlfrescoReport.jrxml");
		JasperReport jasperReport = null;

		try {
			jasperReport = JasperCompileManager.compileReport(jasperReportIS);
		} finally {
			jasperReportIS.close();
		}

		// Map<String, Object> parameters = new HashMap<String, Object>();

		// parameters.put("countFile", 15);
		JasperPrint print = JasperFillManager.fillReport(jasperReport, param,
				generateDatasource.datasource());

		JRExporter exporter = null;

		String reportNodeName = "AlfrescoReport"
				+ String.valueOf(System.currentTimeMillis());

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