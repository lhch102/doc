package com.jzfq.rms.account.utils;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Comparator;

/**
 * 排序工具类
 *
 * @author 大连桔子分期科技有限公司
 * @date 2018/4/13 14:27
 */
public class SortListUtils<T> implements Comparator<T>,Serializable {

    /**
     * 排序字段的get方法名，如字段sort,对应的get方法为getSort(),这里就填getSort.
     */
    private String propertyGetName;
    /**
     * 排序规则--> 降序
     */
    public static final String DESC = "DESC";
    /**
     * 排序规则--> 升序
     */
    public static final String ASC = "ASC";
    /**
     * true 升序</br> flase 降序
     */
    private boolean isAsc;

    @Override
    public int compare(T b1, T b2) {

        Class<?> clz = b1.getClass();
        Method mth = getPropertyMethod(clz, propertyGetName);

        try {
            Object o1 = mth.invoke(b1);
            Object o2 = mth.invoke(b2);

            if (o1 == null || o2 == null)
                return 0;
            Comparable value1 = (Comparable) o1;
            Comparable value2 = (Comparable) o2;

            if (isAsc) {
                return value1.compareTo(value2);
            } else {
                return value2.compareTo(value1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 根据字段的get方法的方法名进行排序
     *
     * @param propertyGetName
     *              排序字段的get方法名，如字段sort,对应的get方法为getSort(),这里就填getSort.
     * @param sortType  排序类型
     *                  SortListUtils.DESC 降序
     *                  SortListUtils.ASC 升序
     */
    public SortListUtils(String propertyGetName, String sortType) {
        this.propertyGetName = propertyGetName;
        if (SortListUtils.ASC.equals(sortType)) {
            this.isAsc = true;
        } else {
            this.isAsc = false;
        }
    }

    /**
     * 获取方法
     *
     * @param clz             </br> 实体对象
     * @param propertyGetName </br> 方法名
     * @return
     */
    public static Method getPropertyMethod(Class clz, String propertyGetName) {
        Method mth = null;
        try {
            mth = clz.getMethod(propertyGetName);
        } catch (Exception e) {
            System.err.println("获取类名发生错误！");
        }
        return mth;
    }


    public String getPropertyGetName() {
        return propertyGetName;
    }

    public void setPropertyGetName(String propertyGetName) {
        this.propertyGetName = propertyGetName;
    }

    public boolean isAsc() {
        return isAsc;
    }

    public void setAsc(boolean isAsc) {
        this.isAsc = isAsc;
    }

}
