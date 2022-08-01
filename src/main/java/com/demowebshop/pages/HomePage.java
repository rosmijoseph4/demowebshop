package com.demowebshop.pages;

import com.demowebshop.utility.ObjectUtility;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class HomePage extends ObjectUtility {
    public WebDriver driver;
    public HomePage(WebDriver driver){
        this.driver=driver;
    }
    public String getHomePageTitle(){
       String title= page.getPageTitle(driver);
       return title;

    }
    public String getHomePageTitleFromExcel() throws IOException {
        //String title1=excel.readStringData(1,0,"homepage");
        String filepath = System.getProperty("user.dir") + "\\src\\main\\resources\\homepage.xlsx";
        String title=excel.readSingleData(1,0,filepath,"homepage");
        return title;
    }
}
