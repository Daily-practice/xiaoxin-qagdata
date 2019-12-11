package com.xiaoxin.gdata.data.test.rider;

import com.xiaoxin.gdata.data.rider.redirSetPage;
import com.xiaoxin.gdata.service.caseservice.CustomRetry;
import com.xiaoxin.gdata.utils.ResponseUtil;
import org.testng.Assert;
import org.testng.annotations.Test;

public class redirSetPageTest {
    public static redirSetPage rsp = new redirSetPage() ;

    @Test(retryAnalyzer = CustomRetry.class)
    public void queryHistroyOrder() {
        Assert.assertEquals(ResponseUtil.getOneKeyValue(rsp.queryHistroyOrder(),"msg"),"成功1");
    }

    @Test
    public void getBills() {
        Assert.assertEquals(ResponseUtil.getOneKeyValue(rsp.getBills(),"msg"),"成功");
    }

    @Test
    public void getSch() {
        Assert.assertEquals(ResponseUtil.getOneKeyValue(rsp.getSch(),"msg"),"成功");
    }
}