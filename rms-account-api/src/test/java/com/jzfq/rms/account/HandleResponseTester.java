package com.jzfq.rms.account;

import com.jzfq.rms.account.common.service.GenerateObjectNoService;
import com.jzfq.util.JsonRpcUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 大连桔子分期科技有限公司
 * @date 2018/1/3 11:47.
 **/
@RunWith(SpringJUnit4ClassRunner.class) // 使用junit4进行测试
@ContextConfiguration(locations = { "file:src/main/resources/spring/applicationContext.xml" }) // 加载配置文件
public class HandleResponseTester {


    private static final String url = "http://127.0.0.1:9080/dic/list.json";

    final static Logger logger = LoggerFactory.getLogger(HandleResponseTester.class);


    // @org.junit.Test
    public void dic() throws IOException {
        Map<String,Object> map = new HashMap<String,Object>();

        map.put("label","0");
        map.put("type","0");
        map.put("description","1");
        map.put("pageNum","0");
        map.put("numPerPage","10");
       String result =  JsonRpcUtils.sendPost(url,map);
        logger.info(result);
    }

    @Autowired
    private GenerateObjectNoService generateObjectNoService;
    /**
     * 生成各种编号测试方法
     */
    @Test
    public void generateObjectNo(){
        System.out.println(generateObjectNoService.generateSystemNo());
    }
}
