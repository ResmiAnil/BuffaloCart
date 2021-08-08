package buffalocart;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pageobjects.BuffalocartPage;
import utility.Constants;
import utility.WebDriverManager;

import org.testng.annotations.BeforeMethod;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

public class ProjectPageTest {

	WebDriver driver;
	WebDriverManager webDriverManager;
	BuffalocartPage pageFactory;

	@Test(priority = 6)
	public void navigateToProjectPage() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(pageFactory.element_projectItem));
		webDriverManager.click(pageFactory.element_projectItem, driver);
	}

	@Test(priority = 7)
	public void triggerNewProject() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(pageFactory.element_newProject));
		webDriverManager.click(pageFactory.element_newProject, driver);
	}

	@Test(priority = 8)
	public void input_project_name_client() throws InterruptedException {
		pageFactory.element_projectName.sendKeys(Constants.PROJECT_NAME);
		WebDriverWait wait1 = new WebDriverWait(driver, 10);
		wait1.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//span[@class='select2-selection__rendered']")));

		pageFactory.element_Project_Dropdown_select_client.click();
		webDriverManager.setSelection(4);
	}

	@Test(priority = 9)
	public void setProgressBar() {
		webDriverManager.showProgress(30, pageFactory.element_Project_progressBar);
		webDriverManager.click(pageFactory.element_Project_billingType, driver);
		// selecting 4th item
		webDriverManager.setSelection(4);
		System.out.println("Text----" + pageFactory.element_Project_billingType.getText());
	}

	@Test(priority = 10)
	public void setStartAndEndDate() throws InterruptedException {
		pageFactory.element_Project_startDate.sendKeys(Constants.PROJECT_START_DATE);
		pageFactory.element_Project_endDate.sendKeys(Constants.PROJECT_END_DATE);
	}

	@Test(priority = 11)
	public void verifyProjectPrice() {
		String fp = pageFactory.element_Project_fixedPrice.getAttribute("placeholder");
		System.out.println("fixed price is " + fp);
		String demo = pageFactory.element_Project_demoUrl.getAttribute("placeholder");
		System.out.println("Demo url is " + demo);
	}

	@Test(priority = 12)
	public void setProjectStatus() throws InterruptedException {
		webDriverManager.click(pageFactory.element_Project_status, driver);
		webDriverManager.setSelection(4);
		pageFactory.element_Project_description.sendKeys("Test Description");
	}

	@Test(priority = 13)
	public void performSave() throws InterruptedException {
		webDriverManager.click(pageFactory.element_Project_Save_Button, driver);

		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(pageFactory.element_projectItem));
		webDriverManager.click(pageFactory.element_projectItem, driver);
		Thread.sleep(2000);

		List<WebElement> col = driver.findElements(By.xpath("//*[@id=\"DataTables\"]/thead/tr/th"));
		System.out.println("col number is" + col.size());

		List<WebElement> rows = driver.findElements(By.xpath("//*[@id=\"DataTables\"]/tbody/tr/td[1]"));
		System.out.println("row number is" + rows.size());

		boolean resultFindData = findTableData();
		if (resultFindData) {
			System.out.println("Element Found");
		} else {
			System.out.println("Element Not Found!!!");
		}

		SoftAssert objSoftAssert = new SoftAssert();
		objSoftAssert.assertEquals(resultFindData, true);
		objSoftAssert.assertAll();
	}

	public boolean findTableData() {
		List<WebElement> rows = driver.findElements(By.xpath("//*[@id=\"DataTables\"]/tbody/tr/td[1]"));

		for (int i = 1; i <= rows.size(); i++) {

			WebElement projectName = driver.findElement(By.xpath("//*[@id=\"DataTables\"]/tbody/tr/td[" + 2 + "]"));
			System.out.println("Project Name = " + projectName.getText());

			WebElement projectShell = driver.findElement(By.xpath("//*[@id=\"DataTables\"]/tbody/tr/td[" + 3 + "]"));
			System.out.println("Project Shell=" + projectShell.getText());

			WebElement projectEndDate = driver.findElement(By.xpath("//*[@id=\"DataTables\"]/tbody/tr/td[" + 4 + "]"));
			System.out.println("Project EndDate= " + projectEndDate.getText());

			WebElement projectStatus = driver.findElement(By.xpath("//*[@id=\"DataTables\"]/tbody/tr/td[" + 6 + "]"));
			System.out.println("Status = " + projectStatus.getText());

			if (projectName.getText().contains(Constants.PROJECT_NAME) && projectShell.getText().contains("Shell")
					&& projectEndDate.getText().contains("07.31.2021") && projectStatus.getText().contains("Cancel")) {
				return true;
			}

		}
		return false;
	}

	@BeforeMethod
	public void beforeMethod() {
	}

	@BeforeClass
	public void beforeClass() throws InterruptedException {
		// get instance of driver manager
		webDriverManager = WebDriverManager.getInstance();
		// get driver
		driver = webDriverManager.getDriver();
		// initialize the home page class
		pageFactory = new BuffalocartPage(driver);
	}

	@AfterMethod
	public void afterMethod(ITestResult result) throws IOException {
		if (ITestResult.FAILURE == result.getStatus()) {
			webDriverManager.screenShot();
		}
	}
}
