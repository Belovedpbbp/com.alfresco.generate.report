package com.alfresco.generate.report;

import java.util.Map;

import org.alfresco.repo.jscript.ValueConverter;
import org.alfresco.repo.processor.BaseProcessorExtension;
import org.alfresco.service.cmr.repository.NodeRef;

public class ConvertJsToJava extends BaseProcessorExtension {

	// private GenerateReport genReport;
	// private GenerateParameter generateParam;

	// private Log logger = LogFactory.getLog(ConvertJsToJava.class);

	// public void setGenReport(GenerateReport genReport) {
	// this.genReport = genReport;
	// }
	//
	// public void setGenerateParam(GenerateParameter generateParam) {
	// this.generateParam = generateParam;
	// }

	@SuppressWarnings({ "unchecked" })
	public Map<? extends String, ? extends Object> objectToMap(Object jsObject,
			NodeRef parentOutputNodeRef, String reportNodeName) {

		Object javaObject = new ValueConverter().convertValueForJava(jsObject);
		Map<? extends String, ? extends Object> map = null;
		if (javaObject instanceof Map) {
			map = (Map<? extends String, ? extends Object>) javaObject;

			// Map<String, Object> maps = generateParam.param(map);
			//
			// NodeRef nodeRef = genReport.generateAlfrescoReport(maps,
			// parentOutputNodeRef, reportNodeName);
			// logger.warn(nodeRef.toString());
		}

		return map;
	}

}
