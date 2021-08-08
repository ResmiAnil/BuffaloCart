package utility;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Class for read excel
 * @author resmi
 *
 */
public class Constants {

	// login Url
	public static String LOGIN_URL =  "https://erp.buffalocart.com/login";
	// Title
	public static String MAIN_PAGE_TITLE =  "Welcome to Codecarrots";
	// User name
	public static String USER_NAME =  "admin";
	// Password
	public static String PASSWORD =  "123456";
	// Login success dashboard
	public static String LOGIN_SUCCESS_DASHBOARD =  "https://erp.buffalocart.com/admin/dashboard";
	// Login error message
	public static String LOGIN_ERROR_MESSAGE =  "username or password information doesn't exist!";
	
	
	//Project sample data
	public static String PROJECT_NAME =  "Buffalo_Project";
	public static String PROJECT_CLIENT = "Shell";
	public static String PROJECT_START_DATE =  "2021-07-27";
	public static String PROJECT_END_DATE =  "2021-07-31";
	public static String PROJECT_STATUS = "Cancel";
}
