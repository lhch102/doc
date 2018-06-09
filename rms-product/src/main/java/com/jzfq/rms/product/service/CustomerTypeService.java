package com.jzfq.rms.product.service;

import com.jzfq.rms.product.bean.ConfigMobile;
import com.jzfq.rms.product.bean.CustomerType;
import com.jzfq.rms.product.bean.LatitudeLongitude;
import com.jzfq.rms.product.bean.RegistCode;
import com.jzfq.rms.product.common.PageData;
import com.jzfq.rms.product.exception.BusinessException;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * @author 大连桔子分期科技有限公司
 * @date 2018/4/9 16:15
 */
public interface CustomerTypeService {

    /**
     * 获取所有客群策略
     * @return
     * @throws BusinessException
     */
    int addRedisKQID(Map<String, Object> param) throws BusinessException;

    int addTacticsKQ() throws BusinessException;

    /**
     * 分页查询客群信息
     * @param record
     * @return
     */
    PageData<CustomerType> queryList(CustomerType record);

    /**
     * 添加客群加载默认项
     * @return
     */
    Map<String,Object> loadTacticsKQ() throws BusinessException;

    /**
     * 校验客群名称是否重复
     * @param tacticskqId
     * @param customerTypeName
     * @return
     */
    boolean checkTacticsKQName(String tacticskqId, String customerTypeName)throws BusinessException;

    /**
     * 客群策略编辑
     * @param customerTypeId
     * @return
     */
    CustomerType editTacticsKQ(Integer customerTypeId) throws BusinessException;

    /**
     * 添加配置手机号
     * @param configMobile
     */
    void addMobile(ConfigMobile configMobile)throws BusinessException;

    /**
     * 移除手机号/F码/经纬度
     * @param type  0:移除手机号;1:移除F码;2:移除经纬度
     * @param ids
     */
    int removeObject(Integer type, List<Integer> ids)throws BusinessException;

    /**
     * 更新有效期
     * @param type
     * @param ids
     * @param validityPeriod
     */
    int updateValidityPeriod(Integer type,List<Integer> ids,String validityPeriod) throws BusinessException, ParseException;

    /**
     * 添加F码
     * @param registCode
     */
    void addRegistCode(RegistCode registCode);

    /**
     * 添加经纬度
     * @param latitudeLongitude
     */
    void addLatitudeLongitude(LatitudeLongitude latitudeLongitude);

    /**
     * 分页查询配置手机列表
     * @param mobile
     * @return
     */
    PageData<ConfigMobile> queryMobilePage(ConfigMobile mobile);

    /**
     * 分页查询F码列表
     * @param registCode
     * @return
     */
    PageData<RegistCode> queryRegistCodePage(RegistCode registCode);

    /**
     * 分页查询经纬度列表
     * @param latitudeLongitude
     * @return
     */
    PageData<LatitudeLongitude> querylatitudeLongitudePage(LatitudeLongitude latitudeLongitude);

    /**
     * 保存客群策略信息
     * @param customerType
     * @return
     */
    int saveTacticsKQ(CustomerType customerType);
}
