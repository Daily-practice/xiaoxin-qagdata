package com.xiaoxin.gdata.utils;

import com.alibaba.fastjson.JSONObject;
import com.xiaoxin.gdata.data.AssembledData;
import com.xiaoxin.gdata.service.httpservice.HttpclientService;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;


import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.*;
import java.lang.reflect.Method;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by Sam.T on 2016/5/26.
 * 参数工具类，用于处理请求的各种参数
 */
public class ParamUtil {

    public static String host = HostUtils.getURL("rider-qa-apph5") ;
    private static HttpclientService hs = new HttpclientService();


    public static String bean2JsonString(Object bean) {
        JSONObject jsonParam = (JSONObject) JSONObject.toJSON(bean);
        String jsonString = jsonParam.toJSONString();
        return jsonString;
    }

    /**
     * 将Map转为List<NameValuePair>，key为参数key，value为参数
     *
     * @param NameValuePairInfo 参数map
     * @return 返回封装好的请求参数
     */

    public static List<NameValuePair> getNameValuePair(HashMap NameValuePairInfo) {
        List<NameValuePair> listNameValuePair = new ArrayList<NameValuePair>();
        Iterator<Map.Entry> iterator = NameValuePairInfo.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry elem = iterator.next();
            if (elem.getValue().getClass().getName().endsWith("HashSet")) {
                HashSet<String> valueSet = (HashSet<String>) elem.getValue();
                for (String value : valueSet) {
                    listNameValuePair.add(new BasicNameValuePair((String) elem.getKey(), value));
                }
            } else {
                listNameValuePair.add(new BasicNameValuePair((String) elem.getKey(), (String) elem.getValue()));
            }

        }
        return listNameValuePair;
    }

    /**
     * 组合Properties中的参数为List<NameValuePair>
     * 截取Properties的Key最后一位为key，value为请求参数
     * 仅post和put开头的Properties key可用
     *
     * @param apiParameterProperties 配置文件
     * @return 封装好的请求参数
     */
    public static List<NameValuePair> getNameValuePair(Properties apiParameterProperties,String interfaceName) {

        HashMap<String, String> NameValuePairInfo = new HashMap<String, String>();
        propertiesParamResolve(apiParameterProperties, NameValuePairInfo,interfaceName);
        List<NameValuePair> listNameValuePair = new ArrayList<NameValuePair>();
        Iterator<Map.Entry<String, String>> iterator = NameValuePairInfo.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> elem = iterator.next();
            listNameValuePair.add(new BasicNameValuePair(elem.getKey(), elem.getValue()));
        }
        return listNameValuePair;
    }

    /**
     * 处理apiParameterProperties中的键值对
     *
     * @param apiParameterProperties 配置文件
     * @param NameValuePairInfo      等待专为ListNameValuePair的Map
     */
    public static void propertiesParamResolve(Properties apiParameterProperties, HashMap<String, String> NameValuePairInfo, String interfaceName) {
        Set<String> parameterKeySet = apiParameterProperties.stringPropertyNames();
        for (String parameterKey : parameterKeySet) {
            if (parameterKey.startsWith(interfaceName) || parameterKey.startsWith("put")) {
                String key = parameterKey.substring(parameterKey.lastIndexOf(".") + 1, parameterKey.length());
                String property = apiParameterProperties.getProperty(parameterKey);
                NameValuePairInfo.put(key, property);
            }
        }
    }

    public static Map<String,String> propertiesParamResolves(Properties apiParameterProperties, String interfaceName) {
        Set<String> parameterKeySet = apiParameterProperties.stringPropertyNames();
        HashMap<String, String> NameValuePairInfo = new HashMap<String, String>();
        for (String parameterKey : parameterKeySet) {
            if (parameterKey.startsWith(interfaceName)) {
                String key = parameterKey.substring(parameterKey.lastIndexOf(".") + 1, parameterKey.length());
                String property = apiParameterProperties.getProperty(parameterKey);
                NameValuePairInfo.put(key, property);
            }
        }
        return NameValuePairInfo ;
    }

    /**
     * 将Properties中的get参数组合为？+参数=参数值+&的形式
     *
     * @param paramMap 配置文件
     * @return
     */
    public static String getParameter(HashMap<String, String> paramMap) {
        String url = "?";
        Set<String> parameterKeySet = paramMap.keySet();
        for (String parameterKey : parameterKeySet) {
            url = url + parameterKey + "=" + paramMap.get(parameterKey) + "&";
        }
        url = url.substring(0, url.length() - 1);
        return url;
    }

    /**
     * 组合参数中参{}中的参数信息，数仅来自于配置文件，由para开头
     * 参数Key的最后一位与{}中的值对应
     *
     * @paramapiParameterProperties 配置文件
     * @return  替换{}中参数的base api
     */
    public static String replaceParam(Properties apiParameterProperties) {
        String baseApi = apiParameterProperties.getProperty("base.api");
        List<String> parameterList = new ArrayList<>();
        // 判断api中有无需要替换的变量
        Pattern p = Pattern.compile("\\{.*?\\}");
        Matcher m = p.matcher(baseApi);
        while (m.find()) {
            String param = m.group().replaceAll("\\{", "").replaceAll("\\}", "");
            parameterList.add(param);
        }
        Set<String> parameterKeySet = apiParameterProperties.stringPropertyNames();
        for (String param : parameterList) {
            for (String parameterKey : parameterKeySet) {
                if (parameterKey.startsWith("para")) {
                    String parameterShortKey = parameterKey.substring(parameterKey.lastIndexOf(".") + 1, parameterKey.length());
                    if (parameterShortKey.equals(param)) {
                        baseApi = baseApi.replaceAll("\\{" + param + "\\}",
                                apiParameterProperties.getProperty(parameterKey));
                    }
                }
            }
        }
//        baseApi = baseApi + getParameter(apiParameterProperties);
        return baseApi;
    }

    public static String replaceParam(String baseApi, Map<String, String> paramMap) {
        List<String> parameterList = new ArrayList<String>();
        // 判断api中有无需要替换的变量
        Pattern p = Pattern.compile("\\{.*?\\}");
        Matcher m = p.matcher(baseApi);
        while (m.find()) {
            String param = m.group().replaceAll("\\{", "").replaceAll("\\}", "");
            parameterList.add(param);
        }
        Set<String> parameterMapKeySet = paramMap.keySet();
        for (String param : parameterList) {
            for (String parameterKey : parameterMapKeySet) {
                if (parameterKey.equals(param)) {
                    baseApi = baseApi.replaceAll("\\{" + param + "\\}",
                            paramMap.get(param));
                }
            }
        }
        return baseApi;
    }

    /**
     * 组合参数中{}中的参数信息，参数来自于配置文件和Map，
     * 其中配置文件key由para开头
     * 配置文件中参数Key的最后一位和Map中的key与{}中的值对应
     *
     * @param apiParameterProperties 配置文件
     * @param paramMap               参数Map
     * @return 返回替换{}中参数的base api
     */
    public static String replaceParam(Properties apiParameterProperties, Map<String, String> paramMap) {
        String baseApi = replaceParam(apiParameterProperties);
        baseApi = replaceParam(baseApi, paramMap);
        return baseApi;
    }

    public static HashMap<String, String> transBean2Map(Object obj) {

        if(obj == null){
            return null;
        }
        HashMap<String, String> map = new HashMap<String, String>();
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();

                // 过滤class属性
                if (!key.equals("class")) {
                    // 得到property对应的getter方法
                    Method getter = property.getReadMethod();
                    String value = (String) getter.invoke(obj);

                    map.put(key, value);
                }

            }
        } catch (Exception e) {
            System.out.println("transBean2Map Error " + e);
        }

        return map;

    }

    public static Properties getProfile(String path){
        Properties prop = new Properties();
        try {
            InputStream in = new BufferedInputStream(new FileInputStream(path));
//            prop.store(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(path)))), "utf-8");
            prop.load(in);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return prop ;
    }
}
