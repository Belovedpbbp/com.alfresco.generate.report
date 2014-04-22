package com.alfresco.generate.report;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
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
import net.sf.jasperreports.engine.export.JRPdfExporter;
import org.alfresco.model.ContentModel;
import org.alfresco.repo.processor.BaseProcessorExtension;
import org.alfresco.service.cmr.repository.ContentService;
import org.alfresco.service.cmr.repository.ContentWriter;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.NodeService;
import org.alfresco.service.namespace.NamespaceService;
import org.alfresco.service.namespace.QName;
import org.apache.log4j.Logger;

public class GenerateReport extends BaseProcessorExtension {
	private static Logger logger = Logger.getLogger(GenerateReport.class);

	private NodeService nodeService;
	private JREmptyDataSource emptyDataSource;
	private ContentService contentService;

	// private JRPdfExporter pdfExporter;
	//
	// public void setPdfExporter(JRPdfExporter pdfExporter) {
	// this.pdfExporter = pdfExporter;
	// }

	public void setContentService(ContentService contentService) {
		this.contentService = contentService;
	}

	public void setEmptyDataSource(JREmptyDataSource emptyDataSource) {
		this.emptyDataSource = emptyDataSource;
	}

	public void setNodeService(NodeService nodeService) {
		this.nodeService = nodeService;
	}

	public boolean reportExists(NodeRef parentNodeRef, String reportNodeName) {

		NodeRef found = nodeService.getChildByName(parentNodeRef,
				ContentModel.ASSOC_CONTAINS, reportNodeName);

		return (found != null);
	}

	public NodeRef generateAlfrescoReport(Map<String, Object> param,
			NodeRef parentOutputNodeRef, String reportNodeName)
			{

		NodeRef reportNodeRef = null;
		JasperReport jasperReport = null;

		try(InputStream jasperReportIS = getClass().getResourceAsStream("/AlfrescoReport.jrxml")) {
			jasperReport = JasperCompileManager.compileReport(jasperReportIS);
			logger.debug(jasperReportIS);
		} catch (IOException | JRException e1) {
			logger.debug(e1.getMessage());
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

		
		try(OutputStream reportOS = contentWriter.getContentOutputStream()) {
			JasperPrint print = JasperFillManager.fillReport(jasperReport, param,
					emptyDataSource);
			
			JRExporter exporter = new JRPdfExporter();

			exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, reportOS);
			
			
			logger.debug(jasperReport.toString());
			logger.debug(contentWriter.isClosed());
			exporter.exportReport();
		} catch (JRException | IOException e) {
			logger.debug(e.getMessage());
		}

		return reportNodeRef;
	}
}