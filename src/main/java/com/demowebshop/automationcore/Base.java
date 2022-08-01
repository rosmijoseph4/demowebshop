package com.demowebshop.automationcore;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class Base {
    public WebDriver driver;
    public void testInitialize(String browser,String url) {
        if(browser.equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver","C:\\Users\\Rosmi Joseph\\Documents\\Selenium\\chromepath\\chromedriver.exe");
           // WebDriverManager.chromedriver().setup();
            driver=new ChromeDriver();
        } else if(browser.equalsIgnoreCase("edge")) {
            //System.setProperty("webdriver.edge.driver","C:\\Users\\Rosmi Joseph\\Documents\\Selenium\\Edgepath\\msedgedriver.exe");
           WebDriverManager.edgedriver().setup();
            driver=new EdgeDriver();
        }else if(browser.equalsIgnoreCase("firefox")) {
            //System.setProperty("webdriver.gecko.driver","C:\\Users\\Rosmi Joseph\\Documents\\Selenium\\Firefoxpath\\geckodriver.exe");
            WebDriverManager.firefoxdriver().setup();
            driver=new FirefoxDriver();
        }else {
            try {
                throw new Exception("Browser value not defined");
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.get(url);
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
    }

    @BeforeMethod(alwaysRun = true)
   // @Parameters({"browser","url"})
    //public void setUp(String browserName,String baseUrl) {
    public void setUp(){
        //testInitialize(browserName,baseUrl);
        testInitialize("firefox","http://demowebshop.tricentis.com/");
    }
    @AfterMethod

    public void tearDown(ITestResult result) throws IOException {

        if(result.getStatus()==ITestResult.FAILURE) {
            TakesScreenshot scrshot = (TakesScreenshot) driver;
            File screenshot = scrshot.getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenshot,new File("./Screenshots/"+result.getName()+".png"));
        }
        //driver.close();
        driver.quit();
    }
}
