package org.TestComponents;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.Resources.GlobalData.ExtentReporterNG;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

public class Listners extends BaseTest implements ITestListener {
    ExtentTest test;
    ExtentReports extent = ExtentReporterNG.getReportObject();
    //Making Thread safe making sure each test has its own thread so they don't interrupt
    ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();

    @Override
    public void onTestStart(ITestResult iTestResult) {
       test = extent.createTest(iTestResult.getMethod().getMethodName());
       extentTest.set(test); // for this test it will assign a new thread id
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        extentTest.get().log(Status.PASS, "Test is passed");
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        extentTest.get().fail(iTestResult.getThrowable());

        try {
            driver = (WebDriver)iTestResult.getTestClass().getRealClass().getField("driver").get(iTestResult.getInstance());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        String filePath = null;
       try{
           filePath = getScreenshot(iTestResult.getMethod().getMethodName(),driver) ;
       } catch (IOException e) {
           throw new RuntimeException(e);
       }
        extentTest.get().addScreenCaptureFromPath(filePath, iTestResult.getMethod().getMethodName());
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {

    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        extent.flush();
    }
}
