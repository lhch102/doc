package com.jzfq.rms.account.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.jzfq.rms.account.bean.AccountDept;
import com.jzfq.rms.account.bean.AccountDic;
import com.jzfq.rms.account.bean.AccountDicType;
import com.jzfq.rms.account.bean.AccountUser;
import com.jzfq.rms.account.common.LoginCommon;
import com.jzfq.rms.account.common.PageData;
import com.jzfq.rms.account.dao.AccountDeptMapper;
import com.jzfq.rms.account.enums.EnumDelFlag;
import com.jzfq.rms.account.enums.EnumEnableFlag;
import com.jzfq.rms.account.enums.EnumModelType;
import com.jzfq.rms.account.exception.BusinessException;
import com.jzfq.rms.account.service.AccountDeptService;
import com.jzfq.rms.account.utils.CommonBeanUtils;
import com.jzfq.rms.account.utils.ElseFiledsUtils;
import com.jzfq.rms.account.utils.IdSplitUtils;
import com.jzfq.rms.account.web.requestModel.AccountDeptRequestModel;
import org.apache.commons.collections4.ListUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author 大连桔子分期科技有限公司
 * @date 2018/4/8 10:09
 */
@Service("accountDeptService")
public class AccountDeptServiceImpl implements AccountDeptService {

    private final static Logger logger = LoggerFactory.getLogger(AccountDeptServiceImpl.class);

    @Autowired
    private AccountDeptMapper deptMapper;
    @Autowired
    LoginCommon loginCommon;


    /**
     * 获取所属机构
     *
     * @param debt
     * @return
     */
    @Override
    public List<AccountDept> getDeptList(AccountDept debt) {
        logger.info("开始连接数据库获取机构数据，参数信息：无");
        List<AccountDept> deptList = deptMapper.getDeptList(debt);
        if (null != deptList && deptList.size() > 0) {
            logger.info("连接数据库获取机构数据成功，返回数据：" + JSONObject.toJSONString(deptList));
            return debtTree(deptList);
        }
        logger.info("连接数据库获取机构数据成功，返回数据为空");
        return null;
    }

    /**
     * 启用/停用
     *
     * @param type，debtNo
     * @return
     */

    @Override
    public int isUsing(String type, List<String> debtNo) throws BusinessException {
        logger.info("开始连接数据库" + EnumEnableFlag.getEnum(type).getMessage() + "机构数据，参数信息：type：" + type + ",debtNo:" + debtNo);
        int i = deptMapper.isUsing(type, debtNo);
        if (i > 0) {
            logger.info(EnumEnableFlag.getEnum(type).getMessage() + "机构数据成功，" + EnumEnableFlag.getEnum(type).getMessage() + "更新数量：" + i);
            return i;
        }
        logger.info(EnumEnableFlag.getEnum(type).getMessage() + "机构数据失败，" + EnumEnableFlag.getEnum(type).getMessage() + "更新数量：0");
        return 0;
    }

    /**
     * 得到机构树状图
     *
     * @param deptList
     * @return
     */

    public List<AccountDept> debtTree(List<AccountDept> deptList) {
        // 声明权限机构集合，将集合中的父子关系按照 父--- 子集和(childrens)
        List<AccountDept> rootTrees = new ArrayList<>();
        for (AccountDept tree : deptList) {
            //首先将父级集合装入集合中
            if ("-1".equals(tree.getParentNo())) {
                tree.setEnableFlag(EnumEnableFlag.getEnum(tree.getEnableFlag()).getMessage());
                rootTrees.add(tree);
            }
            //循环机构集合将具有父子关系的子数据装进子集和中
            for (AccountDept t : deptList) {
                if (t.getParentNo().equals(tree.getDeptNo())) {
                    t.setEnableFlag(EnumEnableFlag.getEnum(t.getEnableFlag()).getMessage());
                    if (tree.getChildrens() == null) {
                        List<AccountDept> myChildrens = new ArrayList<AccountDept>();
                        myChildrens.add(t);
                        tree.setChildrens(myChildrens);
                    } else {
                        tree.getChildrens().add(t);
                    }
                }
            }
            //进行子级排序(同级之间排序)
            sortDebtList(tree.getChildrens());
        }
        //进行菜单父级排序(同级之间排序)
        sortDebtList(rootTrees);
        logger.info("机构数据父子关系处理成功，返回数据：" + JSONObject.toJSONString(rootTrees));
        return rootTrees;
    }


    /**
     * sortDebtList(机构同级排序方法（父子级）)
     *
     * @param rootTrees
     * @return
     */

    public void sortDebtList(List<AccountDept> rootTrees) {
        int deptSize = rootTrees.size();
        AccountDept dept1;
        AccountDept dept2;
        for (int i = 0; i < rootTrees.size() - 1; i++) {
            for (int j = 0; j < deptSize - 1; j++) {
                String order1 = rootTrees.get(j).getSort().toString();
                String order2 = rootTrees.get(j + 1).getSort().toString();
                dept1 = rootTrees.get(j);
                dept2 = rootTrees.get(j + 1);
                if (order1.compareToIgnoreCase(order2) >= 0) {
                    rootTrees.set(j, dept2);
                    rootTrees.set(j + 1, dept1);
                }
            }
            deptSize = deptSize - 1;
        }
    }

    /**
     * 根据机构编码查询自信息
     *
     * @param deptNo
     * @return
     */

    @Override
    public AccountDept queryModel(String deptNo) {
        logger.info("开始连接数据库根据机构编号查询机构数据，参数信息：deptNo：" + deptNo);
        AccountDept debt = deptMapper.selectByPrimaryKey(deptNo);
        if (null != debt) {
            logger.info("根据机构编号查询机构数据成功，返回数据信息：" + JSONObject.toJSONString(debt));
            return debt;
        }
        logger.info("根据机构编号查询机构数据失败，该机构信息不存在！");
        return null;
    }

    /**
     * 保存机构数据
     *
     * @param accountDept
     * @return
     */

    @Override
    public int save(AccountDeptRequestModel accountDept) {
        logger.info("开始连接数据库保存机构数据，参数信息：" + JSONObject.toJSONString(accountDept));
        int count = deptMapper.insert(newAccountDept(accountDept));
        if (count > 0) {
            logger.info("保存机构数据成功，保存数量：" + count);
            return count;
        }
        logger.info("保存机构数据失败，保存数量：0");
        return 0;
    }

    /**
     * 修改机构数据
     *
     * @param accountDeptRequestModel
     * @return
     */

    @Override
    public int update(AccountDeptRequestModel accountDeptRequestModel) {
        logger.info("开始连接数据库修改机构数据，参数信息：" + JSONObject.toJSONString(accountDeptRequestModel));
        AccountDept dept = newAccountDept(accountDeptRequestModel);
        int count =deptMapper.updateByPrimaryKey(dept);
        if (count > 0) {
            logger.info("修改机构数据成功，修改数量：" + count);
            return count;
        }
        logger.info("修改机构数据失败，修改数量：0");
        return 0;
    }

    /**
     * 根据用户编号查询用户所属机构
     * @param userNo
     * @return
     */
    @Override
    public List<Map<String, Object>> getDeptListByUserNo(String userNo) {
        return deptMapper.getDeptListByUserNo(userNo);
    }

    /**
     * 得到AccountDept对象
     *
     * @param model
     * @return
     */

    public AccountDept newAccountDept(AccountDeptRequestModel model) {
        AccountUser accountUser = loginCommon.getCurrentUser();
        String currentLoginUser = "";
        if (null != accountUser) {
            currentLoginUser = accountUser.getLoginName();
        }
        AccountDept accountDept = deptMapper.selectByPrimaryKey(model.getDeptNo());
        if (null != accountDept) {
            //表示修改 可做字段拓展
            CommonBeanUtils.copyPropertiesElseList(model, accountDept, ElseFiledsUtils.elseFileds(EnumModelType.MODEL_DEPT.code()));
            accountDept.setUpdateBy(currentLoginUser);
            accountDept.setUpdateDate(new Date());
        } else {
            accountDept = new AccountDept();
            //表示新增 可做字段拓展
            CommonBeanUtils.copyProperties(model, accountDept);
            accountDept.setDelFlag(EnumDelFlag.DELETE_FLAG.getCode());//未删除
            accountDept.setEnableFlag(EnumEnableFlag.ENABLE.getCode());//启用
            accountDept.setCreateBy(currentLoginUser);
            accountDept.setCreateDate(new Date());
        }
        //做拓展：如果以后页面改造成保存list修改的话，可把 list.add(accountDic);放在这里
        return accountDept;
    }



}

