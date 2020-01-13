package com.xiaoxin.gdata.data.header;

import com.xiaoxin.gdata.data.rider.xiaoxinParameGetsDatabase;

import java.util.HashMap;
import java.util.Map;

public class httpHeaders {
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

    public  static Map<String,String> appletHeader(){
        Map<String,String> header = new HashMap<>() ;
        header.put("token","2d912c676ee24e6faf55fcb5aa6f864a") ;
        header.put("Content-Type","application/json") ;
        header.put("charset","utf-8") ;
        header.put("User-Agent","Mozilla/5.0 (Linux; Android 8.1.0; Redmi 6 Pro Build/OPM1.171019.019; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/62.0.3202.84 Mobile Safari/537.36 MicroMessenger/7.0.7.1521(0x27000735) Process/appbrand2 NetType/WIFI Language/zh_CN") ;
        header.put("referer","https://servicewechat.com/wxf879e9bd75c02dc5/0/page-frame.html") ;

        return header;
    }
}
