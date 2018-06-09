package com.jzfq.rms.product.persistence.cache;

import com.jzfq.rms.product.bean.ProductInfo;
import com.jzfq.rms.product.bean.TacticsRecord;
import com.jzfq.rms.product.exception.BusinessException;

/**
 * @author 大连桔子分期科技有限公司
 * @date 2017年10月15日 20:04:55
 */
public interface IProductInfoCache {

    ProductInfo getProductInfoByProductOrderNo(String productOrderNo) throws BusinessException;

    void setProductInfoCache(ProductInfo record);

    void setProductIdCache(ProductInfo record);

    ProductInfo getProductInfoByProductID(Integer productId);

    void setProductInfoche(ProductInfo record);
}
