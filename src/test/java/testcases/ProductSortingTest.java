
package testcases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import pages.LoginPage;
import pages.ProductsPage;
import utils.DriverManager;

public class ProductSortingTest {

	@Test
	public void verifyProductSortingAtoZ() {

		WebDriver driver = new ChromeDriver();

		DriverManager.setDriver(driver);

		driver.get("https://www.saucedemo.com/");

		LoginPage login = new LoginPage(driver);

		login.enterUsername("standard_user");
		login.enterPassword("secret_sauce");
		login.clickLogin();

		ProductsPage products = new ProductsPage(driver);

		products.sortProductsAtoZ();

		Assert.assertTrue(products.verifyAtoZSorting(), "Products are not sorted A to Z");
	}
}
