package tests;

import org.testng.annotations.Test;

import components.SearchFlights;
import utils.AppUtils;
import utils.ConstantsUtils;
import utils.ExcelUtils;

import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterMethod;

public class Bussiness_SearchFlights {

	public String env;
	public String browser;
	public String url;
	public WebDriver driver;
	public Properties prop;
	public int testCase = 0;
	public HashMap<String, String> data;

	@Test
	public void test1() throws Exception {
		testCase++;
		data = new HashMap<String, String>();
		data = ExcelUtils.readTestData(data,testCase);
		SearchFlights.searchFlights(driver, data);

	}

	@Test
	public void test2() throws Exception {
		testCase++;
		data = new HashMap<String, String>();
		data = ExcelUtils.readTestData(data,testCase);
		SearchFlights.searchFlights(driver, data);
	}

	@BeforeMethod
	public void beforeMethod() throws FileNotFoundException, IOException {
		prop = new Properties();
		prop.load(new FileInputStream(new File(ConstantsUtils.configPath)));
		env = prop.getProperty("env");
		browser = prop.getProperty("browser");
		switch (browser) {
		case "chrome":
			System.setProperty("webdriver.chrome.driver", ConstantsUtils.chromePath);
			driver = new ChromeDriver();
			break;
		case "ff":
			System.setProperty("webdriver.gecko.driver", ConstantsUtils.ffPath);
			driver = new FirefoxDriver();
			break;
		case "ie":
			System.setProperty("webdriver.ie.driver", ConstantsUtils.iePath);
			driver = new InternetExplorerDriver();
			break;
		default:
			System.out.println("enter valid browser");
			break;
		}
		url = prop.getProperty(env + "_url");
		AppUtils.invokeApp(driver, url);
	}

	@AfterMethod
	public void afterMethod() {
		AppUtils.closeBrowser(driver);
	}

}
