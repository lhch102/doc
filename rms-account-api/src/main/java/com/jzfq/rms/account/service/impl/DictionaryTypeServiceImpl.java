package com.jzfq.rms.account.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.jzfq.rms.account.bean.AccountDic;
import com.jzfq.rms.account.bean.AccountDicType;
import com.jzfq.rms.account.common.PageData;
import com.jzfq.rms.account.common.PageParam;
import com.jzfq.rms.account.dao.AccountDicTypeMapper;
import com.jzfq.rms.account.service.DictionaryTypeService;
import com.jzfq.rms.account.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 大连桔子分期科技有限公司
 * @date 2018/4/3 11:46
 */
@Service("dictionaryTypeService")
public class DictionaryTypeServiceImpl implements DictionaryTypeService {
    private final static Logger logger = LoggerFactory.getLogger(DictionaryTypeServiceImpl.class);


    @Autowired
    AccountDicTypeMapper accountDicTypeMapper;

    /**
     * 校验字典名称是否存在
     *
     * @param typeName,id
     * @return
     */

    @Override
    public boolean checkTypeName(String typeName, String id) {
        logger.info("开始连接数据库校验该字典名称是否存在，参数信息：id：" + id + ",typeName:" + typeName);
        int count = accountDicTypeMapper.checkTypeName(typeName, id);
        if (count > 0) {
            logger.info("该字典名称数据已存在！typeName：" + typeName);
            return true;
        }
        logger.info("该字典名称数据可以使用！typeName：" + typeName);
        return false;
    }

    /**
     * 根据字典id查询字典信息
     *
     * @param ,id
     * @return
     */

    @Override
    public AccountDicType queryModel(String id) {
        logger.info("开始连接数据库根据字典id查询字典数据，参数信息：id：" + id);
        AccountDicType dic = accountDicTypeMapper.selectByPrimaryKey(Long.valueOf(id));
        if (null != dic) {
            logger.info("根据字典id查询字典数据成功，返回数据信息："+ JSONObject.toJSONString(dic));
            if (dic.getUpateDate() != null) {
                dic.setUpdateDateStr(DateUtils.date2str(dic.getUpateDate(),DateUtils.DATE_FORMAT_LONG));
            }
            return dic;
        }
        logger.info("根据字典id查询字典数据失败，该字典信息不存在！");
        return null;
    }

    /**
     * 分页查询字典数据
     * @param dic
     * @return
     */

    @Override
    public PageData<AccountDicType> list(AccountDicType dic) {
        PageParam<AccountDicType> page = new PageParam<AccountDicType>(dic, dic.getPageNum(), dic.getNumPerPage());
        logger.info("开始连接数据库获取字典数据，参数信息：" + JSONObject.toJSONString(dic));
        List<AccountDicType> pageData = accountDicTypeMapper.findDictions(page);
        if (null != pageData && pageData.size() > 0) {
            logger.info("连接数据库获取字典数据成功，返回数据：" + JSONObject.toJSONString(pageData));
            for (AccountDicType dicType : pageData) {
                if (null != dicType.getUpateDate()) {
                    dicType.setUpdateDateStr(DateUtils.date2str(dicType.getUpateDate(),DateUtils.DATE_FORMAT_LONG));
                }
            }
            return new PageData(page.getPageNo(), page.getPageSize(), page.getDataTotal(), pageData);
        }
        logger.info("连接数据库获取字典数据成功，返回数据为空");
        return null;
    }
}
