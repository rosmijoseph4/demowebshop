package com.demowebshop.testscripts;

import com.demowebshop.automationcore.Base;
import com.demowebshop.pages.HomePage;
import com.demowebshop.utility.ExcelUtility;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class HomePageTest extends Base {
    HomePage home;
    @Test
    public void verifyHomePageTitle(){
         home=new HomePage(driver);
         String expectedtitle="Demo Web Shop";
         String actualtitle=home.getHomePageTitle();
         System.out.println(actualtitle);
         Assert.assertEquals(actualtitle,expectedtitle,"Error:Invalid homepage title");

    }
    @Test
    public void verifyHomePageTitleFromExcel() throws IOException{
        home=new HomePage(driver);
        String actualtitle=home.getHomePageTitle();
        String expectedtitle=home.getHomePageTitleFromExcel();
        System.out.println(expectedtitle);
        Assert.assertEquals(actualtitle,expectedtitle,"Error:Invalid homepage title");

    }
}
