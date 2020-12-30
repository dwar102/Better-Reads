package pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;


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
	public WebElement getSearch() {
		return driver.findElement(By.id("searchBar"));
	}
	public WebElement getSearchButton() {
		return driver.findElement(By.id("basicSearchButton"));
	}
	public WebElement getSearchType() {
		return driver.findElement(By.xpath("//input[@value='author']"));
	}
	public WebElement getWriteMessage() {
		waitForElementByName("message");
		return driver.findElement(By.name("message"));
	}
	public WebElement getRecId() {
		waitForElementByName("target");
		return driver.findElement(By.name("target"));
	}
	public WebElement getSubmitMessage() {
		waitForElementByXpath("/html/body/app-root/app-navbar/div/app-profile/div/app-writemessage/div/button");
		return driver.findElement(By.xpath("/html/body/app-root/app-navbar/div/app-profile/div/app-writemessage/div/button"));
	}
	public void waitForElementByXpath(String xpath) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(5, TimeUnit.SECONDS)
				.pollingEvery(100, TimeUnit.MILLISECONDS)
				.ignoring(NoSuchElementException.class);
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
	}
	public void waitForElementByName(String name) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(5, TimeUnit.SECONDS)
				.pollingEvery(100, TimeUnit.MILLISECONDS)
				.ignoring(NoSuchElementException.class);
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(name)));
	}
	public WebElement getView() {
		waitForElementByXpath("/html/body/app-root/app-search/div/ul[1]/li/button");
		return driver.findElement(By.xpath("/html/body/app-root/app-search/div/ul[1]/li/button"));
		}
	}
