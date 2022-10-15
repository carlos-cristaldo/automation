package com.ccristaldo.demoguru99v1.tests;

import com.ccristaldo.demoguru99v1.utils.Constants;
import com.ccristaldo.demoguru99v1.utils.excelutilites.ExcelUtils;
import com.ccristaldo.demoguru99v1.utils.listeners.TestListener;
import io.qameta.allure.*;
import org.openqa.selenium.Alert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.IOException;
import java.lang.reflect.Method;

import static com.ccristaldo.demoguru99v1.utils.extentreports.ExtentTestManager.startTest;
import static org.testng.Assert.assertEquals;

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

        assertEquals(driver.getTitle(), title);

    }

    @Test
    public void tddLoginTest(Method method){

        String strAlertMsg = "User or Password is not valid";

        startTest(method.getName(), "Testing login with DATA from Excel Sheet");

        ExcelUtils excelUtils = new ExcelUtils();

        String excelPath = Constants.fileTestData;

        try {
            excelUtils.setExcelFile(excelPath, "loginData");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for (int i = 2; i < excelUtils.getRowCountInSheet(); i++) {

            homePage
                    .openHomePage()
                    .doLoginWithData(excelUtils.getCellData(i,0),excelUtils.getCellData(i,1));

            // Switching to Alert
            Alert alert = driver.switchTo().alert();

            // Capturing alert message.
            String alertMessage= driver.switchTo().alert().getText();

            // Displaying alert message
            System.out.println(alertMessage);
            //Thread.sleep(5000);

            // Accepting alert
            alert.accept();

            assertEquals(alertMessage, strAlertMsg);


        }

    }

    @Test
    public void invalidLoginTest(Method method){

        startTest(method.getName(), "Successful Login Scenario with valid username and password.");

        Alert alert = driver.switchTo().alert();

        homePage
                .openHomePage()
                .doLoginWithData("wrongUser", "wrongPass");

        String alertText = alert.getText();

        System.out.println(alertText);
        //assertEquals("User or Password is not valid".equals(alertText));





    }

}
