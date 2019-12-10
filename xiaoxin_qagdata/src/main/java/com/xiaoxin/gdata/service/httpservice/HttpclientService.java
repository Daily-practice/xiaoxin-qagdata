package com.xiaoxin.gdata.service.httpservice;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Consts;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.config.SocketConfig;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;

public class HttpclientService {
    private static final Logger LOG = LoggerFactory.getLogger(HttpclientService.class);

    protected static final int DEFAULT_MAX_CONNECTION = 50;
    protected static final int DEFAULT_MAX_PER_ROUTE_CONNECTION = 50;

    protected static final int DEFAULT_SOCKET_TIMEOUT = 10000 * 1000;
    protected static final int DEFAULT_CONNECTION_TIMEOUT = 2000 * 1000;

    private CloseableHttpClient httpClient;

    public HttpclientService() {
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setMaxTotal(DEFAULT_MAX_CONNECTION);
        connectionManager.setDefaultMaxPerRoute(DEFAULT_MAX_PER_ROUTE_CONNECTION);

        SocketConfig.Builder sb = SocketConfig.custom();
        sb.setSoKeepAlive(true);
        sb.setTcpNoDelay(true);
        connectionManager.setDefaultSocketConfig(sb.build());

        HttpClientBuilder hb = HttpClientBuilder.create();
        hb.setConnectionManager(connectionManager);

        RequestConfig.Builder rb = RequestConfig.custom();
        rb.setSocketTimeout(DEFAULT_SOCKET_TIMEOUT);
        rb.setConnectTimeout(DEFAULT_CONNECTION_TIMEOUT);

        hb.setDefaultRequestConfig(rb.build());

        httpClient = hb.build();
    }

    public String post(String uri, Object arg) {
        RequestBuilder requestBuilder = RequestBuilder.post();
        requestBuilder.setUri(uri);

        //add http headers
        return doHttpRequest(Maps.<String, String>newHashMap(), arg, requestBuilder);
    }

    public String post(String uri, Map<String, String> headers, Object arg) {
        RequestBuilder requestBuilder = RequestBuilder.post();
        requestBuilder.setUri(uri);
        //add http headers
        return doHttpRequest(headers, arg, requestBuilder);
    }

    public String postJsonObject(String uri, Map<String, String> headers, Object arg) {
        RequestBuilder requestBuilder = RequestBuilder.post();
        requestBuilder.setUri(uri);
        //add http headers
        return doHttpRequestJsonObject(headers, arg, requestBuilder);
    }

    private String doHttpRequestJsonObject(Map<String, String> headers, Object arg, RequestBuilder requestBuilder) {
        //add http headers
        for (Map.Entry<String, String> e : headers.entrySet()) {
            requestBuilder.addHeader(e.getKey(), e.getValue());
        }
        //add Entity
        JSONObject jsonObject = (JSONObject) JSON.toJSON(arg);
//        LOG.info("json请求数据："+jsonObject);
        //过滤反编译符号
//        String s1 = StringEscapeUtils.unescapeJava(jsonObject.toString()) ;
//        LOG.info("去除json转义符"+s1);
        if (jsonObject != null) {
            StringEntity stringEntity = new StringEntity(jsonObject.toString(), "UTF-8");
            requestBuilder.setEntity(stringEntity);
        }
        return doExecute(requestBuilder);
    }

    private String doHttpRequest(Map<String, String> headers, Object arg, RequestBuilder requestBuilder) {
        //add http headers
        for (Map.Entry<String, String> e : headers.entrySet()) {
            requestBuilder.addHeader(e.getKey(), e.getValue());
        }

        //add parameters
        JSONObject jsonObject = (JSONObject) JSON.toJSON(arg);
        LOG.info("json请求数据："+jsonObject);
        if (jsonObject != null) {
            for (Map.Entry<String, Object> entry : jsonObject.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();
                if (StringUtils.isNotBlank(key) && value != null && StringUtils.isNotBlank(value.toString())) {
                    requestBuilder.addParameter(key, value.toString());
                }
            }
        }
        return doExecute(requestBuilder);
    }

    private String doExecute(RequestBuilder requestBuilder) {
        try {
            requestBuilder.setCharset(Consts.UTF_8);
            CloseableHttpResponse response = httpClient.execute(requestBuilder.build());
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode >=300) {
                throw new RuntimeException("Invalid http status code:" + statusCode);
            }
            return EntityUtils.toString(response.getEntity(), "UTF-8");
        } catch (IOException e) {
            throw new RuntimeException("http request error!", e);
        }
    }
}