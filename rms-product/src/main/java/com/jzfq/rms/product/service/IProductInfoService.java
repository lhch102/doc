package com.jzfq.rms.product.service;

import com.jzfq.rms.product.bean.ProductInfo;
import com.jzfq.rms.product.common.PageData;
import com.jzfq.rms.product.exception.BusinessException;

import java.util.List;
import java.util.Map;

/**
 * @author 大连桔子分期科技有限公司
 * @date 2017年10月15日 22:23:15
 */
public interface IProductInfoService {

    ProductInfo getByPK(Integer product_id) throws BusinessException;

    List<ProductInfo> getAllByCondition(Map<String, Object> conditionMap) throws BusinessException;

    long getCountByCondition(Map conditionMap) throws BusinessException;

    int insert(ProductInfo record) throws BusinessException;

    ProductInfo getProductInfoByProductOrderNo(String productOrderNo) throws BusinessException;

    List<ProductInfo> getAll() throws BusinessException;

    /**
     * 查询订单策略分页数据
     * @param record
     * @return
     */
    PageData<ProductInfo> queryList(ProductInfo record);

    /**
     * 根据ProductInfo逻辑删除订单策略
     * @param record
     * @return
     */
    int delProductTactics(ProductInfo record);

    /**
     * 根据productIds逻辑删除订单策略
     * @param productIds
     * @return
     */
    String updateProductTacticsForFlag(String[] productIds);

    boolean checkProductName(Integer productId,String productName);

}
