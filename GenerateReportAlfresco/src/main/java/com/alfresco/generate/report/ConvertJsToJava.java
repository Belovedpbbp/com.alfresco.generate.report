package com.alfresco.generate.report;

import java.io.IOException;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;

import org.alfresco.repo.jscript.ValueConverter;
import org.alfresco.repo.processor.BaseProcessorExtension;
import org.alfresco.service.cmr.repository.NodeRef;

public class ConvertJsToJava extends BaseProcessorExtension {

	private JasperReportsHelper jasperReport;
	private GenerateParameter generateParam;

	public void setJasperReport(JasperReportsHelper jasperReport) {
		this.jasperReport = jasperReport;
	}

	public void setGenerateParam(GenerateParameter generateParam) {
		this.generateParam = generateParam;
	}

	@SuppressWarnings({ "unchecked" })
	public Map<? extends String, ? extends Object> objectToMap(Object jsObject,
			NodeRef parentOutputNodeRef, String output) throws JRException,
			IOException {

		Object javaObject = new ValueConverter().convertValueForJava(jsObject);
		Map<? extends String, ? extends Object> map = null;
		if (javaObject instanceof Map) {
			map = (Map<? extends String, ? extends Object>) javaObject;

			Map<String, Object> maps = generateParam.param(map);

			jasperReport.generateAlfrescoReport(maps, parentOutputNodeRef,
					output);
		}

		return map;
	}

}
