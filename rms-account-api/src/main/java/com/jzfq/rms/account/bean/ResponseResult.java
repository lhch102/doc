package com.jzfq.rms.account.bean;

import com.alibaba.fastjson.JSONObject;
import com.jzfq.rms.account.constant.ResponseCode;

/**
 * @author 大连桔子分期科技有限公司
 * @date 2017年9月05日 20:04:55
 */
public class ResponseResult extends BaseBean {

    /**
     * 响应状态
     */
    private int code = ResponseCode.REQUEST_SUCCESS;
    /**
     * 响应状态说明
     */
    private String msg = "正常调用";
    /**
     * 响应数据
     */
    private Object data = new JSONObject();

    public ResponseResult() {

    }
    public ResponseResult(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    public ResponseResult(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}