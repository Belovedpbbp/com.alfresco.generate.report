package com.alfresco.generate.report;

import java.io.Serializable;
import java.util.HashMap;

import org.alfresco.model.ContentModel;
import org.alfresco.repo.processor.BaseProcessorExtension;
import org.alfresco.service.cmr.repository.ContentService;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.NodeService;
import org.alfresco.service.namespace.NamespaceService;
import org.alfresco.service.namespace.QName;

public class NodeForGenerateReport extends BaseProcessorExtension {
	private NodeService nodeService;
	private ContentService contentService;

	public NodeRef nodeAlfresco(NodeRef parentOutputNodeRef) {

		NodeRef reportNodeRef = null;

		String reportNodeName = String.valueOf(System.currentTimeMillis());

		HashMap<QName, Serializable> contentProperties = new HashMap<QName, Serializable>();
		contentProperties.put(ContentModel.PROP_NAME, reportNodeName);
		QName assocQName = QName.createQName(
				NamespaceService.CONTENT_MODEL_1_0_URI,
				QName.createValidLocalName(reportNodeName));

		reportNodeRef = nodeService.createNode(parentOutputNodeRef,
				ContentModel.ASSOC_CONTAINS, assocQName,
				ContentModel.PROP_CONTENT, contentProperties).getChildRef();

		// ContentWriter contentWriter = contentService.getWriter(reportNodeRef,
		// ContentModel.PROP_CONTENT, true);
		// OutputStream reportOS = contentWriter.getContentOutputStream();

		return reportNodeRef;
	}

	// Getters & setters

	public void setNodeService(NodeService nodeService) {
		this.nodeService = nodeService;
	}

	public void setContentService(ContentService contentService) {
		this.contentService = contentService;
	}
}
