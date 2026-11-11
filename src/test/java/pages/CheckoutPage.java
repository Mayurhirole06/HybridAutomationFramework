
package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage {

	WebDriver driver;

	By firstName = By.id("first-name");
	By lastName = By.id("last-name");
	By postalCode = By.id("postal-code");
	By continueButton = By.id("continue");
	By finishButton = By.id("finish");
	By errorMessage = By.cssSelector("[data-test='error']");
	By confirmationMessage = By.className("complete-header");
	By overviewItems = By.className("cart_item");

	public CheckoutPage(WebDriver driver) {
		this.driver = driver;
	}

	public void enterCustomerDetails(String first, String last, String postal) {

		driver.findElement(firstName).sendKeys(first);
		driver.findElement(lastName).sendKeys(last);
		driver.findElement(postalCode).sendKeys(postal);
	}

	public void clickContinue() {
		driver.findElement(continueButton).click();
	}

	public void clickFinish() {
		driver.findElement(finishButton).click();
	}

	public String getErrorMessage() {
		return driver.findElement(errorMessage).getText();
	}

	public int getOverviewItemCount() {
		return driver.findElements(overviewItems).size();
	}

	public String getConfirmationMessage() {
		return driver.findElement(confirmationMessage).getText();
	}
}
