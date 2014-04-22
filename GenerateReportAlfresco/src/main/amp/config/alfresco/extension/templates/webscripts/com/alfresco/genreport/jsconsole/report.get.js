var def = {
	query : "TYPE:" + "(" + "\"cm:content\"" + "and not" + "(" + "\"fm:post\""
			+ "\"lnk:link\"" + "\"ia:calendarEvent\"" + ")" + ")" +

			"and not" + "(" + "PATH:" + "(" + "\"sys:system//*\""
			+ "\"/app:company_home/app:dictionary//*\""
			+ "\"/app:company_home/st:sites/*/cm:dataLists//*\""
			+ "\"/app:company_home/st:sites/*/cm:wiki//*\""
			+ "\"/app:company_home/st:sites/*/cm:surf-config//*\""
			+ "\"/app:company_home/st:sites/*/cm:blog//*\"" + ")" + ")",
	language : "fts-alfresco"
};
var results = search.query(def);

var countFile = results.length;

var objCount = {
	"countFile" : countFile
};

var reportsFolderName = "AlfrescoJasperReports";

var reports = companyhome.childByNamePath(reportsFolderName);
if (!reports)
	reports = companyhome.createFolder(reportsFolderName);

var node = reports.nodeRef;

try {
	var scriptConvert = convertJsToJava.objectToMap(objCount, node, "pdf");

	model.genReport = "AlfrescoJasperReports - report generated. to "
			+ reports.displayPath + "/" + reports.name;
} catch (err) {
	model.genReport = "AlfrescoJasperReports - error while generating report... :( "
			+ err;
}

<<<<<<< HEAD
=======
var parameter = scriptParameter.param(map);

var genReport = scriptReport.generateReport(parameter);

model.genReport = genReport;

//logger.log(genReport);
>>>>>>> parent of 5252dcf... สร้าง Report บนน Alfresco ได้เรียบร้อยแล้ว ขั้นตอนต่อไป แก้ไข Code
