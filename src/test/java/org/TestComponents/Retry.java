package org.TestComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {

    int count = 0;
    int maxCnt = 1;


    @Override
    public boolean retry(ITestResult iTestResult) {
        if(count<maxCnt){
            count++;
            return true;
        }
        return false;
    }
}
