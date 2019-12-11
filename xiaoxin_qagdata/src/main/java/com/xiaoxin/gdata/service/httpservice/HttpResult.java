package com.xiaoxin.gdata.service.httpservice;

import com.alibaba.fastjson.JSON;
import com.google.common.base.MoreObjects;
import org.apache.http.NameValuePair;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by MaFei on 16-11-10.
 */
public class HttpResult {
    private String result;
    private List<NameValuePair> arg;


    public String getArg(){
        Map<String, String> map=new HashMap<String, String>();

        for (NameValuePair nameValuePair : arg) {
            map.put(nameValuePair.getName(),nameValuePair.getValue());
        }
        return JSON.toJSON(map).toString();
    }


    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public void setArg(List<NameValuePair> arg) {
        this.arg = arg;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("result", result)
                .add("arg", arg)
                .toString();
    }
}


