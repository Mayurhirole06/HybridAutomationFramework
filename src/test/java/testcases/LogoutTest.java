
package testcases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import pages.LoginPage;
import pages.LogoutPage;
import utils.DriverManager;

public class LogoutTest {

	@Test
	public void verifyLogout() {

		WebDriver driver = new ChromeDriver();
		DriverManager.setDriver(driver);

		driver.get("https://www.saucedemo.com/");

		LoginPage login = new LoginPage(driver);
		login.enterUsername("standard_user");
		login.enterPassword("secret_sauce");
		login.clickLogin();

		LogoutPage logout = new LogoutPage(driver);
		logout.logout();

		Assert.assertTrue(driver.getCurrentUrl().contains("saucedemo.com"), "Logout should redirect to login page");

		Assert.assertTrue(driver.findElement(org.openqa.selenium.By.id("login-button")).isDisplayed(),
				"Login button should be displayed after logout");
	}
}
