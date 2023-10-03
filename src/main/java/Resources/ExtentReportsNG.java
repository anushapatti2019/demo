package Resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportsNG {
	public  static ExtentReports  reports() {

		String path = System.getProperty("user.dir")+"//report//index.html";
		ExtentSparkReporter report = new ExtentSparkReporter(path);
		report.config().setReportName("Web Testing");
		report.config().setDocumentTitle("Test Results");

		ExtentReports er = new ExtentReports();
		er.attachReporter(report);
		er.setSystemInfo("Tester", "Anusha ");
		return er;

	}

}
