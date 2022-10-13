package com.ccristaldo.demoguru99v1.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ManagerHomePage extends BasePage{

    public ManagerHomePage(WebDriver driver) {
        super(driver);
    }

    By guru99Bank = By.partialLinkText("Guru99 Bank");
    private static final String title = " Guru99 Bank Manager HomePage ";

    public boolean isManagerHomePageOpened(){
        return driver.getTitle().equals(title);
    }
}
