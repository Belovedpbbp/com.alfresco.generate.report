package com.alfresco.generate.report;

import java.util.Map;

import org.alfresco.repo.jscript.ValueConverter;

<<<<<<< HEAD
<<<<<<< HEAD
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
=======
public class ConvertJsToJava {

	@SuppressWarnings({ "unchecked" })
=======
public class ConvertJsToJava {

	@SuppressWarnings({ "unchecked" })
>>>>>>> parent of 5252dcf... สร้าง Report บนน Alfresco ได้เรียบร้อยแล้ว ขั้นตอนต่อไป แก้ไข Code
	public Map<? extends String, ? extends Object> objectToMap(Object jsObject)
			throws JRException, IOException {

		GenerateParameter generateParameter = new GenerateParameter();
<<<<<<< HEAD
>>>>>>> parent of 5252dcf... สร้าง Report บนน Alfresco ได้เรียบร้อยแล้ว ขั้นตอนต่อไป แก้ไข Code
=======
>>>>>>> parent of 5252dcf... สร้าง Report บนน Alfresco ได้เรียบร้อยแล้ว ขั้นตอนต่อไป แก้ไข Code

		Object javaObject = new ValueConverter().convertValueForJava(jsObject);
		Map<? extends String, ? extends Object> map = null;
		if (javaObject instanceof Map) {
			map = (Map<? extends String, ? extends Object>) javaObject;

<<<<<<< HEAD
<<<<<<< HEAD
			// Map<String, Object> maps = generateParam.param(map);
			//
			// NodeRef nodeRef = genReport.generateAlfrescoReport(maps,
			// parentOutputNodeRef, reportNodeName);
			// logger.warn(nodeRef.toString());
=======
			generateParameter.param(map);

>>>>>>> parent of 5252dcf... สร้าง Report บนน Alfresco ได้เรียบร้อยแล้ว ขั้นตอนต่อไป แก้ไข Code
=======
			generateParameter.param(map);

>>>>>>> parent of 5252dcf... สร้าง Report บนน Alfresco ได้เรียบร้อยแล้ว ขั้นตอนต่อไป แก้ไข Code
		}

		return map;
	}
}
