var def =
	{
		query: 
			"TYPE:"+
			"(" + 
				"\"cm:content\"" + 
				"and not" + 
				"(" +
					"\"fm:post\"" +
					"\"lnk:link\"" +
					"\"ia:calendarEvent\"" +
				")" +
			")" +
			
			"and not" +
			"(" +
				"PATH:" +
				"(" +
					"\"sys:system//*\"" +
					"\"/app:company_home/app:dictionary//*\"" +
					"\"/app:company_home/st:sites/*/cm:dataLists//*\"" +
					"\"/app:company_home/st:sites/*/cm:wiki//*\"" +
					"\"/app:company_home/st:sites/*/cm:surf-config//*\"" +
					"\"/app:company_home/st:sites/*/cm:blog//*\"" +
				")" +
			")"	,
		language: "fts-alfresco"
	};
var results = search.query(def);

var countFile = results.length;

var objCount =
	{
		"countFile" : countFile
	};


var reportsFolderName = "AlfrescoJasperReports";

var reports = companyhome.childByNamePath(reportsFolderName);
if (!reports) {
    reports = companyhome.createFolder(reportsFolderName);
}

var nodeReportName = reports.nodeRef;

var calendar = new Packages.java.util.Calendar.getInstance();

var simpleDateFormat = new Packages.java.text.SimpleDateFormat("yyyy-MM-dd");

var currentDate = simpleDateFormat.format(calendar.getTime());

var reportName = "AlfrescoReport " + currentDate + ".pdf";

var i = 0;
while (generateReport.reportExists(nodeReportName, reportName)) {
    reportName = "AfrescoReport " + currentDate + " (" + (++i) + ")" + ".pdf";
}
try {
	var convert = convertJsToJava.objectToMap(objCount);
	
	var parameter = generateParameter.param(convert);
	
	var reportNodeRef = generateReport.generateAlfrescoReport(parameter, nodeReportName, reportName);

	var generatedReport = search.findNode(reportNodeRef);
	
	model.genReport = "AlfrescoJasperReports - report generated. " + generatedReport.displayPath + "/" + generatedReport.name;

} catch(err) {
	model.genReport = "AlfrescoJasperReports - error while generating report... :( " + err;
}