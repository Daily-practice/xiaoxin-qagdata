package com.xiaoxin.gdata.data.test.rider;

import com.xiaoxin.gdata.data.rider.riderStakeFormPage;
import com.xiaoxin.gdata.utils.ResponseUtil;
import org.testng.Assert;
import org.testng.annotations.Test;


import static org.junit.Assert.*;

public class riderStakeFormPageTest {
    public static riderStakeFormPage rsfp = new riderStakeFormPage() ;

    @Test
    public void queryGrabList() {
        Assert.assertEquals(ResponseUtil.getOneKeyValue(rsfp.queryGrabList(),"msg"),"成功");
    }

    @Test
    public void getImpatInfo() {
        Assert.assertEquals(ResponseUtil.getOneKeyValue(rsfp.getImpatInfo(),"msg"),"成功");
    }

    @Test
    public void insertLocation() {
        Assert.assertEquals(ResponseUtil.getOneKeyValue(rsfp.insertLocation(),"msg"),"成功");
    }

    @Test
    public void checkNewOrder() {
        Assert.assertEquals(ResponseUtil.getOneKeyValue(rsfp.checkNewOrder(),"msg"),"成功");
    }

    @Test
    public void initRiderCode() {
        Assert.assertEquals(ResponseUtil.getOneKeyValue(rsfp.initRiderCode(),"msg"),"成功");
    }

    @Test
    public void sysMobile() {
        Assert.assertEquals(ResponseUtil.getOneKeyValue(rsfp.sysMobile(),"msg"),"成功");
    }

    @Test
    public void updateRiderStatus() {
        Assert.assertEquals(ResponseUtil.getOneKeyValue(rsfp.updateRiderStatus(),"msg"),"成功");
    }

    @Test
    public void queryWaitOrder() {
        Assert.assertEquals(ResponseUtil.getOneKeyValue(rsfp.queryWaitOrder(),"msg"),"成功");
    }

 /*   @Test
    public void selectOrderCountWqc() {
        Assert.assertEquals(ResponseUtil.getOneKeyValue(rsfp.selectOrderCountWqc(),"msg"),"成功");
    }*/

    @Test
    public void queryPszOrder() {
        Assert.assertEquals(ResponseUtil.getOneKeyValue(rsfp.queryPszOrder(),"msg"),"成功");
    }

/*    @Test
    public void selectOrderCountWps() {
        Assert.assertEquals(ResponseUtil.getOneKeyValue(rsfp.selectOrderCountWps(),"msg"),"成功");
    }*/

    @Test
    public void getSumMoney() {
        Assert.assertEquals(ResponseUtil.getOneKeyValue(rsfp.getSumMoney(),"msg"),"成功");
    }
}