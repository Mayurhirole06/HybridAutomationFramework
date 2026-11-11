
package testcases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import pages.LoginPage;
import pages.ProductsPage;
import utils.DriverManager;

public class AddToCartTest {

	@Test
	public void verifyAddToCart() {

		WebDriver driver = new ChromeDriver();

		DriverManager.setDriver(driver);

		driver.get("https://www.saucedemo.com/");

		LoginPage login = new LoginPage(driver);

		login.enterUsername("standard_user");
		login.enterPassword("secret_sauce");
		login.clickLogin();

		ProductsPage products = new ProductsPage(driver);

		products.addFirstProductToCart();

		int cartCount = products.getCartItemCount();

		System.out.println("Cart Item Count: " + cartCount);

		Assert.assertEquals(cartCount, 1, "Product was not added to cart");
	}
}
