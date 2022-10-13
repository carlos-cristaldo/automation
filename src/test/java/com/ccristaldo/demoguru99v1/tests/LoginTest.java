package com.ccristaldo.demoguru99v1.tests;

import com.ccristaldo.demoguru99v1.utils.listeners.TestListener;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static com.ccristaldo.demoguru99v1.utils.extentreports.ExtentTestManager.startTest;

@Listeners({ TestListener.class })
@Epic("Smoke Test")
@Feature("Login")
public class LoginTest extends BaseTest{

    @Test(priority = 0, description = "Successful Login Scenario with valid username and password.")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Test Description: Login test with valid username and password.")
    @Story("Happy Path")
    public void successLoginTest(Method method){

        String title = "Guru99 Bank Manager HomePage";

        startTest(method.getName(), "Successful Login Scenario with valid username and password.");

        homePage
                .openHomePage()
                .doLogin();

        Assert.assertEquals(driver.getTitle(), title);

    }

}
