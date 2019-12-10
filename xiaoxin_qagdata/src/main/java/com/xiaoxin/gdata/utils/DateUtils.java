/**
 * 
 */
package com.xiaoxin.gdata.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author BaoshanMa <br/>
 * @Date CreateDate:2018年4月27日
 */
public class DateUtils {

	public static String format(Date date) {
		if (date == null) {
			return "";
		}
		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return s.format(date);
	}

}
