package com.jzfq.rms.product.service;

import com.jzfq.rms.product.bean.TacticsRecord;
import com.jzfq.rms.product.common.PageData;
import com.jzfq.rms.product.exception.BusinessException;

import java.util.List;
import java.util.Map;

/**
 * @author 大连桔子分期科技有限公司
 * @date 2017年10月15日 22:23:15
 */
public interface ITacticsRecordService {
    TacticsRecord getByPK(String tacticsID) throws BusinessException;

    List<TacticsRecord> getAllByCondition(Map<String, Object> conditionMap) throws BusinessException;

    List<String> getTacticsIDList(Map<String, Object> conditionMap) throws BusinessException;

    long getCountByCondition(Map conditionMap) throws BusinessException;

    void insert(TacticsRecord record) throws BusinessException;

    TacticsRecord getActiveProductTacticsByID(Integer tacticsID) throws BusinessException;

    int updateByPrimaryKeySelective(TacticsRecord record)throws BusinessException;

    /**
     * 查询策略分页数据
     * @param record
     * @return
     */
    PageData<TacticsRecord> queryList(TacticsRecord record);

    /**
     * 修改策略状态
     * @param tacticsIds    策略ID集合
     * @param tacticsState  状态
     * @param updater       修改人
     * @return
     */
    int updateTacticsState(List<Integer> tacticsIds, String tacticsState, String updater);

    /**
     * 校验策略名称是否重复
     * @param tacticsId
     * @param tacticsName
     */
    boolean checkTacticsName(Integer tacticsId,String tacticsName);

    String generateTacticsNo(Integer tacticsType);

    /**
     * 分页查询规则集编号列表
     * @param tacticsNo     规则集编号
     * @param ruleSetsName  规则集名称
     * @param numPerPage    每页显示多少条
     * @param pageNum       当前第几页
     * @return
     */
    PageData<Map<String,Object>> getSkipStepPage(String tacticsNo,String ruleSetsName,Integer pageNum,Integer numPerPage);

    String updateTacticsSwitch(boolean status,String tacticsId) throws BusinessException;
}
