package com.jzfq.rms.product.utils;

import com.jzfq.rms.product.exception.BusinessException;

import java.io.*;

/**
 * 对象序列化工具类
 *
 * @author 大连桔子分期科技有限公司
 * @date 2018/1/31 15:11
 */
public class ObjectSerialiable {

    /**
     * 对象序列化为字符串
     */
    public static String objectSerialiable(Object obj) {
        String serStr = null;
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(obj);
            serStr = byteArrayOutputStream.toString("ISO-8859-1");
            serStr = java.net.URLEncoder.encode(serStr, "UTF-8");

            objectOutputStream.close();
            byteArrayOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return serStr;
    }

    /**
     * 字符串反序列化为对象
     * @param serStr
     * @return
     */
    public static Object objectDeserialization(String serStr){
        Object newObj = null;
        try {
            String redStr = java.net.URLDecoder.decode(serStr, "UTF-8");
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(redStr.getBytes("ISO-8859-1"));
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
            newObj = objectInputStream.readObject();
            objectInputStream.close();
            byteArrayInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return newObj;
    }
}
