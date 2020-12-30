package dev.app;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import pages.BetterReadsHome;

public class BetterReadsAutomation {
	public static void main(String[] args) {
		File file = new File("src/test/resources/chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
		
		WebDriver driver = new ChromeDriver();
		BetterReadsHome readHome = new BetterReadsHome(driver);
		
		readHome.navigateTo();
		readHome.getUsername().sendKeys("hey");
		readHome.getPassword().sendKeys("joe");
		readHome.getLogin().click();
		readHome.getWriteMessage().sendKeys("Hello there");
		readHome.getRecId().sendKeys("68");
		readHome.getSubmitMessage().click();
		driver.quit();
	}
}