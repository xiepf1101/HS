package com.demo.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 获取时间段
 * @author LvDapeng
 * @date 2015年9月9日 下午1:39:17
 */
public class DateUtils_ {
	
	private static final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	
	
	private static final SimpleDateFormat formatter_from = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static final SimpleDateFormat formatter_to = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
	private static final SimpleDateFormat river_formatter_from = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

	public static String getDateString(Date date){
		return formatter.format(date);
	}
	
	/**
	 * 当前时间
	 * @return
	 */
	public static String getCurrentTime(){
		Calendar cal = Calendar.getInstance(); 
		String time=formatter.format(cal.getTime());
		return time;
	}
	
	/**
	 * 前几天
	 * 		-1 前1天
	 * 		-7 前一周
	 * 		-30 前一个月 
	 * @param day
	 * @return
	 */
	public static String getBeforeDay(Integer day){
		Calendar c = Calendar.getInstance(); 
		c.add(Calendar.DATE, day);
		String mDateTime=formatter_from.format(c.getTime());
		
		return mDateTime;
	}
	
	/**
	 * 前几天
	 * 		-1 前1天
	 * 		-7 前一周
	 * 		-30 前一个月 
	 * @param day
	 * @return
	 */
	public static String getBeforeGivenDay(Integer day, String givenTime){
		DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar c = Calendar.getInstance(); 
		try {
			Date myDate1 = dateFormat1.parse(givenTime);
			c.setTime(myDate1);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		c.add(Calendar.DATE, day);
		String mDateTime=formatter.format(c.getTime());
		
		return mDateTime;
	}
	
	/**
	 * 全景舆情中开始时间和结束时间的转换,要求指定时间格式为  yyyy-MM-dd HH:mm:ss
	 * 将 yyyy-MM-dd HH:mm:ss 格式时间
	 * 转 yyyy-MM-dd'T'HH:mm:ss.SSS'Z' 格式
	 * @param day
	 * @return
	 */
	public static String getFromOrToConvertor(String dateStr){
		
		try {
			Date d=formatter_from.parse(dateStr);
			return formatter_to.format(d);
		} catch (ParseException e) {
			e.printStackTrace();
			return "";
		}
	}
	
	/**
	 * 全景舆情中开始时间和结束时间的转换,要求指定时间格式为  yyyy-MM-dd HH:mm:ss.SSS
	 * 将 yyyy-MM-dd HH:mm:ss.SSS 格式时间
	 * 转 yyyy-MM-dd'T'HH:mm:ss.SSS'Z' 格式
	 * @param day
	 * @return
	 */
	public static String getFromOrToConvertorRiver(String dateStr){
		
		try {
			Date d=river_formatter_from.parse(dateStr);
			return formatter_to.format(d);
		} catch (ParseException e) {
			e.printStackTrace();
			return "";
		}
	}
	
	/**
	 * 时间比较实用，返回时间的13位毫秒
	 * @param day
	 * @return
	 */
	public static long getFromOrToConvertorTime(String dateStr){
		
		try {
			Date d=formatter_to.parse(dateStr);
			return d.getTime();
		} catch (ParseException e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	public static void main(String[] args) {
		String qw = getBeforeDay(-1);
		System.out.println(qw);
		String time = "2017-06-03 23:00:00";
		String e = getBeforeGivenDay(-1,time);
		System.out.println(e);
		String c = getCurrentTime();
		System.out.println(c);
		String d = getBeforeDay(3);
		System.out.println(d);
		System.out.println(getFromOrToConvertor("2015-09-08 11:22:33"));
	}
	
}
