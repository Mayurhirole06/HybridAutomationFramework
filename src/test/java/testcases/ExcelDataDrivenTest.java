
package testcases;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.LoginPage;
import utils.DriverManager;

public class ExcelDataDrivenTest {

	@DataProvider(name = "loginData")
	public Object[][] getLoginData() throws IOException {

		FileInputStream file = new FileInputStream("resources/LoginTestData.xlsx");

		XSSFWorkbook workbook = new XSSFWorkbook(file);
		Sheet sheet = workbook.getSheetAt(0);

		int rows = sheet.getPhysicalNumberOfRows();

		Object[][] data = new Object[rows - 1][3];

		for (int i = 1; i < rows; i++) {

			data[i - 1][0] = sheet.getRow(i).getCell(0).getStringCellValue();

			data[i - 1][1] = sheet.getRow(i).getCell(1).getStringCellValue();

			data[i - 1][2] = sheet.getRow(i).getCell(2).getStringCellValue();
		}

		workbook.close();
		file.close();

		return data;
	}

	@Test(dataProvider = "loginData")
	public void loginTest(String username, String password, String expectedResult) {

		WebDriver driver = new ChromeDriver();

		DriverManager.setDriver(driver);

		try {

			driver.get("https://www.saucedemo.com/");

			LoginPage login = new LoginPage(driver);

			login.enterUsername(username);
			login.enterPassword(password);
			login.clickLogin();

			if (expectedResult.equalsIgnoreCase("Pass")) {

				Assert.assertTrue(driver.getCurrentUrl().contains("inventory"), "Login should be successful");

			} else {

				Assert.assertTrue(login.getErrorMessage().length() > 0, "Error message should be displayed");
			}

		} catch (Exception | AssertionError e) {

			throw e;

		}
	}
}
