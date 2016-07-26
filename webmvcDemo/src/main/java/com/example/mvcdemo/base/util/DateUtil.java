package com.example.mvcdemo.base.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * 日期工具类
 * 
 * @author 孙宇
 * 
 */
public class DateUtil {

	/**
	 * 将Date类型转换为字符串
	 * 
	 * @param date
	 *            日期类型
	 * @return 日期字符串
	 */
	public static String format(Date date) {
		return format(date, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 将Date类型转换为字符串
	 * 
	 * @param date
	 *            日期类型
	 * @param pattern
	 *            字符串格式
	 * @return 日期字符串
	 */
	public static String format(Date date, String pattern) {
		return format(date,pattern,null);
	}
	
	/**
	 * 将Date类型转换为字符串
	 * @param date 日期
	 * @param pattern 格式
	 * @param local 地区
	 * @return 格式化日期字符串
	 * @author ysc
	 */
	public static String format(Date date,String pattern, Locale local){
		if(date == null){
			return null;
		}
		if(pattern == null || "".equals(pattern) || pattern.length()==0){
			pattern = "yyyy-MM-dd HH:mm:ss";
		}
		SimpleDateFormat sdf =null;
		if(local == null){
			sdf = new SimpleDateFormat(pattern);
		}else{
			sdf = new SimpleDateFormat(pattern,local);
		}
		
		return sdf.format(date); 
	}

	/**
	 * 将字符串转换为Date类型
	 * 
	 * @param date
	 *            字符串类型
	 * @return 日期类型
	 */
	public static Date format(String date) {
		return format(date, null);
	}

	/**
	 * 将字符串转换为Date类型
	 * 
	 * @param date
	 *            字符串类型
	 * @param pattern
	 *            格式
	 * @return 日期类型
	 */
	public static Date format(String date, String pattern) {
		if (pattern == null || pattern.equals("") || pattern.equals("null")) {
			pattern = "yyyy-MM-dd HH:mm:ss";
		}
		if (date == null || date.equals("") || date.equals("null")) {
			return new Date();
		}
		Date d = null;
		try {
			d = new java.text.SimpleDateFormat(pattern).parse(date);
		} catch (ParseException pe) {
		}
		return d;
	}
	
	/**
	 * 两个日期比较
	 * @param dt1
	 * @param dt2
	 * @return 1 大于  -1 小于  0 等于
	 * @author zwg
	 */
	public static int compareDate(Date dt1, Date dt2) {
        try {
            if (dt1.getTime() > dt2.getTime()) {
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {
                return -1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
	}
	
	/**
	 * 当前时间是否处于两个时间之间
	 * @param dt1
	 * @param dt2
	 * @return 
	 */
	public static boolean betweenDate(Date dt1, Date dt2){
		boolean f = false;
		Date currDate = new Date();
		if(dt1 == null && dt2 == null){
			return true;
		}
		if(dt1 == null && currDate.getTime()<=dt2.getTime()){
			return true;
		}
		if(dt1.getTime() <= currDate.getTime() && dt2 == null){
			return true;
		}
		if (dt1.getTime() <= currDate.getTime() &&  currDate.getTime()<=dt2.getTime()) {
			f = true;
		}
		return f;
	}

}
