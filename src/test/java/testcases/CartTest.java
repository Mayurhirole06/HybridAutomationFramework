
package testcases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import pages.CartPage;
import pages.LoginPage;
import pages.ProductsPage;
import utils.DriverManager;

public class CartTest {

	@Test
	public void verifyCartProductAndRemove() {

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

		Assert.assertEquals(cart.getCartItemCount(), 1);

		System.out.println("Product: " + cart.getProductName());
		System.out.println("Price: " + cart.getProductPrice());

		cart.removeProduct();

		Assert.assertEquals(cart.getCartItemCount(), 0, "Cart should be empty after removing product");
	}
}
