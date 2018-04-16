package com.jzfq.rms.account.web.action;

import com.alibaba.fastjson.JSONObject;
import com.jzfq.rms.account.bean.AccountDic;
import com.jzfq.rms.account.bean.AccountDicType;
import com.jzfq.rms.account.bean.AccountUser;
import com.jzfq.rms.account.bean.ResponseResult;
import com.jzfq.rms.account.common.LoginCommon;
import com.jzfq.rms.account.common.PageData;
import com.jzfq.rms.account.common.service.GenerateObjectNoService;
import com.jzfq.rms.account.constant.ResponseCode;
import com.jzfq.rms.account.enums.EnumEnableFlag;
import com.jzfq.rms.account.enums.EnumModelType;
import com.jzfq.rms.account.enums.ReturnCode;
import com.jzfq.rms.account.service.DictionaryService;
import com.jzfq.rms.account.service.DictionaryTypeService;
import com.jzfq.rms.account.utils.CommonBeanUtils;
import com.jzfq.rms.account.utils.ElseFiledsUtils;
import com.jzfq.rms.account.web.BeanValidators.BeanValidators;
import com.jzfq.rms.account.web.requestModel.AccountDicRequestModel;
import com.jzfq.rms.account.web.requestModel.DictionaryRequestModel;
import com.jzfq.rms.account.web.responseModel.InitResponseModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author 大连桔子分期科技有限公司
 * @date 2018/4/3 11:43
 */
@RestController
@RequestMapping(value = "/dic")
public class DictionaryAction {

    private final static Logger logger = LoggerFactory.getLogger(DictionaryAction.class);

    @Autowired
    DictionaryService dictionaryService;
    @Autowired
    GenerateObjectNoService generateObjectNoService;
    @Autowired
    DictionaryTypeService dictionaryTypeService;
    @Autowired
    LoginCommon loginCommon;


    /**
     * 分页查询字典列表
     *
     * @param dic
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public ResponseResult list(@RequestBody AccountDicType dic, HttpServletRequest request, HttpServletResponse response) throws Exception {
        logger.info("获取分页查询字典列表接口，参数信息：" + JSONObject.toJSONString(dic));
        PageData<AccountDicType> data = dictionaryTypeService.list(dic);
        if (null != data) {
            logger.info("获取分页查询字典列表成功，返回结果：" + JSONObject.toJSONString(data));
            return new ResponseResult(ResponseCode.REQUEST_SUCCESS, ReturnCode.ACTIVE_SUCCESS.msg(), data);
        }
        logger.info("获取分页查询字典列表成功，暂无字典数据！");
        return new ResponseResult(ResponseCode.REQUEST_SUCCESS, ReturnCode.ERROR_NO_DIC_DATA.msg(), null);

    }

    /**
     * 分页查询字典键值列表
     *
     * @param dic
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/queryKeyList", method = RequestMethod.POST)
    public ResponseResult queryKeyList(@RequestBody AccountDic dic, HttpServletRequest request, HttpServletResponse response) throws Exception {
        logger.info("获取分页查询字典列表接口，参数信息：" + JSONObject.toJSONString(dic));
        //校验参数信息
        if (null != BeanValidators.isValidateRequestList(dic)) {
            logger.info("获取分页查询字典列表接口，参数信息校验失败：" + JSONObject.toJSONString(BeanValidators.isValidateRequestList(dic)));
            return BeanValidators.isValidateRequestList(dic);
        }
        PageData<AccountDic> data = dictionaryService.list(dic);
        if (null != data) {
            logger.info("获取分页查询字典列表成功，返回结果：" + JSONObject.toJSONString(data));
            return new ResponseResult(ResponseCode.REQUEST_SUCCESS, ReturnCode.ACTIVE_SUCCESS.msg(), data);
        }
        logger.info("获取分页查询字典列表成功，暂无字典数据！");
        return new ResponseResult(ResponseCode.REQUEST_SUCCESS, ReturnCode.ERROR_NO_DIC_DATA.msg(), null);

    }

    /**
     * 初始化字典接口
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/init", method = RequestMethod.POST)
    public ResponseResult init(HttpServletRequest request, HttpServletResponse response) throws Exception {
        AccountUser accountUser = loginCommon.getCurrentUser();
        //创建人
        String createBy = "";
        if (null != accountUser) {
            createBy = accountUser.getLoginName();
        }
        //字典编号
        String debtNo = generateObjectNoService.generateDeptNo();
        logger.info("初始化字典成功，初始化数据：" + new InitResponseModel(createBy, debtNo).toString());
        return new ResponseResult(ResponseCode.REQUEST_SUCCESS, ReturnCode.ACTIVE_SUCCESS.msg(), new InitResponseModel(createBy, debtNo));
    }

    /**
     * 根据字典类型查询字典信息不是分页
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/queryDicList", method = RequestMethod.POST)
    public ResponseResult queryDicList(HttpServletRequest request, HttpServletResponse response, @RequestBody AccountDic dic)
            throws Exception {
        logger.info("根据字典类型查询字典信息接口，参数信息：" + JSONObject.toJSONString(dic));
        //校验参数信息
        if (null != BeanValidators.isValidateRequestList(dic)) {
            logger.info("根据字典类型查询字典信息接口，参数信息校验失败：" + JSONObject.toJSONString(BeanValidators.isValidateRequestList(dic)));
            return BeanValidators.isValidateRequestList(dic);
        }
        List<AccountDic> list = dictionaryService.queryDicListByType(dic.getType());
        if (null != list && list.size() > 0) {
            logger.info("根据字典类型查询字典信息成功，返回结果：" + JSONObject.toJSONString(list));
            return new ResponseResult(ResponseCode.REQUEST_SUCCESS, ReturnCode.ACTIVE_SUCCESS.msg(), list);
        }
        logger.info("根据字典类型查询字典信息失败，返回结果：null");
        return new ResponseResult(ResponseCode.REQUEST_SUCCESS, ReturnCode.ERROR_NO_DIC_DATA.msg(), null);
    }

    /**
     * 启用/停用字典
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/isUsing", method = RequestMethod.POST)
    public ResponseResult isUsing(HttpServletRequest request, HttpServletResponse response,
                                  @RequestBody DictionaryRequestModel dic)
            throws Exception {
        logger.info("根据字典id更新启用/停用字典信息接口，参数信息：" + JSONObject.toJSONString(dic));
        //校验参数信息
        if (null != BeanValidators.isValidateRequestIsUsing(dic)) {
            logger.info("根据字典id更新启用/停用字典信息接口，参数信息校验失败：" + JSONObject.toJSONString(BeanValidators.isValidateRequestIsUsing(dic)));
            return BeanValidators.isValidateRequestIsUsing(dic);
        }
        int num = dictionaryService.isUsing(dic.getType(), dic.getIds());
        if (num > 0) {
            logger.info("根据字典id更新" + EnumEnableFlag.getEnum(dic.getType()).getMessage() + "字典成功，返回结果：" + num);
            return new ResponseResult(ResponseCode.REQUEST_SUCCESS, ReturnCode.ACTIVE_SUCCESS.msg(), null);
        }
        logger.info("根据字典id更新" + EnumEnableFlag.getEnum(dic.getType()).getMessage() + "字典失败，返回结果：0");
        return new ResponseResult(ResponseCode.REQUEST_SUCCESS, ReturnCode.ACTIVE_FAILURE.msg(), null);
    }


    /**
     * 校验字典名称是否存在
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/checkDic", method = RequestMethod.POST)
    public ResponseResult checkDic(HttpServletRequest request, HttpServletResponse response,
                                   @RequestBody DictionaryRequestModel dic)
            throws Exception {
        logger.info("根据字典id，字典名称校验该字典信息是否存在接口，参数信息：" + JSONObject.toJSONString(dic));
        //校验参数信息
        if (null != BeanValidators.isValidateRequestCheckDic(dic)) {
            logger.info("根据字典id，字典名称校验该字典信息是否存在接口，参数信息校验失败：" + JSONObject.toJSONString(BeanValidators.isValidateRequestCheckDic(dic)));
            return BeanValidators.isValidateRequestCheckDic(dic);
        }
        boolean flag = dictionaryTypeService.checkTypeName(dic.getTypeName(), dic.getId());
        if (flag) {
            logger.info("该字典名称" + dic.getTypeName() + "已经存在！");
            return new ResponseResult(ResponseCode.REQUEST_EXIST_DICNAME, ReturnCode.ERROR_EXIST_DICNAME.msg(), null);
        }
        logger.info("该字典名称" + dic.getTypeName() + "可以使用！");
        return new ResponseResult(ResponseCode.REQUEST_SUCCESS, ReturnCode.ACTIVE_SUCCESS.msg(), null);
    }

    /**
     * 根据字典id查询字典信息
     *
     * @param dic
     * @return
     */
    @RequestMapping(value = "/queryDicById", method = RequestMethod.POST)
    public ResponseResult queryDicById(HttpServletRequest request, HttpServletResponse response,
                                       @RequestBody DictionaryRequestModel dic) {
        logger.info("根据字典id查询字典信息接口，参数信息：" + JSONObject.toJSONString(dic));
        //校验参数信息
        if (null != BeanValidators.isValidateRequestId(dic)) {
            logger.info("根据字典id查询字典信息接口，参数信息校验失败：" + JSONObject.toJSONString(BeanValidators.isValidateRequestId(dic)));
            return BeanValidators.isValidateRequestId(dic);
        }
        AccountDicType data = dictionaryTypeService.queryModel(dic.getId());
        if (null != data) {
            logger.info("根据字典id查询字典信息接口成功，返回结果：" + JSONObject.toJSONString(data));
            return new ResponseResult(ResponseCode.REQUEST_SUCCESS, ReturnCode.ACTIVE_SUCCESS.msg(), data);
        }
        logger.info("根据字典id查询字典信息接口成功，暂无字典数据！");
        return new ResponseResult(ResponseCode.REQUEST_SUCCESS, ReturnCode.ERROR_NO_DIC_DATA.msg(), null);

    }

    /**
     * 保存字典
     *
     * @param dic
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseResult save(HttpServletRequest request, HttpServletResponse response,
                               @RequestBody DictionaryRequestModel dic) {
        logger.info("保存字典类型信息接口，参数信息：" + JSONObject.toJSONString(dic));
        //校验参数信息
        if (null != BeanValidators.isValidateRequestSave(dic)) {
            logger.info("保存字典类型信息接口，参数信息校验失败：" + JSONObject.toJSONString(BeanValidators.isValidateRequestSave(dic)));
            return BeanValidators.isValidateRequestSave(dic);
        }
        //保存字典类型
        int countDicType = dictionaryTypeService.save(dic);
        logger.info("保存字典键值信息接口，参数信息：" + JSONObject.toJSONString(dic.getDataKeyList()));
        //保存键值
        int countDic = dictionaryService.save(dic.getDataKeyList(), dic.getType());
        logger.info("保存字典信息成功，保存数量：" + countDicType + ",保存字典兼职信息成功，保存数量：" + countDic);
        return new ResponseResult(ResponseCode.REQUEST_SUCCESS, ReturnCode.ACTIVE_SUCCESS.msg(), null);
    }


    /**
     * 编辑字典键值根据字典键id
     *
     * @param dic
     * @return
     */
    @RequestMapping(value = "/updateDicKey", method = RequestMethod.POST)
    public ResponseResult updateDicKey(HttpServletRequest request, HttpServletResponse response,
                                       @RequestBody AccountDicRequestModel dic) {
        logger.info("修改字典键值信息接口，参数信息：" + JSONObject.toJSONString(dic));
        //校验参数信息
        if (null != BeanValidators.isValidateRequestUpdateDicKey(dic)) {
            logger.info("修改字典键值信息接口，参数信息校验失败：" + JSONObject.toJSONString(BeanValidators.isValidateRequestUpdateDicKey(dic)));
            return BeanValidators.isValidateRequestUpdateDicKey(dic);
        }//修改字典键值
        int countDicType = dictionaryService.update(dic);
        logger.info("修改字典键值成功，修改数量：" + countDicType);
        return new ResponseResult(ResponseCode.REQUEST_SUCCESS, ReturnCode.ACTIVE_SUCCESS.msg(), null);
    }

    /**
     * 更新字典
     *
     * @param dic
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseResult update(HttpServletRequest request, HttpServletResponse response,
                                 @RequestBody DictionaryRequestModel dic) {
        logger.info("修改字典类型信息接口，参数信息：" + JSONObject.toJSONString(dic));
        //校验参数信息
        if (null != BeanValidators.isValidateRequestUpdate(dic)) {
            logger.info("修改字典类型信息接口，参数信息校验失败：" + JSONObject.toJSONString(BeanValidators.isValidateRequestUpdate(dic)));
            return BeanValidators.isValidateRequestUpdate(dic);
        }
        //更新字典类型信息
        int countDicType = dictionaryTypeService.update(dic);
        logger.info("更新字典键值信息接口，参数信息：" + JSONObject.toJSONString(dic.getDataKeyList()));
        //保存dic键值
        int countDic = dictionaryService.save(dic.getDataKeyList(), dic.getType());
        logger.info("保存字典信息成功，保存数量：" + 0 + ",保存字典兼职信息成功，保存数量：" + countDic);
        return new ResponseResult(ResponseCode.REQUEST_SUCCESS, ReturnCode.ACTIVE_SUCCESS.msg(), null);
    }

    /**
     * 根据字典键id查询字典键值信息
     *
     * @param dic
     * @return
     */
    @RequestMapping(value = "/queryDicKeyById", method = RequestMethod.POST)
    public ResponseResult queryDicKeyById(HttpServletRequest request, HttpServletResponse response,
                                       @RequestBody DictionaryRequestModel dic) {
        logger.info("根据字典键id查询字典键值信息接口，参数信息：" + JSONObject.toJSONString(dic));
        //校验参数信息
        if (null != BeanValidators.isValidateRequestId(dic)) {
            logger.info("根据字典键id查询字典键值信息接口，参数信息校验失败：" + JSONObject.toJSONString(BeanValidators.isValidateRequestId(dic)));
            return BeanValidators.isValidateRequestId(dic);
        }
        AccountDic data = dictionaryService.queryDicKeyById(dic.getId());
        if (null != data) {
            logger.info("根据字典键id查询字典键值信息接口成功，返回结果：" + JSONObject.toJSONString(data));
            return new ResponseResult(ResponseCode.REQUEST_SUCCESS, ReturnCode.ACTIVE_SUCCESS.msg(), data);
        }
        logger.info("根据字典键id查询字典键值信息接口成功，暂无字典数据！");
        return new ResponseResult(ResponseCode.REQUEST_SUCCESS, ReturnCode.ERROR_NO_DIC_DATA.msg(), null);
    }





}
