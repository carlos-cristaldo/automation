package com.ccristaldo.demoguru99v1.pages;

import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
@Slf4j
public class HomePage extends BasePage{

    public HomePage(WebDriver driver) {
        super(driver);
    }

    private static final String baseUrl = "https://www.demo.guru99.com/V4/";

    private static final String userId = "mngr447125";

    private static final String password = "YgunYrE";

    By userTextBox = By.name("uid");

    By passTextBox = By.name("password");

    By btnLogin = By.name("btnLogin");

    @Step("Open HomePage")
    public HomePage openHomePage(){

        log.info("Opening home page...");
        driver.get(baseUrl);
        return this;

    }

    @Step("Login step using user: "+ userId +", password: " + password)
    public void doLogin(){

        writeText(userTextBox, userId);
        writeText(passTextBox, password);
        click(btnLogin);
        //return new ManagerHomePage(driver);


    }


}
