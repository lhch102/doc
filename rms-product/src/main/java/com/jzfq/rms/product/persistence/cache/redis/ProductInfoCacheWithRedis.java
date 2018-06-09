package com.jzfq.rms.product.persistence.cache.redis;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.jzfq.rms.product.bean.ProductInfo;
import com.jzfq.rms.product.bean.TacticsRecord;
import com.jzfq.rms.product.exception.BusinessException;
import com.jzfq.rms.product.persistence.cache.IProductInfoCache;
import com.jzfq.rms.product.persistence.cache.ITacticsCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author 大连桔子分期科技有限公司
 * @date 2017年10月15日 20:04:55
 */
public class ProductInfoCacheWithRedis extends CacheWithRedis implements IProductInfoCache {

	private final Logger LOG = LoggerFactory.getLogger(ProductInfoCacheWithRedis.class);

	private static final String RISK_TACTICS_PREFIX = "risk_productInfo_prefix";

	@Override
	public ProductInfo getProductInfoByProductOrderNo(String productOrderNo) throws BusinessException {
		String key = RISK_TACTICS_PREFIX+"_"+productOrderNo;
		ProductInfo value = JSON.parseObject(getCacheString(key), new TypeReference<ProductInfo>() {});
		return value;

	}

	@Override
	public void setProductInfoCache(ProductInfo record) {
		String key = RISK_TACTICS_PREFIX+"_"+record.getProductOrderNo();
		String value = JSON.toJSONString(record);
		setCacheString(key,value);
	}

	@Override
	public void setProductIdCache(ProductInfo record) {
		String key = RISK_TACTICS_PREFIX+"_"+ record.getProductId();
		String value = JSON.toJSONString(record);
		setCacheString(key,value);
	}

	@Override
	public ProductInfo getProductInfoByProductID(Integer productId) {
		String key = RISK_TACTICS_PREFIX + "_" + productId;
		return JSON.parseObject(getCacheString(key),new TypeReference<ProductInfo>(){});
	}

	@Override
	public void setProductInfoche(ProductInfo productInfoche) {
		String key = RISK_TACTICS_PREFIX+"_"+productInfoche.getProductId();
		String value = JSON.toJSONString(productInfoche);
		setCacheString(key,value);
	}
}
