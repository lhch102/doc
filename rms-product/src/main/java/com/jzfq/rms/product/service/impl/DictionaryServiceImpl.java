package com.jzfq.rms.product.service.impl;

import com.alibaba.druid.support.json.JSONUtils;
import com.jzfq.rms.product.bean.Dictionary;
import com.jzfq.rms.product.bean.DictionaryExample;
import com.jzfq.rms.product.bean.TacticsRecord;
import com.jzfq.rms.product.common.PageData;
import com.jzfq.rms.product.common.PageParam;
import com.jzfq.rms.product.constant.SystemConstants;
import com.jzfq.rms.product.exception.BusinessException;
import com.jzfq.rms.product.persistence.cache.IDictionaryCache;
import com.jzfq.rms.product.persistence.dao.IDictionaryDAO;
import com.jzfq.rms.product.service.IDictionaryService;
import com.jzfq.rms.product.utils.DateUtils;
import com.jzfq.rms.product.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 大连桔子分期科技有限公司
 * @date 2017年10月08日 10:21:22
 */
public class DictionaryServiceImpl implements IDictionaryService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DictionaryServiceImpl.class);
    @Autowired
    private IDictionaryDAO dictionaryDAO;
    @Autowired
    private IDictionaryCache dictionaryCache;

    private static final String TYPE = "type";
    private static final String RMS_KEY = "rms_key";

    /**
     * 根据主键值获取实体
     *
     * @param dictionaryID 主键值
     * @return 实体
     * @throws BusinessException 业务异常
     */
    @Override
    public Dictionary getByPK(Integer dictionaryID) throws BusinessException {
        Dictionary dic = dictionaryDAO.selectByPrimaryKey(dictionaryID);
        return dic;
    }

    /**
     * 根据条件获取列表
     *
     * @param conditionMap 条件   多个条件可以封装成javabean或者map
     * @return 列表
     * @throws BusinessException 业务异常
     */
    @Override
    public List<Dictionary> getAllByCondition(Map<String, Object> conditionMap) throws BusinessException {
        LOGGER.info("#getAllByCondition start");
        List<Dictionary> dicList = new ArrayList<>();
        Dictionary dic = dictionaryCache.getDictionaryValueByCondition(conditionMap);
        if (StringUtil.isEmpty(dic)) {
            DictionaryExample example = new DictionaryExample();
            DictionaryExample.Criteria criteria = example.createCriteria();
            if (!StringUtil.isEmpty(conditionMap.get(TYPE))) {
                criteria.andTypeEqualTo(String.valueOf(conditionMap.get(TYPE)));
            }
            if (!StringUtil.isEmpty(conditionMap.get(RMS_KEY))) {
                criteria.andRms_keyEqualTo(String.valueOf(conditionMap.get(RMS_KEY)));
            }
            criteria.andDel_flagEqualTo(SystemConstants.DELETE_FLAG_0);

            dicList = dictionaryDAO.selectByExample(example);

        } else {
            dicList.add(dic);
        }
        LOGGER.info("#getAllByCondition end");
        return dicList;
    }

    /**
     * 根据条件获取列表
     *
     * @param conditionMap 条件   多个条件可以封装成javabean或者map
     * @return 列表
     * @throws BusinessException 业务异常
     */
    @Override
    public List<String> getvalueByCondition(Map<String, Object> conditionMap) throws BusinessException {
        LOGGER.info("#getvalueByCondition start");
        List<String> valueList = new ArrayList<>();
        List<Dictionary> dicList;
        String value = dictionaryCache.getDictionaryValue(conditionMap);
        if (StringUtil.isEmpty(value)) {
            DictionaryExample example = new DictionaryExample();
            DictionaryExample.Criteria criteria = example.createCriteria();
            criteria.andDel_flagEqualTo(SystemConstants.DELETE_FLAG_0);
            dicList = dictionaryDAO.selectByExample(example);
            if (dicList != null && dicList.size() > 0) {
                for (int i = 0; i < dicList.size(); i++) {
                    dictionaryCache.addDictionaryValue(dicList.get(i));
                    LOGGER.info("#getvalueByCondition addCacheDictionary");
                }
            }
        } else {
            valueList.add(value);
        }
        LOGGER.info("#getvalueByCondition end");
        return valueList;
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
        List<Dictionary> dicList = getAllByCondition(conditionMap);
        if (dicList != null && dicList.size() > 0) {
            size = dicList.size();
        }
        return size;
    }

    /**
     * 增加实体
     *
     * @param record 实体
     * @return 插入条数
     * @throws BusinessException 业务异常
     */
    @Override
    public int insert(Dictionary record) throws BusinessException {
        record.setCreate_time(DateUtils.now());
        record.setUpdate_time(DateUtils.now());
        record.setDel_flag(SystemConstants.DELETE_FLAG_0);
        int index = dictionaryDAO.insertSelective(record);
        //加入redis缓存
        dictionaryCache.addDictionary(record);
        return index;
    }

    @Override
    public List<Map<String, Object>> getDictionary(String type) throws BusinessException {
        LOGGER.info("#getDictionary start");
        List<Map<String, Object>> dicList;
        String dictionaryByType = dictionaryCache.getDictionaryByType(type);
        if (StringUtil.isEmpty(dictionaryByType) || "[]".equals(dictionaryByType)){
            DictionaryExample example = new DictionaryExample();
            DictionaryExample.Criteria criteria = example.createCriteria();
            criteria.andTypeEqualTo(type);
            criteria.andDel_flagEqualTo(SystemConstants.DELETE_FLAG_0);
            dicList = dictionaryDAO.getDictionary(example);
            dictionaryCache.addDictionaryByType(type, JSONUtils.toJSONString(dicList));
        }else {
            dicList = (List<Map<String, Object>>) JSONUtils.parse(dictionaryByType);
        }
        LOGGER.info("#getDictionary end");
        return dicList;
    }

    /**
     * 根据type获取字典value
     * @param key 字典key
     * @param type 字典type
     * @return
     * @throws BusinessException
     */
    @Override
    public String getValue(String key,String type) throws BusinessException {
        Map conditionMap = new HashMap();
        conditionMap.put("rms_key",key);
        conditionMap.put("type",type);
        String dictionaryValue = dictionaryCache.getDictionaryValue(conditionMap);
        if (StringUtil.isEmpty(dictionaryValue)){
            String value = dictionaryDAO.getValue(conditionMap);
            dictionaryCache.addDictionaryByType(type, value);
            return value;
        }
        return dictionaryValue;
    }

    /**
     * 分页查询字典信息
     *
     * @param type
     * @param dictionaryName
     * @return
     */
    @Override
    public PageData<Dictionary> queryList(String type, String dictionaryName) {
        Dictionary dictionary = new Dictionary();
        //删除flag（0：可用、1：已删除）
        dictionary.setDel_flag(SystemConstants.DELETE_FLAG_0);
        dictionary.setType(type);
        dictionary.setRms_value(dictionaryName);
        PageParam<Dictionary> page = new PageParam<>(dictionary, dictionary.getPageNum(), dictionary.getNumPerPage());
        List<Map<String,Object>> list = dictionaryDAO.queryList(page);
        return new PageData(page.getPageNo(), page.getPageSize(), page.getDataTotal(), list);
    }

}