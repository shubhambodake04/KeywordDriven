package com.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class Base {
    public WebDriver driver;
    public Properties prop;
    public static long PAGE_LOAD_TIMEOUT = 20;
    public static long IMPLICIT_WAIT = 10;

    public WebDriver init_driver(String browserName) throws Exception {
        if (browserName.equals("chrome"))
        {
            System.setProperty("webdriver.chrome.driver","chromedriver.exe");
            driver=new ChromeDriver();
            driver.manage().window().maximize();
            driver.manage().timeouts().pageLoadTimeout(PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
            driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT,TimeUnit.SECONDS);
        }
        else
        {
            throw new Exception("Browser is not correct");
        }
        Thread.sleep(5000);
        return driver;
    }

    public Properties init_properties()
    {
        prop=new Properties();
        try {
            FileInputStream ip=new FileInputStream("C:\\Users\\shubh\\IdeaProjects\\KeywordDeiven\\src\\main\\java\\com\\config\\config.properties");
            prop.load(ip);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop;
    }

}

