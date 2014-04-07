package com.alfresco.generate.report;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.alfresco.model.ContentModel;
import org.alfresco.repo.processor.BaseProcessorExtension;
import org.alfresco.service.cmr.repository.ContentService;
import org.alfresco.service.cmr.repository.ContentWriter;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.NodeService;
import org.alfresco.service.namespace.NamespaceService;
import org.alfresco.service.namespace.QName;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

public class GenerateReportonAlfresco extends BaseProcessorExtension {
//
//	private NodeService nodeService;
//	private ContentService contentService;
//
//	@SuppressWarnings("null")
//	public NodeRef generateReportonAlfresco(Map<String, Object> param)
//			throws JRException, IOException {
//		// =================================================
//
//		NodeRef reportNodeRef = null;
//		NodeRef parentOutputNodeRef = null;
//
//		// ===========================================================
//		GenerateDatasource generateDatasource = new GenerateDatasource();
//		InputStream reportFile = getClass().getResourceAsStream(
//				"/AlfrescoReport.jrxml");
//
//		JasperReport compileReport = JasperCompileManager
//				.compileReport(reportFile);
//
//		@SuppressWarnings("unused")
//		Map<JRExporterParameter, String> parameters = new HashMap<JRExporterParameter, String>();
//		JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport,
//				param, generateDatasource.datasource());
//
//		JRExporter exporter = null;
//		// ====================================================================
//
//		// =================================================================
//
//		String outPutFile = "AlfrescoReport.pdf";
//		// ================================================================
//
//		HashMap<QName, Serializable> contentProperties = new HashMap<QName, Serializable>();
//		contentProperties.put(ContentModel.PROP_NAME, outPutFile);
//		QName assocQName = QName.createQName(
//				NamespaceService.CONTENT_MODEL_1_0_URI,
//				QName.createValidLocalName(outPutFile));
//
//		reportNodeRef = nodeService.createNode(parentOutputNodeRef,
//				ContentModel.ASSOC_CONTAINS, assocQName,
//				ContentModel.PROP_CONTENT, contentProperties).getChildRef();
//
//		ContentWriter contentWriter = contentService.getWriter(reportNodeRef,
//				ContentModel.PROP_CONTENT, true);
//
//		OutputStream reportOS = contentWriter.getContentOutputStream();
//
//		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
//		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, reportOS);
//
//		try {
//			exporter.exportReport();
//		} catch (JRException e) {
//			nodeService.deleteNode(reportNodeRef);
//			throw e;
//		} finally {
//			reportOS.close();
//		}
//
//		return reportNodeRef;
//
//	}
//
//	public void setNodeService(NodeService nodeService) {
//		this.nodeService = nodeService;
//	}
//
//	public void setContentService(ContentService contentService) {
//		this.contentService = contentService;
//	}

}
