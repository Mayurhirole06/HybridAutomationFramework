
package pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductsPage {

	WebDriver driver;

	By products = By.className("inventory_item");
	By productNames = By.className("inventory_item_name");
	By sortDropdown = By.className("product_sort_container");
	By addToCartButtons = By.className("btn_inventory");
	By cartIcon = By.className("shopping_cart_link");
	By cartBadge = By.className("shopping_cart_badge");

	public ProductsPage(WebDriver driver) {
		this.driver = driver;
	}

	public int getProductCount() {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

		wait.until(ExpectedConditions.visibilityOfElementLocated(products));

		return driver.findElements(products).size();
	}

	public void sortProductsAtoZ() {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

		WebElement dropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(sortDropdown));

		Select sort = new Select(dropdown);
		sort.selectByValue("az");
	}

	public boolean verifyAtoZSorting() {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

		wait.until(ExpectedConditions.visibilityOfElementLocated(productNames));

		List<String> actualNames = new ArrayList<>();

		List<WebElement> elements = driver.findElements(productNames);

		for (WebElement element : elements) {
			actualNames.add(element.getText());
		}

		List<String> expectedNames = new ArrayList<>(actualNames);

		Collections.sort(expectedNames, String.CASE_INSENSITIVE_ORDER);

		System.out.println("Actual: " + actualNames);
		System.out.println("Expected: " + expectedNames);

		return actualNames.equals(expectedNames);
	}

	public void addFirstProductToCart() {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

		WebElement button = wait.until(ExpectedConditions.elementToBeClickable(addToCartButtons));

		button.click();

		wait.until(ExpectedConditions.textToBePresentInElementLocated(addToCartButtons, "Remove"));

		wait.until(ExpectedConditions.textToBePresentInElementLocated(cartBadge, "1"));
	}

	public int getCartItemCount() {

		List<WebElement> badge = driver.findElements(cartBadge);

		if (badge.isEmpty()) {
			return 0;
		}

		return Integer.parseInt(badge.get(0).getText());
	}

	public void clickCart() {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

		wait.until(ExpectedConditions.elementToBeClickable(cartIcon)).click();

		wait.until(ExpectedConditions.urlContains("cart.html"));

		wait.until(ExpectedConditions.presenceOfElementLocated(By.className("cart_item")));
	}
}
