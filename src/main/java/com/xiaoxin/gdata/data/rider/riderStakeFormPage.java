package com.xiaoxin.gdata.data.rider;

import com.xiaoxin.gdata.service.httpservice.HttpclientService;
import com.xiaoxin.gdata.utils.HostUtils;
import com.xiaoxin.gdata.utils.ParamUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class riderStakeFormPage {
    String path = "src"+ File.separator+"main"+File.separator+"resources"+ File.separator+"config"+ File.separator+"keyv.properties" ;
    private static HttpclientService hs = new HttpclientService();
    private static Logger LOG = LoggerFactory.getLogger(riderStakeFormPage.class) ;
    String host = HostUtils.getURL("rider-qa-app") ;

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

    //请求queryGrabList接口
    public String queryGrabList(){
        Map<String,String> params = ParamUtil.propertiesParamResolves(ParamUtil.getProfile(path),"queryGrabList") ;
        String url = host+params.get("path") ;
        params.remove("path") ;
        String content = hs.postJsonObject(url,riderAppHeader(),params) ;
        LOG.info(content);
        return content;
    }

    public String getImpatInfo(){
        Map<String,String> params = ParamUtil.propertiesParamResolves(ParamUtil.getProfile(path),"getImpatInfo") ;
        String url = host+params.get("path") ;
        params.remove("path") ;
        String content = hs.postJsonObject(url,riderAppHeader(),params) ;
        LOG.info(content);
        return content;
    }

    public String insertLocation(){
        Map<String,String> params = ParamUtil.propertiesParamResolves(ParamUtil.getProfile(path),"insertLocation") ;
        String url = host+params.get("path") ;
        params.remove("path") ;
        String content = hs.postJsonObject(url,riderAppHeader(),params) ;
        LOG.info(content);
        return content;
    }

    public String checkNewOrder(){
        Map<String,String> params = ParamUtil.propertiesParamResolves(ParamUtil.getProfile(path),"checkNewOrder") ;
        String url = host+params.get("path") ;
        params.remove("path") ;
        String content = hs.postJsonObject(url,riderAppHeader(),params) ;
        LOG.info(content);
        return content;
    }

    public String initRiderCode(){
        Map<String,String> params = ParamUtil.propertiesParamResolves(ParamUtil.getProfile(path),"initRiderCode") ;
        String url = host+params.get("path") ;
        params.remove("path") ;
        String content = hs.postJsonObject(url,riderAppHeader(),params) ;
        LOG.info(content);
        return content;
    }

    public String sysMobile(){
        Map<String,String> params = ParamUtil.propertiesParamResolves(ParamUtil.getProfile(path),"sysMobile") ;
        String url = host+params.get("path") ;
        params.remove("path") ;
        String content = hs.postJsonObject(url,riderAppHeader(),params) ;
        LOG.info(content);
        return content;
    }

    public String updateRiderStatus(){
        Map<String,String> params = ParamUtil.propertiesParamResolves(ParamUtil.getProfile(path),"updateRiderStatus") ;
        String url = host+params.get("path") ;
        params.remove("path") ;
        String content = hs.postJsonObject(url,riderAppHeader(),params) ;
        LOG.info(content);
        return content;
    }

    public String queryWaitOrder(){
        Map<String,String> params = ParamUtil.propertiesParamResolves(ParamUtil.getProfile(path),"queryWaitOrder") ;
        String url = host+params.get("path") ;
        params.remove("path") ;
        String content = hs.postJsonObject(url,riderAppHeader(),params) ;
        LOG.info(content);
        return content;
    }

    public String selectOrderCountWqc(){
        Map<String,String> params = ParamUtil.propertiesParamResolves(ParamUtil.getProfile(path),"selectOrderCountWqc") ;
        String url = host+params.get("path") ;
        params.remove("path") ;
        String content = hs.postJsonObject(url,riderAppHeader(),params) ;
        LOG.info(content);
        return content;
    }

    public String queryPszOrder(){
        Map<String,String> params = ParamUtil.propertiesParamResolves(ParamUtil.getProfile(path),"queryPszOrder") ;
        String url = host+params.get("path") ;
        params.remove("path") ;
        String content = hs.postJsonObject(url,riderAppHeader(),params) ;
        LOG.info(content);
        return content;
    }

    public String selectOrderCountWps(){
        Map<String,String> params = ParamUtil.propertiesParamResolves(ParamUtil.getProfile(path),"selectOrderCountWps") ;
        String url = host+params.get("path") ;
        params.remove("path") ;
        String content = hs.postJsonObject(url,riderAppHeader(),params) ;
        LOG.info(content);
        return content;
    }

    public String getSumMoney(){
        Map<String,String> params = ParamUtil.propertiesParamResolves(ParamUtil.getProfile(path),"getSumMoney") ;
        String url = host+params.get("path") ;
        params.remove("path") ;
        String content = hs.postJsonObject(url,riderAppHeader(),params) ;
        LOG.info(content);
        return content;
    }
}
