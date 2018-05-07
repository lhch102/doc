package com.jzfq.rms.account.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.jzfq.rms.account.bean.AccountDic;
import com.jzfq.rms.account.bean.AccountDicType;
import com.jzfq.rms.account.bean.AccountUser;
import com.jzfq.rms.account.common.LoginCommon;
import com.jzfq.rms.account.common.PageData;
import com.jzfq.rms.account.common.PageParam;
import com.jzfq.rms.account.dao.AccountDicTypeMapper;
import com.jzfq.rms.account.enums.EnumModelType;
import com.jzfq.rms.account.service.DictionaryTypeService;
import com.jzfq.rms.account.utils.CommonBeanUtils;
import com.jzfq.rms.account.utils.DateUtils;
import com.jzfq.rms.account.utils.ElseFiledsUtils;
import com.jzfq.rms.account.web.requestModel.DictionaryRequestModel;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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
    @Autowired
    LoginCommon loginCommon;


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
        logger.info("开始连接数据库获取字典数据，参数信息：" + JSONObject.toJSONString(dic));
        PageParam<AccountDicType> page = new PageParam<AccountDicType>(dic, dic.getPageNum(), dic.getNumPerPage());
        List<AccountDicType> pageData = accountDicTypeMapper.findDictions(page);
        if (null != pageData && pageData.size() > 0) {
            logger.info("连接数据库获取字典数据成功，返回数据：" + JSONObject.toJSONString(pageData));
            return new PageData(page.getPageNo(), page.getPageSize(), page.getDataTotal(), pageData);
        }
        logger.info("连接数据库获取字典数据成功，返回数据为空");
        return null;
    }

    /**
     * 保存字典信息
     * @param dictionaryRequestModel
     * @return
     */
    @Override
    public int save(DictionaryRequestModel dictionaryRequestModel) {
        logger.info("开始连接数据库保存字典数据，参数信息：" + JSONObject.toJSONString(dictionaryRequestModel));
        int count = accountDicTypeMapper.insert(newAccountDicType(dictionaryRequestModel));
        if (count > 0) {
            logger.info("保存字典数据成功，保存数量：" + count);
            return count;
        }
        logger.info("保存字典数据失败，保存数量：0");
        return 0;
    }

    /**
     * 修改字典信息
     * @param dictionaryRequestModel
     * @return
     */
    @Override
    public int update(DictionaryRequestModel dictionaryRequestModel) {
        logger.info("开始连接数据库修改字典数据，参数信息：" + JSONObject.toJSONString(dictionaryRequestModel));
        int count =accountDicTypeMapper.updateByPrimaryKey(newAccountDicType(dictionaryRequestModel));
        if (count > 0) {
            logger.info("修改字典数据成功，修改数量：" + count);
            return count;
        }
        logger.info("修改字典数据失败，修改数量：0");
        return 0;
    }

    /**
     * 取出AccountDicType对象
     *
     * @param dic
     * @return
     */

    public AccountDicType newAccountDicType(DictionaryRequestModel dic){
        AccountUser accountUser = loginCommon.getCurrentUser();
        String currentLoginUser = "";
        if (null != accountUser) {
            currentLoginUser = accountUser.getLoginName();
        }
        AccountDicType accountDicType = new AccountDicType();
        if (StringUtils.isNotEmpty(dic.getId())) {
            accountDicType = accountDicTypeMapper.selectByPrimaryKey(Long.valueOf(dic.getId()));
            //表示修改 可做字段拓展
            CommonBeanUtils.copyPropertiesElseList(dic,accountDicType, ElseFiledsUtils.elseFileds(EnumModelType.MODEL_DIC_TYPE.code()));
            accountDicType.setUpdateBy(currentLoginUser);
            accountDicType.setUpdateDate(new Date());
        } else {
            //表示新增 可做字段拓展
            CommonBeanUtils.copyProperties(dic,accountDicType);
        }
        return accountDicType;
    }





}
