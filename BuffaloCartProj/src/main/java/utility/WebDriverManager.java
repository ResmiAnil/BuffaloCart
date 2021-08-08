package utility;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import com.google.common.io.Files;

import pageobjects.BuffalocartPage;

/**
 * Class for managing driver and having utility functions
 * @author resmi
 *
 */
public class WebDriverManager {
	WebDriver driver;
	BuffalocartPage pageFactory;

	private static WebDriverManager single_instance = null;
	// private constructor
	private WebDriverManager() {
	  }
	 // static method to create instance of Singleton class public static
	public static  WebDriverManager getInstance() { 
		 if (single_instance == null) 
			 single_instance = new WebDriverManager();
	
	  return single_instance; 
	  }
	
	 public WebDriver getDriver() {
		 return driver;
	 }

	/*
	 * public enum TestBrowser { CHROME, EDGE, FIREFOX }
	 */
	public WebDriver launchbrowser(String url, String browserType) {

		switch (browserType) {
		case "CHROME": {
			System.setProperty("webdriver.chrome.driver",
					"C:\\Working folder\\selenium\\WebDrivers\\chromedriver_win32\\chromedriver.exe");
			driver = new ChromeDriver();
			driver.get(url);
			driver.manage().window().maximize();
		}
			break;

		case "EDGE": {
			System.setProperty("webdriver.edge.driver",
					"C:\\Working folder\\selenium\\WebDrivers\\edgedriver_win32\\msedgedriver.exe");
			driver = new EdgeDriver();
			driver.get(url);
			driver.manage().window().maximize();
		}
			break;

		case "FIREFOX": {
			System.setProperty("webdriver.gecko.driver",
					"C:\\Working folder\\selenium\\WebDrivers\\geckodriver-v0.29.1-win32\\geckodriver.exe");
			driver = new FirefoxDriver();
			driver.get(url);
			driver.manage().window().maximize();

		}
			break;

		}


		pageFactory = new BuffalocartPage(driver);
		return driver;
	}

	public void click(WebElement element, WebDriver driver) {
		// driver.findElement(value).click();
		element.click();
	}

	public boolean elementStatus(WebElement element, WebDriver driver) {
		boolean status = element.isEnabled();
		return status;
	}

	public void alertOKClick(String sendArgument, WebDriver driver) {
		Alert alert = driver.switchTo().alert();
		if (!sendArgument.isEmpty())
			alert.sendKeys(sendArgument);
		alert.accept();
	}

	public void alertCancelClick(String sendArgument, WebDriver driver) {
		Alert alert = driver.switchTo().alert();
		if (!sendArgument.isEmpty())
			alert.sendKeys(sendArgument);
		alert.dismiss();
	}

	public void screenShot() throws IOException {
		TakesScreenshot scrShot = ((TakesScreenshot) driver);
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm_ss");
		LocalDateTime now = LocalDateTime.now();

		String fileWithPath = "C:/Working folder/selenium/BuffaloScreenshots/Fail_"
				+ dtf.format(now) + ".png";
		// Call getScreenshotAs method to create image file

		File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);

		// Move image file to new destination

		File DestFile = new File(fileWithPath);

		// Copy file at destination

		Files.copy(SrcFile, DestFile);
	}

	public void waitUrl(WebDriver driver, int value, String url) {
		WebDriverWait wait = new WebDriverWait(driver, value);
		wait.until(ExpectedConditions.urlContains(url));
	}

	public void waitElementClick(WebDriver driver, int value, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, value);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public void waitAlertisPresent(WebDriver driver, int value, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, value);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	public void validateCredentials(String userName, String password) throws InterruptedException {
		pageFactory.element_userName.sendKeys(userName);
		pageFactory.element_usrPassword.sendKeys(password);
		pageFactory.element_signIn.click();
	}
	
	public void setSelection(int position) {
		for (int i = 0; i <= position; i++) {
			Actions actions = new Actions(driver);
			actions.sendKeys(Keys.DOWN).build().perform();// press down arrow key
			Actions actions2 = new Actions(driver);
			actions2.sendKeys(Keys.ENTER).build().perform();// press enter
		}

	}
	
	public void showProgress(int progress, WebElement webElement) {
		for (int i = 1; i <= progress; i++) {
			webElement.sendKeys(Keys.ARROW_RIGHT);
		}
	}
}
