package com.jzfq.rms.product.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jzfq.rms.product.bean.TacticsRecord;
import com.jzfq.rms.product.bean.TacticsRecordExample;
import com.jzfq.rms.product.common.PageData;
import com.jzfq.rms.product.common.PageParam;
import com.jzfq.rms.product.constant.SystemConstants;
import com.jzfq.rms.product.enums.TacticsStateEnum;
import com.jzfq.rms.product.enums.TacticsSwitchEnum;
import com.jzfq.rms.product.exception.BusinessException;
import com.jzfq.rms.product.persistence.cache.redis.TacticsCacheWithRedis;
import com.jzfq.rms.product.persistence.dao.IProductInfoDAO;
import com.jzfq.rms.product.persistence.dao.ITacticsRecordDAO;
import com.jzfq.rms.product.service.IDictionaryService;
import com.jzfq.rms.product.service.ITacticsRecordService;
import com.jzfq.rms.product.utils.DateUtils;
import com.jzfq.rms.product.utils.GenerateUtil;
import com.jzfq.rms.product.utils.StringUtil;
import com.jzfq.rms.product.web.action.CustomerTypeAction;
import org.apache.commons.collections4.ListUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @author 大连桔子分期科技有限公司
 * @date 2017年10月08日 10:21:22
 */
public class TacticsRecordServiceImpl implements ITacticsRecordService {
    private static final Logger LOGGER = LoggerFactory.getLogger(TacticsRecordServiceImpl.class);

    @Autowired
    private ITacticsRecordDAO tacticsRecordDAO;
    @Autowired
    private TacticsCacheWithRedis tacticsCacheWithRedis;
    @Autowired
    private IProductInfoDAO productInfoDAO;
    @Autowired
    private IDictionaryService service;

    private static final String TACTICS_ID = "tactics_id";
    private static final String TACTICS_NO = "tactics_No";

    private final static Logger logger = LoggerFactory.getLogger(TacticsRecordServiceImpl.class);

    /**
     * 根据主键值获取实体
     *
     * @param tacticsID 主键值
     * @return 实体
     * @throws BusinessException 业务异常
     */
    @Override
    public TacticsRecord getByPK(String tacticsID) throws BusinessException {
        TacticsRecord dic = tacticsCacheWithRedis.getTacticsByTacticsID(tacticsID);
        if (dic == null) {
            dic = tacticsRecordDAO.selectByPrimaryKey(tacticsID);
            if (dic != null) {
                //将策略信息加入缓存
                tacticsCacheWithRedis.setTacticsCache(dic);
            }
        }
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
    public List<TacticsRecord> getAllByCondition(Map<String, Object> conditionMap) throws BusinessException {
        TacticsRecordExample example = new TacticsRecordExample();
        TacticsRecordExample.Criteria criteria = example.createCriteria();
        if (!StringUtil.isEmpty(conditionMap.get(TACTICS_ID))) {
            criteria.andTacticsIdEqualTo(Integer.valueOf(String.valueOf(conditionMap.get(TACTICS_ID))));
        }
        if (!StringUtil.isEmpty(conditionMap.get(TACTICS_NO))) {
            criteria.andTacticsNoEqualTo(String.valueOf(conditionMap.get(TACTICS_NO)));
        }
        criteria.andDeleteflagEqualTo((int) SystemConstants.DELETE_FLAG_0);
        example.setOrderByClause("tactics_id ASC");
        List<TacticsRecord> dicList = tacticsRecordDAO.selectByExampleWithBLOBs(example);
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
    public List<String> getTacticsIDList(Map<String, Object> conditionMap) throws BusinessException {

        List<TacticsRecord> dicList = getAllByCondition(conditionMap);

        if (dicList != null && dicList.size() > 0) {
            for (int i = 0; i < dicList.size(); i++) {
                TacticsRecord tacticsRecord = dicList.get(i);
                String tacticsId = String.valueOf(tacticsRecord.getTacticsId());
                String tacticsNo = String.valueOf(tacticsRecord.getTacticsNo());
                Map<String, List> tacticsNoMap = new HashMap();
                List list = new ArrayList();
                if (tacticsNoMap.containsKey(tacticsNo)) {
                    list = tacticsNoMap.get(tacticsNo);
                }
                list.add(tacticsId);
                tacticsNoMap.put(tacticsNo, list);
                tacticsCacheWithRedis.setCacheList("risk_tacticsNo_List_" + tacticsNo, list);
            }
        }

        return null;
    }

    /**
     * 策略开启
     * @param tacticsId
     * @return
     */
    @Override
    public String updateTacticsSwitch(boolean status,String tacticsId) throws BusinessException {

        TacticsRecord tacticsByTacticsID = tacticsCacheWithRedis.getTacticsByTacticsID(tacticsId);
        if (tacticsByTacticsID ==null){
            return null;
        }
        String message;
        TacticsRecord params = new TacticsRecord();
        logger.info("redis中的策略数据：>>>>>>>>>>>>>>>>>>>"+tacticsByTacticsID.toString());
        if (status){
            //更新redis中状态
            tacticsByTacticsID.setTacticsSwitchName("开启");
            tacticsByTacticsID.setTacticsSwitch("0");
            //更新数据库中状态
            params.setTacticsId(Integer.parseInt(tacticsId));
            params.setTacticsSwitchName("开启");
            params.setTacticsSwitch("0");
            //更新数据库中的自动通过开关为开启
            message = "开启";
        }else {
            //更新redis中状态
            tacticsByTacticsID.setTacticsSwitchName("关闭");
            tacticsByTacticsID.setTacticsSwitch("1");
            //更新数据库中状态
            params.setTacticsId(Integer.parseInt(tacticsId));
            params.setTacticsSwitchName("关闭");
            params.setTacticsSwitch("1");
            message = "关闭";
        }
        tacticsCacheWithRedis.setTacticsCache(tacticsByTacticsID);
        logger.info("tacticsSwitchName：>>>>>>>>>>>>>>>>>>>"+tacticsByTacticsID.getTacticsSwitchName());
        //更新数据库中的自动通过开关状态
        tacticsRecordDAO.updateByPrimaryKeySelective(params);
        return message;
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
        List<TacticsRecord> dicList = getAllByCondition(conditionMap);
        if (dicList != null && dicList.size() > 0) {
            size = dicList.size();
        }
        return size;
    }

    /**
     * 保存策略信息
     *
     * @param record 实体
     * @return 插入条数
     * @throws BusinessException 业务异常
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = BusinessException.class, isolation = Isolation.DEFAULT)
    public void insert(TacticsRecord record) throws BusinessException {
        record.setCreateTime(DateUtils.now());
        record.setUpdater(record.getCreator());
        record.setDeleteflag((int) SystemConstants.DELETE_FLAG_0);
        record.setTacticsStateName(TacticsStateEnum.getName(record.getTacticsState()));
        record.setTacticsSwitchName(TacticsSwitchEnum.getName(record.getTacticsSwitch()));
        Object ruleSets = record.getRuleSets();
        logger.info("规则集信息："+ruleSets);
        tacticsRecordDAO.insertSelective(record);
        List<String> list = new ArrayList<>();
        list.add(String.valueOf(record.getTacticsId()));
        tacticsCacheWithRedis.setCacheList("risk_tacticsNo_List_" + record.getTacticsNo(), list);
        //将策略信息加入缓存
        tacticsCacheWithRedis.setTacticsCache(record);
    }

    /**
     * 根据主键值获取启用状态的产品策略
     *
     * @param tacticsID 主键值
     * @return 实体
     * @throws BusinessException 业务异常
     */
    @Override
    public TacticsRecord getActiveProductTacticsByID(Integer tacticsID) throws BusinessException {
        LOGGER.info("#getActiveProductTacticsByID.param=" + tacticsID);
        TacticsRecord dic = new TacticsRecord();

        TacticsRecordExample example = new TacticsRecordExample();
        TacticsRecordExample.Criteria criteria = example.createCriteria();
        criteria.andTacticsIdEqualTo(tacticsID);
        criteria.andTacticsStateEqualTo(SystemConstants.TACTICS_STATE_1);
        criteria.andDeleteflagEqualTo((int) SystemConstants.DELETE_FLAG_0);
        List<TacticsRecord> dicList = tacticsRecordDAO.selectByExampleWithBLOBs(example);
        if (dicList != null && dicList.size() > 0) {
            dic = dicList.get(0);
        }
        LOGGER.info("#getActiveProductTacticsByID.end");
        return dic;
    }

    /**
     * 更新新的model中不为空的字段。
     *
     * @param record 实体
     * @return 更新条数
     * @throws BusinessException 业务异常
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = BusinessException.class, timeout = 1, isolation = Isolation.DEFAULT)
    public int updateByPrimaryKeySelective(TacticsRecord record) throws BusinessException {
        //开启状态
        String tacticsState = record.getTacticsState();
        //启用
        if (TacticsStateEnum.ENABLED.getCode().equals(tacticsState)) {
            record.setTacticsStateName(TacticsStateEnum.ENABLED.getName());
        }
        //停用
        if (TacticsStateEnum.DISABLED.getCode().equals(tacticsState)) {
            record.setTacticsStateName(TacticsStateEnum.DISABLED.getName());
        }
        record.setUpdateTime(DateUtils.now());
        tacticsCacheWithRedis.setTacticsCache(record);
        return tacticsRecordDAO.updateByPrimaryKeySelective(record);
    }

    /**
     * 查询策略分页数据
     *
     * @param record
     * @return
     */
    @Override
    public PageData<TacticsRecord> queryList(TacticsRecord record) {
        //删除flag（0：可用、1：已删除）
        record.setDeleteflag((int) SystemConstants.DELETE_FLAG_0);
        PageParam<TacticsRecord> page = new PageParam<>(record, record.getPageNum(), record.getNumPerPage());
        List<TacticsRecord> list = tacticsRecordDAO.queryList(page);
        return new PageData(page.getPageNo(), page.getPageSize(), page.getDataTotal(), list);
    }

    @Override
    public int updateTacticsState(List<Integer> tacticsIds, String tacticsState, String updater) {
        TacticsRecord record = new TacticsRecord();
        for (Integer tacticsId : tacticsIds) {
            record.setTacticsId(tacticsId);
            record.setTacticsState(tacticsState);
            record.setUpdater(updater);
            record.setUpdateTime(DateUtils.now());
            //将更新状态加入缓存
            tacticsCacheWithRedis.setTacticsCache(record);
        }
        return tacticsRecordDAO.updateTacticsState(tacticsIds, tacticsState, updater);
    }

    /**
     * 校验策略名称是否重复
     *
     * @param tacticsId
     * @param tacticsName
     * @return true:策略是可以使用；false策略名称重复，不可使用
     */
    @Override
    public boolean checkTacticsName(Integer tacticsId, String tacticsName) {
        Map<String, Object> params = new HashMap<>(2);
        params.put("tacticsId", tacticsId);
        params.put("tacticsName", tacticsName.trim());
        int count = tacticsRecordDAO.checkTacticsName(params);
        if (count == 0) {
            return true;
        }
        return false;
    }

    /**
     * 生成策略编号
     *
     * @param tacticsType
     * @return
     */
    @Override
    public String generateTacticsNo(Integer tacticsType) {
        String tacticsNo;
        String str = null;
        if (tacticsType == 0) {
            str = "DDCL";
            tacticsNo = productInfoDAO.getProductOrderNo();
        } else {
            switch (tacticsType) {
                case 1:
                    str = "ZRCL";
                    break;
                case 2:
                    str = "FKCL";
                    break;
                case 3:
                    str = "EDCL";
                    break;
                default:
                    break;
            }
            tacticsNo = tacticsRecordDAO.getTacticsNo(tacticsType);
        }

        if (!StringUtil.isEmpty(tacticsNo)) {
            String letter = tacticsNo.substring(0, 4);
            int length = tacticsNo.length();
            int no = Integer.parseInt(tacticsNo.substring(length - 6, length));
            tacticsNo = letter + String.format("%06d", no + 1);
        } else {
            tacticsNo = str + GenerateUtil.plus();
        }
        return tacticsNo;
    }

    /**
     * 分页查询规则集编号列表
     *
     * @param tacticsNo    规则集编号
     * @param name 规则集名称
     * @param numPerPage   每页显示多少条
     * @param pageNum      当前第几页
     * @return
     */
    @Override
    public PageData<Map<String, Object>> getSkipStepPage(String tacticsNo, String name, Integer pageNum, Integer numPerPage) {
        pageNum = pageNum == null ? 1 : pageNum;
        numPerPage = numPerPage == null ? 10 : numPerPage;
        String ruleParamNos = tacticsRecordDAO.getRuleParamNo(tacticsNo);
        JSONArray jsonArray = JSON.parseArray(ruleParamNos);
        List<Map<String, Object>> ruleSetsList = new ArrayList<>();
        List<Map<String, Object>> ruleSetsSearchName = new ArrayList<>();
        for (Object obj : jsonArray) {
            Map<String, Object> ruleSets = new HashMap<>();
            JSONObject jsonObject = (JSONObject) obj;
            String ruleSetsNo = jsonObject.getString("ruleSetsNo");
            String ruleSetsName = jsonObject.getString("ruleSetsName");
            ruleSets.put("ruleSetsNo", ruleSetsNo);
            ruleSets.put("ruleSetsName", ruleSetsName);
            ruleSetsList.add(ruleSets);
            if (!StringUtil.isEmpty(name) && ruleSetsName.contains(name)){
                ruleSets.clear();
                ruleSets.put("ruleSetsNo", ruleSetsNo);
                ruleSets.put("ruleSetsName", ruleSetsName);
                ruleSetsSearchName.add(ruleSets);
            }
        }
        if (ruleSetsSearchName !=null && ruleSetsSearchName.size() >0){
            listMapSort(ruleSetsSearchName);
            return PageData.pageList(ruleSetsSearchName, pageNum, numPerPage);
        }else {
            listMapSort(ruleSetsList);
            return PageData.pageList(ruleSetsList, pageNum, numPerPage);
        }
    }



    public void listMapSort(List<Map<String, Object>> list) {
        Collections.sort(list, (o1, o2) -> {
            //ruleSetsNo1是从你list里面拿出来的一个
            String ruleSetsNo1 = o1.get("ruleSetsNo").toString();
            //ruleSetsNo2是从你list里面拿出来的第二个ruleSetsNo2
            String ruleSetsNo2 = o2.get("ruleSetsNo").toString();
            int i = ruleSetsNo1.compareTo(ruleSetsNo2);
            return i;
        });
    }

}