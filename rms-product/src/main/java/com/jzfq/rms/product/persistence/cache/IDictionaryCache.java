package com.jzfq.rms.product.persistence.cache;

import com.jzfq.rms.product.bean.Dictionary;
import com.jzfq.rms.product.exception.BusinessException;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * @author 大连桔子分期科技有限公司
 * @date 2017年10月15日 20:04:55
 */
public interface IDictionaryCache {
    Dictionary getDictionaryValueByCondition(Map conditionMap) throws BusinessException;

    void addDictionary(Dictionary record);

    String getDictionaryValue(Map conditionMap) throws BusinessException;

    void addDictionaryValue(Dictionary record);

    /**
     * 将字典json加入缓存
     * @param type
     * @param value
     */
    void addDictionaryByType(String type, @Param("value") String value);

    /**
     * 根据type查询字典数据
     * @param type
     * @return
     */
    String getDictionaryByType(@Param("type") String type);

}
