package com.jzfq.rms.account.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
/**
 * @author 大连桔子分期科技有限公司
 * @date 2018年4月04日 20:04:55
 */
public class IdSplitUtils {



    /**
     * 拼接id
     *
     * @param dicid
     * @return
     */
    public static List<String> splitId(String dicid){
        List<String> ids = new ArrayList<String>();
        if (StringUtils.isNotBlank(dicid)) {
            String[] did = dicid.split(",");
            if (null != did) {
                for (String id : did) {
                    ids.add(id);
                }
            }
        }
        return ids;
    }

}
