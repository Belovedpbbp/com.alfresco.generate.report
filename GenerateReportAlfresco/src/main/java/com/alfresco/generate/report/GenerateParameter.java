package com.alfresco.generate.report;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.alfresco.repo.processor.BaseProcessorExtension;

import net.sf.jasperreports.engine.JRException;

public class GenerateParameter extends BaseProcessorExtension {

	public Map<String, Object> param(Map<? extends String, ? extends Object> map)
			throws JRException, IOException {

		GenerateReport generateReport = new GenerateReport();

		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.putAll(map);

		generateReport.generateReport(parameters);

		return parameters;
	}
}
