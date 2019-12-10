package com.xiaoxin.gdata.data.rider;

import com.xiaoxin.gdata.service.httpservice.HttpclientService;
import com.xiaoxin.gdata.utils.HostUtils;
import com.xiaoxin.gdata.utils.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class xiaoxinQSDefaultData {
    private static Logger LOG = LoggerFactory.getLogger(xiaoxinQSDefaultData.class) ;
    private static HostUtils getHosts = new HostUtils() ;
    private static HttpclientService hs = new HttpclientService();
    private  static String riderAppHosts = getHosts.getURL("rider-qa-app") ;

    public static String riderAppHeader(long dcOrderId) {
        Map<String, String> header = new HashMap<>();
        header.put("token", xiaoxinParameGetsDatabase.getRiderToken("U15801631294"));
        header.put("Content-Type", "application/json; charset=utf-8");
        header.put("charset", "utf-8");
        header.put("User-Agent", "Mozilla/5.0 (Linux; U; Android 8.1.0; Redmi 6 Pro Build/ZQL1715-sakura-build-20180715231305) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/61.0.3163.128 Mobile Safari/537.36 XiaoXin/Rider/1.1.2");
        header.put("uuid", "U15801631294");
        header.put("rider", "R15801631294");

        return riderOrder(header,dcOrderId) ;
    }

    /**
     * 骑手枪弹
     * @param header 请求头参数
     * @param dcOrderId 配送单
     * @return getOrderDetail(header,dcOrderId) 获取订单详情中的数据
     */
    public static String riderOrder(Map<String,String> header,long dcOrderId){
        Map<String, Object> params = new HashMap<>();
        params.put("longitude","116.348744") ;
        params.put("latitude","39.975736") ;
        params.put("orderId",dcOrderId) ;

        String url = riderAppHosts+"/rider/order/grabOrder" ;
        String rspParams = hs.postJsonObject(url,header,params) ;
        if(!ResponseUtil.getOneKeyValue(rspParams,"resCode").equals("200")){
            LOG.error("============抢单不成功，失败原因：{}=========",ResponseUtil.getOneKeyValue(rspParams,"msg"));
        }else {
            LOG.info("================抢单成功：{}=================",rspParams);
        }
        return getOrderDetail(header,dcOrderId) ;
    }

    //获取订单详情
    public static String getOrderDetail(Map<String,String> header,long dcOrderId){
        Map<String, Object> params = new HashMap<>();
        params.put("longitude","116.348744") ;
        params.put("latitude","39.975736") ;

        String url = riderAppHosts+"/rider/order/getOrderDetail?orderId="+dcOrderId+"&longitude=116.348734&latitude=39.97573" ;
        String conneext = hs.postJsonObject(url,header,params) ;
        if(!ResponseUtil.getOneKeyValue(conneext,"resCode").equals("200")){
            LOG.error("============获取订单详情失败，失败原因：{}=========",ResponseUtil.getOneKeyValue(conneext,"msg"));
        }else {
            LOG.info("================获取订单详情数据成功：{}=================",conneext);
        }
        List<String> businessIdList = ResponseUtil.getValues(conneext,"businessInfoList","businessId") ;
        List<String> orderList = ResponseUtil.getValues(conneext,"detailDtoList","orderId") ;
        return riderToStore(header,dcOrderId,businessIdList,orderList) ;
    }

    //骑手到店
    public static String riderToStore(Map<String,String> header,long dcOrderId,List<String> businessIdList,List<String> orderList){
        Map<String, Object> params = new HashMap<>();
        params.put("longitude","116.348744") ;
        params.put("latitude","39.975736") ;
        params.put("dcOrderId",dcOrderId) ;
        for(String businessId : businessIdList){
            params.put("businessId",businessId) ;
            String url = riderAppHosts+"/rider/order/updateYdd/v1" ;
            String rspParams = hs.postJsonObject(url,header,params) ;
            if(!ResponseUtil.getOneKeyValue(rspParams,"resCode").equals("200")){
                LOG.error("============骑手到店失败businessId{}，失败原因：{}=========",businessId,ResponseUtil.getOneKeyValue(rspParams,"msg"));
            }else {
                LOG.info("================骑手已到店：{}=================",rspParams);
            }
            params.remove("businessId") ;
        }
        return riderPickUp(header,dcOrderId,orderList) ;
    }

    //骑手送达
    public static String riderPickUp(Map<String,String> header,long dcOrderId,List<String> orderList){
        Map<String, Object> params = new HashMap<>();
        params.put("longitude","116.348744") ;
        params.put("latitude","39.975736") ;
        params.put("dcOrderId",dcOrderId) ;
        for(String orderId : orderList){
            params.put("orderId",orderId) ;
            String url = riderAppHosts+"/rider/order/updateYqc/v1" ;
            String rspParams = hs.postJsonObject(url,header,params) ;
            if(!ResponseUtil.getOneKeyValue(rspParams,"resCode").equals("200")){
                LOG.error("============骑手送餐失败orderid={}，失败原因：{}=========",orderId,ResponseUtil.getOneKeyValue(rspParams,"msg"));
            }else {
                LOG.info("================骑手已送达orderid={}:,{}=================",rspParams);
            }
            params.remove("orderId") ;
        }
        return riderDelivery(header,dcOrderId,orderList) ;
    }

    //骑手操作取餐
    public static String riderDelivery(Map<String,String> header,long dcOrderId,List<String> orderList){
        Map<String, Object> params = new HashMap<>();
        params.put("longitude","116.348744") ;
        params.put("latitude","39.975736") ;
        params.put("dcOrderId",dcOrderId) ;
        for(String orderId : orderList){
            params.put("orderId",orderId) ;
            String url = riderAppHosts+"/rider/order/updateYwc/v1" ;
            String rspParams = hs.postJsonObject(url,header,params) ;
            if(!ResponseUtil.getOneKeyValue(rspParams,"resCode").equals("200")){
                LOG.error("============骑手操作取餐失败，订单号：{}，失败原因:{}=========",orderId,ResponseUtil.getOneKeyValue(rspParams,"msg"));
            }else {
                LOG.info("================骑手操作取餐成功orderid:{}：{}=================",orderId,rspParams);
            }
            params.remove("orderId") ;
        }
        return null ;
    }
}