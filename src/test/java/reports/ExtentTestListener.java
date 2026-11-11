
package reports;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import utils.DriverManager;

public class ExtentTestListener implements ITestListener {

	static ExtentReports extent = ExtentManager.createReport();

	private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

	@Override
	public void onTestStart(ITestResult result) {
		test.set(extent.createTest(result.getName()));
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		test.get().pass("Test Passed Successfully");
		closeBrowser();
	}

	@Override
	public void onTestFailure(ITestResult result) {

		test.get().fail(result.getThrowable());

		WebDriver driver = DriverManager.getDriver();

		if (driver != null) {
			try {
				String folder = "test-output/screenshots";

				Files.createDirectories(Paths.get(folder));

				String fileName = result.getName() + "_" + System.currentTimeMillis() + ".png";

				Path destination = Paths.get(folder, fileName);

				File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

				Files.copy(source.toPath(), destination, StandardCopyOption.REPLACE_EXISTING);

				test.get().addScreenCaptureFromPath(destination.toString());

			} catch (Exception e) {
				test.get().warning("Screenshot error: " + e.getMessage());
			}
		}

		closeBrowser();
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		test.get().skip("Test Skipped");
		closeBrowser();
	}

	private void closeBrowser() {
		WebDriver driver = DriverManager.getDriver();

		if (driver != null) {
			driver.quit();
		}

		DriverManager.unload();
	}

	@Override
	public void onFinish(org.testng.ITestContext context) {
		extent.flush();
	}
}
