package buffalocart;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pageobjects.BuffalocartPage;
import utility.Constants;
import utility.ExcelReader;
import utility.WebDriverManager;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;

public class LoginPageTest {

	WebDriver driver;
	WebDriverManager webDriverManager;
	BuffalocartPage pageFactory;

	@Test(priority = 1)
	public void launchLoginPage() {
		String currenturl = driver.getCurrentUrl();
		SoftAssert objSofturlverify = new SoftAssert();
		objSofturlverify.assertEquals(currenturl, Constants.LOGIN_URL);
		System.out.println("Login page url verification result...");
		objSofturlverify.assertAll();
	}

	@Test(priority = 2)
	public void verifyloginPageTitle() {
		String titleCheck = driver.getTitle();
		SoftAssert objSoftAssert = new SoftAssert();
		objSoftAssert.assertEquals(titleCheck, Constants.MAIN_PAGE_TITLE);
		System.out.println("Main page title verification result...");
		objSoftAssert.assertAll();
	}

	@Test(priority = 3, enabled = true)
	public void inputInvalidUserName() throws InterruptedException {
		webDriverManager.validateCredentials("Abc", Constants.PASSWORD);

		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(pageFactory.element_errorPage));

		String errorMsg = pageFactory.element_errorPage.getText();
		System.out.println("Error message:" + errorMsg);

		String expectedErrorMsg = Constants.LOGIN_ERROR_MESSAGE;
		SoftAssert objSoftAssert = new SoftAssert();
		objSoftAssert.assertEquals(errorMsg, expectedErrorMsg);
		objSoftAssert.assertAll();
	}

	@Test(priority = 4, enabled = false)
	public void inputInvalidPassword() throws InterruptedException {
		webDriverManager.validateCredentials(Constants.USER_NAME, "1122334455");
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(pageFactory.element_errorPage));

		String errorMsg = pageFactory.element_errorPage.getText();
		System.out.println(errorMsg);

		String expectedErrorMsg = Constants.LOGIN_ERROR_MESSAGE;
		SoftAssert objSoftAssert = new SoftAssert();
		objSoftAssert.assertEquals(errorMsg, expectedErrorMsg);
		objSoftAssert.assertAll();
	}

	@Test(priority = 5, enabled = true)
	public void inputValidCredentilas() throws InterruptedException {
		webDriverManager.validateCredentials(Constants.USER_NAME, Constants.PASSWORD);

		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.urlToBe(Constants.LOGIN_SUCCESS_DASHBOARD));

		String expectedUrl = driver.getCurrentUrl();
		SoftAssert objSoftAssert = new SoftAssert();
		objSoftAssert.assertEquals(Constants.LOGIN_SUCCESS_DASHBOARD, expectedUrl);
		objSoftAssert.assertAll();
	}

	@AfterMethod
	public void afterMethod(ITestResult result) throws IOException {
		if (ITestResult.FAILURE == result.getStatus()) {
			webDriverManager.screenShot();
		}
	}

	@BeforeTest
	public void beforeTest() throws IOException {
		// Init WebDriverManager
		webDriverManager = WebDriverManager.getInstance();
		// Read Excel
		ExcelReader excelReader = new ExcelReader();
		String url = excelReader.readExcelRowCloumn(1, 0);
		String browser = excelReader.readExcelRowCloumn(1, 1);
		// Launch browser
		driver = webDriverManager.launchbrowser(url, browser);
		// initialize the home page class
		pageFactory = new BuffalocartPage(driver);
	}

}
