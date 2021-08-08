package buffalocart;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pageobjects.BuffalocartPage;
import utility.WebDriverManager;

public class BugsPageTest {

	WebDriver driver;
	WebDriverManager webDriverManager;
	BuffalocartPage pageFactory;

	@Test(priority = 14)
	public void navigateToBugsPage() throws InterruptedException {
		System.out.println("----- Bug VerifyPage------- ");
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(pageFactory.bugItem));
		webDriverManager.click(pageFactory.bugItem, driver);
	}

	@Test(priority = 15)
	public void performBugButtonClick() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(pageFactory.newBugs));
		webDriverManager.click(pageFactory.newBugs, driver);
	}

	@Test(priority = 16)
	public void inputIssueDetails() {
		WebElement issueNo = driver.findElement(By.name("issue_no"));
		System.out.println(issueNo.getText());
		WebElement Bugtitle = driver.findElement(By.name("bug_title"));
		Bugtitle.sendKeys("testverify");
	}

	@Test(priority = 17)
	public void selectRelatedDrowndown() {
		WebElement relatedTo = driver.findElement(By.id("check_related"));
		Select objMultiselect = new Select(relatedTo);
		objMultiselect.selectByVisibleText("Projects");
	}

	@Test(priority = 18)
	public void selectReporterDropdown() {
		webDriverManager.click(pageFactory.reporter, driver);
		webDriverManager.setSelection(3);
	}

	@Test(priority = 19)
	public void selectPrioritydata() {

		Select objMultisel = new Select(pageFactory.priority);
		objMultisel.selectByVisibleText("Medium");
		Select objMultis = new Select(pageFactory.severity);
		objMultis.selectByVisibleText("Major");
	}

	@Test(priority = 20)
	public void inputDescription() throws InterruptedException {
		pageFactory.bugDescription.sendKeys("Test success11");
		pageFactory.bugReproductability.sendKeys("reproducatbility  test");
	}

	@Test(priority = 21)
	public void inputBugStatus() throws InterruptedException {
		WebElement bugStatus = driver.findElement(By.name("bug_status"));
		Select objMult = new Select(bugStatus);
		objMult.selectByVisibleText("Resolved");

		SoftAssert objSoftAssert = new SoftAssert();
		// radio displayed or not
		boolean displayed = pageFactory.radioButton.isDisplayed();
		objSoftAssert.assertEquals(displayed, true);

		pageFactory.radioButton.click();
		Thread.sleep(3000);
		//WebElement checkBoxadmin = driver.findElement(By.xpath("//*[@id=\"permission_user_1\"]/div/div[1]/label/span"));
		if (pageFactory.checkBoxAdmin.isDisplayed())
			pageFactory.checkBoxAdmin.click();

		//WebElement saveButton = driver.findElement(By.xpath("//*[@id=\"assign_task\"]/div/div/form/div[13]/button"));

		if (pageFactory.saveBug.isEnabled())
			pageFactory.saveBug.click();

	}

	@Test(priority = 22)
	public void savingbugsDetails() throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(pageFactory.bugItem));
		webDriverManager.click(pageFactory.bugItem, driver);
		pageFactory.saveBugs.click();

		Thread.sleep(3000);

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
		// *[@id="DataTables"]

		for (int i = 1; i <= rows.size(); i++) {

			WebElement BugTitle = driver
					.findElement(By.xpath("//*[@id=\"DataTables\"]/tbody/tr[" + i + "]/td[" + 1 + "]"));
			System.out.println("Bug Title = " + BugTitle.getText());

			WebElement Severity = driver
					.findElement(By.xpath("//*[@id=\"DataTables\"]/tbody/tr[" + i + "]/td[" + 4 + "]"));
			System.out.println("Severity = " + Severity.getText());

			WebElement Reporter = driver
					.findElement(By.xpath("//*[@id=\"DataTables\"]/tbody/tr[" + i + "]/td[" + 5 + "]"));
			System.out.println("Reporter = " + Reporter.getText());

			if (BugTitle.getText().contains("testverify") && Severity.getText().contains("Major")
					&& Reporter.getText().contains("xty")) {
				return true;
			}

		}
		return false;
	}

	@BeforeClass
	public void beforeClass() throws InterruptedException {
		System.out.println("----- Bug Verify beforeClass------- ");
		// get instance of driver manager
		webDriverManager = WebDriverManager.getInstance();
		// get driver
		driver = webDriverManager.getDriver();
		// initialize the home page class
		pageFactory = new BuffalocartPage(driver);
		System.out.println("----- Bug Verify beforeClass Ends------- ");
	}

}
