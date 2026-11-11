package testcases;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class ProductsPage {

	WebDriver driver;

	By products = By.className("inventory_item");
	By productNames = By.className("inventory_item_name");
	By sortDropdown = By.className("product_sort_container");

	public ProductsPage(WebDriver driver) {
		this.driver = driver;
	}

	public int getProductCount() {
		return driver.findElements(products).size();
	}

	public void sortProductsAtoZ() {

		Select sort = new Select(driver.findElement(sortDropdown));

		sort.selectByValue("az");
	}

	public List<String> getProductNames() {

		List<String> names = new ArrayList<>();

		List<WebElement> elements = driver.findElements(productNames);

		for (WebElement element : elements) {
			names.add(element.getText());
		}

		return names;
	}

	public boolean verifyAtoZSorting() {

		List<String> actualNames = getProductNames();

		List<String> expectedNames = new ArrayList<>(actualNames);

		Collections.sort(expectedNames);

		return actualNames.equals(expectedNames);
	}
}