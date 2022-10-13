package com.ccristaldo.demoguru99v1.tests;

import com.ccristaldo.demoguru99v1.pages.HomePage;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

@Slf4j
public class BaseTest {

    public WebDriver driver;

    public HomePage homePage;

    public WebDriver getDriver(){
        return driver;
    }

    @BeforeClass
    public void classLevelSetup(){

        //src/test/resources/geckodriver
       // System.setProperty("webdriver.gecko.driver", "/Users/carloscristaldo/bin/geckodriver");
        log.info("Test are starting ... ");
        driver = new FirefoxDriver();

    }

    @BeforeMethod
    public void methodLevelSetup() {

        homePage = new HomePage(driver);
    }

    @AfterClass
    public void teardown() {
        log.info("Tests are ending!");
        driver.quit();
    }
}
