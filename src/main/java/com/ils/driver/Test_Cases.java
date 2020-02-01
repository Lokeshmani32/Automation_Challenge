package com.ils.driver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.regex.Pattern;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.ils.genericmethods.Generic_Methods;

public class Test_Cases extends Generic_Methods {

	static String FCA = "https://www.saucedemo.com/"; // Environment variable for the testing website already set and mutliple options given at the end
	static String GH = "https://www.google.com/";   
	static String fn_env = null;
	static String Browsername = "CH";
	
	public Test_Cases() throws IOException {
		super();

	}
	
		
	public static void Web_call(String Exl_Name) throws InvalidFormatException, IOException, InterruptedException
	{
		String[][] varArray = new String[200][2];
		//Initialize varArray
		for (int i = 0; i < 200; i++) {
		    for (int j = 0; j < 2; j++) {
			
		        varArray[i][j] = "";
		    }
		}
		
		// excel is invoked in order to work with it//
		
		
		String current_path = System.getProperty("user.dir");   // current path declared to user directory
		File Excel_File = new File(current_path + "\\" + Exl_Name); 
		FileInputStream fis = new FileInputStream(Excel_File);  
		Workbook excelbook = WorkbookFactory.create(fis);
		Sheet sheet = excelbook.getSheet("Sheet1"); // map the sheet
		int lst_row = sheet.getLastRowNum(); 
		int lst_Cell = sheet.getRow(0).getLastCellNum(); // last row for the excel to know how much to iterate
		for (int rw = 0; rw <= lst_row; rw++) {
			for (int cl = 0; cl < lst_Cell; cl++) {
				if (sheet.getRow(rw).getCell(cl) == null) {
					sheet.getRow(rw).createCell(cl);
				}
				sheet.getRow(rw).getCell(cl).setCellType(1); // set cell type used to covert data to string (Shortcut for to string method)
			}
		}
		
		
		
		for (int i = 1; i <= lst_row; i++) {
			String flag = sheet.getRow(i).getCell(1).getStringCellValue();
			if (flag.equalsIgnoreCase("Y")) {

				String Action = sheet.getRow(i).getCell(2).getStringCellValue();  
				String Locator = sheet.getRow(i).getCell(3).getStringCellValue();
				String Value = sheet.getRow(i).getCell(4).getStringCellValue();
				

				System.out.println("Action---" + Action);
				try {
				switch (Action) {

				case "Browser_Name":
					driver(Value);
					break;
				case "url":
					url(env(Value));
					
					//Set varEnv for use when looking enviroment variables 
					if (Value.length() > 2) {
					} else {
					}
					break;
				case "inputvalue":
					getelement(Locator).sendKeys(Value);
					break;
				case "enter":
					getelement(Locator).sendKeys(Keys.ENTER);
					
					break;
				case "click":
					getelement(Locator).click();
					Thread.sleep(5000);
					break;
				case "submit":
					getelement(Locator).submit();
					Thread.sleep(1000);
					break;
				case "close":
					driver.quit();
					break;
				case "Alert":
					Thread.sleep(5000);
					Alert AR = driver.switchTo().alert();
					AR.accept();
					break;
				case "sleep":
					 driver.wait(10000);
					 break;
				case "max":
					driver.manage().window().fullscreen();	 
					break;
					
				case "imageMap":
					     WebElement ImageFile = getelement(Locator);
					    		
					     Boolean ImagePresent = (Boolean) ((JavascriptExecutor)driver).executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", ImageFile);
				        if (!ImagePresent)
				        {
				             System.out.println("Image not displayed.");
				        }
				        else
				        {
				            System.out.println("Image displayed.");
				        }
					
					
					break;
				case "get":
					String getstr = getelement(Locator).getText();
					//sheet.getRow(i).getCell(3).setCellValue(getstr); // if the  value need to be printed in excel
					System.out.println("Get Text value is -" + getstr);
					break;
	
					
				default:
					System.out.println("Invalid Action!");
					break;

				}

				System.out.println(">>>>>>>>>>>>>>>>>>>>> Line number -> " + i + " is completed");
				sheet.getRow(i).createCell(5).setCellValue("Passed");
				sheet.getRow(i).getCell(5).setCellStyle(Generic_Methods.passobj(excelbook));
				
				
			}
			
			catch (Exception e)
			{
				System.out.println(" Now we are in Exception block.....for FAILED for Line number.." + i);
				sheet.getRow(i).createCell(5).setCellValue("Failed");
				sheet.getRow(i).getCell(5).setCellStyle(Generic_Methods.failobj(excelbook));
			}
			
		
		
			
			
			FileOutputStream fos = new FileOutputStream(Excel_File);
			excelbook.write(fos);
				
				
				

			} else {
				sheet.getRow(i).createCell(5).setCellValue("Skipped");
				sheet.getRow(i).getCell(5).setCellStyle(Generic_Methods.skipobj(excelbook));
			}
		}
		
		System.out.println("................................. TEST CASE END .......................................");

	}

	public static String env(String val) {
		if (val.equalsIgnoreCase("FCA")) {
			fn_env = FCA;
			
			// if more variable needs to be utilised please use .
		} else if (val.equalsIgnoreCase("FCB")) {
			//fn_env = FCB; /// 
		} else if (val.equalsIgnoreCase("FCD")) {
			//fn_env = FCD;
		} else if (val.equalsIgnoreCase("FH")) {
			//fn_env = FH;
		}
		else {
			fn_env =val;
		}
			
		return fn_env;
	}

}

