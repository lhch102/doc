package com.jzfq.rms.product.persistence.dao;

import com.jzfq.rms.product.bean.CustomerType;
import com.jzfq.rms.product.common.PageParam;

import java.util.List;
import java.util.Map;

public interface CustomerTypeDao {
    int deleteByPrimaryKey(String tacticskqid);

    int insert(CustomerType record);

    int insertSelective(CustomerType record);

    CustomerType selectByPrimaryKey(Integer customerTypeId);

    int updateByPrimaryKeySelective(CustomerType record);

    int updateByPrimaryKey(CustomerType record);

    List<CustomerType> tacticsKQID(Map<String, Object> param);

    /**
     * 获取渠道种类
     * @return
     */
    List<String> getChannelType();

    /**
     * 分页查询客群信息
     * @param page
     * @return
     */
    List<CustomerType> queryList(PageParam<CustomerType> page);

    /**
     * 校验客群名称是否重复
     * @param params
     * @return
     */
    int checkTacticsKQName(Map<String, Object> params);

    String getTacticsKQNo();

}