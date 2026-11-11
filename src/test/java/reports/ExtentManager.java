package reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {

	public static ExtentReports createReport() {

		ExtentSparkReporter spark = new ExtentSparkReporter("test-output/ExtentReport.html");

		ExtentReports extent = new ExtentReports();

		extent.attachReporter(spark);

		extent.setSystemInfo("Project", "Hybrid Automation Framework");
		extent.setSystemInfo("Tester", "Mayur");
		extent.setSystemInfo("Tool", "Selenium with TestNG");

		return extent;
	}
}
