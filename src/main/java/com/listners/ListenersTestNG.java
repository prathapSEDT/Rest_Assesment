package com.listners;

import com.utils.GenericMethods;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ListenersTestNG  implements ITestListener {
    public void onStart(ITestContext context) {
        GenericMethods genericMethods=new GenericMethods();
        genericMethods.intiateRequest();
    }

    public void onFinish(ITestContext context) {

    }

    public void onTestStart(ITestResult result) {

    }

    public void onTestSuccess(ITestResult result) {

    }

    public void onTestFailure(ITestResult result) {

    }

    public void onTestSkipped(ITestResult result) {

    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }
}
