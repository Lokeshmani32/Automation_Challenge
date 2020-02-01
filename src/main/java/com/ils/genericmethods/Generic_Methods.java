package com.ils.genericmethods;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class Generic_Methods {

	public static WebDriver driver;

	public static WebElement getelement(String elementname) throws IOException {

		String[] arr = elementname.split("#");
		WebElement we = null;
		if (arr[0].equalsIgnoreCase("name") == true) {
			we = driver.findElement(By.name(arr[1]));

		} else if (arr[0].equalsIgnoreCase("linklist")) {

			we = driver.findElement(By.linkText(arr[1]));
		} else if (arr[0].equalsIgnoreCase("xpath")) {

			we = driver.findElement(By.xpath(arr[1]));
		}
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		return we;
	}

	public static void click(String elementname) throws IOException {
		WebElement we = getelement(elementname);
		we.click();
	}

	public static void openapp(String brname, String url) {

		if (brname.equalsIgnoreCase("FF") == true) {
			driver = new FirefoxDriver(); // firefox is default set in selenium

		} else if (brname.equalsIgnoreCase("CH") == true) {

			System.setProperty("webdriver.chrome.driver", "\\driver\\chromedriver.exe");
			driver = new ChromeDriver();
		} else if (brname.equalsIgnoreCase("IE") == true) {
			System.setProperty("webdriver.ie.driver", "drivers/IEDriverServer.exe");
			driver = new InternetExplorerDriver();
		}

		driver.get(url);
		//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		//driver.manage().window().maximize();
	}

	public static WebDriver driver(String brname) {
		String current_path = System.getProperty("user.dir");

		if (brname.equalsIgnoreCase("firefox") == true) {
			driver = new FirefoxDriver();

		} else if (brname.equalsIgnoreCase("Chrome") == true) {

			System.setProperty("webdriver.chrome.driver", current_path + "\\driver\\chromedriver.exe");
			driver = new ChromeDriver();
		} else if (brname.equalsIgnoreCase("IE") == true) {
			System.setProperty("webdriver.ie.driver", "drivers/IEDriverServer.exe");
			driver = new InternetExplorerDriver();
		}

		return driver;
	}

	public static void url(String url) {

		driver.get(url);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	    driver.manage().window().maximize();

	}
	
	
	public static CellStyle passobj(Workbook wbook) {
		
		CellStyle passobj = wbook.createCellStyle();
		Short pass_col_num = IndexedColors.BRIGHT_GREEN.getIndex();
		passobj.setFillBackgroundColor(pass_col_num);
		passobj.setFillForegroundColor(pass_col_num);
		passobj.setFillPattern(CellStyle.SOLID_FOREGROUND);
		passobj.setBorderBottom(XSSFCellStyle.SOLID_FOREGROUND);
		passobj.setBorderTop(XSSFCellStyle.BORDER_THIN);
		passobj.setBorderRight(XSSFCellStyle.BORDER_THIN);
		passobj.setBorderLeft(XSSFCellStyle.BORDER_THIN);
		return passobj;
		
	}

	public static CellStyle failobj(Workbook wbook) {
		
		CellStyle failobj = wbook.createCellStyle();
		Short pass_col_num = IndexedColors.RED.getIndex();
		failobj.setFillBackgroundColor(pass_col_num);
		failobj.setFillForegroundColor(pass_col_num);
		failobj.setFillPattern(CellStyle.SOLID_FOREGROUND);
		failobj.setBorderBottom(XSSFCellStyle.SOLID_FOREGROUND);
		failobj.setBorderTop(XSSFCellStyle.BORDER_THIN);
		failobj.setBorderRight(XSSFCellStyle.BORDER_THIN);
		failobj.setBorderLeft(XSSFCellStyle.BORDER_THIN);
		return failobj;
		
	}
	
	public static CellStyle skipobj(Workbook wbook) {
		
		CellStyle skipobj= wbook.createCellStyle();
		Short pass_col_num = IndexedColors.LIGHT_YELLOW.getIndex();
		skipobj.setFillBackgroundColor(pass_col_num);
		skipobj.setFillForegroundColor(pass_col_num);
		skipobj.setFillPattern(CellStyle.SOLID_FOREGROUND);
		skipobj.setBorderBottom(XSSFCellStyle.SOLID_FOREGROUND);
		skipobj.setBorderTop(XSSFCellStyle.BORDER_THIN);
		skipobj.setBorderRight(XSSFCellStyle.BORDER_THIN);
		skipobj.setBorderLeft(XSSFCellStyle.BORDER_THIN);
		return skipobj;
		
	}
	
	public static String timestamp(){
		String str=DateFormat.getDateTimeInstance().format(new Date());
		str=str.replaceAll(":", "_");
		return str;
			
		}


 
}

