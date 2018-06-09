package com.jzfq.rms.product.utils;

import java.util.Properties;

public class PropertiesUtil {

    private static String filename = "/prod.properties";
    private static Properties pro = new Properties();

    static {
        try {
            pro.load(PropertiesUtil.class.getResourceAsStream(filename));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Load prod.properties “" + filename + "” file error.");
        }
    }

    public static int getInt(String key) {
        int i = 0;
        try {
            i = Integer.parseInt(getString(key));
        } catch (Exception e) {
        }
        return i;
    }

    public static String getString(String key) {
        return pro == null ? null : pro.getProperty(key);
    }

}
