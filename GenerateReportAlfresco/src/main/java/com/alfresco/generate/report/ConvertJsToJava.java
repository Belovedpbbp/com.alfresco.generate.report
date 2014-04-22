package com.alfresco.generate.report;

import java.io.IOException;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;

import org.alfresco.repo.jscript.ValueConverter;
import org.alfresco.repo.processor.BaseProcessorExtension;

public class ConvertJsToJava extends BaseProcessorExtension {

	@SuppressWarnings({ "unchecked" })
	public Map<? extends String, ? extends Object> objectToMap(Object jsObject) throws JRException,
			IOException {

		Object javaObject = new ValueConverter().convertValueForJava(jsObject);
		Map<? extends String, ? extends Object> map = null;
		if (javaObject instanceof Map) {
			map = (Map<? extends String, ? extends Object>) javaObject;
		}

		return map;
	}

}
