/*
 * 文件名称: RetDateFm.java
 * 版权信息: Copyright 2001-2012 ZheJiang Collaboration Data System Co., LTD. All right reserved.
 * ----------------------------------------------------------------------------------------------
 * 修改历史:
 * ----------------------------------------------------------------------------------------------
 * 修改原因: 新增
 * 修改人员: huangwb
 * 修改日期: 2012-1-16
 * 修改内容: 
 */
package com.hzecool.codegen.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

/**
 * 日期格式化辅助类
 * @author <a href="mailto:huangwb@zjcds.com">huangwb</a> created on 2012-1-16
 * @since DE6.0
 */
public class DateFormatUtils {
    
    /** 默认日期格式掩码 "yyyy-MM-dd HH:mm:ss" */
    public static String DATE_FORMAT_PATTERN = "yyyy-MM-dd HH:mm:ss";
	public static final String YYYY_MM_DD = "yyyy-MM-dd";

    /**
     * 以默认格式掩码"yyyy-MM-dd HH:mm:ss"格式化日期
     * 
     * @param date 日期
     * @return 日期字符串
     * @author LuoJingtian created on 2012-2-29
     * @since CDS Framework 1.0
     */
    public static String format(Date date) {
        return format(date, DATE_FORMAT_PATTERN);
    }
    
    /**
     * 以指定格式掩码格式化日期
     * @param date 日期
     * @param pattern 日期掩码
     * @return 日期字符串
     * @author LuoJingtian created on 2012-2-29 
     * @since CDS Framework 1.0
     */
    public static String format(Date date, String pattern) {
        if(null == date) {
            return null;
        }
        return new SimpleDateFormat(pattern).format(date);
    }
    
    /**
     * 判断两个日期是否是同一天
     * @param date1
     * @param date2
     * @return
     */
    public static boolean isSameDay(Date date1, Date date2) {
    	String str1 = format(date1, YYYY_MM_DD);
    	String str2 = format(date2, YYYY_MM_DD);
    	if (str1.equals(str2))
    		return true;
    	else
    		return false;
    }
    
    /**
     * 以默认格式掩码"yyyy-MM-dd HH:mm:ss"解析日期
     * @param date 日期字符串
     * @return 日期
     * @author LuoJingtian created on 2012-2-29 
     * @since CDS Framework 1.0
     */
    public static Date parse(String date) {
        return parse(date, DATE_FORMAT_PATTERN);
    }
    
    
    /**
     * 以指定日期格式掩码解析日期
     * @param date 日期字符串
     * @param pattern 日期格式掩码
     * @return 日期
     * @author LuoJingtian created on 2012-2-29 
     * @since CDS Framework 1.0
     */
    public static Date parse(String date, String pattern) {
        if (StringUtils.isEmpty(date)) {
            return null;
        }
        Date dt = null;
        try {
            dt = new SimpleDateFormat(pattern).parse(date);
        }
        catch (Exception e) {
        }
        return dt;
    }
}
