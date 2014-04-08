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


var scriptConvert = new Packages.com.alfresco.generate.report.ConvertJsToJava();

var scriptParameter = new Packages.com.alfresco.generate.report.GenerateParameter();

var scriptReport = new Packages.com.alfresco.generate.report.GenerateReport();

var map = scriptConvert.objectToMap(objCount);

var parameter = scriptParameter.param(map);

var genReport = scriptReport.generateReport(parameter);

model.genReport = genReport;

//logger.log(genReport);
