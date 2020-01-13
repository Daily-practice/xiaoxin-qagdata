package com.xiaoxin.gdata.data;

import com.xiaoxin.gdata.service.httpservice.HttpclientService;
import com.xiaoxin.gdata.utils.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class AssembledData {
    private static HttpclientService hs = new HttpclientService();
    private static Logger LOG = LoggerFactory.getLogger(AssembledData.class) ;

    public static String bulkRequestInterface(Properties apiParameterProperties, String interfaceName,String host,Map<String,String> header) {
        int i = 1 ;
        Integer z = Integer.parseInt(apiParameterProperties.getProperty(interfaceName+".post.count")) ;
        String content = null ;
        Set<String> parameterKeySet = apiParameterProperties.stringPropertyNames();
        HashMap<String, String> NameValuePairInfo = new HashMap<String, String>();
        while (i<=z){
            for (String parameterKey : parameterKeySet) {
                if (parameterKey.startsWith(interfaceName) && parameterKey.substring(parameterKey.indexOf(".",parameterKey.indexOf(".")+1)+1,parameterKey.indexOf(".",parameterKey.indexOf(".")+1)+2).equals(String.valueOf(i))) {
                    String key = parameterKey.substring(parameterKey.lastIndexOf(".") + 1, parameterKey.length());
                    String property = apiParameterProperties.getProperty(parameterKey);
                    NameValuePairInfo.put(key, property);
                }
            }
            String url = host+NameValuePairInfo.get("path") ;
            NameValuePairInfo.remove("path") ;
            content = hs.postJsonObject(url, header,NameValuePairInfo) ;
            LOG.info("请求接口{},第{}次,还差{}次,response:{}",url,i,z-i,content);
            if(!ResponseUtil.getOneKeyValue(content,"msg").equals("成功")){
                throw new RuntimeException("请求"+url+"接口失败,已请求"+i+"次") ;
            }
            NameValuePairInfo.clear();
            i++;
        }
        return content ;
    }
}
