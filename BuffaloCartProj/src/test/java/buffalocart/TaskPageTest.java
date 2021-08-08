package buffalocart;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pageobjects.BuffalocartPage;
import utility.WebDriverManager;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;

public class TaskPageTest {

	WebDriver driver;
	WebDriverManager webDriverManager;
	BuffalocartPage pageFactory;

	@Test(priority = 23)
	public void navigateToTaskPage() throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(pageFactory.taskItem));
		webDriverManager.click(pageFactory.taskItem, driver);
	}

	@Test(priority = 24)
	public void performNewTask() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(pageFactory.newTask));
		webDriverManager.click(pageFactory.newTask, driver);
	}

	@Test(priority = 25)
	public void enterTaskName() {
		//WebElement TaskName = driver.findElement(By.name("task_name"));
		pageFactory.taskName.sendKeys("tasktesting");
	}

	@Test(priority = 26)
	public void enterRelatedto() {
		//WebElement relatedTo = driver.findElement(By.name("related_to"));
		Select objMultiselect = new Select(pageFactory.related_to);
		objMultiselect.selectByVisibleText("Leads");
	}

	@Test(priority = 27)
	public void entertaskDate() {
		pageFactory.taskStartDate.sendKeys("2021-07-28");
	}

	@Test(priority = 28)
	public void enterTaskDueDate() {
		pageFactory.taskDueDate.sendKeys("2021-07-31");
	}

	@Test(priority = 29)
	public void enterHrRate() {
		//WebElement hrRate = driver.findElement(By.name("hourly_rate"));
		pageFactory.hourly_rate.sendKeys("12");
	}

	@Test(priority = 30)
	public void enterEstimatedHrs() {
		//WebElement estimatedHr = driver.findElement(By.name("task_hour"));
		pageFactory.task_hour.sendKeys("24");
	}

	@Test(priority = 31)
	public void saveTaskDetails() throws InterruptedException {
		webDriverManager.showProgress(40, pageFactory.taskProgressBar);

		//WebElement status = driver.findElement(By.name("c"));
		Select objMultiselect = new Select(pageFactory.task_status);
		objMultiselect.selectByVisibleText("Deferred");

		pageFactory.taskDescription.sendKeys("success tasks");

		//WebElement savetaskBtn = driver.findElement(By.xpath("//*[@id=\"assign_task\"]/div/div/form/div[15]/button"));
		if (pageFactory.task_save_button.isDisplayed())
			webDriverManager.click(pageFactory.task_save_button, driver);

		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(pageFactory.taskItem));

		webDriverManager.click(pageFactory.taskItem, driver);
		Thread.sleep(2000);
		webDriverManager.click(pageFactory.allTaskbtn, driver);
		Thread.sleep(2000);

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

			WebElement taskName = driver
					.findElement(By.xpath("//*[@id=\"DataTables\"]/tbody/tr[" + i + "]/td[" + 2 + "]"));
			System.out.println("Task Name: " + taskName.getText());

			WebElement dueDate = driver
					.findElement(By.xpath("//*[@id=\"DataTables\"]/tbody/tr[" + i + "]/td[" + 3 + "]"));
			System.out.println("Due Date: " + dueDate.getText());

			WebElement status = driver
					.findElement(By.xpath("//*[@id=\"DataTables\"]/tbody/tr[" + i + "]/td[" + 4 + "]"));
			System.out.println("Status: " + status.getText());

			if (taskName.getText().contains("tasktesting") && dueDate.getText().contains("07.31.2021")
					&& status.getText().contains("Deferred")) {
				return true;
			}

		}
		return false;
	}

	@BeforeClass
	public void beforeClass() throws InterruptedException {
		System.out.println("----- Task Verify beforeClass------- ");
		// get instance of driver manager
		webDriverManager = WebDriverManager.getInstance();
		// get driver
		driver = webDriverManager.getDriver();
		// initialize the home page class
		pageFactory = new BuffalocartPage(driver);
		System.out.println("----- Task Verify beforeClass Ends------- ");
	}

	@AfterTest
	public void afterTest() {
		driver.quit();
	}

}
