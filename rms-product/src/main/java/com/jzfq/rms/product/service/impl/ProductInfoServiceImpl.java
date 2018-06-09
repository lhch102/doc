package com.jzfq.rms.product.service.impl;

import com.jzfq.rms.product.bean.ProductInfo;
import com.jzfq.rms.product.bean.ProductInfoExample;
import com.jzfq.rms.product.common.PageData;
import com.jzfq.rms.product.common.PageParam;
import com.jzfq.rms.product.constant.SystemConstants;
import com.jzfq.rms.product.exception.BusinessException;
import com.jzfq.rms.product.persistence.cache.IProductInfoCache;
import com.jzfq.rms.product.persistence.dao.IProductInfoDAO;
import com.jzfq.rms.product.persistence.dao.ITacticsRecordDAO;
import com.jzfq.rms.product.service.IDictionaryService;
import com.jzfq.rms.product.service.IProductInfoService;
import com.jzfq.rms.product.utils.StringUtil;
import com.jzfq.rms.product.web.action.CustomerTypeAction;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Transient;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import static com.jzfq.rms.product.utils.DateUtils.now;

/**
 * @author 大连桔子分期科技有限公司
 * @date 2017年10月08日 10:21:22
 */
public class ProductInfoServiceImpl implements IProductInfoService {

    private final static org.slf4j.Logger logger = LoggerFactory.getLogger(ProductInfoServiceImpl.class);

    @Autowired
    private IProductInfoDAO productInfoDAO;
    @Autowired
    private IProductInfoCache cacheWithRedis;
    @Autowired
    private ITacticsRecordDAO tacticsRecordDAO;
    @Autowired
    private IDictionaryService service;

    /**
     * 根据主键值获取实体
     *
     * @param productId 主键值
     * @return 实体
     * @throws BusinessException 业务异常
     */
    @Override
    public ProductInfo getByPK(Integer productId) throws BusinessException {
        ProductInfo productInfo = cacheWithRedis.getProductInfoByProductID(productId);
        if (productInfo == null) {
            productInfo = productInfoDAO.selectByPrimaryKey(productId);
            if (productInfo != null) {
                productInfo.setTacticsedid(productInfo.getTacticsedid() + "-" + tacticsRecordDAO.selectNameByNo(productInfo.getTacticsedid()));
                productInfo.setTacticszrid(productInfo.getTacticszrid() + "-" + tacticsRecordDAO.selectNameByNo(productInfo.getTacticszrid()));
                productInfo.setTacticsfkid(productInfo.getTacticsfkid() + "-" + tacticsRecordDAO.selectNameByNo(productInfo.getTacticsfkid()));
                //将订单策略信息加入缓存
                cacheWithRedis.setProductIdCache(productInfo);
            }
        }
        return productInfo;
    }

    /**
     * 根据条件获取列表
     *
     * @param conditionMap 条件   多个条件可以封装成javabean或者map
     * @return 列表
     * @throws BusinessException 业务异常
     */
    @Override
    public List<ProductInfo> getAllByCondition(Map<String, Object> conditionMap) throws BusinessException {
        ProductInfoExample example = new ProductInfoExample();
        ProductInfoExample.Criteria criteria = example.createCriteria();
        if (!StringUtil.isEmpty(conditionMap.get("product_id"))) {
            criteria.andProductIdEqualTo((Integer) conditionMap.get("product_id"));
        }
        if (!StringUtil.isEmpty(conditionMap.get("product_order_No"))) {
            criteria.andProductOrderNoEqualTo(String.valueOf(conditionMap.get("product_order_No")));
        }
        if (!StringUtil.isEmpty(conditionMap.get("channel_id"))) {
            criteria.andChannelIdEqualTo(String.valueOf(conditionMap.get("channel_id")));
        }
        if (!StringUtil.isEmpty(conditionMap.get("financial_product_id"))) {
            criteria.andFinancialProductIdEqualTo(String.valueOf(conditionMap.get("financial_product_id")));
        }
        if (!StringUtil.isEmpty(conditionMap.get("custom_type"))) {
            criteria.andCustomTypeEqualTo(Integer.valueOf(String.valueOf(conditionMap.get("custom_type"))));
        }
        if (!StringUtil.isEmpty(conditionMap.get("operation_type"))) {
            criteria.andOperationTypeEqualTo(Integer.valueOf(String.valueOf(conditionMap.get("operation_type"))));
        }
        if (!StringUtil.isEmpty(conditionMap.get("operation_sub_type"))) {
            criteria.andOperationSubTypeEqualTo(Integer.valueOf(String.valueOf(conditionMap.get("operation_sub_type"))));
        }
        if (!StringUtil.isEmpty(conditionMap.get("merchant_type"))) {
            criteria.andMerchantTypeEqualTo(Integer.valueOf(String.valueOf(conditionMap.get("merchant_type"))));
        }
        if (!StringUtil.isEmpty(conditionMap.get("client_type"))) {
            criteria.andClientTypeEqualTo(Integer.valueOf(String.valueOf(conditionMap.get("client_type"))));
        }
        if (!StringUtil.isEmpty(conditionMap.get("min_period"))) {
            criteria.andMinPeriodEqualTo(Integer.valueOf(String.valueOf(conditionMap.get("min_period"))));
        }
        if (!StringUtil.isEmpty(conditionMap.get("max_period"))) {
            criteria.andMaxPeriodEqualTo(Integer.valueOf(String.valueOf(conditionMap.get("max_period"))));
        }
        if (!StringUtil.isEmpty(conditionMap.get("min_amount"))) {
            criteria.andMinAmountEqualTo(new BigDecimal(String.valueOf(conditionMap.get("min_amount"))));
        }
        if (!StringUtil.isEmpty(conditionMap.get("max_amount"))) {
            criteria.andMaxAmountEqualTo(new BigDecimal(String.valueOf(conditionMap.get("max_amount"))));
        }
        criteria.andDeleteflagEqualTo((int) SystemConstants.DELETE_FLAG_0);
        example.setOrderByClause("create_time DESC");
        List<ProductInfo> dicList = productInfoDAO.selectByExampleWithBLOBs(example);
        return dicList;
    }

    /**
     * 根据条件获取总条数
     *
     * @param conditionMap 条件   多个条件可以封装成javabean或者map
     * @return 总条数
     * @throws BusinessException 业务异常
     */
    @Override
    public long getCountByCondition(Map conditionMap) throws BusinessException {
        int size = 0;
        List<ProductInfo> dicList = getAllByCondition(conditionMap);
        if (dicList != null && dicList.size() > 0) {
            size = dicList.size();
        }
        return size;
    }

    /**
     * 保存产品信息
     *
     * @param record 实体
     * @return 插入条数
     * @throws BusinessException 业务异常
     */
    @Override
    @Transactional(rollbackFor = BusinessException.class)
    public int insert(ProductInfo record) throws BusinessException {
        record.setCreateTime(now());
        record.setUpdater(record.getCreator());
        record.setUpdateTime(now());
        record.setDeleteflag((int) SystemConstants.DELETE_FLAG_0);
        record.setChannelName(service.getValue(record.getChannelId(), "jd_channel_name"));
        record.setFinancialProductName(service.getValue(record.getFinancialProductId(), "jd_financial_products"));
        record.setCustomTypeName(service.getValue(record.getFinancialProductId(), "jd_customer_group"));
        record.setOperationTypeName(service.getValue(record.getOperationType(), "jd_operation_type"));
        record.setOperationSubTypeName(service.getValue(record.getOperationSubType(), "jd_increase_type"));
        record.setMerchantTypeName(service.getValue(record.getMerchantType(), "jd_merchant_type"));
        record.setClientTypeName(service.getValue(record.getClientType(), "jd_equipment_type"));
        int i = productInfoDAO.insertSelective(record);
        //加入redis缓存
        cacheWithRedis.setProductInfoche(record);
        return i;
    }

    /**
     * 根据订单编号获取列表
     *
     * @param productOrderNo 订单编号
     * @return 列表
     * @throws BusinessException 业务异常
     */
    @Override
    public ProductInfo getProductInfoByProductOrderNo(String productOrderNo) throws BusinessException {
        // if (dic == null) {
        Map<String, Object> conditionMap = new HashMap();
        conditionMap.put("product_order_No", productOrderNo);
        logger.debug("订单策略编号____product_order_No："+productOrderNo);
        List<ProductInfo> dicList = getAllByCondition(conditionMap);
       // if (dicList != null && dicList.size() > 0) {
            cacheWithRedis.setProductInfoCache(dicList.get(0));
            logger.debug("订单策略编号："+dicList.get(0).getProductOrderNo());
            logger.debug("存入redis"+dicList.size()+"条");
        //}
        // }
        ProductInfo dic = cacheWithRedis.getProductInfoByProductOrderNo(productOrderNo);
        return dic;
    }

    /**
     * 根据条件获取列表
     * @return 列表
     * @throws BusinessException 业务异常
     */
    @Override
    public List<ProductInfo> getAll() throws BusinessException {
        List<ProductInfo> productInfosList = getAllByCondition(new HashMap());

        if (productInfosList != null && productInfosList.size() > 0) {
            for (int i = 0; i < productInfosList.size(); i++) {
                String productOrderNo = productInfosList.get(i).getProductOrderNo();
                logger.debug("订单策略编号MYSQL："+productOrderNo);
                getProductInfoByProductOrderNo(productOrderNo);
            }
        }
        return productInfosList;
    }

    /**
     * 查询订单策略分页数据
     *
     * @param record
     * @return
     */
    @Override
    public PageData<ProductInfo> queryList(ProductInfo record) {
        record.setDeleteflag((int) SystemConstants.DELETE_FLAG_0);
        PageParam<ProductInfo> page = new PageParam<>(record, record.getPageNum(), record.getNumPerPage());
        List<ProductInfo> list = productInfoDAO.queryList(page);
        return new PageData(page.getPageNo(), page.getPageSize(), page.getDataTotal(), list);
    }

    /**
     * 逻辑删除订单策略
     *
     * @param record
     * @return
     */
    @Override
    public int delProductTactics(ProductInfo record) {
        return productInfoDAO.updateByPrimaryKeySelective(record);
    }

    @Override
    public String updateProductTacticsForFlag(String[] productIds) {
        String message = "删除成功";
        int i = productInfoDAO.updateProductTacticsForFlag(productIds);
        if (i == 0) {
            message = "删除失败";
        }
        return message;
    }

    @Override
    public boolean checkProductName(Integer productId, String productName) {
        Map<String, Object> params = new HashMap<>(2);
        params.put("productId", productId);
        params.put("productName", productName);
        int count = productInfoDAO.checkProductName(params);
        if (count == 0) {
            return true;
        }
        return false;
    }
}