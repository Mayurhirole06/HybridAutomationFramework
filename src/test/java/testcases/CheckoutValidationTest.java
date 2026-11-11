
package testcases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import pages.CartPage;
import pages.CheckoutPage;
import pages.LoginPage;
import pages.ProductsPage;
import utils.DriverManager;

public class CheckoutValidationTest {

	@Test
	public void verifyEmptyCustomerDetailsValidation() {

		WebDriver driver = new ChromeDriver();
		DriverManager.setDriver(driver);

		driver.get("https://www.saucedemo.com/");

		LoginPage login = new LoginPage(driver);
		login.enterUsername("standard_user");
		login.enterPassword("secret_sauce");
		login.clickLogin();

		ProductsPage products = new ProductsPage(driver);
		products.addFirstProductToCart();
		products.clickCart();

		CartPage cart = new CartPage(driver);
		cart.clickCheckout();

		CheckoutPage checkout = new CheckoutPage(driver);
		checkout.clickContinue();

		String error = checkout.getErrorMessage();

		System.out.println("Validation: " + error);

		Assert.assertTrue(error.contains("First Name is required"), "First name validation is not displayed");
	}
}
