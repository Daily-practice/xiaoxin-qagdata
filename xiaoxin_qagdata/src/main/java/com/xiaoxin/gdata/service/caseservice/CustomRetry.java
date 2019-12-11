package com.xiaoxin.gdata.service.caseservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;


/**
 * 设置testng用例失败重试次数
 */
public class CustomRetry implements IRetryAnalyzer {
    private static final Logger logger = LoggerFactory.getLogger(CustomRetry.class);
    private int initRetryCount = 0;
    private int maxRetryCount = 3; //失败重跑次数

    @Override
    public boolean retry(ITestResult iTestResult) {
        if(initRetryCount < maxRetryCount){
            initRetryCount++;
            logger.warn("================ {}.{} 重试第{}次==============",iTestResult.getTestClass().getName(),iTestResult.getName(),initRetryCount);
            try {
                Thread.sleep(2000);
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
            return true;
        }
        return false;
    }

    // 用于重置retryCnt
    public void reset() {
        initRetryCount = 0;
    }
}
