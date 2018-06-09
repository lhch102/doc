package com.jzfq.rms.product.persistence.cache;

import com.jzfq.rms.product.bean.CustomerType;
import com.jzfq.rms.product.bean.TacticsRecord;
import com.jzfq.rms.product.exception.BusinessException;
import org.springframework.data.redis.core.HashOperations;

import java.util.List;
import java.util.Map;

/**
 * @author 大连桔子分期科技有限公司
 * @date 2017年10月15日 20:04:55
 */
public interface ITacticsCache {
    TacticsRecord getTacticsByTacticsID(String tacticsID) throws BusinessException;

    void setTacticsCache(TacticsRecord record);

    void setTacticsCache(CustomerType customerType);

    CustomerType getTacticsCache(Integer customerTypeId);

    <T> List<T> getCacheList(String key);

    void setCacheString(String key, String value);

    String getCacheString(String key);
}
