package com.jzfq.rms.account.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.jzfq.rms.account.bean.AccountUserDeptKey;
import com.jzfq.rms.account.dao.AccountUserDeptMapper;
import com.jzfq.rms.account.service.AccountUserDeptService;
import com.jzfq.rms.account.utils.CompareOthersUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author 大连桔子分期科技有限公司
 * @date 2018/4/3 11:46
 */

@Service("accountUserDeptService")
public class AccountUserDeptServiceImpl implements AccountUserDeptService {

    private final static Logger logger = LoggerFactory.getLogger(AccountUserDeptServiceImpl.class);

    @Autowired
    AccountUserDeptMapper accountUserDeptMapper;


    /**
     * 保存机构-用户信息
     * @param userIds,deptNo
     * @return
     */

    @Override
    public int save(List<String> userIds, String deptNo) {
        logger.info("开始连接数据库保存机构数据，参数信息：userIds：" + userIds + ",deptNo：" + deptNo);
        int count = 0;
        if (null != userIds && userIds.size() > 0) {
                for (String uid : userIds) {
                    AccountUserDeptKey accountUserDeptKey = new AccountUserDeptKey();
                    count++;
                    accountUserDeptKey.setDeptNo(deptNo);//关联机构编号
                    accountUserDeptKey.setUserNo(uid);
                    //添加
                    accountUserDeptMapper.insert(accountUserDeptKey);
            }
        }
        if (count > 0) {
            logger.info("保存机构数据成功，保存数量：" + count);
            return count;
        }
        logger.info("保存机构数据失败，保存数量：0");
        return 0;
    }

    /**
     * 修改机构-用户信息
     * @param userIds,deptNo
     * @return
     */

    @Override
    public int update(List<String> userIds, String deptNo) {
        logger.info("开始连接数据库修改机构数据，参数信息：userIds：" + userIds + ",deptNo：" + deptNo);
        int countdel = 0;//移除个数
        int countadd = 0;//新增个数
        //根据机构编号获取旧的机构-用户关联关系
        List<String> oldUserDepts = accountUserDeptMapper.queryOldUserDeptList(deptNo);
        logger.info("根据机构编号:"+deptNo+"查询原机构数据，返回信息：oldUserDepts：" + JSONObject.toJSONString(oldUserDepts));
        //获取2个数组中相同与不相同的元素
        Map<String, Object> map = CompareOthersUtils.compare(userIds.toArray(),oldUserDepts);
        logger.info("获取2个数组中相同与不相同的元素：" + JSONObject.toJSONString(map));
        List<String> deleteArry = ( List<String>)map.get("delete_arry"); //要移除的元素
        if (null != deleteArry && deleteArry.size() > 0) {
            del(deleteArry,deptNo);//移除
        }
        logger.info("获取2个数组中相同与不相同的元素,要移除的元素：" + JSONObject.toJSONString(deleteArry)+",移除成功数量："+countdel);
        List<String> addArry = ( List<String>)map.get("add_arry"); //要新增的元素
        if (null != addArry && addArry.size() > 0) {
            countadd = save(addArry,deptNo);//新增
        }
        logger.info("获取2个数组中相同与不相同的元素,要新增的元素：" + JSONObject.toJSONString(addArry)+",新增成功数量："+countadd);
        return 0;
    }

    /**
     * 移除机构-用户信息
     * @param userIds,deptNo
     * @return
     */

    @Override
    public int del(List<String> userIds, String deptNo) {
        logger.info("开始连接数据库删除机构-用户关联数据，参数信息：userId：" + userIds + ",deptNo:" + deptNo);
        int i = 0;//移除个数
        AccountUserDeptKey accountUserDeptKey = new AccountUserDeptKey();
        if (null != userIds && userIds.size() > 0) {
            for (String userNo : userIds) {
                i++;
                accountUserDeptKey.setUserNo(userNo);
                accountUserDeptKey.setDeptNo(deptNo);
                accountUserDeptMapper.deleteByPrimaryKey(accountUserDeptKey);
            }
        }
        if (i > 0) {
            logger.info("删除机构-用户关联数据成功，更新数量：" + i);
            return i;
        }
        logger.info("删除机构-用户关联数据失败，更新数量：0");
        return 0;
    }
}
