package com.jzfq.rms.account.constant;

/**
 * @author 大连桔子分期科技有限公司
 * @date 2017年9月05日 20:04:55
 */
public class ResponseCode {

    /**
     * 调用成功响应码
     */
    public final static int REQUEST_SUCCESS = 200;

    /**
     * 字典名称已经存在返回码
     */
    public final static int REQUEST_EXIST_DICNAME = -300;

    /**
     * 拒绝IP响应码
     */
    public static final int REQUEST_ERROR_REFUSE_IP = -60;

    /**
     * 程序异常
     */
    public static final int REQUEST_ERROR_PROGRAM_EXCEPTION = -100;
    /**
     * 参数格式
     */
    public static final int RESPONSE_CODE_ILLEGAL_ARGUMENT = -10;

    /**
     * 业务参数不能为空
     */
    public static final int BUSINESS_PARAM_ERROR = -1;
}
