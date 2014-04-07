package com.alfresco.generate.report;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;

public class GenerateParameter {

	public Map<String, Object> param(Map<? extends String, ? extends Object> map)
			throws JRException, IOException {

		GenerateReport generateReport = new GenerateReport();

		GenerateReportonAlfresco generateReportonAlfresco = new GenerateReportonAlfresco();

		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.putAll(map);

		generateReport.generateReport(parameters);

		// generateReportonAlfresco.generateReportonAlfresco(parameters);
		
		return parameters;
	}
}
