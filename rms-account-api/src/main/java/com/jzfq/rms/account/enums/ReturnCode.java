package com.jzfq.rms.account.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * Response数据返回码对象
 * @ClassName:  ReturnCode
 * @author 大连桔子分期科技有限公司
 * @date:   2016年2月23日 下午2:16:35
 */
public enum ReturnCode {
    ERROR_REFUSE_IP(-60,"拒绝IP"),
    NOT_EXIST_SEND_HANDLER(-2, "不存在发送处理模块"),
    ACTIVE_EXCEPTION(-1, "异常"),
    ACTIVE_SUCCESS(200, "操作成功"),
    ACTIVE_FAILURE(500, "操作失败"),
    ERROR_PARAMS_NOT_NULL(-400, "参数不能为空"),
    ERROR_HEADER_NOT_NULL(04, "请求头不能为空"),
    ERROR_INVALID_TOKEN(05, "请求token验证不通过"),
    ERROR_UNKNOWN_API(06, "功能接口不存在"),
    ERROR_INVALID_ARGS(07, "请求参数不合法"),
    ERROR_SYSTEM_CONFIG_NULL(8,"系统配置出错"),
    ERROR_RESPONSE_NULL(9,"响应为空"),
    ERROR_THIRD_RESPONSE(10,"第三方接口错误"),
    ERROR_THIRD_RRSPONSE_NULL(11,"第三方返回为空"),
    ERROR_THIRD_RRSPONSE_NON_JSON(12,"d第三方返回一个空的json"),
    ERROR_TASK_ID_NULL(13,"根据订单号获取taskId失败"),
    ERROR_NOT_FOUNT_EVENT_ID(14,"获取不到eventId"),
    ERROR_NOT_FOUNT_STRATEGE_ID(15,"获取不到strategyId"),
    ACTIVE_THIRD_RPC(100,"远程调用接口中"),
    REQUEST_THIRD_GETING(150,"数据抓取中"),
    REQUEST_SAME_EXCUTING(199,"有相同的请求正在执行"),
    REQUEST_SUCCESS(200,"请求成功"),
    ERROR_PARAMS(400, "参数不完整"),
    ERROR_DUPLICATE(401, "重复操作"),
    ERROR_AUTH(402, "无权限"),
    ERROR_PARAMS_DECRYPT(402, "参数解密失败"),
    ERROR_WRONG(403, "用户无法使用此系统"),
    ERROR_RESOURCES(404, "请求资源不存在"),
    ERROR_PARAMS_FORMAT(500, "参数格式错误"),
    ERROR_SERVER(503, "系统异常"),
    ERROR_USER_TYPE_ERROR(1111, "用户类型参数错误"),
    ERROR_RSLL_PARAMS_ERROR(2222, "调用融360接口，返回参数RSL发生错误"),
    REQUEST_NO_EXIST_DATA(200,"此数据mongodb不存在，请删除缓存重新拉取"),
    ERROR_NO_DIC_DATA  (-400, "暂无字典数据"),
    ERROR_NO_DEPT_DATA  (-400, "暂无机构数据"),
    ERROR_NO_MENU_DATA  (-400, "暂无菜单数据"),
    ERROR_NO_OPERATE_DATA  (-400, "暂无操作键值数据"),
    ERROR_NO_MENU_OPERATE  (-200, "暂无操作权限数据"),
    ERROR_EXIST_DICNAME  (-300, "该字典名称已经存在"),
    ERROR_DIC_TYPE (-400, "字典类型不能为空！"),
    ERROR_DIC_ID (-400, "字典id不能为空！"),
    ERROR_DIC_TYPE_NAME (-400, "字典名称不能为空！"),
    ERROR_UPDATE_BY (-400, "更新人不能为空！"),
    ERROR_UPDATE_DATE (-400, "更新时间不能为空！"),
    ERROR_DIC_LABEL (-400, "字典键不能为空！"),
    ERROR_DIC_DICVALUE (-400, "字典值不能为空！"),
    ERROR_DIC_SORT (-400, "字典排序不能为空！"),
    ERROR_DIC_ISUSING_TYPE (-400, "启用/停用类型不能为空！"),
    ERROR_DEBT_DEBT_NO (-400, "机构编号不能为空！"),
    ERROR_DEBT_PARENT_NO (-400, "上级机构编号不能为空！"),
    ERROR_DEBT_DEPT_NAME (-400, "机构名称不能为空！"),
    ERROR_DEBT_DEPT_TYPE (-400, "机构类型不能为空！"),
    ERROR_DEBT_DEPT_SORT (-400, "排序值不能为空！"),
    ERROR_DEBT_DEPT_PRIMARYPERSION (-400, "主负责人不能为空！"),
    ERROR_DEBT_DEPT_USER_NOS (-400, "用户编号集合不能为空！"),
    ERROR_MENU_MENU_NOS (-400, "菜单编号集合不能为空！"),
    ERROR_MENU_MENU_NO (-400, "菜单编号不能为空！"),
    ERROR_MENU_EXIST_MENU_NAME (-400, "菜单名称已存在！"),
    ERROR_MENU_EXIST_MENU_HREF (-400, "菜单链接已存在！"),
    ERROR_MENU_EXIST_MENU_PERMISSION (-400, "菜单标识已存在！"),
    ERROR_MENU_MENU_NAME (-400, "菜单名称不能为空！"),
    ERROR_MENU_MENU_HREF (-400, "菜单链接不能为空！"),
    ERROR_MENU_MENU_PERMISSION (-400, "菜单标识不能为空！"),
    ERROR_MENU_OPERATE_IDS (-400, "操作权限id集合不能为空！"),
    ERROR_MENU_PARENT_NO (-400, "上级菜单编码不能为空！"),
    ERROR_MENU_SORT (-400, "菜单排序值不能为空！"),
    ERROR_OPERATE_KEY (-400, "操作名称不能为空！"),
    ERROR_OPERATE_KEY_VALUE (-400, "操作键值不能为空！"),
    ERROR_SYSTEM_NO (-400, "所属系统编码不能为空！"),
    ERROR_SORT_FORMATE (-400, "排序值格式校验失败！"),
    ERROR_DATE_FORMATE (-400, "日期格式校验失败！格式：yyyy-MM-dd hh:mm:ss"),
    ERROR_OPERATE_ID (-400, "操作id不能为空！"),
    ERROR_UNKOWN_ERROR(9999, "未知错误,响应数据为null"),


    //登录
    UN_FINDNAME(16, "用户名验证失败"),
    UN_FINDPWD(17, "密码验证失败"),
    VALIDATE_CODE_FALSE(18, "验证码验证失败"),
    NAME_PWD_FALSE(19, "用户名或密码错误"),

    PASSWORD_EMPTY(20, "密码不能为空"),
    NAME_EMPTY(21, "用户名不能为空"),
    VALIDAT_CODE_EMPTY(22, "验证码不能为空"),

    SUCCESS(23, "登录成功"),
    FALED(24, "登录失败"),


    EMPTY_TOKEN(25, "请求token不能为空"),
    TOKEN_OVERDUE(26, "请求token已过期");

    private int code;

    private String msg;

    private ReturnCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static ReturnCode codeToEnum(int code) {

        ReturnCode[] values = ReturnCode.values();
        for (ReturnCode returnCode : values) {
            if (returnCode.code == code) {
                return returnCode;
            }
        }
        return ACTIVE_EXCEPTION;
    }

    public int code() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String msg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Map<String, ?> map() {
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("code", this.code);
        hashMap.put("msg", this.msg);
        return hashMap;
    }

    public Map<String, ?> map(int code) {
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("code", code);
        hashMap.put("msg", this.msg);
        return hashMap;
    }

    public Map<String, ?> map(Object msg) {
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("code", this.code);
        hashMap.put("msg", msg);
        return hashMap;
    }

    public Map<String, ?> map(int code, Object msg) {
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("code", code);
        hashMap.put("msg", msg);
        return hashMap;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append("{\"code\":").append(this.code).append(",");
        sb.append("\"msg\":\"").append(this.msg).append("\"}");

        return sb.toString();
    }


}
