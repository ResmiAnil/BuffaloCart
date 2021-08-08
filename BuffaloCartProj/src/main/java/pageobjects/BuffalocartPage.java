package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

/**
 * Class for buffalo cart page elements Currently this class represents all page
 * elements with appropriate comments
 * 
 * @author resmi
 *
 */
public class BuffalocartPage {
	WebDriver driver;

	public BuffalocartPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	// Login Page
	@FindBy(how = How.NAME, using = "user_name")
	public WebElement element_userName;

	@FindBy(how = How.NAME, using = "password")
	public WebElement element_usrPassword;

	@FindBy(how = How.XPATH, using = "/html/body/div[1]/div/div/div[3]/div[2]/form/button[1]")
	public WebElement element_signIn;

	@FindBy(how = How.XPATH, using = "//div[@class='error_login']/div[@class='alert alert-danger']")
	public WebElement element_errorPage;

	// Project Page ----------------------------------------------------------------
	@FindBy(how = How.XPATH, using = "/html/body/div[1]/aside[1]/div/nav/ul[2]/li[5]/a/span")
	public WebElement element_projectItem;

	@FindBy(how = How.XPATH, using = "/html/body/div[1]/section/div/div[2]/div/div[3]/div/div[2]/ul/li[2]/a")
	public WebElement element_newProject;
	
	@FindBy(how = How.NAME, using = "project_name")
	public WebElement element_projectName;

	@FindBy(how = How.XPATH, using = "//span[@class='select2-selection__rendered']")
	public WebElement element_Project_Dropdown;
	
	@FindBy(how = How.XPATH, using = "//span[@class='select2-selection__rendered']")
	public WebElement element_Project_Dropdown_select_client;
	
	@FindBy(how = How.XPATH, using = "//*[@id=\"create\"]/form/div/div[1]/div[4]/div/div[1]/span")
	public WebElement element_Project_progressBar;

	@FindBy(how = How.XPATH, using = "(//span[@class='select2-selection__rendered'])[2]")
	public WebElement element_Project_billingType;
	
	@FindBy(how = How.XPATH, using = "//*[@id=\"create\"]/form/div/div[1]/div[5]/div/div/input")
	public WebElement element_Project_startDate;
	
	@FindBy(how = How.XPATH, using = "//*[@id=\"create\"]/form/div/div[1]/div[6]/div/div/input")
	public WebElement element_Project_endDate;
	
	@FindBy(how = How.XPATH, using = "//*[@id=\"create\"]/form/div/div[1]/div[8]/div/input")
	public WebElement element_Project_fixedPrice;
	
	@FindBy(how = How.XPATH, using = "//*[@id=\"create\"]/form/div/div[1]/div[12]/div/input")
	public WebElement element_Project_demoUrl;
	
	@FindBy(how = How.XPATH, using = "(//span[@class='select2-selection__rendered'])[3]")
	public WebElement element_Project_status;
	
	@FindBy(how = How.XPATH, using = "//*[@id='create']/form/div/div[3]/div[1]/div/div/div[6]")
	public WebElement element_Project_description;

	@FindBy(how = How.XPATH, using = "//*[@id=\"create\"]/form/div/div[3]/div[2]/button")
	public WebElement element_Project_Save_Button;
	
	@FindBy(how = How.XPATH, using = "//*[@id=\"DataTables\"]/thead/tr/th")
	public WebElement element_Project_table_column;

	//======= Bugs Page ======================================================================


	@FindBy(how = How.XPATH, using = "/html/body/div[1]/aside[1]/div/nav/ul[2]/li[7]/a/span")
	public WebElement bugItem;
	@FindBy(how = How.XPATH, using = "/html/body/div[1]/section/div/div[2]/div/div[3]/div/div[2]/ul/li[2]/a")
	public WebElement newBugs;
	@FindBy(how = How.XPATH, using = "//span[@role='combobox']//span[1]")
	public WebElement reporter;
	@FindBy(how = How.NAME, using = "priority")
	public WebElement priority;
	@FindBy(how = How.NAME, using = "severity")
	public WebElement severity;

	@FindBy(how = How.XPATH, using = "//div[@class='note-editable']")
	public WebElement bugDescription;

	@FindBy(how = How.XPATH, using = "(//div[@class='note-editable'])[2]")
	public WebElement bugReproductability;

	@FindBy(how = How.XPATH, using = "//*[@id=\\\"border-none\\\"]/div/div[2]/label/span")
	public WebElement radioButton;
	
	@FindBy(how = How.XPATH, using = "//*[@id=\"permission_user_1\"]/div/div[1]/label/span")
	public WebElement checkBoxAdmin;
	
	@FindBy(how = How.XPATH, using = "//*[@id=\\\"assign_task\\\"]/div/div/form/div[13]/button")
	public WebElement saveBug;
	
	@FindBy(how = How.XPATH, using = "/html/body/div[1]/section/div/div[2]/div/div[3]/div/div[2]/ul/li[2]/a")
	public WebElement saveBugs;
	
	//======= Tasks Page ======================================================================
	@FindBy(how = How.NAME, using = "task_name")
	public WebElement taskName;
	
	@FindBy(how = How.NAME, using = "related_to")
	public WebElement related_to;
	
	@FindBy(how = How.NAME, using = "hourly_rate")
	public WebElement hourly_rate;
	
	@FindBy(how = How.NAME, using = "task_hour")
	public WebElement task_hour;
	
	@FindBy(how = How.NAME, using = "task_status")
	public WebElement task_status;
	
	@FindBy(how = How.XPATH, using = "//*[@id=\\\"assign_task\\\"]/div/div/form/div[15]/button")
	public WebElement task_save_button;
	
	@FindBy(how = How.XPATH, using = "/html/body/div[1]/aside[1]/div/nav/ul[2]/li[6]/a/span")
	public WebElement taskItem;
	@FindBy(how = How.XPATH, using = "/html/body/div[1]/section/div/div[2]/div/div[4]/div/div[2]/ul/li[2]/a")
	public WebElement newTask;
	@FindBy(how = How.NAME, using = "task_start_date")
	public WebElement taskStartDate;
	@FindBy(how = How.NAME, using = "due_date")
	public WebElement taskDueDate;

	@FindBy(how = How.XPATH, using = "//span[contains(@class,'ui-slider-handle ui-corner-all')]")
	public WebElement taskProgressBar;

	@FindBy(how = How.NAME, using = "task_status")
	public WebElement taskStatus;

	@FindBy(how = How.XPATH, using = "//*[@id=\"assign_task\"]/div/div/form/div[11]/div/div/div[6]")
	public WebElement taskDescription;

	@FindBy(how = How.XPATH, using = "//*[@id=\"assign_task\"]/div/div/form/div[15]/button")
	public WebElement saveTaskBtn;

	@FindBy(how = How.XPATH, using = "/html/body/div[1]/section/div/div[2]/div/div[4]/div/div[2]/ul/li[1]/a")

	public WebElement allTaskbtn;

}
