
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

public class OrderTest {

	@Test
	public void verifyCompleteOrder() {

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

		checkout.enterCustomerDetails("Mayur", "Hirole", "425001");

		checkout.clickContinue();

		Assert.assertEquals(checkout.getOverviewItemCount(), 1, "Order overview should contain one product");

		checkout.clickFinish();

		String message = checkout.getConfirmationMessage();

		System.out.println("Order Status: " + message);

		Assert.assertTrue(message.contains("Thank you for your order!"), "Order confirmation is not displayed");
	}
}
