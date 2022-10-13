package com.ccristaldo.demoguru99v1.utils.listeners;

import com.aventstack.extentreports.Status;
import com.ccristaldo.demoguru99v1.tests.BaseTest;
import io.qameta.allure.Attachment;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.Objects;

import static com.ccristaldo.demoguru99v1.utils.extentreports.ExtentManager.getExtentReports;
import static com.ccristaldo.demoguru99v1.utils.extentreports.ExtentTestManager.getTest;

@Slf4j
public class TestListener extends BaseTest implements ITestListener {

    private static String getTestMethodName(ITestResult result){

        return result.getMethod().getConstructorOrMethod().getName();

    }

    @Attachment(value = "Page screenshot", type = "image/png")
    public byte[] saveScreenshotPNG(WebDriver driver) {

        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

    }

    @Attachment(value = "{0}", type = "text/plain")
    public static String saveTextLog(String message) {

        return message;

    }

    @Attachment(value = "{0}", type = "text/html")
    public static String attachHtml(String html) {

        return html;

    }

    @Override
    public void onStart(ITestContext context) {

        log.info("This is onStart method -> " + context.getName());
        context.setAttribute("WebDriver ", this.driver);

    }

    @Override
    public void onFinish(ITestContext context) {

        log.info("This is onFinish method -> " + context.getName());
        getExtentReports().flush();

    }

    @Override
    public void onTestStart(ITestResult result) {

        log.info(getTestMethodName(result) + " -> Test is starting");

    }

    @Override
    public void onTestSuccess(ITestResult result) {

        log.info(getTestMethodName(result) + " -> Test finished succesfully");
        getTest().log(Status.PASS, "Test passed");

    }

    @Override
    public void onTestSkipped(ITestResult result) {

        log.info(getTestMethodName(result) + " -> Test skipped");
        getTest().log(Status.SKIP, "Test Skipped");

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

        log.info(getTestMethodName(result) + " -> Test failed, but acceptance range was reached");

    }

    @Override
    public void onTestFailure(ITestResult result) {

        log.info(getTestMethodName(result) + " -> Test failed");

        //Get driver from BaseTest and assign to local webdriver variable.
        Object testClass = result.getInstance();
        WebDriver driver = ((BaseTest) testClass).getDriver();

        //Allure ScreenShotRobot and SaveTestLog
        if (driver != null) {
            System.out.println("Screenshot captured for test case:" + getTestMethodName(result));
            saveScreenshotPNG(driver);
        }

        //Save a log on allure.
        saveTextLog(getTestMethodName(result) + " failed and screenshot taken!");

        //Take base64Screenshot screenshot for extent reports
        String base64Screenshot =
                "data:image/png;base64," + ((TakesScreenshot) Objects.requireNonNull(driver)).getScreenshotAs(OutputType.BASE64);

        //ExtentReports log and screenshot operations for failed tests.
        getTest().log(Status.FAIL, "Test Failed",
                getTest().addScreenCaptureFromBase64String(base64Screenshot).getModel().getMedia().get(0));

    }
}
