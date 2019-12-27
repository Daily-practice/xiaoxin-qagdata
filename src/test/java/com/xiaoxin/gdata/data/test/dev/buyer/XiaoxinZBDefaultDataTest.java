package com.xiaoxin.gdata.data.test.dev.buyer;

import com.xiaoxin.gdata.data.customer.XiaoxinZBDefaultData;
import com.xiaoxin.gdata.data.rider.xiaoxinParameGetsDatabase;
import com.xiaoxin.gdata.service.httpservice.HttpclientService;
import com.xiaoxin.gdata.utils.HostUtils;
import com.xiaoxin.gdata.utils.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class XiaoxinZBDefaultDataTest extends XiaoxinZBDefaultData {
    private static Logger LOG = LoggerFactory.getLogger(XiaoxinZBDefaultData.class) ;
    private static HostUtils getHosts = new HostUtils() ;
    private static HttpclientService hs = new HttpclientService() ;
    private static String appletfunHosts = getHosts.getURL("applet-fun-qa") ;
    private static String appletApiHosts = getHosts.getURL("applet-qa-api") ;
    private static ResponseUtil rsp = new ResponseUtil();
    private static xiaoxinParameGetsDatabase getdata = new xiaoxinParameGetsDatabase() ;

    public  static String appletHeader(){
        Map<String,String> header = new HashMap<>() ;
        header.put("token","06edb40f563b456b943f95b8f29aab08") ;
        header.put("Content-Type","application/json") ;
        header.put("charset","utf-8") ;
        header.put("User-Agent","Mozilla/5.0 (Linux; Android 8.1.0; Redmi 6 Pro Build/OPM1.171019.019; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/62.0.3202.84 Mobile Safari/537.36 MicroMessenger/7.0.7.1521(0x27000735) Process/appbrand2 NetType/WIFI Language/zh_CN") ;
        header.put("referer","https://servicewechat.com/wxf879e9bd75c02dc5/0/page-frame.html") ;

        return getTimeSlot(header);
    }

    public static String  getTimeSlot(Map<String,String> header){
        Map<String,String> params = new HashMap<>() ;
        params.put("lat","39.975714") ;
        params.put("lng","116.348679") ;
        params.put("menuId",getdata.getaMenuId(190558504077L)) ;
        params.put("addressId","206") ;
        params.put("menuType","1") ;
        params.put("sellerId" ,"190558504077") ;

        //获取时间片
        String url= appletfunHosts+"calendar/getCalendar" ;
        String content = hs.postJsonObject(url,header,params) ;
        if(!ResponseUtil.getOneKeyValue(content,"resCode").equals("200")){
            LOG.error("============获取时间片失败，失败原因：{}=========",ResponseUtil.getOneKeyValue(content,"msg"));
        }else {
            LOG.info("================获取时间片成功：{}=================",content);
        }
        String bookEndTime = rsp.getValue(content,"bookEndTime",0) ;
        String endTime = rsp.getValue(content,"endTime",0) ;
        LOG.info("bookEndTime:{},endTime:{}",bookEndTime,endTime);
        return defaultPlaceOrderInfo(bookEndTime,endTime,header) ;
    }

    public static String  defaultPlaceOrderInfo(String bookEndTime,String endTime,Map<String,String> header){
        Map<String,Object> params = new HashMap<>() ;
        params.put("bookEndTime",bookEndTime) ;
        params.put("mealTime",endTime) ;
        params.put("addressId",206) ;
        params.put("menuId",getdata.getaMenuId(190558504077L)) ;
        List<Map<String,String>> list1 = new ArrayList<Map<String,String>>() ;
        Map<String,String> map1 = new HashMap<>() ;
        map1.put("id","1902078206162206") ;
        map1.put("count","1") ;
        list1.add(map1) ;
        params.put("productIds",list1) ;
        params.put("payAmount","0") ;
        List<Map<String,String>> list2 = new ArrayList<Map<String,String>>() ;
        Map<String,String> map2 = new HashMap<>() ;
        map2.put("sellerId","190558504077") ;
        map2.put("remark","") ;
        list2.add(map2) ;
        params.put("remarkInfo",list2) ;

        params.put("menuType","1") ;
        params.put("isNewUserArea",false) ;

        List<Map<String,String>> list3 = new ArrayList<Map<String,String>>() ;
        Map<String,String> map3 = new HashMap<>() ;
        map3.put("productId","1902220943488691") ;
        map3.put("code","1001") ;
        map3.put("name","清汤") ;
        map3.put("type","1") ;
        map3.put("seq","1") ;
        list3.add(map3) ;
        params.put("attributeInfo",list3) ;

        params.put("source","fujin") ;

        String url= appletApiHosts+"/order/createOrder/v2" ;
        String content = hs.postJsonObject(url,header,params) ;
        if(!ResponseUtil.getOneKeyValue(content,"resCode").equals("200")){
            LOG.error("============创建订单失败，失败原因：{}=========",ResponseUtil.getOneKeyValue(content,"msg"));
        }else {
            LOG.info("=========创建订单成功========就餐时间:{},orderSn:{},subOrderSn:{}",endTime,rsp.getValue(content,"orderSn"),rsp.getValue(content,"subOrderSn"));
        }
        return content ;
    }
}