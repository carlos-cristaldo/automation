package com.ccristaldo.demoguru99v1.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {

    public WebDriver driver;

    public WebDriverWait wait;

    public BasePage(WebDriver driver){

        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    }

    public void click(By by){
        waitVisibility(by).click();
    }

    public void writeText(By by, String data){

        waitVisibility(by).sendKeys(data);
    }

    public void readText(By by){

        waitVisibility(by).getText();
    }

    public WebElement waitVisibility(By by) {

        return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

}
