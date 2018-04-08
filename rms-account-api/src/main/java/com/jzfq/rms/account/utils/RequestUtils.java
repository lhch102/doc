package com.jzfq.rms.account.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Iterator;
import java.util.Map;

/**
 * @author 大连桔子分期科技有限公司
 * @date 2017年9月05日 20:04:55
 */
public class RequestUtils {

    private final static Logger logger = LoggerFactory.getLogger(RequestUtils.class);

    /**
     * 获取远程访问IP地址
     * @return IP
     */
    public static String getRequestIP(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if (ip != null) {
            String[] ipArray = ip.split(",");
            if (ipArray.length > 1) {
                ip = ipArray[0];
            }
        }
        return ip;
    }

    /**
     * 获取请求参数,同名参数只打印第一个
     * @param request request
     * @return 请求参数
     */
    public static Map<String, String> getRequestParameterMap(HttpServletRequest request) {
        Map properties  = request.getParameterMap();
        Iterator it = properties.entrySet().iterator();
        Map<String, String> paramMap = new HashMap<>();
        while(it.hasNext()) {
            Entry entry = (Entry) it.next();
            String key = (String) entry.getKey();
            Object objectValue = entry.getValue();
            String value;
            if (null == objectValue) {
                value = "";
            } else if (objectValue instanceof String[]) {
                String[] values = (String[]) objectValue; //只打印同名参数的第一个值
                value = values[0];
            } else {
                value = objectValue.toString();
            }
            paramMap.put(key, value);
        }
        return paramMap;
    }

    /**
     * 获取所有请求参数,同名参数的值用-拼接
     * @param request request
     * @return 所有请求参数
     */
    public static Map<String, String> getRequestAllParameterMap(HttpServletRequest request) {
        Map properties  = request.getParameterMap();
        Iterator it = properties.entrySet().iterator();
        Map<String, String> paramMap = new HashMap<>();
        while(it.hasNext()) {
            Entry entry = (Entry) it.next();
            String key = (String) entry.getKey();
            Object objectValue = entry.getValue();
            String value = "";
            if (null == objectValue) {
                value = "";
            } else if (objectValue instanceof String[]) {
                String[] values = (String[]) objectValue;
                int valuesLength = values.length;
                for (int i = 0; i < valuesLength; i++) {  //同名参数的值用-拼接
                    if (i == (valuesLength - 1)) {
                        value = value + values[i];
                    } else {
                        value = value + values[i] + "-";
                    }
                }
            } else {
                value = objectValue.toString();
            }
            paramMap.put(key, value);
        }
        return paramMap;
    }

    /**
     * 获取请求数据
     * @param request request
     * @return 请求数据
     */
    public static String getRequestData(HttpServletRequest request) {
        StringBuilder requestData = new StringBuilder();
        requestData.append("requestIP:").append(getRequestIP(request));
        requestData.append("--requestURL:").append(request.getRequestURL());
        requestData.append("--referer:").append(request.getHeader("referer"));
        requestData.append("--allParameterMap:").append(getRequestAllParameterMap(request));
        return requestData.toString();
    }



}