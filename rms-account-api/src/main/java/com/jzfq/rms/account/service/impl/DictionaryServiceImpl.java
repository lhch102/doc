package com.jzfq.rms.account.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.jzfq.rms.account.bean.AccountDic;

import com.jzfq.rms.account.common.PageData;
import com.jzfq.rms.account.common.PageParam;
import com.jzfq.rms.account.common.service.GenerateObjectNoService;
import com.jzfq.rms.account.dao.AccountDicMapper;
import com.jzfq.rms.account.enums.EnumEnableFlag;
import com.jzfq.rms.account.enums.ReturnCode;
import com.jzfq.rms.account.service.DictionaryService;
import com.jzfq.rms.account.utils.IdSplitUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * @author 大连桔子分期科技有限公司
 * @date 2018/4/3 11:46
 */
@Service("dictionaryService")
public class DictionaryServiceImpl implements DictionaryService {

    private final static Logger logger = LoggerFactory.getLogger(DictionaryServiceImpl.class);

    @Autowired
    AccountDicMapper accountDicMapper;

    /**
     * 分页查询字典数据
     *
     * @param dic
     * @return
     */
    @Override
    public PageData<AccountDic> list(AccountDic dic) {
        PageParam<AccountDic> page = new PageParam<AccountDic>(dic, dic.getPageNum(), dic.getNumPerPage());
        logger.info("开始连接数据库获取字典数据，参数信息：" + JSONObject.toJSONString(dic));
        List<AccountDic> pageData = accountDicMapper.findDictions(page);
        if (null != pageData && pageData.size() > 0) {
            logger.info("连接数据库获取字典数据成功，返回数据：" + JSONObject.toJSONString(pageData));
            return new PageData(page.getPageNo(), page.getPageSize(), page.getDataTotal(), pageData);
        }
        logger.info("连接数据库获取字典数据成功，返回数据为空");
        return null;
    }

    /**
     * 根据字典类型查询
     *
     * @param type
     * @return
     */
    @Override
    public List<AccountDic> queryDicListByType(String type) {
        logger.info("开始连接数据库获取字典类型数据，参数信息：" + type);
        List<AccountDic> list = accountDicMapper.queryDicListByType(type);
        if (null != list && list.size() > 0) {
            logger.info("连接数据库获取字典类型数据成功，返回数据：" + JSONObject.toJSONString(list));
            return list;
        }
        logger.info("连接数据库获取字典类型数据失败，返回数据为null");
        return null;
    }

    /**
     * 启用/停用
     *
     * @param type
     * @return
     */

    @Override
    public int isUsing(String type, String dicid) {
        logger.info("开始连接数据库" + EnumEnableFlag.getEnum(type).getMessage() + "字典数据，参数信息：type：" + type + ",dicid:" + dicid);
        int i = accountDicMapper.isUsing(type, IdSplitUtils.splitId(dicid));
        if (i > 0) {
            logger.info(EnumEnableFlag.getEnum(type).getMessage() + "字典数据成功，" + EnumEnableFlag.getEnum(type).getMessage() + "更新数量：" + i);
            return i;
        }
        logger.info(EnumEnableFlag.getEnum(type).getMessage() + "字典数据失败，" + EnumEnableFlag.getEnum(type).getMessage() + "更新数量：0");
        return 0;
    }
}
