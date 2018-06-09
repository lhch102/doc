package com.jzfq.rms.product.service;



/**
 * @author 大连桔子分期科技有限公司
 * @date 2017年10月08日 21:04:55
 */
public interface IMailService {

  void send(String from, String to, String subject, String content);
}
