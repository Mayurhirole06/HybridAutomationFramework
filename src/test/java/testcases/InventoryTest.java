
package testcases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import pages.LoginPage;
import pages.ProductsPage;
import utils.DriverManager;

public class InventoryTest {

	@Test
	public void verifyProductsDisplayed() {

		WebDriver driver = new ChromeDriver();

		DriverManager.setDriver(driver);

		driver.get("https://www.saucedemo.com/");

		LoginPage login = new LoginPage(driver);

		login.enterUsername("standard_user");
		login.enterPassword("secret_sauce");
		login.clickLogin();

		ProductsPage products = new ProductsPage(driver);

		int actualCount = products.getProductCount();

		Assert.assertEquals(actualCount, 6, "Expected 6 products on inventory page");
	}
}
