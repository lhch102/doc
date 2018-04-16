package com.jzfq.rms.account.web.action;

import com.alibaba.fastjson.JSONObject;
import com.jzfq.rms.account.bean.AccountDept;
import com.jzfq.rms.account.bean.AccountDicType;
import com.jzfq.rms.account.bean.AccountUser;
import com.jzfq.rms.account.bean.ResponseResult;
import com.jzfq.rms.account.common.LoginCommon;
import com.jzfq.rms.account.common.service.GenerateObjectNoService;
import com.jzfq.rms.account.constant.ResponseCode;
import com.jzfq.rms.account.enums.EnumEnableFlag;
import com.jzfq.rms.account.enums.ReturnCode;
import com.jzfq.rms.account.service.AccountDeptService;
import com.jzfq.rms.account.service.AccountUserDeptService;
import com.jzfq.rms.account.utils.CommonBeanUtils;
import com.jzfq.rms.account.web.BeanValidators.BeanValidators;
import com.jzfq.rms.account.web.requestModel.AccountDeptRequestModel;
import com.jzfq.rms.account.web.requestModel.DictionaryRequestModel;
import com.jzfq.rms.account.web.responseModel.InitResponseModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 机构控制类
 *
 * @author 大连桔子分期科技有限公司
 * @date 2018/4/8 10:03
 */
@RestController
@RequestMapping(value = "/dept")
public class AccountDeptAction {

    private final static Logger logger = LoggerFactory.getLogger(AccountDeptAction.class);

    @Autowired
    private AccountDeptService accountDeptService;
    @Autowired
    GenerateObjectNoService generateObjectNoService;
    @Autowired
    AccountUserDeptService accountUserDeptService;
    @Autowired
    LoginCommon loginCommon;

    /**
     * 获取机构列表
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public ResponseResult list(@RequestBody AccountDept debt, HttpServletRequest request, HttpServletResponse response) throws Exception {
        logger.info("获取机构数据列表接口，参数信息：" + JSONObject.toJSONString(debt));
        List<AccountDept> childDept = accountDeptService.getDeptList(debt);
        if (null != childDept && childDept.size() > 0) {
            logger.info("获取机构数据列表成功，返回结果：" + JSONObject.toJSONString(childDept));
            return new ResponseResult(ResponseCode.REQUEST_SUCCESS, ReturnCode.ACTIVE_SUCCESS.msg(), childDept);
        }
        logger.info("获取机构数据列表成功，暂无机构数据！");
        return new ResponseResult(ResponseCode.REQUEST_SUCCESS, ReturnCode.ERROR_NO_DEPT_DATA.msg(), null);
    }

    /**
     * 启用/停用机构
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/isUsing", method = RequestMethod.POST)
    public ResponseResult isUsing(HttpServletRequest request, HttpServletResponse response,
                                  @RequestBody AccountDeptRequestModel debt)
            throws Exception {
        logger.info("根据机构编码debtNo更新启用/停用机构信息接口，参数信息：" + JSONObject.toJSONString(debt));
        //校验参数信息
        if (null != BeanValidators.isValidateRequestDebtIsUsing(debt)) {
            logger.info("根据机构编号debtNo更新启用/停用机构信息接口，参数信息校验失败：" + JSONObject.toJSONString(BeanValidators.isValidateRequestDebtIsUsing(debt)));
            return BeanValidators.isValidateRequestDebtIsUsing(debt);
        }
        int num = accountDeptService.isUsing(debt.getType(), debt.getDeptNos());
        if (num > 0) {
            logger.info("根据机构编号debtNo更新" + EnumEnableFlag.getEnum(debt.getType()).getMessage() + "机构成功，返回结果：" + num);
            return new ResponseResult(ResponseCode.REQUEST_SUCCESS, ReturnCode.ACTIVE_SUCCESS.msg(), null);
        }
        logger.info("根据机构编号debtNo更新" + EnumEnableFlag.getEnum(debt.getType()).getMessage() + "机构失败，返回结果：0");
        return new ResponseResult(ResponseCode.REQUEST_SUCCESS, ReturnCode.ACTIVE_FAILURE.msg(), null);
    }

    /**
     * 初始化机构接口
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
        logger.info("初始化机构成功，初始化数据：" + new InitResponseModel(createBy, debtNo).toString());
        return new ResponseResult(ResponseCode.REQUEST_SUCCESS, ReturnCode.ACTIVE_SUCCESS.msg(), new InitResponseModel(createBy, debtNo));
    }

    /**
     * 根据机构编号查询机构信息
     *
     * @param debt
     * @return
     */
    @RequestMapping(value = "/queryOriginByNo", method = RequestMethod.POST)
    public ResponseResult queryOriginByNo(HttpServletRequest request, HttpServletResponse response,
                                       @RequestBody AccountDeptRequestModel debt) {
        logger.info("根据机构编号查询机构信息接口，参数信息：" + JSONObject.toJSONString(debt));
        //校验参数信息
        if (null != BeanValidators.isValidateRequestDebtNo(debt)) {
            logger.info("根据机构debtNo查询信息接口，参数信息校验失败：" + JSONObject.toJSONString(BeanValidators.isValidateRequestDebtNo(debt)));
            return BeanValidators.isValidateRequestDebtNo(debt);
        }
        AccountDept data = accountDeptService.queryModel(debt.getDeptNo());
        if (null != data) {
            logger.info("根据机构编号查询机构信息接口成功，返回结果：" + JSONObject.toJSONString(data));
            return new ResponseResult(ResponseCode.REQUEST_SUCCESS, ReturnCode.ACTIVE_SUCCESS.msg(), data);
        }
        logger.info("根据机构编号查询机构信息接口成功，暂无机构数据！");
        return new ResponseResult(ResponseCode.REQUEST_SUCCESS, ReturnCode.ERROR_NO_DEPT_DATA.msg(), null);
    }

    /**
     * 保存机构
     *
     * @param debt
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseResult save(HttpServletRequest request, HttpServletResponse response,
                               @RequestBody AccountDeptRequestModel debt) {
        logger.info("保存机构信息接口，参数信息：" + JSONObject.toJSONString(debt));
        //校验参数信息
        if (null != BeanValidators.isValidateRequestSaveDept(debt)) {
            logger.info("保存机构信息接口，参数信息校验失败：" + JSONObject.toJSONString(BeanValidators.isValidateRequestSaveDept(debt)));
            return BeanValidators.isValidateRequestSaveDept(debt);
        }
        //保存机构主信息
        int countDept = accountDeptService.save(debt);
        logger.info("保存机构-用户关联信息接口，参数信息：" + debt.getUserIds());
        //保存机构-用户关联信息
        int countDeptUser = accountUserDeptService.save(debt.getUserIds(),debt.getDeptNo());
        logger.info("保存机构-用户关联信息成功，保存数量：" + countDept + ",保存机构-用户关联信息成功，保存数量：" + countDeptUser);
        return new ResponseResult(ResponseCode.REQUEST_SUCCESS, ReturnCode.ACTIVE_SUCCESS.msg(), null);
    }

    /**
     * 修改机构
     *
     * @param debt
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseResult update(HttpServletRequest request, HttpServletResponse response,
                               @RequestBody AccountDeptRequestModel debt) {
        logger.info("修改机构信息接口，参数信息：" + JSONObject.toJSONString(debt));
        //校验参数信息
        if (null != BeanValidators.isValidateRequestSaveDept(debt)) {
            logger.info("修改机构信息接口，参数信息校验失败：" + JSONObject.toJSONString(BeanValidators.isValidateRequestSaveDept(debt)));
            return BeanValidators.isValidateRequestSaveDept(debt);
        }
        //保存机构主信息
        int countDept = accountDeptService.update(debt);
        logger.info("保存机构-用户关联信息接口，参数信息：" + debt.getUserIds());
        //保存机构-用户关联信息
        int countDeptUser = accountUserDeptService.update(debt.getUserIds(),debt.getDeptNo());
        logger.info("保存机构-用户关联信息成功，保存数量：" + countDept + ",保存机构-用户关联信息成功，保存数量：" + countDeptUser);
        return new ResponseResult(ResponseCode.REQUEST_SUCCESS, ReturnCode.ACTIVE_SUCCESS.msg(), null);
    }

    /**
     * 移除用户
     *
     * @param debt
     * @return
     */

    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public ResponseResult remove(HttpServletRequest request, HttpServletResponse response,
                                  @RequestBody AccountDeptRequestModel debt)
            throws Exception {
        logger.info("根据机构编号更新移除用户信息接口，参数信息：" + JSONObject.toJSONString(debt));
        //校验参数信息
        if (null != BeanValidators.isValidateRequestRemoveDebt(debt)) {
            logger.info("根据机构编号更新移除用户信息接口，参数信息校验失败：" + JSONObject.toJSONString(BeanValidators.isValidateRequestRemoveDebt(debt)));
            return BeanValidators.isValidateRequestRemoveDebt(debt);
        }
        int num = accountUserDeptService.del(debt.getUserIds(), debt.getDeptNo());
        if (num > 0) {
            logger.info("根据机构编号更新移除用户成功，返回结果：" + num);
            return new ResponseResult(ResponseCode.REQUEST_SUCCESS, ReturnCode.ACTIVE_SUCCESS.msg(), null);
        }
        logger.info("根据机构编号更新移除用户失败，返回结果：0");
        return new ResponseResult(ResponseCode.REQUEST_SUCCESS, ReturnCode.ACTIVE_FAILURE.msg(), null);
    }




}
