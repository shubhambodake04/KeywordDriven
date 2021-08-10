package com.engine;

import com.base.Base;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class KeywordEngine {
    public WebDriver driver;
    public Properties prop;

    public static Workbook book;
    public static Sheet sheet;

    public Base base;
    public WebElement element;

    public final String SCENARIO_SHEET_PATH = "C:\\Users\\shubh\\IdeaProjects\\KeywordDeiven\\src\\main\\java\\com\\scenarios\\FacebookData.xlsx";

    public void execution(String SheetName) throws Exception {
        String locatorName = null;
        String locatorValue = null;

        FileInputStream file = null;
        try {
            file = new FileInputStream(SCENARIO_SHEET_PATH);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            book = WorkbookFactory.create(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        sheet = book.getSheet(SheetName);

        int k = 0;
        for (int i = 0; i < sheet.getLastRowNum(); i++) {
            try {
                String locatorColValue = sheet.getRow(i + 1).getCell(k + 1).toString().trim();
                if (!locatorColValue.equalsIgnoreCase("NA")) {
                    locatorName = locatorColValue.split("=")[0].trim();//id
                    locatorValue = locatorColValue.split("=")[1].trim();//email
                }
                String action = sheet.getRow(i + 1).getCell(k + 2).toString().trim();
                String value = sheet.getRow(i + 1).getCell(k + 3).toString().trim();

                switch (action) {
                    case "Open browser":
                        base = new Base();
                        prop = base.init_properties();
                        if (value.isEmpty() || value.equals("NA")) {
                            driver = base.init_driver(prop.getProperty("browser"));
                        } else {
                            driver = base.init_driver(value);
                        }
                        break;
                    case "enter url":
                        if (value.isEmpty() || value.equals("NA")) {
                            driver.get(prop.getProperty("url"));
                            //Thread.sleep(3000);
                        } else {
                            driver.get(value);
                        }
                        break;
                 case "quit":
                       driver.quit();
//                       // Thread.sleep(2000);
                        break;
                    default:
                        break;
                }
                switch (locatorName) {
                    case "id":
                        element = driver.findElement(By.id(locatorValue));
                        System.out.println(By.id(locatorValue));
                        if (action.equalsIgnoreCase("sendKey")) {
                            element.clear();
                            element.sendKeys(value);
                        } else if (action.equalsIgnoreCase("click")) {
                            element.click();
                        }
                        locatorName = null;
                        break;
                    case "name":
                        element = driver.findElement(By.name(locatorValue));

                        if (action.equalsIgnoreCase("sendKey")) {
                            element.clear();
                            element.sendKeys(value);
                        }else if (action.equalsIgnoreCase("click")) {
                            element.click();
                            Thread.sleep(10000);
                        }
                        locatorName = null;
                        //default:
                        break;

                    case "xpath":
                        element = driver.findElement(By.linkText(locatorValue));
                        if (action.equalsIgnoreCase("sendKey")) {
                            element.clear();
                            element.sendKeys(value);
                        }else if (action.equalsIgnoreCase("click")) {
                            element.click();
                        }
                        locatorName = null;
                        //default:
                        break;

                    case "linkText":
                        element = driver.findElement(By.linkText(locatorValue));

                        if (action.equalsIgnoreCase("sendKey")) {
                            element.clear();
                            element.sendKeys(value);
                        }else if (action.equalsIgnoreCase("click")) {
                            element.click();
                        }
                        locatorName = null;
                        //default:
                        break;

                    default:
                        break;

                }

            } catch (Exception e) {

            }

        }
driver.quit();
    }

}
