package org.common;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class WebDriverHelper {

	public static WebDriver driver = null;

	@BeforeClass
	public void setUp() throws Exception {

		ConfigProperty config = new ConfigProperty();

		if (config.getBrowser().equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		} else if (config.getBrowser().equalsIgnoreCase("chrome")) {
			if (config.getOpratingSystem().equalsIgnoreCase("mac")) {
				System.setProperty("webdriver.chrome.driver", "./Drivers/chromedriver");
			} else if (config.getOpratingSystem().equalsIgnoreCase("windows")) {
				System.setProperty("webdriver.chrome.driver", "./Drivers/chromedriver.exe");
			}

			driver = new ChromeDriver();
		} else if (config.getBrowser().equalsIgnoreCase("internetExplorer")) {
			driver = new InternetExplorerDriver();

		} else {
			System.out.println(config.getBrowser());
		}

		driver.get(config.getBaseUrl());
		driver.manage().timeouts().implicitlyWait(config.getWaitTime(), TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@AfterClass
	public void tearDown() {
		driver.close();
		driver.quit();
	}

}
