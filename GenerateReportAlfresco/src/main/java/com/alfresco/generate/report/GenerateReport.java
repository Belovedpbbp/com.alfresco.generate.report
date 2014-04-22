<<<<<<< HEAD
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

<<<<<<< HEAD
	private NodeService nodeService;
	private JREmptyDataSource emptyDataSource;
	private ContentService contentService;
=======
	public String generateReport(Map<String, Object> param) throws JRException,
			IOException {
		GenerateDatasource generateDatasource = new GenerateDatasource();

		CatalinaPath catalinaPath = new CatalinaPath();
>>>>>>> parent of 5252dcf... สร้าง Report บนน Alfresco ได้เรียบร้อยแล้ว ขั้นตอนต่อไป แก้ไข Code

	// private JRPdfExporter pdfExporter;
	//
	// public void setPdfExporter(JRPdfExporter pdfExporter) {
	// this.pdfExporter = pdfExporter;
	// }

	public void setContentService(ContentService contentService) {
		this.contentService = contentService;
	}

<<<<<<< HEAD
	public void setEmptyDataSource(JREmptyDataSource emptyDataSource) {
		this.emptyDataSource = emptyDataSource;
	}

	public void setNodeService(NodeService nodeService) {
		this.nodeService = nodeService;
=======
		JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport,
				param, generateDatasource.datasource());

		String outPutFile = catalinaPath.catalinaBase() + "/webapps" + "/AlfrescoReport.pdf";
		
		JasperExportManager.exportReportToPdfFile(jasperPrint, outPutFile);
		
		String returnText = "Complete GenerateReport";
	
		return returnText;
<<<<<<< HEAD
>>>>>>> parent of 5252dcf... สร้าง Report บนน Alfresco ได้เรียบร้อยแล้ว ขั้นตอนต่อไป แก้ไข Code
=======
>>>>>>> parent of 5252dcf... สร้าง Report บนน Alfresco ได้เรียบร้อยแล้ว ขั้นตอนต่อไป แก้ไข Code
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
=======
package com.alfresco.generate.report;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
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

public class GenerateReport extends BaseProcessorExtension {
	private NodeService nodeService;
	private ContentService contentService;
	private JRPdfExporter exporter;

	public void setExporter(JRPdfExporter exporter) {
		this.exporter = exporter;
	}

	public void setNodeService(NodeService nodeService) {
		this.nodeService = nodeService;
	}

	public void setContentService(ContentService contentService) {
		this.contentService = contentService;
	}

	public boolean reportExists(NodeRef parentNodeRef, String reportNodeName) {

		NodeRef found = nodeService.getChildByName(parentNodeRef,
				ContentModel.ASSOC_CONTAINS, reportNodeName);

		return (found != null);
	}

	public NodeRef generateAlfrescoReport(Map<String, Object> param,
			NodeRef parentOutputNodeRef, String reportNodeName)
			throws IOException, JRException {

		NodeRef reportNodeRef = null;

		InputStream jasperReportIS = getClass().getResourceAsStream(
				"/AlfrescoReport.jrxml");
		JasperReport jasperReport = null;

		try {
			jasperReport = JasperCompileManager.compileReport(jasperReportIS);
		} finally {
			jasperReportIS.close();
		}

		JREmptyDataSource dataSource = new JREmptyDataSource();

		JasperPrint print = JasperFillManager.fillReport(jasperReport, param,
				dataSource);

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
>>>>>>> find
