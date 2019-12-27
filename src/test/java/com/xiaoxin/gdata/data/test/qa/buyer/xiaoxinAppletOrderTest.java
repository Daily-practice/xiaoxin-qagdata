package com.xiaoxin.gdata.data.test.qa.buyer;

import com.xiaoxin.gdata.data.customer.XiaoxinZBDefaultData;
import org.testng.annotations.Test;

public class xiaoxinAppletOrderTest {
    /**
     * 构造下单数据
     * 要需从XiaoxinZBDefaultData.appletHeader() 更改自己的token
     * params
     */
    @Test
    public void createOrder(){
        XiaoxinZBDefaultData.appletHeader() ;
    }
}
