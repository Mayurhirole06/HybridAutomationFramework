package testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

public class LoginTest {

	@Test
	public void validLoginTest() {

		WebDriver driver = new ChromeDriver();

		driver.manage().window().maximize();

		driver.get("https://www.saucedemo.com/");

		driver.findElement(By.id("user-name")).sendKeys("standard_user");

		driver.findElement(By.id("password")).sendKeys("secret_sauce");

		driver.findElement(By.id("login-button")).click();

		AssertJUnit.assertTrue(driver.getCurrentUrl().contains("inventory.html"));

		driver.quit();
	}

	@Test

	public void invalidUsernameTest() {

		WebDriver driver = new ChromeDriver();

		driver.manage().window().maximize();

		driver.get("https://www.saucedemo.com/");

		driver.findElement(By.id("user-name")).sendKeys("wrong_user");

		driver.findElement(By.id("password")).sendKeys("secret_sauce");

		driver.findElement(By.id("login-button")).click();

		String errorMessage = driver.findElement(By.cssSelector("[data-test='error']")).getText();

		Assert.assertTrue(errorMessage.contains("Username and password do not match"), "Error message not displayed");

		System.out.println("Invalid Username Test Passed!");

	}

	@Test

	public void invalidPassword() {

		WebDriver driver = new ChromeDriver();

		driver.manage().window().maximize();

		driver.get("https://www.saucedemo.com/");

		driver.findElement(By.id("user-name")).sendKeys("wrong_user");

		driver.findElement(By.id("password")).sendKeys("secret_sauce");

		driver.findElement(By.id("login-button")).click();

		String errorMessage = driver.findElement(By.cssSelector("[data-test='error']")).getText();

		Assert.assertTrue(errorMessage.contains("Username and password do not match any user in this service"));

		System.out.println("Invalid Password Test Passed");

		driver.quit();
	}

	@Test

	public void emptyUsernamePassword() {
		WebDriver driver = new ChromeDriver();

		driver.manage().window().maximize();

		driver.get("https://www.saucedemo.com/");

		driver.findElement(By.id("user-name")).sendKeys("");

		driver.findElement(By.id("password")).sendKeys("");

		driver.findElement(By.id("login-button")).click();

		String errorMessage = driver.findElement(By.cssSelector("[data-test='error']")).getText();

		Assert.assertTrue(errorMessage.contains(" Username is required"));

		System.out.println("Empty Username and Password Test Passed");

		driver.quit();
	}

	@Test

	public void emptyUsername() {

		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();

		driver.get("https://www.saucedemo.com/");

		driver.findElement(By.id("user-name")).sendKeys("");

		driver.findElement(By.id("password")).sendKeys("secret_sauce");

		driver.findElement(By.id("login-button")).click();

		String errorMessage = driver.findElement(By.cssSelector("[data-test='error']")).getText();

		Assert.assertTrue(errorMessage.contains(" Username is required"));

		System.out.println("Empty Username  Test Passed");

		driver.quit();
	}

	@Test

	public void emptyPassword() {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();

		driver.get("https://www.saucedemo.com/");

		driver.findElement(By.id("user-name")).sendKeys("standard_user");

		driver.findElement(By.id("password")).sendKeys("");

		driver.findElement(By.id("login-button")).click();

		String errorMessage = driver.findElement(By.cssSelector("[data-test='error']")).getText();

		Assert.assertTrue(errorMessage.contains(" Password is required"));

		System.out.println("Empty Username  Test Passed");

		driver.quit();
	}
}