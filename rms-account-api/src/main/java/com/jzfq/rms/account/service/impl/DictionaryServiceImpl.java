package com.jzfq.rms.account.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.jzfq.rms.account.bean.AccountDic;

import com.jzfq.rms.account.bean.AccountDicType;
import com.jzfq.rms.account.bean.AccountUser;
import com.jzfq.rms.account.common.LoginCommon;
import com.jzfq.rms.account.common.PageData;
import com.jzfq.rms.account.common.PageParam;
import com.jzfq.rms.account.common.service.GenerateObjectNoService;
import com.jzfq.rms.account.dao.AccountDicMapper;
import com.jzfq.rms.account.enums.EnumDelFlag;
import com.jzfq.rms.account.enums.EnumEnableFlag;
import com.jzfq.rms.account.enums.EnumModelType;
import com.jzfq.rms.account.enums.ReturnCode;
import com.jzfq.rms.account.service.DictionaryService;
import com.jzfq.rms.account.utils.CommonBeanUtils;
import com.jzfq.rms.account.utils.DateUtils;
import com.jzfq.rms.account.utils.ElseFiledsUtils;
import com.jzfq.rms.account.utils.IdSplitUtils;
import com.jzfq.rms.account.web.requestModel.AccountDicRequestModel;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
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
    @Autowired
    LoginCommon loginCommon;

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
            for (AccountDic accountDic : pageData) {
                accountDic.setDelFlag(EnumEnableFlag.getEnum(accountDic.getDelFlag()).getMessage());
            }
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
    public int isUsing(String type, List<String> dicid) {
        logger.info("开始连接数据库" + EnumEnableFlag.getEnum(type).getMessage() + "字典数据，参数信息：type：" + type + ",dicid:" + dicid);
        int i = accountDicMapper.isUsing(type, dicid);
        if (i > 0) {
            logger.info(EnumEnableFlag.getEnum(type).getMessage() + "字典数据成功，" + EnumEnableFlag.getEnum(type).getMessage() + "更新数量：" + i);
            return i;
        }
        logger.info(EnumEnableFlag.getEnum(type).getMessage() + "字典数据失败，" + EnumEnableFlag.getEnum(type).getMessage() + "更新数量：0");
        return 0;
    }

    /**
     * 保存字典数据
     *
     * @param dicList,type
     * @return
     */

    @Override
    public int save(List<AccountDicRequestModel> dicList, String type) {
        logger.info("开始连接数据库保存字典数据，参数信息：" + JSONObject.toJSONString(dicList));
        int count = 0;
        if (null != dicList && dicList.size() > 0) {
            List<AccountDic> dicLists = newAccountDicList(dicList);
            for (AccountDic dic:dicLists) {
                count++;
                dic.setType(type);//关联字典类型
                if (null != dic.getId()) {
                    //更新
                    accountDicMapper.updateByObject(dic);
                }else {
                    //添加
                    accountDicMapper.insert(dic);
                }
            }
        }
        if (count > 0) {
            logger.info("保存字典数据成功，保存数量：" + count);
            return count;
        }
        logger.info("保存字典数据失败，保存数量：0");
        return 0;
    }

    /**
     * 修改字典数据
     * @param dic
     * @return
     */

    @Override
    public int update(AccountDicRequestModel dic) {
        logger.info("开始连接数据库修改字典数据，参数信息：" + JSONObject.toJSONString(dic));
        int count = accountDicMapper.updateByObject(newAccountDic(dic));
        if (count > 0) {
            logger.info("修改字典数据成功，修改数量：" + count);
            return count;
        }
        logger.info("修改字典数据失败，修改数量：0");
        return 0;
    }

    /**
     * 取出修改时新增的键值数据
     *
     * @param dicList
     * @return
     */

    public List<AccountDic> newAccountDicList(List<AccountDicRequestModel> dicList){
        List<AccountDic> list = new ArrayList<AccountDic>();
        if (dicList == null || dicList.size() == 0) {
            return null;
        }
        for (AccountDicRequestModel dic : dicList) {
            //做拓展：如果以后页面改造成保存list修改的话，可把 list.add(accountDic);放在这里
            list.add(newAccountDic(dic));
        }
        return list;
    }

    /**
     * 取出AccountDic对象
     *
     * @param dic
     * @return
     */

    public AccountDic newAccountDic(AccountDicRequestModel dic){
        AccountUser accountUser = loginCommon.getCurrentUser();
        String currentLoginUser = "";
        if (null != accountUser) {
            currentLoginUser = accountUser.getLoginName();
        }
        AccountDic accountDic = new AccountDic();
        if (StringUtils.isNotEmpty(dic.getId())) {
            accountDic = accountDicMapper.selectByPrimaryKey(dic.getId().toString());
            //表示修改 可做字段拓展
            CommonBeanUtils.copyPropertiesElseList(dic,accountDic, ElseFiledsUtils.elseFileds(EnumModelType.MODEL_DIC.code()));
            accountDic.setUpdateBy(currentLoginUser);
            accountDic.setUpdateDate(new Date());
        } else {
            //表示新增 可做字段拓展
            CommonBeanUtils.copyProperties(dic,accountDic);
            accountDic.setCreateBy(currentLoginUser);
            accountDic.setCreateDate(new Date());
        }
        return accountDic;
    }









    /**
     * 根据字典id查询字典数据
     * @param id
     * @return
     */

    @Override
    public AccountDic queryDicKeyById(String id) {
        logger.info("开始连接数据库根据字典id查询字典键值数据，参数信息：id：" + id);
        AccountDic dic = accountDicMapper.selectByPrimaryKey(id);
        if (null != dic) {
            logger.info("根据字典id查询字典键值数据成功，返回数据信息："+ JSONObject.toJSONString(dic));
//            if (dic.getUpdateDate() != null) {
//                dic.setUpdateDateStr(DateUtils.date2str(dic.getUpdateDate(),DateUtils.DATE_FORMAT_LONG));
//            }
            return dic;
        }
        logger.info("根据字典id查询字典键值数据失败，该字典信息不存在！");
        return null;
    }
}
