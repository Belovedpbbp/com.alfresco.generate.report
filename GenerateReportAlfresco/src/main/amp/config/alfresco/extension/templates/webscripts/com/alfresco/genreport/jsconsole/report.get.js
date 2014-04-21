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
if (!reports) {
	reports = companyhome.createFolder(reportsFolderName);
}

var node = reports.nodeRef;

var calendar = new Packages.java.util.Calendar.getInstance();

var simpleDateFormat = new Packages.java.text.SimpleDateFormat("yyyy-MM-dd");

var currentDate = simpleDateFormat.format(calendar.getTime());

var reportName = "AlfrescoReport " + currentDate + ".pdf";

var i = 0;
while (generateReport.reportExists(node, reportName)) {
	reportName = "AfrescoReport " + currentDate + "(" + (++i) + ")" + ".pdf";
}

convertJsToJava.objectToMap(objCount, node, reportName);

try {
	model.genReport = "AlfrescoJasperReports - report generated. to "
			+ reports.displayPath + "/" + reports.name;
} catch (err) {
	model.genReport = "AlfrescoJasperReports - error while generating report... :( "
			+ err;
}
