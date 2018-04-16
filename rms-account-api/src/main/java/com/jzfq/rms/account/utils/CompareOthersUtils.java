package com.jzfq.rms.account.utils;

import java.util.*;

/**
 * @author 大连桔子分期科技有限公司
 * @date 2018年4月04日 20:04:55
 */
public class CompareOthersUtils {


    /**
     * 获取2个数组中相同与不相同的元素
     * @param t1 新的数组
     * @param t2 旧的数组
     * @return
     */
    public static Map<String, Object> compare(Object[] t1, List<String> t2) {
        /**
         * 要移除的元素
         */
        List<String> deleteArry = new ArrayList<String>();
        List<Object> list1 = Arrays.asList(t1);
        for (String t : t2) {//遍历新的数组元素
            if (!list1.contains(t)) {//新的数组中不包含旧的元素（即为要移除的元素）
                deleteArry.add(t);
            }
        }

        /**
         * 要添加的元素
         */
        List<Object> addArry = new ArrayList<Object>();
        for(int j = 0; j < t1.length; j++){//遍历新的数组元素
            if(!t2.contains(t1[j])){//旧的数组中不包含新的数组（即为要添加的元素）
                addArry.add(t1[j]);
            }
        }


        /**
         * 获取2个数组中相同的元素
         */
        Set<String> same = new HashSet<String>(); // 用来存放两个数组中相同的元素
        Set<Object> temp = new HashSet<Object>(); // 用来存放数组a中的元素

        for (int i = 0; i < t1.length; i++) {
            temp.add(t1[i]); // 把数组a中的元素放到Set中，可以去除重复的元素
        }

        for (int j = 0; j < t2.size(); j++) {
            if (!temp.add(t2.get(j))){// 把数组b中的元素添加到temp中
                same.add(t2.get(j));// 如果temp中已存在相同的元素，则temp.add（b[j]）返回false
            }
        }


        Map<String, Object> map=new HashMap<String, Object>();
        map.put("add_arry", addArry);
        map.put("delete_arry", deleteArry);
        map.put("same", same);
        return map;
    }


}
