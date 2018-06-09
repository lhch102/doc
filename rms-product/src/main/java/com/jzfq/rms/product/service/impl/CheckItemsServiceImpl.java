package com.jzfq.rms.product.service.impl;

import com.jzfq.rms.product.bean.CheckItems;
import com.jzfq.rms.product.bean.CheckItemsExample;
import com.jzfq.rms.product.constant.SystemConstants;
import com.jzfq.rms.product.exception.BusinessException;
import com.jzfq.rms.product.persistence.dao.ICheckItemsDAO;
import com.jzfq.rms.product.service.ICheckItemsService;
import com.jzfq.rms.product.utils.DateUtils;
import com.jzfq.rms.product.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * @author 大连桔子分期科技有限公司
 * @date 2017年10月08日 10:21:22
 */
public class CheckItemsServiceImpl implements ICheckItemsService {
    @Autowired
    private ICheckItemsDAO checkItemsDAO;
//    @Autowired
//    private DictionaryCacheWithRedis cacheWithRedis;
    /**
     * 根据主键值获取实体
     * @param check_item_id 主键值
     * @return 实体
     * @throws BusinessException 业务异常
     */
    @Override
    public CheckItems getByPK(Integer check_item_id) throws BusinessException {
        CheckItems dic = checkItemsDAO.selectByPrimaryKey(check_item_id);
        return dic;
    }

    /**
     * 根据条件获取列表
     * @param conditionMap 条件   多个条件可以封装成javabean或者map
     * @return 列表
     * @throws BusinessException 业务异常
     */
    @Override
    public List<CheckItems> getAllByCondition(Map<String, Object> conditionMap) throws BusinessException{
        CheckItemsExample example = new CheckItemsExample();
        CheckItemsExample.Criteria criteria= example.createCriteria();
        if(!StringUtil.isEmpty(conditionMap.get("checkItems"))) {
            criteria.andCheckItemsEqualTo(Integer.valueOf(String.valueOf(conditionMap.get("checkItems"))));
        }
        criteria.andDeleteflagEqualTo((int)SystemConstants.DELETE_FLAG_0);
        List<CheckItems> dicList= checkItemsDAO.selectByExample(example);
        return dicList;
    }

    /**
     * 根据条件获取总条数
     * @param conditionMap 条件   多个条件可以封装成javabean或者map
     * @return 总条数
     * @throws BusinessException 业务异常
     */
    @Override
    public long getCountByCondition(Map conditionMap) throws BusinessException{
        int size =0;
        List<CheckItems> dicList= getAllByCondition(conditionMap);
        if(dicList!=null && dicList.size()>0){
            size = dicList.size();
        }
        return size;
    }

    /**
     * 增加实体
     * @param record 实体
     * @return 插入条数
     * @throws BusinessException 业务异常
     */
    @Override
    public int insert(CheckItems record) throws BusinessException{
        record.setCreateTime(DateUtils.now());
        record.setDeleteflag((int)SystemConstants.DELETE_FLAG_0);
        int index = checkItemsDAO.insertSelective(record);
        //加入redis缓存
//        cacheWithRedis.addDictionary(record);
        return index;
    }

}