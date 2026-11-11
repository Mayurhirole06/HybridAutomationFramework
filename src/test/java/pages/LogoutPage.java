
package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LogoutPage {

	WebDriver driver;

	By menuButton = By.id("react-burger-menu-btn");
	By logoutLink = By.id("logout_sidebar_link");

	public LogoutPage(WebDriver driver) {
		this.driver = driver;
	}

	public void logout() {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

		wait.until(ExpectedConditions.elementToBeClickable(menuButton)).click();

		wait.until(ExpectedConditions.elementToBeClickable(logoutLink)).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login-button")));
	}
}
