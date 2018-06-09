package com.jzfq.rms.product.service.impl;

import com.jzfq.rms.product.bean.ConfigMobile;
import com.jzfq.rms.product.bean.CustomerType;
import com.jzfq.rms.product.bean.LatitudeLongitude;
import com.jzfq.rms.product.bean.RegistCode;
import com.jzfq.rms.product.common.PageData;
import com.jzfq.rms.product.common.PageParam;
import com.jzfq.rms.product.constant.SystemConstants;
import com.jzfq.rms.product.enums.TacticsStateEnum;
import com.jzfq.rms.product.exception.BusinessException;
import com.jzfq.rms.product.persistence.cache.redis.TacticsCacheWithRedis;
import com.jzfq.rms.product.persistence.dao.ConfigMobileDao;
import com.jzfq.rms.product.persistence.dao.CustomerTypeDao;
import com.jzfq.rms.product.persistence.dao.LatitudeLongitudeDao;
import com.jzfq.rms.product.persistence.dao.RegistCodeDao;
import com.jzfq.rms.product.service.CustomerTypeService;
import com.jzfq.rms.product.service.IDictionaryService;
import com.jzfq.rms.product.utils.DateUtils;
import com.jzfq.rms.product.utils.GenerateUtil;
import com.jzfq.rms.product.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author 大连桔子分期科技有限公司
 * @date 2018/4/9 16:18
 */
public class CustomerTypeServiceImpl implements CustomerTypeService {

    @Autowired
    private CustomerTypeDao customerTypeDao;
    @Autowired
    private ConfigMobileDao configMobileDao;
    @Autowired
    private RegistCodeDao registCodeDao;
    @Autowired
    private LatitudeLongitudeDao latitudeLongitudeDao;
    @Autowired
    private TacticsCacheWithRedis tacticsCacheWithRedis;
    @Autowired
    private IDictionaryService dictionaryService;

    private static final String RISK_KQ_CHANNEL_LIST_ = "risk_KQ_channel_List_";
    private static final String risk_tacticskq_list_ = "risk_tacticsKQ_List_";
    /**
     * 获取所有客群策略
     *
     * @return
     * @throws BusinessException
     */
    @Override
    public int addRedisKQID(Map<String, Object> param) throws BusinessException {
        List<String> channelType = customerTypeDao.getChannelType();
        param.put("orderBy", "customer_level desc");
        for (String channelId : channelType) {
            param.put("channelId", channelId);
            List<CustomerType> allByCondition = getAllByCondition(param);
            for (CustomerType customerType : allByCondition) {
                String tacticsKQId = customerType.getTacticskqId();
                Map<String, List> tacticskqMap = new HashMap();
                List list = new ArrayList();
                if (tacticskqMap.containsKey(channelId)) {
                    list = tacticskqMap.get(channelId);
                }
                list.add(tacticsKQId);
                tacticskqMap.put(channelId, list);
                tacticsCacheWithRedis.setCacheList(RISK_KQ_CHANNEL_LIST_ + channelId, list);
            }
        }

        param.clear();
        param.put("orderBy", "customer_type_id asc");
        List<CustomerType> allByCondition = getAllByCondition(param);
        if (allByCondition != null) {
            int size = allByCondition.size();
            if (size > 0) {
                for (int i = 0; i < size; i++) {
                    CustomerType customerType = allByCondition.get(i);
                    String customerTypeId = customerType.getCustomerTypeId().toString();
                    String tacticsKQId = customerType.getTacticskqId();
                    Map<String, List> tacticskqMap = new HashMap();
                    List list = new ArrayList();
                    if (tacticskqMap.containsKey(tacticsKQId)) {
                        list = tacticskqMap.get(tacticsKQId);
                    }
                    list.add(customerTypeId);
                    tacticskqMap.put(tacticsKQId, list);
                    tacticsCacheWithRedis.setCacheList("risk_tacticsKQ_List_" + tacticsKQId, list);
                }
                return size;
            }
        }
        return 0;
    }

    /**
     * 将客群策略添加至缓存
     *
     * @return
     * @throws BusinessException
     */
    @Override
    public int addTacticsKQ() throws BusinessException {
        Map param = new HashMap<>(1);
        param.put("orderBy", "null");
        List<CustomerType> allByCondition = getAllByCondition(param);
        if (allByCondition != null) {
            int size = allByCondition.size();
            if (size > 0) {
                for (CustomerType customerType : allByCondition) {
                    String tacticsKQId = customerType.getTacticskqId();
                    List<ConfigMobile> configMobiles = configMobileDao.selectByPrimaryKey(tacticsKQId);
                    List<RegistCode> registCodes = registCodeDao.selectByPrimaryKey(tacticsKQId);
                    List<LatitudeLongitude> latitudeLongitudes = latitudeLongitudeDao.selectByPrimaryKey(tacticsKQId);
                    customerType.setConfigMobileList(configMobiles);
                    customerType.setRegistCodeList(registCodes);
                    customerType.setLatitudeLongitudeList(latitudeLongitudes);
                    tacticsCacheWithRedis.setTacticsCache(customerType);
                }
                return size;
            }
        }
        return 0;
    }

    /**
     * 分页查询客群信息
     *
     * @param record
     * @return
     */
    @Override
    public PageData<CustomerType> queryList(CustomerType record) {
        record.setDeleteFlag(String.valueOf(SystemConstants.DELETE_FLAG_0));
        PageParam<CustomerType> page = new PageParam<>(record, record.getPageNum(), record.getNumPerPage());
        List<CustomerType> list = customerTypeDao.queryList(page);
        return new PageData(page.getPageNo(), page.getPageSize(), page.getDataTotal(), list);
    }

    /**
     * 添加客群加载默认项
     *
     * @return
     */
    @Override
    public Map<String, Object> loadTacticsKQ() throws BusinessException {
        Map<String, Object> tacticsKQ = new HashMap<>(2);
        tacticsKQ.put("tacticsKQID", generateTacticsKQNo());
        //获取渠道名称字典项
        List<Map<String, Object>> channelNameList = dictionaryService.getDictionary("jd_channel_name");
        tacticsKQ.put("channelNameList", channelNameList);
        //启用标识
        tacticsKQ.put("statusNameList", getstatusNameList());
        return tacticsKQ;
    }

    /**
     * 校验客群名称是否重复
     *
     * @param tacticskqId
     * @param customerTypeName
     * @return true:可是使用;false:名称重复，不能使用
     */
    @Override
    public boolean checkTacticsKQName(String tacticskqId, String customerTypeName) {
        Map<String, Object> params = new HashMap<>(2);
        params.put("customerTypeName", customerTypeName);
        if (!StringUtil.isEmpty(tacticskqId)) {
            params.put("tacticskqId", tacticskqId.trim());
        }
        int count = customerTypeDao.checkTacticsKQName(params);
        if (count == 0) {
            return true;
        }
        return false;
    }

    /**
     * 客群策略编辑
     * @param customerTypeId
     * @return
     */
    @Override
    public CustomerType editTacticsKQ(Integer customerTypeId) throws BusinessException {
        CustomerType customerType = customerTypeDao.selectByPrimaryKey(customerTypeId);
        if (customerType == null) {
            return null;
        }
        String tacticskqId = customerType.getTacticskqId();

        ConfigMobile mobile = new ConfigMobile();
        mobile.setTacticskqId(tacticskqId);
        //分页查询配置手机列表
        PageData<ConfigMobile> mobilesPageData = queryMobilePage(mobile);

        RegistCode registCode = new RegistCode();
        registCode.setTacticskqId(tacticskqId);
        PageData<RegistCode> registCodeData = queryRegistCodePage(registCode);

        LatitudeLongitude latitudeLongitude = new LatitudeLongitude();
        latitudeLongitude.setTacticskqId(tacticskqId);
        PageData<LatitudeLongitude> latitudeLongitudesData = querylatitudeLongitudePage(latitudeLongitude);

        customerType.setConfigMobilePage(mobilesPageData);
        customerType.setRegistCodePage(registCodeData);
        customerType.setLatitudeLongitudePage(latitudeLongitudesData);
        customerType.setChannelNameList(dictionaryService.getDictionary("jd_channel_name"));
        customerType.setStatusNameList(getstatusNameList());
        return customerType;
    }

    /**
     * 添加配置手机号
     * @param configMobile
     */
    @Override
    public void addMobile(ConfigMobile configMobile) {
        configMobileDao.insertSelective(configMobile);
    }

    /**
     * 移除手机号/F码/经纬度
     * @param type  0:移除手机号;1:移除F码;2:移除经纬度
     * @param ids
     */
    @Override
    public int removeObject(Integer type, List<Integer> ids) {
        switch (type) {
            case 0:
                // 0:移除手机号;
                return configMobileDao.deleteByPrimaryKey(ids);
            case 1:
                // 1:移除F码;
                return registCodeDao.deleteByPrimaryKey(ids);
            case 2:
                //2:移除经纬度
                return latitudeLongitudeDao.deleteByPrimaryKey(ids);
            default:
                return 0;
        }

    }

    /**
     * 更新有效期
     *
     * @param type           0:更新手机号有效期;1:更新F码有效期;2:更新经纬度有效期
     * @param ids
     * @param validityPeriod
     */
    @Override
    public int updateValidityPeriod(Integer type, List<Integer> ids, String validityPeriod) throws BusinessException, ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date validityPeriodDate = sdf.parse(validityPeriod);
        switch (type) {
            case 0:
                // 0:更新手机号有效期;
                ConfigMobile mobile = new ConfigMobile();
                mobile.setIds(ids);
                mobile.setValidityPeriod(validityPeriodDate);
                return configMobileDao.updateByPrimaryKeySelective(mobile);
            case 1:
                // 1:更新F码有效期;
                RegistCode code = new RegistCode();
                code.setIds(ids);
                code.setValidityPeriod(validityPeriodDate);
                return registCodeDao.updateByPrimaryKeySelective(code);
            case 2:
                //更新经纬度有效期
                LatitudeLongitude latitudeLongitude = new LatitudeLongitude();
                latitudeLongitude.setIds(ids);
                latitudeLongitude.setValidityPeriod(validityPeriodDate);
                return latitudeLongitudeDao.updateByPrimaryKeySelective(latitudeLongitude);
            default:
                return 0;
        }
    }

    @Override
    @Transactional(rollbackFor = BusinessException.class)
    public void addRegistCode(RegistCode registCode) {
        registCodeDao.insertSelective(registCode);
    }

    /**
     * 添加经纬度
     *
     * @param latitudeLongitude
     */
    @Override
    @Transactional(rollbackFor = BusinessException.class)
    public void addLatitudeLongitude(LatitudeLongitude latitudeLongitude) {
        latitudeLongitudeDao.insertSelective(latitudeLongitude);
    }

    /**
     * 获取所有客群信息
     *
     * @param param
     * @return
     * @throws BusinessException
     */
    public List<CustomerType> getAllByCondition(Map<String, Object> param) throws BusinessException {
        return customerTypeDao.tacticsKQID(param);
    }

    public String generateTacticsKQNo() {
        String tacticsKQNo = customerTypeDao.getTacticsKQNo();
        if (!StringUtil.isEmpty(tacticsKQNo)) {
            String letter = tacticsKQNo.substring(0, 4);
            int length = tacticsKQNo.length();
            int no = Integer.parseInt(tacticsKQNo.substring(length - 6, length));
            tacticsKQNo = letter + String.format("%06d", no + 1);
        } else {
            tacticsKQNo = "KQCL" + GenerateUtil.plus();
        }
        return tacticsKQNo;
    }

    /**
     * 获取启用状态
     *
     * @return
     */
    public static List<Map<String, Object>> getstatusNameList() {
        List<Map<String, Object>> dictList = new ArrayList<>();
        Map<String, Object> dict = new LinkedHashMap<>();
        dict.put("rms_key", TacticsStateEnum.ENABLED.getCode());
        dict.put("rms_value", TacticsStateEnum.ENABLED.getName());
        dictList.add(dict);
        dict = new LinkedHashMap<>();
        dict.put("rms_key", TacticsStateEnum.DISABLED.getCode());
        dict.put("rms_value", TacticsStateEnum.DISABLED.getName());
        dictList.add(dict);
        return dictList;
    }

    /**
     * 分页查询配置手机列表
     * @param mobile
     * @return
     */
    @Override
    public PageData<ConfigMobile> queryMobilePage(ConfigMobile mobile) {

        PageParam<ConfigMobile> mobilePageParam = new PageParam<>(mobile, mobile.getPageNum(), mobile.getNumPerPage());
        //分页查询手机号列表
        List<ConfigMobile> configMobiles = configMobileDao.queryList(mobilePageParam);
        PageData<ConfigMobile> mobilesPageData = new PageData(mobilePageParam.getPageNo(), mobilePageParam.getPageSize(), mobilePageParam.getDataTotal(), configMobiles);
        return mobilesPageData;
    }

    /**
     * 分页查询F码列表
     * @param registCode
     * @return
     */
    @Override
    public PageData<RegistCode> queryRegistCodePage(RegistCode registCode) {
        PageParam<RegistCode> registCodePageParam = new PageParam<>(registCode, registCode.getPageNum(), registCode.getNumPerPage());
        //分页查询F码列表
        List<RegistCode> registCodes = registCodeDao.queryList(registCodePageParam);
        PageData<RegistCode> registCodeData = new PageData(registCodePageParam.getPageNo(), registCodePageParam.getPageSize(), registCodePageParam.getDataTotal(), registCodes);
        return registCodeData;
    }

    /**
     * 分页查询经纬度列表
     * @param latitudeLongitude
     * @return
     */
    @Override
    public PageData<LatitudeLongitude> querylatitudeLongitudePage(LatitudeLongitude latitudeLongitude) {
        PageParam<LatitudeLongitude> latitudeLongitudePageParam = new PageParam<>(latitudeLongitude, latitudeLongitude.getPageNum(), latitudeLongitude.getNumPerPage());
        //分页查询经纬度列表
        List<LatitudeLongitude> latitudeLongitudes = latitudeLongitudeDao.queryList(latitudeLongitudePageParam);
        PageData<LatitudeLongitude> latitudeLongitudesData = new PageData(latitudeLongitudePageParam.getPageNo(), latitudeLongitudePageParam.getPageSize(), latitudeLongitudePageParam.getDataTotal(), latitudeLongitudes);
        return latitudeLongitudesData;
    }

    /**
     * 保存客群策略信息
     * @param customerType
     * @return
     */
    @Override
    public int saveTacticsKQ(CustomerType customerType) {
        customerType.setDeleteFlag(String.valueOf(SystemConstants.DELETE_FLAG_0));
        customerType.setCreateUser(customerType.getUpdateUser());
        customerType.setCreateTime(DateUtils.now());
        int i = customerTypeDao.insertSelective(customerType);
        String tacticsKQId = customerType.getTacticskqId();
        List<String> list = new ArrayList<>();
        list.add(tacticsKQId);
        tacticsCacheWithRedis.setCacheList(RISK_KQ_CHANNEL_LIST_ + customerType.getChannelId(), list);
        list.clear();
        list.add(String.valueOf(customerType.getCustomerTypeId()));
        tacticsCacheWithRedis.setCacheList(risk_tacticskq_list_ + tacticsKQId, list);
        List<ConfigMobile> configMobiles = configMobileDao.selectByPrimaryKey(tacticsKQId);
        List<RegistCode> registCodes = registCodeDao.selectByPrimaryKey(tacticsKQId);
        List<LatitudeLongitude> latitudeLongitudes = latitudeLongitudeDao.selectByPrimaryKey(tacticsKQId);
        customerType.setConfigMobileList(configMobiles);
        customerType.setRegistCodeList(registCodes);
        customerType.setLatitudeLongitudeList(latitudeLongitudes);
        //将策略信息加入缓存
        tacticsCacheWithRedis.setTacticsCache(customerType);
        return i;
    }
}
