package com.xiaoxin.gdata.data.test.qa.rider;

import com.xiaoxin.gdata.data.AssembledData;
import com.xiaoxin.gdata.data.header.httpHeaders;
import com.xiaoxin.gdata.utils.HostUtils;
import com.xiaoxin.gdata.utils.ParamUtil;
import com.xiaoxin.gdata.utils.ResponseUtil;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

public class riderStakeFormPageTest {
   public static AssembledData rsp = new AssembledData() ;
   String path = "src"+ File.separator+"main"+File.separator+"resources"+ File.separator+"config"+ File.separator+"keyv.properties" ;
   String host = HostUtils.getURL("rider-qa-app") ;

    @Test
    public void queryGrabList() {
        Assert.assertEquals(ResponseUtil.getOneKeyValue(AssembledData.bulkRequestInterface(ParamUtil.getProfile(path),"queryGrabList",host,httpHeaders.riderAppHeader()),"msg"),"成功");
    }

    @Test
    public void getImpatInfo() {
        Assert.assertEquals(ResponseUtil.getOneKeyValue(AssembledData.bulkRequestInterface(ParamUtil.getProfile(path),"getImpatInfo",host,httpHeaders.riderAppHeader()),"msg"),"成功");
    }

    @Test
    public void insertLocation() {
        Assert.assertEquals(ResponseUtil.getOneKeyValue(AssembledData.bulkRequestInterface(ParamUtil.getProfile(path),"insertLocation",host,httpHeaders.riderAppHeader()),"msg"),"成功");
    }

    @Test
    public void checkNewOrder() {
        Assert.assertEquals(ResponseUtil.getOneKeyValue(AssembledData.bulkRequestInterface(ParamUtil.getProfile(path),"checkNewOrder",host,httpHeaders.riderAppHeader()),"msg"),"成功");
    }

    @Test
    public void initRiderCode() {
        Assert.assertEquals(ResponseUtil.getOneKeyValue(AssembledData.bulkRequestInterface(ParamUtil.getProfile(path),"initRiderCode",host,httpHeaders.riderAppHeader()),"msg"),"成功");
    }

    @Test
    public void sysMobile() {
        Assert.assertEquals(ResponseUtil.getOneKeyValue(AssembledData.bulkRequestInterface(ParamUtil.getProfile(path),"sysMobile",host,httpHeaders.riderAppHeader()),"msg"),"成功");
    }

    @Test
    public void updateRiderStatus() {
        Assert.assertEquals(ResponseUtil.getOneKeyValue(AssembledData.bulkRequestInterface(ParamUtil.getProfile(path),"updateRiderStatus",host,httpHeaders.riderAppHeader()),"msg"),"成功");
    }

    @Test
    public void queryWaitOrder() {
        Assert.assertEquals(ResponseUtil.getOneKeyValue(AssembledData.bulkRequestInterface(ParamUtil.getProfile(path),"queryWaitOrder",host,httpHeaders.riderAppHeader()),"msg"),"成功");
    }

/*    @Test
    public void selectOrderCountWqc() {
        Assert.assertEquals(ResponseUtil.getOneKeyValue(rsp.requestInterface(ParamUtil.getProfile(path),"selectOrderCountWqc",host),"msg"),"成功");
    }*/

    @Test
    public void queryPszOrder() {
        Assert.assertEquals(ResponseUtil.getOneKeyValue(AssembledData.bulkRequestInterface(ParamUtil.getProfile(path),"queryPszOrder",host,httpHeaders.riderAppHeader()),"msg"),"成功");

    }

/*    @Test
    public void selectOrderCountWps() {
        Assert.assertEquals(ResponseUtil.getOneKeyValue(rsfp.selectOrderCountWps(),"msg"),"成功");
    }*/

    @Test
    public void getSumMoney() {
        Assert.assertEquals(ResponseUtil.getOneKeyValue(AssembledData.bulkRequestInterface(ParamUtil.getProfile(path),"queryHistroyOrder",host, httpHeaders.riderAppHeader()),"msg"),"成功");
    }
}