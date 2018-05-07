package com.jzfq.rms.account.web.action;

import com.alibaba.fastjson.JSONObject;
import com.jzfq.rms.account.bean.*;
import com.jzfq.rms.account.common.LoginCommon;
import com.jzfq.rms.account.common.PageData;
import com.jzfq.rms.account.common.service.GenerateObjectNoService;
import com.jzfq.rms.account.constant.ResponseCode;
import com.jzfq.rms.account.enums.EnumIsShow;
import com.jzfq.rms.account.enums.ReturnCode;
import com.jzfq.rms.account.exception.BusinessException;
import com.jzfq.rms.account.service.AccountMenuOperateService;
import com.jzfq.rms.account.service.AccountMenuService;
import com.jzfq.rms.account.service.AccountOperateService;
import com.jzfq.rms.account.utils.StringUtil;
import com.jzfq.rms.account.web.BeanValidators.BeanValidators;
import com.jzfq.rms.account.web.requestModel.AccountMenuRequestModel;
import com.jzfq.rms.account.web.responseModel.InitResponseModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 菜单控制类
 * @author 大连桔子分期科技有限公司
 * @date 2018/4/8 16:34
 */
@RestController
@RequestMapping(value = "/menu")
public class AccountMenuAction {

    private final static Logger logger = LoggerFactory.getLogger(AccountMenuAction.class);


    @Autowired
    private AccountMenuService accountMenuService;
    @Autowired
    GenerateObjectNoService generateObjectNoService;
    @Autowired
    LoginCommon loginCommon;
    @Autowired
    AccountOperateService accountOperateService;
    @Autowired
    AccountMenuOperateService accountMenuOperateService;


    /**
     * 根据系统编号查询菜单
     * @param systemNo
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getMenuListBySystemNo", method = RequestMethod.GET)
    public ResponseResult getMenuListBySystemNo(@RequestParam String systemNo) throws Exception {
        if (StringUtil.isEmpty(systemNo)) {
            throw new BusinessException(-1, "参数：systemNo不能为空", true);
        }
        AccountMenu childMenu = accountMenuService.getMenuListBySystemNo(systemNo);
        return new ResponseResult(ResponseCode.REQUEST_SUCCESS, "正常调用",childMenu);
    }

    /**
     * 获取菜单列表
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public ResponseResult list(@RequestBody AccountMenu menu, HttpServletRequest request, HttpServletResponse response) throws Exception {
        logger.info("获取菜单数据列表接口，参数信息：" + JSONObject.toJSONString(menu));
        List<AccountMenu> childMenu = accountMenuService.queryMenuList(menu);
        if (null != childMenu && childMenu.size() > 0) {
            logger.info("获取菜单数据列表成功，返回结果：" + JSONObject.toJSONString(childMenu));
            return new ResponseResult(ResponseCode.REQUEST_SUCCESS, ReturnCode.ACTIVE_SUCCESS.msg(), childMenu);
        }
        logger.info("获取菜单数据列表成功，暂无菜单数据！");
        return new ResponseResult(ResponseCode.REQUEST_SUCCESS, ReturnCode.ERROR_NO_DIC_DATA.msg(), null);
    }


    /**
     * 初始化菜单接口
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
        String debtNo = generateObjectNoService.generateMenuNo(null);
        logger.info("初始化菜单成功，初始化数据：" + new InitResponseModel(createBy, debtNo).toString());
        return new ResponseResult(ResponseCode.REQUEST_SUCCESS, ReturnCode.ACTIVE_SUCCESS.msg(), new InitResponseModel(createBy, debtNo));
    }

    /**
     * 显示/隐藏菜单
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/showOrVisable", method = RequestMethod.POST)
    public ResponseResult showOrVisable(HttpServletRequest request, HttpServletResponse response,
                                  @RequestBody AccountMenuRequestModel menuRequestModel)
            throws Exception {
        logger.info("根据菜单编码menuNo更新显示/隐藏菜单信息接口，参数信息：" + JSONObject.toJSONString(menuRequestModel));
        //校验参数信息
        if (null != BeanValidators.isValidateRequestDebtIsShowingMenu(menuRequestModel)) {
            logger.info("根据菜单编码menuNo更新显示/隐藏菜单信息接口，参数信息校验失败：" + JSONObject.toJSONString(BeanValidators.isValidateRequestDebtIsShowingMenu(menuRequestModel)));
            return BeanValidators.isValidateRequestDebtIsShowingMenu(menuRequestModel);
        }
        int num = accountMenuService.showOrVisable(menuRequestModel.getType(), menuRequestModel.getMenuNos());
        if (num > 0) {
            logger.info("根据菜单编码menuNo更新" + EnumIsShow.getEnum(menuRequestModel.getType()).getMessage() + "菜单成功，返回结果：" + num);
            return new ResponseResult(ResponseCode.REQUEST_SUCCESS, ReturnCode.ACTIVE_SUCCESS.msg(), null);
        }
        logger.info("根据单编码menuNo更新" + EnumIsShow.getEnum(menuRequestModel.getType()).getMessage() + "菜单失败，返回结果：0");
        return new ResponseResult(ResponseCode.REQUEST_SUCCESS, ReturnCode.ACTIVE_FAILURE.msg(), null);
    }


    /**
     * 查询菜单信息( 根据菜单编号)
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/queryMenuById", method = RequestMethod.POST)
    public ResponseResult queryMenuById(HttpServletRequest request, HttpServletResponse response,
                                        @RequestBody AccountMenuRequestModel model) {
        logger.info("根据菜单编号menuNo查询菜单信息接口，参数信息：" + JSONObject.toJSONString(model));
        //校验参数信息
        if (null != BeanValidators.isValidateRequestMenuId(model)) {
            logger.info("根据菜单编号menuNo查询菜单信息接口，参数信息校验失败：" + JSONObject.toJSONString(BeanValidators.isValidateRequestMenuId(model)));
            return BeanValidators.isValidateRequestMenuId(model);
        }
        AccountMenu data = accountMenuService.queryMenuById(model.getMenuNo());
        if (null != data) {
            logger.info("根据菜单编号menuNo查询菜单接口成功，返回结果：" + JSONObject.toJSONString(data));
            return new ResponseResult(ResponseCode.REQUEST_SUCCESS, ReturnCode.ACTIVE_SUCCESS.msg(), data);
        }
        logger.info("根据菜单编号menuNo查询菜单接口成功，暂无菜单数据！");
        return new ResponseResult(ResponseCode.REQUEST_SUCCESS, ReturnCode.ERROR_NO_MENU_DATA.msg(), null);

    }



    /**
     * 查询操作权限列表（根据菜单编号）
     *
     * @param menu
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/queryOperate", method = RequestMethod.POST)
    public ResponseResult queryOperate(@RequestBody AccountMenuRequestModel menu, HttpServletRequest request, HttpServletResponse response) throws Exception {
        logger.info("获取分页查询菜单操作权限列表接口，参数信息：" + JSONObject.toJSONString(menu));
        //校验参数信息
        if (null != BeanValidators.isValidateRequestList(menu)) {
            logger.info("获取分页查询菜单操作权限列表接口，参数信息校验失败：" + JSONObject.toJSONString(BeanValidators.isValidateRequestList(menu)));
            return BeanValidators.isValidateRequestList(menu);
        }
        PageData<AccountOperate> data = accountOperateService.list(menu);
        if (null != data) {
            logger.info("获取菜单操作权限列表成功，返回结果：" + JSONObject.toJSONString(data));
            return new ResponseResult(ResponseCode.REQUEST_SUCCESS, ReturnCode.ACTIVE_SUCCESS.msg(), data);
        }
        logger.info("获取菜单操作权限列表成功，暂无操作权限数据！");
        return new ResponseResult(ResponseCode.REQUEST_SUCCESS, ReturnCode.ERROR_NO_MENU_OPERATE.msg(), null);

    }

    /**
     * 查询操作权限列表（根据菜单编号 不是分页）
     *
     * @param menu
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/queryOperateByMenuNo", method = RequestMethod.POST)
    public ResponseResult queryOperateByMenuNo(@RequestBody AccountMenuRequestModel menu, HttpServletRequest request, HttpServletResponse response) throws Exception {
        logger.info("获取分页查询菜单操作权限列表接口，参数信息：" + JSONObject.toJSONString(menu));
        //校验参数信息
        if (null != BeanValidators.isValidateRequestList(menu)) {
            logger.info("获取分页查询菜单操作权限列表接口，参数信息校验失败：" + JSONObject.toJSONString(BeanValidators.isValidateRequestList(menu)));
            return BeanValidators.isValidateRequestList(menu);
        }
        List<AccountOperate> data = accountOperateService.queryOperateByMenuNo(menu);
        if (null != data) {
            logger.info("获取菜单操作权限列表成功，返回结果：" + JSONObject.toJSONString(data));
            return new ResponseResult(ResponseCode.REQUEST_SUCCESS, ReturnCode.ACTIVE_SUCCESS.msg(), data);
        }
        logger.info("获取菜单操作权限列表成功，暂无操作权限数据！");
        return new ResponseResult(ResponseCode.REQUEST_SUCCESS, ReturnCode.ERROR_NO_MENU_OPERATE.msg(), null);

    }

    /**
     * 校验菜单名称是否存在
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/checkMenuName", method = RequestMethod.POST)
    public ResponseResult checkMenuName(HttpServletRequest request, HttpServletResponse response,
                                   @RequestBody AccountMenuRequestModel model)
            throws Exception {
        logger.info("根据菜单id，菜单名称校验该字典信息是否存在接口，参数信息：" + JSONObject.toJSONString(model));
        //校验参数信息
        if (null != BeanValidators.isValidateRequestCheckMenuName(model)) {
            logger.info("根据菜单id，菜单名称校验该字典信息是否存在接口，参数信息校验失败：" + JSONObject.toJSONString(BeanValidators.isValidateRequestCheckMenuName(model)));
            return BeanValidators.isValidateRequestCheckMenuName(model);
        }
        boolean flag = accountMenuService.checkMenu(model);
        if (flag) {
            logger.info("该菜单名称" + model.getMenuName() + "已经存在！");
            return new ResponseResult(ReturnCode.ERROR_MENU_EXIST_MENU_NAME.code(), ReturnCode.ERROR_MENU_EXIST_MENU_NAME.msg(), null);
        }
        logger.info("该菜单名称" + model.getMenuName() + "可以使用！");
        return new ResponseResult(ResponseCode.REQUEST_SUCCESS, ReturnCode.ACTIVE_SUCCESS.msg(), null);
    }

    /**
     * 校验菜单链接是否存在
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/checkMenuHref", method = RequestMethod.POST)
    public ResponseResult checkMenuHref(HttpServletRequest request, HttpServletResponse response,
                                        @RequestBody AccountMenuRequestModel model)
            throws Exception {
        logger.info("根据菜单id，菜单链接校验该字典信息是否存在接口，参数信息：" + JSONObject.toJSONString(model));
        //校验参数信息
        if (null != BeanValidators.isValidateRequestCheckMenuHref(model)) {
            logger.info("根据菜单id，菜单链接校验该字典信息是否存在接口，参数信息校验失败：" + JSONObject.toJSONString(BeanValidators.isValidateRequestCheckMenuHref(model)));
            return BeanValidators.isValidateRequestCheckMenuHref(model);
        }
        boolean flag = accountMenuService.checkMenu(model);
        if (flag) {
            logger.info("该菜单链接" + model.getHref() + "已经存在！");
            return new ResponseResult(ReturnCode.ERROR_MENU_EXIST_MENU_HREF.code(), ReturnCode.ERROR_MENU_EXIST_MENU_HREF.msg(), null);
        }
        logger.info("该菜单链接" + model.getHref() + "可以使用！");
        return new ResponseResult(ResponseCode.REQUEST_SUCCESS, ReturnCode.ACTIVE_SUCCESS.msg(), null);
    }

    /**
     * 校验菜单标识是否存在
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/checkMenuPermission", method = RequestMethod.POST)
    public ResponseResult checkMenuPermission(HttpServletRequest request, HttpServletResponse response,
                                        @RequestBody AccountMenuRequestModel model)
            throws Exception {
        logger.info("根据菜单id，菜单标识校验该字典信息是否存在接口，参数信息：" + JSONObject.toJSONString(model));
        //校验参数信息
        if (null != BeanValidators.isValidateRequestCheckMenuPermission(model)) {
            logger.info("根据菜单id，菜单标识校验该字典信息是否存在接口，参数信息校验失败：" + JSONObject.toJSONString(BeanValidators.isValidateRequestCheckMenuPermission(model)));
            return BeanValidators.isValidateRequestCheckMenuPermission(model);
        }
        boolean flag = accountMenuService.checkMenu(model);
        if (flag) {
            logger.info("该菜单标识" + model.getHref() + "已经存在！");
            return new ResponseResult(ReturnCode.ERROR_MENU_EXIST_MENU_PERMISSION.code(), ReturnCode.ERROR_MENU_EXIST_MENU_PERMISSION.msg(), null);
        }
        logger.info("该菜单标识" + model.getHref() + "可以使用！");
        return new ResponseResult(ResponseCode.REQUEST_SUCCESS, ReturnCode.ACTIVE_SUCCESS.msg(), null);
    }


    /**
     * 删除操作权限
     *
     * @param model
     * @return
     */

    @RequestMapping(value = "/delOperate", method = RequestMethod.POST)
    public ResponseResult delOperate(HttpServletRequest request, HttpServletResponse response,
                                 @RequestBody AccountMenuRequestModel model)
            throws Exception {
        logger.info("根据菜单编号，操作权限id删除菜单操作权限信息接口，参数信息：" + JSONObject.toJSONString(model));
        //校验参数信息
        if (null != BeanValidators.isValidateRequestDelOperate(model)) {
            logger.info("根据菜单编号，操作权限id删除菜单操作权限信息接口，参数信息校验失败：" + JSONObject.toJSONString(BeanValidators.isValidateRequestDelOperate(model)));
            return BeanValidators.isValidateRequestDelOperate(model);
        }
        //删除操作权限表
        int delOperate = accountOperateService.del(model.getIds());
        logger.info("根据菜单编号，操作权限id删除菜单操作权限关联信息接口，参数信息：" + JSONObject.toJSONString(model));
        //删除菜单-操作权限关联表
        int delMenuOperate = accountMenuOperateService.del(model.getIds(),model.getMenuNo());
        logger.info("根据操作权限id删除操作权限信息成功，删除数量："+delOperate+"根据菜单编号，操作权限id删除菜单操作权限关联数据成功，删除数量：" + delMenuOperate);
        return new ResponseResult(ResponseCode.REQUEST_SUCCESS, ReturnCode.ACTIVE_SUCCESS.msg(), null);
    }


    /**
     * 保存菜单
     *
     * @param model
     * @return
     */

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseResult save(HttpServletRequest request, HttpServletResponse response,
                               @RequestBody AccountMenuRequestModel model) {
        logger.info("保存菜单信息接口，参数信息：" + JSONObject.toJSONString(model));
        //校验参数信息
        if (null != BeanValidators.isValidateRequestSaveMenu(model)) {
            logger.info("保存菜单信息接口，参数信息校验失败：" + JSONObject.toJSONString(BeanValidators.isValidateRequestSaveMenu(model)));
            return BeanValidators.isValidateRequestSaveMenu(model);
        }
        //保存菜单主信息
        int countDept = accountMenuService.save(model);
        logger.info("保存菜单-操作权限关联信息接口，参数信息：" + model.getMenuOperateList());
        //保存菜单操作权限关联信息
        int countDeptUser = accountOperateService.save(model);
        logger.info("保存菜单-操作权限关联信息成功，保存数量：" + countDept + ",保存菜单-操作权限关联信息成功，保存数量：" + countDeptUser);
        return new ResponseResult(ResponseCode.REQUEST_SUCCESS, ReturnCode.ACTIVE_SUCCESS.msg(), null);
    }

    /**
     * 更新菜单
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseResult update(HttpServletRequest request, HttpServletResponse response,
                               @RequestBody AccountMenuRequestModel model) {
        logger.info("修改菜单信息接口，参数信息：" + JSONObject.toJSONString(model));
        //校验参数信息
        if (null != BeanValidators.isValidateRequestUpdateMenu(model)) {
            logger.info("修改菜单信息接口，参数信息校验失败：" + JSONObject.toJSONString(BeanValidators.isValidateRequestUpdateMenu(model)));
            return BeanValidators.isValidateRequestUpdateMenu(model);
        }
        //保存菜单主信息
        int countMenu = accountMenuService.update(model);
        logger.info("保存菜单-操作权限关联信息接口，参数信息：" + model.getMenuOperateList());
        //保存菜单操作权限关联信息
        int countOperate = accountOperateService.save(model);
        logger.info("保存菜单-操作权限关联信息成功，保存数量：" + countMenu + ",保存菜单-操作权限关联信息成功，保存数量：" + countOperate);
        return new ResponseResult(ResponseCode.REQUEST_SUCCESS, ReturnCode.ACTIVE_SUCCESS.msg(), null);
    }

    /**
     * 编辑操作权限
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/updateOperate", method = RequestMethod.POST)
    public ResponseResult updateOperate(HttpServletRequest request, HttpServletResponse response,
                                 @RequestBody AccountOperate model) {
        logger.info("修改操作权限信息接口，参数信息：" + JSONObject.toJSONString(model));
        //校验参数信息
        if (null != BeanValidators.isValidateRequestUpdateOperateMenu(model)) {
            logger.info("修改操作权限信息接口，参数信息校验失败：" + JSONObject.toJSONString(BeanValidators.isValidateRequestUpdateOperateMenu(model)));
            return BeanValidators.isValidateRequestUpdateOperateMenu(model);
        }
        //保存菜单主信息
        int countOperate = accountOperateService.update(model);
//        logger.info("保存机构-用户关联信息接口，参数信息：" + model());
//        //保存菜单-操作权限关联信息
//        int countDeptUser = accountUserDeptService.update(debt.getUserIds(),debt.getDeptNo());
        logger.info("修改操作权限关联信息成功，保存数量：" + countOperate);
        return new ResponseResult(ResponseCode.REQUEST_SUCCESS, ReturnCode.ACTIVE_SUCCESS.msg(), null);
    }

    /**
     * 根据操作键id查询操作键值信息
     *
     * @param dic
     * @return
     */
        @RequestMapping(value = "/queryOperateById", method = RequestMethod.POST)
    public ResponseResult queryOperateById(HttpServletRequest request, HttpServletResponse response,
                                          @RequestBody AccountMenuRequestModel dic) {
        logger.info("根据操作键id查询操作键值信息接口，参数信息：" + JSONObject.toJSONString(dic));
        //校验参数信息
        if (null != BeanValidators.isValidateRequestOperateId(dic)) {
            logger.info("根据操作键id查询操作键值信息接口，参数信息校验失败：" + JSONObject.toJSONString(BeanValidators.isValidateRequestOperateId(dic)));
            return BeanValidators.isValidateRequestOperateId(dic);
        }
        AccountOperate data = accountOperateService.queryByOperateId(dic.getId());
        if (null != data) {
            logger.info("根据操作键id查询操作键值信息接口成功，返回结果：" + JSONObject.toJSONString(data));
            return new ResponseResult(ResponseCode.REQUEST_SUCCESS, ReturnCode.ACTIVE_SUCCESS.msg(), data);
        }
        logger.info("根据操作键id查询操作键值信息接口成功，暂无字典数据！");
        return new ResponseResult(ResponseCode.REQUEST_SUCCESS, ReturnCode.ERROR_NO_OPERATE_DATA.msg(), null);
    }






}
