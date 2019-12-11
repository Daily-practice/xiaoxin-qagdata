package com.xiaoxin.gdata.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class HostUtils {
    private static final String FILE_SEPAPATOR = System.getProperty("file.separator");


    public static String getURL(String key)   {
        Properties prop = new Properties();
        try {
            InputStream in = new BufferedInputStream(new FileInputStream("src"+ File.separator+"main"+File.separator+"resources"+ File.separator+"config"+ File.separator+"hosts.properties"));
            prop.load(in);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return prop.getProperty(key) ;
    }
}
