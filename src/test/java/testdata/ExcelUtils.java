
package testdata;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

	public static void main(String[] args) throws IOException {

		FileInputStream file = new FileInputStream("resources/LoginTestData.xlsx");

		XSSFWorkbook workbook = new XSSFWorkbook(file);

		Sheet sheet = workbook.getSheetAt(0);

		System.out.println("Total Rows: " + sheet.getPhysicalNumberOfRows());

		for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {

			String username = sheet.getRow(i).getCell(0).getStringCellValue();

			String password = sheet.getRow(i).getCell(1).getStringCellValue();

			String expectedResult = sheet.getRow(i).getCell(2).getStringCellValue();

			System.out.println("Username: " + username);
			System.out.println("Password: " + password);
			System.out.println("Expected Result: " + expectedResult);

			System.out.println("----------------------");
		}

		workbook.close();
		file.close();

		System.out.println("Excel Data Read Successfully!");
	}
}
