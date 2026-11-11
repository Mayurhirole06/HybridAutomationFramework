
package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage {

	WebDriver driver;

	By cartItems = By.className("cart_item");
	By productName = By.className("inventory_item_name");
	By productPrice = By.className("inventory_item_price");
	By removeButton = By.className("cart_button");
	By checkoutButton = By.id("checkout");

	public CartPage(WebDriver driver) {
		this.driver = driver;
	}

	public int getCartItemCount() {
		return driver.findElements(cartItems).size();
	}

	public String getProductName() {
		return driver.findElement(productName).getText();
	}

	public String getProductPrice() {
		return driver.findElement(productPrice).getText();
	}

	public void removeProduct() {
		driver.findElement(removeButton).click();
	}

	public void clickCheckout() {
		driver.findElement(checkoutButton).click();
	}
}
