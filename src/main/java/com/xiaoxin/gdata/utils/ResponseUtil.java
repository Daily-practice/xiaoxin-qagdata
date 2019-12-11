package com.xiaoxin.gdata.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.net.URLDecoder;
import java.util.*;

public class ResponseUtil {
    /**
     * 读取response中的信息返回为String
     * 该方法不关闭Response资源，需要调用后手动关闭已释放链接
     *
     * @param response 请求的返回体
     * @return response中的返回内容
     * @throws IOException
     */
    public static String readResponseWithOutClose(CloseableHttpResponse response) throws IOException {
        HttpEntity entity = response.getEntity();
        String content = "";
        if (entity != null) {
            content = EntityUtils.toString(entity, "UTF-8");
        } else {
        }
        return content;
    }

    /**
     * 读取response中的信息返回为JSONObject
     * 该方法不关闭Response资源，需要调用后手动关闭已释放链接
     *
     * @param response 请求的返回体
     * @return response中的返回内容
     * @throws IOException
     */
    public static JSONObject readResponseWithOutCloseReturnJSON(CloseableHttpResponse response) throws IOException {
        String content = readResponseWithOutClose(response);
        JSONObject jsonContent = JSONObject.parseObject(content);
        return jsonContent;
    }

    /**
     * 读取response中的信息返回为String
     * 调用后关闭Response资源
     *
     * @param response 请求的返回体
     * @return response中的返回内容
     * @throws IOException
     */
    public static String readResponseThenClose(CloseableHttpResponse response) throws IOException {
        String content = readResponseWithOutClose(response);
        response.close();
        return content;
    }

    /**
     * 读取response中的信息返回为JSONObject
     * 调用后关闭Response资源
     *
     * @param response 请求的返回体
     * @return response中的返回内容
     * @throws IOException
     */
    public static JSONObject readResponseThenCloseReturnJSON(CloseableHttpResponse response) throws IOException {
        String content = readResponseThenClose(response);
        JSONObject jsonContent = JSONObject.parseObject(content);
        return jsonContent;
    }

    public static String getValue(String response,String key,int index){
        JSONObject jsonContent = JSONObject.parseObject(response);
        JSONArray array = jsonContent.getJSONArray("data") ;
        if(array.isEmpty()) {
            throw new RuntimeException("Invalid http response data:" +"data值为空");
        }

        String value = array.getJSONObject(index).get(key).toString() ;
        return value ;
    }

    public static String getOneKeyValue(String json,String key){
        JSONObject jsonContent = JSONObject.parseObject(json);
        String value = jsonContent.get(key).toString() ;
        return value ;
    }

    public static String getValue(String json,String key){
        JSONObject jsonContent = JSONObject.parseObject(json);
        String value = jsonContent.getJSONObject("data").get(key).toString() ;
        return value ;
    }
    public static String getValue(String json,String key1,String key2){
        JSONObject jsonContent = JSONObject.parseObject(json);
        String value = jsonContent.getJSONObject("data").getJSONObject(key1).get(key2).toString() ;
        return value ;
    }

    public static List<String> getValues(String json,String key1,String key2){
        JSONObject jsonContent = JSONObject.parseObject(json);
        JSONArray arr = jsonContent.getJSONObject("data").getJSONArray(key1) ;
        List<String> list = new ArrayList<>() ;
        for(int i = 0 ;i<arr.size();i++){
            list.add(arr.getJSONObject(i).get(key2).toString()) ;
        }
        return list ;
    }

    public static List<String> getValues(String json,String key1,String key2,String key3){
        JSONObject jsonContent = JSONObject.parseObject(json);
        JSONArray arr = jsonContent.getJSONObject("data").getJSONObject(key1).getJSONArray(key2) ;
        List<String> list = new ArrayList<>() ;
        for(int i = 0 ;i<arr.size();i++){
            list.add(arr.getJSONObject(i).get(key3).toString()) ;
        }
        return list ;
    }


    public static File loadFile(CloseableHttpResponse response, File file) throws IOException {
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            InputStream is = entity.getContent();
            FileOutputStream fos = new FileOutputStream(file);
            byte[] buffer = new byte[5600];
            int inByte;
            while ((inByte = is.read(buffer)) > 0) {
                fos.write(buffer, 0, inByte);
            }
            is.close();
            fos.close();
            response.close();
        }
        return file;
    }

    public static String getCookie(String cookieName, CloseableHttpResponse response) throws UnsupportedEncodingException {
        HashMap<String, String> cookieMap = new HashMap<String, String>();
        Header headers[] = response.getHeaders("Set-Cookie");
        if (headers == null || headers.length == 0) {
            return null;
        }
        String cookie = "";
        for (int i = 0; i < headers.length; i++) {
            cookie += headers[i].getValue();
            if (i != headers.length - 1) {
                cookie += ";";
            }
        }
        String cookies[] = cookie.split(";");
        for (String c : cookies) {
            c = c.trim();
            if (cookieMap.containsKey(c.split("=")[0])) {
                cookieMap.remove(c.split("=")[0]);
            }
            cookieMap.put(c.split("=")[0],
                    c.split("=").length == 1 ? "" : (c.split("=").length == 2 ? c.split("=")[1] : c.split("=", 2)[1]));
        }
        String cookiesTmp = "";

        Iterator<Map.Entry<String, String>> iter = cookieMap.entrySet().iterator();
        Map.Entry<String, String> entry;
        while (iter.hasNext()) {
            entry = iter.next();
            String key = entry.getKey();
            String value = entry.getValue();
            if (key.equals(cookieName)) {
                cookiesTmp += key + "=" + value + ";";
            }

        }
        cookiesTmp = cookiesTmp.substring(0, cookiesTmp.length() - 1);
        cookiesTmp = cookiesTmp.substring(cookiesTmp.indexOf("=") + 1, cookiesTmp.length());
        cookiesTmp = URLDecoder.decode(cookiesTmp, "utf-8");
        return cookiesTmp;
    }
}
