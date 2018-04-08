package com.jzfq.rms.account.service.impl;

import com.jzfq.rms.account.bean.AccountSystem;
import com.jzfq.rms.account.dao.AccountSystemMapper;
import com.jzfq.rms.account.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author 大连桔子分期科技有限公司
 * @date 2018/4/4 15:35
 */
@Service
public class SystemServiceImpl implements SystemService {


    @Autowired
    private AccountSystemMapper systemMapper;
    /**
     * 获取所属系统
     *
     * @return
     */
    @Override
    public List<Map<String,String>> getSystemList() {
        return systemMapper.getSystemList();
    }
}
