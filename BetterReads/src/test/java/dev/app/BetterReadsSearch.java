package dev.app;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import pages.BetterReadsHome;

public class BetterReadsSearch {
	public static void main(String[] args) {
		File file = new File("src/test/resources/chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
		
		WebDriver driver = new ChromeDriver();
		BetterReadsHome betterReads = new BetterReadsHome(driver);
		
		betterReads.navigateTo();
		betterReads.getSearchType().click();
		betterReads.getSearch().sendKeys("Gene Wolfe");
		betterReads.getSearchButton().click();
		betterReads.getView().click();
	}
}
