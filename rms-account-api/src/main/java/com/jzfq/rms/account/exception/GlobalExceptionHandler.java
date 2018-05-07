package com.jzfq.rms.account.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jzfq.rms.account.bean.ResponseResult;
import com.jzfq.rms.account.constant.ResponseCode;
import com.jzfq.rms.account.context.TraceIDThreadLocal;

/**
 * @author 大连桔子分期科技有限公司
 * @date 2017年9月05日 20:04:55
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(BusinessException.class)
    public ResponseResult handleBusinessException(BusinessException e) {
        LOGGER.error("业务异常:{}", e.getMessage());
        return new ResponseResult(e.getErrorCode(), e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseResult handleException(Exception e) {
        LOGGER.error("程序异常:", e);
        return new ResponseResult(ResponseCode.REQUEST_ERROR_PROGRAM_EXCEPTION, e.getMessage());
    }
}