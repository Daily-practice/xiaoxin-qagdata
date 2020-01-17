package com.xiaoxin.gdata.data.test.qa.rider;

import com.xiaoxin.gdata.data.AssembledData;
import com.xiaoxin.gdata.data.header.httpHeaders;
import com.xiaoxin.gdata.utils.HostUtils;
import com.xiaoxin.gdata.utils.ParamUtil;
import org.testng.annotations.Test;

import java.io.File;

public class riderStakeFormPageTest {
   String path = "src"+ File.separator+"main"+File.separator+"resources"+ File.separator+"config"+ File.separator+"keyv.properties" ;
   String host = HostUtils.getURL("rider-qa-app") ;

    @Test
    public void queryGrabList() {
        AssembledData.bulkRequestInterface(ParamUtil.getProfile(path),"queryGrabList",host,httpHeaders.riderAppHeader());
    }

    @Test
    public void getImpatInfo() {
        AssembledData.bulkRequestInterface(ParamUtil.getProfile(path),"getImpatInfo",host,httpHeaders.riderAppHeader());
    }

    @Test
    public void insertLocation() {
        AssembledData.bulkRequestInterface(ParamUtil.getProfile(path),"insertLocation",host,httpHeaders.riderAppHeader());
    }

    @Test
    public void checkNewOrder() {
        AssembledData.bulkRequestInterface(ParamUtil.getProfile(path),"checkNewOrder",host,httpHeaders.riderAppHeader());
    }

    @Test
    public void initRiderCode() {
        AssembledData.bulkRequestInterface(ParamUtil.getProfile(path),"initRiderCode",host,httpHeaders.riderAppHeader());
    }

    @Test
    public void sysMobile() {
        AssembledData.bulkRequestInterface(ParamUtil.getProfile(path),"sysMobile",host,httpHeaders.riderAppHeader());
    }

    @Test
    public void updateRiderStatus() {
        AssembledData.bulkRequestInterface(ParamUtil.getProfile(path),"updateRiderStatus",host,httpHeaders.riderAppHeader());
    }

    @Test
    public void queryWaitOrder() {
        AssembledData.bulkRequestInterface(ParamUtil.getProfile(path),"queryWaitOrder",host,httpHeaders.riderAppHeader());
    }

/*    @Test
    public void selectOrderCountWqc() {
        Assert.assertEquals(ResponseUtil.getOneKeyValue(rsp.requestInterface(ParamUtil.getProfile(path),"selectOrderCountWqc",host),"msg"),"成功");
    }*/

    @Test
    public void queryPszOrder() {
        AssembledData.bulkRequestInterface(ParamUtil.getProfile(path),"queryPszOrder",host,httpHeaders.riderAppHeader());

    }

/*    @Test
    public void selectOrderCountWps() {
        Assert.assertEquals(ResponseUtil.getOneKeyValue(rsfp.selectOrderCountWps(),"msg"),"成功");
    }*/

    @Test
    public void getSumMoney() {
        AssembledData.bulkRequestInterface(ParamUtil.getProfile(path),"queryHistroyOrder",host, httpHeaders.riderAppHeader());
    }
}