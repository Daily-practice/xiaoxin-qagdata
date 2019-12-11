package com.xiaoxin.gdata.data.test.rider;

import com.xiaoxin.gdata.data.rider.xiaoxinParameGetsDatabase;
import com.xiaoxin.gdata.service.httpservice.HttpclientService;
import com.xiaoxin.gdata.utils.HostUtils;
import com.xiaoxin.gdata.utils.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class xiaoxinRiderDisliveryTest {

    private static Logger LOG = LoggerFactory.getLogger(xiaoxinRiderDisliveryTest.class) ;
    private  static String riderAppHosts = HostUtils.getURL("rider-qa-app") ;
    private static HttpclientService hs = new HttpclientService();
    private static int i = 1 ;


    public static Map<String,String> riderAppHeader() {
        Map<String, String> header = new HashMap<>();
        header.put("token", xiaoxinParameGetsDatabase.getRiderToken("U15801631294"));
        header.put("Content-Type", "application/json; charset=utf-8");
        header.put("charset", "utf-8");
        header.put("User-Agent", "Mozilla/5.0 (Linux; U; Android 8.1.0; Redmi 6 Pro Build/ZQL1715-sakura-build-20180715231305) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/61.0.3163.128 Mobile Safari/537.36 XiaoXin/Rider/1.1.2");
        header.put("uuid", "U15801631294");
        header.put("rider", "R15801631294");
        return header;
    }

    /**
     * 骑手枪弹 更改dcOrderId
     */
    @Test
    public void riderOrder(){
        Map<String, Object> params = new HashMap<>();
        params.put("longitude","116.348744") ;
        params.put("latitude","39.975736") ;
        params.put("orderId",101100005221L) ;

        String url = riderAppHosts+"/rider/order/grabOrder" ;
        String rspParams = hs.postJsonObject(url,riderAppHeader(),params) ;

        if(!ResponseUtil.getOneKeyValue(rspParams,"resCode").equals("200")){
            LOG.error("============抢单不成功，失败原因：{}=========",ResponseUtil.getOneKeyValue(rspParams,"msg"));
        }else {
            LOG.info("================抢单成功：{}=================",rspParams);
        }
    }

    //骑手操作到店
    @Test
    public void riderToStore(){
        long dcOrderId = 101100005221L ;
        List<String> businessIdList = ResponseUtil.getValues(getOrderDetail(dcOrderId),"businessInfoList","businessId") ;

        Map<String, Object> params = new HashMap<>();
        params.put("longitude","116.348744") ;
        params.put("latitude","39.975736") ;
        params.put("dcOrderId",dcOrderId) ;
        for(String businessId : businessIdList){
            params.put("businessId",businessId) ;
            String url = riderAppHosts+"/rider/order/updateYdd/v1" ;
            String rspParams = hs.postJsonObject(url,riderAppHeader(),params) ;
            if(!ResponseUtil.getOneKeyValue(rspParams,"resCode").equals("200")){
                LOG.error("============骑手到店失败businessId:{}，失败原因：{}=========",businessId,ResponseUtil.getOneKeyValue(rspParams,"msg"));
            }else {
                LOG.info("================骑手已到店：{}=================",rspParams);
            }
            i+=1 ;
            LOG.info("配送商家{}.businessId:{}",i,businessId);
            params.remove("businessId") ;
        }
    }

    //骑手送达
    @Test
    public void riderPickUp(){
        long dcOrderId = 101100005221L ;
        List<String> orderList = ResponseUtil.getValues(getOrderDetail(dcOrderId),"detailDtoList","orderId") ;

        Map<String, Object> params = new HashMap<>();
        params.put("longitude","116.348744") ;
        params.put("latitude","39.975736") ;
        params.put("dcOrderId",dcOrderId) ;
        for(String orderId : orderList){
            params.put("orderId",orderId) ;
            String url = riderAppHosts+"/rider/order/updateYqc/v1" ;
            String rspParams = hs.postJsonObject(url,riderAppHeader(),params) ;
            if(!ResponseUtil.getOneKeyValue(rspParams,"resCode").equals("200")){
                LOG.error("============骑手送餐失败orderid={}，失败原因：{}=========",orderId,ResponseUtil.getOneKeyValue(rspParams,"msg"));
            }else {
                LOG.info("================骑手已送达{}:,{}=================",rspParams);
            }
            i+=1 ;
            LOG.info("配送订单{}.orderId:{}",i,orderId);
            params.remove("orderId") ;
        }
    }

    //骑手操作取餐
    @Test
    public void riderDelivery(){
        long dcOrderId = 101100005221L ;
        List<String> orderList = ResponseUtil.getValues(getOrderDetail(dcOrderId),"detailDtoList","orderId") ;
        Map<String, Object> params = new HashMap<>();

        params.put("longitude","116.348744") ;
        params.put("latitude","39.975736") ;
        params.put("dcOrderId",dcOrderId) ;
        for(String orderId : orderList){
            params.put("orderId",orderId) ;
            String url = riderAppHosts+"/rider/order/updateYwc/v1" ;
            String rspParams = hs.postJsonObject(url,riderAppHeader(),params) ;
            if(!ResponseUtil.getOneKeyValue(rspParams,"resCode").equals("200")){
                LOG.error("============骑手操作取餐失败，订单号：{}，失败原因:{}=========",orderId,ResponseUtil.getOneKeyValue(rspParams,"msg"));
            }else {
                LOG.info("================骑手取餐orderid:{}成功：{}=================",orderId,rspParams);
            }
            i+=1 ;
            LOG.info("用户{}已取餐，businessId:{}",i,orderId);
            params.remove("orderId") ;
        }
    }

    //获取订单详情
    public String getOrderDetail(long dcOrderId){
        Map<String, Object> params = new HashMap<>();
        params.put("longitude","116.348744") ;
        params.put("latitude","39.975736") ;

        String url = riderAppHosts+"/rider/order/getOrderDetail?orderId="+dcOrderId+"&longitude=116.348734&latitude=39.97573" ;
        String conneext = hs.postJsonObject(url,riderAppHeader(),params) ;
        if(!ResponseUtil.getOneKeyValue(conneext,"resCode").equals("200")){
            LOG.error("============获取订单详情失败，失败原因：{}=========",ResponseUtil.getOneKeyValue(conneext,"msg"));
        }else {
            LOG.info("================获取订单详情数据成功：{}=================",conneext);
        }
        return conneext ;
    }
}
