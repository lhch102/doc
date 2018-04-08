package com.jzfq.rms.account.service;

import com.jzfq.rms.account.bean.AccountSystem;

import java.util.List;
import java.util.Map;

/**
 * @author 大连桔子分期科技有限公司
 * @date 2018/4/4 15:33
 */
public interface SystemService {

    /**
     * 获取所属系统
     * @return
     */
    List<Map<String,String>> getSystemList();
}
