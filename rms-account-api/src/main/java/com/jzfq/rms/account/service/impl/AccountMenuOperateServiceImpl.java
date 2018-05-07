package com.jzfq.rms.account.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.jzfq.rms.account.bean.AccountMenuOperateKey;
import com.jzfq.rms.account.bean.AccountOperate;
import com.jzfq.rms.account.bean.Extended.AccountOperateRelationExtended;
import com.jzfq.rms.account.dao.AccountMenuOperateMapper;
import com.jzfq.rms.account.dao.AccountOperateMapper;
import com.jzfq.rms.account.service.AccountMenuOperateService;
import com.jzfq.rms.account.service.AccountOperateService;
import com.jzfq.rms.account.utils.CompareOthersUtils;
import com.jzfq.rms.account.web.requestModel.AccountMenuRequestModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 大连桔子分期科技有限公司
 * @date 2018/4/8 16:38
 */
@Transactional(readOnly = false)
@Service("accountMenuOperateService")
public class AccountMenuOperateServiceImpl implements AccountMenuOperateService {

    private static final Logger logger = LoggerFactory.getLogger(AccountMenuOperateServiceImpl.class);

    @Autowired
    private AccountMenuOperateMapper accountMenuOperateMapper;
    @Autowired
    AccountOperateService accountOperateService;

    @Autowired
    private AccountOperateMapper accountOperateMapper;

    /**
     * 删除菜单-操作权限
     *
     * @param ids,menuNo
     * @return
     */
    @Transactional(readOnly = false)
    @Override
    public int del(List<String> ids, String menuNo) {
        logger.info("开始连接数据库删除菜单-操作权限关联数据，参数信息：ids：" + ids + ",menuNo:" + menuNo);
        int i = 0;//移除个数
        AccountMenuOperateKey accountMenuOperateKey = new AccountMenuOperateKey();
        if (null != ids && ids.size() > 0) {
            for (String id : ids) {
                i++;
                accountMenuOperateKey.setId(Long.valueOf(id));
                accountMenuOperateKey.setMenuNo(menuNo);
                accountMenuOperateMapper.deleteByPrimaryKey(accountMenuOperateKey);
            }
        }
        if (i > 0) {
            logger.info("删除菜单-操作权限关联数据成功，更新数量：" + i);
            return i;
        }
        logger.info("删除菜单-操作权限关联数据失败，更新数量：0");
        return 0;
    }

    /**
     * 保存操作权限-菜单数据
     *
     * @param accountMenuOperateKey
     * @return
     */
    @Transactional(readOnly = false)
    @Override
    public int save(AccountMenuOperateKey accountMenuOperateKey) {
        logger.info("开始连接数据库保存菜单-操作权限关联数据，参数信息：" + JSONObject.toJSONString(accountMenuOperateKey));
        int count = accountMenuOperateMapper.insert(accountMenuOperateKey);
        if (count > 0) {
            logger.info("保存菜单-操作权限关联数据成功，保存数量：" + count);
            return count;
        }
        logger.info("保存菜单-操作权限关联数据失败，保存数量：0");
        return 0;
    }

    /**
     * 保存   操作关联关系
     *
     * @param accountOperateRelationExtended
     */
    @Override
    @Transactional
    public void save(AccountOperateRelationExtended accountOperateRelationExtended) {

        if (accountOperateRelationExtended != null) {
//            accountOperateMapper.insert();

//            AccountMenuOperateKey accountMenuOperateKey = accountOperateRelationExtended.getAccountMenuOperateKey();
//            AccountOperate accountOperate = accountOperateRelationExtended.getAccountOperate();
//            if (accountMenuOperateKey != null) {
//                accountMenuOperateMapper.insert(accountMenuOperateKey);
//            }
//            if (accountOperate != null) {
//                accountOperateMapper.insert(accountOperate);
//            }
        }
    }

    @Transactional(readOnly = false)
    @Override
    public void deleteByMenuNo(HashMap<String, Object> paramMap) {
        accountMenuOperateMapper.deleteByMenuNo(paramMap);
    }

    @Transactional(readOnly = false)
    @Override
    public int insert(AccountMenuOperateKey record) {
        return accountMenuOperateMapper.insert(record);
    }
}
