package com.jzfq.rms.account.web.action;

import com.jzfq.rms.account.bean.AccountSystem;
import com.jzfq.rms.account.bean.AccountUser;
import com.jzfq.rms.account.bean.ResponseResult;
import com.jzfq.rms.account.constant.ResponseCode;
import com.jzfq.rms.account.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 系统维护控制类
 *
 * @author 大连桔子分期科技有限公司
 * @date 2018/4/4 15:29
 */
@RestController
@RequestMapping(value = "/system")
public class SystemAction {

    @Autowired
    private SystemService service;
    /**
     * 获取所属系统，只查询系统名称
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getSystemList", method = RequestMethod.GET)
    public ResponseResult getSystemList() throws Exception {
        List<Map<String,String>> systemList = service.getSystemList();
        return new ResponseResult(ResponseCode.REQUEST_SUCCESS, "正常调用", systemList);
    }
}
