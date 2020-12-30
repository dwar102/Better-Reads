package pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class BetterReadsHome {
	private WebDriver driver;
	
	public BetterReadsHome(WebDriver d) {
		driver = d;
	}
	public void navigateTo() {
		driver.get("localhost:4200");
	}
	public WebElement getUsername() {
		return driver.findElement(By.name("user"));
	}
	public WebElement getPassword() {
		return driver.findElement(By.name("pass"));
	}
	public WebElement getLogin() {
		return driver.findElement(By.id("loginBtn"));
	}
}