package com.jzfq.rms.account.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.jzfq.rms.account.bean.AccountMenuOperateKey;
import com.jzfq.rms.account.bean.AccountOperate;
import com.jzfq.rms.account.bean.AccountUser;
import com.jzfq.rms.account.bean.Extended.AccountOperateParamExtended;
import com.jzfq.rms.account.bean.Extended.AccountOperateRelationExtended;
import com.jzfq.rms.account.common.LoginCommon;
import com.jzfq.rms.account.common.PageData;
import com.jzfq.rms.account.common.PageParam;
import com.jzfq.rms.account.constant.RedisConstants;
import com.jzfq.rms.account.dao.AccountMenuOperateMapper;
import com.jzfq.rms.account.dao.AccountOperateMapper;
import com.jzfq.rms.account.enums.EnumEnableFlag;
import com.jzfq.rms.account.enums.EnumModelType;
import com.jzfq.rms.account.enums.EnumOperateType;
import com.jzfq.rms.account.exception.BusinessException;
import com.jzfq.rms.account.service.AccountMenuOperateService;
import com.jzfq.rms.account.bean.AccountOperate;
import com.jzfq.rms.account.bean.Extended.AccountOperateExtended;
import com.jzfq.rms.account.dao.AccountOperateMapper;
import com.jzfq.rms.account.service.AccountOperateService;
import com.jzfq.rms.account.utils.CommonBeanUtils;
import com.jzfq.rms.account.utils.CompareOthersUtils;
import com.jzfq.rms.account.utils.ElseFiledsUtils;
import com.jzfq.rms.account.web.requestModel.AccountMenuRequestModel;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author 大连桔子分期科技有限公司
 * @date 2018/4/8 16:34
 */
@Service("accountOperateService")
public class AccountOperateServiceImpl implements AccountOperateService {

    private static final Logger logger = LoggerFactory.getLogger(AccountOperateServiceImpl.class);

    @Autowired
    AccountOperateMapper accountOperateMapper;
    @Autowired
    LoginCommon loginCommon;
    @Autowired
    AccountMenuOperateService accountMenuOperateService;
    @Autowired
    AccountMenuOperateMapper accountMenuOperateMapper;
    @Autowired
    private RedisTemplate redisTemplate;


    /**
     * 分页查询操作权限数据
     *
     * @param menu
     * @return
     */

    @Override
    public PageData<AccountOperate> list(AccountMenuRequestModel menu) throws BusinessException {
        PageParam<AccountMenuRequestModel> page = new PageParam<AccountMenuRequestModel>(menu, menu.getPageNum(), menu.getNumPerPage());
        logger.info("开始连接数据库获取操作权限数据，参数信息：" + JSONObject.toJSONString(menu));
        List<AccountOperate> pageData = accountOperateMapper.findOperaters(page);
        if (null != pageData && pageData.size() > 0) {
            logger.info("连接数据库获取操作权限数据成功，返回数据：" + JSONObject.toJSONString(pageData));
//            for (AccountDic accountDic : pageData) {
//                accountDic.setDelFlag(EnumEnableFlag.getEnum(accountDic.getDelFlag()).getMessage());
//            }
            return new PageData(page.getPageNo(), page.getPageSize(), page.getDataTotal(), pageData);
        }
        logger.info("连接数据库获取操作权限数据成功，返回数据为空");
        return null;
    }

    /**
     * 删除操作权限
     *
     * @param ids
     * @return
     */

    @Override
    public int del(List<String> ids) {
        logger.info("开始连接数据库删除操作权限数据，参数信息：ids：" + ids);
        int i = accountOperateMapper.updateEnable(ids, EnumEnableFlag.UN_ENABLE.code());//停用
        if (i > 0) {
            logger.info("删除操作权限数据成功，更新数量：" + i);
            return i;
        }
        logger.info("删除操作权限数据失败，更新数量：0");
        return 0;
    }

    /**
     * 保存操作权限数据
     *
     * @param accountMenuRequestModel
     * @return
     */

    @Override
    public int save(AccountMenuRequestModel accountMenuRequestModel) {
        logger.info("开始连接数据库保存操作权限数据，参数信息：" + JSONObject.toJSONString(accountMenuRequestModel));
        int addCount = 0;
        if (null != accountMenuRequestModel.getMenuOperateList()
                && accountMenuRequestModel.getMenuOperateList().size() > 0) {
            //删除移除项
            delIds(accountMenuRequestModel);
            for (AccountOperate accountOperate : accountMenuRequestModel.getMenuOperateList()) {
                addCount++;
                AccountOperate accountOperate1 = newAccountOperate(accountOperate);
                if (null != accountOperate1.getId()) {
                    //修改
                    update(accountOperate1);
                } else {
                    accountOperateMapper.insertOperate(accountOperate1);
                    //保存菜单-操作权限关联关系数据
                    accountMenuOperateService.save(newAccountMenuOperateKey(accountMenuRequestModel.getMenuNo(), accountOperate1.getId()));
                }
            }
        }
        if (addCount > 0) {
            logger.info("保存操作权限数据成功，保存数量：" + addCount);
            return addCount;
        }
        logger.info("保存操作权限数据失败，保存数量：0");
        return 0;
    }

    /**
     * 修改操作权限数据
     *
     * @param accountOperate
     * @return
     */

    @Override
    public int update(AccountOperate accountOperate) {
        logger.info("开始连接数据库修改操作权限数据，参数信息：" + JSONObject.toJSONString(accountOperate));
//        AccountOperate accountOperate = new AccountOperate();
//        CommonBeanUtils.copyPropertiesElseList(accountMenuRequestModel, accountOperate, ElseFiledsUtils.elseFileds(EnumModelType.MODEL_OPERATE.code()));
        int count = accountOperateMapper.updateByPrimaryKey(newAccountOperate(accountOperate));
        if (count > 0) {
            logger.info("修改操作权限数据成功，修改数量：" + count);
            return count;
        }
        logger.info("修改操作权限数据失败，修改数量：0");
        return 0;
    }

    /**
     * 查询操作权限数据 不是分页
     *
     * @param menu
     * @return
     */

    @Override
    public List<AccountOperate> queryOperateByMenuNo(AccountMenuRequestModel menu) throws BusinessException {
        PageParam<AccountMenuRequestModel> page = new PageParam<AccountMenuRequestModel>(menu, menu.getPageNum(), menu.getNumPerPage());
        logger.info("开始连接数据库获取操作权限数据，参数信息：" + JSONObject.toJSONString(menu));
        List<AccountOperate> pageData = accountOperateMapper.findOperaters(page);
        if (null != pageData && pageData.size() > 0) {
            redisTemplate.opsForValue().set(RedisConstants.MENU_OPERATE_LIST,JSONObject.toJSONString(pageData));
            redisTemplate.expire(RedisConstants.MENU_OPERATE_LIST, RedisConstants.OUT_TIME, TimeUnit.SECONDS);
            logger.info("连接数据库获取操作权限数据成功，返回数据：" + JSONObject.toJSONString(pageData));
//            for (AccountDic accountDic : pageData) {
//                accountDic.setDelFlag(EnumEnableFlag.getEnum(accountDic.getDelFlag()).getMessage());
//            }
            return pageData;
        }
        logger.info("连接数据库获取操作权限数据成功，返回数据为空");
        return null;
    }

    /**
     * 得到AccountOperate对象
     *
     * @param model
     * @return
     */

    public AccountOperate newAccountOperate(AccountOperate model) {
        AccountUser accountUser = loginCommon.getCurrentUser();
        String currentLoginUser = "";
        if (null != accountUser) {
            currentLoginUser = accountUser.getLoginName();
        }
        AccountOperate accountOperate = new AccountOperate();
        if (null != model.getId()) {
            accountOperate = accountOperateMapper.selectByPrimaryKey(Long.valueOf(model.getId()));
        }
        if (null != accountOperate) {
            //表示修改 可做字段拓展
            CommonBeanUtils.copyPropertiesElseList(model, accountOperate, ElseFiledsUtils.elseFileds(EnumModelType.MODEL_OPERATE.code()));
        } else {
            accountOperate = new AccountOperate();
            //表示新增 可做字段拓展
            CommonBeanUtils.copyProperties(model, accountOperate);
            accountOperate.setEnable(EnumEnableFlag.ENABLE.getCode());//启用
        }
        //做拓展：如果以后页面改造成保存list修改的话，可把 list.add(accountDic);放在这里
        accountOperate.setOperateType(EnumOperateType.BUTTON_PERMISSIONS.code());//按钮权限
        return accountOperate;
    }


    /**
     * 得到AccountMenuOperateKey对象
     *
     * @param menuNo,id
     * @return
     */

    public AccountMenuOperateKey newAccountMenuOperateKey(String menuNo, Long id) {
        AccountMenuOperateKey accountMenuOperateKey = new AccountMenuOperateKey();
        accountMenuOperateKey.setMenuNo(menuNo);
        accountMenuOperateKey.setId(id);
        return accountMenuOperateKey;
    }

    /**
     * 删除移除项
     *
     * @param accountMenuRequestModel
     * @return
     */

    public int delIds(AccountMenuRequestModel accountMenuRequestModel) {
        //根据菜单编号获取旧的操作权限-菜单关联关系
        List<String> oldMenuOperates = accountMenuOperateMapper.queryOldMenuOperateList(accountMenuRequestModel.getMenuNo());
        logger.info("根据菜单编号:" + accountMenuRequestModel.getMenuNo() + "查询原操作权限数据，返回信息：oldMenuOperates：" + JSONObject.toJSONString(oldMenuOperates));
        //获取2个数组中相同与不相同的元素
        Map<String, Object> map = CompareOthersUtils.compare(getOperateIds(accountMenuRequestModel.getMenuOperateList()).toArray(), oldMenuOperates);
        logger.info("获取2个数组中相同与不相同的元素：" + JSONObject.toJSONString(map));
        List<String> deleteArry = (List<String>) map.get("delete_arry"); //要移除的元素
        del(deleteArry);
        //删除关联表
        accountMenuOperateService.del(deleteArry, accountMenuRequestModel.getMenuNo());
        return 0;
    }


    /**
     * 封装操作权限ids
     *
     * @param operateList
     * @return
     */

    private static List<String> getOperateIds(List<AccountOperate> operateList) {
        List<String> list = new ArrayList<String>();
        if (null != operateList && operateList.size() > 0) {
            for (AccountOperate accountOperate : operateList) {
                list.add(accountOperate.getId() == null ? "" : accountOperate.getId().toString());
            }
        }
        return list;
    }

    /**
     * 根据操作键id查询操作权限
     *
     * @param id
     * @return
     */

    @Override
    public AccountOperate queryByOperateId(String id) {
        logger.info("开始连接数据库根据操作键id查询操作键值数据，参数信息：id：" + id);
        AccountOperate dic = accountOperateMapper.selectByPrimaryKey(Long.valueOf(id));
        if (null != dic) {
            logger.info("根据操作id查询操作键值数据成功，返回数据信息："+ JSONObject.toJSONString(dic));
//            if (dic.getUpdateDate() != null) {
//                dic.setUpdateDateStr(DateUtils.date2str(dic.getUpdateDate(),DateUtils.DATE_FORMAT_LONG));
//            }
            return dic;
        }
        logger.info("根据操作id查询操作键值数据失败，该操作信息不存在！");
        return null;
    }

    private final static String OPERATE_TYPE_BUTTON = "0";
    private final static String OPERATE_TYPE_SELECT = "1";
    private final static String OPERATE_TYPE_DEPT = "2";


    public List<AccountOperate> selectByMenuNo(HashMap<String, Object> paramNo) {
        return accountOperateMapper.selectByMenuNo(paramNo);
    }

    @Override
    public AccountOperateExtended selectExtendByMenuNo(HashMap<String, Object> paramMenuNo) {

        AccountOperateExtended accountOperateExtended = new AccountOperateExtended();
        List<AccountOperate> accountOperates = this.selectByMenuNo(paramMenuNo);

        for (AccountOperate operate : accountOperates) {
            if (operate.getOperateType().equals(OPERATE_TYPE_BUTTON)) {
                accountOperateExtended.getButtonOperate().add(operate);
            } else if (operate.getOperateType().equals(OPERATE_TYPE_SELECT)) {
                accountOperateExtended.getSelectOperate().add(operate);
            } else if (operate.getOperateType().equals(OPERATE_TYPE_DEPT)) {
                accountOperateExtended.getDeptOperate().add(operate);
            }
        }
        return accountOperateExtended;
    }

    /**
     * 操作权限  启用/停用  方法
     *
     * @param paramMap
     */
    @Override
    public void updateOperRelation(HashMap<String, Object> paramMap) {
        if (paramMap != null) {
            accountOperateMapper.updateOperRelation(paramMap);
        }
    }

    @Transactional
    @Override
    public void save(AccountOperateRelationExtended accountOperateRelationExtended) {

        if (accountOperateRelationExtended != null) {
            HashMap<String, Object> paramMap = new HashMap<>();
            AccountOperateParamExtended accountOperateParamExtended = accountOperateRelationExtended.getOperate();
            String menuNo = accountOperateRelationExtended.getMenuNo();
            String operateValue = accountOperateRelationExtended.getOperate().getOperateValue();
            String operateType = null;
            if(accountOperateParamExtended!=null){
                //查看是那种权限
                operateType = accountOperateParamExtended.getOperateType();
            }
            Long operId = null;

            if ("1".equals(operateType)) {

                //查询权限e
                if (accountOperateParamExtended != null) {

                    AccountOperate accountOperate = new AccountOperate();
                    accountOperate.setOperateKey(accountOperateParamExtended.getOperateKey());
                    accountOperate.setOperateRemark(accountOperateParamExtended.getOperateRemark());
                    accountOperate.setOperateValue(accountOperateParamExtended.getOperateValue());
                    accountOperate.setEnable(EnumEnableFlag.ENABLE.getCode());
                    accountOperate.setOperateType("1");
                    accountOperateMapper.insert(accountOperate);

                    AccountMenuOperateKey accountMenuOperateKey = new AccountMenuOperateKey();
                    accountMenuOperateKey.setId(accountOperate.getId());
                    accountMenuOperateKey.setMenuNo(accountOperateRelationExtended.getMenuNo());
                    accountMenuOperateMapper.insert(accountMenuOperateKey);

                }
            } else if (operateType.equals("2")) {
                //部门权限
                paramMap.put("operateTye", "2");
                paramMap.put("operateValue", operateValue);

                //部门权限 只能获取到一条数据。
                List<AccountOperate> accountOperates = accountOperateMapper.selectByType(paramMap);
                if (accountOperates != null && accountOperates.size() > 0) {
                    paramMap.put("id", accountOperates.get(0).getId());

                    accountMenuOperateMapper.deleteByMenuNo(paramMap);
                    AccountMenuOperateKey accountMenuOperateKey = new AccountMenuOperateKey();
                    accountMenuOperateKey.setId(accountOperates.get(0).getId());
                    accountMenuOperateKey.setMenuNo(accountOperateRelationExtended.getMenuNo());
                    accountMenuOperateMapper.insert(accountMenuOperateKey);

                }



            } else if (operateType.equals("0")) {

            } else {

            }

        }
    }

}

