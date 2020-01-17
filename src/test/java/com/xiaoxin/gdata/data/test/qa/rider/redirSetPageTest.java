package com.xiaoxin.gdata.data.test.qa.rider;

import com.xiaoxin.gdata.data.AssembledData;
import com.xiaoxin.gdata.data.header.httpHeaders;
import com.xiaoxin.gdata.service.caseservice.CustomRetry;
import com.xiaoxin.gdata.utils.HostUtils;
import com.xiaoxin.gdata.utils.ParamUtil;
import org.testng.annotations.Test;

import java.io.File;

public class redirSetPageTest {
    public static String host = HostUtils.getURL("rider-qa-apph5") ;
    public static AssembledData rsp = new AssembledData() ;
    String path = "src"+ File.separator+"main"+File.separator+"resources"+ File.separator+"config"+ File.separator+"keyv.properties" ;


    @Test(retryAnalyzer = CustomRetry.class)
    public void queryHistroyOrder() {
        rsp.bulkRequestInterface(ParamUtil.getProfile(path),"queryHistroyOrder",host, httpHeaders.riderAppHeader());
    }

    @Test
    public void getBills() {
        rsp.bulkRequestInterface(ParamUtil.getProfile(path),"getBills",host, httpHeaders.riderAppHeader());
    }

    @Test
    public void getSch() {
        rsp.bulkRequestInterface(ParamUtil.getProfile(path),"getSch",host, httpHeaders.riderAppHeader());
    }
}