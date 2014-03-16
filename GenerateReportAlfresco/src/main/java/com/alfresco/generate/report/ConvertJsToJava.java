package com.alfresco.generate.report;

import java.io.IOException;
import java.io.Serializable;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;

import org.alfresco.repo.jscript.ValueConverter;

public class ConvertJsToJava {
	
	private ValueConverter valueConverter;

	public ConvertJsToJava() {
		valueConverter = new ValueConverter();
	}

	@SuppressWarnings({ "unchecked" })
	public Integer objectToMap(Object jsObject) throws JRException, IOException {

		Integer totalFile = null;

		GenerateReport geReport = new GenerateReport();

		Object javaObject = valueConverter.convertValueForJava(jsObject);

		if (javaObject instanceof Map) {

			Map<Serializable, Serializable> map = (Map<Serializable, Serializable>) javaObject;

			Double countFile = (Double) map.get("total");

			totalFile = countFile.intValue();

			geReport.generateReport(totalFile);
		}

		return totalFile;

	}

}
